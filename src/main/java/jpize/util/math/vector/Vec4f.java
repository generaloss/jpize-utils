package jpize.util.math.vector;

import jpize.util.math.Maths;
import jpize.util.math.Mathc;
import java.util.Objects;

public class Vec4f {

    public float x;
    public float y;
    public float z;
    public float w;

    public Vec4f() { }

    public Vec4f(float xyzw) {
        this.set(xyzw);
    }

    public Vec4f(double xyzw) {
        this.set(xyzw);
    }

    public Vec4f(int xyzw) {
        this.set(xyzw);
    }

    public Vec4f(float x, float y) {
        this.set(x, y);
    }

    public Vec4f(double x, double y) {
        this.set(x, y);
    }

    public Vec4f(int x, int y) {
        this.set(x, y);
    }

    public Vec4f(float x, float y, float z) {
        this.set(x, y, z);
    }

    public Vec4f(double x, double y, double z) {
        this.set(x, y, z);
    }

    public Vec4f(int x, int y, int z) {
        this.set(x, y, z);
    }

    public Vec4f(float x, float y, float z, float w) {
        this.set(x, y, z, w);
    }

    public Vec4f(double x, double y, double z, double w) {
        this.set(x, y, z, w);
    }

    public Vec4f(int x, int y, int z, int w) {
        this.set(x, y, z, w);
    }

    public Vec4f(Vec2f vector) {
        this.set(vector.x, vector.y);
    }

    public Vec4f(Vec2d vector) {
        this.set(vector.x, vector.y);
    }

    public Vec4f(Vec2i vector) {
        this.set(vector.x, vector.y);
    }

