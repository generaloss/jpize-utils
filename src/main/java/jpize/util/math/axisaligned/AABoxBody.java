package jpize.util.math.axisaligned;

import jpize.util.math.geometry.Intersector;
import jpize.util.math.Ray3f;
import jpize.util.math.vector.Vec3f;

public class AABoxBody {

    private final AABox boundingBox;
    private final Vec3f position, min, max;

    public AABoxBody(AABox box, Vec3f position) {
        this.boundingBox = box;
        this.position = position;
        this.min = new Vec3f();
        this.max = new Vec3f();
    }

    public AABoxBody(AABoxBody body) {
        this(
            body.box().copy(),
            body.position().copy()
        );
    }

    public AABoxBody(AABox box) {
        this(box, new Vec3f());
    }

    public AABoxBody(Vec3f position) {
        this(new AABox(0, 0, 0, 0, 0, 0), position);
    }

    public AABoxBody() {
        this(new AABox(0, 0, 0, 0, 0, 0), new Vec3f());
    }


    public Vec3f getMin() {
        return min.set(boundingBox.min()).add(position);
    }

    public Vec3f getMax() {
        return max.set(boundingBox.max()).add(position);
    }


    public AABox box() {
        return boundingBox;
    }

    public Vec3f position() {
        return position;
    }


    public boolean isIntersectBox(AABoxBody box) {
        return Intersector.isAABoxIntersectAABox(this, box);
    }

    public float getIntersectionRay(Ray3f ray) {
        return Intersector.getRayIntersectAABox(ray, this);
    }

    public boolean isIntersectRay(Ray3f ray) {
        return Intersector.isRayIntersectAABox(ray, this);
    }


    public AABoxBody copy() {
        return new AABoxBody(this);
    }

}
