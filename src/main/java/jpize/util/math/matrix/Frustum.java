package jpize.util.math.matrix;

import jpize.util.math.axisaligned.AABoxBody;
import jpize.util.math.vector.Vec3f;
import jpize.util.math.vector.Vec4f;

import static jpize.util.math.matrix.Matrix4.*;

public class Frustum {

    private final Vec4f[] vectors;

    public Frustum() {
        this.vectors = new Vec4f[]{
            new Vec4f(), new Vec4f(), new Vec4f(),
            new Vec4f(), new Vec4f(), new Vec4f()
        };
    }

    public Frustum(Matrix4f combined) {
        this();
        this.setFrustum(combined);
    }

    public Frustum(Matrix4f view, Matrix4f projection) {
        this();
        this.setFrustum(projection, view);
    }


    public void setFrustum(float[] combined) {
        final float v0 = combined[m03];
        final float v1 = combined[m13];
        final float v2 = combined[m23];
        final float v3 = combined[m33];

        vectors[0].set((v0 - combined[m00]), (v1 - combined[m10]), (v2 - combined[m20]), (v3 - combined[m30])).nor();
        vectors[1].set((v0 + combined[m00]), (v1 + combined[m10]), (v2 + combined[m20]), (v3 + combined[m30])).nor();
        vectors[2].set((v0 + combined[m01]), (v1 + combined[m11]), (v2 + combined[m21]), (v3 + combined[m31])).nor();
        vectors[3].set((v0 - combined[m01]), (v1 - combined[m11]), (v2 - combined[m21]), (v3 - combined[m31])).nor();
        vectors[4].set((v0 - combined[m02]), (v1 - combined[m12]), (v2 - combined[m22]), (v3 - combined[m32])).nor();
        vectors[5].set((v0 + combined[m02]), (v1 + combined[m12]), (v2 + combined[m22]), (v3 + combined[m32])).nor();
    }

    public void setFrustum(Matrix4f combined) {
        this.setFrustum(combined.val);
    }

    public void setFrustum(Matrix4f view, Matrix4f projection) {
        final float[] combined = Matrix4f.mul(projection.val, view.val);
        this.setFrustum(combined);
    }


    private float multiply(int index, float x, float y, float z) {
        final Vec4f vector = vectors[index];
        return (vector.x * x) + (vector.y * y) + (vector.z * z) + vector.w;
    }


    public boolean isPointIn(float x, float y, float z) {
        for(int i = 0; i < 6; i++)
            if(this.multiply(i, x, y, z) <= 0F)
                return false;
        return true;
    }

    public boolean isPointIn(Vec3f point) {
        return this.isPointIn(point.x, point.y, point.z);
    }


    public boolean isAABoxIn(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        for(int i = 0; i < 6; i++)
            if(this.multiply(i, minX, minY, minZ) <= 0F && this.multiply(i, maxX, minY, minZ) <= 0F && this.multiply(i, minX, maxY, minZ) <= 0F &&
               this.multiply(i, maxX, maxY, minZ) <= 0F && this.multiply(i, minX, minY, maxZ) <= 0F && this.multiply(i, maxX, minY, maxZ) <= 0F &&
               this.multiply(i, minX, maxY, maxZ) <= 0F && this.multiply(i, maxX, maxY, maxZ) <= 0F)
                return false;
        return true;
    }

    public boolean isAABoxIn(Vec3f min, Vec3f max) {
        return this.isAABoxIn(min.x, min.y, min.z, max.x, max.y, max.z);
    }

    public boolean isAABoxIn(AABoxBody box) {
        return this.isAABoxIn(box.getMin(), box.getMax());
    }

}