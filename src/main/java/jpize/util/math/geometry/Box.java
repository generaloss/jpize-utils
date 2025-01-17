package jpize.util.math.geometry;

import jpize.util.math.vector.Vec3f;

import java.util.Objects;

public class Box {

    public float x;
    public float y;
    public float z;
    public float width;
    public float height;
    public float depth;

    public Box(Box box) {
        this.set(box);
    }

    public Box(float x, float y, float z, float width, float height, float depth) {
        this.set(x, y, z, width, height, depth);
    }

    public Box(float width, float height, float depth) {
        this.setSize(width, height, depth);
    }

    public Box() { }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getDepth() {
        return depth;
    }


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }


    public void setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setPosition(float xyz) {
        this.setPosition(xyz, xyz, xyz);
    }


    public void setSize(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public void setSize(float sizeXYZ) {
        this.setSize(sizeXYZ, sizeXYZ, sizeXYZ);
    }


    public void set(float x, float y, float z, float width, float height, float depth) {
        this.setPosition(x, y, z);
        this.setSize(width, height, depth);
    }

    public void set(Box box) {
        this.set(box.x, box.y, box.z, box.width, box.height, box.depth);
    }

    public void reset() {
        this.set(0F, 0F, 0F, 0F, 0F, 0F);
    }


    public Box copy() {
        return new Box(this);
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;
        final Box box = (Box) object;
        return Vec3f.equals(x, y, z, box.x, box.y, box.z) && Vec3f.equals(width, height, depth, box.width, box.height, box.depth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, width, height, depth);
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + ", " + z + "; " + width + ", " + height + ", " + depth + "}";
    }


    public static Box boundsOf(Box dst, Vec3f... vertices) {
        dst.setPosition(Float.MAX_VALUE);
        dst.setSize(0F);

        for(Vec3f vertex: vertices){
            dst.set(
                    Math.min(dst.x, vertex.x),
                    Math.min(dst.y, vertex.y),
                    Math.min(dst.z, vertex.z),
                    Math.max(dst.width, vertex.x),
                    Math.max(dst.height, vertex.y),
                    Math.max(dst.depth, vertex.z)
            );
        }

        dst.setSize(dst.width - dst.x, dst.height - dst.y, dst.depth - dst.z);
        return dst;
    }

    public static Box boundsOf(Box dst, float... vertices) {
        dst.setPosition(Float.MAX_VALUE);
        dst.setSize(0F);

        for(int i = 0; i < vertices.length; i += 3){
            final float x = vertices[i];
            final float y = vertices[i + 1];
            final float z = vertices[i + 2];
            dst.set(
                    Math.min(dst.x, x),
                    Math.min(dst.y, y),
                    Math.min(dst.z, z),
                    Math.max(dst.width, x),
                    Math.max(dst.height, y),
                    Math.max(dst.depth, z)
            );
        }

        dst.setSize(dst.width - dst.x, dst.height - dst.y, dst.depth - dst.z);
        return dst;
    }

}
