package jpize.util.math.matrix;

import jpize.util.math.Mathc;
import jpize.util.math.vector.Vec4f;

import static jpize.util.math.matrix.Matrix4.*;

public class Frustum {

    private final Vec4f[] val;

    public Frustum() {
        this.val = new Vec4f[6];
        for(int i = 0; i < val.length; i++)
            val[i] = new Vec4f();
    }

    public Frustum(Matrix4f view, Matrix4f proj) {
        this();
        this.setFrustum(proj, view);
    }

    public Frustum(Matrix4f comb) {
        this();
        this.setFrustum(comb);
    }


    public void setFrustum(Matrix4f view, Matrix4f proj) {
        final float[] comb = Matrix4f.mul(proj.val, view.val);
        this.setFrustum(comb);
    }

    public void setFrustum(Matrix4f combined) {
        this.setFrustum(combined.val);
    }

    public void setFrustum(float[] combined) {
        val[0].x = (combined[m03] - combined[m00]);
        val[0].y = (combined[m13] - combined[m10]);
        val[0].z = (combined[m23] - combined[m20]);
        val[0].w = (combined[m33] - combined[m30]);

        val[1].x = (combined[m03] + combined[m00]);
        val[1].y = (combined[m13] + combined[m10]);
        val[1].z = (combined[m23] + combined[m20]);
        val[1].w = (combined[m33] + combined[m30]);

        val[2].x = (combined[m03] + combined[m01]);
        val[2].y = (combined[m13] + combined[m11]);
        val[2].z = (combined[m23] + combined[m21]);
        val[2].w = (combined[m33] + combined[m31]);

        val[3].x = (combined[m03] - combined[m01]);
        val[3].y = (combined[m13] - combined[m11]);
        val[3].z = (combined[m23] - combined[m21]);
        val[3].w = (combined[m33] - combined[m31]);

        val[4].x = (combined[m03] - combined[m02]);
        val[4].y = (combined[m13] - combined[m12]);
        val[4].z = (combined[m23] - combined[m22]);
        val[4].w = (combined[m33] - combined[m32]);

        val[5].x = (combined[m03] + combined[m02]);
        val[5].y = (combined[m13] + combined[m12]);
        val[5].z = (combined[m23] + combined[m22]);
        val[5].w = (combined[m33] + combined[m32]);

        for(int i = 0; i < 6; i++)
            this.divide(i);
    }

    private void divide(int index) {
        final float sqrt = Mathc.sqrt(
            val[index].x * val[index].x +
            val[index].y * val[index].y +
            val[index].z * val[index].z
        );

        val[index].x /= sqrt;
        val[index].y /= sqrt;
        val[index].z /= sqrt;
        val[index].w /= sqrt;
    }

    private float multiply(int index, float x, float y, float z) {
        return val[index].x * x  +  val[index].y * y  +  val[index].z * z  +  val[index].w;
    }

    public boolean isBoxInFrustum(float x1, float y1, float z1, float x2, float y2, float z2) {
        for(int i = 0; i < 6; i++)
            if(this.multiply(i, x1, y1, z1) <= 0F && this.multiply(i, x2, y1, z1) <= 0F && this.multiply(i, x1, y2, z1) <= 0F &&
               this.multiply(i, x2, y2, z1) <= 0F && this.multiply(i, x1, y1, z2) <= 0F && this.multiply(i, x2, y1, z2) <= 0F &&
               this.multiply(i, x1, y2, z2) <= 0F && this.multiply(i, x2, y2, z2) <= 0F)
                return false;
        return true;
    }

    public boolean isVertexInFrustum(float x, float y, float z) {
        for(int i = 0; i < 6; i++)
            if(this.multiply(i, x, y, z) <= 0F)
                return false;
        return true;
    }

}