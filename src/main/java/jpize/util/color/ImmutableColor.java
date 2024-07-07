package jpize.util.color;

public class ImmutableColor {

    public final float r, g, b, a;

    public ImmutableColor() {
        this.r = 1F;
        this.g = 1F;
        this.b = 1F;
        this.a = 1F;
    }

    public ImmutableColor(float red, float green, float blue, float alpha) {
        this.r = red;
        this.g = green;
        this.b = blue;
        this.a = alpha;
    }

    public ImmutableColor(float red, float green, float blue) {
        this(red, green, blue, 1F);
    }

    public ImmutableColor(float grayscale, float alpha) {
        this(grayscale, grayscale, grayscale, alpha);
    }

    public ImmutableColor(float grayscale) {
        this(grayscale, 1F);
    }

    public ImmutableColor(double red, double green, double blue, double alpha) {
        this((float) red, (float) green, (float) blue, (float) alpha);
    }

    public ImmutableColor(double red, double green, double blue) {
        this((float) red, (float) green, (float) blue, 1F);
    }

    public ImmutableColor(double grayscale, double alpha) {
        this((float) grayscale, (float) alpha);
    }

    public ImmutableColor(double grayscale) {
        this((float) grayscale, 1F);
    }

    public ImmutableColor(Color color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = color.a;
    }

    public ImmutableColor(ImmutableColor color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = color.a;
    }

    public ImmutableColor(float[] color) {
        this.r = color[0];
        this.g = color[1];
        this.b = color[2];
        this.a = color[3];
    }


    public float[] toArray(){
        return Colors.rgbaToArray(this);
    }


    public ImmutableColor copy() {
        return new ImmutableColor(this);
    }

    @Override
    public String toString(){
        return Colors.toString(this);
    }

}
