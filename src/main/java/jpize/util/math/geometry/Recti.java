package jpize.util.math.geometry;

import jpize.util.math.vector.Vec2i;
import jpize.util.math.vector.Vec4i;

import java.util.Collection;
import java.util.Objects;

public class Recti {

    public int x, y;
    public int width, height;

    public Recti(Recti rect) {
        this.set(rect);
    }

    public Recti(int x, int y, int width, int height) {
        this.set(x, y, width, height);
    }

    public Recti(int width, int height) {
        this.setSize(width, height);
    }

    public Recti() { }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public Recti setX(int x) {
        this.x = x;
        return this;
    }

    public Recti setY(int y) {
        this.y = y;
        return this;
    }

    public Recti setWidth(int width) {
        this.width = width;
        return this;
    }

    public Recti setHeight(int height) {
        this.height = height;
        return this;
    }
    

    public Recti setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Recti setPosition(int xy) {
        return this.setPosition(xy, xy);
    }


    public Recti setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Recti setSize(int sizeXY) {
        return this.setSize(sizeXY, sizeXY);
    }


    public Recti set(int x, int y, int width, int height) {
        this.setPosition(x, y);
        this.setSize(width, height);
        return this;
    }
    
    public Recti set(Recti rect) {
        return this.set(rect.x, rect.y, rect.width, rect.height);
    }

    public Recti reset() {
        return this.set(0, 0, 0, 0);
    }


    public Recti calculateFor(Collection<Vec2i> points) {
        this.setPosition(Integer.MAX_VALUE);
        this.setSize(0);

        for(Vec2i vertex: points){
            this.set(
                Math.min(x, vertex.x),
                Math.min(y, vertex.y),
                Math.max(width, vertex.x),
                Math.max(height, vertex.y)
            );
        }

        return this.setSize(width - x, height - y);
    }

    public Recti calculateFor(int... points) {
        this.setPosition(Integer.MAX_VALUE);
        this.setSize(0);

        for(int i = 0; i < points.length; i += 2){
            final int px = points[i];
            final int py = points[i + 1];
            this.set(
                Math.min(x, px),
                Math.min(y, py),
                Math.max(width, px),
                Math.max(height, py)
            );
        }

        return this.setSize(width - x, height - y);
    }


    public Recti copy() {
        return new Recti(this);
    }
    
    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;
        final Recti rect = (Recti) object;
        return Vec4i.equals(x, y, width, height, rect.x, rect.y, rect.width, rect.height);
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
