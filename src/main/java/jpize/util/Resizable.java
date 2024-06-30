package jpize.util;

public abstract class Resizable {

    protected int width;
    protected int height;

    public Resizable(int width, int height) {
        setSize(width, height);
    }

    public Resizable(Resizable resizable) {
        setSize(resizable);
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getHalfWidth() {
        return width * 0.5F;
    }

    public float getHalfHeight() {
        return height * 0.5F;
    }


    protected void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected void setSize(Resizable resizable) {
        this.width = resizable.width;
        this.height = resizable.height;
    }


    public float aspect() {
        return (float) width / height;
    }

    public boolean matchSize(Resizable resizable) {
        return resizable.width == width && resizable.height == height;
    }

    public boolean matchSize(int width, int height) {
        return this.width == width && this.height == height;
    }

}
