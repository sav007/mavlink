package io.dronefleet.mavlink.avssuas;

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

public final class AvssuasDialect extends AbstractMavlinkDialect {
    /**
     * A list of all of the dependencies of this dialect.
     */
    private static final List<MavlinkDialect> dependencies = Arrays.asList(
            new CommonDialect());

    /**
     * A list of all message types supported by this dialect.
     */
    private static final Map<Integer, Class> messages = new UnmodifiableMapBuilder<Integer, Class>()
            .put(60050, AvssPrsSysStatus.class)
            .put(60051, AvssDronePosition.class)
            .put(60052, AvssDroneImu.class)
            .put(60053, AvssDroneOperationMode.class)
            .build();

    private static final Map<Class, Function<ByteBuffer, Object>> deserializers = new UnmodifiableMapBuilder<Class, Function<ByteBuffer, Object>>()
            .put(AvssPrsSysStatus.class, AvssPrsSysStatus::deserialize)
            .put(AvssDronePosition.class, AvssDronePosition::deserialize)
            .put(AvssDroneImu.class, AvssDroneImu::deserialize)
            .put(AvssDroneOperationMode.class, AvssDroneOperationMode::deserialize)
            .build();

    public AvssuasDialect() {
        super("avssuas", dependencies, messages, deserializers);
    }
}
