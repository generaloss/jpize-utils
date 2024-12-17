package jpize.util.color;

import jpize.util.math.Maths;

public class Color extends IColor {

    public float red;
    public float green;
    public float blue;
    public float alpha;

    public Color() {
        this.reset();
    }

    public Color(float red, float green, float blue, float alpha) {
        this.set(red, green, blue, alpha);
    }

    public Color(double red, double green, double blue, double alpha) {
        this.set(red, green, blue, alpha);
    }

    public Color(float red, float green, float blue) {
        this.set(red, green, blue);
    }

    public Color(double red, double green, double blue) {
        this.set(red, green, blue);
    }

    public Color(float grayscale, float alpha) {
        this.set(grayscale, alpha);
    }

    public Color(double grayscale, double alpha) {
        this.set(grayscale, alpha);
    }

    public Color(float grayscale) {
        this.set(grayscale);
    }

    public Color(double grayscale) {
        this.set(grayscale);
    }

    public Color(IColor color) {
        this.set(color);
    }

    public Color(float[] array) {
        this.set(array);
    }

    public Color(double[] array) {
        this.set(array);
    }

    public Color(int[] array) {
        this.seti(array);
    }


    @Override
    public float getRed() {
        return red;
    }

    public Color setRed(float red) {
        this.red = red;
        return this;
    }

    public Color setRed(double red) {
        return this.setRed((float) red);
    }

    public Color setRedi(int red) {
        return this.setRed(red / 255F);
    }


    @Override
    public float getGreen() {
        return green;
    }

    public Color setGreen(float green) {
        this.green = green;
        return this;
    }

    public Color setGreen(double green) {
        return this.setGreen((float) green);
    }

    public Color setGreeni(int green) {
        return this.setGreen(green / 255F);
    }


    @Override
    public float getBlue() {
        return blue;
    }

    public Color setBlue(float blue) {
        this.blue = blue;
        return this;
    }

    public Color setBlue(double blue) {
        return this.setBlue((float) blue);
    }

    public Color setBluei(int blue) {
        return this.setBlue(blue / 255F);
    }


    @Override
    public float getAlpha() {
        return alpha;
    }

    public Color setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public Color setAlpha(double alpha) {
        return this.setAlpha((float) alpha);
    }

    public Color setAlphai(int alpha) {
        return this.setAlpha(alpha / 255F);
    }


    public Color set(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        return this;
    }

    public Color set(double red, double green, double blue, double alpha) {
        return this.set((float) red, (float) green, (float) blue, (float) alpha);
    }

    public Color seti(int red, int green, int blue, int alpha) {
        return this.set(
            (red   / 255F),
            (green / 255F),
            (blue  / 255F),
            (alpha / 255F)
        );
    }


    public Color set(float red, float green, float blue) {
        return this.set(red, green, blue, 1F);
    }

    public Color set(double red, double green, double blue) {
        return this.set((float) red, (float) green, (float) blue, 1F);
    }

    public Color seti(int red, int green, int blue) {
        return this.set(
            (red   / 255F),
            (green / 255F),
            (blue  / 255F)
        );
    }


    public Color set(float grayscale, float alpha) {
        return this.set(grayscale, grayscale, grayscale, alpha);
    }

    public Color set(double grayscale, double alpha) {
        return this.set(grayscale, grayscale, grayscale, alpha);
    }

    public Color seti(int grayscale, int alpha) {
        return this.set(
            (grayscale / 255F),
            (alpha     / 255F)
        );
    }


    public Color set(float grayscale) {
        return this.set(grayscale, 1F);
    }

    public Color set(double grayscale) {
        return this.set((float) grayscale, 1F);
    }

    public Color seti(int grayscale) {
        return this.set(grayscale / 255F);
    }


    public Color reset() {
        return this.set(1F, 1F, 1F, 1F);
    }


    public Color set(IColor color) {
        return this.set(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public Color set(float[] array) {
        return this.set(array[0], array[1], array[2], array[3]);
    }

    public Color set(double[] array) {
        return this.set(array[0], array[1], array[2], array[3]);
    }

    public Color seti(int[] array) {
        return this.seti(array[0], array[1], array[2], array[3]);
    }


    public Color set(String hexString) {
        // remove '#'
        if(hexString.startsWith("#")) {
            hexString = hexString.substring(1);
        }
        final int length = hexString.length();
        final int mul = Maths.ceil(length * 0.25F);
        final int repeat = (3 - mul);
        final int components = (length / mul);
        try{
            // red
            final String redStr = hexString.substring(0, mul).repeat(repeat);
            this.setRedi(Integer.parseInt(redStr, 16));
            // green
            final String greenStr = hexString.substring(mul, 2 * mul).repeat(repeat);
            this.setGreeni(Integer.parseInt(greenStr, 16));
            // blue
            final String blueStr = hexString.substring(2 * mul, 3 * mul).repeat(repeat);
            this.setBluei(Integer.parseInt(blueStr, 16));
            // alpha
            if(components == 4){
                final String alphaStr = hexString.substring(3 * mul, 4 * mul).repeat(repeat);
                this.setAlphai(Integer.parseInt(alphaStr, 16));
            }
        }catch(Exception e){
            throw new IllegalArgumentException("Invalid hexadecimal color: '" + hexString + "'");
        }
        return this;
    }

    public Color set(int hexInteger) {
        // rgba
        if(hexInteger > 0xFFFFFF) {
            return this.seti(
                (hexInteger >> 24 & 0xFF),
                (hexInteger >> 16 & 0xFF),
                (hexInteger >> 8  & 0xFF),
                (hexInteger       & 0xFF)
            );
        }
        // rgb
        return this.seti(
            (hexInteger >> 16 & 0xFF),
            (hexInteger >> 8  & 0xFF),
            (hexInteger       & 0xFF)
        );
    }


    public Color addRGB(double red, double green, double blue) {
        return this.set(
            (this.red   + red  ),
            (this.green + green),
            (this.blue  + blue )
        );
    }

    public Color addRGB(Color color) {
        return this.addRGB(color.red, color.green, color.blue);
    }


    public Color mul(double red, double green, double blue, double alpha) {
        return this.set(
            (this.red   * red  ),
            (this.green * green),
            (this.blue  * blue ),
            (this.alpha * alpha)
        );
    }

    public Color mulRGB(double value) {
        return this.set(
            (red   * value),
            (green * value),
            (blue  * value)
        );
    }

    public Color divRGB(double value) {
        return this.set(
            (red   / value),
            (green / value),
            (blue  / value)
        );
    }


    public Color blend(Color color) {
        final float invAlphaSum = (1F / (alpha + color.alpha));
        final float a1 = (invAlphaSum * alpha);
        final float a2 = (invAlphaSum * color.alpha);

        return this.set(
            (red   * a1) + (color.red   * a2),
            (green * a1) + (color.green * a2),
            (blue  * a1) + (color.blue  * a2),
            Math.max(alpha, color.alpha)
        );
    }


    public Color inverseRGB() {
        return this.set(
            (1F - red  ),
            (1F - green),
            (1F - blue )
        );
    }

    public Color inverseRGBA() {
        this.inverseRGB();
        return this.setAlpha(1F - this.alpha);
    }


    @Override
    public Color copy() {
        return new Color(this);
    }

}
