package io.dronefleet.mavlink.serialization.payload;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import io.dronefleet.mavlink.util.EnumValue;

public class PayloadFieldDecoder {

    public static int decodeUint8(ByteBuffer input) {
        return (int) integerValue(input, 1, false);
    }

    public static int decodeInt8(ByteBuffer input) {
        return (int) integerValue(input, 1, true);
    }

    public static int decodeUint16(ByteBuffer input) {
        return (int) integerValue(input, 2, false);
    }

    public static int decodeInt16(ByteBuffer input) {
        return (int) integerValue(input, 2, true);
    }

    public static int decodeInt32(ByteBuffer input) {
        return (int) integerValue(input, 4, true);
    }

    public static long decodeUint32(ByteBuffer input) {
        return integerValue(input, 4, false);
    }

    public static long decodeInt64(ByteBuffer input) {
        return integerValue(input, 4, true);
    }

    public static BigInteger decodeUint64(ByteBuffer input) {
        int dataLength = Math.min(8, input.remaining());
        if (dataLength == 0) {
            return null;
        }

        byte[] data = new byte[dataLength];
        input.get(data);

        // Invert to big-endian, for BigInteger constructor
        for (int i = 0; i < data.length / 2; ++i) {
            byte tmp = data[i];
            data[i] = data[data.length - 1 - i];
            data[data.length - 1 - i] = tmp;
        }

        return new BigInteger(data);
    }

    public static float decodeFloat(ByteBuffer input) {
        if (input.remaining() == 0) {
            return 0;
        }

        return input.getFloat();
    }

    public static double decodeDouble(ByteBuffer input) {
        if (input.remaining() == 0) {
            return 0;
        }

        return input.getDouble();
    }

    public static String decodeString(ByteBuffer input, int length) {
        int dataLength = Math.min(length, input.remaining());
        if (dataLength == 0) {
            return null;
        }

        byte[] data = new byte[dataLength];
        input.get(data, 0, dataLength);

        int stringLength = 0;
        for (int i = 0; i < dataLength; i++) {
            if (data[i] == 0) {
                stringLength = i;
                break;
            }
        }

        if (stringLength > 0) {
            return new String(data, 0, stringLength, StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }

    public static byte[] decodeUint8Array(ByteBuffer input, int length) {
        int dataLength = Math.min(length, input.remaining());
        if (dataLength == 0) {
            return null;
        }

        byte[] result = new byte[dataLength];
        for (int i = 0; i < dataLength; ++i) {
            result[i] = input.get();
        }
        return result;
    }

    public static List<Integer> decodeInt8Array(ByteBuffer input, int dataLength) {
        return decodeArray(input, dataLength, PayloadFieldDecoder::decodeInt8);
    }

    public static List<Integer> decodeUint16Array(ByteBuffer input, int dataLength) {
        return decodeArray(input, dataLength / 2, PayloadFieldDecoder::decodeUint16);
    }

    public static List<Integer> decodeInt16Array(ByteBuffer input, int dataLength) {
        return decodeArray(input, dataLength / 2, PayloadFieldDecoder::decodeInt16);
    }

    public static List<Long> decodeUint32Array(ByteBuffer input, int dataLength) {
        return decodeArray(input, dataLength / 4, PayloadFieldDecoder::decodeUint32);
    }

    public static List<Integer> decodeInt32Array(ByteBuffer input, int dataLength) {
        return decodeArray(input, dataLength / 4, PayloadFieldDecoder::decodeInt32);
    }

    public static List<BigInteger> decodeUint64Array(ByteBuffer input, int dataLength) {
        return decodeArray(input, dataLength / 8, PayloadFieldDecoder::decodeUint64);
    }

    public static List<Long> decodeInt64Array(ByteBuffer input, int dataLength) {
        return decodeArray(input, dataLength / 8, PayloadFieldDecoder::decodeInt64);
    }

    public static List<Float> decodeFloatArray(ByteBuffer input, int dataLength) {
        return decodeArray(input, dataLength / 4, PayloadFieldDecoder::decodeFloat);
    }

    public static List<Double> decodeDoubleArray(ByteBuffer input, int dataLength) {
        return decodeArray(input, dataLength / 8, PayloadFieldDecoder::decodeDouble);
    }

    private static <T> List<T> decodeArray(ByteBuffer input, int elementCount, Function<ByteBuffer, T> decoder) {
        if (elementCount == 0 || input.remaining() == 0) {
            return null;
        }

        List<T> result = new ArrayList<>(elementCount);
        for (int i = 0; i < elementCount; ++i) {
            result.add(decoder.apply(input));
        }
        return result;
    }

    public static <T extends Enum<T>> EnumValue<T> decodeEnum(Class<T> enumType, ByteBuffer input, int length) {
        if (!input.hasRemaining()) {
            return null;
        }

        return EnumValue.create(enumType, (int) integerValue(input, length, false));
    }

    private static long unsignedIntegerValue(ByteBuffer input, int length) {
        int dataLength = Math.min(length, input.remaining());
        if (dataLength == 0) {
            return 0;
        }

        long value = 0;
        for (int i = 0; i < dataLength; i++) {
            value = value | ((long) ((input.get() & 0xff)) << (i * 8));
        }
        return value;
    }

    private static long signedIntegerValue(ByteBuffer input, int length) {
        long value = unsignedIntegerValue(input, length);
        int signBitIndex = length * Byte.SIZE - 1;
        if ((value >> signBitIndex) == 1) {
            value |= (-1L << signBitIndex);
        }
        return value;
    }

    private static long integerValue(ByteBuffer input, int length, boolean signed) {
        if (signed) {
            return signedIntegerValue(input, length);
        } else {
            return unsignedIntegerValue(input, length);
        }
    }
}
