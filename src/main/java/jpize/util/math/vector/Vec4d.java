package jpize.util.math.vector;

import jpize.util.math.Maths;
import java.util.Objects;

public class Vec4d {

    public double x;
    public double y;
    public double z;
    public double w;

    public Vec4d() { }

    public Vec4d(float xyzw) {
        this.set(xyzw);
    }

    public Vec4d(double xyzw) {
        this.set(xyzw);
    }

    public Vec4d(int xyzw) {
        this.set(xyzw);
    }

    public Vec4d(float x, float y) {
        this.set(x, y);
    }

    public Vec4d(double x, double y) {
        this.set(x, y);
    }

    public Vec4d(int x, int y) {
        this.set(x, y);
    }

    public Vec4d(float x, float y, float z) {
        this.set(x, y, z);
    }

    public Vec4d(double x, double y, double z) {
        this.set(x, y, z);
    }

    public Vec4d(int x, int y, int z) {
        this.set(x, y, z);
    }

    public Vec4d(float x, float y, float z, float w) {
        this.set(x, y, z, w);
    }

    public Vec4d(double x, double y, double z, double w) {
        this.set(x, y, z, w);
    }

    public Vec4d(int x, int y, int z, int w) {
        this.set(x, y, z, w);
    }

    public Vec4d(Vec2f vector) {
        this.set(vector.x, vector.y);
    }

    public Vec4d(Vec2d vector) {
        this.set(vector.x, vector.y);
    }

    public Vec4d(Vec2i vector) {
        this.set(vector.x, vector.y);
    }

