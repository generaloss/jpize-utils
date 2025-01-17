package jpize.util.math.geometry;

import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec4f;

import java.util.Objects;

public class Rect {

    public float x;
    public float y;
    public float width;
    public float height;

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


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(float xy) {
        this.setPosition(xy, xy);
    }


    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(float sizeXY) {
        this.setSize(sizeXY, sizeXY);
    }


    public void set(float x, float y, float width, float height) {
        this.setPosition(x, y);
        this.setSize(width, height);
    }
    
    public void set(Rect rect) {
        this.set(rect.x, rect.y, rect.width, rect.height);
    }

    public void reset() {
        this.set(0F, 0F, 0F, 0F);
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


    public static Rect boundsOf(Rect dst, Vec2f... vertices) {
        dst.setPosition(Float.MAX_VALUE);
        dst.setSize(0F);

        for(Vec2f vertex: vertices){
            dst.set(
                    Math.min(dst.x, vertex.x),
                    Math.min(dst.y, vertex.y),
                    Math.max(dst.width, vertex.x),
                    Math.max(dst.height, vertex.y)
            );
        }

        dst.setSize(dst.width - dst.x, dst.height - dst.y);
        return dst;
    }

    public static Rect boundsOf(Rect dst, float... vertices) {
        dst.setPosition(Float.MAX_VALUE);
        dst.setSize(0F);

        for(int i = 0; i < vertices.length; i += 3){
            final float x = vertices[i];
            final float y = vertices[i + 1];
            dst.set(
                    Math.min(dst.x, x),
                    Math.min(dst.y, y),
                    Math.max(dst.width, x),
                    Math.max(dst.height, y)
            );
        }

        dst.setSize(dst.width - dst.x, dst.height - dst.y);
        return dst;
    }

}
