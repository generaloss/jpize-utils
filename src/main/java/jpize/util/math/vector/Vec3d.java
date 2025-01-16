package jpize.util.math.vector;

import jpize.util.math.Maths;
import jpize.util.math.matrix.*;
import java.util.Objects;

public class Vec3d {

    public double x;
    public double y;
    public double z;

    public Vec3d() { }

    public Vec3d(float xyz) {
        this.set(xyz);
    }

    public Vec3d(double xyz) {
        this.set(xyz);
    }

    public Vec3d(int xyz) {
        this.set(xyz);
    }

    public Vec3d(float x, float y) {
        this.set(x, y);
    }

    public Vec3d(double x, double y) {
        this.set(x, y);
    }

    public Vec3d(int x, int y) {
        this.set(x, y);
    }

    public Vec3d(float x, float y, float z) {
        this.set(x, y, z);
    }

    public Vec3d(double x, double y, double z) {
        this.set(x, y, z);
    }

    public Vec3d(int x, int y, int z) {
        this.set(x, y, z);
    }

    public Vec3d(Vec2f vector) {
        this.set(vector.x, vector.y);
    }

    public Vec3d(Vec2d vector) {
        this.set(vector.x, vector.y);
    }

    public Vec3d(Vec2i vector) {
        this.set(vector.x, vector.y);
    }

