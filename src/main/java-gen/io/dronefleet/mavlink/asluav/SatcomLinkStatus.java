package io.dronefleet.mavlink.asluav;

import io.dronefleet.mavlink.annotations.MavlinkFieldInfo;
import io.dronefleet.mavlink.annotations.MavlinkMessageBuilder;
import io.dronefleet.mavlink.annotations.MavlinkMessageInfo;
import io.dronefleet.mavlink.serialization.payload.PayloadFieldDecoder;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * Status of the SatCom link 
 */
@MavlinkMessageInfo(
        id = 8015,
        crc = 23,
        description = "Status of the SatCom link"
)
public final class SatcomLinkStatus {
    private final BigInteger timestamp;

    private final BigInteger lastHeartbeat;

    private final int failedSessions;

    private final int successfulSessions;

    private final int signalQuality;

    private final int ringPending;

    private final int txSessionPending;

    private final int rxSessionPending;

    private SatcomLinkStatus(BigInteger timestamp, BigInteger lastHeartbeat, int failedSessions,
            int successfulSessions, int signalQuality, int ringPending, int txSessionPending,
            int rxSessionPending) {
        this.timestamp = timestamp;
        this.lastHeartbeat = lastHeartbeat;
        this.failedSessions = failedSessions;
        this.successfulSessions = successfulSessions;
        this.signalQuality = signalQuality;
        this.ringPending = ringPending;
        this.txSessionPending = txSessionPending;
        this.rxSessionPending = rxSessionPending;
    }

    /**
     * Returns a builder instance for this message.
     */
    @MavlinkMessageBuilder
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Timestamp 
     */
    @MavlinkFieldInfo(
            position = 1,
            unitSize = 8,
            description = "Timestamp"
    )
    public final BigInteger timestamp() {
        return this.timestamp;
    }

    /**
     * Timestamp of the last successful sbd session 
     */
    @MavlinkFieldInfo(
            position = 2,
            unitSize = 8,
            description = "Timestamp of the last successful sbd session"
    )
    public final BigInteger lastHeartbeat() {
        return this.lastHeartbeat;
    }

    /**
     * Number of failed sessions 
     */
    @MavlinkFieldInfo(
            position = 3,
            unitSize = 2,
            description = "Number of failed sessions"
    )
    public final int failedSessions() {
        return this.failedSessions;
    }

    /**
     * Number of successful sessions 
     */
    @MavlinkFieldInfo(
            position = 4,
            unitSize = 2,
            description = "Number of successful sessions"
    )
    public final int successfulSessions() {
        return this.successfulSessions;
    }

    /**
     * Signal quality 
     */
    @MavlinkFieldInfo(
            position = 5,
            unitSize = 1,
            description = "Signal quality"
    )
    public final int signalQuality() {
        return this.signalQuality;
    }

    /**
     * Ring call pending 
     */
    @MavlinkFieldInfo(
            position = 6,
            unitSize = 1,
            description = "Ring call pending"
    )
    public final int ringPending() {
        return this.ringPending;
    }

    /**
     * Transmission session pending 
     */
    @MavlinkFieldInfo(
            position = 7,
            unitSize = 1,
            description = "Transmission session pending"
    )
    public final int txSessionPending() {
        return this.txSessionPending;
    }

    /**
     * Receiving session pending 
     */
    @MavlinkFieldInfo(
            position = 8,
            unitSize = 1,
            description = "Receiving session pending"
    )
    public final int rxSessionPending() {
        return this.rxSessionPending;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(o.getClass())) return false;
        SatcomLinkStatus other = (SatcomLinkStatus)o;
        if (!Objects.deepEquals(timestamp, other.timestamp)) return false;
        if (!Objects.deepEquals(lastHeartbeat, other.lastHeartbeat)) return false;
        if (!Objects.deepEquals(failedSessions, other.failedSessions)) return false;
        if (!Objects.deepEquals(successfulSessions, other.successfulSessions)) return false;
        if (!Objects.deepEquals(signalQuality, other.signalQuality)) return false;
        if (!Objects.deepEquals(ringPending, other.ringPending)) return false;
        if (!Objects.deepEquals(txSessionPending, other.txSessionPending)) return false;
        if (!Objects.deepEquals(rxSessionPending, other.rxSessionPending)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + Objects.hashCode(timestamp);
        result = 31 * result + Objects.hashCode(lastHeartbeat);
        result = 31 * result + Objects.hashCode(failedSessions);
        result = 31 * result + Objects.hashCode(successfulSessions);
        result = 31 * result + Objects.hashCode(signalQuality);
        result = 31 * result + Objects.hashCode(ringPending);
        result = 31 * result + Objects.hashCode(txSessionPending);
        result = 31 * result + Objects.hashCode(rxSessionPending);
        return result;
    }

