package jpize.util.math.matrix;

import jpize.util.math.Mathc;
import jpize.util.math.Maths;
import jpize.util.math.vector.*;

import java.util.Arrays;

public class Matrix3f implements Matrix3 {

    public final float[] val;

    public Matrix3f() {
        this.val = new float[16];
        this.val[m00] = 1F;
        this.val[m11] = 1F;
        this.val[m22] = 1F;
    }

    public Matrix3f(float... values) {
        this.val = new float[16];
        this.set(values);
    }

    public Matrix3f(Matrix3f matrix) {
        this(matrix.val);
    }


    public Matrix3f set(float... values) {
        System.arraycopy(values, 0, val, 0, values.length);
        return this;
    }

    public Matrix3f set(Matrix3f matrix) {
        this.set(matrix.val);
        return this;
    }

    public Matrix3f zero() {
        Arrays.fill(val, 0F);
        return this;
    }

    public Matrix3f identity() {
        val[m00] = 1F;
        val[m10] = 0F;
        val[m20] = 0F;
        val[m01] = 0F;
        val[m11] = 1F;
        val[m21] = 0F;
        val[m02] = 0F;
        val[m12] = 0F;
        val[m22] = 1F;
        return this;
    }


    /* Translate */

    public Matrix3f translate(float x, float y) {
        val[m20] += val[m00] * x + val[m10] * y;
        val[m21] += val[m01] * x + val[m11] * y;
        val[m22] += val[m02] * x + val[m12] * y;
        return this;
    }

    public Matrix3f translate(double x, double y) {
        return this.translate((float) x, (float) y);
    }

    public Matrix3f translate(Vec2f vec2) {
        return this.translate(vec2.x, vec2.y);
    }

    public Matrix3f translate(Vec2d vec2) {
        return this.translate(vec2.x, vec2.y);
    }

    public Matrix3f translate(Vec2i vec2) {
        return this.translate(vec2.x, vec2.y);
    }


    /* Set Translate */

    public Matrix3f setTranslate(float x, float y) {
        this.identity();
        val[m20] = x;
        val[m21] = y;
        return this;
    }

    public Matrix3f setTranslate(double x, double y) {
        return this.setTranslate((float) x, (float) y);
    }

    public Matrix3f setTranslate(Vec2f vec2) {
        return this.setTranslate(vec2.x, vec2.y);
    }

    public Matrix3f setTranslate(Vec2d vec2) {
        return this.setTranslate(vec2.x, vec2.y);
    }

    public Matrix3f setTranslate(Vec2i vec2) {
        return this.setTranslate(vec2.x, vec2.y);
    }


    /* Set Scale */

    public Matrix3f setScale(float x, float y) {
        this.identity();
        val[m00] = x;
        val[m11] = y;
        return this;
    }

    public Matrix3f setScale(double x, double y) {
        return this.setScale((float) x, (float) y);
    }

    public Matrix3f setScale(float scale) {
        return this.setScale(scale, scale);
    }

    public Matrix3f setScale(double scale) {
        return this.setScale((float) scale);
    }

    public Matrix3f setScale(Vec2f vec2) {
        return this.setScale(vec2.x, vec2.y);
    }

    public Matrix3f setScale(Vec2d vec2) {
        return this.setScale(vec2.x, vec2.y);
    }

    public Matrix3f setScale(Vec2i vec2) {
        return this.setScale(vec2.x, vec2.y);
    }


    /* Scale */

    public Matrix3f scale(float x, float y) {
        val[m00] *= x;
        val[m01] *= x;
        val[m02] *= x;

        val[m10] *= y;
        val[m11] *= y;
        val[m12] *= y;
        return this;
    }

    public Matrix3f scale(double x, double y) {
        return this.scale((float) x, (float) y);
    }

    public Matrix3f scale(float scale) {
        return this.scale(scale, scale);
    }

    public Matrix3f scale(double scale) {
        return this.scale((float) scale);
    }

    public Matrix3f scale(Vec2f vec2) {
        return this.scale(vec2.x, vec2.y);
    }

