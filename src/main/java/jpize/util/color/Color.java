package jpize.util.color;

public class Color {

    public float r, g, b, a;

    public Color() {
        this.reset();
    }

    public Color(float red, float green, float blue, float alpha) {
        this.set(red, green, blue, alpha);
    }

    public Color(float red, float green, float blue) {
        this.set(red, green, blue);
    }

    public Color(float grayscale, float alpha) {
        this.set(grayscale, alpha);
    }

    public Color(float grayscale) {
        this.set(grayscale);
    }

    public Color(double red, double green, double blue, double alpha) {
        this.set(red, green, blue, alpha);
    }

    public Color(double red, double green, double blue) {
        this.set(red, green, blue);
    }

    public Color(double grayscale, double alpha) {
        this.set(grayscale, alpha);
    }

    public Color(double grayscale) {
        this.set(grayscale);
    }

    public Color(Color color) {
        this.set(color);
    }

    public Color(ImmutableColor immutableColor) {
        this.set(immutableColor);
    }

    public Color(float[] colorArray) {
        this.set(colorArray);
    }

    public Color(double[] colorArray) {
        this.set(colorArray);
    }


    public Color reset() {
        return set(1F, 1F, 1F, 1F);
    }


    public Color set(float red, float green, float blue, float alpha) {
        this.r = red;
        this.g = green;
        this.b = blue;
        this.a = alpha;
        return this;
    }

    public Color set(float red, float green, float blue) {
        return set(red, green, blue, 1F);
    }

    public Color set(float grayscale, float alpha) {
        return set(grayscale, grayscale, grayscale, alpha);
    }

    public Color set(float grayscale) {
        return set(grayscale, 1F);
    }

    public Color set(double red, double green, double blue, double alpha) {
        return set((float) red, (float) green, (float) blue, (float) alpha);
    }

    public Color set(double red, double green, double blue) {
        return set((float) red, (float) green, (float) blue, 1F);
    }

    public Color set(double grayscale, double alpha) {
        return set(grayscale, grayscale, grayscale, alpha);
    }

    public Color set(double grayscale) {
        return set((float) grayscale, 1F);
    }

    public Color set(Color color) {
        return set(color.r, color.g, color.b, color.a);
    }

    public Color set(ImmutableColor immutableColor) {
        return set(immutableColor.r, immutableColor.g, immutableColor.b, immutableColor.a);
    }

    public Color set(float[] colorArray) {
        return set(colorArray[0], colorArray[1], colorArray[2], colorArray[3]);
    }

    public Color set(double[] colorArray) {
        return set(colorArray[0], colorArray[1], colorArray[2], colorArray[3]);
    }

    public Color setInt(int red, int green, int blue, int alpha) {
        return set(
            red   / 255F,
            green / 255F,
            blue  / 255F,
            alpha / 255F
        );
    }

    public Color setInt(int red, int green, int blue) {
        return set(
            red   / 255F,
            green / 255F,
            blue  / 255F
        );
    }

    public Color setInt(int grayscale, int alpha) {
        return set(
            grayscale / 255F,
            alpha     / 255F
        );
    }

    public Color setInt(int grayscale) {
        return set(
            grayscale / 255F
        );
    }


    public Color setR(float red) {
        this.r = red;
        return this;
    }

    public Color setG(float green) {
        this.g = green;
        return this;
    }

    public Color setB(float blue) {
        this.b = blue;
        return this;
    }

    public Color setA(float alpha) {
        this.a = alpha;
        return this;
    }


    public Color setR(double red) {
        return setR((float) red);
    }

    public Color setG(double green) {
        return setG((float) green);
    }

    public Color setB(double blue) {
        return setB((float) blue);
    }

    public Color setA(double alpha) {
        return setA((float) alpha);
    }


    public Color addRgb(double red, double green, double blue) {
        return set(
            this.r + red,
            this.g + green,
            this.b + blue
        );
    }

    public Color addRgb(Color color) {
        return addRgb(color.r, color.g, color.b);
    }


    public Color mul(double red, double green, double blue, double alpha) {
        return set(
            this.r * red,
            this.g * green,
            this.b * blue,
            this.a * alpha
        );
    }

    public Color mulRgb(double value) {
        return set(
            this.r * value,
            this.g * value,
            this.b * value
        );
    }

    public Color divRgb(double value) {
        return set(
            this.r / value,
            this.g / value,
            this.b / value
        );
    }


    public Color blend(Color color) {
        float alphaSum = this.a + color.a;
        float w1 = this.a / alphaSum;
        float w2 = color.a / alphaSum;

        return set(
            this.r * w1 + color.r * w2,
            this.g * w1 + color.g * w2,
            this.b * w1 + color.b * w2,
            Math.max(this.a, color.a)
        );
    }


    public Color inverseRgb() {
        return set(
            1F - this.r,
            1F - this.g,
            1F - this.b
        );
    }

    public Color inverseRgba() {
        inverseRgb();
        return setA(1F - this.a);
    }


    public float[] toArray(){
        return Colors.rgbaToArray(this);
    }


    public Color copy() {
        return new Color(this);
    }

    @Override
    public String toString(){
        return Colors.toString(this);
    }

}
