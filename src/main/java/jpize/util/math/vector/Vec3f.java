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


    public Vec3f set(float xyz) {
        this.x = xyz;
        this.y = xyz;
        this.z = xyz;
        return this;
    }

    public Vec3f set(double xyz) {
        return set((float) xyz);
    }

    public Vec3f set(int xyz) {
        return set((float) xyz);
    }

    public Vec3f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec3f set(double x, double y) {
        return set((float) x, (float) y);
    }

    public Vec3f set(int x, int y) {
        return set((float) x, (float) y);
    }

    public Vec3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec3f set(double x, double y, double z) {
        return set((float) x, (float) y, (float) z);
    }

    public Vec3f set(int x, int y, int z) {
        return set((float) x, (float) y, (float) z);
    }

    public Vec3f set(Vec2f vector) {
        return set(vector.x, vector.y);
    }

    public Vec3f set(Vec2d vector) {
        return set(vector.x, vector.y);
    }

    public Vec3f set(Vec2i vector) {
        return set(vector.x, vector.y);
    }

    public Vec3f set(Vec3f vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec3f set(Vec3d vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec3f set(Vec3i vector) {
        return set(vector.x, vector.y, vector.z);
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


    public Vec3f add(float xyz) {
        this.x += xyz;
        this.y += xyz;
        this.z += xyz;
        return this;
    }

    public Vec3f add(double xyz) {
        return add((float) xyz);
    }

    public Vec3f add(int xyz) {
        return add((float) xyz);
    }

    public Vec3f add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec3f add(double x, double y) {
        return add((float) x, (float) y);
    }

    public Vec3f add(int x, int y) {
        return add((float) x, (float) y);
    }

    public Vec3f add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec3f add(double x, double y, double z) {
        return add((float) x, (float) y, (float) z);
    }

    public Vec3f add(int x, int y, int z) {
        return add((float) x, (float) y, (float) z);
    }

    public Vec3f add(Vec2f vector) {
        return add(vector.x, vector.y);
    }

    public Vec3f add(Vec2d vector) {
        return add(vector.x, vector.y);
    }

    public Vec3f add(Vec2i vector) {
        return add(vector.x, vector.y);
    }

    public Vec3f add(Vec3f vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec3f add(Vec3d vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec3f add(Vec3i vector) {
        return add(vector.x, vector.y, vector.z);
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


    public Vec3f sub(float xyz) {
        this.x -= xyz;
        this.y -= xyz;
        this.z -= xyz;
        return this;
    }

    public Vec3f sub(double xyz) {
        return sub((float) xyz);
    }

    public Vec3f sub(int xyz) {
        return sub((float) xyz);
    }

    public Vec3f sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec3f sub(double x, double y) {
        return sub((float) x, (float) y);
    }

    public Vec3f sub(int x, int y) {
        return sub((float) x, (float) y);
    }

    public Vec3f sub(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec3f sub(double x, double y, double z) {
        return sub((float) x, (float) y, (float) z);
    }

    public Vec3f sub(int x, int y, int z) {
        return sub((float) x, (float) y, (float) z);
    }

    public Vec3f sub(Vec2f vector) {
        return sub(vector.x, vector.y);
    }

    public Vec3f sub(Vec2d vector) {
        return sub(vector.x, vector.y);
    }

    public Vec3f sub(Vec2i vector) {
        return sub(vector.x, vector.y);
    }

    public Vec3f sub(Vec3f vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec3f sub(Vec3d vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec3f sub(Vec3i vector) {
        return sub(vector.x, vector.y, vector.z);
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


    public Vec3f mul(float xyz) {
        this.x *= xyz;
        this.y *= xyz;
        this.z *= xyz;
        return this;
    }

    public Vec3f mul(double xyz) {
        return mul((float) xyz);
    }

    public Vec3f mul(int xyz) {
        return mul((float) xyz);
    }

    public Vec3f mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec3f mul(double x, double y) {
        return mul((float) x, (float) y);
    }

    public Vec3f mul(int x, int y) {
        return mul((float) x, (float) y);
    }

    public Vec3f mul(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec3f mul(double x, double y, double z) {
        return mul((float) x, (float) y, (float) z);
    }

    public Vec3f mul(int x, int y, int z) {
        return mul((float) x, (float) y, (float) z);
    }

    public Vec3f mul(Vec2f vector) {
        return mul(vector.x, vector.y);
    }

    public Vec3f mul(Vec2d vector) {
        return mul(vector.x, vector.y);
    }

    public Vec3f mul(Vec2i vector) {
        return mul(vector.x, vector.y);
    }

    public Vec3f mul(Vec3f vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec3f mul(Vec3d vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec3f mul(Vec3i vector) {
        return mul(vector.x, vector.y, vector.z);
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


    public Vec3f div(float xyz) {
        this.x /= xyz;
        this.y /= xyz;
        this.z /= xyz;
        return this;
    }

    public Vec3f div(double xyz) {
        return div((float) xyz);
    }

    public Vec3f div(int xyz) {
        return div((float) xyz);
    }

    public Vec3f div(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec3f div(double x, double y) {
        return div((float) x, (float) y);
    }

    public Vec3f div(int x, int y) {
        return div((float) x, (float) y);
    }

    public Vec3f div(float x, float y, float z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec3f div(double x, double y, double z) {
        return div((float) x, (float) y, (float) z);
    }

    public Vec3f div(int x, int y, int z) {
        return div((float) x, (float) y, (float) z);
    }

    public Vec3f div(Vec2f vector) {
        return div(vector.x, vector.y);
    }

    public Vec3f div(Vec2d vector) {
        return div(vector.x, vector.y);
    }

    public Vec3f div(Vec2i vector) {
        return div(vector.x, vector.y);
    }

    public Vec3f div(Vec3f vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec3f div(Vec3d vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec3f div(Vec3i vector) {
        return div(vector.x, vector.y, vector.z);
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


    public static float dst(float x1, float y1, float z1, float x2, float y2, float z2) {
        final float dx = x2 - x1;
        final float dy = y2 - y1;
        final float dz = z2 - z1;
        
        return Mathc.sqrt(dx * dx + dy * dy + dz * dz);
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

    public static float dst(Vec3f vector1 , Vec3f vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static float dst(Vec3f vector1 , Vec3d vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public static float dst(Vec3f vector1 , Vec3i vector2) {
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

    public static Vec3f minCompsVec(Vec3f vector1, Vec3f vector2) {
        return new Vec3f(Math.min(vector1.x, vector2.x), Math.min(vector1.y, vector2.y), Math.min(vector1.z, vector2.z));
    }

    public static Vec3f maxCompsVec(Vec3f vector1, Vec3f vector2) {
        return new Vec3f(Math.max(vector1.x, vector2.x), Math.max(vector1.y, vector2.y), Math.max(vector1.z, vector2.z));
    }

    public Vec3f setShorter(Vec3f vector1, Vec3f vector2) {
        return set(shorter(vector1, vector2));
    }

    public Vec3f setLonger(Vec3f vector1, Vec3f vector2) {
        return set(longer(vector1, vector2));
    }

    public Vec3f setMinComps(Vec3f vector1, Vec3f vector2) {
        return set(Math.min(vector1.x, vector2.x), Math.min(vector1.y, vector2.y), Math.min(vector1.z, vector2.z));
    }

    public Vec3f setMaxComps(Vec3f vector1, Vec3f vector2) {
        return set(Math.max(vector1.x, vector2.x), Math.max(vector1.y, vector2.y), Math.max(vector1.z, vector2.z));
    }

    public float minComp() {
        return Math.min(x, Math.min(y, z));
    }

    public float maxComp() {
        return Math.max(x, Math.max(y, z));
    }


    public Vec3f zero() {
        return set(0);
    }

    public boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }

    public Vec3f zeroThatLess(float x, float y, float z) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0;
        if(Math.abs(this.z) < Math.abs(z))
            this.z = 0;
        return this;
    }

    public Vec3f zeroThatLess(double x, double y, double z) {
        return zeroThatLess((float) x, (float) y, (float) z);
    }

    public Vec3f zeroThatLess(int x, int y, int z) {
        return zeroThatLess((float) x, (float) y, (float) z);
    }

    public Vec3f zeroThatLess(float xyz) {
        return zeroThatLess(xyz, xyz, xyz);
    }

    public Vec3f zeroThatLess(double xyz) {
        return zeroThatLess(xyz, xyz, xyz);
    }

    public Vec3f zeroThatLess(int xyz) {
        return zeroThatLess(xyz, xyz, xyz);
    }

    public Vec3f zeroThatLess(Vec3f vector) {
        return zeroThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroThatLess(Vec3d vector) {
        return zeroThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroThatLess(Vec3i vector) {
        return zeroThatLess(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroThatZero(float x, float y, float z) {
        if(x == 0)
            this.x = 0;
        if(y == 0)
            this.y = 0;
        if(z == 0)
            this.z = 0;
        return this;
    }

    public Vec3f zeroThatZero(double x, double y, double z) {
        return zeroThatZero((float) x, (float) y, (float) z);
    }

    public Vec3f zeroThatZero(int x, int y, int z) {
        return zeroThatZero((float) x, (float) y, (float) z);
    }

    public Vec3f zeroThatZero(float xyz) {
        return zeroThatZero(xyz, xyz, xyz);
    }

    public Vec3f zeroThatZero(double xyz) {
        return zeroThatZero(xyz, xyz, xyz);
    }

    public Vec3f zeroThatZero(int xyz) {
        return zeroThatZero(xyz, xyz, xyz);
    }

    public Vec3f zeroThatZero(Vec3f vector) {
        return zeroThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroThatZero(Vec3d vector) {
        return zeroThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroThatZero(Vec3i vector) {
        return zeroThatZero(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroThatBigger(float x, float y, float z) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0;
        if(Math.abs(this.z) > Math.abs(z))
            this.z = 0;
        return this;
    }

    public Vec3f zeroThatBigger(double x, double y, double z) {
        return zeroThatBigger((float) x, (float) y, (float) z);
    }

    public Vec3f zeroThatBigger(int x, int y, int z) {
        return zeroThatBigger((float) x, (float) y, (float) z);
    }

    public Vec3f zeroThatBigger(float xyz) {
        return zeroThatBigger(xyz, xyz, xyz);
    }

    public Vec3f zeroThatBigger(double xyz) {
        return zeroThatBigger(xyz, xyz, xyz);
    }

    public Vec3f zeroThatBigger(int xyz) {
        return zeroThatBigger(xyz, xyz, xyz);
    }

    public Vec3f zeroThatBigger(Vec3f vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroThatBigger(Vec3d vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z);
    }

    public Vec3f zeroThatBigger(Vec3i vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z);
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
        float len = len2();
        if(len == 0 || len == 1)
            return this;
        
        len = 1F / Mathc.sqrt(len);
        return mul(len);
    }


    public Vec3f abs() {
        if(x < 0) x *= -1;
        if(y < 0) y *= -1;
        if(z < 0) z *= -1;
        return this;
    }


    public static Vec3f lerp(Vec3f vector, float startX, float startY, float startZ, float endX, float endY, float endZ, float t) {
        return vector.set(Maths.lerp(startX, endX, t), Maths.lerp(startY, endY, t), Maths.lerp(startZ, endZ, t));
    }

    public static Vec3f lerp(Vec3f vector, Vec3f start, Vec3f end, float t) {
        return lerp(vector, start.x, start.y, start.z, end.x, end.y, end.z, t);
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


    public static Vec3f crs(float x1, float y1, float z1, float x2, float y2, float z2) {
        return new Vec3f((y1 * z2 - z1 * y2), (z1 * x2 - x1 * z2), (x1 * y2 - y1 * x2));
    }

    public static Vec3f crs(Vec3f vector1, float x2, float y2, float z2) {
        return crs(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static Vec3f crs(float x1, float y1, float z1, Vec3f vector2) {
        return crs(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static Vec3f crs(Vec3f vector1, Vec3f vector2) {
        return crs(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public Vec3f crs(Vec3f vector) {
        return crs(this, vector);
    }

    public Vec3f crs(float x, float y, float z) {
        return crs(this, x, y, z);
    }

    public Vec3f setCrs(float x1, float y1, float z1, float x2, float y2, float z2) {
        return set((y1 * z2 - z1 * y2), (z1 * x2 - x1 * z2), (x1 * y2 - y1 * x2));
    }

    public Vec3f setCrs(Vec3f vector1, float x2, float y2, float z2) {
        return setCrs(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public Vec3f setCrs(float x1, float y1, float z1, Vec3f vector2) {
        return setCrs(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public Vec3f setCrs(Vec3f vector1, Vec3f vector2) {
        return setCrs(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }


    public Vec3f frac() {
        x = Maths.frac(x);
        y = Maths.frac(y);
        z = Maths.frac(z);
        return this;
    }


    public Vec3i signum() {
        return new Vec3i(Math.signum(x), Math.signum(y), Math.signum(z));
    }


    public static float rad(float x1, float y1, float z1, float x2, float y2, float z2) {
        final float cos = dot(x1, y1, z1, x2, y2, z2) / (len(x1, y1, z1) * len(x2, y2, z2));
        return Mathc.acos(Maths.clamp(cos, -1, 1));
    }

    public static float rad(Vec3f vector1, float x2, float y2, float z2) {
        return rad(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static float rad(float x1, float y1, float z1, Vec3f vector2) {
        return rad(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static float rad(Vec3f vector1, Vec3f vector2) {
        return rad(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public float rad(float x, float y, float z) {
        return rad(this, x, y, z);
    }

    public float rad(Vec3f vector) {
        return rad(this, vector);
    }

    public static float deg(float x1, float y1, float z1, float x2, float y2, float z2) {
        return rad(x1, y1, z1, x2, y2, z2) * Maths.toDeg;
    }

    public static float deg(Vec3f vector1, float x2, float y2, float z2) {
        return deg(vector1.x, vector1.y, vector1.z, x2, y2, z2);
    }

    public static float deg(float x1, float y1, float z1, Vec3f vector2) {
        return deg(x1, y1, z1, vector2.x, vector2.y, vector2.z);
    }

    public static float deg(Vec3f vector1, Vec3f vector2) {
        return deg(vector1.x, vector1.y, vector1.z, vector2.x, vector2.y, vector2.z);
    }

    public float deg(float x, float y, float z) {
        return deg(this, x, y, z);
    }

    public float deg(Vec3f vector) {
        return deg(this, vector);
    }


    public Vec3f rotateRadX(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return setYZ((y * cos + z * sin), (y * -sin + z * cos));
    }

    public Vec3f rotateRadY(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return setXZ((x * cos + z * -sin), (x * sin + z * cos));
    }

    public Vec3f rotateRadZ(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return setXY((x * cos + y * sin), (x * -sin + y * cos));
    }

    public Vec3f rotateX(double degrees) {
        return rotateRadX(degrees * Maths.toDeg);
    }

    public Vec3f rotateY(double degrees) {
        return rotateRadY(degrees * Maths.toDeg);
    }

    public Vec3f rotateZ(double degrees) {
        return rotateRadZ(degrees * Maths.toDeg);
    }


    public Vec3f mulMat4(float[] matrix) {
        return set(
            x * matrix[Matrix4.m00] + y * matrix[Matrix4.m10] + z * matrix[Matrix4.m20] + matrix[Matrix4.m30],
            x * matrix[Matrix4.m01] + y * matrix[Matrix4.m11] + z * matrix[Matrix4.m21] + matrix[Matrix4.m31],
            x * matrix[Matrix4.m02] + y * matrix[Matrix4.m12] + z * matrix[Matrix4.m22] + matrix[Matrix4.m32]
        );
    }

    public Vec3f mulMat3(float[] matrix) {
        return set(
            x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + z * matrix[Matrix3.m20],
            x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + z * matrix[Matrix3.m21],
            x * matrix[Matrix3.m02] + y * matrix[Matrix3.m12] + z * matrix[Matrix3.m22]
        );
    }

    public Vec3f mulMat4(Matrix4f matrix) {
        return mulMat4(matrix.val);
    }

    public Vec3f mulMat3(Matrix3f matrix) {
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


    public Vec3f clampLength(double max) {
        final float len = len();
        if(len <= max)
            return this;
        return nor().mul(max);
    }


    public Vec3f reduce(double reduceX, double reduceY, double reduceZ) {
        final float len = len();
        return nor().mul(len - reduceX, len - reduceY, len - reduceZ);
    }


    public Vec3f copy() {
        return new Vec3f(this);
    }

    public static boolean equals(float x1, float y1, float z1, float x2, float y2, float z2) {
        return x1 == x2 && y1 == y2 && z1 == z2;
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
        return x + ", " + y + ", " + z;
    }

}