package jpize.util.color;

public class ImmutableColor extends AbstractColor {

    public final float red;
    public final float green;
    public final float blue;
    public final float alpha;

    public ImmutableColor(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public ImmutableColor() {
        this(1F, 1F, 1F, 1F);
    }

    public ImmutableColor(double red, double green, double blue, double alpha) {
        this((float) red, (float) green, (float) blue, (float) alpha);
    }

    public ImmutableColor(float red, float green, float blue) {
        this(red, green, blue, 1F);
    }

    public ImmutableColor(double red, double green, double blue) {
        this((float) red, (float) green, (float) blue, 1F);
    }

    public ImmutableColor(float grayscale, float alpha) {
        this(grayscale, grayscale, grayscale, alpha);
    }

    public ImmutableColor(double grayscale, double alpha) {
        this((float) grayscale, (float) alpha);
    }

    public ImmutableColor(float grayscale) {
        this(grayscale, 1F);
    }

    public ImmutableColor(double grayscale) {
        this((float) grayscale, 1F);
    }

    public ImmutableColor(AbstractColor color) {
        this(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public ImmutableColor(float[] color) {
        this(color[0], color[1], color[2], color[3]);
    }

    public ImmutableColor(double[] color) {
        this(color[0], color[1], color[2], color[3]);
    }

    public ImmutableColor(int[] color) {
        this(
            color[0] / 255F,
            color[1] / 255F,
            color[2] / 255F,
            color[3] / 255F
        );
    }


    @Override
    public float getRed() {
        return red;
    }

    @Override
    public float getGreen() {
        return green;
    }

    @Override
    public float getBlue() {
        return blue;
    }

    @Override
    public float getAlpha() {
        return alpha;
    }

    @Override
    public ImmutableColor copy() {
        return new ImmutableColor(this);
    }

}
