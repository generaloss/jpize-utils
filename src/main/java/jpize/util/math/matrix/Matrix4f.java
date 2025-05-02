package jpize.util.math.matrix;

import jpize.util.math.Mathc;
import jpize.util.math.Maths;
import jpize.util.math.EulerAngles;
import jpize.util.math.Quaternion;
import jpize.util.math.vector.*;

import java.util.Arrays;

public class Matrix4f {

    private static final int SIZE = (4 * 4);

    public static final int M00 = 0, M10 = 4, M20 = 8,  M30 = 12;
    public static final int M01 = 1, M11 = 5, M21 = 9,  M31 = 13;
    public static final int M02 = 2, M12 = 6, M22 = 10, M32 = 14;
    public static final int M03 = 3, M13 = 7, M23 = 11, M33 = 15;

    private static final Vec3f UP = new Vec3f(0F, 1F, 0F);
    private final Vec3f tmp_vec_x = new Vec3f();
    private final Vec3f tmp_vec_y = new Vec3f();


    public final float[] values;

    public Matrix4f() {
        this.values = new float[SIZE];
        this.values[M00] = 1F;
        this.values[M11] = 1F;
        this.values[M22] = 1F;
        this.values[M33] = 1F;
    }

    public Matrix4f(float... values) {
        this.values = new float[SIZE];
        this.set(values);
    }

    public Matrix4f(Matrix4f matrix) {
        this(matrix.values);
    }


    public Matrix4f set(float... values) {
        System.arraycopy(values, 0, this.values, 0, SIZE);
        return this;
    }

    public Matrix4f set(Matrix4f matrix) {
        this.set(matrix.values);
        return this;
    }

    public Matrix4f zero() {
        Arrays.fill(values, 0);
        return this;
    }

    public Matrix4f identity() {
        values[M00] = 1F;
        values[M10] = 0F;
        values[M20] = 0F;
        values[M30] = 0F;
        values[M01] = 0F;
        values[M11] = 1F;
        values[M21] = 0F;
        values[M31] = 0F;
        values[M02] = 0F;
        values[M12] = 0F;
        values[M22] = 1F;
        values[M32] = 0F;
        values[M03] = 0F;
        values[M13] = 0F;
        values[M23] = 0F;
        values[M33] = 1F;
        return this;
    }


    /* To Projection */

    public Matrix4f setOrthographic(float left, float right, float bottom, float top, float near, float far) {
        final float iw = 1F / (right - left);
        final float ih = 1F / (top - bottom);
        final float id = 1F / (far - near);

        final float A =  2F * iw;
        final float B =  2F * ih;
        final float C = -2F * id;
        final float D = -(right + left) * iw;
        final float E = -(top + bottom) * ih;
        final float F = -(far + near) * id;

        values[M00] = A;
        values[M10] = 0F;
        values[M20] = 0F;
        values[M30] = D;
        values[M01] = 0F;
        values[M11] = B;
        values[M21] = 0F;
        values[M31] = E;
        values[M02] = 0F;
        values[M12] = 0F;
        values[M22] = C;
        values[M32] = F;
        values[M03] = 0F;
        values[M13] = 0F;
        values[M23] = 0F;
        values[M33] = 1F;
        return this;
    }

    public Matrix4f setOrthographic(float x, float y, float width, float height) {
        final float iw = 1F / width;
        final float ih = 1F / height;

        final float A = 2F * iw;
        final float B = 2F * ih;
        final float C = -(2F * x + width) * iw;
        final float D = -(2F * y + height) * ih;

        values[M00] = A;
        values[M10] = 0F;
        values[M20] = 0F;
        values[M30] = C;
        values[M01] = 0F;
        values[M11] = B;
        values[M21] = 0F;
        values[M31] = D;
        values[M02] = 0F;
        values[M12] = 0F;
        values[M22] = -2F;
        values[M32] = -1F;
        values[M03] = 0F;
        values[M13] = 0F;
        values[M23] = 0F;
        values[M33] = 1F;
        return this;
    }

    public Matrix4f setPerspective(float aspect, float near, float far, double fovY) {
        final float fmn = far - near;

        final float B = 1F / Maths.tanDeg(fovY * 0.5);
        final float A = B / aspect;
        final float C = -(far + near) / fmn;
        final float D = (2F * far * near) / fmn;

        values[M00] = A;
        values[M10] = 0F;
        values[M20] = 0F;
        values[M30] = 0F;
        values[M01] = 0F;
        values[M11] = B;
        values[M21] = 0F;
        values[M31] = 0F;
        values[M02] = 0F;
        values[M12] = 0F;
        values[M22] = C;
        values[M32] = D;
        values[M03] = 0F;
        values[M13] = 0F;
        values[M23] = 1F;
        values[M33] = 0F;
        return this;
    }

    public Matrix4f setPerspective(float width, float height, float near, float far, double fovY) {
        return this.setPerspective(width / height, near, far, fovY);
    }


    /* To Look At (Left-Handled) */

