package jpize.util.math.geometry;

import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec4f;

import java.util.Objects;

public class Rect {

    public float x, y;
    public float width, height;

    public Rect(Rect rect) {
        this.set(rect);
    }

    public Rect(float x, float y, float width, float height) {
        this.set(x, y, width, height);
    }

    public Rect(float width, float height) {
        this.setSize(width, height);
    }

    public Rect() { }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


    public Rect setX(float x) {
        this.x = x;
        return this;
    }

    public Rect setY(float y) {
        this.y = y;
        return this;
    }

    public Rect setWidth(float width) {
        this.width = width;
        return this;
    }

    public Rect setHeight(float height) {
        this.height = height;
        return this;
    }
    

    public Rect setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Rect setPosition(float xy) {
        return this.setPosition(xy, xy);
    }


    public Rect setSize(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Rect setSize(float sizeXY) {
        return this.setSize(sizeXY, sizeXY);
    }


    public Rect set(float x, float y, float width, float height) {
        this.setPosition(x, y);
        this.setSize(width, height);
        return this;
    }
    
    public Rect set(Rect rect) {
        return this.set(rect.x, rect.y, rect.width, rect.height);
    }

    public Rect reset() {
        return this.set(0F, 0F, 0F, 0F);
    }


    public Rect calculateFor(Vec2f... points) {
        this.setPosition(Float.MAX_VALUE);
        this.setSize(0F);

        for(Vec2f vertex: points){
            this.set(
                Math.min(x, vertex.x),
                Math.min(y, vertex.y),
                Math.max(width, vertex.x),
                Math.max(height, vertex.y)
            );
        }

        return this.setSize(width - x, height - y);
    }

    public Rect calculateFor(float... points) {
        this.setPosition(Float.MAX_VALUE);
        this.setSize(0F);

        for(int i = 0; i < points.length; i += 2){
            final float px = points[i];
            final float py = points[i + 1];
            this.set(
                Math.min(x, px),
                Math.min(y, py),
                Math.max(width, px),
                Math.max(height, py)
            );
        }

        return this.setSize(width - x, height - y);
    }


    public Rect copy() {
        return new Rect(this);
    }
    
    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;
        final Rect rect = (Rect) object;
        return Vec4f.equals(x, y, width, height, rect.x, rect.y, rect.width, rect.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + "; " + width + ", " + height + "}";
    }

}
