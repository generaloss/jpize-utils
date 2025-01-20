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


    public AABoxBody copy() {
        return new AABoxBody(this);
    }


    public static boolean intersects(float min1X, float min1Y, float min1Z,
                                     float max1X, float max1Y, float max1Z,
                                     float min2X, float min2Y, float min2Z,
                                     float max2X, float max2Y, float max2Z) {
        return (
                Intersector.isGapIntersectGap(min1X, max1X, min2X, max2X) &&
                Intersector.isGapIntersectGap(min1Y, max1Y, min2Y, max2Y) &&
                Intersector.isGapIntersectGap(min1Z, max1Z, min2Z, max2Z)
        );
    }

    public static boolean intersects(AABoxBody a, AABoxBody b) {
        final Vec3f min1 = a.getMin();
        final Vec3f max1 = a.getMax();
        final Vec3f min2 = b.getMin();
        final Vec3f max2 = b.getMax();

        return intersects(
                min1.x, min1.y, min1.z,
                max1.x, max1.y, max1.z,
                min2.x, min2.y, min2.z,
                max2.x, max2.y, max2.z
        );
    }

    public boolean intersect(AABoxBody box) {
        return intersects(this, box);
    }

    public boolean intersect(Ray3f ray) {
        return ray.intersect(this);
    }

    public float getIntersection(Ray3f ray) {
        return ray.getIntersection(this);
    }






}
