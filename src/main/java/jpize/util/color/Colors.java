package jpize.util.color;

import jpize.util.math.Mathc;
import jpize.util.math.Maths;

public class Colors {

    public static final ImmutableColor WHITE = new ImmutableColor(1, 1, 1, 1);
    public static final ImmutableColor BLACK = new ImmutableColor(0, 0, 0, 1);

    public static String rgbaToHex(float r, float g, float b, float a) {
        final String red   = Integer.toHexString(Maths.round(Maths.clamp(r, 0, 1) * 255));
        final String green = Integer.toHexString(Maths.round(Maths.clamp(g, 0, 1) * 255));
        final String blue  = Integer.toHexString(Maths.round(Maths.clamp(b, 0, 1) * 255));
        final String alpha = Integer.toHexString(Maths.round(Maths.clamp(a, 0, 1) * 255));

        final StringBuilder sb = new StringBuilder("#");

        if(red.length() < 2)
            sb.append('0');
        sb.append(red);

        if(green.length() < 2)
            sb.append('0');
        sb.append(green);

        if(blue.length() < 2)
            sb.append('0');
        sb.append(blue);

        if(alpha.length() < 2)
            sb.append('0');
        sb.append(alpha);

        return sb.toString();
    }

    public static String rgbaToHex(Color color){
        return rgbaToHex(color.r, color.g, color.b, color.a);
    }

    public static String rgbaToHex(ImmutableColor immutableColor){
        return rgbaToHex(immutableColor.r, immutableColor.g, immutableColor.b, immutableColor.a);
    }


    public static float[] rgbaToArray(float r, float g, float b, float a){
        return new float[]{ r, g, b, a };
    }

    public static float[] rgbaToArray(Color color){
        return rgbaToArray(color.r, color.g, color.b, color.a);
    }

    public static float[] rgbaToArray(ImmutableColor immutableColor){
        return rgbaToArray(immutableColor.r, immutableColor.g, immutableColor.b, immutableColor.a);
    }


    public static String toString(float r, float g, float b, float a){
        return "{" + r + ", " + g + ", " + b + ", " + a + "}";
    }

    public static String toString(Color color){
        return toString(color.r, color.g, color.b, color.a);
    }

    public static String toString(ImmutableColor immutableColor){
        return toString(immutableColor.r, immutableColor.g, immutableColor.b, immutableColor.a);
    }


    public static float intToFloatColor(int value){
        return Float.intBitsToFloat(value & 0xFEFFFFFF);
    }

    public static int floatToIntColor(float value){
        int intBits = Float.floatToRawIntBits(value);
        intBits |= (int) (( intBits >>> 24) * (255F / 254F)) << 24;
        return intBits;
    }


    public static Color randomRGBColor(){
        return new Color(Mathc.random(), Mathc.random(), Mathc.random());
    }

    public static ImmutableColor randomRGBImmutableColor(){
        return new ImmutableColor(Mathc.random(), Mathc.random(), Mathc.random());
    }

    public static float[] randomRGBAArray(){
        return new float[]{ Mathc.random(), Mathc.random(), Mathc.random(), 1F };
    }

}
