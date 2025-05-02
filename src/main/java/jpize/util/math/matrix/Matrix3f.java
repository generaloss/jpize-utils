package jpize.util.math.matrix;

import jpize.util.math.Mathc;
import jpize.util.math.Maths;
import jpize.util.math.vector.*;

import java.util.Arrays;

public class Matrix3f {

    private static final int SIZE = (3 * 3);

    public static final int M00 = 0, M10 = 3, M20 = 6;
    public static final int M01 = 1, M11 = 4, M21 = 7;
    public static final int M02 = 2, M12 = 5, M22 = 8;


    public final float[] values;

    public Matrix3f() {
        this.values = new float[SIZE];
        this.values[M00] = 1F;
        this.values[M11] = 1F;
        this.values[M22] = 1F;
    }

    public Matrix3f(float... values) {
        this.values = new float[SIZE];
        this.set(values);
    }

    public Matrix3f(Matrix3f matrix) {
        this(matrix.values);
    }


    public Matrix3f set(float... values) {
        System.arraycopy(values, 0, this.values, 0, SIZE);
        return this;
    }

    public Matrix3f set(Matrix3f matrix) {
        this.set(matrix.values);
        return this;
    }

    public Matrix3f zero() {
        Arrays.fill(values, 0F);
        return this;
    }

    public Matrix3f identity() {
        values[M00] = 1F;
        values[M10] = 0F;
        values[M20] = 0F;
        values[M01] = 0F;
        values[M11] = 1F;
        values[M21] = 0F;
        values[M02] = 0F;
        values[M12] = 0F;
        values[M22] = 1F;
        return this;
    }


    /* Set Translate */

    public Matrix3f setTranslatePart(float x, float y) {
        values[M20] = x;
        values[M21] = y;
        return this;
    }