    public Matrix4f setLookAlong(float rightX, float rightY, float rightZ, float upX, float upY, float upZ, float forwardX, float forwardY, float forwardZ) {
        values[M00] = rightX;
        values[M10] = rightY;
        values[M20] = rightZ;
        values[M30] = 0F;
        values[M01] = upX;
        values[M11] = upY;
        values[M21] = upZ;
        values[M31] = 0F;
        values[M02] = forwardX;
        values[M12] = forwardY;
        values[M22] = forwardZ;
        values[M32] = 0F;
        values[M03] = 0F;
        values[M13] = 0F;
        values[M23] = 0F;
        values[M33] = 1F;
        return this;
    }

    public Matrix4f setLookAlong(Vec3f right, Vec3f up, Vec3f forward) {
        return this.setLookAlong(right.x, right.y, right.z, up.x, up.y, up.z, forward.x, forward.y, forward.z);
    }

    public Matrix4f setLookAlong(float posX, float posY, float posZ, Vec3f right, Vec3f up, Vec3f forward) {
        return this.setLookAlong(right, up, forward).translate(posX, posY, posZ);
    }

    public Matrix4f setLookAlong(Vec3f pos, Vec3f right, Vec3f up, Vec3f forward) {
        return this.setLookAlong(pos.x, pos.y, pos.z, right, up, forward);
    }


    public Matrix4f setLookAlong(Vec3f direction) {
        tmp_vec_x.setCrs(UP, direction).nor();
        tmp_vec_y.setCrs(direction, tmp_vec_x).nor();
        return setLookAlong(tmp_vec_x, tmp_vec_y, direction);
    }

    public Matrix4f setLookAlong(float posX, float posY, float posZ, Vec3f direction) {
        return this.setLookAlong(direction).translate(posX, posY, posZ);
    }

    public Matrix4f setLookAlong(Vec3f position, Vec3f direction) {
        return this.setLookAlong(position.x, position.y, position.z, direction);
    }


    public Matrix4f setLookAt(float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        // Normalize direction
        float invDirLength = 1F / (float) Math.sqrt((centerX - eyeX) * (centerX - eyeX) + (centerY - eyeY) * (centerY - eyeY) + (centerZ - eyeZ) * (centerZ - eyeZ));
        // Compute direction from position to lookAt
        final float dirX = (centerX - eyeX) * invDirLength;
        final float dirY = (centerY - eyeY) * invDirLength;
        final float dirZ = (centerZ - eyeZ) * invDirLength;
        // right = direction x up
        float rightX = (dirY * upZ - dirZ * upY);
        float rightY = (dirZ * upX - dirX * upZ);
        float rightZ = (dirX * upY - dirY * upX);
        // normalize right
        final float invRightLength = 1F / (float) Math.sqrt(rightX * rightX + rightY * rightY + rightZ * rightZ);
        rightX *= invRightLength;
        rightY *= invRightLength;
        rightZ *= invRightLength;
        // up = right x direction
        final float upnX = (rightY * dirZ - rightZ * dirY);
        final float upnY = (rightZ * dirX - rightX * dirZ);
        final float upnZ = (rightX * dirY - rightY * dirX);
        values[M00] = rightX;
        values[M10] = upnX;
        values[M20] = -dirX;
        values[M30] = 0F;
        values[M01] = rightY;
        values[M11] = upnY;
        values[M21] = -dirY;
        values[M31] = 0F;
        values[M02] = rightZ;
        values[M12] = upnZ;
        values[M22] = -dirZ;
        values[M32] = 0F;
        values[M03] = -rightX * eyeX - rightY * eyeY - rightZ * eyeZ;
        values[M13] = -upnX * eyeX - upnY * eyeY - upnZ * eyeZ;
        values[M23] = dirX * eyeX + dirY * eyeY + dirZ * eyeZ;
        values[M33] = 1F;
        return this;
    }

    public Matrix4f setLookAt(Vec3f eye, Vec3f center, Vec3f up) {
        return this.setLookAt(eye.x, eye.y, eye.z, center.x, center.y, center.z, up.x, up.y, up.z);
    }


    /* Culling */

    public Matrix4f cullPosition() {
        values[M30] = 0F; // X
        values[M31] = 0F; // Y
        values[M32] = 0F; // Z
        return this;
    }

