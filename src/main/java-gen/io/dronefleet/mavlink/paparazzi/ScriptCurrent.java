package io.dronefleet.mavlink.paparazzi;

import io.dronefleet.mavlink.annotations.MavlinkFieldInfo;
import io.dronefleet.mavlink.annotations.MavlinkMessageBuilder;
import io.dronefleet.mavlink.annotations.MavlinkMessageInfo;
import io.dronefleet.mavlink.serialization.payload.PayloadFieldDecoder;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * This message informs about the currently active SCRIPT. 
 */
@MavlinkMessageInfo(
        id = 184,
        crc = 40,
        description = "This message informs about the currently active SCRIPT."
)
public final class ScriptCurrent {
    private final int seq;

    private ScriptCurrent(int seq) {
        this.seq = seq;
    }

    /**
     * Returns a builder instance for this message.
     */
    @MavlinkMessageBuilder
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Active Sequence 
     */
    @MavlinkFieldInfo(
            position = 1,
            unitSize = 2,
            description = "Active Sequence"
    )
    public final int seq() {
        return this.seq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(o.getClass())) return false;
        ScriptCurrent other = (ScriptCurrent)o;
        if (!Objects.deepEquals(seq, other.seq)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + Objects.hashCode(seq);
        return result;
    }

    @Override
    public String toString() {
        return "ScriptCurrent{seq=" + seq + "}";
    }

    public static ScriptCurrent deserialize(ByteBuffer input) {
        int seq = PayloadFieldDecoder.decodeUint16(input);
        return new ScriptCurrent(seq);
    }

    public static final class Builder {
        private int seq;

        /**
         * Active Sequence 
         */
        @MavlinkFieldInfo(
                position = 1,
                unitSize = 2,
                description = "Active Sequence"
        )
        public final Builder seq(int seq) {
            this.seq = seq;
            return this;
        }

        public final ScriptCurrent build() {
            return new ScriptCurrent(seq);
        }
    }
}
