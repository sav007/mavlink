package io.dronefleet.mavlink.matrixpilot;

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

public final class MatrixpilotDialect extends AbstractMavlinkDialect {
    /**
     * A list of all of the dependencies of this dialect.
     */
    private static final List<MavlinkDialect> dependencies = Arrays.asList(
            new CommonDialect());

    /**
     * A list of all message types supported by this dialect.
     */
    private static final Map<Integer, Class> messages = new UnmodifiableMapBuilder<Integer, Class>()
            .put(150, FlexifunctionSet.class)
            .put(151, FlexifunctionReadReq.class)
            .put(152, FlexifunctionBufferFunction.class)
            .put(153, FlexifunctionBufferFunctionAck.class)
            .put(155, FlexifunctionDirectory.class)
            .put(156, FlexifunctionDirectoryAck.class)
            .put(157, FlexifunctionCommand.class)
            .put(158, FlexifunctionCommandAck.class)
            .put(170, SerialUdbExtraF2A.class)
            .put(171, SerialUdbExtraF2B.class)
            .put(172, SerialUdbExtraF4.class)
            .put(173, SerialUdbExtraF5.class)
            .put(174, SerialUdbExtraF6.class)
            .put(175, SerialUdbExtraF7.class)
            .put(176, SerialUdbExtraF8.class)
            .put(177, SerialUdbExtraF13.class)
            .put(178, SerialUdbExtraF14.class)
            .put(179, SerialUdbExtraF15.class)
            .put(180, SerialUdbExtraF16.class)
            .put(181, Altitudes.class)
            .put(182, Airspeeds.class)
            .put(183, SerialUdbExtraF17.class)
            .put(184, SerialUdbExtraF18.class)
            .put(185, SerialUdbExtraF19.class)
            .put(186, SerialUdbExtraF20.class)
            .put(187, SerialUdbExtraF21.class)
            .put(188, SerialUdbExtraF22.class)
            .build();

    private static final Map<Class, Function<ByteBuffer, Object>> deserializers = new UnmodifiableMapBuilder<Class, Function<ByteBuffer, Object>>()
            .put(FlexifunctionSet.class, FlexifunctionSet::deserialize)
            .put(FlexifunctionReadReq.class, FlexifunctionReadReq::deserialize)
            .put(FlexifunctionBufferFunction.class, FlexifunctionBufferFunction::deserialize)
            .put(FlexifunctionBufferFunctionAck.class, FlexifunctionBufferFunctionAck::deserialize)
            .put(FlexifunctionDirectory.class, FlexifunctionDirectory::deserialize)
            .put(FlexifunctionDirectoryAck.class, FlexifunctionDirectoryAck::deserialize)
            .put(FlexifunctionCommand.class, FlexifunctionCommand::deserialize)
            .put(FlexifunctionCommandAck.class, FlexifunctionCommandAck::deserialize)
            .put(SerialUdbExtraF2A.class, SerialUdbExtraF2A::deserialize)
            .put(SerialUdbExtraF2B.class, SerialUdbExtraF2B::deserialize)
            .put(SerialUdbExtraF4.class, SerialUdbExtraF4::deserialize)
            .put(SerialUdbExtraF5.class, SerialUdbExtraF5::deserialize)
            .put(SerialUdbExtraF6.class, SerialUdbExtraF6::deserialize)
            .put(SerialUdbExtraF7.class, SerialUdbExtraF7::deserialize)
            .put(SerialUdbExtraF8.class, SerialUdbExtraF8::deserialize)
            .put(SerialUdbExtraF13.class, SerialUdbExtraF13::deserialize)
            .put(SerialUdbExtraF14.class, SerialUdbExtraF14::deserialize)
            .put(SerialUdbExtraF15.class, SerialUdbExtraF15::deserialize)
            .put(SerialUdbExtraF16.class, SerialUdbExtraF16::deserialize)
            .put(Altitudes.class, Altitudes::deserialize)
            .put(Airspeeds.class, Airspeeds::deserialize)
            .put(SerialUdbExtraF17.class, SerialUdbExtraF17::deserialize)
            .put(SerialUdbExtraF18.class, SerialUdbExtraF18::deserialize)
            .put(SerialUdbExtraF19.class, SerialUdbExtraF19::deserialize)
            .put(SerialUdbExtraF20.class, SerialUdbExtraF20::deserialize)
            .put(SerialUdbExtraF21.class, SerialUdbExtraF21::deserialize)
            .put(SerialUdbExtraF22.class, SerialUdbExtraF22::deserialize)
            .build();

    public MatrixpilotDialect() {
        super("matrixpilot", dependencies, messages, deserializers);
    }
}
