package jpize.util.math.matrix;

import jpize.util.math.Mathc;
import jpize.util.math.Maths;
import jpize.util.math.EulerAngles;
import jpize.util.math.Quaternion;
import jpize.util.math.vector.*;

import java.util.Arrays;

public class Matrix4f implements Matrix4 {

    private static final Vec3f UP = new Vec3f(0, 1, 0);
    private final Vec3f tmp_vec_x = new Vec3f();
    private final Vec3f tmp_vec_y = new Vec3f();


    public final float[] val;

    public Matrix4f() {
        this.val = new float[16];
        this.val[m00] = 1F;
        this.val[m11] = 1F;
        this.val[m22] = 1F;
        this.val[m33] = 1F;
    }

    public Matrix4f(float... values) {
        this.val = new float[16];
        this.set(values);
    }

    public Matrix4f(Matrix4f matrix) {
        this(matrix.val);
    }


    public Matrix4f set(float... values) {
        System.arraycopy(values, 0, val, 0, values.length);
        return this;
    }

    public Matrix4f set(Matrix4f matrix) {
        this.set(matrix.val);
        return this;
    }

    public Matrix4f zero() {
        Arrays.fill(val, 0);
        return this;
    }

    public Matrix4f identity() {
        val[m00] = 1F;
        val[m10] = 0F;
        val[m20] = 0F;
        val[m30] = 0F;
        val[m01] = 0F;
        val[m11] = 1F;
        val[m21] = 0F;
        val[m31] = 0F;
        val[m02] = 0F;
        val[m12] = 0F;
        val[m22] = 1F;
        val[m32] = 0F;
        val[m03] = 0F;
        val[m13] = 0F;
        val[m23] = 0F;
        val[m33] = 1F;
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

        val[m00] = A;
        val[m10] = 0F;
        val[m20] = 0F;
        val[m30] = D;
        val[m01] = 0F;
        val[m11] = B;
        val[m21] = 0F;
        val[m31] = E;
        val[m02] = 0F;
        val[m12] = 0F;
        val[m22] = C;
        val[m32] = F;
        val[m03] = 0F;
        val[m13] = 0F;
        val[m23] = 0F;
        val[m33] = 1F;
        return this;
    }

    public Matrix4f setOrthographic(float x, float y, float width, float height) {
        final float iw = 1F / width;
        final float ih = 1F / height;

        final float A = 2F * iw;
        final float B = 2F * ih;
        final float C = -(2F * x + width) * iw;
        final float D = -(2F * y + height) * ih;

        val[m00] = A;
        val[m10] = 0F;
        val[m20] = 0F;
        val[m30] = C;
        val[m01] = 0F;
        val[m11] = B;
        val[m21] = 0F;
        val[m31] = D;
        val[m02] = 0F;
        val[m12] = 0F;
        val[m22] = -2F;
        val[m32] = -1F;
        val[m03] = 0F;
        val[m13] = 0F;
        val[m23] = 0F;
        val[m33] = 1F;
        return this;
    }

    public Matrix4f setPerspective(float aspect, float near, float far, double fovY) {
        final float fmn = far - near;

        final float B = 1F / Maths.tanDeg(fovY * 0.5);
        final float A = B / aspect;
        final float C = -(far + near) / fmn;
        final float D = (2F * far * near) / fmn;

        val[m00] = A;
        val[m10] = 0F;
        val[m20] = 0F;
        val[m30] = 0F;
        val[m01] = 0F;
        val[m11] = B;
        val[m21] = 0F;
        val[m31] = 0F;
        val[m02] = 0F;
        val[m12] = 0F;
        val[m22] = C;
        val[m32] = D;
        val[m03] = 0F;
        val[m13] = 0F;
        val[m23] = 1F;
        val[m33] = 0F;
        return this;
    }

    public Matrix4f setPerspective(float width, float height, float near, float far, double fovY) {
        return this.setPerspective(width / height, near, far, fovY);
    }


    /* To Look At (Left-Handled) */

