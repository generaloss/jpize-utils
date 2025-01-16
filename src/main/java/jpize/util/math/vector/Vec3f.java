package jpize.util.math.vector;

import jpize.util.math.Maths;
import jpize.util.math.Mathc;
import jpize.util.math.matrix.*;
import java.util.Objects;

public class Vec3f {

    public float x;
    public float y;
    public float z;

    public Vec3f() { }

    public Vec3f(float xyz) {
        this.set(xyz);
    }

    public Vec3f(double xyz) {
        this.set(xyz);
    }

    public Vec3f(int xyz) {
        this.set(xyz);
    }

    public Vec3f(float x, float y) {
        this.set(x, y);
    }

    public Vec3f(double x, double y) {
        this.set(x, y);
    }

    public Vec3f(int x, int y) {
        this.set(x, y);
    }

    public Vec3f(float x, float y, float z) {
        this.set(x, y, z);
    }

    public Vec3f(double x, double y, double z) {
        this.set(x, y, z);
    }

    public Vec3f(int x, int y, int z) {
        this.set(x, y, z);
    }

    public Vec3f(Vec2f vector) {
        this.set(vector.x, vector.y);
    }

    public Vec3f(Vec2d vector) {
        this.set(vector.x, vector.y);
    }

    public Vec3f(Vec2i vector) {
        this.set(vector.x, vector.y);
    }

