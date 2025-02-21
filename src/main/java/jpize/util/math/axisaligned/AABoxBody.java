package jpize.util.math.axisaligned;

import jpize.util.math.geometry.Intersector;
import jpize.util.math.geometry.Ray3f;
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


    public Vec3f min() {
        return min.set(boundingBox.min()).add(position);
    }

    public Vec3f max() {
        return max.set(boundingBox.max()).add(position);
    }


    public AABox box() {
        return boundingBox;
    }

    public Vec3f position() {
        return position;
    }


    public AABoxBody copy() {
        return new AABoxBody(this);
    }


    public static boolean overlaps(float min1X, float min1Y, float min1Z,
                                   float max1X, float max1Y, float max1Z,
                                   float min2X, float min2Y, float min2Z,
                                   float max2X, float max2Y, float max2Z) {
        return (
                Intersector.isRangesOverlapping(min1X, max1X, min2X, max2X) &&
                Intersector.isRangesOverlapping(min1Y, max1Y, min2Y, max2Y) &&
                Intersector.isRangesOverlapping(min1Z, max1Z, min2Z, max2Z)
        );
    }

    public static boolean overlaps(AABoxBody a, AABoxBody b) {
        final Vec3f min1 = a.min();
        final Vec3f max1 = a.max();
        final Vec3f min2 = b.min();
        final Vec3f max2 = b.max();

        return overlaps(
                min1.x, min1.y, min1.z,
                max1.x, max1.y, max1.z,
                min2.x, min2.y, min2.z,
                max2.x, max2.y, max2.z
        );
    }

    public boolean overlaps(AABoxBody box) {
        return AABoxBody.overlaps(this, box);
    }

    public boolean intersect(Ray3f ray) {
        return ray.intersect(this);
    }

    public float intersection(Ray3f ray) {
        return ray.intersection(this);
    }

}
