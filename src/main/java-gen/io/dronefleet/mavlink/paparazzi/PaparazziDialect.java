package io.dronefleet.mavlink.paparazzi;

import io.dronefleet.mavlink.AbstractMavlinkDialect;
import io.dronefleet.mavlink.MavlinkDialect;
import io.dronefleet.mavlink.common.CommonDialect;
import io.dronefleet.mavlink.util.UnmodifiableMapBuilder;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class PaparazziDialect extends AbstractMavlinkDialect {
    /**
     * A list of all of the dependencies of this dialect.
     */
    private static final List<MavlinkDialect> dependencies = Arrays.asList(
            new CommonDialect());

    /**
     * A list of all message types supported by this dialect.
     */
    private static final Map<Integer, Class> messages = new UnmodifiableMapBuilder<Integer, Class>()
            .put(180, ScriptItem.class)
            .put(181, ScriptRequest.class)
            .put(182, ScriptRequestList.class)
            .put(183, ScriptCount.class)
            .put(184, ScriptCurrent.class)
            .build();

    private static final Map<Class, Function<ByteBuffer, Object>> deserializers = new UnmodifiableMapBuilder<Class, Function<ByteBuffer, Object>>()
            .put(ScriptItem.class, ScriptItem::deserialize)
            .put(ScriptRequest.class, ScriptRequest::deserialize)
            .put(ScriptRequestList.class, ScriptRequestList::deserialize)
            .put(ScriptCount.class, ScriptCount::deserialize)
            .put(ScriptCurrent.class, ScriptCurrent::deserialize)
            .build();

    public PaparazziDialect() {
        super("paparazzi", dependencies, messages, deserializers);
    }
}
