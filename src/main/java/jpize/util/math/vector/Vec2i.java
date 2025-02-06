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
        return this.set((int) xy);
    }

    public Vec2i set(double xy) {
        return this.set((int) xy);
    }

    public Vec2i set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec2i set(float x, float y) {
        return this.set((int) x, (int) y);
    }

    public Vec2i set(double x, double y) {
        return this.set((int) x, (int) y);
    }

    public Vec2i set(Vec2f vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec2i set(Vec2d vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec2i set(Vec2i vector) {
        return this.set(vector.x, vector.y);
    }


    public Vec2i add(int xy) {
        this.x += xy;
        this.y += xy;
        return this;
    }

    public Vec2i add(float xy) {
        return this.add((int) xy);
    }

    public Vec2i add(double xy) {
        return this.add((int) xy);
    }

    public Vec2i add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2i add(float x, float y) {
        return this.add((int) x, (int) y);
    }

    public Vec2i add(double x, double y) {
        return this.add((int) x, (int) y);
    }

    public Vec2i add(Vec2f vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec2i add(Vec2d vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec2i add(Vec2i vector) {
        return this.add(vector.x, vector.y);
    }


    public Vec2i sub(int xy) {
        this.x -= xy;
        this.y -= xy;
        return this;
    }

    public Vec2i sub(float xy) {
        return this.sub((int) xy);
    }

    public Vec2i sub(double xy) {
        return this.sub((int) xy);
    }

    public Vec2i sub(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2i sub(float x, float y) {
        return this.sub((int) x, (int) y);
    }

    public Vec2i sub(double x, double y) {
        return this.sub((int) x, (int) y);
    }

    public Vec2i sub(Vec2f vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec2i sub(Vec2d vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec2i sub(Vec2i vector) {
        return this.sub(vector.x, vector.y);
    }


    public Vec2i mul(int xy) {
        this.x *= xy;
        this.y *= xy;
        return this;
    }

    public Vec2i mul(float xy) {
        return this.mul((int) xy);
    }

    public Vec2i mul(double xy) {
        return this.mul((int) xy);
    }

    public Vec2i mul(int x, int y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec2i mul(float x, float y) {
        return this.mul((int) x, (int) y);
    }

    public Vec2i mul(double x, double y) {
        return this.mul((int) x, (int) y);
    }

    public Vec2i mul(Vec2f vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec2i mul(Vec2d vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec2i mul(Vec2i vector) {
        return this.mul(vector.x, vector.y);
    }


    public Vec2i div(int xy) {
        this.x /= xy;
        this.y /= xy;
        return this;
    }

    public Vec2i div(float xy) {
        return this.div((int) xy);
    }

    public Vec2i div(double xy) {
        return this.div((int) xy);
    }

    public Vec2i div(int x, int y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec2i div(float x, float y) {
        return this.div((int) x, (int) y);
    }

    public Vec2i div(double x, double y) {
        return this.div((int) x, (int) y);
    }

    public Vec2i div(Vec2f vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec2i div(Vec2d vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec2i div(Vec2i vector) {
        return this.div(vector.x, vector.y);
    }


    public static int dst2(int x1, int y1, int x2, int y2) {
        final int dx = (x2 - x1);
        final int dy = (y2 - y1);
        return (dx * dx + dy * dy);
    }

    public static int dst2(int x1, int y1, float x2, float y2) {
        return dst2(x1, y1, (int) x2, (int) y2);
    }

    public static int dst2(int x1, int y1, double x2, double y2) {
        return dst2(x1, y1, (int) x2, (int) y2);
    }

    public static int dst2(int x, int y, Vec2f vector) {
        return dst2(x, y, vector.x, vector.y);
    }

    public static int dst2(int x, int y, Vec2d vector) {
        return dst2(x, y, vector.x, vector.y);
    }

    public static int dst2(int x, int y, Vec2i vector) {
        return dst2(x, y, vector.x, vector.y);
    }

    public static int dst2(Vec2i vector, float x, float y) {
        return dst2(vector.x, vector.y, x, y);
    }

    public static int dst2(Vec2i vector, double x, double y) {
        return dst2(vector.x, vector.y, x, y);
    }

    public static int dst2(Vec2i vector, int x, int y) {
        return dst2(vector.x, vector.y, x, y);
    }

    public static int dst2(Vec2i vector1, Vec2f vector2) {
        return dst2(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public static int dst2(Vec2i vector1, Vec2d vector2) {
        return dst2(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public static int dst2(Vec2i vector1, Vec2i vector2) {
        return dst2(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public int dst2(float x, float y) {
        return dst2(this, x, y);
    }

    public int dst2(double x, double y) {
        return dst2(this, x, y);
    }

    public int dst2(int x, int y) {
        return dst2(this, x, y);
    }

    public int dst2(Vec2f vector) {
        return dst2(this, vector);
    }

    public int dst2(Vec2d vector) {
        return dst2(this, vector);
    }

    public int dst2(Vec2i vector) {
        return dst2(this, vector);
    }


    public static float dst(int x1, int y1, int x2, int y2) {
        return Mathc.sqrt(dst2(x1, y1, x2, y2));
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

    public static float dst(Vec2i vector1, Vec2f vector2) {
        return dst(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public static float dst(Vec2i vector1, Vec2d vector2) {
        return dst(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public static float dst(Vec2i vector1, Vec2i vector2) {
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

    public Vec2i setShorter(Vec2i vector1, Vec2i vector2) {
        return this.set(shorter(vector1, vector2));
    }

    public Vec2i setLonger(Vec2i vector1, Vec2i vector2) {
        return this.set(longer(vector1, vector2));
    }


    public int minComp() {
        return Math.min(x, y);
    }

    public int maxComp() {
        return Math.max(x, y);
    }


    public static Vec2i minComps(Vec2i dst, int x1, int y1, int x2, int y2) {
        return dst.set(Math.min(x1, x2), Math.min(y1, y2));
    }

    public static Vec2i minComps(Vec2i dst, int x1, int y1, Vec2i vector2) {
        return minComps(dst, x1, y1, vector2.x, vector2.y);
    }

    public static Vec2i minComps(Vec2i dst, Vec2i vector1, int x2, int y2) {
        return minComps(dst, vector1.x, vector1.y, x2, y2);
    }

    public static Vec2i minComps(Vec2i dst, Vec2i vector1, int xy2) {
        return minComps(dst, vector1.x, vector1.y, xy2, xy2);
    }

    public static Vec2i minComps(Vec2i dst, Vec2i vector1, Vec2i vector2) {
        return minComps(dst, vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public Vec2i setMinComps(int x1, int y1, int x2, int y2) {
        return minComps(this, x1, y1, x2, y2);
    }

    public Vec2i setMinComps(int x1, int y1, Vec2i vector2) {
        return minComps(this, x1, y1, vector2);
    }

    public Vec2i setMinComps(Vec2i vector1, int x2, int y2) {
        return minComps(this, vector1, x2, y2);
    }

    public Vec2i setMinComps(Vec2i vector1, int xy2) {
        return minComps(this, vector1, xy2);
    }

    public Vec2i setMinComps(Vec2i vector1, Vec2i vector2) {
        return minComps(this, vector1, vector2);
    }

    public Vec2i setMinComps(int x2, int y2) {
        return this.setMinComps(this, x2, y2);
    }

    public Vec2i setMinComps(int xy2) {
        return this.setMinComps(this, xy2);
    }

    public Vec2i setMinComps(Vec2i vector2) {
        return this.setMinComps(this, vector2);
    }


    public static Vec2i maxComps(Vec2i dst, int x1, int y1, int x2, int y2) {
        return dst.set(Math.max(x1, x2), Math.max(y1, y2));
    }

    public static Vec2i maxComps(Vec2i dst, int x1, int y1, Vec2i vector2) {
        return maxComps(dst, x1, y1, vector2.x, vector2.y);
    }

    public static Vec2i maxComps(Vec2i dst, Vec2i vector1, int x2, int y2) {
        return maxComps(dst, vector1.x, vector1.y, x2, y2);
    }

    public static Vec2i maxComps(Vec2i dst, Vec2i vector1, int xy2) {
        return maxComps(dst, vector1.x, vector1.y, xy2, xy2);
    }

    public static Vec2i maxComps(Vec2i dst, Vec2i vector1, Vec2i vector2) {
        return maxComps(dst, vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public Vec2i setMaxComps(int x1, int y1, int x2, int y2) {
        return maxComps(this, x1, y1, x2, y2);
    }

    public Vec2i setMaxComps(int x1, int y1, Vec2i vector2) {
        return maxComps(this, x1, y1, vector2);
    }

    public Vec2i setMaxComps(Vec2i vector1, int x2, int y2) {
        return maxComps(this, vector1, x2, y2);
    }

    public Vec2i setMaxComps(Vec2i vector1, int xy2) {
        return maxComps(this, vector1, xy2);
    }

    public Vec2i setMaxComps(Vec2i vector1, Vec2i vector2) {
        return maxComps(this, vector1, vector2);
    }

    public Vec2i setMaxComps(int x2, int y2) {
        return this.setMaxComps(this, x2, y2);
    }

    public Vec2i setMaxComps(int xy2) {
        return this.setMaxComps(this, xy2);
    }

    public Vec2i setMaxComps(Vec2i vector2) {
        return this.setMaxComps(this, vector2);
    }


    public boolean isShorter(int comparableX, int comparableY) {
        return this.len2() < len2(comparableX, comparableY);
    }

    public boolean isShorter(Vec2i comparable) {
        return this.isShorter(comparable.x, comparable.y);
    }

    public boolean isLonger(int comparableX, int comparableY) {
        return this.len2() > len2(comparableX, comparableY);
    }

    public boolean isLonger(Vec2i comparable) {
        return this.isLonger(comparable.x, comparable.y);
    }


    public boolean isZero() {
        return x == 0 && y == 0;
    }

    public boolean isNotZero() {
        return x != 0 || y != 0;
    }

    public Vec2i zero() {
        return this.set(0);
    }

    public Vec2i zeroCompsThatLess(int x, int y) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0;
        return this;
    }

    public Vec2i zeroCompsThatLess(float x, float y) {
        return this.zeroCompsThatLess((int) x, (int) y);
    }

    public Vec2i zeroCompsThatLess(double x, double y) {
        return this.zeroCompsThatLess((int) x, (int) y);
    }

    public Vec2i zeroCompsThatLess(float xy) {
        return this.zeroCompsThatLess(xy, xy);
    }

    public Vec2i zeroCompsThatLess(double xy) {
        return this.zeroCompsThatLess(xy, xy);
    }

    public Vec2i zeroCompsThatLess(int xy) {
        return this.zeroCompsThatLess(xy, xy);
    }

    public Vec2i zeroCompsThatLess(Vec2f vector) {
        return this.zeroCompsThatLess(vector.x, vector.y);
    }

    public Vec2i zeroCompsThatLess(Vec2d vector) {
        return this.zeroCompsThatLess(vector.x, vector.y);
    }

    public Vec2i zeroCompsThatLess(Vec2i vector) {
        return this.zeroCompsThatLess(vector.x, vector.y);
    }

    public Vec2i zeroCompsThatZero(int x, int y) {
        if(x == 0)
            this.x = 0;
        if(y == 0)
            this.y = 0;
        return this;
    }

    public Vec2i zeroCompsThatZero(float x, float y) {
        return this.zeroCompsThatZero((int) x, (int) y);
    }

    public Vec2i zeroCompsThatZero(double x, double y) {
        return this.zeroCompsThatZero((int) x, (int) y);
    }

    public Vec2i zeroCompsThatZero(float xy) {
        return this.zeroCompsThatZero(xy, xy);
    }

    public Vec2i zeroCompsThatZero(double xy) {
        return this.zeroCompsThatZero(xy, xy);
    }

    public Vec2i zeroCompsThatZero(int xy) {
        return this.zeroCompsThatZero(xy, xy);
    }

    public Vec2i zeroCompsThatZero(Vec2f vector) {
        return this.zeroCompsThatZero(vector.x, vector.y);
    }

    public Vec2i zeroCompsThatZero(Vec2d vector) {
        return this.zeroCompsThatZero(vector.x, vector.y);
    }

    public Vec2i zeroCompsThatZero(Vec2i vector) {
        return this.zeroCompsThatZero(vector.x, vector.y);
    }

    public Vec2i zeroCompsThatBigger(int x, int y) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0;
        return this;
    }

    public Vec2i zeroCompsThatBigger(float x, float y) {
        return this.zeroCompsThatBigger((int) x, (int) y);
    }

    public Vec2i zeroCompsThatBigger(double x, double y) {
        return this.zeroCompsThatBigger((int) x, (int) y);
    }

    public Vec2i zeroCompsThatBigger(float xy) {
        return this.zeroCompsThatBigger(xy, xy);
    }

    public Vec2i zeroCompsThatBigger(double xy) {
        return this.zeroCompsThatBigger(xy, xy);
    }

    public Vec2i zeroCompsThatBigger(int xy) {
        return this.zeroCompsThatBigger(xy, xy);
    }

    public Vec2i zeroCompsThatBigger(Vec2f vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y);
    }

    public Vec2i zeroCompsThatBigger(Vec2d vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y);
    }

    public Vec2i zeroCompsThatBigger(Vec2i vector) {
        return this.zeroCompsThatBigger(vector.x, vector.y);
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


    public static Vec2i lerp(Vec2i dst, int startX, int startY, int endX, int endY, float t) {
        return dst.set(
            Maths.lerp(startX, endX, t),
            Maths.lerp(startY, endY, t)
        );
    }

    public static Vec2i lerp(Vec2i dst, Vec2i start, Vec2i end, float t) {
        return lerp(dst, start.x, start.y, end.x, end.y, t);
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


    public Vec2i signum(Vec2i dst) {
        return dst.set(Math.signum(x), Math.signum(y));
    }


    public Vec2i setAngleRad(double radians) {
        return this.set(Math.cos(radians), Math.sin(radians));
    }

    public Vec2i setAngle(double degrees) {
        return this.setAngleRad(degrees * Maths.TO_RAD);
    }


    public static float angleRad(int x, int y) {
        float angle = Mathc.atan2(y, x);
        if(angle < 0)
            angle += Maths.TWO_PI;
        return angle;
    }

    public static float angleRad(Vec2i vector) {
        return angleRad(vector.x, vector.y);
    }

    public static float angle(int x, int y) {
        return angleRad(x, y) * Maths.TO_DEG;
    }

    public static float angle(Vec2i vector) {
        return angle(vector.x, vector.y);
    }

    public float angleRad() {
        return angleRad(this);
    }

    public float angle() {
        return angle(this);
    }


    public static float angleBetweenRad(int x1, int y1, int x2, int y2) {
        final float lengthProduct = len(x1, y1) * len(x2, y2);
        if(lengthProduct == 0)
            return 0;
        final float cos = dot(x1, y1, x2, y2) / lengthProduct;
        return Mathc.acos(Maths.clamp(cos, -1, 1));
    }

    public static float angleBetweenRad(Vec2i vector1, int x2, int y2) {
        return angleBetweenRad(vector1.x, vector1.y, x2, y2);
    }

    public static float angleBetweenRad(int x1, int y1, Vec2i vector2) {
        return angleBetweenRad(x1, y1, vector2.x, vector2.y);
    }

    public static float angleBetweenRad(Vec2i vector1, Vec2i vector2) {
        return angleBetweenRad(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public float angleBetweenRad(int x, int y) {
        return angleBetweenRad(this, x, y);
    }

    public float angleBetweenRad(Vec2i vector) {
        return angleBetweenRad(this, vector);
    }

    public static float angleBetween(int x1, int y1, int x2, int y2) {
        return angleBetweenRad(x1, y1, x2, y2) * Maths.TO_DEG;
    }

    public static float angleBetween(Vec2i vector1, int x2, int y2) {
        return angleBetween(vector1.x, vector1.y, x2, y2);
    }

    public static float angleBetween(int x1, int y1, Vec2i vector2) {
        return angleBetween(x1, y1, vector2.x, vector2.y);
    }

    public static float angleBetween(Vec2i vector1, Vec2i vector2) {
        return angleBetween(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public float angleBetween(int x, int y) {
        return angleBetween(this, x, y);
    }

    public float angleBetween(Vec2i vector) {
        return angleBetween(this, vector);
    }


    public Vec2i rotateRad(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return this.set((x * cos - y * sin), (x * sin + y * cos));
    }

    public Vec2i rotate(double degrees) {
        return this.rotateRad(degrees * Maths.TO_RAD);
    }


    public Vec2i mulMat3(float[] matrix) {
        return this.set(
            x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + matrix[Matrix3.m20],
            x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + matrix[Matrix3.m21]
        );
    }

    public Vec2i mulMat3(Matrix3f matrix) {
        return this.mulMat3(matrix.val);
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


    public Vec2i clamp(int minX, int minY, int maxX, int maxY) {
        return this.set(
            Maths.clamp(x, minX, maxX),
            Maths.clamp(y, minY, maxY)
        );
    }

    public Vec2i clamp(int minX, int minY, Vec2i max) {
        return this.clamp(minX, minY, max.x, max.y);
    }

    public Vec2i clamp(int minXY, int maxXY) {
        return this.clamp(minXY, minXY, maxXY, maxXY);
    }

    public Vec2i clamp(int minXY, Vec2i max) {
        return this.clamp(minXY, minXY, max.x, max.y);
    }

    public Vec2i clamp(Vec2i min, int maxX, int maxY) {
        return this.clamp(min.x, min.y, maxX, maxY);
    }

    public Vec2i clamp(Vec2i min, int maxXY) {
        return this.clamp(min.x, min.y, maxXY, maxXY);
    }

    public Vec2i clamp(Vec2i min, Vec2i max) {
        return this.clamp(min.x, min.y, max.x, max.y);
    }


    public Vec2i copy() {
        return new Vec2i(this);
    }

    public static boolean equals(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 == y2;
    }

    public static boolean notEquals(int x1, int y1, int x2, int y2) {
        return x1 != x2 || y1 != y2;
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
        return "{" + x + ", " + y + "}";
    }

}