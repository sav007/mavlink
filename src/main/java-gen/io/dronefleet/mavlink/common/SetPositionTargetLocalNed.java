package io.dronefleet.mavlink.common;

import io.dronefleet.mavlink.annotations.MavlinkMessage;
import io.dronefleet.mavlink.annotations.MavlinkMessageBuilder;
import io.dronefleet.mavlink.annotations.MavlinkMessageField;
import java.lang.Override;
import java.lang.String;

/**
 * Sets a desired vehicle position in a local north-east-down coordinate frame. Used by an 
 * external controller to command the vehicle (manual controller or other system). 
 */
@MavlinkMessage(
    id = 84,
    crc = 143
)
public final class SetPositionTargetLocalNed {
  /**
   * Timestamp in milliseconds since system boot 
   */
  private final long timeBootMs;

  /**
   * X Position in NED frame in meters 
   */
  private final float x;

  /**
   * Y Position in NED frame in meters 
   */
  private final float y;

  /**
   * Z Position in NED frame in meters (note, altitude is negative in NED) 
   */
  private final float z;

  /**
   * X velocity in NED frame in meter / s 
   */
  private final float vx;

  /**
   * Y velocity in NED frame in meter / s 
   */
  private final float vy;

  /**
   * Z velocity in NED frame in meter / s 
   */
  private final float vz;

  /**
   * X acceleration or force (if bit 10 of type_mask is set) in NED frame in meter / s^2 or N 
   */
  private final float afx;

  /**
   * Y acceleration or force (if bit 10 of type_mask is set) in NED frame in meter / s^2 or N 
   */
  private final float afy;

  /**
   * Z acceleration or force (if bit 10 of type_mask is set) in NED frame in meter / s^2 or N 
   */
  private final float afz;

  /**
   * yaw setpoint in rad 
   */
  private final float yaw;

  /**
   * yaw rate setpoint in rad/s 
   */
  private final float yawRate;

  /**
   * Bitmask to indicate which dimensions should be ignored by the vehicle: a value of 
   * 0b0000000000000000 or 0b0000001000000000 indicates that none of the setpoint dimensions 
   * should be ignored. If bit 10 is set the floats afx afy afz should be interpreted as force instead 
   * of acceleration. Mapping: bit 1: x, bit 2: y, bit 3: z, bit 4: vx, bit 5: vy, bit 6: vz, bit 7: ax, bit 
   * 8: ay, bit 9: az, bit 10: is force setpoint, bit 11: yaw, bit 12: yaw rate 
   */
  private final int typeMask;

  /**
   * System ID 
   */
  private final int targetSystem;

  /**
   * Component ID 
   */
  private final int targetComponent;

  /**
   * Valid options are: MAV_FRAME_LOCAL_NED = 1, MAV_FRAME_LOCAL_OFFSET_NED = 7, 
   * MAV_FRAME_BODY_NED = 8, MAV_FRAME_BODY_OFFSET_NED = 9 
   */
  private final MavFrame coordinateFrame;

  private SetPositionTargetLocalNed(long timeBootMs, float x, float y, float z, float vx, float vy,
      float vz, float afx, float afy, float afz, float yaw, float yawRate, int typeMask,
      int targetSystem, int targetComponent, MavFrame coordinateFrame) {
    this.timeBootMs = timeBootMs;
    this.x = x;
    this.y = y;
    this.z = z;
    this.vx = vx;
    this.vy = vy;
    this.vz = vz;
    this.afx = afx;
    this.afy = afy;
    this.afz = afz;
    this.yaw = yaw;
    this.yawRate = yawRate;
    this.typeMask = typeMask;
    this.targetSystem = targetSystem;
    this.targetComponent = targetComponent;
    this.coordinateFrame = coordinateFrame;
  }

