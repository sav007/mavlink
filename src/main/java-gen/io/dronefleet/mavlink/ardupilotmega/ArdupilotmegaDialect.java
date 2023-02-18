package io.dronefleet.mavlink.ardupilotmega;

import io.dronefleet.mavlink.AbstractMavlinkDialect;
import io.dronefleet.mavlink.MavlinkDialect;
import io.dronefleet.mavlink.common.CommonDialect;
import io.dronefleet.mavlink.cubepilot.CubepilotDialect;
import io.dronefleet.mavlink.icarous.IcarousDialect;
import io.dronefleet.mavlink.uavionix.UavionixDialect;
import io.dronefleet.mavlink.util.UnmodifiableMapBuilder;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class ArdupilotmegaDialect extends AbstractMavlinkDialect {
    /**
     * A list of all of the dependencies of this dialect.
     */
    private static final List<MavlinkDialect> dependencies = Arrays.asList(
            new CommonDialect(),
            new UavionixDialect(),
            new IcarousDialect(),
            new CubepilotDialect());

    /**
     * A list of all message types supported by this dialect.
     */
    private static final Map<Integer, Class> messages = new UnmodifiableMapBuilder<Integer, Class>()
            .put(150, SensorOffsets.class)
            .put(151, SetMagOffsets.class)
            .put(152, Meminfo.class)
            .put(153, ApAdc.class)
            .put(154, DigicamConfigure.class)
            .put(155, DigicamControl.class)
            .put(156, MountConfigure.class)
            .put(157, MountControl.class)
            .put(158, MountStatus.class)
            .put(160, FencePoint.class)
            .put(161, FenceFetchPoint.class)
            .put(163, Ahrs.class)
            .put(164, Simstate.class)
            .put(165, Hwstatus.class)
            .put(166, Radio.class)
            .put(167, LimitsStatus.class)
            .put(168, Wind.class)
            .put(169, Data16.class)
            .put(170, Data32.class)
            .put(171, Data64.class)
            .put(172, Data96.class)
            .put(173, Rangefinder.class)
            .put(174, AirspeedAutocal.class)
            .put(175, RallyPoint.class)
            .put(176, RallyFetchPoint.class)
            .put(177, CompassmotStatus.class)
            .put(178, Ahrs2.class)
            .put(179, CameraStatus.class)
            .put(180, CameraFeedback.class)
            .put(181, Battery2.class)
            .put(182, Ahrs3.class)
            .put(183, AutopilotVersionRequest.class)
            .put(184, RemoteLogDataBlock.class)
            .put(185, RemoteLogBlockStatus.class)
            .put(186, LedControl.class)
            .put(191, MagCalProgress.class)
            .put(193, EkfStatusReport.class)
            .put(194, PidTuning.class)
            .put(195, Deepstall.class)
            .put(200, GimbalReport.class)
            .put(201, GimbalControl.class)
            .put(214, GimbalTorqueCmdReport.class)
            .put(215, GoproHeartbeat.class)
            .put(216, GoproGetRequest.class)
            .put(217, GoproGetResponse.class)
            .put(218, GoproSetRequest.class)
            .put(219, GoproSetResponse.class)
            .put(226, Rpm.class)
            .put(11000, DeviceOpRead.class)
            .put(11001, DeviceOpReadReply.class)
            .put(11002, DeviceOpWrite.class)
            .put(11003, DeviceOpWriteReply.class)
            .put(11010, AdapTuning.class)
            .put(11011, VisionPositionDelta.class)
            .put(11020, AoaSsa.class)
            .put(11030, EscTelemetry1To4.class)
            .put(11031, EscTelemetry5To8.class)
            .put(11032, EscTelemetry9To12.class)
            .put(11033, OsdParamConfig.class)
            .put(11034, OsdParamConfigReply.class)
            .put(11035, OsdParamShowConfig.class)
            .put(11036, OsdParamShowConfigReply.class)
            .put(11037, ObstacleDistance3d.class)
            .put(11038, WaterDepth.class)
            .put(11039, McuStatus.class)
            .build();

    private static final Map<Class, Function<ByteBuffer, Object>> deserializers = new UnmodifiableMapBuilder<Class, Function<ByteBuffer, Object>>()
            .put(SensorOffsets.class, SensorOffsets::deserialize)
            .put(SetMagOffsets.class, SetMagOffsets::deserialize)
            .put(Meminfo.class, Meminfo::deserialize)
            .put(ApAdc.class, ApAdc::deserialize)
            .put(DigicamConfigure.class, DigicamConfigure::deserialize)
            .put(DigicamControl.class, DigicamControl::deserialize)
            .put(MountConfigure.class, MountConfigure::deserialize)
            .put(MountControl.class, MountControl::deserialize)
            .put(MountStatus.class, MountStatus::deserialize)
            .put(FencePoint.class, FencePoint::deserialize)
            .put(FenceFetchPoint.class, FenceFetchPoint::deserialize)
            .put(Ahrs.class, Ahrs::deserialize)
            .put(Simstate.class, Simstate::deserialize)
            .put(Hwstatus.class, Hwstatus::deserialize)
            .put(Radio.class, Radio::deserialize)
            .put(LimitsStatus.class, LimitsStatus::deserialize)
            .put(Wind.class, Wind::deserialize)
            .put(Data16.class, Data16::deserialize)
            .put(Data32.class, Data32::deserialize)
            .put(Data64.class, Data64::deserialize)
            .put(Data96.class, Data96::deserialize)
            .put(Rangefinder.class, Rangefinder::deserialize)
            .put(AirspeedAutocal.class, AirspeedAutocal::deserialize)
            .put(RallyPoint.class, RallyPoint::deserialize)
            .put(RallyFetchPoint.class, RallyFetchPoint::deserialize)
            .put(CompassmotStatus.class, CompassmotStatus::deserialize)
            .put(Ahrs2.class, Ahrs2::deserialize)
            .put(CameraStatus.class, CameraStatus::deserialize)
            .put(CameraFeedback.class, CameraFeedback::deserialize)
            .put(Battery2.class, Battery2::deserialize)
            .put(Ahrs3.class, Ahrs3::deserialize)
            .put(AutopilotVersionRequest.class, AutopilotVersionRequest::deserialize)
            .put(RemoteLogDataBlock.class, RemoteLogDataBlock::deserialize)
            .put(RemoteLogBlockStatus.class, RemoteLogBlockStatus::deserialize)
            .put(LedControl.class, LedControl::deserialize)
            .put(MagCalProgress.class, MagCalProgress::deserialize)
            .put(EkfStatusReport.class, EkfStatusReport::deserialize)
            .put(PidTuning.class, PidTuning::deserialize)
            .put(Deepstall.class, Deepstall::deserialize)
            .put(GimbalReport.class, GimbalReport::deserialize)
            .put(GimbalControl.class, GimbalControl::deserialize)
            .put(GimbalTorqueCmdReport.class, GimbalTorqueCmdReport::deserialize)
            .put(GoproHeartbeat.class, GoproHeartbeat::deserialize)
            .put(GoproGetRequest.class, GoproGetRequest::deserialize)
            .put(GoproGetResponse.class, GoproGetResponse::deserialize)
            .put(GoproSetRequest.class, GoproSetRequest::deserialize)
            .put(GoproSetResponse.class, GoproSetResponse::deserialize)
            .put(Rpm.class, Rpm::deserialize)
            .put(DeviceOpRead.class, DeviceOpRead::deserialize)
            .put(DeviceOpReadReply.class, DeviceOpReadReply::deserialize)
            .put(DeviceOpWrite.class, DeviceOpWrite::deserialize)
            .put(DeviceOpWriteReply.class, DeviceOpWriteReply::deserialize)
            .put(AdapTuning.class, AdapTuning::deserialize)
            .put(VisionPositionDelta.class, VisionPositionDelta::deserialize)
            .put(AoaSsa.class, AoaSsa::deserialize)
            .put(EscTelemetry1To4.class, EscTelemetry1To4::deserialize)
            .put(EscTelemetry5To8.class, EscTelemetry5To8::deserialize)
            .put(EscTelemetry9To12.class, EscTelemetry9To12::deserialize)
            .put(OsdParamConfig.class, OsdParamConfig::deserialize)
            .put(OsdParamConfigReply.class, OsdParamConfigReply::deserialize)
            .put(OsdParamShowConfig.class, OsdParamShowConfig::deserialize)
            .put(OsdParamShowConfigReply.class, OsdParamShowConfigReply::deserialize)
            .put(ObstacleDistance3d.class, ObstacleDistance3d::deserialize)
            .put(WaterDepth.class, WaterDepth::deserialize)
            .put(McuStatus.class, McuStatus::deserialize)
            .build();

    public ArdupilotmegaDialect() {
        super("ardupilotmega", dependencies, messages, deserializers);
    }
}
