package jpize.util.math.vector;

import jpize.util.math.Maths;
import jpize.util.math.Mathc;
import jpize.util.math.matrix.Matrix3;
import jpize.util.math.matrix.Matrix3f;
import java.util.Objects;

public class Vec2f {

    public float x;
    public float y;

    public Vec2f() { }

    public Vec2f(float xy) {
        this.set(xy);
    }

    public Vec2f(double xy) {
        this.set(xy);
    }

    public Vec2f(int xy) {
        this.set(xy);
    }

    public Vec2f(float x, float y) {
        this.set(x, y);
    }

    public Vec2f(double x, double y) {
        this.set(x, y);
    }

    public Vec2f(int x, int y) {
        this.set(x, y);
    }

    public Vec2f(Vec2f vector) {
        this.set(vector.x, vector.y);
    }

    public Vec2f(Vec2d vector) {
        this.set(vector.x, vector.y);
    }

    public Vec2f(Vec2i vector) {
        this.set(vector.x, vector.y);
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public Vec2f set(float xy) {
        this.x = xy;
        this.y = xy;
        return this;
    }

    public Vec2f set(double xy) {
        return this.set((float) xy);
    }

    public Vec2f set(int xy) {
        return this.set((float) xy);
    }

    public Vec2f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec2f set(double x, double y) {
        return this.set((float) x, (float) y);
    }

    public Vec2f set(int x, int y) {
        return this.set((float) x, (float) y);
    }

    public Vec2f set(Vec2f vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec2f set(Vec2d vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec2f set(Vec2i vector) {
        return this.set(vector.x, vector.y);
    }


    public Vec2f add(float xy) {
        this.x += xy;
        this.y += xy;
        return this;
    }

    public Vec2f add(double xy) {
        return this.add((float) xy);
    }

    public Vec2f add(int xy) {
        return this.add((float) xy);
    }

    public Vec2f add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2f add(double x, double y) {
        return this.add((float) x, (float) y);
    }

    public Vec2f add(int x, int y) {
        return this.add((float) x, (float) y);
    }

    public Vec2f add(Vec2f vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec2f add(Vec2d vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec2f add(Vec2i vector) {
        return this.add(vector.x, vector.y);
    }


    public Vec2f sub(float xy) {
        this.x -= xy;
        this.y -= xy;
        return this;
    }

    public Vec2f sub(double xy) {
        return this.sub((float) xy);
    }

    public Vec2f sub(int xy) {
        return this.sub((float) xy);
    }

    public Vec2f sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2f sub(double x, double y) {
        return this.sub((float) x, (float) y);
    }

    public Vec2f sub(int x, int y) {
        return this.sub((float) x, (float) y);
    }

    public Vec2f sub(Vec2f vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec2f sub(Vec2d vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec2f sub(Vec2i vector) {
        return this.sub(vector.x, vector.y);
    }


    public Vec2f mul(float xy) {
        this.x *= xy;
        this.y *= xy;
        return this;
    }

    public Vec2f mul(double xy) {
        return this.mul((float) xy);
    }

    public Vec2f mul(int xy) {
        return this.mul((float) xy);
    }

    public Vec2f mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec2f mul(double x, double y) {
        return this.mul((float) x, (float) y);
    }

    public Vec2f mul(int x, int y) {
        return this.mul((float) x, (float) y);
    }

    public Vec2f mul(Vec2f vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec2f mul(Vec2d vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec2f mul(Vec2i vector) {
        return this.mul(vector.x, vector.y);
    }


    public Vec2f div(float xy) {
        this.x /= xy;
        this.y /= xy;
        return this;
    }

    public Vec2f div(double xy) {
        return this.div((float) xy);
    }

    public Vec2f div(int xy) {
        return this.div((float) xy);
    }

    public Vec2f div(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec2f div(double x, double y) {
        return this.div((float) x, (float) y);
    }

    public Vec2f div(int x, int y) {
        return this.div((float) x, (float) y);
    }

    public Vec2f div(Vec2f vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec2f div(Vec2d vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec2f div(Vec2i vector) {
        return this.div(vector.x, vector.y);
    }


    public static float dst(float x1, float y1, float x2, float y2) {
        final float dx = x2 - x1;
        final float dy = y2 - y1;
        
        return Mathc.sqrt(dx * dx + dy * dy);
    }

    public static float dst(float x1, float y1, double x2, double y2) {
        return dst(x1, y1, (float) x2, (float) y2);
    }

    public static float dst(float x1, float y1, int x2, int y2) {
        return dst(x1, y1, (float) x2, (float) y2);
    }

    public static float dst(float x, float y, Vec2f vector) {
        return dst(x, y, vector.x, vector.y);
    }

    public static float dst(float x, float y, Vec2d vector) {
        return dst(x, y, vector.x, vector.y);
    }

    public static float dst(float x, float y, Vec2i vector) {
        return dst(x, y, vector.x, vector.y);
    }

    public static float dst(Vec2f vector, float x, float y) {
        return dst(vector.x, vector.y, x, y);
    }

    public static float dst(Vec2f vector, double x, double y) {
        return dst(vector.x, vector.y, x, y);
    }

    public static float dst(Vec2f vector, int x, int y) {
        return dst(vector.x, vector.y, x, y);
    }

    public static float dst(Vec2f vector1 , Vec2f vector2) {
        return dst(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public static float dst(Vec2f vector1 , Vec2d vector2) {
        return dst(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public static float dst(Vec2f vector1 , Vec2i vector2) {
        return dst(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public float dst(float x, float y) {
        return dst(this, x, y);
    }

    public float dst(double x, double y) {
        return dst(this, x, y);
    }

    public float dst(int x, int y) {
        return dst(this, x, y);
    }

    public float dst(Vec2f vector) {
        return dst(this, vector);
    }

    public float dst(Vec2d vector) {
        return dst(this, vector);
    }

    public float dst(Vec2i vector) {
        return dst(this, vector);
    }


    public static Vec2f shorter(Vec2f vector1, Vec2f vector2) {
        return (vector1.len2() < vector2.len2()) ? vector1 : vector2;
    }

    public static Vec2f longer(Vec2f vector1, Vec2f vector2) {
        return (vector1.len2() > vector2.len2()) ? vector1 : vector2;
    }

    public Vec2f setShorter(Vec2f vector1, Vec2f vector2) {
        return this.set(shorter(vector1, vector2));
    }

    public Vec2f setLonger(Vec2f vector1, Vec2f vector2) {
        return this.set(longer(vector1, vector2));
    }


    public float minComp() {
        return Math.min(x, y);
    }

    public float maxComp() {
        return Math.max(x, y);
    }


    public static Vec2f minComps(Vec2f dst, float x1, float y1, float x2, float y2) {
        return dst.set(Math.min(x1, x2), Math.min(y1, y2));
    }

    public static Vec2f minComps(Vec2f dst, float x1, float y1, Vec2f vector2) {
        return minComps(dst, x1, y1, vector2.x, vector2.y);
    }

    public static Vec2f minComps(Vec2f dst, Vec2f vector1, float x2, float y2) {
        return minComps(dst, vector1.x, vector1.y, x2, y2);
    }

    public static Vec2f minComps(Vec2f dst, Vec2f vector1, float xy2) {
        return minComps(dst, vector1.x, vector1.y, xy2, xy2);
    }

    public static Vec2f minComps(Vec2f dst, Vec2f vector1, Vec2f vector2) {
        return minComps(dst, vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public Vec2f setMinComps(float x1, float y1, float x2, float y2) {
        return minComps(this, x1, y1, x2, y2);
    }

    public Vec2f setMinComps(float x1, float y1, Vec2f vector2) {
        return minComps(this, x1, y1, vector2);
    }

    public Vec2f setMinComps(Vec2f vector1, float x2, float y2) {
        return minComps(this, vector1, x2, y2);
    }

    public Vec2f setMinComps(Vec2f vector1, float xy2) {
        return minComps(this, vector1, xy2);
    }

    public Vec2f setMinComps(Vec2f vector1, Vec2f vector2) {
        return minComps(this, vector1, vector2);
    }


    public static Vec2f maxComps(Vec2f dst, float x1, float y1, float x2, float y2) {
        return dst.set(Math.max(x1, x2), Math.max(y1, y2));
    }

    public static Vec2f maxComps(Vec2f dst, float x1, float y1, Vec2f vector2) {
        return maxComps(dst, x1, y1, vector2.x, vector2.y);
    }

    public static Vec2f maxComps(Vec2f dst, Vec2f vector1, float x2, float y2) {
        return maxComps(dst, vector1.x, vector1.y, x2, y2);
    }

    public static Vec2f maxComps(Vec2f dst, Vec2f vector1, float xy2) {
        return maxComps(dst, vector1.x, vector1.y, xy2, xy2);
    }

    public static Vec2f maxComps(Vec2f dst, Vec2f vector1, Vec2f vector2) {
        return maxComps(dst, vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public Vec2f setMaxComps(float x1, float y1, float x2, float y2) {
        return maxComps(this, x1, y1, x2, y2);
    }

    public Vec2f setMaxComps(float x1, float y1, Vec2f vector2) {
        return maxComps(this, x1, y1, vector2);
    }

    public Vec2f setMaxComps(Vec2f vector1, float x2, float y2) {
        return maxComps(this, vector1, x2, y2);
    }

    public Vec2f setMaxComps(Vec2f vector1, float xy2) {
        return maxComps(this, vector1, xy2);
    }

    public Vec2f setMaxComps(Vec2f vector1, Vec2f vector2) {
        return maxComps(this, vector1, vector2);
    }


    public boolean isShorter(float comparableX, float comparableY) {
        return this.len2() < len2(comparableX, comparableY);
    }

    public boolean isShorter(Vec2f comparable) {
        return this.isShorter(comparable.x, comparable.y);
    }

    public boolean isLonger(float comparableX, float comparableY) {
        return this.len2() > len2(comparableX, comparableY);
    }

    public boolean isLonger(Vec2f comparable) {
        return this.isLonger(comparable.x, comparable.y);
    }


    public boolean isZero() {
        return x == 0F && y == 0F;
    }

    public Vec2f zero() {
        return this.set(0F);
    }

    public Vec2f zeroCompsThatLess(float x, float y) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0F;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0F;
        return this;
    }

    public Vec2f zeroCompsThatLess(double x, double y) {
        return this.zeroCompsThatLess((float) x, (float) y);
    }

    public Vec2f zeroCompsThatLess(int x, int y) {
        return this.zeroCompsThatLess((float) x, (float) y);
    }

    public Vec2f zeroCompsThatLess(float xy) {
        return this.zeroCompsThatLess(xy, xy);
    }

    public Vec2f zeroCompsThatLess(double xy) {
        return this.zeroCompsThatLess(xy, xy);
    }

    public Vec2f zeroCompsThatLess(int xy) {
        return this.zeroCompsThatLess(xy, xy);
    }

    public Vec2f zeroCompsThatLess(Vec2f vector) {
        return this.zeroCompsThatLess(vector.x, vector.y);
    }

    public Vec2f zeroCompsThatLess(Vec2d vector) {
        return this.zeroCompsThatLess(vector.x, vector.y);
    }

    public Vec2f zeroCompsThatLess(Vec2i vector) {
        return this.zeroCompsThatLess(vector.x, vector.y);
    }

    public Vec2f zeroCompsThatZero(float x, float y) {
        if(x == 0F)
            this.x = 0F;
        if(y == 0F)
            this.y = 0F;
        return this;
    }

    public Vec2f zeroCompsThatZero(double x, double y) {
        return this.zeroCompsThatZero((float) x, (float) y);
    }

    public Vec2f zeroCompsThatZero(int x, int y) {
        return this.zeroCompsThatZero((float) x, (float) y);
    }

    public Vec2f zeroCompsThatZero(float xy) {
        return this.zeroCompsThatZero(xy, xy);
    }

    public Vec2f zeroCompsThatZero(double xy) {
        return this.zeroCompsThatZero(xy, xy);
    }

    public Vec2f zeroCompsThatZero(int xy) {
        return this.zeroCompsThatZero(xy, xy);
    }

    public Vec2f zeroCompsThatZero(Vec2f vector) {
        return this.zeroCompsThatZero(vector.x, vector.y);
    }

    public Vec2f zeroCompsThatZero(Vec2d vector) {
        return this.zeroCompsThatZero(vector.x, vector.y);
    }

    public Vec2f zeroCompsThatZero(Vec2i vector) {
        return this.zeroCompsThatZero(vector.x, vector.y);
    }

    public Vec2f zeroCompsThatBigger(float x, float y) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0F;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0F;
        return this;
    }

    public Vec2f zeroCompsThatBigger(double x, double y) {
        return this.zeroCompsThatBigger((float) x, (float) y);
    }

    public Vec2f zeroCompsThatBigger(int x, int y) {
        return this.zeroCompsThatBigger((float) x, (float) y);
    }

    public Vec2f zeroCompsThatBigger(float xy) {
        return this.zeroCompsThatBigger(xy, xy);
    }

    public Vec2f zeroCompsThatBigger(double xy) {
        return this.zeroCompsThatBigger(xy, xy);
    }

    public Vec2f zeroCompsThatBigger(int xy) {
        return this.zeroCompsThatBigger(xy, xy);
    }

    public Vec2f zeroCompsThatBigger(Vec2f vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y);
    }

    public Vec2f zeroCompsThatBigger(Vec2d vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y);
    }

    public Vec2f zeroCompsThatBigger(Vec2i vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y);
    }


    public float area() {
        return x * y;
    }


    public static float len2(float x, float y) {
        return x * x + y * y;
    }

    public static float len2(Vec2f vector) {
        return len2(vector.x, vector.y);
    }

    public static float len(float x, float y) {
        return Mathc.sqrt(len2(x, y));
    }

    public static float len(Vec2f vector) {
        return Mathc.sqrt(len2(vector.x, vector.y));
    }

    public float len2() {
        return len2(this);
    }

    public float len() {
        return len(this);
    }


    public Vec2f nor() {
        float len = this.len2();
        if(len == 0F || len == 1F)
            return this;
        
        len = 1F / Mathc.sqrt(len);
        return this.mul(len);
    }


    public Vec2f abs() {
        if(x < 0F) x *= -1F;
        if(y < 0F) y *= -1F;
        return this;
    }


    public static Vec2f lerp(Vec2f dst, float startX, float startY, float endX, float endY, float t) {
        return dst.set(
            Maths.lerp(startX, endX, t),
            Maths.lerp(startY, endY, t)
        );
    }

    public static Vec2f lerp(Vec2f dst, Vec2f start, Vec2f end, float t) {
        return lerp(dst, start.x, start.y, end.x, end.y, t);
    }

    public Vec2f lerp(float startX, float startY, float endX, float endY, float t) {
        return lerp(this, startX, startY, endX, endY, t);
    }

    public Vec2f lerp(Vec2f start, Vec2f end, float t) {
        return lerp(this, start, end, t);
    }


    public static float dot(float x1, float y1, float x2, float y2) {
        return x1 * x2 + y1 * y2;
    }

    public static float dot(Vec2f vector1, float x2, float y2) {
        return dot(vector1.x, vector1.y, x2, y2);
    }

    public static float dot(float x1, float y1, Vec2f vector2) {
        return dot(x1, y1, vector2.x, vector2.y);
    }

    public static float dot(Vec2f vector1, Vec2f vector2) {
        return dot(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public float dot(Vec2f vector) {
        return dot(this, vector);
    }

    public float dot(float x, float y) {
        return dot(this, x, y);
    }


    public static float crs(float x1, float y1, float x2, float y2) {
        return (x1 * y2) - (y1 * x2);
    }

    public static float crs(Vec2f vector1, float x2, float y2) {
        return crs(vector1.x, vector1.y, x2, y2);
    }

    public static float crs(float x1, float y1, Vec2f vector2) {
        return crs(x1, y1, vector2.x, vector2.y);
    }

    public static float crs(Vec2f vector1, Vec2f vector2) {
        return crs(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public float crs(Vec2f vector) {
        return crs(this, vector);
    }

    public float crs(float x, float y) {
        return crs(this, x, y);
    }


    public Vec2f frac() {
        x = Maths.frac(x);
        y = Maths.frac(y);
        return this;
    }


    public Vec2i signum(Vec2i dst) {
        return dst.set(Math.signum(x), Math.signum(y));
    }


    public static float rad(float x1, float y1, float x2, float y2) {
        final float cos = dot(x1, y1, x2, y2) / (len(x1, y1) * len(x2, y2));
        return Mathc.acos(Maths.clamp(cos, -1F, 1F));
    }

    public static float rad(Vec2f vector1, float x2, float y2) {
        return rad(vector1.x, vector1.y, x2, y2);
    }

    public static float rad(float x1, float y1, Vec2f vector2) {
        return rad(x1, y1, vector2.x, vector2.y);
    }

    public static float rad(Vec2f vector1, Vec2f vector2) {
        return rad(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public float rad(float x, float y) {
        return rad(this, x, y);
    }

    public float rad(Vec2f vector) {
        return rad(this, vector);
    }

    public static float deg(float x1, float y1, float x2, float y2) {
        return rad(x1, y1, x2, y2) * Maths.toDeg;
    }

    public static float deg(Vec2f vector1, float x2, float y2) {
        return deg(vector1.x, vector1.y, x2, y2);
    }

    public static float deg(float x1, float y1, Vec2f vector2) {
        return deg(x1, y1, vector2.x, vector2.y);
    }

    public static float deg(Vec2f vector1, Vec2f vector2) {
        return deg(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public float deg(float x, float y) {
        return deg(this, x, y);
    }

    public float deg(Vec2f vector) {
        return deg(this, vector);
    }


    public Vec2f rotateRad(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return this.set((x * cos + y * sin), (x * -sin + y * cos));
    }

    public Vec2f rotate(double degrees) {
        return this.rotateRad(degrees * Maths.toRad);
    }


    public Vec2f mulMat3(float[] matrix) {
        return this.set(
            x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + matrix[Matrix3.m20],
            x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + matrix[Matrix3.m21]
        );
    }

    public Vec2f mulMat3(Matrix3f matrix) {
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


    public static float aspect(float x, float y) {
        return x / y;
    }

    public float aspect() {
        return aspect(x, y);
    }


    public Vec2f floor() {
        x = Maths.floor(x);
        y = Maths.floor(y);
        return this;
    }

    public Vec2f round() {
        x = Maths.round(x);
        y = Maths.round(y);
        return this;
    }

    public Vec2f ceil() {
        x = Maths.ceil(x);
        y = Maths.ceil(y);
        return this;
    }


    public Vec2d castDouble() {
        return new Vec2d(this);
    }

    public Vec2i castInt() {
        return new Vec2i(this);
    }


    public Vec3f to3D() {
        return new Vec3f(this);
    }


    public Vec2f clampLength(double max) {
        final float len = this.len();
        if(len <= max)
            return this;
        return this.nor().mul(max);
    }


    public Vec2f reduce(double reduceX, double reduceY) {
        final float len = this.len();
        return this.nor().mul(len - reduceX, len - reduceY);
    }


    public Vec2f copy() {
        return new Vec2f(this);
    }

    public static boolean equals(float x1, float y1, float x2, float y2) {
        return x1 == x2 && y1 == y2;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        
        final Vec2f vec = (Vec2f) object;
        return Vec2f.equals(x, y, vec.x, vec.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

}