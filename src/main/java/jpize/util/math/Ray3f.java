package jpize.util.math;

import jpize.util.math.axisaligned.AABoxBody;
import jpize.util.math.geometry.Intersector;
import jpize.util.math.vector.Vec3f;

public class Ray3f {

    private final Vec3f origin;
    private final Vec3f direction;

    public Ray3f() {
        this.origin = new Vec3f();
        this.direction = new Vec3f();
    }

    public Ray3f(Ray3f ray) {
        this.origin = ray.origin.copy();
        this.direction = ray.direction.copy();
    }


    public Ray3f set(Vec3f origin, Vec3f direction) {
        this.origin.set(origin);
        this.direction.set(direction);
        return this;
    }

    public Ray3f set(Vec3f origin, Vec3f direction, float length) {
        this.set(origin, direction);
        direction.mul(length);
        return this;
    }

    public Ray3f set(Ray3f ray) {
        this.set(ray.origin, ray.direction);
        return this;
    }

    public Ray3f set(Vec3f direction) {
        direction.set(direction);
        return this;
    }


    public Vec3f origin() {
        return origin;
    }

    public Vec3f directory() {
        return direction;
    }

    public float length() {
        return direction.len();
    }


    public float getIntersectAABB(AABoxBody box) {
        return Intersector.getRayIntersectAABox(this, box);
    }

    public boolean isIntersectAABB(AABoxBody box) {
        return Intersector.isRayIntersectAABox(this, box);
    }

    public float getIntersectTriangle(Vec3f vertex1, Vec3f vertex2, Vec3f vertex3) {
        return Intersector.getRayIntersectTriangle(this, vertex1, vertex2, vertex3);
    }

    public boolean isIntersectTriangle(Vec3f vertex1, Vec3f vertex2, Vec3f vertex3) {
        return Intersector.isRayIntersectTriangle(this, vertex1, vertex2, vertex3);
    }

    public float getIntersectTriangle(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3) {
        return Intersector.getRayIntersectTriangle(this, x1, y1, z1, x2, y2, z2, x3, y3, z3);
    }

    public boolean isIntersectTriangle(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3) {
        return Intersector.isRayIntersectTriangle(this, x1, y1, z1, x2, y2, z2, x3, y3, z3);
    }


    public Ray3f copy() {
        return new Ray3f(this);
    }

}
