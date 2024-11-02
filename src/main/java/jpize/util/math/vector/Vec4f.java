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
        return this.set((float) xyzw);
    }

    public Vec4f set(int xyzw) {
        return this.set((float) xyzw);
    }

    public Vec4f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec4f set(double x, double y) {
        return this.set((float) x, (float) y);
    }

    public Vec4f set(int x, int y) {
        return this.set((float) x, (float) y);
    }

    public Vec4f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vec4f set(double x, double y, double z) {
        return this.set((float) x, (float) y, (float) z);
    }

    public Vec4f set(int x, int y, int z) {
        return this.set((float) x, (float) y, (float) z);
    }

    public Vec4f set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Vec4f set(double x, double y, double z, double w) {
        return this.set((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f set(int x, int y, int z, int w) {
        return this.set((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f set(Vec2f vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec4f set(Vec2d vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec4f set(Vec2i vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec4f set(Vec3f vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    public Vec4f set(Vec3d vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    public Vec4f set(Vec3i vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    public Vec4f set(Vec4f vector) {
        return this.set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f set(Vec4d vector) {
        return this.set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f set(Vec4i vector) {
        return this.set(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4f add(float xyzw) {
        this.x += xyzw;
        this.y += xyzw;
        this.z += xyzw;
        this.w += xyzw;
        return this;
    }

    public Vec4f add(double xyzw) {
        return this.add((float) xyzw);
    }

    public Vec4f add(int xyzw) {
        return this.add((float) xyzw);
    }

    public Vec4f add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec4f add(double x, double y) {
        return this.add((float) x, (float) y);
    }

    public Vec4f add(int x, int y) {
        return this.add((float) x, (float) y);
    }

    public Vec4f add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vec4f add(double x, double y, double z) {
        return this.add((float) x, (float) y, (float) z);
    }

    public Vec4f add(int x, int y, int z) {
        return this.add((float) x, (float) y, (float) z);
    }

    public Vec4f add(float x, float y, float z, float w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    public Vec4f add(double x, double y, double z, double w) {
        return this.add((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f add(int x, int y, int z, int w) {
        return this.add((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f add(Vec2f vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec4f add(Vec2d vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec4f add(Vec2i vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec4f add(Vec3f vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vec4f add(Vec3d vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vec4f add(Vec3i vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vec4f add(Vec4f vector) {
        return this.add(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f add(Vec4d vector) {
        return this.add(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f add(Vec4i vector) {
        return this.add(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4f sub(float xyzw) {
        this.x -= xyzw;
        this.y -= xyzw;
        this.z -= xyzw;
        this.w -= xyzw;
        return this;
    }

    public Vec4f sub(double xyzw) {
        return this.sub((float) xyzw);
    }

    public Vec4f sub(int xyzw) {
        return this.sub((float) xyzw);
    }

    public Vec4f sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec4f sub(double x, double y) {
        return this.sub((float) x, (float) y);
    }

    public Vec4f sub(int x, int y) {
        return this.sub((float) x, (float) y);
    }

    public Vec4f sub(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vec4f sub(double x, double y, double z) {
        return this.sub((float) x, (float) y, (float) z);
    }

    public Vec4f sub(int x, int y, int z) {
        return this.sub((float) x, (float) y, (float) z);
    }

    public Vec4f sub(float x, float y, float z, float w) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        this.w -= w;
        return this;
    }

    public Vec4f sub(double x, double y, double z, double w) {
        return this.sub((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f sub(int x, int y, int z, int w) {
        return this.sub((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f sub(Vec2f vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec4f sub(Vec2d vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec4f sub(Vec2i vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec4f sub(Vec3f vector) {
        return this.sub(vector.x, vector.y, vector.z);
    }

    public Vec4f sub(Vec3d vector) {
        return this.sub(vector.x, vector.y, vector.z);
    }

    public Vec4f sub(Vec3i vector) {
        return this.sub(vector.x, vector.y, vector.z);
    }

    public Vec4f sub(Vec4f vector) {
        return this.sub(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f sub(Vec4d vector) {
        return this.sub(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f sub(Vec4i vector) {
        return this.sub(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4f mul(float xyzw) {
        this.x *= xyzw;
        this.y *= xyzw;
        this.z *= xyzw;
        this.w *= xyzw;
        return this;
    }

    public Vec4f mul(double xyzw) {
        return this.mul((float) xyzw);
    }

    public Vec4f mul(int xyzw) {
        return this.mul((float) xyzw);
    }

    public Vec4f mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec4f mul(double x, double y) {
        return this.mul((float) x, (float) y);
    }

    public Vec4f mul(int x, int y) {
        return this.mul((float) x, (float) y);
    }

    public Vec4f mul(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vec4f mul(double x, double y, double z) {
        return this.mul((float) x, (float) y, (float) z);
    }

    public Vec4f mul(int x, int y, int z) {
        return this.mul((float) x, (float) y, (float) z);
    }

    public Vec4f mul(float x, float y, float z, float w) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        this.w *= w;
        return this;
    }

    public Vec4f mul(double x, double y, double z, double w) {
        return this.mul((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f mul(int x, int y, int z, int w) {
        return this.mul((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f mul(Vec2f vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec4f mul(Vec2d vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec4f mul(Vec2i vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec4f mul(Vec3f vector) {
        return this.mul(vector.x, vector.y, vector.z);
    }

    public Vec4f mul(Vec3d vector) {
        return this.mul(vector.x, vector.y, vector.z);
    }

    public Vec4f mul(Vec3i vector) {
        return this.mul(vector.x, vector.y, vector.z);
    }

    public Vec4f mul(Vec4f vector) {
        return this.mul(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f mul(Vec4d vector) {
        return this.mul(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f mul(Vec4i vector) {
        return this.mul(vector.x, vector.y, vector.z, vector.w);
    }


    public Vec4f div(float xyzw) {
        this.x /= xyzw;
        this.y /= xyzw;
        this.z /= xyzw;
        this.w /= xyzw;
        return this;
    }

    public Vec4f div(double xyzw) {
        return this.div((float) xyzw);
    }

    public Vec4f div(int xyzw) {
        return this.div((float) xyzw);
    }

    public Vec4f div(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec4f div(double x, double y) {
        return this.div((float) x, (float) y);
    }

    public Vec4f div(int x, int y) {
        return this.div((float) x, (float) y);
    }

    public Vec4f div(float x, float y, float z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vec4f div(double x, double y, double z) {
        return this.div((float) x, (float) y, (float) z);
    }

    public Vec4f div(int x, int y, int z) {
        return this.div((float) x, (float) y, (float) z);
    }

    public Vec4f div(float x, float y, float z, float w) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        this.w /= w;
        return this;
    }

    public Vec4f div(double x, double y, double z, double w) {
        return this.div((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f div(int x, int y, int z, int w) {
        return this.div((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f div(Vec2f vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec4f div(Vec2d vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec4f div(Vec2i vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec4f div(Vec3f vector) {
        return this.div(vector.x, vector.y, vector.z);
    }

    public Vec4f div(Vec3d vector) {
        return this.div(vector.x, vector.y, vector.z);
    }

    public Vec4f div(Vec3i vector) {
        return this.div(vector.x, vector.y, vector.z);
    }

    public Vec4f div(Vec4f vector) {
        return this.div(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f div(Vec4d vector) {
        return this.div(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f div(Vec4i vector) {
        return this.div(vector.x, vector.y, vector.z, vector.w);
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

    public Vec4f setShorter(Vec4f vector1, Vec4f vector2) {
        return this.set(shorter(vector1, vector2));
    }

    public Vec4f setLonger(Vec4f vector1, Vec4f vector2) {
        return this.set(longer(vector1, vector2));
    }


    public float minComp() {
        return Math.min(x, Math.min(y, Math.min(z, w)));
    }

    public float maxComp() {
        return Math.max(x, Math.max(y, Math.max(z, w)));
    }


    public static Vec4f minComps(Vec4f dst, float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2) {
        return dst.set(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.min(w1, w2));
    }

    public static Vec4f minComps(Vec4f dst, float x1, float y1, float z1, float w1, Vec4f vector2) {
        return minComps(dst, x1, y1, z1, w1, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static Vec4f minComps(Vec4f dst, Vec4f vector1, float x2, float y2, float z2, float w2) {
        return minComps(dst, vector1.x, vector1.y, vector1.z, vector1.w, x2, y2, z2, w2);
    }

    public static Vec4f minComps(Vec4f dst, Vec4f vector1, float xyzw2) {
        return minComps(dst, vector1.x, vector1.y, vector1.z, vector1.w, xyzw2, xyzw2, xyzw2, xyzw2);
    }

    public static Vec4f minComps(Vec4f dst, Vec4f vector1, Vec4f vector2) {
        return minComps(dst, vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public Vec4f setMinComps(float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2) {
        return minComps(this, x1, y1, z1, w1, x2, y2, z2, w2);
    }

    public Vec4f setMinComps(float x1, float y1, float z1, float w1, Vec4f vector2) {
        return minComps(this, x1, y1, z1, w1, vector2);
    }

    public Vec4f setMinComps(Vec4f vector1, float x2, float y2, float z2, float w2) {
        return minComps(this, vector1, x2, y2, z2, w2);
    }

    public Vec4f setMinComps(Vec4f vector1, float xyzw2) {
        return minComps(this, vector1, xyzw2);
    }

    public Vec4f setMinComps(Vec4f vector1, Vec4f vector2) {
        return minComps(this, vector1, vector2);
    }

    public Vec4f setMinComps(float x2, float y2, float z2, float w2) {
        return this.setMinComps(this, x2, y2, z2, w2);
    }

    public Vec4f setMinComps(float xyzw2) {
        return this.setMinComps(this, xyzw2);
    }

    public Vec4f setMinComps(Vec4f vector2) {
        return this.setMinComps(this, vector2);
    }


    public static Vec4f maxComps(Vec4f dst, float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2) {
        return dst.set(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2), Math.max(w1, w2));
    }

    public static Vec4f maxComps(Vec4f dst, float x1, float y1, float z1, float w1, Vec4f vector2) {
        return maxComps(dst, x1, y1, z1, w1, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public static Vec4f maxComps(Vec4f dst, Vec4f vector1, float x2, float y2, float z2, float w2) {
        return maxComps(dst, vector1.x, vector1.y, vector1.z, vector1.w, x2, y2, z2, w2);
    }

    public static Vec4f maxComps(Vec4f dst, Vec4f vector1, float xyzw2) {
        return maxComps(dst, vector1.x, vector1.y, vector1.z, vector1.w, xyzw2, xyzw2, xyzw2, xyzw2);
    }

    public static Vec4f maxComps(Vec4f dst, Vec4f vector1, Vec4f vector2) {
        return maxComps(dst, vector1.x, vector1.y, vector1.z, vector1.w, vector2.x, vector2.y, vector2.z, vector2.w);
    }

    public Vec4f setMaxComps(float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2) {
        return maxComps(this, x1, y1, z1, w1, x2, y2, z2, w2);
    }

    public Vec4f setMaxComps(float x1, float y1, float z1, float w1, Vec4f vector2) {
        return maxComps(this, x1, y1, z1, w1, vector2);
    }

    public Vec4f setMaxComps(Vec4f vector1, float x2, float y2, float z2, float w2) {
        return maxComps(this, vector1, x2, y2, z2, w2);
    }

    public Vec4f setMaxComps(Vec4f vector1, float xyzw2) {
        return maxComps(this, vector1, xyzw2);
    }

    public Vec4f setMaxComps(Vec4f vector1, Vec4f vector2) {
        return maxComps(this, vector1, vector2);
    }

    public Vec4f setMaxComps(float x2, float y2, float z2, float w2) {
        return this.setMaxComps(this, x2, y2, z2, w2);
    }

    public Vec4f setMaxComps(float xyzw2) {
        return this.setMaxComps(this, xyzw2);
    }

    public Vec4f setMaxComps(Vec4f vector2) {
        return this.setMaxComps(this, vector2);
    }


    public boolean isShorter(float comparableX, float comparableY, float comparableZ, float comparableW) {
        return this.len2() < len2(comparableX, comparableY, comparableZ, comparableW);
    }

    public boolean isShorter(Vec4f comparable) {
        return this.isShorter(comparable.x, comparable.y, comparable.z, comparable.w);
    }

    public boolean isLonger(float comparableX, float comparableY, float comparableZ, float comparableW) {
        return this.len2() > len2(comparableX, comparableY, comparableZ, comparableW);
    }

    public boolean isLonger(Vec4f comparable) {
        return this.isLonger(comparable.x, comparable.y, comparable.z, comparable.w);
    }


    public boolean isZero() {
        return x == 0F && y == 0F && z == 0F && w == 0F;
    }

    public Vec4f zero() {
        return this.set(0F);
    }

    public Vec4f zeroCompsThatLess(float x, float y, float z, float w) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0F;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0F;
        if(Math.abs(this.z) < Math.abs(z))
            this.z = 0F;
        if(Math.abs(this.w) < Math.abs(w))
            this.w = 0F;
        return this;
    }

    public Vec4f zeroCompsThatLess(double x, double y, double z, double w) {
        return this.zeroCompsThatLess((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroCompsThatLess(int x, int y, int z, int w) {
        return this.zeroCompsThatLess((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroCompsThatLess(float xyzw) {
        return this.zeroCompsThatLess(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroCompsThatLess(double xyzw) {
        return this.zeroCompsThatLess(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroCompsThatLess(int xyzw) {
        return this.zeroCompsThatLess(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroCompsThatLess(Vec4f vector) {
        return this.zeroCompsThatLess(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroCompsThatLess(Vec4d vector) {
        return this.zeroCompsThatLess(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroCompsThatLess(Vec4i vector) {
        return this.zeroCompsThatLess(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroCompsThatZero(float x, float y, float z, float w) {
        if(x == 0F)
            this.x = 0F;
        if(y == 0F)
            this.y = 0F;
        if(z == 0F)
            this.z = 0F;
        if(w == 0F)
            this.w = 0F;
        return this;
    }

    public Vec4f zeroCompsThatZero(double x, double y, double z, double w) {
        return this.zeroCompsThatZero((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroCompsThatZero(int x, int y, int z, int w) {
        return this.zeroCompsThatZero((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroCompsThatZero(float xyzw) {
        return this.zeroCompsThatZero(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroCompsThatZero(double xyzw) {
        return this.zeroCompsThatZero(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroCompsThatZero(int xyzw) {
        return this.zeroCompsThatZero(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroCompsThatZero(Vec4f vector) {
        return this.zeroCompsThatZero(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroCompsThatZero(Vec4d vector) {
        return this.zeroCompsThatZero(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroCompsThatZero(Vec4i vector) {
        return this.zeroCompsThatZero(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroCompsThatBigger(float x, float y, float z, float w) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0F;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0F;
        if(Math.abs(this.z) > Math.abs(z))
            this.z = 0F;
        if(Math.abs(this.w) > Math.abs(w))
            this.w = 0F;
        return this;
    }

    public Vec4f zeroCompsThatBigger(double x, double y, double z, double w) {
        return this.zeroCompsThatBigger((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroCompsThatBigger(int x, int y, int z, int w) {
        return this.zeroCompsThatBigger((float) x, (float) y, (float) z, (float) w);
    }

    public Vec4f zeroCompsThatBigger(float xyzw) {
        return this.zeroCompsThatBigger(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroCompsThatBigger(double xyzw) {
        return this.zeroCompsThatBigger(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroCompsThatBigger(int xyzw) {
        return this.zeroCompsThatBigger(xyzw, xyzw, xyzw, xyzw);
    }

    public Vec4f zeroCompsThatBigger(Vec4f vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroCompsThatBigger(Vec4d vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y, vector.z, vector.w);
    }

    public Vec4f zeroCompsThatBigger(Vec4i vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y, vector.z, vector.w);
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
        float len = this.len2();
        if(len == 0F || len == 1F)
            return this;
        
        len = 1F / Mathc.sqrt(len);
        return this.mul(len);
    }


    public Vec4f abs() {
        if(x < 0F) x *= -1F;
        if(y < 0F) y *= -1F;
        if(z < 0F) z *= -1F;
        if(w < 0F) w *= -1F;
        return this;
    }


    public static Vec4f lerp(Vec4f dst, float startX, float startY, float startZ, float startW, float endX, float endY, float endZ, float endW, float t) {
        return dst.set(
            Maths.lerp(startX, endX, t),
            Maths.lerp(startY, endY, t),
            Maths.lerp(startZ, endZ, t),
            Maths.lerp(startW, endW, t)
        );
    }

    public static Vec4f lerp(Vec4f dst, Vec4f start, Vec4f end, float t) {
        return lerp(dst, start.x, start.y, start.z, start.w, end.x, end.y, end.z, end.w, t);
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


    public Vec4i signum(Vec4i dst) {
        return dst.set(Math.signum(x), Math.signum(y), Math.signum(z), Math.signum(w));
    }


    public static float rad(float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2) {
        final float cos = dot(x1, y1, z1, w1, x2, y2, z2, w2) / (len(x1, y1, z1, w1) * len(x2, y2, z2, w2));
        return Mathc.acos(Maths.clamp(cos, -1F, 1F));
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


    public Vec4f clamp(float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        return this.set(
            Maths.clamp(x, minX, maxX),
            Maths.clamp(y, minY, maxY),
            Maths.clamp(z, minZ, maxZ),
            Maths.clamp(w, minW, maxW)
        );
    }

    public Vec4f clamp(float minX, float minY, float minZ, float minW, Vec4f max) {
        return this.clamp(minX, minY, minZ, minW, max.x, max.y, max.z, max.w);
    }

    public Vec4f clamp(float minXYZW, float maxXYZW) {
        return this.clamp(minXYZW, minXYZW, minXYZW, minXYZW, maxXYZW, maxXYZW, maxXYZW, maxXYZW);
    }

    public Vec4f clamp(float minXYZW, Vec4f max) {
        return this.clamp(minXYZW, minXYZW, minXYZW, minXYZW, max.x, max.y, max.z, max.w);
    }

    public Vec4f clamp(Vec4f min, float maxX, float maxY, float maxZ, float maxW) {
        return this.clamp(min.x, min.y, min.z, min.w, maxX, maxY, maxZ, maxW);
    }

    public Vec4f clamp(Vec4f min, float maxXYZW) {
        return this.clamp(min.x, min.y, min.z, min.w, maxXYZW, maxXYZW, maxXYZW, maxXYZW);
    }

    public Vec4f clamp(Vec4f min, Vec4f max) {
        return this.clamp(min.x, min.y, min.z, min.w, max.x, max.y, max.z, max.w);
    }


    public Vec4f clampLength(double max) {
        final float len = this.len();
        if(len <= max)
            return this;
        return this.nor().mul(max);
    }


    public Vec4f reduce(double reduceX, double reduceY, double reduceZ, double reduceW) {
        final float len = this.len();
        return this.nor().mul(len - reduceX, len - reduceY, len - reduceZ, len - reduceW);
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