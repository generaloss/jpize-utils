package jpize.util;

public class ByteUtils {

    public static int makeUnsignedShort(byte b2, byte b1) {
        return (b2 << 8) + b1;
    }

    public static int makeUnsignedShort(int b2, int b1) {
        return makeUnsignedShort((byte) b2, (byte) b1);
    }

    public static short makeShort(byte b2, byte b1) {
        return (short) makeUnsignedShort(b2, b1);
    }

    public static int makeShort(int b2, int b1) {
        return makeShort((byte) b2, (byte) b1);
    }

    public static char makeChar(byte b2, byte b1) {
        return (char) makeUnsignedShort(b2, b1);
    }

    public static int makeChar(int b2, int b1) {
        return makeChar((byte) b2, (byte) b1);
    }

    public static int makeInt(byte b4, byte b3, byte b2, byte b1) {
        return (b4 << 24) + (b3 << 16) + (b2 << 8) + b1;
    }

    public static int makeInt(int b4, int b3, int b2, int b1) {
        return makeInt((byte) b4, (byte) b3, (byte) b2, (byte) b1);
    }

    public static float makeFloat(byte b4, byte b3, byte b2, byte b1) {
        return Float.intBitsToFloat(makeInt(b4, b3, b2, b1));
    }

    public static long makeLong(byte b8, byte b7, byte b6, byte b5, byte b4, byte b3, byte b2, byte b1) {
        return ((long) (b8 & 0xFF) << 56) |
               ((long) (b7 & 0xFF) << 48) |
               ((long) (b6 & 0xFF) << 40) |
               ((long) (b5 & 0xFF) << 32) |
               ((long) (b4 & 0xFF) << 24) |
               ((long) (b3 & 0xFF) << 16) |
               ((long) (b2 & 0xFF) << 8)  |
               ((long) (b1 & 0xFF));
    }

    public static long makeLong(int b8, int b7, int b6, int b5, int b4, int b3, int b2, int b1) {
        return makeLong((byte) b8, (byte) b7, (byte) b6, (byte) b5, (byte) b4, (byte) b3, (byte) b2, (byte) b1);
    }

    public static double makeDouble(byte b8, byte b7, byte b6, byte b5, byte b4, byte b3, byte b2, byte b1) {
        return Double.longBitsToDouble(makeLong(b8, b7, b6, b5, b4, b3, b2, b1));
    }

    public static double makeDouble(int b8, int b7, int b6, int b5, int b4, int b3, int b2, int b1) {
        return makeDouble((byte) b8, (byte) b7, (byte) b6, (byte) b5, (byte) b4, (byte) b3, (byte) b2, (byte) b1);
    }


    public static int ubyte1(int number) {
        return (number & 0xFF);
    }

    public static int ubyte2(int number) {
        return ((number >> 8) & 0xFF);
    }

    public static int ubyte3(int number) {
        return ((number >> 16) & 0xFF);
    }

    public static int ubyte4(int number) {
        return ((number >> 24) & 0xFF);
    }


    public static int ubyte1(long number) {
        return (int) (number & 0xFF);
    }

    public static int ubyte2(long number) {
        return (int) ((number >> 8) & 0xFF);
    }

    public static int ubyte3(long number) {
        return (int) ((number >> 16) & 0xFF);
    }

    public static int ubyte4(long number) {
        return (int) ((number >> 24) & 0xFF);
    }

    public static int ubyte5(long number) {
        return (int) ((number >> 32) & 0xFF);
    }

    public static int ubyte6(long number) {
        return (int) ((number >> 40) & 0xFF);
    }

    public static int ubyte7(long number) {
        return (int) ((number >> 48) & 0xFF);
    }

    public static int ubyte8(long number) {
        return (int) ((number >> 56) & 0xFF);
    }


    public static byte byte1(int number) {
        return (byte) (number & 0xFF);
    }

    public static byte byte2(int number) {
        return (byte) ((number >> 8) & 0xFF);
    }

    public static byte byte3(int number) {
        return (byte) ((number >> 16) & 0xFF);
    }

    public static byte byte4(int number) {
        return (byte) ((number >> 24) & 0xFF);
    }


    public static byte byte1(long number) {
        return (byte) (number & 0xFF);
    }

    public static byte byte2(long number) {
        return (byte) ((number >> 8) & 0xFF);
    }

    public static byte byte3(long number) {
        return (byte) ((number >> 16) & 0xFF);
    }

    public static byte byte4(long number) {
        return (byte) ((number >> 24) & 0xFF);
    }

    public static byte byte5(long number) {
        return (byte) ((number >> 32) & 0xFF);
    }

    public static byte byte6(long number) {
        return (byte) ((number >> 40) & 0xFF);
    }

    public static byte byte7(long number) {
        return (byte) ((number >> 48) & 0xFF);
    }

    public static byte byte8(long number) {
        return (byte) ((number >> 56) & 0xFF);
    }

}
