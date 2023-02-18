package io.dronefleet.mavlink.slugs;

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

public final class SlugsDialect extends AbstractMavlinkDialect {
    /**
     * A list of all of the dependencies of this dialect.
     */
    private static final List<MavlinkDialect> dependencies = Arrays.asList(
            new CommonDialect());

    /**
     * A list of all message types supported by this dialect.
     */
    private static final Map<Integer, Class> messages = new UnmodifiableMapBuilder<Integer, Class>()
            .put(170, CpuLoad.class)
            .put(172, SensorBias.class)
            .put(173, Diagnostic.class)
            .put(176, SlugsNavigation.class)
            .put(177, DataLog.class)
            .put(179, GpsDateTime.class)
            .put(180, MidLvlCmds.class)
            .put(181, CtrlSrfcPt.class)
            .put(184, SlugsCameraOrder.class)
            .put(185, ControlSurface.class)
            .put(186, SlugsMobileLocation.class)
            .put(188, SlugsConfigurationCamera.class)
            .put(189, IsrLocation.class)
            .put(191, VoltSensor.class)
            .put(192, PtzStatus.class)
            .put(193, UavStatus.class)
            .put(194, StatusGps.class)
            .put(195, NovatelDiag.class)
            .put(196, SensorDiag.class)
            .put(197, Boot.class)
            .build();

    private static final Map<Class, Function<ByteBuffer, Object>> deserializers = new UnmodifiableMapBuilder<Class, Function<ByteBuffer, Object>>()
            .put(CpuLoad.class, CpuLoad::deserialize)
            .put(SensorBias.class, SensorBias::deserialize)
            .put(Diagnostic.class, Diagnostic::deserialize)
            .put(SlugsNavigation.class, SlugsNavigation::deserialize)
            .put(DataLog.class, DataLog::deserialize)
            .put(GpsDateTime.class, GpsDateTime::deserialize)
            .put(MidLvlCmds.class, MidLvlCmds::deserialize)
            .put(CtrlSrfcPt.class, CtrlSrfcPt::deserialize)
            .put(SlugsCameraOrder.class, SlugsCameraOrder::deserialize)
            .put(ControlSurface.class, ControlSurface::deserialize)
            .put(SlugsMobileLocation.class, SlugsMobileLocation::deserialize)
            .put(SlugsConfigurationCamera.class, SlugsConfigurationCamera::deserialize)
            .put(IsrLocation.class, IsrLocation::deserialize)
            .put(VoltSensor.class, VoltSensor::deserialize)
            .put(PtzStatus.class, PtzStatus::deserialize)
            .put(UavStatus.class, UavStatus::deserialize)
            .put(StatusGps.class, StatusGps::deserialize)
            .put(NovatelDiag.class, NovatelDiag::deserialize)
            .put(SensorDiag.class, SensorDiag::deserialize)
            .put(Boot.class, Boot::deserialize)
            .build();

    public SlugsDialect() {
        super("slugs", dependencies, messages, deserializers);
    }
}
