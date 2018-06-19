package io.dronefleet.mavlink.common;

import io.dronefleet.mavlink.annotations.MavlinkMessage;
import io.dronefleet.mavlink.annotations.MavlinkMessageBuilder;
import io.dronefleet.mavlink.annotations.MavlinkMessageField;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.List;

/**
 * null
 */
@MavlinkMessage(
    id = 131,
    crc = 223
)
public final class EncapsulatedData {
  /**
   * sequence number (starting with 0 on every transmission) 
   */
  private final int seqnr;

  /**
   * image data bytes 
   */
  private final List<Integer> data;

  private EncapsulatedData(int seqnr, List<Integer> data) {
    this.seqnr = seqnr;
    this.data = data;
  }

  @MavlinkMessageBuilder
  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "EncapsulatedData{seqnr=" + seqnr
         + ", data=" + data + "}";
  }

  /**
   * sequence number (starting with 0 on every transmission) 
   */
  @MavlinkMessageField(
      position = 0,
      unitSize = 2
  )
  public final int seqnr() {
    return seqnr;
  }

  /**
   * image data bytes 
   */
  @MavlinkMessageField(
      position = 1,
      unitSize = 1,
      arraySize = 253
  )
  public final List<Integer> data() {
    return data;
  }

  public static class Builder {
    private int seqnr;

    private List<Integer> data;

    private Builder() {
    }

    /**
     * sequence number (starting with 0 on every transmission) 
     */
    @MavlinkMessageField(
        position = 0,
        unitSize = 2
    )
    public final Builder seqnr(int seqnr) {
      this.seqnr = seqnr;
      return this;
    }

    /**
     * image data bytes 
     */
    @MavlinkMessageField(
        position = 1,
        unitSize = 1,
        arraySize = 253
    )
    public final Builder data(List<Integer> data) {
      this.data = data;
      return this;
    }

    public final EncapsulatedData build() {
      return new EncapsulatedData(seqnr, data);
    }
  }
}