    public Vec4f(Vec3f vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec4f(Vec3d vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec4f(Vec3i vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec4f(Vec4f vector) {
        this.set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f(Vec4d vector) {
        this.set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f(Vec4i vector) {
        this.set(vector.x, vector.y, vector.z, vector.w);
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

    public float getW() {
        return w;
    }


    public Vec4f set(float xyzw) {
        this.x = xyzw;
        this.y = xyzw;
        this.z = xyzw;
        this.w = xyzw;
        return this;
    }

    public Vec4f set(double xyzw) {
        return set((float) xyzw);
    }

    public Vec4f set(int xyzw) {
        return set((float) xyzw);
    }

    public Vec4f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec4f set(double x, double y) {
        return set((float) x, (float) y);
    }

    public Vec4f set(int x, int y) {
        return set((float) x, (float) y);
    }

    public Vec4f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec4f set(double x, double y, double z) {
        return set((float) x, (float) y, (float) z);
    }

    public Vec4f set(int x, int y, int z) {
        return set((float) x, (float) y, (float) z);
    }

    public Vec4f set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Vec4f set(double x, double y, double z, double w) {
        return set((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f set(int x, int y, int z, int w) {
        return set((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f set(Vec2f vector) {
        return set(vector.x, vector.y);
    }

    public Vec4f set(Vec2d vector) {
        return set(vector.x, vector.y);
    }

    public Vec4f set(Vec2i vector) {
        return set(vector.x, vector.y);
    }

    public Vec4f set(Vec3f vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec4f set(Vec3d vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec4f set(Vec3i vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec4f set(Vec4f vector) {
        return set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f set(Vec4d vector) {
        return set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f set(Vec4i vector) {
        return set(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4f add(float xyzw) {
        this.x += xyzw;
        this.y += xyzw;
        this.z += xyzw;
        this.w += xyzw;
        return this;
    }

    public Vec4f add(double xyzw) {
        return add((float) xyzw);
    }

    public Vec4f add(int xyzw) {
        return add((float) xyzw);
    }

    public Vec4f add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec4f add(double x, double y) {
        return add((float) x, (float) y);
    }

    public Vec4f add(int x, int y) {
        return add((float) x, (float) y);
    }

    public Vec4f add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec4f add(double x, double y, double z) {
        return add((float) x, (float) y, (float) z);
    }

    public Vec4f add(int x, int y, int z) {
        return add((float) x, (float) y, (float) z);
    }

    public Vec4f add(float x, float y, float z, float w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    public Vec4f add(double x, double y, double z, double w) {
        return add((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f add(int x, int y, int z, int w) {
        return add((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f add(Vec2f vector) {
        return add(vector.x, vector.y);
    }

    public Vec4f add(Vec2d vector) {
        return add(vector.x, vector.y);
    }

    public Vec4f add(Vec2i vector) {
        return add(vector.x, vector.y);
    }

    public Vec4f add(Vec3f vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec4f add(Vec3d vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec4f add(Vec3i vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec4f add(Vec4f vector) {
        return add(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f add(Vec4d vector) {
        return add(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f add(Vec4i vector) {
        return add(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4f sub(float xyzw) {
        this.x -= xyzw;
        this.y -= xyzw;
        this.z -= xyzw;
        this.w -= xyzw;
        return this;
    }

    public Vec4f sub(double xyzw) {
        return sub((float) xyzw);
    }

    public Vec4f sub(int xyzw) {
        return sub((float) xyzw);
    }

    public Vec4f sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec4f sub(double x, double y) {
        return sub((float) x, (float) y);
    }

    public Vec4f sub(int x, int y) {
        return sub((float) x, (float) y);
    }

    public Vec4f sub(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec4f sub(double x, double y, double z) {
        return sub((float) x, (float) y, (float) z);
    }

    public Vec4f sub(int x, int y, int z) {
        return sub((float) x, (float) y, (float) z);
    }

    public Vec4f sub(float x, float y, float z, float w) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        this.w -= w;
        return this;
    }

    public Vec4f sub(double x, double y, double z, double w) {
        return sub((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f sub(int x, int y, int z, int w) {
        return sub((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f sub(Vec2f vector) {
        return sub(vector.x, vector.y);
    }

    public Vec4f sub(Vec2d vector) {
        return sub(vector.x, vector.y);
    }

    public Vec4f sub(Vec2i vector) {
        return sub(vector.x, vector.y);
    }

    public Vec4f sub(Vec3f vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec4f sub(Vec3d vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec4f sub(Vec3i vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec4f sub(Vec4f vector) {
        return sub(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f sub(Vec4d vector) {
        return sub(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f sub(Vec4i vector) {
        return sub(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4f mul(float xyzw) {
        this.x *= xyzw;
        this.y *= xyzw;
        this.z *= xyzw;
        this.w *= xyzw;
        return this;
    }

    public Vec4f mul(double xyzw) {
        return mul((float) xyzw);
    }

    public Vec4f mul(int xyzw) {
        return mul((float) xyzw);
    }

    public Vec4f mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec4f mul(double x, double y) {
        return mul((float) x, (float) y);
    }

    public Vec4f mul(int x, int y) {
        return mul((float) x, (float) y);
    }

    public Vec4f mul(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec4f mul(double x, double y, double z) {
        return mul((float) x, (float) y, (float) z);
    }

    public Vec4f mul(int x, int y, int z) {
        return mul((float) x, (float) y, (float) z);
    }

    public Vec4f mul(float x, float y, float z, float w) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        this.w *= w;
        return this;
    }

    public Vec4f mul(double x, double y, double z, double w) {
        return mul((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f mul(int x, int y, int z, int w) {
        return mul((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f mul(Vec2f vector) {
        return mul(vector.x, vector.y);
    }

    public Vec4f mul(Vec2d vector) {
        return mul(vector.x, vector.y);
    }

    public Vec4f mul(Vec2i vector) {
        return mul(vector.x, vector.y);
    }

    public Vec4f mul(Vec3f vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec4f mul(Vec3d vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec4f mul(Vec3i vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec4f mul(Vec4f vector) {
        return mul(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f mul(Vec4d vector) {
        return mul(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f mul(Vec4i vector) {
        return mul(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4f div(float xyzw) {
        this.x /= xyzw;
        this.y /= xyzw;
        this.z /= xyzw;
        this.w /= xyzw;
        return this;
    }

    public Vec4f div(double xyzw) {
        return div((float) xyzw);
    }

    public Vec4f div(int xyzw) {
        return div((float) xyzw);
    }

    public Vec4f div(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec4f div(double x, double y) {
        return div((float) x, (float) y);
    }

    public Vec4f div(int x, int y) {
        return div((float) x, (float) y);
    }

    public Vec4f div(float x, float y, float z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec4f div(double x, double y, double z) {
        return div((float) x, (float) y, (float) z);
    }

    public Vec4f div(int x, int y, int z) {
        return div((float) x, (float) y, (float) z);
    }

    public Vec4f div(float x, float y, float z, float w) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        this.w /= w;
        return this;
    }

    public Vec4f div(double x, double y, double z, double w) {
        return div((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f div(int x, int y, int z, int w) {
        return div((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f div(Vec2f vector) {
        return div(vector.x, vector.y);
    }

    public Vec4f div(Vec2d vector) {
        return div(vector.x, vector.y);
    }

    public Vec4f div(Vec2i vector) {
        return div(vector.x, vector.y);
    }

    public Vec4f div(Vec3f vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec4f div(Vec3d vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec4f div(Vec3i vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec4f div(Vec4f vector) {
        return div(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f div(Vec4d vector) {
        return div(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f div(Vec4i vector) {
        return div(vector.x, vector.y, vector.z, vector.w);
    }


    public static float dst(float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2) {
        final float dx = x2 - x1;
        final float dy = y2 - y1;
        final float dz = z2 - z1;
        final float dw = w2 - w1;
        
        return Mathc.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
    }

    public static float dst(float x1, float y1, float z1, float w1, double x2, double y2, double z2, double w2) {
        return dst(x1, y1, z1, w1, (float) x2, (float) y2, (float) z2, (float) w2);
    }

    public static float dst(float x1, float y1, float z1, float w1, int x2, int y2, int z2, int w2) {
        return dst(x1, y1, z1, w1, (float) x2, (float) y2, (float) z2, (float) w2);
    }

    public static float dst(float x, float y, float z, float w, Vec4f vector) {
        return dst(x, y, z, w, vector.x, vector.y, vector.z, vector.w);
    }

    public static float dst(float x, float y, float z, float w, Vec4d vector) {
        return dst(x, y, z, w, vector.x, vector.y, vector.z, vector.w);
    }

    public static float dst(float x, float y, float z, float w, Vec4i vector) {
        return dst(x, y, z, w, vector.x, vector.y, vector.z, vector.w);
    }

    public static float dst(Vec4f vector, float x, float y, float z, float w) {
        return dst(vector.x, vector.y, vector.z, vector.w, x, y, z, w);
    }

    public static float dst(Vec4f vector, double x, double y, double z, double w) {
        return dst(vector.x, vector.y, vector.z, vector.w, x, y, z, w);
    }

    public static float dst(Vec4f vector, int x, int y, int z, int w) {
        return dst(vector.x, vector.y, vector.z, vector.w, x, y, z, w);
    }

    public static float dst(Vec4f vector1 , Vec4f vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static float dst(Vec4f vector1 , Vec4d vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static float dst(Vec4f vector1 , Vec4i vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public float dst(float x, float y, float z, float w) {
        return dst(this, x, y, z, w);
    }

    public float dst(double x, double y, double z, double w) {
        return dst(this, x, y, z, w);
    }

    public float dst(int x, int y, int z, int w) {
        return dst(this, x, y, z, w);
    }

    public float dst(Vec4f vector) {
        return dst(this, vector);
    }

    public float dst(Vec4d vector) {
        return dst(this, vector);
    }

    public float dst(Vec4i vector) {
        return dst(this, vector);
    }


    public static Vec4f shorter(Vec4f vector1, Vec4f vector2) {
        return (vector1.len2() < vector2.len2()) ? vector1 : vector2;
    }

    public static Vec4f longer(Vec4f vector1, Vec4f vector2) {
        return (vector1.len2() > vector2.len2()) ? vector1 : vector2;
    }

    public static Vec4f minCompsVec(Vec4f vector1, Vec4f vector2) {
        return new Vec4f(Math.min(vector1.x, vector2.x), Math.min(vector1.y, vector2.y), Math.min(vector1.z, vector2.z), Math.min(vector1.w, vector2.w));
    }

    public static Vec4f maxCompsVec(Vec4f vector1, Vec4f vector2) {
        return new Vec4f(Math.max(vector1.x, vector2.x), Math.max(vector1.y, vector2.y), Math.max(vector1.z, vector2.z), Math.max(vector1.w, vector2.w));
    }

    public Vec4f setShorter(Vec4f vector1, Vec4f vector2) {
        return set(shorter(vector1, vector2));
    }

    public Vec4f setLonger(Vec4f vector1, Vec4f vector2) {
        return set(longer(vector1, vector2));
    }

    public Vec4f setMinComps(Vec4f vector1, Vec4f vector2) {
        return set(Math.min(vector1.x, vector2.x), Math.min(vector1.y, vector2.y), Math.min(vector1.z, vector2.z), Math.min(vector1.w, vector2.w));
    }

    public Vec4f setMaxComps(Vec4f vector1, Vec4f vector2) {
        return set(Math.max(vector1.x, vector2.x), Math.max(vector1.y, vector2.y), Math.max(vector1.z, vector2.z), Math.max(vector1.w, vector2.w));
    }

    public float minComp() {
        return Math.min(x, Math.min(y, Math.min(z, w)));
    }

    public float maxComp() {
        return Math.max(x, Math.max(y, Math.max(z, w)));
    }


    public Vec4f zero() {
        return set(0);
    }

    public boolean isZero() {
        return x == 0 && y == 0 && z == 0 && w == 0;
    }

    public Vec4f zeroThatLess(float x, float y, float z, float w) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0;
        if(Math.abs(this.z) < Math.abs(z))
            this.z = 0;
        if(Math.abs(this.w) < Math.abs(w))
            this.w = 0;
        return this;
    }

    public Vec4f zeroThatLess(double x, double y, double z, double w) {
        return zeroThatLess((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroThatLess(int x, int y, int z, int w) {
        return zeroThatLess((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroThatLess(float xyzw) {
        return zeroThatLess(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroThatLess(double xyzw) {
        return zeroThatLess(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroThatLess(int xyzw) {
        return zeroThatLess(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroThatLess(Vec4f vector) {
        return zeroThatLess(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroThatLess(Vec4d vector) {
        return zeroThatLess(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroThatLess(Vec4i vector) {
        return zeroThatLess(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroThatZero(float x, float y, float z, float w) {
        if(x == 0)
            this.x = 0;
        if(y == 0)
            this.y = 0;
        if(z == 0)
            this.z = 0;
        if(w == 0)
            this.w = 0;
        return this;
    }

    public Vec4f zeroThatZero(double x, double y, double z, double w) {
        return zeroThatZero((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroThatZero(int x, int y, int z, int w) {
        return zeroThatZero((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroThatZero(float xyzw) {
        return zeroThatZero(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroThatZero(double xyzw) {
        return zeroThatZero(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroThatZero(int xyzw) {
        return zeroThatZero(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroThatZero(Vec4f vector) {
        return zeroThatZero(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroThatZero(Vec4d vector) {
        return zeroThatZero(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroThatZero(Vec4i vector) {
        return zeroThatZero(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroThatBigger(float x, float y, float z, float w) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0;
        if(Math.abs(this.z) > Math.abs(z))
            this.z = 0;
        if(Math.abs(this.w) > Math.abs(w))
            this.w = 0;
        return this;
    }

    public Vec4f zeroThatBigger(double x, double y, double z, double w) {
        return zeroThatBigger((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroThatBigger(int x, int y, int z, int w) {
        return zeroThatBigger((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroThatBigger(float xyzw) {
        return zeroThatBigger(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroThatBigger(double xyzw) {
        return zeroThatBigger(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroThatBigger(int xyzw) {
        return zeroThatBigger(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroThatBigger(Vec4f vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroThatBigger(Vec4d vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroThatBigger(Vec4i vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z, vector.w);
    }


    public float hypervolume() {
        return x * y * z * w;
    }


    public static float len2(float x, float y, float z, float w) {
        return x * x + y * y + z * z + w * w;
    }

    public static float len2(Vec4f vector) {
        return len2(vector.x, vector.y, vector.z, vector.w);
    }

    public static float len(float x, float y, float z, float w) {
        return Mathc.sqrt(len2(x, y, z, w));
    }

    public static float len(Vec4f vector) {
        return Mathc.sqrt(len2(vector.x, vector.y, vector.z, vector.w));
    }

    public float len2() {
        return len2(this);
    }

    public float len() {
        return len(this);
    }


    public Vec4f nor() {
        float len = len2();
        if(len == 0 || len == 1)
            return this;
        
        len = 1F / Mathc.sqrt(len);
        return mul(len);
    }


    public Vec4f abs() {
        if(x < 0) x *= -1;
        if(y < 0) y *= -1;
        if(z < 0) z *= -1;
        if(w < 0) w *= -1;
        return this;
    }


    public static Vec4f lerp(Vec4f vector, float startX, float startY, float startZ, float startW, float endX, float endY, float endZ, float endW, float t) {
        return vector.set(Maths.lerp(startX, endX, t), Maths.lerp(startY, endY, t), Maths.lerp(startZ, endZ, t), Maths.lerp(startW, endW, t));
    }

    public static Vec4f lerp(Vec4f vector, Vec4f start, Vec4f end, float t) {
        return lerp(vector, start.x, start.y, start.z, start.w, end.x, end.y, end.z, end.w, t);
    }

    public Vec4f lerp(float startX, float startY, float startZ, float startW, float endX, float endY, float endZ, float endW, float t) {
        return lerp(this, startX, startY, startZ, startW, endX, endY, endZ, endW, t);
    }

    public Vec4f lerp(Vec4f start, Vec4f end, float t) {
        return lerp(this, start, end, t);
    }


    public static float dot(float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2) {
        return x1 * x2 + y1 * y2 + z1 * z2 + w1 * w2;
    }

    public static float dot(Vec4f vector1, float x2, float y2, float z2, float w2) {
        return dot(vector1.x, vector1.y, vector1.z, vector1.w, x2, y2, z2, w2);
    }

    public static float dot(float x1, float y1, float z1, float w1, Vec4f vector2) {
        return dot(x1, y1, z1, w1, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static float dot(Vec4f vector1, Vec4f vector2) {
        return dot(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public float dot(Vec4f vector) {
        return dot(this, vector);
    }

    public float dot(float x, float y, float z, float w) {
        return dot(this, x, y, z, w);
    }


    public Vec4f frac() {
        x = Maths.frac(x);
        y = Maths.frac(y);
        z = Maths.frac(z);
        w = Maths.frac(w);
        return this;
    }


    public Vec4i signum() {
        return new Vec4i(Math.signum(x), Math.signum(y), Math.signum(z), Math.signum(w));
    }


    public static float rad(float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2) {
        final float cos = dot(x1, y1, z1, w1, x2, y2, z2, w2) / (len(x1, y1, z1, w1) * len(x2, y2, z2, w2));
        return Mathc.acos(Maths.clamp(cos, -1, 1));
    }

    public static float rad(Vec4f vector1, float x2, float y2, float z2, float w2) {
        return rad(vector1.x, vector1.y, vector1.z, vector1.w, x2, y2, z2, w2);
    }

    public static float rad(float x1, float y1, float z1, float w1, Vec4f vector2) {
        return rad(x1, y1, z1, w1, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static float rad(Vec4f vector1, Vec4f vector2) {
        return rad(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public float rad(float x, float y, float z, float w) {
        return rad(this, x, y, z, w);
    }

    public float rad(Vec4f vector) {
        return rad(this, vector);
    }

    public static float deg(float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2) {
        return rad(x1, y1, z1, w1, x2, y2, z2, w2) * Maths.toDeg;
    }

    public static float deg(Vec4f vector1, float x2, float y2, float z2, float w2) {
        return deg(vector1.x, vector1.y, vector1.z, vector1.w, x2, y2, z2, w2);
    }

    public static float deg(float x1, float y1, float z1, float w1, Vec4f vector2) {
        return deg(x1, y1, z1, w1, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static float deg(Vec4f vector1, Vec4f vector2) {
        return deg(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public float deg(float x, float y, float z, float w) {
        return deg(this, x, y, z, w);
    }

    public float deg(Vec4f vector) {
        return deg(this, vector);
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

    public int wFloor() {
        return Maths.floor(w);
    }

    public int wRound() {
        return Maths.round(w);
    }

    public int wCeil() {
        return Maths.ceil(w);
    }


    public Vec4f floor() {
        x = Maths.floor(x);
        y = Maths.floor(y);
        z = Maths.floor(z);
        w = Maths.floor(w);
        return this;
    }

    public Vec4f round() {
        x = Maths.round(x);
        y = Maths.round(y);
        z = Maths.round(z);
        w = Maths.round(w);
        return this;
    }

    public Vec4f ceil() {
        x = Maths.ceil(x);
        y = Maths.ceil(y);
        z = Maths.ceil(z);
        w = Maths.ceil(w);
        return this;
    }


    public Vec4d castDouble() {
        return new Vec4d(this);
    }

    public Vec4i castInt() {
        return new Vec4i(this);
    }


    public Vec4f clampLength(double max) {
        final float len = len();
        if(len <= max)
            return this;
        return nor().mul(max);
    }


    public Vec4f reduce(double reduceX, double reduceY, double reduceZ, double reduceW) {
        final float len = len();
        return nor().mul(len - reduceX, len - reduceY, len - reduceZ, len - reduceW);
    }


    public Vec4f copy() {
        return new Vec4f(this);
    }

    public static boolean equals(float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2) {
        return x1 == x2 && y1 == y2 && z1 == z2 && w1 == w2;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        
        final Vec4f vec = (Vec4f) object;
        return Vec4f.equals(x, y, z, w, vec.x, vec.y, vec.z, vec.w);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z + ", " + w;
    }

}