    public Vec4d(Vec3f vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec4d(Vec3d vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec4d(Vec3i vector) {
        this.set(vector.x, vector.y, vector.z);
    }

    public Vec4d(Vec4f vector) {
        this.set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d(Vec4d vector) {
        this.set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d(Vec4i vector) {
        this.set(vector.x, vector.y, vector.z, vector.w);
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

    public double getW() {
        return w;
    }


    public Vec4d set(double xyzw) {
        this.x = xyzw;
        this.y = xyzw;
        this.z = xyzw;
        this.w = xyzw;
        return this;
    }

    public Vec4d set(float xyzw) {
        return set((double) xyzw);
    }

    public Vec4d set(int xyzw) {
        return set((double) xyzw);
    }

    public Vec4d set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec4d set(float x, float y) {
        return set((double) x, (double) y);
    }

    public Vec4d set(int x, int y) {
        return set((double) x, (double) y);
    }

    public Vec4d set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec4d set(float x, float y, float z) {
        return set((double) x, (double) y, (double) z);
    }

    public Vec4d set(int x, int y, int z) {
        return set((double) x, (double) y, (double) z);
    }

    public Vec4d set(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Vec4d set(float x, float y, float z, float w) {
        return set((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d set(int x, int y, int z, int w) {
        return set((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d set(Vec2f vector) {
        return set(vector.x, vector.y);
    }

    public Vec4d set(Vec2d vector) {
        return set(vector.x, vector.y);
    }

    public Vec4d set(Vec2i vector) {
        return set(vector.x, vector.y);
    }

    public Vec4d set(Vec3f vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec4d set(Vec3d vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec4d set(Vec3i vector) {
        return set(vector.x, vector.y, vector.z);
    }

    public Vec4d set(Vec4f vector) {
        return set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d set(Vec4d vector) {
        return set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d set(Vec4i vector) {
        return set(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4d add(double xyzw) {
        this.x += xyzw;
        this.y += xyzw;
        this.z += xyzw;
        this.w += xyzw;
        return this;
    }

    public Vec4d add(float xyzw) {
        return add((double) xyzw);
    }

    public Vec4d add(int xyzw) {
        return add((double) xyzw);
    }

    public Vec4d add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec4d add(float x, float y) {
        return add((double) x, (double) y);
    }

    public Vec4d add(int x, int y) {
        return add((double) x, (double) y);
    }

    public Vec4d add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec4d add(float x, float y, float z) {
        return add((double) x, (double) y, (double) z);
    }

    public Vec4d add(int x, int y, int z) {
        return add((double) x, (double) y, (double) z);
    }

    public Vec4d add(double x, double y, double z, double w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    public Vec4d add(float x, float y, float z, float w) {
        return add((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d add(int x, int y, int z, int w) {
        return add((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d add(Vec2f vector) {
        return add(vector.x, vector.y);
    }

    public Vec4d add(Vec2d vector) {
        return add(vector.x, vector.y);
    }

    public Vec4d add(Vec2i vector) {
        return add(vector.x, vector.y);
    }

    public Vec4d add(Vec3f vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec4d add(Vec3d vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec4d add(Vec3i vector) {
        return add(vector.x, vector.y, vector.z);
    }

    public Vec4d add(Vec4f vector) {
        return add(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d add(Vec4d vector) {
        return add(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d add(Vec4i vector) {
        return add(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4d sub(double xyzw) {
        this.x -= xyzw;
        this.y -= xyzw;
        this.z -= xyzw;
        this.w -= xyzw;
        return this;
    }

    public Vec4d sub(float xyzw) {
        return sub((double) xyzw);
    }

    public Vec4d sub(int xyzw) {
        return sub((double) xyzw);
    }

    public Vec4d sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec4d sub(float x, float y) {
        return sub((double) x, (double) y);
    }

    public Vec4d sub(int x, int y) {
        return sub((double) x, (double) y);
    }

    public Vec4d sub(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec4d sub(float x, float y, float z) {
        return sub((double) x, (double) y, (double) z);
    }

    public Vec4d sub(int x, int y, int z) {
        return sub((double) x, (double) y, (double) z);
    }

    public Vec4d sub(double x, double y, double z, double w) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        this.w -= w;
        return this;
    }

    public Vec4d sub(float x, float y, float z, float w) {
        return sub((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d sub(int x, int y, int z, int w) {
        return sub((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d sub(Vec2f vector) {
        return sub(vector.x, vector.y);
    }

    public Vec4d sub(Vec2d vector) {
        return sub(vector.x, vector.y);
    }

    public Vec4d sub(Vec2i vector) {
        return sub(vector.x, vector.y);
    }

    public Vec4d sub(Vec3f vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec4d sub(Vec3d vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec4d sub(Vec3i vector) {
        return sub(vector.x, vector.y, vector.z);
    }

    public Vec4d sub(Vec4f vector) {
        return sub(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d sub(Vec4d vector) {
        return sub(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d sub(Vec4i vector) {
        return sub(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4d mul(double xyzw) {
        this.x *= xyzw;
        this.y *= xyzw;
        this.z *= xyzw;
        this.w *= xyzw;
        return this;
    }

    public Vec4d mul(float xyzw) {
        return mul((double) xyzw);
    }

    public Vec4d mul(int xyzw) {
        return mul((double) xyzw);
    }

    public Vec4d mul(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec4d mul(float x, float y) {
        return mul((double) x, (double) y);
    }

    public Vec4d mul(int x, int y) {
        return mul((double) x, (double) y);
    }

    public Vec4d mul(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec4d mul(float x, float y, float z) {
        return mul((double) x, (double) y, (double) z);
    }

    public Vec4d mul(int x, int y, int z) {
        return mul((double) x, (double) y, (double) z);
    }

    public Vec4d mul(double x, double y, double z, double w) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        this.w *= w;
        return this;
    }

    public Vec4d mul(float x, float y, float z, float w) {
        return mul((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d mul(int x, int y, int z, int w) {
        return mul((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d mul(Vec2f vector) {
        return mul(vector.x, vector.y);
    }

    public Vec4d mul(Vec2d vector) {
        return mul(vector.x, vector.y);
    }

    public Vec4d mul(Vec2i vector) {
        return mul(vector.x, vector.y);
    }

    public Vec4d mul(Vec3f vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec4d mul(Vec3d vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec4d mul(Vec3i vector) {
        return mul(vector.x, vector.y, vector.z);
    }

    public Vec4d mul(Vec4f vector) {
        return mul(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d mul(Vec4d vector) {
        return mul(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d mul(Vec4i vector) {
        return mul(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4d div(double xyzw) {
        this.x /= xyzw;
        this.y /= xyzw;
        this.z /= xyzw;
        this.w /= xyzw;
        return this;
    }

    public Vec4d div(float xyzw) {
        return div((double) xyzw);
    }

    public Vec4d div(int xyzw) {
        return div((double) xyzw);
    }

    public Vec4d div(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec4d div(float x, float y) {
        return div((double) x, (double) y);
    }

    public Vec4d div(int x, int y) {
        return div((double) x, (double) y);
    }

    public Vec4d div(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec4d div(float x, float y, float z) {
        return div((double) x, (double) y, (double) z);
    }

    public Vec4d div(int x, int y, int z) {
        return div((double) x, (double) y, (double) z);
    }

    public Vec4d div(double x, double y, double z, double w) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        this.w /= w;
        return this;
    }

    public Vec4d div(float x, float y, float z, float w) {
        return div((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d div(int x, int y, int z, int w) {
        return div((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d div(Vec2f vector) {
        return div(vector.x, vector.y);
    }

    public Vec4d div(Vec2d vector) {
        return div(vector.x, vector.y);
    }

    public Vec4d div(Vec2i vector) {
        return div(vector.x, vector.y);
    }

    public Vec4d div(Vec3f vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec4d div(Vec3d vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec4d div(Vec3i vector) {
        return div(vector.x, vector.y, vector.z);
    }

    public Vec4d div(Vec4f vector) {
        return div(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d div(Vec4d vector) {
        return div(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d div(Vec4i vector) {
        return div(vector.x, vector.y, vector.z, vector.w);
    }


    public static double dst(double x1, double y1, double z1, double w1, double x2, double y2, double z2, double w2) {
        final double dx = x2 - x1;
        final double dy = y2 - y1;
        final double dz = z2 - z1;
        final double dw = w2 - w1;
        
        return Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
    }

    public static double dst(double x1, double y1, double z1, double w1, float x2, float y2, float z2, float w2) {
        return dst(x1, y1, z1, w1, (double) x2, (double) y2, (double) z2, (double) w2);
    }

    public static double dst(double x1, double y1, double z1, double w1, int x2, int y2, int z2, int w2) {
        return dst(x1, y1, z1, w1, (double) x2, (double) y2, (double) z2, (double) w2);
    }

    public static double dst(double x, double y, double z, double w, Vec4f vector) {
        return dst(x, y, z, w, vector.x, vector.y, vector.z, vector.w);
    }

    public static double dst(double x, double y, double z, double w, Vec4d vector) {
        return dst(x, y, z, w, vector.x, vector.y, vector.z, vector.w);
    }

    public static double dst(double x, double y, double z, double w, Vec4i vector) {
        return dst(x, y, z, w, vector.x, vector.y, vector.z, vector.w);
    }

    public static double dst(Vec4d vector, float x, float y, float z, float w) {
        return dst(vector.x, vector.y, vector.z, vector.w, x, y, z, w);
    }

    public static double dst(Vec4d vector, double x, double y, double z, double w) {
        return dst(vector.x, vector.y, vector.z, vector.w, x, y, z, w);
    }

    public static double dst(Vec4d vector, int x, int y, int z, int w) {
        return dst(vector.x, vector.y, vector.z, vector.w, x, y, z, w);
    }

    public static double dst(Vec4d vector1 , Vec4f vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static double dst(Vec4d vector1 , Vec4d vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static double dst(Vec4d vector1 , Vec4i vector2) {
        return dst(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public double dst(float x, float y, float z, float w) {
        return dst(this, x, y, z, w);
    }

    public double dst(double x, double y, double z, double w) {
        return dst(this, x, y, z, w);
    }

    public double dst(int x, int y, int z, int w) {
        return dst(this, x, y, z, w);
    }

    public double dst(Vec4f vector) {
        return dst(this, vector);
    }

    public double dst(Vec4d vector) {
        return dst(this, vector);
    }

    public double dst(Vec4i vector) {
        return dst(this, vector);
    }


    public static Vec4d shorter(Vec4d vector1, Vec4d vector2) {
        return (vector1.len2() < vector2.len2()) ? vector1 : vector2;
    }

    public static Vec4d longer(Vec4d vector1, Vec4d vector2) {
        return (vector1.len2() > vector2.len2()) ? vector1 : vector2;
    }


    public static Vec4d minCompsVec(Vec4d vector1, Vec4d vector2) {
        return new Vec4d(Math.min(vector1.x, vector2.x), Math.min(vector1.y, vector2.y), Math.min(vector1.z, vector2.z), Math.min(vector1.w, vector2.w));
    }

    public static Vec4d maxCompsVec(Vec4d vector1, Vec4d vector2) {
        return new Vec4d(Math.max(vector1.x, vector2.x), Math.max(vector1.y, vector2.y), Math.max(vector1.z, vector2.z), Math.max(vector1.w, vector2.w));
    }


    public Vec4d setShorter(Vec4d vector1, Vec4d vector2) {
        return set(shorter(vector1, vector2));
    }

    public Vec4d setLonger(Vec4d vector1, Vec4d vector2) {
        return set(longer(vector1, vector2));
    }


    public Vec4d setMinComps(Vec4d vector1, double x2, double y2, double z2, double w2) {
        return set(Math.min(vector1.x, x2), Math.min(vector1.y, y2), Math.min(vector1.z, z2), Math.min(vector1.w, w2));
    }

    public Vec4d setMinComps(Vec4d vector1, double xyzw2) {
        return setMinComps(vector1, xyzw2, xyzw2, xyzw2, xyzw2);
    }

    public Vec4d setMinComps(Vec4d vector1, Vec4d vector2) {
        return setMinComps(vector1, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public Vec4d setMaxComps(Vec4d vector1, double x2, double y2, double z2, double w2) {
        return set(Math.max(vector1.x, x2), Math.max(vector1.y, y2), Math.max(vector1.z, z2), Math.max(vector1.w, w2));
    }

    public Vec4d setMaxComps(Vec4d vector1, double xyzw2) {
        return setMaxComps(vector1, xyzw2, xyzw2, xyzw2, xyzw2);
    }

    public Vec4d setMaxComps(Vec4d vector1, Vec4d vector2) {
        return setMaxComps(vector1, vector2.x, vector2.y, vector2.z, vector2.w);
    }


    public double minComp() {
        return Math.min(x, Math.min(y, Math.min(z, w)));
    }

    public double maxComp() {
        return Math.max(x, Math.max(y, Math.max(z, w)));
    }


    public Vec4d zero() {
        return set(0);
    }

    public boolean isZero() {
        return x == 0 && y == 0 && z == 0 && w == 0;
    }

    public Vec4d zeroThatLess(double x, double y, double z, double w) {
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

    public Vec4d zeroThatLess(float x, float y, float z, float w) {
        return zeroThatLess((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d zeroThatLess(int x, int y, int z, int w) {
        return zeroThatLess((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d zeroThatLess(float xyzw) {
        return zeroThatLess(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4d zeroThatLess(double xyzw) {
        return zeroThatLess(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4d zeroThatLess(int xyzw) {
        return zeroThatLess(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4d zeroThatLess(Vec4f vector) {
        return zeroThatLess(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d zeroThatLess(Vec4d vector) {
        return zeroThatLess(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d zeroThatLess(Vec4i vector) {
        return zeroThatLess(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d zeroThatZero(double x, double y, double z, double w) {
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

    public Vec4d zeroThatZero(float x, float y, float z, float w) {
        return zeroThatZero((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d zeroThatZero(int x, int y, int z, int w) {
        return zeroThatZero((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d zeroThatZero(float xyzw) {
        return zeroThatZero(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4d zeroThatZero(double xyzw) {
        return zeroThatZero(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4d zeroThatZero(int xyzw) {
        return zeroThatZero(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4d zeroThatZero(Vec4f vector) {
        return zeroThatZero(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d zeroThatZero(Vec4d vector) {
        return zeroThatZero(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d zeroThatZero(Vec4i vector) {
        return zeroThatZero(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d zeroThatBigger(double x, double y, double z, double w) {
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

    public Vec4d zeroThatBigger(float x, float y, float z, float w) {
        return zeroThatBigger((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d zeroThatBigger(int x, int y, int z, int w) {
        return zeroThatBigger((double) x, (double) y, (double) z, (double) w);
    }

    public Vec4d zeroThatBigger(float xyzw) {
        return zeroThatBigger(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4d zeroThatBigger(double xyzw) {
        return zeroThatBigger(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4d zeroThatBigger(int xyzw) {
        return zeroThatBigger(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4d zeroThatBigger(Vec4f vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d zeroThatBigger(Vec4d vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4d zeroThatBigger(Vec4i vector) {
        return zeroThatBigger(vector.x, vector.y, vector.z, vector.w);
    }


    public double hypervolume() {
        return x * y * z * w;
    }


    public static double len2(double x, double y, double z, double w) {
        return x * x + y * y + z * z + w * w;
    }

    public static double len2(Vec4d vector) {
        return len2(vector.x, vector.y, vector.z, vector.w);
    }

    public static double len(double x, double y, double z, double w) {
        return Math.sqrt(len2(x, y, z, w));
    }

    public static double len(Vec4d vector) {
        return Math.sqrt(len2(vector.x, vector.y, vector.z, vector.w));
    }

    public double len2() {
        return len2(this);
    }

    public double len() {
        return len(this);
    }


    public Vec4d nor() {
        double len = len2();
        if(len == 0 || len == 1)
            return this;
        
        len = 1D / Math.sqrt(len);
        return mul(len);
    }


    public Vec4d abs() {
        if(x < 0) x *= -1;
        if(y < 0) y *= -1;
        if(z < 0) z *= -1;
        if(w < 0) w *= -1;
        return this;
    }


    public static Vec4d lerp(Vec4d vector, double startX, double startY, double startZ, double startW, double endX, double endY, double endZ, double endW, double t) {
        return vector.set(Maths.lerp(startX, endX, t), Maths.lerp(startY, endY, t), Maths.lerp(startZ, endZ, t), Maths.lerp(startW, endW, t));
    }

    public static Vec4d lerp(Vec4d vector, Vec4d start, Vec4d end, double t) {
        return lerp(vector, start.x, start.y, start.z, start.w, end.x, end.y, end.z, end.w, t);
    }

    public Vec4d lerp(double startX, double startY, double startZ, double startW, double endX, double endY, double endZ, double endW, double t) {
        return lerp(this, startX, startY, startZ, startW, endX, endY, endZ, endW, t);
    }

    public Vec4d lerp(Vec4d start, Vec4d end, double t) {
        return lerp(this, start, end, t);
    }


    public static double dot(double x1, double y1, double z1, double w1, double x2, double y2, double z2, double w2) {
        return x1 * x2 + y1 * y2 + z1 * z2 + w1 * w2;
    }

    public static double dot(Vec4d vector1, double x2, double y2, double z2, double w2) {
        return dot(vector1.x, vector1.y, vector1.z, vector1.w, x2, y2, z2, w2);
    }

    public static double dot(double x1, double y1, double z1, double w1, Vec4d vector2) {
        return dot(x1, y1, z1, w1, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static double dot(Vec4d vector1, Vec4d vector2) {
        return dot(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public double dot(Vec4d vector) {
        return dot(this, vector);
    }

    public double dot(double x, double y, double z, double w) {
        return dot(this, x, y, z, w);
    }


    public Vec4d frac() {
        x = Maths.frac(x);
        y = Maths.frac(y);
        z = Maths.frac(z);
        w = Maths.frac(w);
        return this;
    }


    public Vec4i signum() {
        return new Vec4i(Math.signum(x), Math.signum(y), Math.signum(z), Math.signum(w));
    }


    public static double rad(double x1, double y1, double z1, double w1, double x2, double y2, double z2, double w2) {
        final double cos = dot(x1, y1, z1, w1, x2, y2, z2, w2) / (len(x1, y1, z1, w1) * len(x2, y2, z2, w2));
        return Math.acos(Maths.clamp(cos, -1, 1));
    }

    public static double rad(Vec4d vector1, double x2, double y2, double z2, double w2) {
        return rad(vector1.x, vector1.y, vector1.z, vector1.w, x2, y2, z2, w2);
    }

    public static double rad(double x1, double y1, double z1, double w1, Vec4d vector2) {
        return rad(x1, y1, z1, w1, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static double rad(Vec4d vector1, Vec4d vector2) {
        return rad(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public double rad(double x, double y, double z, double w) {
        return rad(this, x, y, z, w);
    }

    public double rad(Vec4d vector) {
        return rad(this, vector);
    }

    public static double deg(double x1, double y1, double z1, double w1, double x2, double y2, double z2, double w2) {
        return rad(x1, y1, z1, w1, x2, y2, z2, w2) * Maths.toDeg;
    }

    public static double deg(Vec4d vector1, double x2, double y2, double z2, double w2) {
        return deg(vector1.x, vector1.y, vector1.z, vector1.w, x2, y2, z2, w2);
    }

    public static double deg(double x1, double y1, double z1, double w1, Vec4d vector2) {
        return deg(x1, y1, z1, w1, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static double deg(Vec4d vector1, Vec4d vector2) {
        return deg(vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public double deg(double x, double y, double z, double w) {
        return deg(this, x, y, z, w);
    }

    public double deg(Vec4d vector) {
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


    public Vec4d floor() {
        x = Maths.floor(x);
        y = Maths.floor(y);
        z = Maths.floor(z);
        w = Maths.floor(w);
        return this;
    }

    public Vec4d round() {
        x = Maths.round(x);
        y = Maths.round(y);
        z = Maths.round(z);
        w = Maths.round(w);
        return this;
    }

    public Vec4d ceil() {
        x = Maths.ceil(x);
        y = Maths.ceil(y);
        z = Maths.ceil(z);
        w = Maths.ceil(w);
        return this;
    }


    public Vec4f castFloat() {
        return new Vec4f(this);
    }

    public Vec4i castInt() {
        return new Vec4i(this);
    }


    public Vec4d clampLength(double max) {
        final double len = len();
        if(len <= max)
            return this;
        return nor().mul(max);
    }


    public Vec4d reduce(double reduceX, double reduceY, double reduceZ, double reduceW) {
        final double len = len();
        return nor().mul(len - reduceX, len - reduceY, len - reduceZ, len - reduceW);
    }


    public Vec4d copy() {
        return new Vec4d(this);
    }

    public static boolean equals(double x1, double y1, double z1, double w1, double x2, double y2, double z2, double w2) {
        return x1 == x2 && y1 == y2 && z1 == z2 && w1 == w2;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        
        final Vec4d vec = (Vec4d) object;
        return Vec4d.equals(x, y, z, w, vec.x, vec.y, vec.z, vec.w);
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