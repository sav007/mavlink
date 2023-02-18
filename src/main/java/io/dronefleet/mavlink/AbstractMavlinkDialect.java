package io.dronefleet.mavlink;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("rawtypes")
public class AbstractMavlinkDialect implements MavlinkDialect {

    private final String name;
    private final List<MavlinkDialect> dependencies;
    private final Map<Integer, Class> messages;
    private final Map<Class, Function<ByteBuffer, Object>> deserializers;

    public AbstractMavlinkDialect(
        String name,
        List<MavlinkDialect> dependencies,
        Map<Integer, Class> messages,
        Map<Class, Function<ByteBuffer, Object>> deserializers
    ) {
        this.name = name;
        this.dependencies = dependencies;
        this.messages = messages;
        this.deserializers = deserializers;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Class resolve(int messageId) {
        if (messages.containsKey(messageId)) {
            return messages.get(messageId);
        } else {
            for (MavlinkDialect dependency : dependencies) {
                Class result = dependency.resolve(messageId);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    @Override
    public boolean supports(int messageId) {
        if (messages.containsKey(messageId)) {
            return true;
        } else {
            for (MavlinkDialect dependency : dependencies) {
                if (dependency.supports(messageId)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Class> messageTypes() {
        return Stream.concat(
                messages.values().stream(),
                dependencies.stream()
                    .map(MavlinkDialect::messageTypes)
                    .flatMap(List::stream))
            .distinct()
            .collect(Collectors.toList());
    }

    @Override
    public boolean canDeserializeMessage(Class messageType) {
        return deserializers.containsKey(messageType) ||
            dependencies.stream().anyMatch(d -> d.canDeserializeMessage(messageType));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserializeMessage(Class messageType, byte[] data) {
        if (deserializers.containsKey(messageType)) {
            ByteBuffer buffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
            return (T) deserializers.get(messageType).apply(buffer);
        } else if (dependencies != null && dependencies.size() > 0) {
            for (MavlinkDialect dependency : dependencies) {
                if (dependency.canDeserializeMessage(messageType)) {
                    return dependency.deserializeMessage(messageType, data);
                }
            }
        }
        throw new IllegalArgumentException("No deserializer for message " + messageType + " found.");
    }
}
