package jpize.util.math.geometry;

import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec4f;

import java.util.Collection;
import java.util.Objects;

public class Rectf {

    public float x, y;
    public float width, height;

    public Rectf(Rectf rect) {
        this.set(rect);
    }

    public Rectf(float x, float y, float width, float height) {
        this.set(x, y, width, height);
    }

    public Rectf(float width, float height) {
        this.setSize(width, height);
    }

    public Rectf() { }


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


    public Rectf setX(float x) {
        this.x = x;
        return this;
    }

    public Rectf setY(float y) {
        this.y = y;
        return this;
    }

    public Rectf setWidth(float width) {
        this.width = width;
        return this;
    }

    public Rectf setHeight(float height) {
        this.height = height;
        return this;
    }
    

    public Rectf setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Rectf setPosition(float xy) {
        return this.setPosition(xy, xy);
    }


    public Rectf setSize(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Rectf setSize(float sizeXY) {
        return this.setSize(sizeXY, sizeXY);
    }


    public Rectf set(float x, float y, float width, float height) {
        this.setPosition(x, y);
        this.setSize(width, height);
        return this;
    }
    
    public Rectf set(Rectf rect) {
        return this.set(rect.x, rect.y, rect.width, rect.height);
    }

    public Rectf reset() {
        return this.set(0F, 0F, 0F, 0F);
    }


    public Rectf calculateFor(Collection<Vec2f> points) {
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

    public Rectf calculateFor(float... points) {
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


    public Rectf copy() {
        return new Rectf(this);
    }
    
    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;
        final Rectf rect = (Rectf) object;
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
