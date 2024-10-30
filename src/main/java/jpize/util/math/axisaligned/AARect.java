package jpize.util.math.axisaligned;

import jpize.util.math.vector.Vec2f;

public class AARect {

    private final Vec2f min, max;

    public AARect(double minX, double minY, double maxX, double maxY) {
        this.min = new Vec2f(minX, minY);
        this.max = new Vec2f(maxX, maxY);
    }

    public AARect(Vec2f min, Vec2f max) {
        this.min = min;
        this.max = max;
    }

    public AARect(AARect box) {
        this.min = box.min.copy();
        this.max = box.max.copy();
    }


    public void resize(double minX, double minY, double maxX, double maxY) {
        this.min.set(minX, minY);
        this.max.set(maxX, maxY);
    }

    public void resize(Vec2f min, Vec2f max) {
        this.min.set(min);
        this.max.set(max);
    }

    public void resize(AARect box) {
        this.resize(box.min, box.max);
    }


    public void expand(double negativeX, double negativeY, double positiveX, double positiveY) {
        this.resize(
            min.x - negativeX, min.y - negativeY,
            max.x + positiveX, max.y + positiveY
        );
    }

    public void expand(Vec2f negative, Vec2f positive) {
        this.expand(negative.x, negative.y, positive.x, positive.y);
    }

    public void expand(double expandX, double expandY) {
        this.expand(expandX, expandY, expandX, expandY);
    }

    public void expand(double expand) {
        this.expand(expand, expand);
    }


    public void shift(double shiftX, double shiftY) {
        this.resize(
            min.x + shiftX, min.y + shiftY,
            max.x + shiftX, max.y + shiftY
        );
    }

    public void shift(Vec2f shift) {
        this.shift(shift.x, shift.y);
    }


    public Vec2f min() {
        return min;
    }

    public Vec2f max() {
        return max;
    }


    public float getWidth() {
        return max.x - min.x;
    }

    public float getHeight() {
        return max.y - min.y;
    }


    public float getArea() {
        return this.getHeight() * this.getWidth();
    }

    public float getCenterX() {
        return (min.x + this.getWidth() * 0.5F);
    }

    public float getCenterY() {
        return (min.y + this.getHeight() * 0.5F);
    }

    public Vec2f getCenter(Vec2f dst) {
        return dst.set(this.getCenterX(), this.getCenterY());
    }


    public AARect copy() {
        return new AARect(this);
    }

}