    public Matrix3f setTranslate(float x, float y) {
        this.identity();
        return this.setTranslatePart(x, y);
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


    /* Translate */

    public Matrix3f translate(float x, float y) {
        values[M20] += (values[M00] * x + values[M10] * y);
        values[M21] += (values[M01] * x + values[M11] * y);
        values[M22] += (values[M02] * x + values[M12] * y);
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


    /* Set Scale */

    public Matrix3f setScalePart(float x, float y) {
        values[M00] = x;
        values[M11] = y;
        return this;
    }

    public Matrix3f setScale(float x, float y) {
        this.identity();
        return this.setScalePart(x, y);
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
        values[M00] *= x;
        values[M01] *= x;
        values[M02] *= x;

        values[M10] *= y;
        values[M11] *= y;
        values[M12] *= y;
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


    public Vec2f getScale(Vec2f dst) {
        return dst.set(
            Mathc.sqrt(values[M00] * values[M00] + values[M10] * values[M10]),
            Mathc.sqrt(values[M01] * values[M01] + values[M11] * values[M11])
        );
    }


    /* Set Rotation */

    public Matrix3f setRotationRad(double radians) {
        this.identity();
        final float cos = Mathc.cos(radians);
        final float sin = Mathc.sin(radians);

        values[M00] = cos;
        values[M10] = -sin;
        values[M01] = sin;
        values[M11] = cos;
        return this;
    }

    public Matrix3f setRotation(double degrees) {
        return this.setRotationRad(degrees * Maths.TO_RAD);
    }


    /* Shear */

    public Matrix3f shear(double degreesX, double degreesY) {
        final float M10 = Maths.tanDeg(degreesX);
        final float M01 = Maths.tanDeg(degreesY);

        final float oldM10 = values[Matrix3f.M10];
        final float oldM11 = values[M11];
        final float oldM12 = values[M12];

        values[Matrix3f.M10] += values[M00] * M10;
        values[M11] += values[Matrix3f.M01] * M10;
        values[M12] += values[M02] * M10;
        values[M00] += oldM10 * M01;
        values[Matrix3f.M01] += oldM11 * M01;
        values[M02] += oldM12 * M01;
        return this;
    }

    public Matrix3f shear(Vec2f vec2) {
        return this.shear(vec2.x, vec2.y);
    }


    /* Set Shear */

    public Matrix3f setShear(double degreesX, double degreesY) {
        this.identity();
        values[M10] = Maths.tanDeg(degreesX);
        values[M01] = Maths.tanDeg(degreesY);
        return this;
    }

    public Matrix3f setShear(Vec2f vec2) {
        return this.setShear(vec2.x, vec2.y);
    }


    /* Culling */

    public Matrix3f cullPosition() {
        values[M20] = 0F; // X
        values[M21] = 0F; // Y
        return this;
    }

    public Matrix3f cullRotation() {
        values[M00] = 1F;
        values[M10] = 0F;
        values[M01] = 0F;
        values[M11] = 1F;
        return this;
    }


    /* Linear Interpolation */

    public Matrix3f lerp(Matrix3f matrix, float t) {
        final float inv_t = (1F - t);
        for(int i = 0; i < SIZE; i++)
            values[i] = (values[i] * inv_t + matrix.values[i] * t);
        return this;
    }


    /* Copy */

    public Matrix3f copy() {
        return new Matrix3f(this);
    }


    /* Multiply */

    public static void mulBuf(float[] dst, float[] a, float[] b) {
        dst[M00]  =  a[M00] * b[M00]  +  a[M10] * b[M01]  +  a[M20] * b[M02];
        dst[M01]  =  a[M01] * b[M00]  +  a[M11] * b[M01]  +  a[M21] * b[M02];
        dst[M02]  =  a[M02] * b[M00]  +  a[M12] * b[M01]  +  a[M22] * b[M02];

        dst[M10]  =  a[M00] * b[M10]  +  a[M10] * b[M11]  +  a[M20] * b[M12];
        dst[M11]  =  a[M01] * b[M10]  +  a[M11] * b[M11]  +  a[M21] * b[M12];
        dst[M12]  =  a[M02] * b[M10]  +  a[M12] * b[M11]  +  a[M22] * b[M12];

        dst[M20]  =  a[M00] * b[M20]  +  a[M10] * b[M21]  +  a[M20] * b[M22];
        dst[M21]  =  a[M01] * b[M20]  +  a[M11] * b[M21]  +  a[M21] * b[M22];
        dst[M22]  =  a[M02] * b[M20]  +  a[M12] * b[M21]  +  a[M22] * b[M22];
    }

    public static void mulBuf(Matrix3f dst, Matrix3f a, Matrix3f b) {
        mulBuf(dst.values, a.values, b.values);
    }

    public static float[] mulToNew(float[] a, float[] b) {
        final float[] dst = new float[SIZE];
        mulBuf(dst, a, b);
        return dst;
    }

    public static Matrix3f mulToNew(Matrix3f a, Matrix3f b) {
        final Matrix3f dst = new Matrix3f();
        mulBuf(dst, a, b);
        return dst;
    }

    public static void mulToA(float[] a, float[] b) {
        final float[] aCopy = new float[SIZE];
        System.arraycopy(a, 0, aCopy, 0, SIZE);
        mulBuf(a, aCopy, b);
    }

    public static void mulToA(Matrix3f a, Matrix3f b) {
        final float[] aCopy = new float[SIZE];
        System.arraycopy(a.values, 0, aCopy, 0, SIZE);
        mulBuf(a.values, aCopy, b.values);
    }


    public Matrix3f mul(Matrix3f dst, Matrix3f b) {
        mulBuf(dst, this, b);
        return this;
    }

    public Matrix3f mul(float[] dst, float[] b) {
        mulBuf(dst, this.values, b);
        return this;
    }

    public Matrix3f mul(Matrix3f b) {
        mulToA(this, b);
        return this;
    }

    public Matrix3f mul(float[] b) {
        mulToA(this.values, b);
        return this;
    }


    /* Override */

    @Override
    public String toString() {
        return Arrays.toString(values);
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;

        final Matrix3f matrix = (Matrix3f) object;
        return (Arrays.compare(values, matrix.values) == 0);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

}