    public Vec3d(Vec3f vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec3d(Vec3d vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec3d(Vec3i vector) {
        this.set(vector.x, vector.y, vector.z);
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }


    public Vec3d set(double xyz) {
        this.x = xyz;
        this.y = xyz;
        this.z = xyz;
        return this;
    }

    public Vec3d set(float xyz) {
        return this.set((double) xyz);
    }

    public Vec3d set(int xyz) {
        return this.set((double) xyz);
    }

    public Vec3d set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec3d set(float x, float y) {
        return this.set((double) x, y);
    }

    public Vec3d set(int x, int y) {
        return this.set((double) x, y);
    }

    public Vec3d set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3d set(float x, float y, float z) {
        return this.set((double) x, y, z);
    }

    public Vec3d set(int x, int y, int z) {
        return this.set((double) x, y, z);
    }

    public Vec3d set(Vec2f vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec3d set(Vec2d vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec3d set(Vec2i vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec3d set(Vec3f vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    public Vec3d set(Vec3d vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    public Vec3d set(Vec3i vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    public Vec3d setXY(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec3d setXY(float x, float y) {
        return setXY((double) x, y);
    }

    public Vec3d setXY(int x, int y) {
        return setXY((double) x, y);
    }

    public Vec3d setXZ(double x, double z) {
        this.x = x;
        this.z = z;
        return this;
    }

    public Vec3d setXZ(float x, float z) {
        return setXZ((double) x, z);
    }

    public Vec3d setXZ(int x, int z) {
        return setXZ((double) x, z);
    }

    public Vec3d setYZ(double y, double z) {
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3d setYZ(float y, float z) {
        return setYZ((double) y, z);
    }

    public Vec3d setYZ(int y, int z) {
        return setYZ((double) y, z);
    }

    public Vec3d setXY(Vec2f vector) {
        return setXY(vector.x, vector.y);
    }

    public Vec3d setXY(Vec2d vector) {
        return setXY(vector.x, vector.y);
    }

    public Vec3d setXY(Vec2i vector) {
        return setXY(vector.x, vector.y);
    }

    public Vec3d setXZ(Vec2f vector) {
        return setXZ(vector.x, vector.y);
    }

    public Vec3d setXZ(Vec2d vector) {
        return setXZ(vector.x, vector.y);
    }

    public Vec3d setXZ(Vec2i vector) {
        return setXZ(vector.x, vector.y);
    }

    public Vec3d setYZ(Vec2f vector) {
        return setYZ(vector.x, vector.y);
    }

    public Vec3d setYZ(Vec2d vector) {
        return setYZ(vector.x, vector.y);
    }

    public Vec3d setYZ(Vec2i vector) {
        return setYZ(vector.x, vector.y);
    }


    public Vec3d add(double xyz) {
        this.x += xyz;
        this.y += xyz;
        this.z += xyz;
        return this;
    }

    public Vec3d add(float xyz) {
        return this.add((double) xyz);
    }

    public Vec3d add(int xyz) {
        return this.add((double) xyz);
    }

    public Vec3d add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec3d add(float x, float y) {
        return this.add((double) x, y);
    }

    public Vec3d add(int x, int y) {
        return this.add((double) x, y);
    }

    public Vec3d add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3d add(float x, float y, float z) {
        return this.add((double) x, y, z);
    }

    public Vec3d add(int x, int y, int z) {
        return this.add((double) x, y, z);
    }

    public Vec3d add(Vec2f vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec3d add(Vec2d vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec3d add(Vec2i vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec3d add(Vec3f vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vec3d add(Vec3d vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vec3d add(Vec3i vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vec3d addXY(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec3d addXY(float x, float y) {
        return addXY((double) x, y);
    }

    public Vec3d addXY(int x, int y) {
        return addXY((double) x, y);
    }

    public Vec3d addXZ(double x, double z) {
        this.x += x;
        this.z += z;
        return this;
    }

    public Vec3d addXZ(float x, float z) {
        return addXZ((double) x, z);
    }

    public Vec3d addXZ(int x, int z) {
        return addXZ((double) x, z);
    }

    public Vec3d addYZ(double y, double z) {
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3d addYZ(float y, float z) {
        return addYZ((double) y, z);
    }

    public Vec3d addYZ(int y, int z) {
        return addYZ((double) y, z);
    }

    public Vec3d addXY(Vec2f vector) {
        return addXY(vector.x, vector.y);
    }

    public Vec3d addXY(Vec2d vector) {
        return addXY(vector.x, vector.y);
    }

    public Vec3d addXY(Vec2i vector) {
        return addXY(vector.x, vector.y);
    }

    public Vec3d addXZ(Vec2f vector) {
        return addXZ(vector.x, vector.y);
    }

    public Vec3d addXZ(Vec2d vector) {
        return addXZ(vector.x, vector.y);
    }

    public Vec3d addXZ(Vec2i vector) {
        return addXZ(vector.x, vector.y);
    }

    public Vec3d addYZ(Vec2f vector) {
        return addYZ(vector.x, vector.y);
    }

    public Vec3d addYZ(Vec2d vector) {
        return addYZ(vector.x, vector.y);
    }

    public Vec3d addYZ(Vec2i vector) {
        return addYZ(vector.x, vector.y);
    }


    public Vec3d sub(double xyz) {
        this.x -= xyz;
        this.y -= xyz;
        this.z -= xyz;
        return this;
    }

    public Vec3d sub(float xyz) {
        return this.sub((double) xyz);
    }

    public Vec3d sub(int xyz) {
        return this.sub((double) xyz);
    }

    public Vec3d sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec3d sub(float x, float y) {
        return this.sub((double) x, y);
    }

    public Vec3d sub(int x, int y) {
        return this.sub((double) x, y);
    }

    public Vec3d sub(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3d sub(float x, float y, float z) {
        return this.sub((double) x, y, z);
    }

    public Vec3d sub(int x, int y, int z) {
        return this.sub((double) x, y, z);
    }

    public Vec3d sub(Vec2f vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec3d sub(Vec2d vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec3d sub(Vec2i vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec3d sub(Vec3f vector) {
        return this.sub(vector.x, vector.y, vector.z);
    }

    public Vec3d sub(Vec3d vector) {
        return this.sub(vector.x, vector.y, vector.z);
    }

    public Vec3d sub(Vec3i vector) {
        return this.sub(vector.x, vector.y, vector.z);
    }

    public Vec3d subXY(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec3d subXY(float x, float y) {
        return subXY((double) x, y);
    }

    public Vec3d subXY(int x, int y) {
        return subXY((double) x, y);
    }

    public Vec3d subXZ(double x, double z) {
        this.x -= x;
        this.z -= z;
        return this;
    }

    public Vec3d subXZ(float x, float z) {
        return subXZ((double) x, z);
    }

    public Vec3d subXZ(int x, int z) {
        return subXZ((double) x, z);
    }

    public Vec3d subYZ(double y, double z) {
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3d subYZ(float y, float z) {
        return subYZ((double) y, z);
    }

    public Vec3d subYZ(int y, int z) {
        return subYZ((double) y, z);
    }

    public Vec3d subXY(Vec2f vector) {
        return subXY(vector.x, vector.y);
    }

    public Vec3d subXY(Vec2d vector) {
        return subXY(vector.x, vector.y);
    }

    public Vec3d subXY(Vec2i vector) {
        return subXY(vector.x, vector.y);
    }

    public Vec3d subXZ(Vec2f vector) {
        return subXZ(vector.x, vector.y);
    }

    public Vec3d subXZ(Vec2d vector) {
        return subXZ(vector.x, vector.y);
    }

    public Vec3d subXZ(Vec2i vector) {
        return subXZ(vector.x, vector.y);
    }

    public Vec3d subYZ(Vec2f vector) {
        return subYZ(vector.x, vector.y);
    }

    public Vec3d subYZ(Vec2d vector) {
        return subYZ(vector.x, vector.y);
    }

    public Vec3d subYZ(Vec2i vector) {
        return subYZ(vector.x, vector.y);
    }


    public Vec3d mul(double xyz) {
        this.x *= xyz;
        this.y *= xyz;
        this.z *= xyz;
        return this;
    }

    public Vec3d mul(float xyz) {
        return this.mul((double) xyz);
    }

    public Vec3d mul(int xyz) {
        return this.mul((double) xyz);
    }

    public Vec3d mul(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec3d mul(float x, float y) {
        return this.mul((double) x, y);
    }

    public Vec3d mul(int x, int y) {
        return this.mul((double) x, y);
    }

    public Vec3d mul(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3d mul(float x, float y, float z) {
        return this.mul((double) x, y, z);
    }

    public Vec3d mul(int x, int y, int z) {
        return this.mul((double) x, y, z);
    }

    public Vec3d mul(Vec2f vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec3d mul(Vec2d vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec3d mul(Vec2i vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec3d mul(Vec3f vector) {
        return this.mul(vector.x, vector.y, vector.z);
    }

    public Vec3d mul(Vec3d vector) {
        return this.mul(vector.x, vector.y, vector.z);
    }

    public Vec3d mul(Vec3i vector) {
        return this.mul(vector.x, vector.y, vector.z);
    }

    public Vec3d mulXY(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec3d mulXY(float x, float y) {
        return mulXY((double) x, y);
    }

    public Vec3d mulXY(int x, int y) {
        return mulXY((double) x, y);
    }

    public Vec3d mulXZ(double x, double z) {
        this.x *= x;
        this.z *= z;
        return this;
    }

    public Vec3d mulXZ(float x, float z) {
        return mulXZ((double) x, z);
    }

    public Vec3d mulXZ(int x, int z) {
        return mulXZ((double) x, z);
    }

    public Vec3d mulYZ(double y, double z) {
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3d mulYZ(float y, float z) {
        return mulYZ((double) y, z);
    }

    public Vec3d mulYZ(int y, int z) {
        return mulYZ((double) y, z);
    }

    public Vec3d mulXY(Vec2f vector) {
        return mulXY(vector.x, vector.y);
    }

    public Vec3d mulXY(Vec2d vector) {
        return mulXY(vector.x, vector.y);
    }

    public Vec3d mulXY(Vec2i vector) {
        return mulXY(vector.x, vector.y);
    }

    public Vec3d mulXZ(Vec2f vector) {
        return mulXZ(vector.x, vector.y);
    }

    public Vec3d mulXZ(Vec2d vector) {
        return mulXZ(vector.x, vector.y);
    }

    public Vec3d mulXZ(Vec2i vector) {
        return mulXZ(vector.x, vector.y);
    }

    public Vec3d mulYZ(Vec2f vector) {
        return mulYZ(vector.x, vector.y);
    }

    public Vec3d mulYZ(Vec2d vector) {
        return mulYZ(vector.x, vector.y);
    }

    public Vec3d mulYZ(Vec2i vector) {
        return mulYZ(vector.x, vector.y);
    }


    public Vec3d div(double xyz) {
        this.x /= xyz;
        this.y /= xyz;
        this.z /= xyz;
        return this;
    }

    public Vec3d div(float xyz) {
        return this.div((double) xyz);
    }

    public Vec3d div(int xyz) {
        return this.div((double) xyz);
    }

    public Vec3d div(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec3d div(float x, float y) {
        return this.div((double) x, y);
    }

    public Vec3d div(int x, int y) {
        return this.div((double) x, y);
    }

    public Vec3d div(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3d div(float x, float y, float z) {
        return this.div((double) x, y, z);
    }

    public Vec3d div(int x, int y, int z) {
        return this.div((double) x, y, z);
    }

    public Vec3d div(Vec2f vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec3d div(Vec2d vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec3d div(Vec2i vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec3d div(Vec3f vector) {
        return this.div(vector.x, vector.y, vector.z);
    }

    public Vec3d div(Vec3d vector) {
        return this.div(vector.x, vector.y, vector.z);
    }

    public Vec3d div(Vec3i vector) {
        return this.div(vector.x, vector.y, vector.z);
    }

    public Vec3d divXY(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec3d divXY(float x, float y) {
        return divXY((double) x, y);
    }

    public Vec3d divXY(int x, int y) {
        return divXY((double) x, y);
    }

    public Vec3d divXZ(double x, double z) {
        this.x /= x;
        this.z /= z;
        return this;
    }

    public Vec3d divXZ(float x, float z) {
        return divXZ((double) x, z);
    }

    public Vec3d divXZ(int x, int z) {
        return divXZ((double) x, z);
    }

    public Vec3d divYZ(double y, double z) {
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3d divYZ(float y, float z) {
        return divYZ((double) y, z);
    }

    public Vec3d divYZ(int y, int z) {
        return divYZ((double) y, z);
    }

    public Vec3d divXY(Vec2f vector) {
        return divXY(vector.x, vector.y);
    }

    public Vec3d divXY(Vec2d vector) {
        return divXY(vector.x, vector.y);
    }

    public Vec3d divXY(Vec2i vector) {
        return divXY(vector.x, vector.y);
    }

    public Vec3d divXZ(Vec2f vector) {
        return divXZ(vector.x, vector.y);
    }

    public Vec3d divXZ(Vec2d vector) {
        return divXZ(vector.x, vector.y);
    }

    public Vec3d divXZ(Vec2i vector) {
        return divXZ(vector.x, vector.y);
    }

    public Vec3d divYZ(Vec2f vector) {
        return divYZ(vector.x, vector.y);
    }

    public Vec3d divYZ(Vec2d vector) {
        return divYZ(vector.x, vector.y);
    }

    public Vec3d divYZ(Vec2i vector) {
        return divYZ(vector.x, vector.y);
    }


    public static double dst2(double x1, double y1, double z1, double x2, double y2, double z2) {
        final double dx = (x2 - x1);
        final double dy = (y2 - y1);
        final double dz = (z2 - z1);
        return (dx * dx + dy * dy + dz * dz);
    }

    public static double dst2(double x1, double y1, double z1, float x2, float y2, float z2) {
        return dst2(x1, y1, z1, (double) x2, y2, z2);
    }

    public static double dst2(double x1, double y1, double z1, int x2, int y2, int z2) {
        return dst2(x1, y1, z1, (double) x2, y2, z2);
    }

    public static double dst2(double x, double y, double z, Vec3f vector) {
        return dst2(x, y, z, vector.x, vector.y, vector.z);
    }

    public static double dst2(double x, double y, double z, Vec3d vector) {
        return dst2(x, y, z, vector.x, vector.y, vector.z);
    }

    public static double dst2(double x, double y, double z, Vec3i vector) {
        return dst2(x, y, z, vector.x, vector.y, vector.z);
    }

    public static double dst2(Vec3d vector, float x, float y, float z) {
        return dst2(vector.x, vector.y, vector.z, x, y, z);
    }

    public static double dst2(Vec3d vector, double x, double y, double z) {
        return dst2(vector.x, vector.y, vector.z, x, y, z);
    }

    public static double dst2(Vec3d vector, int x, int y, int z) {
        return dst2(vector.x, vector.y, vector.z, x, y, z);
    }

    public static double dst2(Vec3d vector1, Vec3f vector2) {
        return dst2(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static double dst2(Vec3d vector1, Vec3d vector2) {
        return dst2(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static double dst2(Vec3d vector1, Vec3i vector2) {
        return dst2(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public double dst2(float x, float y, float z) {
        return dst2(this, x, y, z);
    }

    public double dst2(double x, double y, double z) {
        return dst2(this, x, y, z);
    }

    public double dst2(int x, int y, int z) {
        return dst2(this, x, y, z);
    }

    public double dst2(Vec3f vector) {
        return dst2(this, vector);
    }

    public double dst2(Vec3d vector) {
        return dst2(this, vector);
    }

    public double dst2(Vec3i vector) {
        return dst2(this, vector);
    }


    public static double dst(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(dst2(x1, y1, z1, x2, y2, z2));
    }

    public static double dst(double x1, double y1, double z1, float x2, float y2, float z2) {
        return dst(x1, y1, z1, (double) x2, y2, z2);
    }

    public static double dst(double x1, double y1, double z1, int x2, int y2, int z2) {
        return dst(x1, y1, z1, (double) x2, y2, z2);
    }

    public static double dst(double x, double y, double z, Vec3f vector) {
        return dst(x, y, z, vector.x, vector.y, vector.z);
    }

    public static double dst(double x, double y, double z, Vec3d vector) {
        return dst(x, y, z, vector.x, vector.y, vector.z);
    }

    public static double dst(double x, double y, double z, Vec3i vector) {
        return dst(x, y, z, vector.x, vector.y, vector.z);
    }

    public static double dst(Vec3d vector, float x, float y, float z) {
        return dst(vector.x, vector.y, vector.z, x, y, z);
    }

    public static double dst(Vec3d vector, double x, double y, double z) {
        return dst(vector.x, vector.y, vector.z, x, y, z);
    }

    public static double dst(Vec3d vector, int x, int y, int z) {
        return dst(vector.x, vector.y, vector.z, x, y, z);
    }

    public static double dst(Vec3d vector1, Vec3f vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static double dst(Vec3d vector1, Vec3d vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static double dst(Vec3d vector1, Vec3i vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public double dst(float x, float y, float z) {
        return dst(this, x, y, z);
    }

    public double dst(double x, double y, double z) {
        return dst(this, x, y, z);
    }

    public double dst(int x, int y, int z) {
        return dst(this, x, y, z);
    }

    public double dst(Vec3f vector) {
        return dst(this, vector);
    }

    public double dst(Vec3d vector) {
        return dst(this, vector);
    }

    public double dst(Vec3i vector) {
        return dst(this, vector);
    }


    public static Vec3d shorter(Vec3d vector1, Vec3d vector2) {
        return (vector1.len2() < vector2.len2()) ? vector1 : vector2;
    }

    public static Vec3d longer(Vec3d vector1, Vec3d vector2) {
        return (vector1.len2() > vector2.len2()) ? vector1 : vector2;
    }

    public Vec3d setShorter(Vec3d vector1, Vec3d vector2) {
        return this.set(shorter(vector1, vector2));
    }

    public Vec3d setLonger(Vec3d vector1, Vec3d vector2) {
        return this.set(longer(vector1, vector2));
    }


    public double minComp() {
        return Math.min(x, Math.min(y, z));
    }

    public double maxComp() {
        return Math.max(x, Math.max(y, z));
    }


    public static Vec3d minComps(Vec3d dst, double x1, double y1, double z1, double x2, double y2, double z2) {
        return dst.set(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));
    }

    public static Vec3d minComps(Vec3d dst, double x1, double y1, double z1, Vec3d vector2) {
        return minComps(dst, x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static Vec3d minComps(Vec3d dst, Vec3d vector1, double x2, double y2, double z2) {
        return minComps(dst, vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static Vec3d minComps(Vec3d dst, Vec3d vector1, double xyz2) {
        return minComps(dst, vector1.x, vector1.y, vector1.z, xyz2, xyz2, xyz2);
    }

    public static Vec3d minComps(Vec3d dst, Vec3d vector1, Vec3d vector2) {
        return minComps(dst, vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public Vec3d setMinComps(double x1, double y1, double z1, double x2, double y2, double z2) {
        return minComps(this, x1, y1, z1, x2, y2, z2);
    }

    public Vec3d setMinComps(double x1, double y1, double z1, Vec3d vector2) {
        return minComps(this, x1, y1, z1, vector2);
    }

    public Vec3d setMinComps(Vec3d vector1, double x2, double y2, double z2) {
        return minComps(this, vector1, x2, y2, z2);
    }

    public Vec3d setMinComps(Vec3d vector1, double xyz2) {
        return minComps(this, vector1, xyz2);
    }

    public Vec3d setMinComps(Vec3d vector1, Vec3d vector2) {
        return minComps(this, vector1, vector2);
    }

    public Vec3d setMinComps(double x2, double y2, double z2) {
        return this.setMinComps(this, x2, y2, z2);
    }

    public Vec3d setMinComps(double xyz2) {
        return this.setMinComps(this, xyz2);
    }

    public Vec3d setMinComps(Vec3d vector2) {
        return this.setMinComps(this, vector2);
    }


    public static Vec3d maxComps(Vec3d dst, double x1, double y1, double z1, double x2, double y2, double z2) {
        return dst.set(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
    }

    public static Vec3d maxComps(Vec3d dst, double x1, double y1, double z1, Vec3d vector2) {
        return maxComps(dst, x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static Vec3d maxComps(Vec3d dst, Vec3d vector1, double x2, double y2, double z2) {
        return maxComps(dst, vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static Vec3d maxComps(Vec3d dst, Vec3d vector1, double xyz2) {
        return maxComps(dst, vector1.x, vector1.y, vector1.z, xyz2, xyz2, xyz2);
    }

    public static Vec3d maxComps(Vec3d dst, Vec3d vector1, Vec3d vector2) {
        return maxComps(dst, vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public Vec3d setMaxComps(double x1, double y1, double z1, double x2, double y2, double z2) {
        return maxComps(this, x1, y1, z1, x2, y2, z2);
    }

    public Vec3d setMaxComps(double x1, double y1, double z1, Vec3d vector2) {
        return maxComps(this, x1, y1, z1, vector2);
    }

    public Vec3d setMaxComps(Vec3d vector1, double x2, double y2, double z2) {
        return maxComps(this, vector1, x2, y2, z2);
    }

    public Vec3d setMaxComps(Vec3d vector1, double xyz2) {
        return maxComps(this, vector1, xyz2);
    }

    public Vec3d setMaxComps(Vec3d vector1, Vec3d vector2) {
        return maxComps(this, vector1, vector2);
    }

    public Vec3d setMaxComps(double x2, double y2, double z2) {
        return this.setMaxComps(this, x2, y2, z2);
    }

    public Vec3d setMaxComps(double xyz2) {
        return this.setMaxComps(this, xyz2);
    }

    public Vec3d setMaxComps(Vec3d vector2) {
        return this.setMaxComps(this, vector2);
    }


    public boolean isShorter(double comparableX, double comparableY, double comparableZ) {
        return this.len2() < len2(comparableX, comparableY, comparableZ);
    }

    public boolean isShorter(Vec3d comparable) {
        return this.isShorter(comparable.x, comparable.y, comparable.z);
    }

    public boolean isLonger(double comparableX, double comparableY, double comparableZ) {
        return this.len2() > len2(comparableX, comparableY, comparableZ);
    }

    public boolean isLonger(Vec3d comparable) {
        return this.isLonger(comparable.x, comparable.y, comparable.z);
    }


    public boolean isZero() {
        return x == 0D && y == 0D && z == 0D;
    }

    public Vec3d zero() {
        return this.set(0D);
    }

    public Vec3d zeroCompsThatLess(double x, double y, double z) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0D;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0D;
        if(Math.abs(this.z) < Math.abs(z))
            this.z = 0D;
        return this;
    }

    public Vec3d zeroCompsThatLess(float x, float y, float z) {
        return this.zeroCompsThatLess((double) x, y, z);
    }

    public Vec3d zeroCompsThatLess(int x, int y, int z) {
        return this.zeroCompsThatLess((double) x, y, z);
    }

    public Vec3d zeroCompsThatLess(float xyz) {
        return this.zeroCompsThatLess(xyz, xyz, xyz);
    }

    public Vec3d zeroCompsThatLess(double xyz) {
        return this.zeroCompsThatLess(xyz, xyz, xyz);
    }

    public Vec3d zeroCompsThatLess(int xyz) {
        return this.zeroCompsThatLess(xyz, xyz, xyz);
    }

    public Vec3d zeroCompsThatLess(Vec3f vector) {
        return this.zeroCompsThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroCompsThatLess(Vec3d vector) {
        return this.zeroCompsThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroCompsThatLess(Vec3i vector) {
        return this.zeroCompsThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroCompsThatZero(double x, double y, double z) {
        if(x == 0D)
            this.x = 0D;
        if(y == 0D)
            this.y = 0D;
        if(z == 0D)
            this.z = 0D;
        return this;
    }

    public Vec3d zeroCompsThatZero(float x, float y, float z) {
        return this.zeroCompsThatZero((double) x, y, z);
    }

    public Vec3d zeroCompsThatZero(int x, int y, int z) {
        return this.zeroCompsThatZero((double) x, y, z);
    }

    public Vec3d zeroCompsThatZero(float xyz) {
        return this.zeroCompsThatZero(xyz, xyz, xyz);
    }

    public Vec3d zeroCompsThatZero(double xyz) {
        return this.zeroCompsThatZero(xyz, xyz, xyz);
    }

    public Vec3d zeroCompsThatZero(int xyz) {
        return this.zeroCompsThatZero(xyz, xyz, xyz);
    }

    public Vec3d zeroCompsThatZero(Vec3f vector) {
        return this.zeroCompsThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroCompsThatZero(Vec3d vector) {
        return this.zeroCompsThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroCompsThatZero(Vec3i vector) {
        return this.zeroCompsThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroCompsThatBigger(double x, double y, double z) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0D;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0D;
        if(Math.abs(this.z) > Math.abs(z))
            this.z = 0D;
        return this;
    }

    public Vec3d zeroCompsThatBigger(float x, float y, float z) {
        return this.zeroCompsThatBigger((double) x, y, z);
    }

    public Vec3d zeroCompsThatBigger(int x, int y, int z) {
        return this.zeroCompsThatBigger((double) x, y, z);
    }

    public Vec3d zeroCompsThatBigger(float xyz) {
        return this.zeroCompsThatBigger(xyz, xyz, xyz);
    }

    public Vec3d zeroCompsThatBigger(double xyz) {
        return this.zeroCompsThatBigger(xyz, xyz, xyz);
    }

    public Vec3d zeroCompsThatBigger(int xyz) {
        return this.zeroCompsThatBigger(xyz, xyz, xyz);
    }

    public Vec3d zeroCompsThatBigger(Vec3f vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroCompsThatBigger(Vec3d vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroCompsThatBigger(Vec3i vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y, vector.z);
    }


    public double volume() {
        return x * y * z;
    }


    public static double len2(double x, double y, double z) {
        return x * x + y * y + z * z;
    }

    public static double len2(Vec3d vector) {
        return len2(vector.x, vector.y, vector.z);
    }

    public static double len(double x, double y, double z) {
        return Math.sqrt(len2(x, y, z));
    }

    public static double len(Vec3d vector) {
        return Math.sqrt(len2(vector.x, vector.y, vector.z));
    }

    public double len2() {
        return len2(this);
    }

    public double len() {
        return len(this);
    }

    public static double lenh2(Vec3d vector) {
        return vector.x * vector.x + vector.z * vector.z;
    }

    public static double lenh(Vec3d vector) {
        return Math.sqrt(lenh2(vector));
    }

    public double lenh2() {
        return lenh2(this);
    }

    public double lenh() {
        return lenh(this);
    }


    public Vec3d nor() {
        double len = this.len2();
        if(len == 0D || len == 1D)
            return this;
        
        len = 1D / Math.sqrt(len);
        return this.mul(len);
    }


    public Vec3d abs() {
        if(x < 0D) x *= -1D;
        if(y < 0D) y *= -1D;
        if(z < 0D) z *= -1D;
        return this;
    }


    public static Vec3d lerp(Vec3d dst, double startX, double startY, double startZ, double endX, double endY, double endZ, double t) {
        return dst.set(
            Maths.lerp(startX, endX, t),
            Maths.lerp(startY, endY, t),
            Maths.lerp(startZ, endZ, t)
        );
    }

    public static Vec3d lerp(Vec3d dst, Vec3d start, Vec3d end, double t) {
        return lerp(dst, start.x, start.y, start.z, end.x, end.y, end.z, t);
    }

    public Vec3d lerp(double startX, double startY, double startZ, double endX, double endY, double endZ, double t) {
        return lerp(this, startX, startY, startZ, endX, endY, endZ, t);
    }

    public Vec3d lerp(Vec3d start, Vec3d end, double t) {
        return lerp(this, start, end, t);
    }


    public static double dot(double x1, double y1, double z1, double x2, double y2, double z2) {
        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    public static double dot(Vec3d vector1, double x2, double y2, double z2) {
        return dot(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static double dot(double x1, double y1, double z1, Vec3d vector2) {
        return dot(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static double dot(Vec3d vector1, Vec3d vector2) {
        return dot(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public double dot(Vec3d vector) {
        return dot(this, vector);
    }

    public double dot(double x, double y, double z) {
        return dot(this, x, y, z);
    }


    public static Vec3d crs(Vec3d dst, double x1, double y1, double z1, double x2, double y2, double z2) {
        return dst.set((y1 * z2 - z1 * y2), (z1 * x2 - x1 * z2), (x1 * y2 - y1 * x2));
    }

    public static Vec3d crs(Vec3d dst, Vec3d vector1, double x2, double y2, double z2) {
        return crs(dst, vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static Vec3d crs(Vec3d dst, double x1, double y1, double z1, Vec3d vector2) {
        return crs(dst, x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static Vec3d crs(Vec3d dst, Vec3d vector1, Vec3d vector2) {
        return crs(dst, vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public Vec3d crs(Vec3d dst, Vec3d vector) {
        return crs(dst, this, vector);
    }

    public Vec3d crs(Vec3d dst, double x, double y, double z) {
        return crs(dst, this, x, y, z);
    }

    public Vec3d setCrs(double x1, double y1, double z1, double x2, double y2, double z2) {
        return crs(this, x1, y1, z1, x2, y2, z2);
    }

    public Vec3d setCrs(Vec3d vector1, double x2, double y2, double z2) {
        return this.setCrs(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public Vec3d setCrs(double x1, double y1, double z1, Vec3d vector2) {
        return this.setCrs(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public Vec3d setCrs(Vec3d vector1, Vec3d vector2) {
        return this.setCrs(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }


    public Vec3d frac() {
        x = Maths.frac(x);
        y = Maths.frac(y);
        z = Maths.frac(z);
        return this;
    }


    public Vec3i signum(Vec3i dst) {
        return dst.set(Math.signum(x), Math.signum(y), Math.signum(z));
    }


    public static double angleBetweenRad(double x1, double y1, double z1, double x2, double y2, double z2) {
        final double lengthProduct = len(x1, y1, z1) * len(x2, y2, z2);
        if(lengthProduct == 0D)
            return 0D;
        final double cos = dot(x1, y1, z1, x2, y2, z2) / lengthProduct;
        return Math.acos(Maths.clamp(cos, -1D, 1D));
    }

    public static double angleBetweenRad(Vec3d vector1, double x2, double y2, double z2) {
        return angleBetweenRad(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static double angleBetweenRad(double x1, double y1, double z1, Vec3d vector2) {
        return angleBetweenRad(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static double angleBetweenRad(Vec3d vector1, Vec3d vector2) {
        return angleBetweenRad(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public double angleBetweenRad(double x, double y, double z) {
        return angleBetweenRad(this, x, y, z);
    }

    public double angleBetweenRad(Vec3d vector) {
        return angleBetweenRad(this, vector);
    }

    public static double angleBetween(double x1, double y1, double z1, double x2, double y2, double z2) {
        return angleBetweenRad(x1, y1, z1, x2, y2, z2) * Maths.toDeg;
    }

    public static double angleBetween(Vec3d vector1, double x2, double y2, double z2) {
        return angleBetween(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static double angleBetween(double x1, double y1, double z1, Vec3d vector2) {
        return angleBetween(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static double angleBetween(Vec3d vector1, Vec3d vector2) {
        return angleBetween(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public double angleBetween(double x, double y, double z) {
        return angleBetween(this, x, y, z);
    }

    public double angleBetween(Vec3d vector) {
        return angleBetween(this, vector);
    }


    public Vec3d rotateRadX(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return this.setYZ((y * cos + z * sin), (y * -sin + z * cos));
    }

    public Vec3d rotateRadY(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return this.setXZ((x * cos - z * sin), (x * sin + z * cos));
    }

    public Vec3d rotateRadZ(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return this.setXY((x * cos + y * sin), (x * -sin + y * cos));
    }

    public Vec3d rotateX(double degrees) {
        return this.rotateRadX(degrees * Maths.toRad);
    }

    public Vec3d rotateY(double degrees) {
        return this.rotateRadY(degrees * Maths.toRad);
    }

    public Vec3d rotateZ(double degrees) {
        return this.rotateRadZ(degrees * Maths.toRad);
    }


    public Vec3d mulMat4(float[] matrix) {
        return this.set(
            x * matrix[Matrix4.m00] + y * matrix[Matrix4.m10] + z * matrix[Matrix4.m20] + matrix[Matrix4.m30],
            x * matrix[Matrix4.m01] + y * matrix[Matrix4.m11] + z * matrix[Matrix4.m21] + matrix[Matrix4.m31],
            x * matrix[Matrix4.m02] + y * matrix[Matrix4.m12] + z * matrix[Matrix4.m22] + matrix[Matrix4.m32]
        );
    }

    public Vec3d mulMat3(float[] matrix) {
        return this.set(
            x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + z * matrix[Matrix3.m20],
            x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + z * matrix[Matrix3.m21],
            x * matrix[Matrix3.m02] + y * matrix[Matrix3.m12] + z * matrix[Matrix3.m22]
        );
    }

    public Vec3d mulMat4(Matrix4f matrix) {
        return this.mulMat4(matrix.val);
    }

    public Vec3d mulMat3(Matrix3f matrix) {
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


    public Vec3d floor() {
        x = Maths.floor(x);
        y = Maths.floor(y);
        z = Maths.floor(z);
        return this;
    }

    public Vec3d round() {
        x = Maths.round(x);
        y = Maths.round(y);
        z = Maths.round(z);
        return this;
    }

    public Vec3d ceil() {
        x = Maths.ceil(x);
        y = Maths.ceil(y);
        z = Maths.ceil(z);
        return this;
    }


    public Vec3f castFloat() {
        return new Vec3f(this);
    }

    public Vec3i castInt() {
        return new Vec3i(this);
    }


    public Vec2d xy() {
        return new Vec2d(x, y);
    }

    public Vec2d xz() {
        return new Vec2d(x, z);
    }

    public Vec2d yz() {
        return new Vec2d(y, z);
    }


    public Vec3d clamp(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return this.set(
            Maths.clamp(x, minX, maxX),
            Maths.clamp(y, minY, maxY),
            Maths.clamp(z, minZ, maxZ)
        );
    }

    public Vec3d clamp(double minX, double minY, double minZ, Vec3d max) {
        return this.clamp(minX, minY, minZ, max.x, max.y, max.z);
    }

    public Vec3d clamp(double minXYZ, double maxXYZ) {
        return this.clamp(minXYZ, minXYZ, minXYZ, maxXYZ, maxXYZ, maxXYZ);
    }

    public Vec3d clamp(double minXYZ, Vec3d max) {
        return this.clamp(minXYZ, minXYZ, minXYZ, max.x, max.y, max.z);
    }

    public Vec3d clamp(Vec3d min, double maxX, double maxY, double maxZ) {
        return this.clamp(min.x, min.y, min.z, maxX, maxY, maxZ);
    }

    public Vec3d clamp(Vec3d min, double maxXYZ) {
        return this.clamp(min.x, min.y, min.z, maxXYZ, maxXYZ, maxXYZ);
    }

    public Vec3d clamp(Vec3d min, Vec3d max) {
        return this.clamp(min.x, min.y, min.z, max.x, max.y, max.z);
    }


    public Vec3d clampLength(double max) {
        final double len = this.len();
        if(len <= max)
            return this;
        return this.nor().mul(max);
    }


    public Vec3d reduce(double reduceX, double reduceY, double reduceZ) {
        final double len = this.len();
        return this.nor().mul(len - reduceX, len - reduceY, len - reduceZ);
    }


    public Vec3d copy() {
        return new Vec3d(this);
    }

    public static boolean equals(double x1, double y1, double z1, double x2, double y2, double z2) {
        return x1 == x2 && y1 == y2 && z1 == z2;
    }

    public static boolean notEquals(double x1, double y1, double z1, double x2, double y2, double z2) {
        return x1 != x2 || y1 != y2 || z1 != z2;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        
        final Vec3d vec = (Vec3d) object;
        return Vec3d.equals(x, y, z, vec.x, vec.y, vec.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }

}