    public Matrix4f cullRotation() {
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


    /* Set Translated */

    public Matrix4f setTranslatePart(float x, float y, float z) {
        values[M30] = x;
        values[M31] = y;
        values[M32] = z;
        return this;
    }

    public Matrix4f setTranslate(float x, float y, float z) {
        this.identity();
        return this.setTranslatePart(x, y, z);
    }

    public Matrix4f setTranslate(float x, float y) {
        this.identity();
        values[M30] = x;
        values[M31] = y;
        return this;
    }

    public Matrix4f setTranslate(double x, double y, double z) {
        return this.setTranslate((float) x, (float) y, (float) z);
    }

    public Matrix4f setTranslate(double x, double y) {
        return this.setTranslate((float) x, (float) y);
    }

    public Matrix4f setTranslate(Vec2f vec2) {
        return this.setTranslate(vec2.x, vec2.y);
    }

    public Matrix4f setTranslate(Vec2d vec2) {
        return this.setTranslate(vec2.x, vec2.y);
    }

    public Matrix4f setTranslate(Vec2i vec2) {
        return this.setTranslate(vec2.x, vec2.y);
    }

    public Matrix4f setTranslate(Vec3f vec3) {
        return this.setTranslate(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f setTranslate(Vec3d vec3) {
        return this.setTranslate(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f setTranslate(Vec3i vec3) {
        return this.setTranslate(vec3.x, vec3.y, vec3.z);
    }


    /* Translate */

    public Matrix4f translate(float x, float y, float z) {
        values[M30] += values[M00] * x + values[M10] * y + values[M20] * z;
        values[M31] += values[M01] * x + values[M11] * y + values[M21] * z;
        values[M32] += values[M02] * x + values[M12] * y + values[M22] * z;
        values[M33] += values[M03] * x + values[M13] * y + values[M23] * z;
        return this;
    }

    public Matrix4f translate(float x, float y) {
        values[M30] += values[M00] * x + values[M10] * y;
        values[M31] += values[M01] * x + values[M11] * y;
        values[M32] += values[M02] * x + values[M12] * y;
        values[M33] += values[M03] * x + values[M13] * y;
        return this;
    }

    public Matrix4f translate(Vec2f vec2) {
        return this.translate(vec2.x, vec2.y);
    }

    public Matrix4f translate(Vec2d vec2) {
        return this.translate((float) vec2.x, (float) vec2.y);
    }

    public Matrix4f translate(Vec2i vec2) {
        return this.translate(vec2.x, vec2.y);
    }

    public Matrix4f translate(Vec3f vec3) {
        return this.translate(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f translate(Vec3d vec3) {
        return this.translate((float) vec3.x, (float) vec3.y, (float) vec3.z);
    }

    public Matrix4f translate(Vec3i vec3) {
        return this.translate(vec3.x, vec3.y, vec3.z);
    }


    /* Set Scale */

    public Matrix4f setScalePart(float x, float y, float z) {
        values[M00] = x;
        values[M11] = y;
        values[M22] = z;
        return this;
    }

    public Matrix4f setScale(float x, float y, float z) {
        this.identity();
        return this.setScalePart(x, y, z);
    }

    public Matrix4f setScale(float x, float y) {
        this.identity();
        values[M00] = x;
        values[M11] = y;
        return this;
    }

    public Matrix4f setScale(double x, double y, double z) {
        return this.setScale((float) x, (float) y, (float) z);
    }

    public Matrix4f setScale(double x, double y) {
        return this.setScale((float) x, (float) y);
    }

    public Matrix4f setScale(float scale) {
        return this.setScale(scale, scale, scale);
    }

    public Matrix4f setScale(double scale) {
        return this.setScale((float) scale);
    }

    public Matrix4f setScale(Vec3f vec3) {
        return this.setScale(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f setScale(Vec3d vec3) {
        return this.setScale(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f setScale(Vec3i vec3) {
        return this.setScale(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f setScale(Vec2f vec2) {
        return this.setScale(vec2.x, vec2.y);
    }

    public Matrix4f setScale(Vec2d vec2) {
        return this.setScale(vec2.x, vec2.y);
    }

    public Matrix4f setScale(Vec2i vec2) {
        return this.setScale(vec2.x, vec2.y);
    }


    /* Scale */

    public Matrix4f scale(float x, float y, float z) {
        values[M00] *= x;
        values[M01] *= x;
        values[M02] *= x;
        values[M03] *= x;

        values[M10] *= y;
        values[M11] *= y;
        values[M12] *= y;
        values[M13] *= y;

        values[M20] *= z;
        values[M21] *= z;
        values[M22] *= z;
        values[M23] *= z;
        return this;
    }

    public Matrix4f scale(float x, float y) {
        values[M00] *= x;
        values[M01] *= x;
        values[M02] *= x;
        values[M03] *= x;

        values[M10] *= y;
        values[M11] *= y;
        values[M12] *= y;
        values[M13] *= y;
        return this;
    }

    public Matrix4f scale(double x, double y, double z) {
        return this.scale((float) x, (float) y, (float) z);
    }

    public Matrix4f scale(double x, double y) {
        return this.scale((float) x, (float) y);
    }

    public Matrix4f scale(float scale) {
        return this.scale(scale, scale, scale);
    }

    public Matrix4f scale(double scale) {
        return this.scale((float) scale);
    }

    public Matrix4f scale(Vec3f vec3) {
        return this.scale(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f scale(Vec3d vec3) {
        return this.scale(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f scale(Vec3i vec3) {
        return this.scale(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f scale(Vec2f vec2) {
        return this.scale(vec2.x, vec2.y);
    }

    public Matrix4f scale(Vec2d vec2) {
        return this.scale(vec2.x, vec2.y);
    }

    public Matrix4f scale(Vec2i vec2) {
        return this.scale(vec2.x, vec2.y);
    }


    public Vec3f getScale(Vec3f dst) {
        return dst.set(
            Mathc.sqrt(values[M00] * values[M00] + values[M10] * values[M10] + values[M20] * values[M20]),
            Mathc.sqrt(values[M01] * values[M01] + values[M11] * values[M11] + values[M21] * values[M21]),
            Mathc.sqrt(values[M02] * values[M02] + values[M12] * values[M12] + values[M22] * values[M22])
        );
    }


    /* Rotate */

    public Matrix4f rotateRadXYZ(double radiansX, double radiansY, double radiansZ) {
        final float cosX = Mathc.cos(radiansX);
        final float sinX = Mathc.sin(radiansX);
        final float cosY = Mathc.cos(radiansY);
        final float sinY = Mathc.sin(radiansY);
        final float cosZ = Mathc.cos(radiansZ);
        final float sinZ = Mathc.sin(radiansZ);
        final float m_sinX = -sinX;
        final float m_sinY = -sinY;
        final float m_sinZ = -sinZ;
        final float nm01 = m_sinX * m_sinY;
        final float nm02 = cosX * m_sinY;
        values[M02] = sinY;
        values[M12] = m_sinX * cosY;
        values[M22] = cosX * cosY;
        values[M32] = 0F;
        values[M00] = cosY * cosZ;
        values[M10] = nm01 * cosZ + cosX * sinZ;
        values[M20] = nm02 * cosZ + sinX * sinZ;
        values[M30] = 0F;
        values[M01] = cosY * m_sinZ;
        values[M11] = nm01 * m_sinZ + cosX * cosZ;
        values[M21] = nm02 * m_sinZ + sinX * cosZ;
        values[M31] = 0F;
        values[M03] = 0F;
        values[M13] = 0F;
        values[M23] = 0F;
        values[M33] = 1F;
        return this;
    }

    public Matrix4f rotateXYZ(double degreesX, double degreesY, double degreesZ) {
        return this.rotateRadXYZ(degreesX * Maths.TO_RAD, degreesY * Maths.TO_RAD, degreesZ * Maths.TO_RAD);
    }

    public Matrix4f rotateRadZYX(double radiansZ, double radiansY, double radiansX) {
        final float cosZ = Mathc.cos(radiansZ);
        final float sinZ = Mathc.sin(radiansZ);
        final float cosY = Mathc.cos(radiansY);
        final float sinY = Mathc.sin(radiansY);
        final float cosX = Mathc.cos(radiansX);
        final float sinX = Mathc.sin(radiansX);
        final float m_sinZ = -sinZ;
        final float m_sinY = -sinY;
        final float m_sinX = -sinX;
        final float nm20 = cosZ * sinY;
        final float nm21 = sinZ * sinY;
        values[M00] = cosZ * cosY;
        values[M10] = sinZ * cosY;
        values[M20] = m_sinY;
        values[M30] = 0F;
        values[M01] = m_sinZ * cosX + nm20 * sinX;
        values[M11] = cosZ * cosX + nm21 * sinX;
        values[M21] = cosY * sinX;
        values[M31] = 0F;
        values[M02] = m_sinZ * m_sinX + nm20 * cosX;
        values[M12] = cosZ * m_sinX + nm21 * cosX;
        values[M22] = cosY * cosX;
        values[M32] = 0F;
        values[M03] = 0F;
        values[M13] = 0F;
        values[M23] = 0F;
        values[M33] = 1F;
        return this;
    }

    public Matrix4f rotateZYX(double degreesZ, double degreesY, double degreesX) {
        return this.rotateRadZYX(degreesZ * Maths.TO_RAD, degreesY * Maths.TO_RAD, degreesX * Maths.TO_RAD);
    }

    public Matrix4f rotateRadYXZ(double radiansY, double radiansX, double radiansZ) {
        final float cosY = Mathc.cos(radiansY);
        final float sinY = Mathc.sin(radiansY);
        final float cosX = Mathc.cos(radiansX);
        final float sinX = Mathc.sin(radiansX);
        final float cosZ = Mathc.cos(radiansZ);
        final float sinZ = Mathc.sin(radiansZ);
        final float m_sinY = -sinY;
        final float m_sinX = -sinX;
        final float m_sinZ = -sinZ;
        final float nm10 = m_sinY * m_sinX;
        final float nm11 = cosX;
        final float nm12 = cosY * m_sinX;
        values[M20] = m_sinY * cosX;
        values[M21] = sinX;
        values[M22] = cosY * cosX;
        values[M23] = 0F;
        values[M00] = cosY * cosZ + nm10 * m_sinZ;
        values[M01] = nm11 * m_sinZ;
        values[M02] = sinY * cosZ + nm12 * m_sinZ;
        values[M03] = 0F;
        values[M10] = cosY * sinZ + nm10 * cosZ;
        values[M11] = nm11 * cosZ;
        values[M12] = sinY * sinZ + nm12 * cosZ;
        values[M13] = 0F;
        values[M30] = 0F;
        values[M31] = 0F;
        values[M32] = 0F;
        values[M33] = 1F;
        return this;
    }

    public Matrix4f rotateYXZ(double degreesY, double degreesX, double degreesZ) {
        return this.rotateRadYXZ(degreesY * Maths.TO_RAD, degreesX * Maths.TO_RAD, degreesZ * Maths.TO_RAD);
    }


    public Matrix4f rotateRadX(double radians) {
        final float cos = Mathc.cos(radians);
        final float sin = Mathc.sin(radians);
        final float rm11 = cos;
        final float rm12 = sin;
        final float rm21 = -sin;
        final float rm22 = cos;
        final float nm01 = values[M01] * rm11 + values[M02] * rm12;
        final float nm11 = values[M11] * rm11 + values[M12] * rm12;
        final float nm21 = values[M21] * rm11 + values[M22] * rm12;
        final float nm31 = values[M31] * rm11 + values[M32] * rm12;
        values[M02] = values[M01] * rm21 + values[M02] * rm22;
        values[M12] = values[M11] * rm21 + values[M12] * rm22;
        values[M22] = values[M21] * rm21 + values[M22] * rm22;
        values[M32] = values[M31] * rm21 + values[M32] * rm22;
        values[M01] = nm01;
        values[M11] = nm11;
        values[M21] = nm21;
        values[M31] = nm31;
        return this;
    }

    public Matrix4f rotateX(double degrees) {
        return this.rotateRadX(degrees * Maths.TO_RAD);
    }

    public Matrix4f rotateRadY(double radians) {
        final float cos = Mathc.cos(radians);
        final float sin = Mathc.sin(radians);
        final float rm00 = cos;
        final float rm02 = -sin;
        final float rm20 = sin;
        final float rm22 = cos;
        final float nm00 = values[M00] * rm00 + values[M02] * rm02;
        final float nm10 = values[M10] * rm00 + values[M12] * rm02;
        final float nm20 = values[M20] * rm00 + values[M22] * rm02;
        final float nm30 = values[M30] * rm00 + values[M32] * rm02;
        values[M02] = values[M00] * rm20 + values[M02] * rm22;
        values[M12] = values[M10] * rm20 + values[M12] * rm22;
        values[M22] = values[M20] * rm20 + values[M22] * rm22;
        values[M32] = values[M30] * rm20 + values[M32] * rm22;
        values[M00] = nm00;
        values[M10] = nm10;
        values[M20] = nm20;
        values[M30] = nm30;
        return this;
    }

    public Matrix4f rotateY(double degrees) {
        return this.rotateRadY(degrees * Maths.TO_RAD);
    }

    public Matrix4f rotateRadZ(double radians) {
        final float cos = Mathc.cos(radians);
        final float sin = Mathc.sin(radians);
        final float rm00 = cos;
        final float rm01 = sin;
        final float rm10 = -sin;
        final float rm11 = cos;
        final float nm00 = values[M00] * rm00 + values[M01] * rm01;
        final float nm10 = values[M10] * rm00 + values[M11] * rm01;
        final float nm20 = values[M20] * rm00 + values[M21] * rm01;
        final float nm30 = values[M30] * rm00 + values[M31] * rm01;
        values[M01] = values[M00] * rm10 + values[M01] * rm11;
        values[M11] = values[M10] * rm10 + values[M11] * rm11;
        values[M21] = values[M20] * rm10 + values[M21] * rm11;
        values[M31] = values[M30] * rm10 + values[M31] * rm11;
        values[M00] = nm00;
        values[M10] = nm10;
        values[M20] = nm20;
        values[M30] = nm30;
        return this;
    }

    public Matrix4f rotateZ(double degrees) {
        return this.rotateRadZ(degrees * Maths.TO_RAD);
    }

    public Matrix4f rotateQuaternion(float x, float y, float z, float w) {
        final float xx = (x * x);
        final float xy = (x * y);
        final float xz = (x * z);
        final float xw = (x * w);
        final float yy = (y * y);
        final float yz = (y * z);
        final float yw = (y * w);
        final float zz = (z * z);
        final float zw = (z * w);

        final float r00 = 1F - (yy + zz) * 2F;
        final float r01 = (xy - zw) * 2F;
        final float r02 = (xz + yw) * 2F;
        final float r10 = (xy + zw) * 2F;
        final float r11 = 1F - (xx + zz) * 2F;
        final float r12 = (yz - xw) * 2F;
        final float r20 = (xz - yw) * 2F;
        final float r21 = (yz + xw) * 2F;
        final float r22 = 1F - (xx + yy) * 2F;

        final float M00 = values[Matrix4f.M00] * r00 + values[M10] * r10 + values[M20] * r20;
        final float M01 = values[Matrix4f.M00] * r01 + values[M10] * r11 + values[M20] * r21;
        final float M02 = values[Matrix4f.M00] * r02 + values[M10] * r12 + values[M20] * r22;
        final float M10 = values[Matrix4f.M01] * r00 + values[M11] * r10 + values[M21] * r20;
        final float M11 = values[Matrix4f.M01] * r01 + values[Matrix4f.M11] * r11 + values[M21] * r21;
        final float M12 = values[Matrix4f.M01] * r02 + values[Matrix4f.M11] * r12 + values[M21] * r22;
        final float M20 = values[Matrix4f.M02] * r00 + values[Matrix4f.M12] * r10 + values[M22] * r20;
        final float M21 = values[Matrix4f.M02] * r01 + values[Matrix4f.M12] * r11 + values[M22] * r21;
        final float M22 = values[Matrix4f.M02] * r02 + values[Matrix4f.M12] * r12 + values[Matrix4f.M22] * r22;
        final float M30 = values[M03] * r00 + values[M13] * r10 + values[M23] * r20;
        final float M31 = values[M03] * r01 + values[M13] * r11 + values[M23] * r21;
        final float M32 = values[M03] * r02 + values[M13] * r12 + values[M23] * r22;

        values[Matrix4f.M00] = M00;
        values[Matrix4f.M10] = M01;
        values[Matrix4f.M20] = M02;
        values[Matrix4f.M01] = M10;
        values[Matrix4f.M11] = M11;
        values[Matrix4f.M21] = M12;
        values[Matrix4f.M02] = M20;
        values[Matrix4f.M12] = M21;
        values[Matrix4f.M22] = M22;
        values[M03] = M30;
        values[M13] = M31;
        values[M23] = M32;
        return this;
    }

    public Matrix4f rotateQuaternion(Quaternion quaternion) {
        return this.rotateQuaternion(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }

    public Matrix4f rotate(EulerAngles angles) {
        return this.rotateXYZ(angles.yaw, angles.pitch, angles.roll);
    }

    /* Set Rotation */

    public Matrix4f setRotationRad(double radians, float x, float y, float z) {
        final float cos = Mathc.cos(radians);
        final float sin = Mathc.sin(radians);
        final float C = 1F - cos;
        final float xy = x * y;
        final float xz = x * z;
        final float yz = y * z;
        values[M00] = cos + x * x * C;
        values[M01] = xy * C - z * sin;
        values[M02] = xz * C + y * sin;
        values[M03] = 0F;
        values[M10] = xy * C + z * sin;
        values[M11] = cos + y * y * C;
        values[M12] = yz * C - x * sin;
        values[M13] = 0F;
        values[M20] = xz * C - y * sin;
        values[M21] = yz * C + x * sin;
        values[M22] = cos + z * z * C;
        values[M23] = 0F;
        values[M30] = 0F;
        values[M31] = 0F;
        values[M32] = 0F;
        values[M33] = 1F;
        return this;
    }

    public Matrix4f setRotation(double degrees, float x, float y, float z) {
        return this.setRotationRad(degrees * Maths.TO_RAD, x, y, z);
    }

    public Matrix4f setRotationRadX(double radians) {
        this.identity();
        final float cos = Mathc.cos(radians);
        final float sin = Mathc.sin(radians);
        values[M11] = cos;
        values[M21] = sin;
        values[M12] = -sin;
        values[M22] = cos;
        return this;
    }

    public Matrix4f setRotationX(double degrees) {
        return this.setRotationRadX(degrees * Maths.TO_RAD);
    }

    public Matrix4f setRotationRadY(double radians) {
        this.identity();
        final float cos = Mathc.cos(radians);
        final float sin = Mathc.sin(radians);
        values[M00] = cos;
        values[M20] = -sin;
        values[M02] = sin;
        values[M22] = cos;
        return this;
    }

    public Matrix4f setRotationY(double degrees) {
        return this.setRotationRadY(degrees * Maths.TO_RAD);
    }

    public Matrix4f setRotationRadZ(double radians) {
        this.identity();
        final float cos = Mathc.cos(radians);
        final float sin = Mathc.sin(radians);
        values[M00] = cos;
        values[M10] = sin;
        values[M01] = -sin;
        values[M11] = cos;
        return this;
    }

    public Matrix4f setRotationZ(double degrees) {
        return this.setRotationRadZ(degrees * Maths.TO_RAD);
    }

    public Matrix4f setRotationRadXYZ(double radiansX, double radiansY, double radiansZ) {
        final float cosX = Mathc.cos(radiansX);
        final float sinX = Mathc.sin(radiansX);
        final float cosY = Mathc.cos(radiansY);
        final float sinY = Mathc.sin(radiansY);
        final float cosZ = Mathc.cos(radiansZ);
        final float sinZ = Mathc.sin(radiansZ);
        final float m_sinX = -sinX;
        final float m_sinY = -sinY;
        final float m_sinZ = -sinZ;
        // rotate X
        final float nm11 = cosX;
        final float nm12 = sinX;
        final float nm21 = m_sinX;
        final float nm22 = cosX;
        // rotate Y
        final float nm00 = cosY;
        final float nm01 = nm21 * m_sinY;
        final float nm02 = nm22 * m_sinY;
        values[M02] = sinY;
        values[M12] = nm21 * cosY;
        values[M22] = nm22 * cosY;
        // rotate Z
        values[M00] = nm00 * cosZ;
        values[M10] = nm01 * cosZ + nm11 * sinZ;
        values[M20] = nm02 * cosZ + nm12 * sinZ;
        values[M01] = nm00 * m_sinZ;
        values[M11] = nm01 * m_sinZ + nm11 * cosZ;
        values[M21] = nm02 * m_sinZ + nm12 * cosZ;
        return this;
    }

    public Matrix4f setRotationXYZ(double degreesX, double degreesY, double degreesZ) {
        return this.setRotationRadXYZ(degreesX * Maths.TO_RAD, degreesY * Maths.TO_RAD, degreesZ * Maths.TO_RAD);
    }

    public Matrix4f setRotationRadZYX(double radiansZ, double radiansY, double radiansX) {
        final float cosZ = Mathc.cos(radiansZ);
        final float sinZ = Mathc.sin(radiansZ);
        final float cosY = Mathc.cos(radiansY);
        final float sinY = Mathc.sin(radiansY);
        final float cosX = Mathc.cos(radiansX);
        final float sinX = Mathc.sin(radiansX);
        final float m_sinZ = -sinZ;
        final float m_sinY = -sinY;
        final float m_sinX = -sinX;
        // rotate Z
        final float nm00 = cosZ;
        final float nm01 = sinZ;
        final float nm10 = m_sinZ;
        final float nm11 = cosZ;
        // rotate Y
        final float nm20 = nm00 * sinY;
        final float nm21 = nm01 * sinY;
        final float nm22 = cosY;
        values[M00] = nm00 * cosY;
        values[M10] = nm01 * cosY;
        values[M20] = m_sinY;
        // rotate X
        values[M01] = nm10 * cosX + nm20 * sinX;
        values[M11] = nm11 * cosX + nm21 * sinX;
        values[M21] = nm22 * sinX;
        values[M02] = nm10 * m_sinX + nm20 * cosX;
        values[M12] = nm11 * m_sinX + nm21 * cosX;
        values[M22] = nm22 * cosX;
        return this;
    }

    public Matrix4f setRotationZYX(double degreesZ, double degreesY, double degreesX) {
        return this.setRotationRadZYX(degreesZ * Maths.TO_RAD, degreesY * Maths.TO_RAD, degreesX * Maths.TO_RAD);
    }

    public Matrix4f setRotationRadYXZ(double radiansY, double radiansX, double radiansZ) {
        final float cosY = Mathc.cos(radiansY);
        final float sinY = Mathc.sin(radiansY);
        final float cosX = Mathc.cos(radiansX);
        final float sinX = Mathc.sin(radiansX);
        final float cosZ = Mathc.cos(radiansZ);
        final float sinZ = Mathc.sin(radiansZ);
        final float m_sinY = -sinY;
        final float m_sinX = -sinX;
        final float m_sinZ = -sinZ;
        // rotate Y
        final float nm00 = cosY;
        final float nm02 = m_sinY;
        final float nm20 = sinY;
        final float nm22 = cosY;
        // rotate X
        final float nm10 = nm20 * sinX;
        final float nm11 = cosX;
        final float nm12 = nm22 * sinX;
        values[M02] = nm20 * cosX;
        values[M12] = m_sinX;
        values[M22] = nm22 * cosX;
        // rotate Z
        values[M00] = nm00 * cosZ + nm10 * sinZ;
        values[M10] = nm11 * sinZ;
        values[M20] = nm02 * cosZ + nm12 * sinZ;
        values[M01] = nm00 * m_sinZ + nm10 * cosZ;
        values[M11] = nm11 * cosZ;
        values[M21] = nm02 * m_sinZ + nm12 * cosZ;
        return this;
    }

    public Matrix4f setRotationYXZ(double degreesY, double degreesX, double degreesZ) {
        return this.setRotationRadYXZ(degreesY * Maths.TO_RAD, degreesX * Maths.TO_RAD, degreesZ * Maths.TO_RAD);
    }

    public Matrix4f setRotation(EulerAngles angles) {
        return this.setRotationXYZ(angles.yaw, angles.pitch, angles.roll);
    }


    /* Quaternion */

    public Matrix4f setQuaternion(float w, float x, float y, float z) {
        final float xx = (2F * x * x);
        final float yy = (2F * y * y);
        final float zz = (2F * z * z);

        final float xy = (2F * x * y);
        final float zw = (2F * z * w);

        final float xz = (2F * x * z);
        final float yw = (2F * y * w);

        final float yz = (2F * y * z);
        final float xw = (2F * x * w);

        values[M00] = (1F - yy - zz);
        values[M11] = (1F - zz - xx);
        values[M22] = (1F - xx - yy);

        values[M10] = (xy + zw);
        values[M01] = (xy - zw);

        values[M20] = (xz - yw);
        values[M02] = (xz + yw);

        values[M21] = (yz + xw);
        values[M12] = (yz - xw);

        values[M03] = 0F;
        values[M13] = 0F;
        values[M23] = 0F;
        values[M33] = 1F;
        values[M30] = 0F;
        values[M31] = 0F;
        values[M32] = 0F;
        return this;
    }

    public Matrix4f setQuaternion(Quaternion quaternion) {
        return this.setQuaternion(quaternion.w, quaternion.x, quaternion.y, quaternion.z);
    }


    public Vec3f getPositiveZ(Vec3f dst) {
        return dst.set(
            (values[M01] * values[M12] - values[M11] * values[M02]),
            (values[M02] * values[M10] - values[M12] * values[M00]),
            (values[M00] * values[M11] - values[M10] * values[M01])
        ).nor();
    }

    public Vec3f getPositiveX(Vec3f dst) {
        return dst.set(
            (values[M11] * values[M22] - values[M21] * values[M12]),
            (values[M20] * values[M12] - values[M10] * values[M22]),
            (values[M10] * values[M21] - values[M20] * values[M11])
        ).nor();
    }

    public Vec3f getPositiveY(Vec3f dst) {
        return dst.set(
            (values[M21] * values[M02] - values[M01] * values[M22]),
            (values[M00] * values[M22] - values[M20] * values[M02]),
            (values[M20] * values[M01] - values[M00] * values[M21])
        ).nor();
    }


    /* Linear Interpolation */

    public Matrix4f lerp(Matrix4f matrix, float t) {
        final float k = (1F - t);
        values[M00]  =  values[M00] * k  +  matrix.values[M00] * t;
        values[M01]  =  values[M01] * k  +  matrix.values[M01] * t;
        values[M02]  =  values[M02] * k  +  matrix.values[M02] * t;
        values[M03]  =  values[M03] * k  +  matrix.values[M03] * t;
        values[M10]  =  values[M10] * k  +  matrix.values[M10] * t;
        values[M11]  =  values[M11] * k  +  matrix.values[M11] * t;
        values[M12]  =  values[M12] * k  +  matrix.values[M12] * t;
        values[M13]  =  values[M13] * k  +  matrix.values[M13] * t;
        values[M20]  =  values[M20] * k  +  matrix.values[M20] * t;
        values[M21]  =  values[M21] * k  +  matrix.values[M21] * t;
        values[M22]  =  values[M22] * k  +  matrix.values[M22] * t;
        values[M23]  =  values[M23] * k  +  matrix.values[M23] * t;
        values[M30]  =  values[M30] * k  +  matrix.values[M30] * t;
        values[M31]  =  values[M31] * k  +  matrix.values[M31] * t;
        values[M32]  =  values[M32] * k  +  matrix.values[M32] * t;
        values[M33]  =  values[M33] * k  +  matrix.values[M33] * t;
        return this;
    }


    /* Copy */

    public Matrix4f copy() {
        return new Matrix4f(this);
    }


    /* Multiply */

    public static void mulBuf(float[] dst, float[] a, float[] b) {
        dst[M00]  =  a[M00] * b[M00]  +  a[M10] * b[M01]  +  a[M20] * b[M02]  +  a[M30] * b[M03];
        dst[M01]  =  a[M01] * b[M00]  +  a[M11] * b[M01]  +  a[M21] * b[M02]  +  a[M31] * b[M03];
        dst[M02]  =  a[M02] * b[M00]  +  a[M12] * b[M01]  +  a[M22] * b[M02]  +  a[M32] * b[M03];
        dst[M03]  =  a[M03] * b[M00]  +  a[M13] * b[M01]  +  a[M23] * b[M02]  +  a[M33] * b[M03];

        dst[M10]  =  a[M00] * b[M10]  +  a[M10] * b[M11]  +  a[M20] * b[M12]  +  a[M30] * b[M13];
        dst[M11]  =  a[M01] * b[M10]  +  a[M11] * b[M11]  +  a[M21] * b[M12]  +  a[M31] * b[M13];
        dst[M12]  =  a[M02] * b[M10]  +  a[M12] * b[M11]  +  a[M22] * b[M12]  +  a[M32] * b[M13];
        dst[M13]  =  a[M03] * b[M10]  +  a[M13] * b[M11]  +  a[M23] * b[M12]  +  a[M33] * b[M13];

        dst[M20]  =  a[M00] * b[M20]  +  a[M10] * b[M21]  +  a[M20] * b[M22]  +  a[M30] * b[M23];
        dst[M21]  =  a[M01] * b[M20]  +  a[M11] * b[M21]  +  a[M21] * b[M22]  +  a[M31] * b[M23];
        dst[M22]  =  a[M02] * b[M20]  +  a[M12] * b[M21]  +  a[M22] * b[M22]  +  a[M32] * b[M23];
        dst[M23]  =  a[M03] * b[M20]  +  a[M13] * b[M21]  +  a[M23] * b[M22]  +  a[M33] * b[M23];

        dst[M30]  =  a[M00] * b[M30]  +  a[M10] * b[M31]  +  a[M20] * b[M32]  +  a[M30] * b[M33];
        dst[M31]  =  a[M01] * b[M30]  +  a[M11] * b[M31]  +  a[M21] * b[M32]  +  a[M31] * b[M33];
        dst[M32]  =  a[M02] * b[M30]  +  a[M12] * b[M31]  +  a[M22] * b[M32]  +  a[M32] * b[M33];
        dst[M33]  =  a[M03] * b[M30]  +  a[M13] * b[M31]  +  a[M23] * b[M32]  +  a[M33] * b[M33];
    }

    public static void mulBuf(Matrix4f dst, Matrix4f a, Matrix4f b) {
        mulBuf(dst.values, a.values, b.values);
    }

    public static float[] mulToNew(float[] a, float[] b) {
        final float[] dst = new float[SIZE];
        mulBuf(dst, a, b);
        return dst;
    }

    public static Matrix4f mulToNew(Matrix4f a, Matrix4f b) {
        final Matrix4f dst = new Matrix4f();
        mulBuf(dst, a, b);
        return dst;
    }

    public static void mulToA(float[] a, float[] b) {
        final float[] aCopy = new float[SIZE];
        System.arraycopy(a, 0, aCopy, 0, SIZE);
        mulBuf(a, aCopy, b);
    }

    public static void mulToA(Matrix4f a, Matrix4f b) {
        final float[] aCopy = new float[SIZE];
        System.arraycopy(a.values, 0, aCopy, 0, SIZE);
        mulBuf(a.values, aCopy, b.values);
    }


    public Matrix4f mul(Matrix4f dst, Matrix4f b) {
        mulBuf(dst, this, b);
        return this;
    }

    public Matrix4f mul(float[] dst, float[] b) {
        mulBuf(dst, this.values, b);
        return this;
    }

    public Matrix4f mul(Matrix4f b) {
        mulToA(this, b);
        return this;
    }

    public Matrix4f mul(float[] b) {
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

        final Matrix4f matrix = (Matrix4f) object;
        return (Arrays.compare(values, matrix.values) == 0);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

}