  @MavlinkMessageBuilder
  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "SetPositionTargetLocalNed{timeBootMs=" + timeBootMs
         + ", targetSystem=" + targetSystem
         + ", targetComponent=" + targetComponent
         + ", coordinateFrame=" + coordinateFrame
         + ", typeMask=" + typeMask
         + ", x=" + x
         + ", y=" + y
         + ", z=" + z
         + ", vx=" + vx
         + ", vy=" + vy
         + ", vz=" + vz
         + ", afx=" + afx
         + ", afy=" + afy
         + ", afz=" + afz
         + ", yaw=" + yaw
         + ", yawRate=" + yawRate + "}";
  }

  /**
   * Timestamp in milliseconds since system boot 
   */
  @MavlinkMessageField(
      position = 1,
      unitSize = 4
  )
  public final long timeBootMs() {
    return timeBootMs;
  }

  /**
   * X Position in NED frame in meters 
   */
  @MavlinkMessageField(
      position = 6,
      unitSize = 4
  )
  public final float x() {
    return x;
  }

  /**
   * Y Position in NED frame in meters 
   */
  @MavlinkMessageField(
      position = 7,
      unitSize = 4
  )
  public final float y() {
    return y;
  }

  /**
   * Z Position in NED frame in meters (note, altitude is negative in NED) 
   */
  @MavlinkMessageField(
      position = 8,
      unitSize = 4
  )
  public final float z() {
    return z;
  }

  /**
   * X velocity in NED frame in meter / s 
   */
  @MavlinkMessageField(
      position = 9,
      unitSize = 4
  )
  public final float vx() {
    return vx;
  }

  /**
   * Y velocity in NED frame in meter / s 
   */
  @MavlinkMessageField(
      position = 10,
      unitSize = 4
  )
  public final float vy() {
    return vy;
  }

  /**
   * Z velocity in NED frame in meter / s 
   */
  @MavlinkMessageField(
      position = 11,
      unitSize = 4
  )
  public final float vz() {
    return vz;
  }

  /**
   * X acceleration or force (if bit 10 of type_mask is set) in NED frame in meter / s^2 or N 
   */
  @MavlinkMessageField(
      position = 12,
      unitSize = 4
  )
  public final float afx() {
    return afx;
  }

  /**
   * Y acceleration or force (if bit 10 of type_mask is set) in NED frame in meter / s^2 or N 
   */
  @MavlinkMessageField(
      position = 13,
      unitSize = 4
  )
  public final float afy() {
    return afy;
  }

  /**
   * Z acceleration or force (if bit 10 of type_mask is set) in NED frame in meter / s^2 or N 
   */
  @MavlinkMessageField(
      position = 14,
      unitSize = 4
  )
  public final float afz() {
    return afz;
  }

  /**
   * yaw setpoint in rad 
   */
  @MavlinkMessageField(
      position = 15,
      unitSize = 4
  )
  public final float yaw() {
    return yaw;
  }

  /**
   * yaw rate setpoint in rad/s 
   */
  @MavlinkMessageField(
      position = 16,
      unitSize = 4
  )
  public final float yawRate() {
    return yawRate;
  }

  /**
   * Bitmask to indicate which dimensions should be ignored by the vehicle: a value of 
   * 0b0000000000000000 or 0b0000001000000000 indicates that none of the setpoint dimensions 
   * should be ignored. If bit 10 is set the floats afx afy afz should be interpreted as force instead 
   * of acceleration. Mapping: bit 1: x, bit 2: y, bit 3: z, bit 4: vx, bit 5: vy, bit 6: vz, bit 7: ax, bit 
   * 8: ay, bit 9: az, bit 10: is force setpoint, bit 11: yaw, bit 12: yaw rate 
   */
  @MavlinkMessageField(
      position = 5,
      unitSize = 2
  )
  public final int typeMask() {
    return typeMask;
  }

  /**
   * System ID 
   */
  @MavlinkMessageField(
      position = 2,
      unitSize = 1
  )
  public final int targetSystem() {
    return targetSystem;
  }

  /**
   * Component ID 
   */
  @MavlinkMessageField(
      position = 3,
      unitSize = 1
  )
  public final int targetComponent() {
    return targetComponent;
  }

  /**
   * Valid options are: MAV_FRAME_LOCAL_NED = 1, MAV_FRAME_LOCAL_OFFSET_NED = 7, 
   * MAV_FRAME_BODY_NED = 8, MAV_FRAME_BODY_OFFSET_NED = 9 
   */
  @MavlinkMessageField(
      position = 4,
      unitSize = 1
  )
  public final MavFrame coordinateFrame() {
    return coordinateFrame;
  }

  public static class Builder {
    private long timeBootMs;

    private float x;

    private float y;

    private float z;

    private float vx;

    private float vy;

    private float vz;

    private float afx;

    private float afy;

    private float afz;

    private float yaw;

    private float yawRate;

    private int typeMask;

    private int targetSystem;

    private int targetComponent;

    private MavFrame coordinateFrame;

    private Builder() {
    }

    /**
     * Timestamp in milliseconds since system boot 
     */
    @MavlinkMessageField(
        position = 1,
        unitSize = 4
    )
    public final Builder timeBootMs(long timeBootMs) {
      this.timeBootMs = timeBootMs;
      return this;
    }

    /**
     * X Position in NED frame in meters 
     */
    @MavlinkMessageField(
        position = 6,
        unitSize = 4
    )
    public final Builder x(float x) {
      this.x = x;
      return this;
    }

    /**
     * Y Position in NED frame in meters 
     */
    @MavlinkMessageField(
        position = 7,
        unitSize = 4
    )
    public final Builder y(float y) {
      this.y = y;
      return this;
    }

    /**
     * Z Position in NED frame in meters (note, altitude is negative in NED) 
     */
    @MavlinkMessageField(
        position = 8,
        unitSize = 4
    )
    public final Builder z(float z) {
      this.z = z;
      return this;
    }

    /**
     * X velocity in NED frame in meter / s 
     */
    @MavlinkMessageField(
        position = 9,
        unitSize = 4
    )
    public final Builder vx(float vx) {
      this.vx = vx;
      return this;
    }

    /**
     * Y velocity in NED frame in meter / s 
     */
    @MavlinkMessageField(
        position = 10,
        unitSize = 4
    )
    public final Builder vy(float vy) {
      this.vy = vy;
      return this;
    }

    /**
     * Z velocity in NED frame in meter / s 
     */
    @MavlinkMessageField(
        position = 11,
        unitSize = 4
    )
    public final Builder vz(float vz) {
      this.vz = vz;
      return this;
    }

    /**
     * X acceleration or force (if bit 10 of type_mask is set) in NED frame in meter / s^2 or N 
     */
    @MavlinkMessageField(
        position = 12,
        unitSize = 4
    )
    public final Builder afx(float afx) {
      this.afx = afx;
      return this;
    }

    /**
     * Y acceleration or force (if bit 10 of type_mask is set) in NED frame in meter / s^2 or N 
     */
    @MavlinkMessageField(
        position = 13,
        unitSize = 4
    )
    public final Builder afy(float afy) {
      this.afy = afy;
      return this;
    }

    /**
     * Z acceleration or force (if bit 10 of type_mask is set) in NED frame in meter / s^2 or N 
     */
    @MavlinkMessageField(
        position = 14,
        unitSize = 4
    )
    public final Builder afz(float afz) {
      this.afz = afz;
      return this;
    }

    /**
     * yaw setpoint in rad 
     */
    @MavlinkMessageField(
        position = 15,
        unitSize = 4
    )
    public final Builder yaw(float yaw) {
      this.yaw = yaw;
      return this;
    }

    /**
     * yaw rate setpoint in rad/s 
     */
    @MavlinkMessageField(
        position = 16,
        unitSize = 4
    )
    public final Builder yawRate(float yawRate) {
      this.yawRate = yawRate;
      return this;
    }

    /**
     * Bitmask to indicate which dimensions should be ignored by the vehicle: a value of 
     * 0b0000000000000000 or 0b0000001000000000 indicates that none of the setpoint dimensions 
     * should be ignored. If bit 10 is set the floats afx afy afz should be interpreted as force instead 
     * of acceleration. Mapping: bit 1: x, bit 2: y, bit 3: z, bit 4: vx, bit 5: vy, bit 6: vz, bit 7: ax, bit 
     * 8: ay, bit 9: az, bit 10: is force setpoint, bit 11: yaw, bit 12: yaw rate 
     */
    @MavlinkMessageField(
        position = 5,
        unitSize = 2
    )
    public final Builder typeMask(int typeMask) {
      this.typeMask = typeMask;
      return this;
    }

    /**
     * System ID 
     */
    @MavlinkMessageField(
        position = 2,
        unitSize = 1
    )
    public final Builder targetSystem(int targetSystem) {
      this.targetSystem = targetSystem;
      return this;
    }

    /**
     * Component ID 
     */
    @MavlinkMessageField(
        position = 3,
        unitSize = 1
    )
    public final Builder targetComponent(int targetComponent) {
      this.targetComponent = targetComponent;
      return this;
    }

    /**
     * Valid options are: MAV_FRAME_LOCAL_NED = 1, MAV_FRAME_LOCAL_OFFSET_NED = 7, 
     * MAV_FRAME_BODY_NED = 8, MAV_FRAME_BODY_OFFSET_NED = 9 
     */
    @MavlinkMessageField(
        position = 4,
        unitSize = 1
    )
    public final Builder coordinateFrame(MavFrame coordinateFrame) {
      this.coordinateFrame = coordinateFrame;
      return this;
    }

    public final SetPositionTargetLocalNed build() {
      return new SetPositionTargetLocalNed(timeBootMs, x, y, z, vx, vy, vz, afx, afy, afz, yaw, yawRate, typeMask, targetSystem, targetComponent, coordinateFrame);
    }
  }
}