    public Matrix4f setLookAlong(float rightX, float rightY, float rightZ, float upX, float upY, float upZ, float forwardX, float forwardY, float forwardZ) {
        val[m00] = rightX;
        val[m10] = rightY;
        val[m20] = rightZ;
        val[m30] = 0F;
        val[m01] = upX;
        val[m11] = upY;
        val[m21] = upZ;
        val[m31] = 0F;
        val[m02] = forwardX;
        val[m12] = forwardY;
        val[m22] = forwardZ;
        val[m32] = 0F;
        val[m03] = 0F;
        val[m13] = 0F;
        val[m23] = 0F;
        val[m33] = 1F;
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
        val[m00] = rightX;
        val[m10] = upnX;
        val[m20] = -dirX;
        val[m30] = 0F;
        val[m01] = rightY;
        val[m11] = upnY;
        val[m21] = -dirY;
        val[m31] = 0F;
        val[m02] = rightZ;
        val[m12] = upnZ;
        val[m22] = -dirZ;
        val[m32] = 0F;
        val[m03] = -rightX * eyeX - rightY * eyeY - rightZ * eyeZ;
        val[m13] = -upnX * eyeX - upnY * eyeY - upnZ * eyeZ;
        val[m23] = dirX * eyeX + dirY * eyeY + dirZ * eyeZ;
        val[m33] = 1F;
        return this;
    }

    public Matrix4f setLookAt(Vec3f eye, Vec3f center, Vec3f up) {
        return this.setLookAt(eye.x, eye.y, eye.z, center.x, center.y, center.z, up.x, up.y, up.z);
    }


    /* Culling */

    public Matrix4f cullPosition() {
        val[m30] = 0F; // X
        val[m31] = 0F; // Y
        val[m32] = 0F; // Z
        return this;
    }

