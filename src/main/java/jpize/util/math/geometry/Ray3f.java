package jpize.util.math.geometry;

import jpize.util.math.Maths;
import jpize.util.math.axisaligned.AABoxBody;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec3f;

import java.util.Objects;

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


    public float getIntersection(AABoxBody box) {
        return getIntersection(this, box);
    }

    public boolean intersect(AABoxBody box) {
        return intersect(this, box);
    }

    public float getIntersectionTriangle(Vec3f vertex1, Vec3f vertex2, Vec3f vertex3) {
        return getIntersectionTriangle(this, vertex1, vertex2, vertex3);
    }

    public boolean intersectTriangle(Vec3f vertex1, Vec3f vertex2, Vec3f vertex3) {
        return intersectTriangle(this, vertex1, vertex2, vertex3);
    }

    public float getIntersectionTriangle(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3) {
        return getIntersectionTriangle(this, x1, y1, z1, x2, y2, z2, x3, y3, z3);
    }

    public boolean intersectTriangle(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3) {
        return intersectTriangle(this, x1, y1, z1, x2, y2, z2, x3, y3, z3);
    }


    public Ray3f copy() {
        return new Ray3f(this);
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        final Ray3f ray = (Ray3f) object;
        return origin.equals(ray.origin) && direction.equals(ray.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, direction);
    }


    public static float getIntersection(Ray3f ray, AABoxBody aabb) {
        final float x1 = (aabb.getMin().x - ray.origin().x) / ray.directory().x;
        final float x2 = (aabb.getMax().x - ray.origin().x) / ray.directory().x;

        final float y1 = (aabb.getMin().y - ray.origin().y) / ray.directory().y;
        final float y2 = (aabb.getMax().y - ray.origin().y) / ray.directory().y;

        final float z1 = (aabb.getMin().z - ray.origin().z) / ray.directory().z;
        final float z2 = (aabb.getMax().z - ray.origin().z) / ray.directory().z;

        final float max = Maths.max(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));
        final float min = Maths.min(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));

        return (min < 0F || max > min) ? -1F : max;
    }

    public static boolean intersect(Ray3f ray, AABoxBody aabb) {
        return getIntersection(ray, aabb) != -1F;
    }

    public static float getIntersectionTriangle(Ray3f ray,
                                                float x1, float y1, float z1,
                                                float x2, float y2, float z2,
                                                float x3, float y3, float z3) {
        // edge1 = v2 - v1
        final float edge1x = (x2 - x1);
        final float edge1y = (y2 - y1);
        final float edge1z = (z2 - z1);

        // edge2 = v3 - v1
        final float edge2x = (x3 - x1);
        final float edge2y = (y3 - y1);
        final float edge2z = (z3 - z1);

        // h = cross(direction, edge2)
        final float hx = (ray.directory().y * edge2z - ray.directory().z * edge2y);
        final float hy = (ray.directory().z * edge2x - ray.directory().x * edge2z);
        final float hz = (ray.directory().x * edge2y - ray.directory().y * edge2x);

        // a = 1 / dot(h, edge1)
        final float a = (1F / (hx * edge1x + hy * edge1y + hz * edge1z));
        if(a == 0F)
            return -1F;

        // s = origin - v1
        final float sx = (ray.origin().x - x1);
        final float sy = (ray.origin().y - y1);
        final float sz = (ray.origin().z - z1);

        // u = a * dot(h, s)
        final float u = (a * (hx * sx + hy * sy + hz * sz));
        if(u < 0F || u > 1F)
            return -1F;

        // q = cross(s, edge1)
        final float qx = (sy * edge1z - sz * edge1y);
        final float qy = (sz * edge1x - sx * edge1z);
        final float qz = (sx * edge1y - sy * edge1x);

        // a * dot(q, direction)
        final float v = (a * (qx * ray.directory().x + qy * ray.directory().y + qz * ray.directory().z));
        if(v < 0F || u + v > 1F)
            return -1F;

        // t = a * dot(q, edge2) * len(direction)
        final float t = (a * (qx * edge2x + qy * edge2y + qz * edge2z) * ray.length());
        return (t > 0F) ? t : -1F;
    }

    public static boolean intersectTriangle(Ray3f ray,
                                            float x1, float y1, float z1,
                                            float x2, float y2, float z2,
                                            float x3, float y3, float z3) {
        return getIntersectionTriangle(ray, x1, y1, z1, x2, y2, z2, x3, y3, z3) != -1F;
    }

    public static float getIntersectionTriangle(Ray3f ray, Vec3f v1, Vec3f v2, Vec3f v3) {
        return getIntersectionTriangle(ray, v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, v3.x, v3.y, v3.z);
    }

    public static boolean intersectTriangle(Ray3f ray, Vec3f v1, Vec3f v2, Vec3f v3) {
        return getIntersectionTriangle(ray, v1, v2, v3) != -1F;
    }


    public static float getIntersectionQuad(Ray3f ray,
                                            float x1, float y1, float z1,
                                            float x2, float y2, float z2,
                                            float x3, float y3, float z3,
                                            float x4, float y4, float z4) {
        final float result = getIntersectionTriangle(ray, x1, y1, z1, x2, y2, z2, x3, y3, z3);
        if(result != -1F)
            return result;
        return getIntersectionTriangle(ray, x3, y3, z3, x4, y4, z4, x1, y1, z1);
    }

    public static boolean intersectQuad(Ray3f ray,
                                        float x1, float y1, float z1,
                                        float x2, float y2, float z2,
                                        float x3, float y3, float z3,
                                        float x4, float y4, float z4) {
        return getIntersectionQuad(ray, x1, y1, z1, x2, y2, z2, x3, y3, z3, x4, y4, z4) != -1F;
    }

    public static float getIntersection(Ray3f ray, Vec3f v1, Vec3f v2, Vec3f v3, Vec3f v4) {
        return getIntersectionQuad(ray, v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, v3.x, v3.y, v3.z, v4.x, v4.y, v4.z);
    }

    public static boolean intersectQuad(Ray3f ray, Vec3f v1, Vec3f v2, Vec3f v3, Vec3f v4) {
        return getIntersection(ray, v1, v2, v3, v4) != -1F;
    }


    public static float getIntersectionMesh(Ray3f ray, Matrix4f mat,
                                            float[] vertices, int[] indices,
                                            int stride, int positionAttributeOffset) {
        final Vec3f v1 = new Vec3f();
        final Vec3f v2 = new Vec3f();
        final Vec3f v3 = new Vec3f();

        for(int i = 0; i < indices.length; ){
            int offset1 = (indices[i++] * stride + positionAttributeOffset);
            int offset2 = (indices[i++] * stride + positionAttributeOffset);
            int offset3 = (indices[i++] * stride + positionAttributeOffset);

            v1.set(vertices[offset1++], vertices[offset1++], vertices[offset1]).mulMat4(mat);
            v2.set(vertices[offset2++], vertices[offset2++], vertices[offset2]).mulMat4(mat);
            v3.set(vertices[offset3++], vertices[offset3++], vertices[offset3]).mulMat4(mat);

            final float result = getIntersectionTriangle(ray, v1, v2, v3);
            if(result != -1F)
                return result;
        }
        return -1F;
    }

    public static float getIntersectionMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices, int stride) {
        return getIntersectionMesh(ray, mat, vertices, indices, stride, 0);
    }

    public static float getIntersectionMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices) {
        return getIntersectionMesh(ray, mat, vertices, indices, 3);
    }

    public static boolean intersectMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices,
                                        int stride, int positionAttributeOffset) {
        return getIntersectionMesh(ray, mat, vertices, indices, stride, positionAttributeOffset) != -1F;
    }

    public static boolean intersectMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices, int stride) {
        return intersectMesh(ray, mat, vertices, indices, stride, 0);
    }

    public static boolean intersectMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices) {
        return intersectMesh(ray, mat, vertices, indices, 3);
    }


    public static float getIntersectionQuadMesh(Ray3f ray, Matrix4f matrix, float[] vertices, int[] indices,
                                                int stride, int positionAttributeOffset) {
        final Vec3f v1 = new Vec3f();
        final Vec3f v2 = new Vec3f();
        final Vec3f v3 = new Vec3f();
        final Vec3f v4 = new Vec3f();

        for(int i = 0; i < indices.length; ){
            int offset1 = (indices[i++] * stride + positionAttributeOffset);
            int offset2 = (indices[i++] * stride + positionAttributeOffset);
            int offset3 = (indices[i++] * stride + positionAttributeOffset);
            int offset4 = (indices[i++] * stride + positionAttributeOffset);

            v1.set(vertices[offset1++], vertices[offset1++], vertices[offset1]).mulMat4(matrix);
            v2.set(vertices[offset2++], vertices[offset2++], vertices[offset2]).mulMat4(matrix);
            v3.set(vertices[offset3++], vertices[offset3++], vertices[offset3]).mulMat4(matrix);
            v4.set(vertices[offset4++], vertices[offset4++], vertices[offset4]).mulMat4(matrix);

            final float result = getIntersection(ray, v1, v2, v3, v4);
            if(result != -1F)
                return result;
        }
        return -1F;
    }

    public static float getIntersectionQuadMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices, int stride) {
        return getIntersectionQuadMesh(ray, mat, vertices, indices, stride, 0);
    }

    public static float getIntersectionQuadMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices) {
        return getIntersectionQuadMesh(ray, mat, vertices, indices, 3);
    }

    public static boolean intersectQuadMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices,
                                            int stride, int positionAttributeOffset) {
        return getIntersectionQuadMesh(ray, mat, vertices, indices, stride, positionAttributeOffset) != -1F;
    }

    public static boolean intersectQuadMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices, int stride) {
        return intersectQuadMesh(ray, mat, vertices, indices, stride, 0);
    }

    public static boolean intersectQuadMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices) {
        return intersectQuadMesh(ray, mat, vertices, indices, 3);
    }

}
