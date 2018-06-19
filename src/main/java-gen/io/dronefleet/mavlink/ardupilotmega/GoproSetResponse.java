package io.dronefleet.mavlink.ardupilotmega;

import io.dronefleet.mavlink.annotations.MavlinkMessage;
import io.dronefleet.mavlink.annotations.MavlinkMessageBuilder;
import io.dronefleet.mavlink.annotations.MavlinkMessageField;
import java.lang.Override;
import java.lang.String;

/**
 * Response from a {@link io.dronefleet.mavlink.ardupilotmega.GoproCommand GoproCommand} set request 
 */
@MavlinkMessage(
    id = 219,
    crc = 162
)
public final class GoproSetResponse {
  /**
   * Command ID 
   */
  private final GoproCommand cmdId;

  /**
   * Status 
   */
  private final GoproRequestStatus status;

  private GoproSetResponse(GoproCommand cmdId, GoproRequestStatus status) {
    this.cmdId = cmdId;
    this.status = status;
  }

  @MavlinkMessageBuilder
  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "GoproSetResponse{cmdId=" + cmdId
         + ", status=" + status + "}";
  }

  /**
   * Command ID 
   */
  @MavlinkMessageField(
      position = 1,
      unitSize = 1
  )
  public final GoproCommand cmdId() {
    return cmdId;
  }

  /**
   * Status 
   */
  @MavlinkMessageField(
      position = 2,
      unitSize = 1
  )
  public final GoproRequestStatus status() {
    return status;
  }

  public static class Builder {
    private GoproCommand cmdId;

    private GoproRequestStatus status;

    private Builder() {
    }

    /**
     * Command ID 
     */
    @MavlinkMessageField(
        position = 1,
        unitSize = 1
    )
    public final Builder cmdId(GoproCommand cmdId) {
      this.cmdId = cmdId;
      return this;
    }

    /**
     * Status 
     */
    @MavlinkMessageField(
        position = 2,
        unitSize = 1
    )
    public final Builder status(GoproRequestStatus status) {
      this.status = status;
      return this;
    }

    public final GoproSetResponse build() {
      return new GoproSetResponse(cmdId, status);
    }
  }
}