    public Matrix4f cullRotation() {
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

    public Matrix4f translate(float x, float y, float z) {
        val[m30] += val[m00] * x + val[m10] * y + val[m20] * z;
        val[m31] += val[m01] * x + val[m11] * y + val[m21] * z;
        val[m32] += val[m02] * x + val[m12] * y + val[m22] * z;
        val[m33] += val[m03] * x + val[m13] * y + val[m23] * z;
        return this;
    }

    public Matrix4f translate(float x, float y) {
        val[m30] += val[m00] * x + val[m10] * y;
        val[m31] += val[m01] * x + val[m11] * y;
        val[m32] += val[m02] * x + val[m12] * y;
        val[m33] += val[m03] * x + val[m13] * y;
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


    /* Set Translated */

    public Matrix4f setTranslate(float x, float y, float z) {
        this.identity();
        val[m30] = x;
        val[m31] = y;
        val[m32] = z;
        return this;
    }

    public Matrix4f setTranslate(float x, float y) {
        this.identity();
        val[m30] = x;
        val[m31] = y;
        return this;
    }

    public Matrix4f setTranslate(Vec2f vec2) {
        return this.setTranslate(vec2.x, vec2.y);
    }

    public Matrix4f setTranslate(Vec2d vec2) {
        return this.setTranslate((float) vec2.x, (float) vec2.y);
    }

    public Matrix4f setTranslate(Vec2i vec2) {
        return this.setTranslate(vec2.x, vec2.y);
    }

    public Matrix4f setTranslate(Vec3f vec3) {
        return this.setTranslate(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f setTranslate(Vec3d vec3) {
        return this.setTranslate((float) vec3.x, (float) vec3.y, (float) vec3.z);
    }

    public Matrix4f setTranslate(Vec3i vec3) {
        return this.setTranslate(vec3.x, vec3.y, vec3.z);
    }


    /* Scale */

    public Matrix4f scale(float x, float y, float z) {
        val[m00] *= x;
        val[m01] *= x;
        val[m02] *= x;
        val[m03] *= x;

        val[m10] *= y;
        val[m11] *= y;
        val[m12] *= y;
        val[m13] *= y;

        val[m20] *= z;
        val[m21] *= z;
        val[m22] *= z;
        val[m23] *= z;
        return this;
    }

    public Matrix4f scale(float x, float y) {
        val[m00] *= x;
        val[m01] *= x;
        val[m02] *= x;
        val[m03] *= x;

        val[m10] *= y;
        val[m11] *= y;
        val[m12] *= y;
        val[m13] *= y;
        return this;
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
        return this.scale((float) vec3.x, (float) vec3.y, (float) vec3.z);
    }

    public Matrix4f scale(Vec3i vec3) {
        return this.scale(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f scale(Vec2f vec2) {
        return this.scale(vec2.x, vec2.y);
    }

    public Matrix4f scale(Vec2d vec2) {
        return this.scale((float) vec2.x, (float) vec2.y);
    }

    public Matrix4f scale(Vec2i vec2) {
        return this.scale(vec2.x, vec2.y);
    }


    /* Set Scale */

    public Matrix4f setScale(float x, float y, float z) {
        this.identity();
        val[m00] = x;
        val[m11] = y;
        val[m22] = z;
        return this;
    }

    public Matrix4f setScale(float x, float y) {
        this.identity();
        val[m00] = x;
        val[m11] = y;
        return this;
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
        return this.setScale((float) vec3.x, (float) vec3.y, (float) vec3.z);
    }

    public Matrix4f setScale(Vec3i vec3) {
        return this.setScale(vec3.x, vec3.y, vec3.z);
    }

    public Matrix4f setScale(Vec2f vec2) {
        return this.setScale(vec2.x, vec2.y);
    }

    public Matrix4f setScale(Vec2d vec2) {
        return this.setScale((float) vec2.x, (float) vec2.y);
    }

    public Matrix4f setScale(Vec2i vec2) {
        return this.setScale(vec2.x, vec2.y);
    }


    public Vec3f getScale(Vec3f dst) {
        return dst.set(
            Mathc.sqrt(val[m00] * val[m00] + val[m10] * val[m10] + val[m20] * val[m20]),
            Mathc.sqrt(val[m01] * val[m01] + val[m11] * val[m11] + val[m21] * val[m21]),
            Mathc.sqrt(val[m02] * val[m02] + val[m12] * val[m12] + val[m22] * val[m22])
        );
    }


    /* Rotate */

    public Matrix4f rotate(EulerAngles angles) {
        return this.rotateXYZ(angles.yaw, angles.pitch, angles.roll);
    }

    public Matrix4f rotate(Quaternion rotation) {
        final float x = rotation.x;
        final float y = rotation.y;
        final float z = rotation.z;
        final float w = rotation.w;

        final float xx = x * x;
        final float xy = x * y;
        final float xz = x * z;
        final float xw = x * w;
        final float yy = y * y;
        final float yz = y * z;
        final float yw = y * w;
        final float zz = z * z;
        final float zw = z * w;

        final float r00 = 1F - (yy + zz) * 2F;
        final float r01 = (xy - zw) * 2F;
        final float r02 = (xz + yw) * 2F;
        final float r10 = (xy + zw) * 2F;
        final float r11 = 1F - (xx + zz) * 2F;
        final float r12 = (yz - xw) * 2F;
        final float r20 = (xz - yw) * 2F;
        final float r21 = (yz + xw) * 2F;
        final float r22 = 1F - (xx + yy) * 2F;

        final float M00 = val[m00] * r00 + val[m10] * r10 + val[m20] * r20;
        final float M01 = val[m00] * r01 + val[m10] * r11 + val[m20] * r21;
        final float M02 = val[m00] * r02 + val[m10] * r12 + val[m20] * r22;
        final float M10 = val[m01] * r00 + val[m11] * r10 + val[m21] * r20;
        final float M11 = val[m01] * r01 + val[m11] * r11 + val[m21] * r21;
        final float M12 = val[m01] * r02 + val[m11] * r12 + val[m21] * r22;
        final float M20 = val[m02] * r00 + val[m12] * r10 + val[m22] * r20;
        final float M21 = val[m02] * r01 + val[m12] * r11 + val[m22] * r21;
        final float M22 = val[m02] * r02 + val[m12] * r12 + val[m22] * r22;
        final float M30 = val[m03] * r00 + val[m13] * r10 + val[m23] * r20;
        final float M31 = val[m03] * r01 + val[m13] * r11 + val[m23] * r21;
        final float M32 = val[m03] * r02 + val[m13] * r12 + val[m23] * r22;

        val[m00] = M00;
        val[m10] = M01;
        val[m20] = M02;
        val[m01] = M10;
        val[m11] = M11;
        val[m21] = M12;
        val[m02] = M20;
        val[m12] = M21;
        val[m22] = M22;
        val[m03] = M30;
        val[m13] = M31;
        val[m23] = M32;
        return this;
    }

    public Matrix4f rotateXYZ(double angleX, double angleY, double angleZ) {
        final float cosX = Mathc.cos(angleX);
        final float sinX = Mathc.sin(angleX);
        final float cosY = Mathc.cos(angleY);
        final float sinY = Mathc.sin(angleY);
        final float cosZ = Mathc.cos(angleZ);
        final float sinZ = Mathc.sin(angleZ);
        final float m_sinX = -sinX;
        final float m_sinY = -sinY;
        final float m_sinZ = -sinZ;
        // Rotate X
        final float nm11 = cosX;
        final float nm12 = sinX;
        final float nm21 = m_sinX;
        final float nm22 = cosX;
        // Rotate Y
        final float nm00 = cosY;
        final float nm01 = nm21 * m_sinY;
        final float nm02 = nm22 * m_sinY;
        val[m02] = sinY;
        val[m12] = nm21 * cosY;
        val[m22] = nm22 * cosY;
        val[m32] = 0F;
        // Rotate Z
        val[m00] = nm00 * cosZ;
        val[m10] = nm01 * cosZ + nm11 * sinZ;
        val[m20] = nm02 * cosZ + nm12 * sinZ;
        val[m30] = 0F;
        val[m01] = nm00 * m_sinZ;
        val[m11] = nm01 * m_sinZ + nm11 * cosZ;
        val[m21] = nm02 * m_sinZ + nm12 * cosZ;
        val[m31] = 0F;
        // Set last column to identity
        val[m03] = 0F;
        val[m13] = 0F;
        val[m23] = 0F;
        val[m33] = 1F;
        return this;
    }

    public Matrix4f rotateZYX(float angleZ, float angleY, float angleX) {
        final float cosZ = Mathc.cos(angleZ);
        final float sinZ = Mathc.sin(angleZ);
        final float cosY = Mathc.cos(angleY);
        final float sinY = Mathc.sin(angleY);
        final float cosX = Mathc.cos(angleX);
        final float sinX = Mathc.sin(angleX);
        final float m_sinZ = -sinZ;
        final float m_sinY = -sinY;
        final float m_sinX = -sinX;
        // Rotate Z
        final float nm00 = cosZ;
        final float nm01 = sinZ;
        final float nm10 = m_sinZ;
        final float nm11 = cosZ;
        // Rotate Y
        final float nm20 = nm00 * sinY;
        final float nm21 = nm01 * sinY;
        final float nm22 = cosY;
        val[m00] = nm00 * cosY;
        val[m10] = nm01 * cosY;
        val[m20] = m_sinY;
        val[m30] = 0F;
        // Rotate X
        val[m01] = nm10 * cosX + nm20 * sinX;
        val[m11] = nm11 * cosX + nm21 * sinX;
        val[m21] = nm22 * sinX;
        val[m31] = 0F;
        val[m02] = nm10 * m_sinX + nm20 * cosX;
        val[m12] = nm11 * m_sinX + nm21 * cosX;
        val[m22] = nm22 * cosX;
        val[m32] = 0F;
        // Set last column to identity
        val[m03] = 0F;
        val[m13] = 0F;
        val[m23] = 0F;
        val[m33] = 1F;
        return this;
    }

    public Matrix4f rotateYXZ(float angleY, float angleX, float angleZ) {
        final float cosY = Mathc.cos(angleY);
        final float sinY = Mathc.sin(angleY);
        final float cosX = Mathc.cos(angleX);
        final float sinX = Mathc.sin(angleX);
        final float cosZ = Mathc.cos(angleZ);
        final float sinZ = Mathc.sin(angleZ);
        final float m_sinY = -sinY;
        final float m_sinX = -sinX;
        final float m_sinZ = -sinZ;
        // Rotate Y
        final float nm00 = cosY;
        final float nm02 = m_sinY;
        final float nm20 = sinY;
        final float nm22 = cosY;
        // Rotate X
        final float nm10 = nm20 * sinX;
        final float nm11 = cosX;
        final float nm12 = nm22 * sinX;
        val[m20] = nm20 * cosX;
        val[m21] = m_sinX;
        val[m22] = nm22 * cosX;
        val[m23] = 0F;
        // Rotate Z
        val[m00] = nm00 * cosZ + nm10 * sinZ;
        val[m01] = nm11 * sinZ;
        val[m02] = nm02 * cosZ + nm12 * sinZ;
        val[m03] = 0F;
        val[m10] = nm00 * m_sinZ + nm10 * cosZ;
        val[m11] = nm11 * cosZ;
        val[m12] = nm02 * m_sinZ + nm12 * cosZ;
        val[m13] = 0F;
        // Set last column to identity
        val[m30] = 0F;
        val[m31] = 0F;
        val[m32] = 0F;
        val[m33] = 1F;
        return this;
    }


    public Matrix4f rotateX(float ang) {
        final float cos = Mathc.cos(ang);
        final float sin = Mathc.sin(ang);
        final float rm11 = cos;
        final float rm12 = sin;
        final float rm21 = -sin;
        final float rm22 = cos;
        // merge temporaries for dependent values
        final float nm01 = val[m01] * rm11 + val[m02] * rm12;
        final float nm11 = val[m11] * rm11 + val[m12] * rm12;
        final float nm21 = val[m21] * rm11 + val[m22] * rm12;
        final float nm31 = val[m31] * rm11 + val[m32] * rm12;
        // set non-dependent values directly
        val[m02] = val[m01] * rm21 + val[m02] * rm22;
        val[m12] = val[m11] * rm21 + val[m12] * rm22;
        val[m22] = val[m21] * rm21 + val[m22] * rm22;
        val[m32] = val[m31] * rm21 + val[m32] * rm22;
        // set other values
        val[m01] = nm01;
        val[m11] = nm11;
        val[m21] = nm21;
        val[m31] = nm31;
        return this;
    }

    public Matrix4f rotateY(float ang) {
        final float cos = Mathc.cos(ang);
        final float sin = Mathc.sin(ang);
        final float rm00 = cos;
        final float rm02 = -sin;
        final float rm20 = sin;
        final float rm22 = cos;
        // merge temporaries for dependent values
        final float nm00 = val[m00] * rm00 + val[m02] * rm02;
        final float nm10 = val[m10] * rm00 + val[m12] * rm02;
        final float nm20 = val[m20] * rm00 + val[m22] * rm02;
        final float nm30 = val[m30] * rm00 + val[m32] * rm02;
        // set non-dependent values directly
        val[m02] = val[m00] * rm20 + val[m02] * rm22;
        val[m12] = val[m10] * rm20 + val[m12] * rm22;
        val[m22] = val[m20] * rm20 + val[m22] * rm22;
        val[m32] = val[m30] * rm20 + val[m32] * rm22;
        // set other values
        val[m00] = nm00;
        val[m10] = nm10;
        val[m20] = nm20;
        val[m30] = nm30;
        return this;
    }

    public Matrix4f rotateZ(float ang) {
        final float cos = Mathc.cos(ang);
        final float sin = Mathc.sin(ang);
        final float rm00 = cos;
        final float rm01 = sin;
        final float rm10 = -sin;
        final float rm11 = cos;

        final float nm00 = val[m00] * rm00 + val[m01] * rm01;
        final float nm10 = val[m10] * rm00 + val[m11] * rm01;
        final float nm20 = val[m20] * rm00 + val[m21] * rm01;
        final float nm30 = val[m30] * rm00 + val[m31] * rm01;
        val[m01] = val[m00] * rm10 + val[m01] * rm11;
        val[m11] = val[m10] * rm10 + val[m11] * rm11;
        val[m21] = val[m20] * rm10 + val[m21] * rm11;
        val[m31] = val[m30] * rm10 + val[m31] * rm11;
        val[m00] = nm00;
        val[m10] = nm10;
        val[m20] = nm20;
        val[m30] = nm30;
        return this;
    }

    /* Set Rotation */

    public Matrix4f setRotation(float angle, float x, float y, float z) {
        final float cos = Mathc.cos(angle);
        final float sin = Mathc.sin(angle);
        final float C = 1F - cos;
        final float xy = x * y;
        final float xz = x * z;
        final float yz = y * z;
        val[m00] = cos + x * x * C;
        val[m01] = xy * C - z * sin;
        val[m02] = xz * C + y * sin;
        val[m03] = 0F;
        val[m10] = xy * C + z * sin;
        val[m11] = cos + y * y * C;
        val[m12] = yz * C - x * sin;
        val[m13] = 0F;
        val[m20] = xz * C - y * sin;
        val[m21] = yz * C + x * sin;
        val[m22] = cos + z * z * C;
        val[m23] = 0F;
        val[m30] = 0F;
        val[m31] = 0F;
        val[m32] = 0F;
        val[m33] = 1F;
        return this;
    }

    public Matrix4f setRotationX(double degrees) {
        this.identity();
        final float cos = Maths.cosDeg(degrees);
        final float sin = Maths.sinDeg(degrees);
        val[m11] = cos;
        val[m21] = -sin;
        val[m12] = sin;
        val[m22] = cos;
        return this;
    }

    public Matrix4f setRotationY(double degrees) {
        this.identity();
        final float cos = Maths.cosDeg(degrees);
        final float sin = Maths.sinDeg(degrees);
        val[m00] = cos;
        val[m20] = sin;
        val[m02] = -sin;
        val[m22] = cos;
        return this;
    }

    public Matrix4f setRotationZ(double degrees) {
        this.identity();
        final float cos = Maths.cosDeg(degrees);
        final float sin = Maths.sinDeg(degrees);
        val[m00] = cos;
        val[m10] = -sin;
        val[m01] = sin;
        val[m11] = cos;
        return this;
    }

    public Matrix4f setRotationXYZ(float angleX, float angleY, float angleZ) {
        final float cosX = Mathc.cos(angleX);
        final float sinX = Mathc.sin(angleX);
        final float cosY = Mathc.cos(angleY);
        final float sinY = Mathc.sin(angleY);
        final float cosZ = Mathc.cos(angleZ);
        final float sinZ = Mathc.sin(angleZ);
        final float m_sinX = -sinX;
        final float m_sinY = -sinY;
        final float m_sinZ = -sinZ;
        // rotateX
        final float nm11 = cosX;
        final float nm12 = sinX;
        final float nm21 = m_sinX;
        final float nm22 = cosX;
        // rotateY
        final float nm00 = cosY;
        final float nm01 = nm21 * m_sinY;
        final float nm02 = nm22 * m_sinY;
        val[m02] = sinY;
        val[m12] = nm21 * cosY;
        val[m22] = nm22 * cosY;
        // rotateZ
        val[m00] = nm00 * cosZ;
        val[m10] = nm01 * cosZ + nm11 * sinZ;
        val[m20] = nm02 * cosZ + nm12 * sinZ;
        val[m01] = nm00 * m_sinZ;
        val[m11] = nm01 * m_sinZ + nm11 * cosZ;
        val[m21] = nm02 * m_sinZ + nm12 * cosZ;
        return this;
    }

    public Matrix4f setRotationZYX(float angleZ, float angleY, float angleX) {
        final float cosZ = Mathc.cos(angleZ);
        final float sinZ = Mathc.sin(angleZ);
        final float cosY = Mathc.cos(angleY);
        final float sinY = Mathc.sin(angleY);
        final float cosX = Mathc.cos(angleX);
        final float sinX = Mathc.sin(angleX);
        final float m_sinZ = -sinZ;
        final float m_sinY = -sinY;
        final float m_sinX = -sinX;
        // rotateZ
        final float nm00 = cosZ;
        final float nm01 = sinZ;
        final float nm10 = m_sinZ;
        final float nm11 = cosZ;
        // rotateY
        final float nm20 = nm00 * sinY;
        final float nm21 = nm01 * sinY;
        final float nm22 = cosY;
        val[m00] = nm00 * cosY;
        val[m10] = nm01 * cosY;
        val[m20] = m_sinY;
        // rotateX
        val[m01] = nm10 * cosX + nm20 * sinX;
        val[m11] = nm11 * cosX + nm21 * sinX;
        val[m21] = nm22 * sinX;
        val[m02] = nm10 * m_sinX + nm20 * cosX;
        val[m12] = nm11 * m_sinX + nm21 * cosX;
        val[m22] = nm22 * cosX;
        return this;
    }

    public Matrix4f setRotationYXZ(float angleY, float angleX, float angleZ) {
        final float cosY = Mathc.cos(angleY);
        final float sinY = Mathc.sin(angleY);
        final float cosX = Mathc.cos(angleX);
        final float sinX = Mathc.sin(angleX);
        final float cosZ = Mathc.cos(angleZ);
        final float sinZ = Mathc.sin(angleZ);
        final float m_sinY = -sinY;
        final float m_sinX = -sinX;
        final float m_sinZ = -sinZ;
        // rotateY
        final float nm00 = cosY;
        final float nm02 = m_sinY;
        final float nm20 = sinY;
        final float nm22 = cosY;
        // rotateX
        final float nm10 = nm20 * sinX;
        final float nm11 = cosX;
        final float nm12 = nm22 * sinX;
        val[m02] = nm20 * cosX;
        val[m12] = m_sinX;
        val[m22] = nm22 * cosX;
        // rotateZ
        val[m00] = nm00 * cosZ + nm10 * sinZ;
        val[m10] = nm11 * sinZ;
        val[m20] = nm02 * cosZ + nm12 * sinZ;
        val[m01] = nm00 * m_sinZ + nm10 * cosZ;
        val[m11] = nm11 * cosZ;
        val[m21] = nm02 * m_sinZ + nm12 * cosZ;
        return this;
    }

    public Matrix4f setRotation(EulerAngles angles) {
        return this.setRotationXYZ(angles.yaw, angles.pitch, angles.roll);
    }


    /* Quaternion */

    public Matrix4f setQuaternion(float w, float x, float y, float z) {
        final float xx = (x * x);
        final float xy = (x * y);
        final float xz = (x * z);
        final float xw = (x * w);
        final float yy = (y * y);
        final float yz = (y * z);
        final float yw = (y * w);
        final float zz = (z * z);
        final float zw = (z * w);

        val[m00] = 1F - (yy + zz) * 2F;
        val[m01] =      (xy - zw) * 2F;
        val[m02] =      (xz + yw) * 2F;
        val[m03] = 0F;
        val[m10] =      (xy + zw) * 2F;
        val[m11] = 1F - (xx + zz) * 2F;
        val[m12] =      (yz - xw) * 2F;
        val[m13] = 0F;
        val[m20] =      (xz - yw) * 2F;
        val[m21] =      (yz + xw) * 2F;
        val[m22] = 1F - (xx + yy) * 2F;
        val[m23] = 0F;
        val[m30] = 0F;
        val[m31] = 0F;
        val[m32] = 0F;
        val[m33] = 1F;
        return this;
    }

    public Matrix4f setQuaternion(Quaternion quaternion) {
        return this.setQuaternion(quaternion.w, quaternion.x, quaternion.y, quaternion.z);
    }


    public Vec3f getPositiveZ(Vec3f dst) {
        return dst.set(
            (val[m01] * val[m12] - val[m11] * val[m02]),
            (val[m02] * val[m10] - val[m12] * val[m00]),
            (val[m00] * val[m11] - val[m10] * val[m01])
        ).nor();
    }

    public Vec3f getPositiveX(Vec3f dst) {
        return dst.set(
            (val[m11] * val[m22] - val[m21] * val[m12]),
            (val[m20] * val[m12] - val[m10] * val[m22]),
            (val[m10] * val[m21] - val[m20] * val[m11])
        ).nor();
    }

    public Vec3f getPositiveY(Vec3f dst) {
        return dst.set(
            (val[m21] * val[m02] - val[m01] * val[m22]),
            (val[m00] * val[m22] - val[m20] * val[m02]),
            (val[m20] * val[m01] - val[m00] * val[m21])
        ).nor();
    }


    /* Linear Interpolation */

    public Matrix4f lerp(Matrix4f matrix, float t) {
        final float ti = 1F - t;
        for(int i = 0; i < 16; i++)
            val[i] = val[i] * ti + matrix.val[i] * t;
        return this;
    }


    /* Copy */

    public Matrix4f copy() {
        return new Matrix4f(this);
    }


    /* Multiply */

    public float[] getMul(float[] values) {
        return mul(this.val, values);
    }

    public float[] getMul(Matrix4f matrix) {
        return mul(this, matrix);
    }

    public Matrix4f mul(Matrix4f matrix) {
        return set(mul(this, matrix));
    }

    public Matrix4f mul(float[] values) {
        return set(mul(this.val, values));
    }


    public static float[] mul(float[] a, float[] b) {
        return new float[]{
            a[m00] * b[m00] + a[m10] * b[m01] + a[m20] * b[m02] + a[m30] * b[m03],
            a[m01] * b[m00] + a[m11] * b[m01] + a[m21] * b[m02] + a[m31] * b[m03],
            a[m02] * b[m00] + a[m12] * b[m01] + a[m22] * b[m02] + a[m32] * b[m03],
            a[m03] * b[m00] + a[m13] * b[m01] + a[m23] * b[m02] + a[m33] * b[m03],

            a[m00] * b[m10] + a[m10] * b[m11] + a[m20] * b[m12] + a[m30] * b[m13],
            a[m01] * b[m10] + a[m11] * b[m11] + a[m21] * b[m12] + a[m31] * b[m13],
            a[m02] * b[m10] + a[m12] * b[m11] + a[m22] * b[m12] + a[m32] * b[m13],
            a[m03] * b[m10] + a[m13] * b[m11] + a[m23] * b[m12] + a[m33] * b[m13],

            a[m00] * b[m20] + a[m10] * b[m21] + a[m20] * b[m22] + a[m30] * b[m23],
            a[m01] * b[m20] + a[m11] * b[m21] + a[m21] * b[m22] + a[m31] * b[m23],
            a[m02] * b[m20] + a[m12] * b[m21] + a[m22] * b[m22] + a[m32] * b[m23],
            a[m03] * b[m20] + a[m13] * b[m21] + a[m23] * b[m22] + a[m33] * b[m23],

            a[m00] * b[m30] + a[m10] * b[m31] + a[m20] * b[m32] + a[m30] * b[m33],
            a[m01] * b[m30] + a[m11] * b[m31] + a[m21] * b[m32] + a[m31] * b[m33],
            a[m02] * b[m30] + a[m12] * b[m31] + a[m22] * b[m32] + a[m32] * b[m33],
            a[m03] * b[m30] + a[m13] * b[m31] + a[m23] * b[m32] + a[m33] * b[m33]
        };
    }

    public static float[] mul(Matrix4f a, Matrix4f b) {
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

        final Matrix4f matrix = (Matrix4f) object;
        return Arrays.compare(val, matrix.val) == 0;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(val);
    }

}