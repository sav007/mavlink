package io.dronefleet.mavlink.uavionix;

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

public final class UavionixDialect extends AbstractMavlinkDialect {
    /**
     * A list of all of the dependencies of this dialect.
     */
    private static final List<MavlinkDialect> dependencies = Arrays.asList(
            new CommonDialect());

    /**
     * A list of all message types supported by this dialect.
     */
    private static final Map<Integer, Class> messages = new UnmodifiableMapBuilder<Integer, Class>()
            .put(10001, UavionixAdsbOutCfg.class)
            .put(10002, UavionixAdsbOutDynamic.class)
            .put(10003, UavionixAdsbTransceiverHealthReport.class)
            .build();

    private static final Map<Class, Function<ByteBuffer, Object>> deserializers = new UnmodifiableMapBuilder<Class, Function<ByteBuffer, Object>>()
            .put(UavionixAdsbOutCfg.class, UavionixAdsbOutCfg::deserialize)
            .put(UavionixAdsbOutDynamic.class, UavionixAdsbOutDynamic::deserialize)
            .put(UavionixAdsbTransceiverHealthReport.class, UavionixAdsbTransceiverHealthReport::deserialize)
            .build();

    public UavionixDialect() {
        super("uavionix", dependencies, messages, deserializers);
    }
}
