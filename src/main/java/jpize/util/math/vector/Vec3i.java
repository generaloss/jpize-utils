package jpize.util.math.vector;

import jpize.util.math.Maths;
import jpize.util.math.Mathc;
import jpize.util.math.matrix.*;
import java.util.Objects;

public class Vec3i {

    public int x;
    public int y;
    public int z;

    public Vec3i() { }

    public Vec3i(float xyz) {
        this.set(xyz);
    }

    public Vec3i(double xyz) {
        this.set(xyz);
    }

    public Vec3i(int xyz) {
        this.set(xyz);
    }

    public Vec3i(float x, float y) {
        this.set(x, y);
    }

    public Vec3i(double x, double y) {
        this.set(x, y);
    }

    public Vec3i(int x, int y) {
        this.set(x, y);
    }

    public Vec3i(float x, float y, float z) {
        this.set(x, y, z);
    }

    public Vec3i(double x, double y, double z) {
        this.set(x, y, z);
    }

    public Vec3i(int x, int y, int z) {
        this.set(x, y, z);
    }

    public Vec3i(Vec2f vector) {
        this.set(vector.x, vector.y);
    }

    public Vec3i(Vec2d vector) {
        this.set(vector.x, vector.y);
    }

    public Vec3i(Vec2i vector) {
        this.set(vector.x, vector.y);
    }