    @Override
    public String toString() {
        return "SatcomLinkStatus{timestamp=" + timestamp
                 + ", lastHeartbeat=" + lastHeartbeat
                 + ", failedSessions=" + failedSessions
                 + ", successfulSessions=" + successfulSessions
                 + ", signalQuality=" + signalQuality
                 + ", ringPending=" + ringPending
                 + ", txSessionPending=" + txSessionPending
                 + ", rxSessionPending=" + rxSessionPending + "}";
    }

    public static SatcomLinkStatus deserialize(ByteBuffer input) {
        BigInteger timestamp = PayloadFieldDecoder.decodeUint64(input);
        BigInteger lastHeartbeat = PayloadFieldDecoder.decodeUint64(input);
        int failedSessions = PayloadFieldDecoder.decodeUint16(input);
        int successfulSessions = PayloadFieldDecoder.decodeUint16(input);
        int signalQuality = PayloadFieldDecoder.decodeUint8(input);
        int ringPending = PayloadFieldDecoder.decodeUint8(input);
        int txSessionPending = PayloadFieldDecoder.decodeUint8(input);
        int rxSessionPending = PayloadFieldDecoder.decodeUint8(input);
        return new SatcomLinkStatus(timestamp, lastHeartbeat, failedSessions, successfulSessions, signalQuality, ringPending, txSessionPending, rxSessionPending);
    }

    public static final class Builder {
        private BigInteger timestamp;

        private BigInteger lastHeartbeat;

        private int failedSessions;

        private int successfulSessions;

        private int signalQuality;

        private int ringPending;

        private int txSessionPending;

        private int rxSessionPending;

        /**
         * Timestamp 
         */
        @MavlinkFieldInfo(
                position = 1,
                unitSize = 8,
                description = "Timestamp"
        )
        public final Builder timestamp(BigInteger timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        /**
         * Timestamp of the last successful sbd session 
         */
        @MavlinkFieldInfo(
                position = 2,
                unitSize = 8,
                description = "Timestamp of the last successful sbd session"
        )
        public final Builder lastHeartbeat(BigInteger lastHeartbeat) {
            this.lastHeartbeat = lastHeartbeat;
            return this;
        }

        /**
         * Number of failed sessions 
         */
        @MavlinkFieldInfo(
                position = 3,
                unitSize = 2,
                description = "Number of failed sessions"
        )
        public final Builder failedSessions(int failedSessions) {
            this.failedSessions = failedSessions;
            return this;
        }

        /**
         * Number of successful sessions 
         */
        @MavlinkFieldInfo(
                position = 4,
                unitSize = 2,
                description = "Number of successful sessions"
        )
        public final Builder successfulSessions(int successfulSessions) {
            this.successfulSessions = successfulSessions;
            return this;
        }

        /**
         * Signal quality 
         */
        @MavlinkFieldInfo(
                position = 5,
                unitSize = 1,
                description = "Signal quality"
        )
        public final Builder signalQuality(int signalQuality) {
            this.signalQuality = signalQuality;
            return this;
        }

        /**
         * Ring call pending 
         */
        @MavlinkFieldInfo(
                position = 6,
                unitSize = 1,
                description = "Ring call pending"
        )
        public final Builder ringPending(int ringPending) {
            this.ringPending = ringPending;
            return this;
        }

        /**
         * Transmission session pending 
         */
        @MavlinkFieldInfo(
                position = 7,
                unitSize = 1,
                description = "Transmission session pending"
        )
        public final Builder txSessionPending(int txSessionPending) {
            this.txSessionPending = txSessionPending;
            return this;
        }

        /**
         * Receiving session pending 
         */
        @MavlinkFieldInfo(
                position = 8,
                unitSize = 1,
                description = "Receiving session pending"
        )
        public final Builder rxSessionPending(int rxSessionPending) {
            this.rxSessionPending = rxSessionPending;
            return this;
        }

        public final SatcomLinkStatus build() {
            return new SatcomLinkStatus(timestamp, lastHeartbeat, failedSessions, successfulSessions, signalQuality, ringPending, txSessionPending, rxSessionPending);
        }
    }
}
