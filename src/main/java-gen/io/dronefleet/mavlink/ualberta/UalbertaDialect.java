package io.dronefleet.mavlink.ualberta;

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

public final class UalbertaDialect extends AbstractMavlinkDialect {
    /**
     * A list of all of the dependencies of this dialect.
     */
    private static final List<MavlinkDialect> dependencies = Arrays.asList(
            new CommonDialect());

    /**
     * A list of all message types supported by this dialect.
     */
    private static final Map<Integer, Class> messages = new UnmodifiableMapBuilder<Integer, Class>()
            .put(220, NavFilterBias.class)
            .put(221, RadioCalibration.class)
            .put(222, UalbertaSysStatus.class)
            .build();

    private static final Map<Class, Function<ByteBuffer, Object>> deserializers = new UnmodifiableMapBuilder<Class, Function<ByteBuffer, Object>>()
            .put(NavFilterBias.class, NavFilterBias::deserialize)
            .put(RadioCalibration.class, RadioCalibration::deserialize)
            .put(UalbertaSysStatus.class, UalbertaSysStatus::deserialize)
            .build();

    public UalbertaDialect() {
        super("ualberta", dependencies, messages, deserializers);
    }
}
