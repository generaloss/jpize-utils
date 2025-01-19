package jpize.util.math.geometry;

import jpize.util.math.vector.Vec3f;

import java.util.Objects;

public class Box {

    public float x, y, z;
    public float width, height, depth;

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


    public Box setX(float x) {
        this.x = x;
        return this;
    }

    public Box setY(float y) {
        this.y = y;
        return this;
    }

    public Box setZ(float z) {
        this.z = z;
        return this;
    }

    public Box setWidth(float width) {
        this.width = width;
        return this;
    }

    public Box setHeight(float height) {
        this.height = height;
        return this;
    }

    public Box setDepth(float depth) {
        this.depth = depth;
        return this;
    }


    public Box setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Box setPosition(float xyz) {
        return this.setPosition(xyz, xyz, xyz);
    }


    public Box setSize(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        return this;
    }

    public Box setSize(float sizeXYZ) {
        return this.setSize(sizeXYZ, sizeXYZ, sizeXYZ);
    }


    public Box set(float x, float y, float z, float width, float height, float depth) {
        this.setPosition(x, y, z);
        this.setSize(width, height, depth);
        return this;
    }

    public Box set(Box box) {
        return this.set(box.x, box.y, box.z, box.width, box.height, box.depth);
    }

    public Box reset() {
        return this.set(0F, 0F, 0F, 0F, 0F, 0F);
    }


    public Box calculateFor(Vec3f... points) {
        this.setPosition(Float.MAX_VALUE);
        this.setSize(0F);

        for(Vec3f vertex: points){
            this.set(
                Math.min(x, vertex.x),
                Math.min(y, vertex.y),
                Math.min(z, vertex.z),
                Math.max(width, vertex.x),
                Math.max(height, vertex.y),
                Math.max(depth, vertex.z)
            );
        }

        return this.setSize(width - x, height - y, depth - z);
    }

    public Box calculateFor(float... points) {
        this.setPosition(Float.MAX_VALUE);
        this.setSize(0F);

        for(int i = 0; i < points.length; i += 3){
            final float px = points[i];
            final float py = points[i + 1];
            final float pz = points[i + 2];
            this.set(
                Math.min(x, px),
                Math.min(y, py),
                Math.min(z, pz),
                Math.max(width, px),
                Math.max(height, py),
                Math.max(depth, pz)
            );
        }

        return this.setSize(width - x, height - y, depth - z);
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

}
