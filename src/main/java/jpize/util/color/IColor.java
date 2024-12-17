package jpize.util.color;

import jpize.util.math.Maths;

public abstract class IColor {

    public static final ImmutableColor WHITE = new ImmutableColor(1F, 1F, 1F, 1F);
    public static final ImmutableColor BLACK = new ImmutableColor(0F, 0F, 0F, 1F);


    public abstract float getRed();

    public abstract float getGreen();

    public abstract float getBlue();

    public abstract float getAlpha();

    public abstract IColor copy();


    public String getHexString() {
        final String redStr   = Integer.toHexString(Maths.round(Maths.clamp(this.getRed()  , 0F, 1F) * 255F));
        final String greenStr = Integer.toHexString(Maths.round(Maths.clamp(this.getGreen(), 0F, 1F) * 255F));
        final String blueStr  = Integer.toHexString(Maths.round(Maths.clamp(this.getBlue() , 0F, 1F) * 255F));
        final String alphaStr = Integer.toHexString(Maths.round(Maths.clamp(this.getAlpha(), 0F, 1F) * 255F));

        final StringBuilder builder = new StringBuilder("#");

        if(redStr.length() < 2) builder.append('0');
        builder.append(redStr);

        if(greenStr.length() < 2) builder.append('0');
        builder.append(greenStr);

        if(blueStr.length() < 2) builder.append('0');
        builder.append(blueStr);

        if(alphaStr.length() < 2) builder.append('0');
        builder.append(alphaStr);

        return builder.toString();
    }

    public int getHexInteger() {
        return Integer.parseInt(this.getHexString());
    }


    public float[] toArray(){
        return new float[]{
            this.getRed(),
            this.getGreen(),
            this.getBlue(),
            this.getAlpha()
        };
    }

    @Override
    public String toString(){
        return "{" + this.getRed() + ", " + this.getGreen() + ", " + this.getBlue() + ", " + this.getAlpha() + "}";
    }


    public static Color random(double minBright, double maxBright) {
        return new Color(
            Maths.random(minBright, maxBright),
            Maths.random(minBright, maxBright),
            Maths.random(minBright, maxBright)
        );
    }

    public static Color random(double minBright) {
        return random(minBright, 1F);
    }

    public static Color random() {
        return random(0F, 1F);
    }


    public static ImmutableColor randomImmutable(double minBright, double maxBright){
        return new ImmutableColor(
            Maths.random(minBright, maxBright),
            Maths.random(minBright, maxBright),
            Maths.random(minBright, maxBright)
        );
    }

    public static ImmutableColor randomImmutable(double minBright) {
        return randomImmutable(minBright, 1F);
    }

    public static ImmutableColor randomImmutable() {
        return randomImmutable(0F, 1F);
    }


    public static float intToFloatColor(int value){
        return Float.intBitsToFloat(value & 0xFEFFFFFF);
    }

    public static int floatToIntColor(float value){
        int intBits = Float.floatToRawIntBits(value);
        intBits |= (int) (( intBits >>> 24) * (255F / 254F)) << 24;
        return intBits;
    }


    public static Color blend(Color dst, float r1, float g1, float b1, float a1, float r2, float g2, float b2, float a2){
        final float inv_a2 = (1F - a2);
        dst.set(
            (r1 * inv_a2) + (r2 * a2),
            (g1 * inv_a2) + (g2 * a2),
            (b1 * inv_a2) + (b2 * a2),
            Math.max(a1, a2)
        );
        return dst;
    }

    public static Color blend(Color dst, IColor color1, float r2, float g2, float b2, float a2){
        return blend(
            dst,
            color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha(),
            r2, g2, b2, a2
        );
    }

    public static Color blend(Color dst, float r1, float g1, float b1, float a1, IColor color2){
        return blend(
            dst,
            r1, g1, b1, a1,
            color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()
        );
    }

    public static Color blend(Color dst, IColor color1, IColor color2){
        return blend(
            dst,
            color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha(),
            color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()
        );
    }

}
