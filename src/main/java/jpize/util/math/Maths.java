package jpize.util.math;

public class Maths {

    public static final float E = 2.718281828459045F;
    public static final float PI = 3.141592653589793F;
    public static final float TWO_PI = PI * 2;
    public static final float HALF_PI = PI * 0.5F;

    public static final float TO_DEG = 57.29577951308232F;
    public static final float TO_RAD = 0.017453292519943295F;

    public static final float SQRT2 = Mathc.sqrt(2);
    public static final float SQRT3 = Mathc.sqrt(3);
    public static final float SQRT4 = Mathc.sqrt(4);

    public static final float NANOS_IN_SECf = 1_000_000_000;
    public static final float NANOS_IN_MSf = 1_000_000;
    public static final float MILLIS_IN_SECf = 1_000;
    public static final int NANOS_IN_SECi = 1_000_000_000;
    public static final int NANOS_IN_MSi = 1_000_000;
    public static final int MILLIS_IN_SECi = 1_000;


    public static float sinFromCos(float cos) {
        return Mathc.sqrt(1 - cos * cos);
    }

    public static float cosFromSin(float sin) {
        return Mathc.sqrt(1 - sin * sin);
    }


    public static int floor(double a) {
        return (int) (a < 0 ? a - 0.999999999999999D : a);
    }

    public static int round(double a) {
        return floor(a + 0.5);
    }

    public static int ceil(double a) {
        return (int) (a > 0 ? a + 0.999999999999999D : a);
    }


    public static float frac(float a) {
        return a - floor(a);
    }

    public static double frac(double a) {
        return a - floor(a);
    }

    public static double frac(double value, double min, double max) {
        final double interval = (max - min);
        return frac((value - min) / interval) * interval + min;
    }


    public static boolean isEven(int a) {
        return (a % 2 == 0);
    }

    public static boolean isOdd(int a) {
        return (a % 2 != 0);
    }

    public static boolean isPow2(int a) {
        return (a != 0 && (a & -a) == a);
    }


    public static int nextPow2(int value) {
        if(value == 0)
            return 1;
        value--;
        value |= (value >> 1);
        value |= (value >> 2);
        value |= (value >> 4);
        value |= (value >> 8);
        value |= (value >> 16);
        return (value + 1);
    }


    public static int clamp(int a, int min, int max) {
        return Math.max(min, Math.min(a, max));
    }

    public static float clamp(float a, float min, float max) {
        return Math.max(min, Math.min(a, max));
    }

    public static double clamp(double a, double min, double max) {
        return Math.max(min, Math.min(a, max));
    }


    public static float clamp01(float a) {
        return clamp(a, 0F, 1F);
    }

    public static double clamp01(double a) {
        return clamp(a, 0D, 1D);
    }


    public static float random(float min, float max) {
        return lerp(min, max, Mathc.random());
    }

    public static double random(double min, double max) {
        return lerp(min, max, Mathc.random());
    }

    public static int random(int min, int max) {
        return round(lerp(min, max, Mathc.random()));
    }

    public static float random(float max) {
        return Mathc.random() * max;
    }

    public static int random(int max) {
        return round(Math.random() * max);
    }

    public static boolean randomBoolean(float chance) {
        return Math.random() < chance;
    }

    public static boolean randomBoolean() {
        return randomBoolean(0.5F);
    }

    public static long randomSeed(int length) {
        if(length < 1)
            return 0;

        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++)
            builder.append(random((i == 0 ? 1 : 0), 9));

        return Long.parseLong(builder.toString());
    }

    public static void randomInts(int[] array, int min, int max) {
        final int range = (max - min);
        for(int i = 0; i < array.length; i++)
            array[i] = Maths.round(Math.random() * range + min);
    }

    public static void randomShorts(Short[] array, int min, int max) {
        final int range = (max - min);
        for(int i = 0; i < array.length; i++){
            array[i] = (short) Maths.round(Math.random() * range + min);
        }
    }

    public static void randomBytes(byte[] array, int min, int max) {
        final int range = (max - min);
        for(int i = 0; i < array.length; i++)
            array[i] = (byte) Maths.round(Math.random() * range + min);
    }


    public static float lerp(float start, float end, float t) {
        return start + (end - start) * t;
    }

    public static double lerp(double start, double end, double t) {
        return start + (end - start) * t;
    }

    public static int lerp(int start, int end, int t) {
        return start + (end - start) * t;
    }

    public static float cerp(float a, float b, float c, float d, float t) {
        float p = (d - c) - (a - b);
        float q = (a - b) - p;
        float r = (c - a);

        return t * (t * (t * p + q) + r) + b; // pt^3 + qt^2 + rt^1 + b
    }


    public static float cubic(float t) {
        return -2 * t * t * t + 3 * t * t;
    }

    public static float cosine(float t) {
        return (1 - Mathc.cos(t / PI)) / 2;
    }

    public static float quintic(float t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    public static float hermite(float t) {
        return t * t * (3 - 2 * t);
    }


    public static float sigmoid(float x) {
        return 1 / (1 + Mathc.exp(-x));
    }

    public static float relu(float x) {
        return Math.max(0, x);
    }

    public static float leakyRelu(float x) {
        return Math.max(0.1F * x, x);
    }

    public static float gelu(float x) {
        return x * 0.5F * (1F + Mathc.tanh(
            0.7978845608028654F * (0.044715F * x * x * x + x)
        ));
    }

    public static float selu(float x) {
        return 1.0507009873554804934193349852946F * (
            Math.max(0, x) + Math.min(0,
                1.6732632423543772848170429916717F *
                (Mathc.exp(x) - 1F)
            )
        );
    }


    public static float map(float value, float fromLow, float fromHigh, float toLow, float toHigh) {
        return (value - fromLow) * (toHigh - toLow) / (fromHigh - fromLow) + toLow;
    }

    public static double map(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
        return (value - fromLow) * (toHigh - toLow) / (fromHigh - fromLow) + toLow;
    }


    public static float sinDeg(double a) {
        return Mathc.sin(a * TO_RAD);
    }

    public static float cosDeg(double a) {
        return Mathc.cos(a * TO_RAD);
    }

    public static float tanDeg(double a) {
        return Mathc.tan(a * TO_RAD);
    }


    public static int nonZeroSignum(int a) {
        return (a >= 0 ? 1 : -1);
    }

    public static int nonZeroSignum(float a) {
        return (a >= 0 ? 1 : -1);
    }

    public static int nonZeroSignum(double a) {
        return (a >= 0 ? 1 : -1);
    }


    public static float dot(float[] a, float[] b) {
        float result = 0;
        for(int i = 0; i < a.length; i++)
            result += a[i] * b[i];
        return result;
    }

    public static void mul(float[] in, float[] w, float[] out) {
        for(int o = 0; o < out.length; o++)
            for(int i = 0; i < in.length; i++)
                out[o] += in[i] * w[i];
    }


    public static int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static long max(long a, long b, long c) {
        return Math.max(Math.max(a, b), c);
    }

    public static float max(float a, float b, float c) {
        return Math.max(Math.max(a, b), c);
    }

    public static double max(double a, double b, double c) {
        return Math.max(Math.max(a, b), c);
    }


    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static long min(long a, long b, long c) {
        return Math.min(Math.min(a, b), c);
    }

    public static float min(float a, float b, float c) {
        return Math.min(Math.min(a, b), c);
    }

    public static double min(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }


    public static int sigFlag(boolean negative) {
        return negative ? -1 : 1;
    }


    public static byte toByteRange(double a) {
        return (byte) ((int) (a * 255) & 0xFF);
    }

}
