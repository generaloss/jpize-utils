package jpize.util.math.vector;

import jpize.util.math.Maths;
import jpize.util.math.Mathc;
import jpize.util.math.matrix.Matrix3;
import jpize.util.math.matrix.Matrix3f;
import java.util.Objects;

public class Vec2i {

    public int x;
    public int y;

    public Vec2i() { }

    public Vec2i(float xy) {
        this.set(xy);
    }

    public Vec2i(double xy) {
        this.set(xy);
    }

    public Vec2i(int xy) {
        this.set(xy);
    }

    public Vec2i(float x, float y) {
        this.set(x, y);
    }

    public Vec2i(double x, double y) {
        this.set(x, y);
    }

    public Vec2i(int x, int y) {
        this.set(x, y);
    }

    public Vec2i(Vec2f vector) {
        this.set(vector.x, vector.y);
    }

    public Vec2i(Vec2d vector) {
        this.set(vector.x, vector.y);
    }

    public Vec2i(Vec2i vector) {
        this.set(vector.x, vector.y);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public Vec2i set(int xy) {
        this.x = xy;
        this.y = xy;
        return this;
    }

    public Vec2i set(float xy) {
        return set((int) xy);
    }

    public Vec2i set(double xy) {
        return set((int) xy);
    }

    public Vec2i set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec2i set(float x, float y) {
        return set((int) x, (int) y);
    }

    public Vec2i set(double x, double y) {
        return set((int) x, (int) y);
    }

    public Vec2i set(Vec2f vector) {
        return set(vector.x, vector.y);
    }

    public Vec2i set(Vec2d vector) {
        return set(vector.x, vector.y);
    }

    public Vec2i set(Vec2i vector) {
        return set(vector.x, vector.y);
    }


    public Vec2i add(int xy) {
        this.x += xy;
        this.y += xy;
        return this;
    }

    public Vec2i add(float xy) {
        return add((int) xy);
    }

    public Vec2i add(double xy) {
        return add((int) xy);
    }

    public Vec2i add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2i add(float x, float y) {
        return add((int) x, (int) y);
    }

    public Vec2i add(double x, double y) {
        return add((int) x, (int) y);
    }

    public Vec2i add(Vec2f vector) {
        return add(vector.x, vector.y);
    }

    public Vec2i add(Vec2d vector) {
        return add(vector.x, vector.y);
    }

    public Vec2i add(Vec2i vector) {
        return add(vector.x, vector.y);
    }


    public Vec2i sub(int xy) {
        this.x -= xy;
        this.y -= xy;
        return this;
    }

    public Vec2i sub(float xy) {
        return sub((int) xy);
    }

    public Vec2i sub(double xy) {
        return sub((int) xy);
    }

    public Vec2i sub(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2i sub(float x, float y) {
        return sub((int) x, (int) y);
    }

    public Vec2i sub(double x, double y) {
        return sub((int) x, (int) y);
    }

    public Vec2i sub(Vec2f vector) {
        return sub(vector.x, vector.y);
    }

    public Vec2i sub(Vec2d vector) {
        return sub(vector.x, vector.y);
    }

    public Vec2i sub(Vec2i vector) {
        return sub(vector.x, vector.y);
    }


    public Vec2i mul(int xy) {
        this.x *= xy;
        this.y *= xy;
        return this;
    }

    public Vec2i mul(float xy) {
        return mul((int) xy);
    }

    public Vec2i mul(double xy) {
        return mul((int) xy);
    }

    public Vec2i mul(int x, int y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec2i mul(float x, float y) {
        return mul((int) x, (int) y);
    }

    public Vec2i mul(double x, double y) {
        return mul((int) x, (int) y);
    }

    public Vec2i mul(Vec2f vector) {
        return mul(vector.x, vector.y);
    }

    public Vec2i mul(Vec2d vector) {
        return mul(vector.x, vector.y);
    }

    public Vec2i mul(Vec2i vector) {
        return mul(vector.x, vector.y);
    }


    public Vec2i div(int xy) {
        this.x /= xy;
        this.y /= xy;
        return this;
    }

    public Vec2i div(float xy) {
        return div((int) xy);
    }

    public Vec2i div(double xy) {
        return div((int) xy);
    }

    public Vec2i div(int x, int y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec2i div(float x, float y) {
        return div((int) x, (int) y);
    }

    public Vec2i div(double x, double y) {
        return div((int) x, (int) y);
    }

    public Vec2i div(Vec2f vector) {
        return div(vector.x, vector.y);
    }

    public Vec2i div(Vec2d vector) {
        return div(vector.x, vector.y);
    }

    public Vec2i div(Vec2i vector) {
        return div(vector.x, vector.y);
    }


    public static float dst(int x1, int y1, int x2, int y2) {
        final int dx = x2 - x1;
        final int dy = y2 - y1;
        
        return Mathc.sqrt(dx * dx + dy * dy);
    }

    public static float dst(int x1, int y1, float x2, float y2) {
        return dst(x1, y1, (int) x2, (int) y2);
    }

    public static float dst(int x1, int y1, double x2, double y2) {
        return dst(x1, y1, (int) x2, (int) y2);
    }

    public static float dst(int x, int y, Vec2f vector) {
        return dst(x, y, vector.x, vector.y);
    }

    public static float dst(int x, int y, Vec2d vector) {
        return dst(x, y, vector.x, vector.y);
    }

    public static float dst(int x, int y, Vec2i vector) {
        return dst(x, y, vector.x, vector.y);
    }

    public static float dst(Vec2i vector, float x, float y) {
        return dst(vector.x, vector.y, x, y);
    }

    public static float dst(Vec2i vector, double x, double y) {
        return dst(vector.x, vector.y, x, y);
    }

    public static float dst(Vec2i vector, int x, int y) {
        return dst(vector.x, vector.y, x, y);
    }

    public static float dst(Vec2i vector1 , Vec2f vector2) {
        return dst(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public static float dst(Vec2i vector1 , Vec2d vector2) {
        return dst(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public static float dst(Vec2i vector1 , Vec2i vector2) {
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


    public static Vec2i shorter(Vec2i vector1, Vec2i vector2) {
        return (vector1.len2() < vector2.len2()) ? vector1 : vector2;
    }

    public static Vec2i longer(Vec2i vector1, Vec2i vector2) {
        return (vector1.len2() > vector2.len2()) ? vector1 : vector2;
    }


    public static Vec2i minCompsVec(Vec2i vector1, Vec2i vector2) {
        return new Vec2i(Math.min(vector1.x, vector2.x), Math.min(vector1.y, vector2.y));
    }

    public static Vec2i maxCompsVec(Vec2i vector1, Vec2i vector2) {
        return new Vec2i(Math.max(vector1.x, vector2.x), Math.max(vector1.y, vector2.y));
    }


    public Vec2i setShorter(Vec2i vector1, Vec2i vector2) {
        return set(shorter(vector1, vector2));
    }

    public Vec2i setLonger(Vec2i vector1, Vec2i vector2) {
        return set(longer(vector1, vector2));
    }


    public Vec2i setMinComps(Vec2i vector1, int x2, int y2) {
        return set(Math.min(vector1.x, x2), Math.min(vector1.y, y2));
    }

    public Vec2i setMinComps(Vec2i vector1, int xy2) {
        return setMinComps(vector1, xy2, xy2);
    }

    public Vec2i setMinComps(Vec2i vector1, Vec2i vector2) {
        return setMinComps(vector1, vector2.x, vector2.y);
    }

    public Vec2i setMaxComps(Vec2i vector1, int x2, int y2) {
        return set(Math.max(vector1.x, x2), Math.max(vector1.y, y2));
    }

    public Vec2i setMaxComps(Vec2i vector1, int xy2) {
        return setMaxComps(vector1, xy2, xy2);
    }

    public Vec2i setMaxComps(Vec2i vector1, Vec2i vector2) {
        return setMaxComps(vector1, vector2.x, vector2.y);
    }


    public int minComp() {
        return Math.min(x, y);
    }

    public int maxComp() {
        return Math.max(x, y);
    }


    public Vec2i zero() {
        return set(0);
    }

    public boolean isZero() {
        return x == 0 && y == 0;
    }

    public Vec2i zeroThatLess(int x, int y) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0;
        return this;
    }

    public Vec2i zeroThatLess(float x, float y) {
        return zeroThatLess((int) x, (int) y);
    }

    public Vec2i zeroThatLess(double x, double y) {
        return zeroThatLess((int) x, (int) y);
    }

    public Vec2i zeroThatLess(float xy) {
        return zeroThatLess(xy, xy);
    }

    public Vec2i zeroThatLess(double xy) {
        return zeroThatLess(xy, xy);
    }

    public Vec2i zeroThatLess(int xy) {
        return zeroThatLess(xy, xy);
    }

    public Vec2i zeroThatLess(Vec2f vector) {
        return zeroThatLess(vector.x, vector.y);
    }

    public Vec2i zeroThatLess(Vec2d vector) {
        return zeroThatLess(vector.x, vector.y);
    }

    public Vec2i zeroThatLess(Vec2i vector) {
        return zeroThatLess(vector.x, vector.y);
    }

    public Vec2i zeroThatZero(int x, int y) {
        if(x == 0)
            this.x = 0;
        if(y == 0)
            this.y = 0;
        return this;
    }

    public Vec2i zeroThatZero(float x, float y) {
        return zeroThatZero((int) x, (int) y);
    }

    public Vec2i zeroThatZero(double x, double y) {
        return zeroThatZero((int) x, (int) y);
    }

    public Vec2i zeroThatZero(float xy) {
        return zeroThatZero(xy, xy);
    }

    public Vec2i zeroThatZero(double xy) {
        return zeroThatZero(xy, xy);
    }

    public Vec2i zeroThatZero(int xy) {
        return zeroThatZero(xy, xy);
    }

    public Vec2i zeroThatZero(Vec2f vector) {
        return zeroThatZero(vector.x, vector.y);
    }

    public Vec2i zeroThatZero(Vec2d vector) {
        return zeroThatZero(vector.x, vector.y);
    }

    public Vec2i zeroThatZero(Vec2i vector) {
        return zeroThatZero(vector.x, vector.y);
    }

    public Vec2i zeroThatBigger(int x, int y) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0;
        return this;
    }

    public Vec2i zeroThatBigger(float x, float y) {
        return zeroThatBigger((int) x, (int) y);
    }

    public Vec2i zeroThatBigger(double x, double y) {
        return zeroThatBigger((int) x, (int) y);
    }

    public Vec2i zeroThatBigger(float xy) {
        return zeroThatBigger(xy, xy);
    }

    public Vec2i zeroThatBigger(double xy) {
        return zeroThatBigger(xy, xy);
    }

    public Vec2i zeroThatBigger(int xy) {
        return zeroThatBigger(xy, xy);
    }

    public Vec2i zeroThatBigger(Vec2f vector) {
        return zeroThatBigger(vector.x, vector.y);
    }

    public Vec2i zeroThatBigger(Vec2d vector) {
        return zeroThatBigger(vector.x, vector.y);
    }

    public Vec2i zeroThatBigger(Vec2i vector) {
        return zeroThatBigger(vector.x, vector.y);
    }


    public int area() {
        return x * y;
    }


    public static int len2(int x, int y) {
        return x * x + y * y;
    }

    public static int len2(Vec2i vector) {
        return len2(vector.x, vector.y);
    }

    public static float len(int x, int y) {
        return Mathc.sqrt(len2(x, y));
    }

    public static float len(Vec2i vector) {
        return Mathc.sqrt(len2(vector.x, vector.y));
    }

    public int len2() {
        return len2(this);
    }

    public float len() {
        return len(this);
    }


    public Vec2i abs() {
        if(x < 0) x *= -1;
        if(y < 0) y *= -1;
        return this;
    }


    public static Vec2i lerp(Vec2i vector, int startX, int startY, int endX, int endY, float t) {
        return vector.set(Maths.lerp(startX, endX, t), Maths.lerp(startY, endY, t));
    }

    public static Vec2i lerp(Vec2i vector, Vec2i start, Vec2i end, float t) {
        return lerp(vector, start.x, start.y, end.x, end.y, t);
    }

    public Vec2i lerp(int startX, int startY, int endX, int endY, float t) {
        return lerp(this, startX, startY, endX, endY, t);
    }

    public Vec2i lerp(Vec2i start, Vec2i end, float t) {
        return lerp(this, start, end, t);
    }


    public static int dot(int x1, int y1, int x2, int y2) {
        return x1 * x2 + y1 * y2;
    }

    public static int dot(Vec2i vector1, int x2, int y2) {
        return dot(vector1.x, vector1.y, x2, y2);
    }

    public static int dot(int x1, int y1, Vec2i vector2) {
        return dot(x1, y1, vector2.x, vector2.y);
    }

    public static int dot(Vec2i vector1, Vec2i vector2) {
        return dot(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public int dot(Vec2i vector) {
        return dot(this, vector);
    }

    public int dot(int x, int y) {
        return dot(this, x, y);
    }


    public static int crs(int x1, int y1, int x2, int y2) {
        return (x1 * y2) - (y1 * x2);
    }

    public static int crs(Vec2i vector1, int x2, int y2) {
        return crs(vector1.x, vector1.y, x2, y2);
    }

    public static int crs(int x1, int y1, Vec2i vector2) {
        return crs(x1, y1, vector2.x, vector2.y);
    }

    public static int crs(Vec2i vector1, Vec2i vector2) {
        return crs(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public int crs(Vec2i vector) {
        return crs(this, vector);
    }

    public int crs(int x, int y) {
        return crs(this, x, y);
    }


    public Vec2i signum() {
        return new Vec2i(Math.signum(x), Math.signum(y));
    }


    public static float rad(int x1, int y1, int x2, int y2) {
        final float cos = dot(x1, y1, x2, y2) / (len(x1, y1) * len(x2, y2));
        return Mathc.acos(Maths.clamp(cos, -1, 1));
    }

    public static float rad(Vec2i vector1, int x2, int y2) {
        return rad(vector1.x, vector1.y, x2, y2);
    }

    public static float rad(int x1, int y1, Vec2i vector2) {
        return rad(x1, y1, vector2.x, vector2.y);
    }

    public static float rad(Vec2i vector1, Vec2i vector2) {
        return rad(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public float rad(int x, int y) {
        return rad(this, x, y);
    }

    public float rad(Vec2i vector) {
        return rad(this, vector);
    }

    public static float deg(int x1, int y1, int x2, int y2) {
        return rad(x1, y1, x2, y2) * Maths.toDeg;
    }

    public static float deg(Vec2i vector1, int x2, int y2) {
        return deg(vector1.x, vector1.y, x2, y2);
    }

    public static float deg(int x1, int y1, Vec2i vector2) {
        return deg(x1, y1, vector2.x, vector2.y);
    }

    public static float deg(Vec2i vector1, Vec2i vector2) {
        return deg(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public float deg(int x, int y) {
        return deg(this, x, y);
    }

    public float deg(Vec2i vector) {
        return deg(this, vector);
    }


    public Vec2i rotateRad(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return set((x * cos + y * sin), (x * -sin + y * cos));
    }

    public Vec2i rotate(double degrees) {
        return rotateRad(degrees * Maths.toDeg);
    }


    public Vec2i mulMat3(float[] matrix) {
        return set(
            x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + matrix[Matrix3.m20],
            x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + matrix[Matrix3.m21]
        );
    }

    public Vec2i mulMat3(Matrix3f matrix) {
        return mulMat3(matrix.val);
    }


    public static float aspect(int x, int y) {
        return (float) x / y;
    }

    public float aspect() {
        return aspect(x, y);
    }


    public Vec2f castFloat() {
        return new Vec2f(this);
    }

    public Vec2d castDouble() {
        return new Vec2d(this);
    }


    public Vec3i to3D() {
        return new Vec3i(this);
    }


    public Vec2i copy() {
        return new Vec2i(this);
    }

    public static boolean equals(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 == y2;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        
        final Vec2i vec = (Vec2i) object;
        return Vec2i.equals(x, y, vec.x, vec.y);
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