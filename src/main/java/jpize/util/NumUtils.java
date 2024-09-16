package jpize.util;

public class NumUtils {

    public final int makeUnsignedShort(byte b1, byte b2) {
        return (b1 << 8) + b2;
    }

    public final short makeShort(byte b1, byte b2) {
        return (short) makeUnsignedShort(b1, b2);
    }

    public final char makeChar(byte b1, byte b2) {
        return (char) makeUnsignedShort(b1, b2);
    }

    public final int makeInt(byte b1, byte b2, byte b3, byte b4) {
        return (b1 << 24) + (b2 << 16) + (b3 << 8) + b4;
    }

    public final float makeFloat(byte b1, byte b2, byte b3, byte b4) {
        return Float.intBitsToFloat(makeInt(b1, b2, b3, b4));
    }

    public final long makeLong(byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return ((long) ( b1) << 56) +
            ((long) ( b2 & 255) << 48) +
            ((long) ( b3 & 255) << 40) +
            ((long) ( b4 & 255) << 32) +
            ((long) ( b5 & 255) << 24) +
             (long) ((b6 & 255) << 16) +
             (long) ((b7 & 255) << 8) +
             (long) ( b8 & 255);
    }

    public final double makeDouble(byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return Double.longBitsToDouble(makeLong(b1, b2, b3, b4, b5, b6, b7, b8));
    }

}