    public Vec3f(Vec3f vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec3f(Vec3d vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec3f(Vec3i vector) {
        this.set(vector.x, vector.y, vector.z);
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }


    public Vec3f set(float xyz) {
        this.x = xyz;
        this.y = xyz;
        this.z = xyz;
        return this;
    }

    public Vec3f set(double xyz) {
        return this.set((float) xyz);
    }

    public Vec3f set(int xyz) {
        return this.set((float) xyz);
    }

    public Vec3f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec3f set(double x, double y) {
        return this.set((float) x, (float) y);
    }

    public Vec3f set(int x, int y) {
        return this.set((float) x, (float) y);
    }

    public Vec3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3f set(double x, double y, double z) {
        return this.set((float) x, (float) y, (float) z);
    }

    public Vec3f set(int x, int y, int z) {
        return this.set((float) x, (float) y, (float) z);
    }

    public Vec3f set(Vec2f vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec3f set(Vec2d vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec3f set(Vec2i vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec3f set(Vec3f vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    public Vec3f set(Vec3d vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    public Vec3f set(Vec3i vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    public Vec3f setXY(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec3f setXY(double x, double y) {
        return setXY((float) x, (float) y);
    }

    public Vec3f setXY(int x, int y) {
        return setXY((float) x, (float) y);
    }

    public Vec3f setXZ(float x, float z) {
        this.x = x;
        this.z = z;
        return this;
    }

    public Vec3f setXZ(double x, double z) {
        return setXZ((float) x, (float) z);
    }

    public Vec3f setXZ(int x, int z) {
        return setXZ((float) x, (float) z);
    }

    public Vec3f setYZ(float y, float z) {
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3f setYZ(double y, double z) {
        return setYZ((float) y, (float) z);
    }

    public Vec3f setYZ(int y, int z) {
        return setYZ((float) y, (float) z);
    }

    public Vec3f setXY(Vec2f vector) {
        return setXY(vector.x, vector.y);
    }

    public Vec3f setXY(Vec2d vector) {
        return setXY(vector.x, vector.y);
    }

    public Vec3f setXY(Vec2i vector) {
        return setXY(vector.x, vector.y);
    }

    public Vec3f setXZ(Vec2f vector) {
        return setXZ(vector.x, vector.y);
    }

    public Vec3f setXZ(Vec2d vector) {
        return setXZ(vector.x, vector.y);
    }

    public Vec3f setXZ(Vec2i vector) {
        return setXZ(vector.x, vector.y);
    }

    public Vec3f setYZ(Vec2f vector) {
        return setYZ(vector.x, vector.y);
    }

    public Vec3f setYZ(Vec2d vector) {
        return setYZ(vector.x, vector.y);
    }

    public Vec3f setYZ(Vec2i vector) {
        return setYZ(vector.x, vector.y);
    }


    public Vec3f add(float xyz) {
        this.x += xyz;
        this.y += xyz;
        this.z += xyz;
        return this;
    }

    public Vec3f add(double xyz) {
        return this.add((float) xyz);
    }

    public Vec3f add(int xyz) {
        return this.add((float) xyz);
    }

    public Vec3f add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec3f add(double x, double y) {
        return this.add((float) x, (float) y);
    }

    public Vec3f add(int x, int y) {
        return this.add((float) x, (float) y);
    }

    public Vec3f add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3f add(double x, double y, double z) {
        return this.add((float) x, (float) y, (float) z);
    }

    public Vec3f add(int x, int y, int z) {
        return this.add((float) x, (float) y, (float) z);
    }

    public Vec3f add(Vec2f vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec3f add(Vec2d vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec3f add(Vec2i vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec3f add(Vec3f vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vec3f add(Vec3d vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vec3f add(Vec3i vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vec3f addXY(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec3f addXY(double x, double y) {
        return addXY((float) x, (float) y);
    }

    public Vec3f addXY(int x, int y) {
        return addXY((float) x, (float) y);
    }

    public Vec3f addXZ(float x, float z) {
        this.x += x;
        this.z += z;
        return this;
    }

    public Vec3f addXZ(double x, double z) {
        return addXZ((float) x, (float) z);
    }

    public Vec3f addXZ(int x, int z) {
        return addXZ((float) x, (float) z);
    }

    public Vec3f addYZ(float y, float z) {
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3f addYZ(double y, double z) {
        return addYZ((float) y, (float) z);
    }

    public Vec3f addYZ(int y, int z) {
        return addYZ((float) y, (float) z);
    }

    public Vec3f addXY(Vec2f vector) {
        return addXY(vector.x, vector.y);
    }

    public Vec3f addXY(Vec2d vector) {
        return addXY(vector.x, vector.y);
    }

    public Vec3f addXY(Vec2i vector) {
        return addXY(vector.x, vector.y);
    }

    public Vec3f addXZ(Vec2f vector) {
        return addXZ(vector.x, vector.y);
    }

    public Vec3f addXZ(Vec2d vector) {
        return addXZ(vector.x, vector.y);
    }

    public Vec3f addXZ(Vec2i vector) {
        return addXZ(vector.x, vector.y);
    }

    public Vec3f addYZ(Vec2f vector) {
        return addYZ(vector.x, vector.y);
    }

    public Vec3f addYZ(Vec2d vector) {
        return addYZ(vector.x, vector.y);
    }

    public Vec3f addYZ(Vec2i vector) {
        return addYZ(vector.x, vector.y);
    }


    public Vec3f sub(float xyz) {
        this.x -= xyz;
        this.y -= xyz;
        this.z -= xyz;
        return this;
    }

    public Vec3f sub(double xyz) {
        return this.sub((float) xyz);
    }

    public Vec3f sub(int xyz) {
        return this.sub((float) xyz);
    }

    public Vec3f sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec3f sub(double x, double y) {
        return this.sub((float) x, (float) y);
    }

    public Vec3f sub(int x, int y) {
        return this.sub((float) x, (float) y);
    }

    public Vec3f sub(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3f sub(double x, double y, double z) {
        return this.sub((float) x, (float) y, (float) z);
    }

    public Vec3f sub(int x, int y, int z) {
        return this.sub((float) x, (float) y, (float) z);
    }

    public Vec3f sub(Vec2f vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec3f sub(Vec2d vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec3f sub(Vec2i vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec3f sub(Vec3f vector) {
        return this.sub(vector.x, vector.y, vector.z);
    }

    public Vec3f sub(Vec3d vector) {
        return this.sub(vector.x, vector.y, vector.z);
    }

    public Vec3f sub(Vec3i vector) {
        return this.sub(vector.x, vector.y, vector.z);
    }

    public Vec3f subXY(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec3f subXY(double x, double y) {
        return subXY((float) x, (float) y);
    }

    public Vec3f subXY(int x, int y) {
        return subXY((float) x, (float) y);
    }

    public Vec3f subXZ(float x, float z) {
        this.x -= x;
        this.z -= z;
        return this;
    }

    public Vec3f subXZ(double x, double z) {
        return subXZ((float) x, (float) z);
    }

    public Vec3f subXZ(int x, int z) {
        return subXZ((float) x, (float) z);
    }

    public Vec3f subYZ(float y, float z) {
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3f subYZ(double y, double z) {
        return subYZ((float) y, (float) z);
    }

    public Vec3f subYZ(int y, int z) {
        return subYZ((float) y, (float) z);
    }

    public Vec3f subXY(Vec2f vector) {
        return subXY(vector.x, vector.y);
    }

    public Vec3f subXY(Vec2d vector) {
        return subXY(vector.x, vector.y);
    }

    public Vec3f subXY(Vec2i vector) {
        return subXY(vector.x, vector.y);
    }

    public Vec3f subXZ(Vec2f vector) {
        return subXZ(vector.x, vector.y);
    }

    public Vec3f subXZ(Vec2d vector) {
        return subXZ(vector.x, vector.y);
    }

    public Vec3f subXZ(Vec2i vector) {
        return subXZ(vector.x, vector.y);
    }

    public Vec3f subYZ(Vec2f vector) {
        return subYZ(vector.x, vector.y);
    }

    public Vec3f subYZ(Vec2d vector) {
        return subYZ(vector.x, vector.y);
    }

    public Vec3f subYZ(Vec2i vector) {
        return subYZ(vector.x, vector.y);
    }


    public Vec3f mul(float xyz) {
        this.x *= xyz;
        this.y *= xyz;
        this.z *= xyz;
        return this;
    }

    public Vec3f mul(double xyz) {
        return this.mul((float) xyz);
    }

    public Vec3f mul(int xyz) {
        return this.mul((float) xyz);
    }

    public Vec3f mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec3f mul(double x, double y) {
        return this.mul((float) x, (float) y);
    }

    public Vec3f mul(int x, int y) {
        return this.mul((float) x, (float) y);
    }

    public Vec3f mul(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3f mul(double x, double y, double z) {
        return this.mul((float) x, (float) y, (float) z);
    }

    public Vec3f mul(int x, int y, int z) {
        return this.mul((float) x, (float) y, (float) z);
    }

    public Vec3f mul(Vec2f vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec3f mul(Vec2d vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec3f mul(Vec2i vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec3f mul(Vec3f vector) {
        return this.mul(vector.x, vector.y, vector.z);
    }

    public Vec3f mul(Vec3d vector) {
        return this.mul(vector.x, vector.y, vector.z);
    }

    public Vec3f mul(Vec3i vector) {
        return this.mul(vector.x, vector.y, vector.z);
    }

    public Vec3f mulXY(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec3f mulXY(double x, double y) {
        return mulXY((float) x, (float) y);
    }

    public Vec3f mulXY(int x, int y) {
        return mulXY((float) x, (float) y);
    }

    public Vec3f mulXZ(float x, float z) {
        this.x *= x;
        this.z *= z;
        return this;
    }

    public Vec3f mulXZ(double x, double z) {
        return mulXZ((float) x, (float) z);
    }

    public Vec3f mulXZ(int x, int z) {
        return mulXZ((float) x, (float) z);
    }

    public Vec3f mulYZ(float y, float z) {
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3f mulYZ(double y, double z) {
        return mulYZ((float) y, (float) z);
    }

    public Vec3f mulYZ(int y, int z) {
        return mulYZ((float) y, (float) z);
    }

    public Vec3f mulXY(Vec2f vector) {
        return mulXY(vector.x, vector.y);
    }

    public Vec3f mulXY(Vec2d vector) {
        return mulXY(vector.x, vector.y);
    }

    public Vec3f mulXY(Vec2i vector) {
        return mulXY(vector.x, vector.y);
    }

    public Vec3f mulXZ(Vec2f vector) {
        return mulXZ(vector.x, vector.y);
    }

    public Vec3f mulXZ(Vec2d vector) {
        return mulXZ(vector.x, vector.y);
    }

    public Vec3f mulXZ(Vec2i vector) {
        return mulXZ(vector.x, vector.y);
    }

    public Vec3f mulYZ(Vec2f vector) {
        return mulYZ(vector.x, vector.y);
    }

    public Vec3f mulYZ(Vec2d vector) {
        return mulYZ(vector.x, vector.y);
    }

    public Vec3f mulYZ(Vec2i vector) {
        return mulYZ(vector.x, vector.y);
    }


    public Vec3f div(float xyz) {
        this.x /= xyz;
        this.y /= xyz;
        this.z /= xyz;
        return this;
    }

    public Vec3f div(double xyz) {
        return this.div((float) xyz);
    }

    public Vec3f div(int xyz) {
        return this.div((float) xyz);
    }

    public Vec3f div(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec3f div(double x, double y) {
        return this.div((float) x, (float) y);
    }

    public Vec3f div(int x, int y) {
        return this.div((float) x, (float) y);
    }

    public Vec3f div(float x, float y, float z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3f div(double x, double y, double z) {
        return this.div((float) x, (float) y, (float) z);
    }

    public Vec3f div(int x, int y, int z) {
        return this.div((float) x, (float) y, (float) z);
    }

    public Vec3f div(Vec2f vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec3f div(Vec2d vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec3f div(Vec2i vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec3f div(Vec3f vector) {
        return this.div(vector.x, vector.y, vector.z);
    }

    public Vec3f div(Vec3d vector) {
        return this.div(vector.x, vector.y, vector.z);
    }

    public Vec3f div(Vec3i vector) {
        return this.div(vector.x, vector.y, vector.z);
    }

    public Vec3f divXY(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec3f divXY(double x, double y) {
        return divXY((float) x, (float) y);
    }

    public Vec3f divXY(int x, int y) {
        return divXY((float) x, (float) y);
    }

    public Vec3f divXZ(float x, float z) {
        this.x /= x;
        this.z /= z;
        return this;
    }

    public Vec3f divXZ(double x, double z) {
        return divXZ((float) x, (float) z);
    }

    public Vec3f divXZ(int x, int z) {
        return divXZ((float) x, (float) z);
    }

    public Vec3f divYZ(float y, float z) {
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3f divYZ(double y, double z) {
        return divYZ((float) y, (float) z);
    }

    public Vec3f divYZ(int y, int z) {
        return divYZ((float) y, (float) z);
    }

    public Vec3f divXY(Vec2f vector) {
        return divXY(vector.x, vector.y);
    }

    public Vec3f divXY(Vec2d vector) {
        return divXY(vector.x, vector.y);
    }

    public Vec3f divXY(Vec2i vector) {
        return divXY(vector.x, vector.y);
    }

    public Vec3f divXZ(Vec2f vector) {
        return divXZ(vector.x, vector.y);
    }

    public Vec3f divXZ(Vec2d vector) {
        return divXZ(vector.x, vector.y);
    }

    public Vec3f divXZ(Vec2i vector) {
        return divXZ(vector.x, vector.y);
    }

    public Vec3f divYZ(Vec2f vector) {
        return divYZ(vector.x, vector.y);
    }

    public Vec3f divYZ(Vec2d vector) {
        return divYZ(vector.x, vector.y);
    }

    public Vec3f divYZ(Vec2i vector) {
        return divYZ(vector.x, vector.y);
    }


    public static float dst2(float x1, float y1, float z1, float x2, float y2, float z2) {
        final float dx = (x2 - x1);
        final float dy = (y2 - y1);
        final float dz = (z2 - z1);
        return (dx * dx + dy * dy + dz * dz);
    }

    public static float dst2(float x1, float y1, float z1, double x2, double y2, double z2) {
        return dst2(x1, y1, z1, (float) x2, (float) y2, (float) z2);
    }

    public static float dst2(float x1, float y1, float z1, int x2, int y2, int z2) {
        return dst2(x1, y1, z1, (float) x2, (float) y2, (float) z2);
    }

    public static float dst2(float x, float y, float z, Vec3f vector) {
        return dst2(x, y, z, vector.x, vector.y, vector.z);
    }

    public static float dst2(float x, float y, float z, Vec3d vector) {
        return dst2(x, y, z, vector.x, vector.y, vector.z);
    }

    public static float dst2(float x, float y, float z, Vec3i vector) {
        return dst2(x, y, z, vector.x, vector.y, vector.z);
    }

    public static float dst2(Vec3f vector, float x, float y, float z) {
        return dst2(vector.x, vector.y, vector.z, x, y, z);
    }

    public static float dst2(Vec3f vector, double x, double y, double z) {
        return dst2(vector.x, vector.y, vector.z, x, y, z);
    }

    public static float dst2(Vec3f vector, int x, int y, int z) {
        return dst2(vector.x, vector.y, vector.z, x, y, z);
    }

    public static float dst2(Vec3f vector1, Vec3f vector2) {
        return dst2(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static float dst2(Vec3f vector1, Vec3d vector2) {
        return dst2(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static float dst2(Vec3f vector1, Vec3i vector2) {
        return dst2(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public float dst2(float x, float y, float z) {
        return dst2(this, x, y, z);
    }

    public float dst2(double x, double y, double z) {
        return dst2(this, x, y, z);
    }

    public float dst2(int x, int y, int z) {
        return dst2(this, x, y, z);
    }

    public float dst2(Vec3f vector) {
        return dst2(this, vector);
    }

    public float dst2(Vec3d vector) {
        return dst2(this, vector);
    }

    public float dst2(Vec3i vector) {
        return dst2(this, vector);
    }


    public static float dst(float x1, float y1, float z1, float x2, float y2, float z2) {
        return Mathc.sqrt(dst2(x1, y1, z1, x2, y2, z2));
    }

    public static float dst(float x1, float y1, float z1, double x2, double y2, double z2) {
        return dst(x1, y1, z1, (float) x2, (float) y2, (float) z2);
    }

    public static float dst(float x1, float y1, float z1, int x2, int y2, int z2) {
        return dst(x1, y1, z1, (float) x2, (float) y2, (float) z2);
    }

    public static float dst(float x, float y, float z, Vec3f vector) {
        return dst(x, y, z, vector.x, vector.y, vector.z);
    }

    public static float dst(float x, float y, float z, Vec3d vector) {
        return dst(x, y, z, vector.x, vector.y, vector.z);
    }

    public static float dst(float x, float y, float z, Vec3i vector) {
        return dst(x, y, z, vector.x, vector.y, vector.z);
    }

    public static float dst(Vec3f vector, float x, float y, float z) {
        return dst(vector.x, vector.y, vector.z, x, y, z);
    }

    public static float dst(Vec3f vector, double x, double y, double z) {
        return dst(vector.x, vector.y, vector.z, x, y, z);
    }

    public static float dst(Vec3f vector, int x, int y, int z) {
        return dst(vector.x, vector.y, vector.z, x, y, z);
    }

    public static float dst(Vec3f vector1, Vec3f vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static float dst(Vec3f vector1, Vec3d vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static float dst(Vec3f vector1, Vec3i vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public float dst(float x, float y, float z) {
        return dst(this, x, y, z);
    }

    public float dst(double x, double y, double z) {
        return dst(this, x, y, z);
    }

    public float dst(int x, int y, int z) {
        return dst(this, x, y, z);
    }

    public float dst(Vec3f vector) {
        return dst(this, vector);
    }

    public float dst(Vec3d vector) {
        return dst(this, vector);
    }

    public float dst(Vec3i vector) {
        return dst(this, vector);
    }


    public static Vec3f shorter(Vec3f vector1, Vec3f vector2) {
        return (vector1.len2() < vector2.len2()) ? vector1 : vector2;
    }

    public static Vec3f longer(Vec3f vector1, Vec3f vector2) {
        return (vector1.len2() > vector2.len2()) ? vector1 : vector2;
    }

    public Vec3f setShorter(Vec3f vector1, Vec3f vector2) {
        return this.set(shorter(vector1, vector2));
    }

    public Vec3f setLonger(Vec3f vector1, Vec3f vector2) {
        return this.set(longer(vector1, vector2));
    }


    public float minComp() {
        return Math.min(x, Math.min(y, z));
    }

    public float maxComp() {
        return Math.max(x, Math.max(y, z));
    }


    public static Vec3f minComps(Vec3f dst, float x1, float y1, float z1, float x2, float y2, float z2) {
        return dst.set(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));
    }

    public static Vec3f minComps(Vec3f dst, float x1, float y1, float z1, Vec3f vector2) {
        return minComps(dst, x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static Vec3f minComps(Vec3f dst, Vec3f vector1, float x2, float y2, float z2) {
        return minComps(dst, vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static Vec3f minComps(Vec3f dst, Vec3f vector1, float xyz2) {
        return minComps(dst, vector1.x, vector1.y, vector1.z, xyz2, xyz2, xyz2);
    }

    public static Vec3f minComps(Vec3f dst, Vec3f vector1, Vec3f vector2) {
        return minComps(dst, vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public Vec3f setMinComps(float x1, float y1, float z1, float x2, float y2, float z2) {
        return minComps(this, x1, y1, z1, x2, y2, z2);
    }

    public Vec3f setMinComps(float x1, float y1, float z1, Vec3f vector2) {
        return minComps(this, x1, y1, z1, vector2);
    }

    public Vec3f setMinComps(Vec3f vector1, float x2, float y2, float z2) {
        return minComps(this, vector1, x2, y2, z2);
    }

    public Vec3f setMinComps(Vec3f vector1, float xyz2) {
        return minComps(this, vector1, xyz2);
    }

    public Vec3f setMinComps(Vec3f vector1, Vec3f vector2) {
        return minComps(this, vector1, vector2);
    }

    public Vec3f setMinComps(float x2, float y2, float z2) {
        return this.setMinComps(this, x2, y2, z2);
    }

    public Vec3f setMinComps(float xyz2) {
        return this.setMinComps(this, xyz2);
    }

    public Vec3f setMinComps(Vec3f vector2) {
        return this.setMinComps(this, vector2);
    }


    public static Vec3f maxComps(Vec3f dst, float x1, float y1, float z1, float x2, float y2, float z2) {
        return dst.set(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
    }

    public static Vec3f maxComps(Vec3f dst, float x1, float y1, float z1, Vec3f vector2) {
        return maxComps(dst, x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static Vec3f maxComps(Vec3f dst, Vec3f vector1, float x2, float y2, float z2) {
        return maxComps(dst, vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static Vec3f maxComps(Vec3f dst, Vec3f vector1, float xyz2) {
        return maxComps(dst, vector1.x, vector1.y, vector1.z, xyz2, xyz2, xyz2);
    }

    public static Vec3f maxComps(Vec3f dst, Vec3f vector1, Vec3f vector2) {
        return maxComps(dst, vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public Vec3f setMaxComps(float x1, float y1, float z1, float x2, float y2, float z2) {
        return maxComps(this, x1, y1, z1, x2, y2, z2);
    }

    public Vec3f setMaxComps(float x1, float y1, float z1, Vec3f vector2) {
        return maxComps(this, x1, y1, z1, vector2);
    }

    public Vec3f setMaxComps(Vec3f vector1, float x2, float y2, float z2) {
        return maxComps(this, vector1, x2, y2, z2);
    }

    public Vec3f setMaxComps(Vec3f vector1, float xyz2) {
        return maxComps(this, vector1, xyz2);
    }

    public Vec3f setMaxComps(Vec3f vector1, Vec3f vector2) {
        return maxComps(this, vector1, vector2);
    }

    public Vec3f setMaxComps(float x2, float y2, float z2) {
        return this.setMaxComps(this, x2, y2, z2);
    }

    public Vec3f setMaxComps(float xyz2) {
        return this.setMaxComps(this, xyz2);
    }

    public Vec3f setMaxComps(Vec3f vector2) {
        return this.setMaxComps(this, vector2);
    }


    public boolean isShorter(float comparableX, float comparableY, float comparableZ) {
        return this.len2() < len2(comparableX, comparableY, comparableZ);
    }

    public boolean isShorter(Vec3f comparable) {
        return this.isShorter(comparable.x, comparable.y, comparable.z);
    }

    public boolean isLonger(float comparableX, float comparableY, float comparableZ) {
        return this.len2() > len2(comparableX, comparableY, comparableZ);
    }

    public boolean isLonger(Vec3f comparable) {
        return this.isLonger(comparable.x, comparable.y, comparable.z);
    }


    public boolean isZero() {
        return x == 0F && y == 0F && z == 0F;
    }

    public Vec3f zero() {
        return this.set(0F);
    }

    public Vec3f zeroCompsThatLess(float x, float y, float z) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0F;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0F;
        if(Math.abs(this.z) < Math.abs(z))
            this.z = 0F;
        return this;
    }

    public Vec3f zeroCompsThatLess(double x, double y, double z) {
        return this.zeroCompsThatLess((float) x, (float) y, (float) z);
    }

    public Vec3f zeroCompsThatLess(int x, int y, int z) {
        return this.zeroCompsThatLess((float) x, (float) y, (float) z);
    }

    public Vec3f zeroCompsThatLess(float xyz) {
        return this.zeroCompsThatLess(xyz, xyz, xyz);
    }

    public Vec3f zeroCompsThatLess(double xyz) {
        return this.zeroCompsThatLess(xyz, xyz, xyz);
    }

    public Vec3f zeroCompsThatLess(int xyz) {
        return this.zeroCompsThatLess(xyz, xyz, xyz);
    }

    public Vec3f zeroCompsThatLess(Vec3f vector) {
        return this.zeroCompsThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroCompsThatLess(Vec3d vector) {
        return this.zeroCompsThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroCompsThatLess(Vec3i vector) {
        return this.zeroCompsThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroCompsThatZero(float x, float y, float z) {
        if(x == 0F)
            this.x = 0F;
        if(y == 0F)
            this.y = 0F;
        if(z == 0F)
            this.z = 0F;
        return this;
    }

    public Vec3f zeroCompsThatZero(double x, double y, double z) {
        return this.zeroCompsThatZero((float) x, (float) y, (float) z);
    }

    public Vec3f zeroCompsThatZero(int x, int y, int z) {
        return this.zeroCompsThatZero((float) x, (float) y, (float) z);
    }

    public Vec3f zeroCompsThatZero(float xyz) {
        return this.zeroCompsThatZero(xyz, xyz, xyz);
    }

    public Vec3f zeroCompsThatZero(double xyz) {
        return this.zeroCompsThatZero(xyz, xyz, xyz);
    }

    public Vec3f zeroCompsThatZero(int xyz) {
        return this.zeroCompsThatZero(xyz, xyz, xyz);
    }

    public Vec3f zeroCompsThatZero(Vec3f vector) {
        return this.zeroCompsThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroCompsThatZero(Vec3d vector) {
        return this.zeroCompsThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroCompsThatZero(Vec3i vector) {
        return this.zeroCompsThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroCompsThatBigger(float x, float y, float z) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0F;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0F;
        if(Math.abs(this.z) > Math.abs(z))
            this.z = 0F;
        return this;
    }

    public Vec3f zeroCompsThatBigger(double x, double y, double z) {
        return this.zeroCompsThatBigger((float) x, (float) y, (float) z);
    }

    public Vec3f zeroCompsThatBigger(int x, int y, int z) {
        return this.zeroCompsThatBigger((float) x, (float) y, (float) z);
    }

    public Vec3f zeroCompsThatBigger(float xyz) {
        return this.zeroCompsThatBigger(xyz, xyz, xyz);
    }

    public Vec3f zeroCompsThatBigger(double xyz) {
        return this.zeroCompsThatBigger(xyz, xyz, xyz);
    }

    public Vec3f zeroCompsThatBigger(int xyz) {
        return this.zeroCompsThatBigger(xyz, xyz, xyz);
    }

    public Vec3f zeroCompsThatBigger(Vec3f vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroCompsThatBigger(Vec3d vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroCompsThatBigger(Vec3i vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y, vector.z);
    }


    public float volume() {
        return x * y * z;
    }


    public static float len2(float x, float y, float z) {
        return x * x + y * y + z * z;
    }

    public static float len2(Vec3f vector) {
        return len2(vector.x, vector.y, vector.z);
    }

    public static float len(float x, float y, float z) {
        return Mathc.sqrt(len2(x, y, z));
    }

    public static float len(Vec3f vector) {
        return Mathc.sqrt(len2(vector.x, vector.y, vector.z));
    }

    public float len2() {
        return len2(this);
    }

    public float len() {
        return len(this);
    }

    public static float lenh2(Vec3f vector) {
        return vector.x * vector.x + vector.z * vector.z;
    }

    public static float lenh(Vec3f vector) {
        return Mathc.sqrt(lenh2(vector));
    }

    public float lenh2() {
        return lenh2(this);
    }

    public float lenh() {
        return lenh(this);
    }


    public Vec3f nor() {
        float len = this.len2();
        if(len == 0F || len == 1F)
            return this;
        
        len = 1F / Mathc.sqrt(len);
        return this.mul(len);
    }


    public Vec3f abs() {
        if(x < 0F) x *= -1F;
        if(y < 0F) y *= -1F;
        if(z < 0F) z *= -1F;
        return this;
    }


    public static Vec3f lerp(Vec3f dst, float startX, float startY, float startZ, float endX, float endY, float endZ, float t) {
        return dst.set(
            Maths.lerp(startX, endX, t),
            Maths.lerp(startY, endY, t),
            Maths.lerp(startZ, endZ, t)
        );
    }

    public static Vec3f lerp(Vec3f dst, Vec3f start, Vec3f end, float t) {
        return lerp(dst, start.x, start.y, start.z, end.x, end.y, end.z, t);
    }

    public Vec3f lerp(float startX, float startY, float startZ, float endX, float endY, float endZ, float t) {
        return lerp(this, startX, startY, startZ, endX, endY, endZ, t);
    }

    public Vec3f lerp(Vec3f start, Vec3f end, float t) {
        return lerp(this, start, end, t);
    }


    public static float dot(float x1, float y1, float z1, float x2, float y2, float z2) {
        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    public static float dot(Vec3f vector1, float x2, float y2, float z2) {
        return dot(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static float dot(float x1, float y1, float z1, Vec3f vector2) {
        return dot(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static float dot(Vec3f vector1, Vec3f vector2) {
        return dot(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public float dot(Vec3f vector) {
        return dot(this, vector);
    }

    public float dot(float x, float y, float z) {
        return dot(this, x, y, z);
    }


    public static Vec3f crs(Vec3f dst, float x1, float y1, float z1, float x2, float y2, float z2) {
        return dst.set((y1 * z2 - z1 * y2), (z1 * x2 - x1 * z2), (x1 * y2 - y1 * x2));
    }

    public static Vec3f crs(Vec3f dst, Vec3f vector1, float x2, float y2, float z2) {
        return crs(dst, vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static Vec3f crs(Vec3f dst, float x1, float y1, float z1, Vec3f vector2) {
        return crs(dst, x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static Vec3f crs(Vec3f dst, Vec3f vector1, Vec3f vector2) {
        return crs(dst, vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public Vec3f crs(Vec3f dst, Vec3f vector) {
        return crs(dst, this, vector);
    }

    public Vec3f crs(Vec3f dst, float x, float y, float z) {
        return crs(dst, this, x, y, z);
    }

    public Vec3f setCrs(float x1, float y1, float z1, float x2, float y2, float z2) {
        return crs(this, x1, y1, z1, x2, y2, z2);
    }

    public Vec3f setCrs(Vec3f vector1, float x2, float y2, float z2) {
        return this.setCrs(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public Vec3f setCrs(float x1, float y1, float z1, Vec3f vector2) {
        return this.setCrs(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public Vec3f setCrs(Vec3f vector1, Vec3f vector2) {
        return this.setCrs(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }


    public Vec3f frac() {
        x = Maths.frac(x);
        y = Maths.frac(y);
        z = Maths.frac(z);
        return this;
    }


    public Vec3i signum(Vec3i dst) {
        return dst.set(Math.signum(x), Math.signum(y), Math.signum(z));
    }


    public static float angleBetweenRad(float x1, float y1, float z1, float x2, float y2, float z2) {
        final float lengthProduct = len(x1, y1, z1) * len(x2, y2, z2);
        if(lengthProduct == 0F)
            return 0F;
        final float cos = dot(x1, y1, z1, x2, y2, z2) / lengthProduct;
        return Mathc.acos(Maths.clamp(cos, -1F, 1F));
    }

    public static float angleBetweenRad(Vec3f vector1, float x2, float y2, float z2) {
        return angleBetweenRad(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static float angleBetweenRad(float x1, float y1, float z1, Vec3f vector2) {
        return angleBetweenRad(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static float angleBetweenRad(Vec3f vector1, Vec3f vector2) {
        return angleBetweenRad(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public float angleBetweenRad(float x, float y, float z) {
        return angleBetweenRad(this, x, y, z);
    }

    public float angleBetweenRad(Vec3f vector) {
        return angleBetweenRad(this, vector);
    }

    public static float angleBetween(float x1, float y1, float z1, float x2, float y2, float z2) {
        return angleBetweenRad(x1, y1, z1, x2, y2, z2) * Maths.toDeg;
    }

    public static float angleBetween(Vec3f vector1, float x2, float y2, float z2) {
        return angleBetween(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static float angleBetween(float x1, float y1, float z1, Vec3f vector2) {
        return angleBetween(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static float angleBetween(Vec3f vector1, Vec3f vector2) {
        return angleBetween(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public float angleBetween(float x, float y, float z) {
        return angleBetween(this, x, y, z);
    }

    public float angleBetween(Vec3f vector) {
        return angleBetween(this, vector);
    }


    public Vec3f rotateRadX(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return this.setYZ((y * cos + z * sin), (y * -sin + z * cos));
    }

    public Vec3f rotateRadY(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return this.setXZ((x * cos - z * sin), (x * sin + z * cos));
    }

    public Vec3f rotateRadZ(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return this.setXY((x * cos + y * sin), (x * -sin + y * cos));
    }

    public Vec3f rotateX(double degrees) {
        return this.rotateRadX(degrees * Maths.toRad);
    }

    public Vec3f rotateY(double degrees) {
        return this.rotateRadY(degrees * Maths.toRad);
    }

    public Vec3f rotateZ(double degrees) {
        return this.rotateRadZ(degrees * Maths.toRad);
    }


    public Vec3f mulMat4(float[] matrix) {
        return this.set(
            x * matrix[Matrix4.m00] + y * matrix[Matrix4.m10] + z * matrix[Matrix4.m20] + matrix[Matrix4.m30],
            x * matrix[Matrix4.m01] + y * matrix[Matrix4.m11] + z * matrix[Matrix4.m21] + matrix[Matrix4.m31],
            x * matrix[Matrix4.m02] + y * matrix[Matrix4.m12] + z * matrix[Matrix4.m22] + matrix[Matrix4.m32]
        );
    }

    public Vec3f mulMat3(float[] matrix) {
        return this.set(
            x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + z * matrix[Matrix3.m20],
            x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + z * matrix[Matrix3.m21],
            x * matrix[Matrix3.m02] + y * matrix[Matrix3.m12] + z * matrix[Matrix3.m22]
        );
    }

    public Vec3f mulMat4(Matrix4f matrix) {
        return this.mulMat4(matrix.val);
    }

    public Vec3f mulMat3(Matrix3f matrix) {
        return this.mulMat3(matrix.val);
    }


    public int xFloor() {
        return Maths.floor(x);
    }

    public int xRound() {
        return Maths.round(x);
    }

    public int xCeil() {
        return Maths.ceil(x);
    }

    public int yFloor() {
        return Maths.floor(y);
    }

    public int yRound() {
        return Maths.round(y);
    }

    public int yCeil() {
        return Maths.ceil(y);
    }

    public int zFloor() {
        return Maths.floor(z);
    }

    public int zRound() {
        return Maths.round(z);
    }

    public int zCeil() {
        return Maths.ceil(z);
    }


    public Vec3f floor() {
        x = Maths.floor(x);
        y = Maths.floor(y);
        z = Maths.floor(z);
        return this;
    }

    public Vec3f round() {
        x = Maths.round(x);
        y = Maths.round(y);
        z = Maths.round(z);
        return this;
    }

    public Vec3f ceil() {
        x = Maths.ceil(x);
        y = Maths.ceil(y);
        z = Maths.ceil(z);
        return this;
    }


    public Vec3d castDouble() {
        return new Vec3d(this);
    }

    public Vec3i castInt() {
        return new Vec3i(this);
    }


    public Vec2f xy() {
        return new Vec2f(x, y);
    }

    public Vec2f xz() {
        return new Vec2f(x, z);
    }

    public Vec2f yz() {
        return new Vec2f(y, z);
    }


    public Vec3f clamp(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        return this.set(
            Maths.clamp(x, minX, maxX),
            Maths.clamp(y, minY, maxY),
            Maths.clamp(z, minZ, maxZ)
        );
    }

    public Vec3f clamp(float minX, float minY, float minZ, Vec3f max) {
        return this.clamp(minX, minY, minZ, max.x, max.y, max.z);
    }

    public Vec3f clamp(float minXYZ, float maxXYZ) {
        return this.clamp(minXYZ, minXYZ, minXYZ, maxXYZ, maxXYZ, maxXYZ);
    }

    public Vec3f clamp(float minXYZ, Vec3f max) {
        return this.clamp(minXYZ, minXYZ, minXYZ, max.x, max.y, max.z);
    }

    public Vec3f clamp(Vec3f min, float maxX, float maxY, float maxZ) {
        return this.clamp(min.x, min.y, min.z, maxX, maxY, maxZ);
    }

    public Vec3f clamp(Vec3f min, float maxXYZ) {
        return this.clamp(min.x, min.y, min.z, maxXYZ, maxXYZ, maxXYZ);
    }

    public Vec3f clamp(Vec3f min, Vec3f max) {
        return this.clamp(min.x, min.y, min.z, max.x, max.y, max.z);
    }


    public Vec3f clampLength(double max) {
        final float len = this.len();
        if(len <= max)
            return this;
        return this.nor().mul(max);
    }


    public Vec3f reduce(double reduceX, double reduceY, double reduceZ) {
        final float len = this.len();
        return this.nor().mul(len - reduceX, len - reduceY, len - reduceZ);
    }


    public Vec3f copy() {
        return new Vec3f(this);
    }

    public static boolean equals(float x1, float y1, float z1, float x2, float y2, float z2) {
        return x1 == x2 && y1 == y2 && z1 == z2;
    }

    public static boolean notEquals(float x1, float y1, float z1, float x2, float y2, float z2) {
        return x1 != x2 || y1 != y2 || z1 != z2;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        
        final Vec3f vec = (Vec3f) object;
        return Vec3f.equals(x, y, z, vec.x, vec.y, vec.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + ", " + z + "}";
    }

}