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


    public Vec3d set(double xyz) {
        this.x = xyz;
        this.y = xyz;
        this.z = xyz;
        return this;
    }

    public Vec3d set(float xyz) {
        return set((double) xyz);
    }

    public Vec3d set(int xyz) {
        return set((double) xyz);
    }

    public Vec3d set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec3d set(float x, float y) {
        return set((double) x, (double) y);
    }

    public Vec3d set(int x, int y) {
        return set((double) x, (double) y);
    }

    public Vec3d set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3d set(float x, float y, float z) {
        return set((double) x, (double) y, (double) z);
    }

    public Vec3d set(int x, int y, int z) {
        return set((double) x, (double) y, (double) z);
    }

    public Vec3d set(Vec2f vector) {
        return set(vector.x, vector.y);
    }

    public Vec3d set(Vec2d vector) {
        return set(vector.x, vector.y);
    }

    public Vec3d set(Vec2i vector) {
        return set(vector.x, vector.y);
    }

    public Vec3d set(Vec3f vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec3d set(Vec3d vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec3d set(Vec3i vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec3d setXY(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec3d setXY(float x, float y) {
        return setXY((double) x, (double) y);
    }

    public Vec3d setXY(int x, int y) {
        return setXY((double) x, (double) y);
    }

    public Vec3d setXZ(double x, double z) {
        this.x = x;
        this.z = z;
        return this;
    }

    public Vec3d setXZ(float x, float z) {
        return setXZ((double) x, (double) z);
    }

    public Vec3d setXZ(int x, int z) {
        return setXZ((double) x, (double) z);
    }

    public Vec3d setYZ(double y, double z) {
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3d setYZ(float y, float z) {
        return setYZ((double) y, (double) z);
    }

    public Vec3d setYZ(int y, int z) {
        return setYZ((double) y, (double) z);
    }


    public Vec3d add(double xyz) {
        this.x += xyz;
        this.y += xyz;
        this.z += xyz;
        return this;
    }

    public Vec3d add(float xyz) {
        return add((double) xyz);
    }

    public Vec3d add(int xyz) {
        return add((double) xyz);
    }

    public Vec3d add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec3d add(float x, float y) {
        return add((double) x, (double) y);
    }

    public Vec3d add(int x, int y) {
        return add((double) x, (double) y);
    }

    public Vec3d add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3d add(float x, float y, float z) {
        return add((double) x, (double) y, (double) z);
    }

    public Vec3d add(int x, int y, int z) {
        return add((double) x, (double) y, (double) z);
    }

    public Vec3d add(Vec2f vector) {
        return add(vector.x, vector.y);
    }

    public Vec3d add(Vec2d vector) {
        return add(vector.x, vector.y);
    }

    public Vec3d add(Vec2i vector) {
        return add(vector.x, vector.y);
    }

    public Vec3d add(Vec3f vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec3d add(Vec3d vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec3d add(Vec3i vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec3d addXY(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec3d addXY(float x, float y) {
        return addXY((double) x, (double) y);
    }

    public Vec3d addXY(int x, int y) {
        return addXY((double) x, (double) y);
    }

    public Vec3d addXZ(double x, double z) {
        this.x += x;
        this.z += z;
        return this;
    }

    public Vec3d addXZ(float x, float z) {
        return addXZ((double) x, (double) z);
    }

    public Vec3d addXZ(int x, int z) {
        return addXZ((double) x, (double) z);
    }

    public Vec3d addYZ(double y, double z) {
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3d addYZ(float y, float z) {
        return addYZ((double) y, (double) z);
    }

    public Vec3d addYZ(int y, int z) {
        return addYZ((double) y, (double) z);
    }


    public Vec3d sub(double xyz) {
        this.x -= xyz;
        this.y -= xyz;
        this.z -= xyz;
        return this;
    }

    public Vec3d sub(float xyz) {
        return sub((double) xyz);
    }

    public Vec3d sub(int xyz) {
        return sub((double) xyz);
    }

    public Vec3d sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec3d sub(float x, float y) {
        return sub((double) x, (double) y);
    }

    public Vec3d sub(int x, int y) {
        return sub((double) x, (double) y);
    }

    public Vec3d sub(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3d sub(float x, float y, float z) {
        return sub((double) x, (double) y, (double) z);
    }

    public Vec3d sub(int x, int y, int z) {
        return sub((double) x, (double) y, (double) z);
    }

    public Vec3d sub(Vec2f vector) {
        return sub(vector.x, vector.y);
    }

    public Vec3d sub(Vec2d vector) {
        return sub(vector.x, vector.y);
    }

    public Vec3d sub(Vec2i vector) {
        return sub(vector.x, vector.y);
    }

    public Vec3d sub(Vec3f vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec3d sub(Vec3d vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec3d sub(Vec3i vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec3d subXY(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec3d subXY(float x, float y) {
        return subXY((double) x, (double) y);
    }

    public Vec3d subXY(int x, int y) {
        return subXY((double) x, (double) y);
    }

    public Vec3d subXZ(double x, double z) {
        this.x -= x;
        this.z -= z;
        return this;
    }

    public Vec3d subXZ(float x, float z) {
        return subXZ((double) x, (double) z);
    }

    public Vec3d subXZ(int x, int z) {
        return subXZ((double) x, (double) z);
    }

    public Vec3d subYZ(double y, double z) {
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3d subYZ(float y, float z) {
        return subYZ((double) y, (double) z);
    }

    public Vec3d subYZ(int y, int z) {
        return subYZ((double) y, (double) z);
    }


    public Vec3d mul(double xyz) {
        this.x *= xyz;
        this.y *= xyz;
        this.z *= xyz;
        return this;
    }

    public Vec3d mul(float xyz) {
        return mul((double) xyz);
    }

    public Vec3d mul(int xyz) {
        return mul((double) xyz);
    }

    public Vec3d mul(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec3d mul(float x, float y) {
        return mul((double) x, (double) y);
    }

    public Vec3d mul(int x, int y) {
        return mul((double) x, (double) y);
    }

    public Vec3d mul(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3d mul(float x, float y, float z) {
        return mul((double) x, (double) y, (double) z);
    }

    public Vec3d mul(int x, int y, int z) {
        return mul((double) x, (double) y, (double) z);
    }

    public Vec3d mul(Vec2f vector) {
        return mul(vector.x, vector.y);
    }

    public Vec3d mul(Vec2d vector) {
        return mul(vector.x, vector.y);
    }

    public Vec3d mul(Vec2i vector) {
        return mul(vector.x, vector.y);
    }

    public Vec3d mul(Vec3f vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec3d mul(Vec3d vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec3d mul(Vec3i vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec3d mulXY(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec3d mulXY(float x, float y) {
        return mulXY((double) x, (double) y);
    }

    public Vec3d mulXY(int x, int y) {
        return mulXY((double) x, (double) y);
    }

    public Vec3d mulXZ(double x, double z) {
        this.x *= x;
        this.z *= z;
        return this;
    }

    public Vec3d mulXZ(float x, float z) {
        return mulXZ((double) x, (double) z);
    }

    public Vec3d mulXZ(int x, int z) {
        return mulXZ((double) x, (double) z);
    }

    public Vec3d mulYZ(double y, double z) {
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3d mulYZ(float y, float z) {
        return mulYZ((double) y, (double) z);
    }

    public Vec3d mulYZ(int y, int z) {
        return mulYZ((double) y, (double) z);
    }


    public Vec3d div(double xyz) {
        this.x /= xyz;
        this.y /= xyz;
        this.z /= xyz;
        return this;
    }

    public Vec3d div(float xyz) {
        return div((double) xyz);
    }

    public Vec3d div(int xyz) {
        return div((double) xyz);
    }

    public Vec3d div(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec3d div(float x, float y) {
        return div((double) x, (double) y);
    }

    public Vec3d div(int x, int y) {
        return div((double) x, (double) y);
    }

    public Vec3d div(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3d div(float x, float y, float z) {
        return div((double) x, (double) y, (double) z);
    }

    public Vec3d div(int x, int y, int z) {
        return div((double) x, (double) y, (double) z);
    }

    public Vec3d div(Vec2f vector) {
        return div(vector.x, vector.y);
    }

    public Vec3d div(Vec2d vector) {
        return div(vector.x, vector.y);
    }

    public Vec3d div(Vec2i vector) {
        return div(vector.x, vector.y);
    }

    public Vec3d div(Vec3f vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec3d div(Vec3d vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec3d div(Vec3i vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec3d divXY(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec3d divXY(float x, float y) {
        return divXY((double) x, (double) y);
    }

    public Vec3d divXY(int x, int y) {
        return divXY((double) x, (double) y);
    }

    public Vec3d divXZ(double x, double z) {
        this.x /= x;
        this.z /= z;
        return this;
    }

    public Vec3d divXZ(float x, float z) {
        return divXZ((double) x, (double) z);
    }

    public Vec3d divXZ(int x, int z) {
        return divXZ((double) x, (double) z);
    }

    public Vec3d divYZ(double y, double z) {
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3d divYZ(float y, float z) {
        return divYZ((double) y, (double) z);
    }

    public Vec3d divYZ(int y, int z) {
        return divYZ((double) y, (double) z);
    }


    public static double dst(double x1, double y1, double z1, double x2, double y2, double z2) {
        final double dx = x2 - x1;
        final double dy = y2 - y1;
        final double dz = z2 - z1;
        
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static double dst(double x1, double y1, double z1, float x2, float y2, float z2) {
        return dst(x1, y1, z1, (double) x2, (double) y2, (double) z2);
    }

    public static double dst(double x1, double y1, double z1, int x2, int y2, int z2) {
        return dst(x1, y1, z1, (double) x2, (double) y2, (double) z2);
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

    public static double dst(Vec3d vector1 , Vec3f vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static double dst(Vec3d vector1 , Vec3d vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static double dst(Vec3d vector1 , Vec3i vector2) {
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

    public static Vec3d minCompsVec(Vec3d vector1, Vec3d vector2) {
        return new Vec3d(Math.min(vector1.x, vector2.x), Math.min(vector1.y, vector2.y), Math.min(vector1.z, vector2.z));
    }

    public static Vec3d maxCompsVec(Vec3d vector1, Vec3d vector2) {
        return new Vec3d(Math.max(vector1.x, vector2.x), Math.max(vector1.y, vector2.y), Math.max(vector1.z, vector2.z));
    }

    public Vec3d setShorter(Vec3d vector1, Vec3d vector2) {
        return set(shorter(vector1, vector2));
    }

    public Vec3d setLonger(Vec3d vector1, Vec3d vector2) {
        return set(longer(vector1, vector2));
    }

    public Vec3d setMinComps(Vec3d vector1, Vec3d vector2) {
        return set(Math.min(vector1.x, vector2.x), Math.min(vector1.y, vector2.y), Math.min(vector1.z, vector2.z));
    }

    public Vec3d setMaxComps(Vec3d vector1, Vec3d vector2) {
        return set(Math.max(vector1.x, vector2.x), Math.max(vector1.y, vector2.y), Math.max(vector1.z, vector2.z));
    }


    public Vec3d zero() {
        return set(0);
    }

    public boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }

    public Vec3d zeroThatLess(double x, double y, double z) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0;
        if(Math.abs(this.z) < Math.abs(z))
            this.z = 0;
        return this;
    }

    public Vec3d zeroThatLess(float x, float y, float z) {
        return zeroThatLess((double) x, (double) y, (double) z);
    }

    public Vec3d zeroThatLess(int x, int y, int z) {
        return zeroThatLess((double) x, (double) y, (double) z);
    }

    public Vec3d zeroThatLess(float xyz) {
        return zeroThatLess(xyz, xyz, xyz);
    }

    public Vec3d zeroThatLess(double xyz) {
        return zeroThatLess(xyz, xyz, xyz);
    }

    public Vec3d zeroThatLess(int xyz) {
        return zeroThatLess(xyz, xyz, xyz);
    }

    public Vec3d zeroThatLess(Vec3f vector) {
        return zeroThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroThatLess(Vec3d vector) {
        return zeroThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroThatLess(Vec3i vector) {
        return zeroThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroThatZero(double x, double y, double z) {
        if(x == 0)
            this.x = 0;
        if(y == 0)
            this.y = 0;
        if(z == 0)
            this.z = 0;
        return this;
    }

    public Vec3d zeroThatZero(float x, float y, float z) {
        return zeroThatZero((double) x, (double) y, (double) z);
    }

    public Vec3d zeroThatZero(int x, int y, int z) {
        return zeroThatZero((double) x, (double) y, (double) z);
    }

    public Vec3d zeroThatZero(float xyz) {
        return zeroThatZero(xyz, xyz, xyz);
    }

    public Vec3d zeroThatZero(double xyz) {
        return zeroThatZero(xyz, xyz, xyz);
    }

    public Vec3d zeroThatZero(int xyz) {
        return zeroThatZero(xyz, xyz, xyz);
    }

    public Vec3d zeroThatZero(Vec3f vector) {
        return zeroThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroThatZero(Vec3d vector) {
        return zeroThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroThatZero(Vec3i vector) {
        return zeroThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroThatBigger(double x, double y, double z) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0;
        if(Math.abs(this.z) > Math.abs(z))
            this.z = 0;
        return this;
    }

    public Vec3d zeroThatBigger(float x, float y, float z) {
        return zeroThatBigger((double) x, (double) y, (double) z);
    }

    public Vec3d zeroThatBigger(int x, int y, int z) {
        return zeroThatBigger((double) x, (double) y, (double) z);
    }

    public Vec3d zeroThatBigger(float xyz) {
        return zeroThatBigger(xyz, xyz, xyz);
    }

    public Vec3d zeroThatBigger(double xyz) {
        return zeroThatBigger(xyz, xyz, xyz);
    }

    public Vec3d zeroThatBigger(int xyz) {
        return zeroThatBigger(xyz, xyz, xyz);
    }

    public Vec3d zeroThatBigger(Vec3f vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroThatBigger(Vec3d vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z);
    }

    public Vec3d zeroThatBigger(Vec3i vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z);
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
        double len = len2();
        if(len == 0 || len == 1)
            return this;
        
        len = 1D / Math.sqrt(len);
        return mul(len);
    }


    public Vec3d abs() {
        if(x < 0) x *= -1;
        if(y < 0) y *= -1;
        if(z < 0) z *= -1;
        return this;
    }


    public static Vec3d lerp(Vec3d vector, double startX, double startY, double startZ, double endX, double endY, double endZ, double t) {
        return vector.set(Maths.lerp(startX, endX, t), Maths.lerp(startY, endY, t), Maths.lerp(startZ, endZ, t));
    }

    public static Vec3d lerp(Vec3d vector, Vec3d start, Vec3d end, double t) {
        return lerp(vector, start.x, start.y, start.z, end.x, end.y, end.z, t);
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


    public static Vec3d crs(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new Vec3d((y1 * z2 - z1 * y2), (z1 * x2 - x1 * z2), (x1 * y2 - y1 * x2));
    }

    public static Vec3d crs(Vec3d vector1, double x2, double y2, double z2) {
        return crs(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static Vec3d crs(double x1, double y1, double z1, Vec3d vector2) {
        return crs(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static Vec3d crs(Vec3d vector1, Vec3d vector2) {
        return crs(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public Vec3d crs(Vec3d vector) {
        return crs(this, vector);
    }

    public Vec3d crs(double x, double y, double z) {
        return crs(this, x, y, z);
    }

    public Vec3d setCrs(double x1, double y1, double z1, double x2, double y2, double z2) {
        return set((y1 * z2 - z1 * y2), (z1 * x2 - x1 * z2), (x1 * y2 - y1 * x2));
    }

    public Vec3d setCrs(Vec3d vector1, double x2, double y2, double z2) {
        return setCrs(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public Vec3d setCrs(double x1, double y1, double z1, Vec3d vector2) {
        return setCrs(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public Vec3d setCrs(Vec3d vector1, Vec3d vector2) {
        return setCrs(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }


    public Vec3d frac() {
        x = Maths.frac(x);
        y = Maths.frac(y);
        z = Maths.frac(z);
        return this;
    }


    public Vec3i signum() {
        return new Vec3i(Math.signum(x), Math.signum(y), Math.signum(z));
    }


    public static double rad(double x1, double y1, double z1, double x2, double y2, double z2) {
        final double cos = dot(x1, y1, z1, x2, y2, z2) / (len(x1, y1, z1) * len(x2, y2, z2));
        return Math.acos(Maths.clamp(cos, -1, 1));
    }

    public static double rad(Vec3d vector1, double x2, double y2, double z2) {
        return rad(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static double rad(double x1, double y1, double z1, Vec3d vector2) {
        return rad(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static double rad(Vec3d vector1, Vec3d vector2) {
        return rad(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public double rad(double x, double y, double z) {
        return rad(this, x, y, z);
    }

    public double rad(Vec3d vector) {
        return rad(this, vector);
    }

    public static double deg(double x1, double y1, double z1, double x2, double y2, double z2) {
        return rad(x1, y1, z1, x2, y2, z2) * Maths.toDeg;
    }

    public static double deg(Vec3d vector1, double x2, double y2, double z2) {
        return deg(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static double deg(double x1, double y1, double z1, Vec3d vector2) {
        return deg(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static double deg(Vec3d vector1, Vec3d vector2) {
        return deg(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public double deg(double x, double y, double z) {
        return deg(this, x, y, z);
    }

    public double deg(Vec3d vector) {
        return deg(this, vector);
    }


    public Vec3d rotateRadX(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return setYZ((y * cos + z * sin), (y * -sin + z * cos));
    }

    public Vec3d rotateRadY(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return setXZ((x * cos + z * -sin), (x * sin + z * cos));
    }

    public Vec3d rotateRadZ(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return setXY((x * cos + y * sin), (x * -sin + y * cos));
    }

    public Vec3d rotateX(double degrees) {
        return rotateRadX(degrees * Maths.toDeg);
    }

    public Vec3d rotateY(double degrees) {
        return rotateRadY(degrees * Maths.toDeg);
    }

    public Vec3d rotateZ(double degrees) {
        return rotateRadZ(degrees * Maths.toDeg);
    }


    public Vec3d mulMat4(float[] matrix) {
        return set(
            x * matrix[Matrix4.m00] + y * matrix[Matrix4.m10] + z * matrix[Matrix4.m20] + matrix[Matrix4.m30],
            x * matrix[Matrix4.m01] + y * matrix[Matrix4.m11] + z * matrix[Matrix4.m21] + matrix[Matrix4.m31],
            x * matrix[Matrix4.m02] + y * matrix[Matrix4.m12] + z * matrix[Matrix4.m22] + matrix[Matrix4.m32]
        );
    }

    public Vec3d mulMat3(float[] matrix) {
        return set(
            x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + z * matrix[Matrix3.m20],
            x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + z * matrix[Matrix3.m21],
            x * matrix[Matrix3.m02] + y * matrix[Matrix3.m12] + z * matrix[Matrix3.m22]
        );
    }

    public Vec3d mulMat4(Matrix4f matrix) {
        return mulMat4(matrix.val);
    }

    public Vec3d mulMat3(Matrix3f matrix) {
        return mulMat3(matrix.val);
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


    public Vec3d clampLength(double max) {
        final double len = len();
        if(len <= max)
            return this;
        return nor().mul(max);
    }


    public Vec3d reduce(double reduceX, double reduceY, double reduceZ) {
        final double len = len();
        return nor().mul(len - reduceX, len - reduceY, len - reduceZ);
    }


    public Vec3d copy() {
        return new Vec3d(this);
    }

    public static boolean equals(double x1, double y1, double z1, double x2, double y2, double z2) {
        return x1 == x2 && y1 == y2 && z1 == z2;
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