    public Matrix3f scale(Vec2d vec2) {
        return this.scale(vec2.x, vec2.y);
    }

    public Matrix3f scale(Vec2i vec2) {
        return this.scale(vec2.x, vec2.y);
    }


    /* Set Rotation */

    public Matrix3f setRotationRad(double radians) {
        this.identity();
        final float cos = Mathc.cos(radians);
        final float sin = Mathc.sin(radians);

        val[m00] = cos;
        val[m10] = -sin;
        val[m01] = sin;
        val[m11] = cos;
        return this;
    }

    public Matrix3f setRotation(double degrees) {
        return this.setRotationRad(degrees * Maths.TO_RAD);
    }


    /* Shear */

    public Matrix3f shear(double degreesX, double degreesY) {
        final float M10 = Maths.tanDeg(degreesX);
        final float M01 = Maths.tanDeg(degreesY);

        final float oldM10 = val[m10];
        final float oldM11 = val[m11];
        final float oldM12 = val[m12];

        val[m10] += val[m00] * M10;
        val[m11] += val[m01] * M10;
        val[m12] += val[m02] * M10;
        val[m00] += oldM10 * M01;
        val[m01] += oldM11 * M01;
        val[m02] += oldM12 * M01;
        return this;
    }

    public Matrix3f shear(Vec2f vec2) {
        return this.shear(vec2.x, vec2.y);
    }


    /* Set Shear */

    public Matrix3f setShear(double degreesX, double degreesY) {
        this.identity();
        val[m10] = Maths.tanDeg(degreesX);
        val[m01] = Maths.tanDeg(degreesY);
        return this;
    }

    public Matrix3f setShear(Vec2f vec2) {
        return this.setShear(vec2.x, vec2.y);
    }


    /* Culling */

    public Matrix3f cullPosition() {
        val[m20] = 0F; // X
        val[m21] = 0F; // Y
        return this;
    }

    public Matrix3f cullRotation() {
        val[m00] = 1F;
        val[m10] = 0F;
        val[m01] = 0F;
        val[m11] = 1F;
        return this;
    }


    /* Linear Interpolation */

    public Matrix3f lerp(Matrix3f matrix, float t) {
        final float inv_t = (1F - t);
        for(int i = 0; i < 9; i++)
            val[i] = (val[i] * inv_t + matrix.val[i] * t);
        return this;
    }


    /* Copy */

    public Matrix3f copy() {
        return new Matrix3f(this);
    }


    /* Multiply */

    public float[] getMul(float[] values) {
        return mul(this.val, values);
    }

    public float[] getMul(Matrix3f matrix) {
        return mul(this, matrix);
    }

    public Matrix3f mul(Matrix3f matrix) {
        return this.set(mul(this, matrix));
    }

    public Matrix3f mul(float[] values) {
        return this.set(mul(this.val, values));
    }


    public static float[] mul(float[] a, float[] b) {
        return new float[] {
            a[m00] * b[m00] + a[m10] * b[m01] + a[m20] * b[m02],
            a[m01] * b[m00] + a[m11] * b[m01] + a[m21] * b[m02],
            a[m02] * b[m00] + a[m12] * b[m01] + a[m22] * b[m02],
            a[m00] * b[m10] + a[m10] * b[m11] + a[m20] * b[m12],
            a[m01] * b[m10] + a[m11] * b[m11] + a[m21] * b[m12],
            a[m02] * b[m10] + a[m12] * b[m11] + a[m22] * b[m12],
            a[m00] * b[m20] + a[m10] * b[m21] + a[m20] * b[m22],
            a[m01] * b[m20] + a[m11] * b[m21] + a[m21] * b[m22],
            a[m02] * b[m20] + a[m12] * b[m21] + a[m22] * b[m22],
        };
    }

    public static float[] mul(Matrix3f a, Matrix3f b) {
        return mul(a.val, b.val);
    }


    /* Override */

    @Override
    public String toString() {
        return Arrays.toString(val);
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;

        final Matrix3f matrix = (Matrix3f) object;
        return Arrays.compare(val, matrix.val) == 0;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(val);
    }

}