    public Vec3i(Vec3f vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec3i(Vec3d vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec3i(Vec3i vector) {
        this.set(vector.x, vector.y, vector.z);
    }


    public Vec3i set(int xyz) {
        this.x = xyz;
        this.y = xyz;
        this.z = xyz;
        return this;
    }

    public Vec3i set(float xyz) {
        return set((int) xyz);
    }

    public Vec3i set(double xyz) {
        return set((int) xyz);
    }

    public Vec3i set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec3i set(float x, float y) {
        return set((int) x, (int) y);
    }

    public Vec3i set(double x, double y) {
        return set((int) x, (int) y);
    }

    public Vec3i set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3i set(float x, float y, float z) {
        return set((int) x, (int) y, (int) z);
    }

    public Vec3i set(double x, double y, double z) {
        return set((int) x, (int) y, (int) z);
    }

    public Vec3i set(Vec2f vector) {
        return set(vector.x, vector.y);
    }

    public Vec3i set(Vec2d vector) {
        return set(vector.x, vector.y);
    }

    public Vec3i set(Vec2i vector) {
        return set(vector.x, vector.y);
    }

    public Vec3i set(Vec3f vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec3i set(Vec3d vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec3i set(Vec3i vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec3i setXY(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec3i setXY(float x, float y) {
        return setXY((int) x, (int) y);
    }

    public Vec3i setXY(double x, double y) {
        return setXY((int) x, (int) y);
    }

    public Vec3i setXZ(int x, int z) {
        this.x = x;
        this.z = z;
        return this;
    }

    public Vec3i setXZ(float x, float z) {
        return setXZ((int) x, (int) z);
    }

    public Vec3i setXZ(double x, double z) {
        return setXZ((int) x, (int) z);
    }

    public Vec3i setYZ(int y, int z) {
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3i setYZ(float y, float z) {
        return setYZ((int) y, (int) z);
    }

    public Vec3i setYZ(double y, double z) {
        return setYZ((int) y, (int) z);
    }


    public Vec3i add(int xyz) {
        this.x += xyz;
        this.y += xyz;
        this.z += xyz;
        return this;
    }

    public Vec3i add(float xyz) {
        return add((int) xyz);
    }

    public Vec3i add(double xyz) {
        return add((int) xyz);
    }

    public Vec3i add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec3i add(float x, float y) {
        return add((int) x, (int) y);
    }

    public Vec3i add(double x, double y) {
        return add((int) x, (int) y);
    }

    public Vec3i add(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3i add(float x, float y, float z) {
        return add((int) x, (int) y, (int) z);
    }

    public Vec3i add(double x, double y, double z) {
        return add((int) x, (int) y, (int) z);
    }

    public Vec3i add(Vec2f vector) {
        return add(vector.x, vector.y);
    }

    public Vec3i add(Vec2d vector) {
        return add(vector.x, vector.y);
    }

    public Vec3i add(Vec2i vector) {
        return add(vector.x, vector.y);
    }

    public Vec3i add(Vec3f vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec3i add(Vec3d vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec3i add(Vec3i vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec3i addXY(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec3i addXY(float x, float y) {
        return addXY((int) x, (int) y);
    }

    public Vec3i addXY(double x, double y) {
        return addXY((int) x, (int) y);
    }

    public Vec3i addXZ(int x, int z) {
        this.x += x;
        this.z += z;
        return this;
    }

    public Vec3i addXZ(float x, float z) {
        return addXZ((int) x, (int) z);
    }

    public Vec3i addXZ(double x, double z) {
        return addXZ((int) x, (int) z);
    }

    public Vec3i addYZ(int y, int z) {
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3i addYZ(float y, float z) {
        return addYZ((int) y, (int) z);
    }

    public Vec3i addYZ(double y, double z) {
        return addYZ((int) y, (int) z);
    }


    public Vec3i sub(int xyz) {
        this.x -= xyz;
        this.y -= xyz;
        this.z -= xyz;
        return this;
    }

    public Vec3i sub(float xyz) {
        return sub((int) xyz);
    }

    public Vec3i sub(double xyz) {
        return sub((int) xyz);
    }

    public Vec3i sub(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec3i sub(float x, float y) {
        return sub((int) x, (int) y);
    }

    public Vec3i sub(double x, double y) {
        return sub((int) x, (int) y);
    }

    public Vec3i sub(int x, int y, int z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3i sub(float x, float y, float z) {
        return sub((int) x, (int) y, (int) z);
    }

    public Vec3i sub(double x, double y, double z) {
        return sub((int) x, (int) y, (int) z);
    }

    public Vec3i sub(Vec2f vector) {
        return sub(vector.x, vector.y);
    }

    public Vec3i sub(Vec2d vector) {
        return sub(vector.x, vector.y);
    }

    public Vec3i sub(Vec2i vector) {
        return sub(vector.x, vector.y);
    }

    public Vec3i sub(Vec3f vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec3i sub(Vec3d vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec3i sub(Vec3i vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec3i subXY(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec3i subXY(float x, float y) {
        return subXY((int) x, (int) y);
    }

    public Vec3i subXY(double x, double y) {
        return subXY((int) x, (int) y);
    }

    public Vec3i subXZ(int x, int z) {
        this.x -= x;
        this.z -= z;
        return this;
    }

    public Vec3i subXZ(float x, float z) {
        return subXZ((int) x, (int) z);
    }

    public Vec3i subXZ(double x, double z) {
        return subXZ((int) x, (int) z);
    }

    public Vec3i subYZ(int y, int z) {
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3i subYZ(float y, float z) {
        return subYZ((int) y, (int) z);
    }

    public Vec3i subYZ(double y, double z) {
        return subYZ((int) y, (int) z);
    }


    public Vec3i mul(int xyz) {
        this.x *= xyz;
        this.y *= xyz;
        this.z *= xyz;
        return this;
    }

    public Vec3i mul(float xyz) {
        return mul((int) xyz);
    }

    public Vec3i mul(double xyz) {
        return mul((int) xyz);
    }

    public Vec3i mul(int x, int y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec3i mul(float x, float y) {
        return mul((int) x, (int) y);
    }

    public Vec3i mul(double x, double y) {
        return mul((int) x, (int) y);
    }

    public Vec3i mul(int x, int y, int z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3i mul(float x, float y, float z) {
        return mul((int) x, (int) y, (int) z);
    }

    public Vec3i mul(double x, double y, double z) {
        return mul((int) x, (int) y, (int) z);
    }

    public Vec3i mul(Vec2f vector) {
        return mul(vector.x, vector.y);
    }

    public Vec3i mul(Vec2d vector) {
        return mul(vector.x, vector.y);
    }

    public Vec3i mul(Vec2i vector) {
        return mul(vector.x, vector.y);
    }

    public Vec3i mul(Vec3f vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec3i mul(Vec3d vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec3i mul(Vec3i vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec3i mulXY(int x, int y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec3i mulXY(float x, float y) {
        return mulXY((int) x, (int) y);
    }

    public Vec3i mulXY(double x, double y) {
        return mulXY((int) x, (int) y);
    }

    public Vec3i mulXZ(int x, int z) {
        this.x *= x;
        this.z *= z;
        return this;
    }

    public Vec3i mulXZ(float x, float z) {
        return mulXZ((int) x, (int) z);
    }

    public Vec3i mulXZ(double x, double z) {
        return mulXZ((int) x, (int) z);
    }

    public Vec3i mulYZ(int y, int z) {
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3i mulYZ(float y, float z) {
        return mulYZ((int) y, (int) z);
    }

    public Vec3i mulYZ(double y, double z) {
        return mulYZ((int) y, (int) z);
    }


    public Vec3i div(int xyz) {
        this.x /= xyz;
        this.y /= xyz;
        this.z /= xyz;
        return this;
    }

    public Vec3i div(float xyz) {
        return div((int) xyz);
    }

    public Vec3i div(double xyz) {
        return div((int) xyz);
    }

    public Vec3i div(int x, int y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec3i div(float x, float y) {
        return div((int) x, (int) y);
    }

    public Vec3i div(double x, double y) {
        return div((int) x, (int) y);
    }

    public Vec3i div(int x, int y, int z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3i div(float x, float y, float z) {
        return div((int) x, (int) y, (int) z);
    }

    public Vec3i div(double x, double y, double z) {
        return div((int) x, (int) y, (int) z);
    }

    public Vec3i div(Vec2f vector) {
        return div(vector.x, vector.y);
    }

    public Vec3i div(Vec2d vector) {
        return div(vector.x, vector.y);
    }

    public Vec3i div(Vec2i vector) {
        return div(vector.x, vector.y);
    }

    public Vec3i div(Vec3f vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec3i div(Vec3d vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec3i div(Vec3i vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec3i divXY(int x, int y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec3i divXY(float x, float y) {
        return divXY((int) x, (int) y);
    }

    public Vec3i divXY(double x, double y) {
        return divXY((int) x, (int) y);
    }

    public Vec3i divXZ(int x, int z) {
        this.x /= x;
        this.z /= z;
        return this;
    }

    public Vec3i divXZ(float x, float z) {
        return divXZ((int) x, (int) z);
    }

    public Vec3i divXZ(double x, double z) {
        return divXZ((int) x, (int) z);
    }

    public Vec3i divYZ(int y, int z) {
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3i divYZ(float y, float z) {
        return divYZ((int) y, (int) z);
    }

    public Vec3i divYZ(double y, double z) {
        return divYZ((int) y, (int) z);
    }


    public static float dst(int x1, int y1, int z1, int x2, int y2, int z2) {
        final int dx = x2 - x1;
        final int dy = y2 - y1;
        final int dz = z2 - z1;
        
        return Mathc.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static float dst(int x1, int y1, int z1, float x2, float y2, float z2) {
        return dst(x1, y1, z1, (int) x2, (int) y2, (int) z2);
    }

    public static float dst(int x1, int y1, int z1, double x2, double y2, double z2) {
        return dst(x1, y1, z1, (int) x2, (int) y2, (int) z2);
    }

    public static float dst(int x, int y, int z, Vec3f vector) {
        return dst(x, y, z, vector.x, vector.y, vector.z);
    }

    public static float dst(int x, int y, int z, Vec3d vector) {
        return dst(x, y, z, vector.x, vector.y, vector.z);
    }

    public static float dst(int x, int y, int z, Vec3i vector) {
        return dst(x, y, z, vector.x, vector.y, vector.z);
    }

    public static float dst(Vec3i vector, float x, float y, float z) {
        return dst(vector.x, vector.y, vector.z, x, y, z);
    }

    public static float dst(Vec3i vector, double x, double y, double z) {
        return dst(vector.x, vector.y, vector.z, x, y, z);
    }

    public static float dst(Vec3i vector, int x, int y, int z) {
        return dst(vector.x, vector.y, vector.z, x, y, z);
    }

    public static float dst(Vec3i vector1 , Vec3f vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static float dst(Vec3i vector1 , Vec3d vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static float dst(Vec3i vector1 , Vec3i vector2) {
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


    public static Vec3i shorter(Vec3i vector1, Vec3i vector2) {
        return (vector1.len2() < vector2.len2()) ? vector1 : vector2;
    }

    public static Vec3i longer(Vec3i vector1, Vec3i vector2) {
        return (vector1.len2() > vector2.len2()) ? vector1 : vector2;
    }

    public static Vec3i minCompsVec(Vec3i vector1, Vec3i vector2) {
        return new Vec3i(Math.min(vector1.x, vector2.x), Math.min(vector1.y, vector2.y), Math.min(vector1.z, vector2.z));
    }

    public static Vec3i maxCompsVec(Vec3i vector1, Vec3i vector2) {
        return new Vec3i(Math.max(vector1.x, vector2.x), Math.max(vector1.y, vector2.y), Math.max(vector1.z, vector2.z));
    }

    public Vec3i setShorter(Vec3i vector1, Vec3i vector2) {
        return set(shorter(vector1, vector2));
    }

    public Vec3i setLonger(Vec3i vector1, Vec3i vector2) {
        return set(longer(vector1, vector2));
    }

    public Vec3i setMinComps(Vec3i vector1, Vec3i vector2) {
        return set(Math.min(vector1.x, vector2.x), Math.min(vector1.y, vector2.y), Math.min(vector1.z, vector2.z));
    }

    public Vec3i setMaxComps(Vec3i vector1, Vec3i vector2) {
        return set(Math.max(vector1.x, vector2.x), Math.max(vector1.y, vector2.y), Math.max(vector1.z, vector2.z));
    }

    public int minComp() {
        return Math.min(x, Math.min(y, z));
    }

    public int maxComp() {
        return Math.max(x, Math.max(y, z));
    }


    public Vec3i zero() {
        return set(0);
    }

    public boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }

    public Vec3i zeroThatLess(int x, int y, int z) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0;
        if(Math.abs(this.z) < Math.abs(z))
            this.z = 0;
        return this;
    }

    public Vec3i zeroThatLess(float x, float y, float z) {
        return zeroThatLess((int) x, (int) y, (int) z);
    }

    public Vec3i zeroThatLess(double x, double y, double z) {
        return zeroThatLess((int) x, (int) y, (int) z);
    }

    public Vec3i zeroThatLess(float xyz) {
        return zeroThatLess(xyz, xyz, xyz);
    }

    public Vec3i zeroThatLess(double xyz) {
        return zeroThatLess(xyz, xyz, xyz);
    }

    public Vec3i zeroThatLess(int xyz) {
        return zeroThatLess(xyz, xyz, xyz);
    }

    public Vec3i zeroThatLess(Vec3f vector) {
        return zeroThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3i zeroThatLess(Vec3d vector) {
        return zeroThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3i zeroThatLess(Vec3i vector) {
        return zeroThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3i zeroThatZero(int x, int y, int z) {
        if(x == 0)
            this.x = 0;
        if(y == 0)
            this.y = 0;
        if(z == 0)
            this.z = 0;
        return this;
    }

    public Vec3i zeroThatZero(float x, float y, float z) {
        return zeroThatZero((int) x, (int) y, (int) z);
    }

    public Vec3i zeroThatZero(double x, double y, double z) {
        return zeroThatZero((int) x, (int) y, (int) z);
    }

    public Vec3i zeroThatZero(float xyz) {
        return zeroThatZero(xyz, xyz, xyz);
    }

    public Vec3i zeroThatZero(double xyz) {
        return zeroThatZero(xyz, xyz, xyz);
    }

    public Vec3i zeroThatZero(int xyz) {
        return zeroThatZero(xyz, xyz, xyz);
    }

    public Vec3i zeroThatZero(Vec3f vector) {
        return zeroThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3i zeroThatZero(Vec3d vector) {
        return zeroThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3i zeroThatZero(Vec3i vector) {
        return zeroThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3i zeroThatBigger(int x, int y, int z) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0;
        if(Math.abs(this.z) > Math.abs(z))
            this.z = 0;
        return this;
    }

    public Vec3i zeroThatBigger(float x, float y, float z) {
        return zeroThatBigger((int) x, (int) y, (int) z);
    }

    public Vec3i zeroThatBigger(double x, double y, double z) {
        return zeroThatBigger((int) x, (int) y, (int) z);
    }

    public Vec3i zeroThatBigger(float xyz) {
        return zeroThatBigger(xyz, xyz, xyz);
    }

    public Vec3i zeroThatBigger(double xyz) {
        return zeroThatBigger(xyz, xyz, xyz);
    }

    public Vec3i zeroThatBigger(int xyz) {
        return zeroThatBigger(xyz, xyz, xyz);
    }

    public Vec3i zeroThatBigger(Vec3f vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z);
    }

    public Vec3i zeroThatBigger(Vec3d vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z);
    }

    public Vec3i zeroThatBigger(Vec3i vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z);
    }


    public int volume() {
        return x * y * z;
    }


    public static int len2(int x, int y, int z) {
        return x * x + y * y + z * z;
    }

    public static int len2(Vec3i vector) {
        return len2(vector.x, vector.y, vector.z);
    }

    public static float len(int x, int y, int z) {
        return Mathc.sqrt(len2(x, y, z));
    }

    public static float len(Vec3i vector) {
        return Mathc.sqrt(len2(vector.x, vector.y, vector.z));
    }

    public int len2() {
        return len2(this);
    }

    public float len() {
        return len(this);
    }

    public static int lenh2(Vec3i vector) {
        return vector.x * vector.x + vector.z * vector.z;
    }

    public static float lenh(Vec3i vector) {
        return Mathc.sqrt(lenh2(vector));
    }

    public int lenh2() {
        return lenh2(this);
    }

    public float lenh() {
        return lenh(this);
    }


    public Vec3i abs() {
        if(x < 0) x *= -1;
        if(y < 0) y *= -1;
        if(z < 0) z *= -1;
        return this;
    }


    public static Vec3i lerp(Vec3i vector, int startX, int startY, int startZ, int endX, int endY, int endZ, float t) {
        return vector.set(Maths.lerp(startX, endX, t), Maths.lerp(startY, endY, t), Maths.lerp(startZ, endZ, t));
    }

    public static Vec3i lerp(Vec3i vector, Vec3i start, Vec3i end, float t) {
        return lerp(vector, start.x, start.y, start.z, end.x, end.y, end.z, t);
    }

    public Vec3i lerp(int startX, int startY, int startZ, int endX, int endY, int endZ, float t) {
        return lerp(this, startX, startY, startZ, endX, endY, endZ, t);
    }

    public Vec3i lerp(Vec3i start, Vec3i end, float t) {
        return lerp(this, start, end, t);
    }


    public static int dot(int x1, int y1, int z1, int x2, int y2, int z2) {
        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    public static int dot(Vec3i vector1, int x2, int y2, int z2) {
        return dot(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static int dot(int x1, int y1, int z1, Vec3i vector2) {
        return dot(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static int dot(Vec3i vector1, Vec3i vector2) {
        return dot(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public int dot(Vec3i vector) {
        return dot(this, vector);
    }

    public int dot(int x, int y, int z) {
        return dot(this, x, y, z);
    }


    public static Vec3i crs(int x1, int y1, int z1, int x2, int y2, int z2) {
        return new Vec3i((y1 * z2 - z1 * y2), (z1 * x2 - x1 * z2), (x1 * y2 - y1 * x2));
    }

    public static Vec3i crs(Vec3i vector1, int x2, int y2, int z2) {
        return crs(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static Vec3i crs(int x1, int y1, int z1, Vec3i vector2) {
        return crs(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static Vec3i crs(Vec3i vector1, Vec3i vector2) {
        return crs(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public Vec3i crs(Vec3i vector) {
        return crs(this, vector);
    }

    public Vec3i crs(int x, int y, int z) {
        return crs(this, x, y, z);
    }

    public Vec3i setCrs(int x1, int y1, int z1, int x2, int y2, int z2) {
        return set((y1 * z2 - z1 * y2), (z1 * x2 - x1 * z2), (x1 * y2 - y1 * x2));
    }

    public Vec3i setCrs(Vec3i vector1, int x2, int y2, int z2) {
        return setCrs(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public Vec3i setCrs(int x1, int y1, int z1, Vec3i vector2) {
        return setCrs(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public Vec3i setCrs(Vec3i vector1, Vec3i vector2) {
        return setCrs(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }


    public Vec3i signum() {
        return new Vec3i(Math.signum(x), Math.signum(y), Math.signum(z));
    }


    public static float rad(int x1, int y1, int z1, int x2, int y2, int z2) {
        final float cos = dot(x1, y1, z1, x2, y2, z2) / (len(x1, y1, z1) * len(x2, y2, z2));
        return Mathc.acos(Maths.clamp(cos, -1, 1));
    }

    public static float rad(Vec3i vector1, int x2, int y2, int z2) {
        return rad(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static float rad(int x1, int y1, int z1, Vec3i vector2) {
        return rad(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static float rad(Vec3i vector1, Vec3i vector2) {
        return rad(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public float rad(int x, int y, int z) {
        return rad(this, x, y, z);
    }

    public float rad(Vec3i vector) {
        return rad(this, vector);
    }

    public static float deg(int x1, int y1, int z1, int x2, int y2, int z2) {
        return rad(x1, y1, z1, x2, y2, z2) * Maths.toDeg;
    }

    public static float deg(Vec3i vector1, int x2, int y2, int z2) {
        return deg(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static float deg(int x1, int y1, int z1, Vec3i vector2) {
        return deg(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static float deg(Vec3i vector1, Vec3i vector2) {
        return deg(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public float deg(int x, int y, int z) {
        return deg(this, x, y, z);
    }

    public float deg(Vec3i vector) {
        return deg(this, vector);
    }


    public Vec3i rotateRadX(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return setYZ((y * cos + z * sin), (y * -sin + z * cos));
    }

    public Vec3i rotateRadY(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return setXZ((x * cos + z * -sin), (x * sin + z * cos));
    }

    public Vec3i rotateRadZ(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return setXY((x * cos + y * sin), (x * -sin + y * cos));
    }

    public Vec3i rotateX(double degrees) {
        return rotateRadX(degrees * Maths.toDeg);
    }

    public Vec3i rotateY(double degrees) {
        return rotateRadY(degrees * Maths.toDeg);
    }

    public Vec3i rotateZ(double degrees) {
        return rotateRadZ(degrees * Maths.toDeg);
    }


    public Vec3i mulMat4(float[] matrix) {
        return set(
            x * matrix[Matrix4.m00] + y * matrix[Matrix4.m10] + z * matrix[Matrix4.m20] + matrix[Matrix4.m30],
            x * matrix[Matrix4.m01] + y * matrix[Matrix4.m11] + z * matrix[Matrix4.m21] + matrix[Matrix4.m31],
            x * matrix[Matrix4.m02] + y * matrix[Matrix4.m12] + z * matrix[Matrix4.m22] + matrix[Matrix4.m32]
        );
    }

    public Vec3i mulMat3(float[] matrix) {
        return set(
            x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + z * matrix[Matrix3.m20],
            x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + z * matrix[Matrix3.m21],
            x * matrix[Matrix3.m02] + y * matrix[Matrix3.m12] + z * matrix[Matrix3.m22]
        );
    }

    public Vec3i mulMat4(Matrix4f matrix) {
        return mulMat4(matrix.val);
    }

    public Vec3i mulMat3(Matrix3f matrix) {
        return mulMat3(matrix.val);
    }


    public Vec3f castFloat() {
        return new Vec3f(this);
    }

    public Vec3d castDouble() {
        return new Vec3d(this);
    }


    public Vec2i xy() {
        return new Vec2i(x, y);
    }

    public Vec2i xz() {
        return new Vec2i(x, z);
    }

    public Vec2i yz() {
        return new Vec2i(y, z);
    }


    public Vec3i copy() {
        return new Vec3i(this);
    }

    public static boolean equals(int x1, int y1, int z1, int x2, int y2, int z2) {
        return x1 == x2 && y1 == y2 && z1 == z2;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        
        final Vec3i vec = (Vec3i) object;
        return Vec3i.equals(x, y, z, vec.x, vec.y, vec.z);
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