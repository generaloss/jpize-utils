package jpize.util.math.vector;

import jpize.util.math.Maths;
import jpize.util.math.matrix.Matrix3;
import jpize.util.math.matrix.Matrix3f;
import java.util.Objects;

public class Vec2d {

    public double x;
    public double y;

    public Vec2d() { }

    public Vec2d(float xy) {
        this.set(xy);
    }

    public Vec2d(double xy) {
        this.set(xy);
    }

    public Vec2d(int xy) {
        this.set(xy);
    }

    public Vec2d(float x, float y) {
        this.set(x, y);
    }

    public Vec2d(double x, double y) {
        this.set(x, y);
    }

    public Vec2d(int x, int y) {
        this.set(x, y);
    }

    public Vec2d(Vec2f vector) {
        this.set(vector.x, vector.y);
    }

    public Vec2d(Vec2d vector) {
        this.set(vector.x, vector.y);
    }

    public Vec2d(Vec2i vector) {
        this.set(vector.x, vector.y);
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public Vec2d set(double xy) {
        this.x = xy;
        this.y = xy;
        return this;
    }

    public Vec2d set(float xy) {
        return this.set((double) xy);
    }

    public Vec2d set(int xy) {
        return this.set((double) xy);
    }

    public Vec2d set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec2d set(float x, float y) {
        return this.set((double) x, (double) y);
    }

    public Vec2d set(int x, int y) {
        return this.set((double) x, (double) y);
    }

    public Vec2d set(Vec2f vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec2d set(Vec2d vector) {
        return this.set(vector.x, vector.y);
    }

    public Vec2d set(Vec2i vector) {
        return this.set(vector.x, vector.y);
    }


    public Vec2d add(double xy) {
        this.x += xy;
        this.y += xy;
        return this;
    }

    public Vec2d add(float xy) {
        return this.add((double) xy);
    }

    public Vec2d add(int xy) {
        return this.add((double) xy);
    }

    public Vec2d add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2d add(float x, float y) {
        return this.add((double) x, (double) y);
    }

    public Vec2d add(int x, int y) {
        return this.add((double) x, (double) y);
    }

    public Vec2d add(Vec2f vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec2d add(Vec2d vector) {
        return this.add(vector.x, vector.y);
    }

    public Vec2d add(Vec2i vector) {
        return this.add(vector.x, vector.y);
    }


    public Vec2d sub(double xy) {
        this.x -= xy;
        this.y -= xy;
        return this;
    }

    public Vec2d sub(float xy) {
        return this.sub((double) xy);
    }

    public Vec2d sub(int xy) {
        return this.sub((double) xy);
    }

    public Vec2d sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2d sub(float x, float y) {
        return this.sub((double) x, (double) y);
    }

    public Vec2d sub(int x, int y) {
        return this.sub((double) x, (double) y);
    }

    public Vec2d sub(Vec2f vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec2d sub(Vec2d vector) {
        return this.sub(vector.x, vector.y);
    }

    public Vec2d sub(Vec2i vector) {
        return this.sub(vector.x, vector.y);
    }


    public Vec2d mul(double xy) {
        this.x *= xy;
        this.y *= xy;
        return this;
    }

    public Vec2d mul(float xy) {
        return this.mul((double) xy);
    }

    public Vec2d mul(int xy) {
        return this.mul((double) xy);
    }

    public Vec2d mul(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vec2d mul(float x, float y) {
        return this.mul((double) x, (double) y);
    }

    public Vec2d mul(int x, int y) {
        return this.mul((double) x, (double) y);
    }

    public Vec2d mul(Vec2f vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec2d mul(Vec2d vector) {
        return this.mul(vector.x, vector.y);
    }

    public Vec2d mul(Vec2i vector) {
        return this.mul(vector.x, vector.y);
    }


    public Vec2d div(double xy) {
        this.x /= xy;
        this.y /= xy;
        return this;
    }

    public Vec2d div(float xy) {
        return this.div((double) xy);
    }

    public Vec2d div(int xy) {
        return this.div((double) xy);
    }

    public Vec2d div(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vec2d div(float x, float y) {
        return this.div((double) x, (double) y);
    }

    public Vec2d div(int x, int y) {
        return this.div((double) x, (double) y);
    }

    public Vec2d div(Vec2f vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec2d div(Vec2d vector) {
        return this.div(vector.x, vector.y);
    }

    public Vec2d div(Vec2i vector) {
        return this.div(vector.x, vector.y);
    }


    public static double dst(double x1, double y1, double x2, double y2) {
        final double dx = x2 - x1;
        final double dy = y2 - y1;
        
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double dst(double x1, double y1, float x2, float y2) {
        return dst(x1, y1, (double) x2, (double) y2);
    }

    public static double dst(double x1, double y1, int x2, int y2) {
        return dst(x1, y1, (double) x2, (double) y2);
    }

    public static double dst(double x, double y, Vec2f vector) {
        return dst(x, y, vector.x, vector.y);
    }

    public static double dst(double x, double y, Vec2d vector) {
        return dst(x, y, vector.x, vector.y);
    }

    public static double dst(double x, double y, Vec2i vector) {
        return dst(x, y, vector.x, vector.y);
    }

    public static double dst(Vec2d vector, float x, float y) {
        return dst(vector.x, vector.y, x, y);
    }

    public static double dst(Vec2d vector, double x, double y) {
        return dst(vector.x, vector.y, x, y);
    }

    public static double dst(Vec2d vector, int x, int y) {
        return dst(vector.x, vector.y, x, y);
    }

    public static double dst(Vec2d vector1 , Vec2f vector2) {
        return dst(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public static double dst(Vec2d vector1 , Vec2d vector2) {
        return dst(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public static double dst(Vec2d vector1 , Vec2i vector2) {
        return dst(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public double dst(float x, float y) {
        return dst(this, x, y);
    }

    public double dst(double x, double y) {
        return dst(this, x, y);
    }

    public double dst(int x, int y) {
        return dst(this, x, y);
    }

    public double dst(Vec2f vector) {
        return dst(this, vector);
    }

    public double dst(Vec2d vector) {
        return dst(this, vector);
    }

    public double dst(Vec2i vector) {
        return dst(this, vector);
    }


    public static Vec2d shorter(Vec2d vector1, Vec2d vector2) {
        return (vector1.len2() < vector2.len2()) ? vector1 : vector2;
    }

    public static Vec2d longer(Vec2d vector1, Vec2d vector2) {
        return (vector1.len2() > vector2.len2()) ? vector1 : vector2;
    }

    public Vec2d setShorter(Vec2d vector1, Vec2d vector2) {
        return this.set(shorter(vector1, vector2));
    }

    public Vec2d setLonger(Vec2d vector1, Vec2d vector2) {
        return this.set(longer(vector1, vector2));
    }


    public double minComp() {
        return Math.min(x, y);
    }

    public double maxComp() {
        return Math.max(x, y);
    }


    public static Vec2d minComps(Vec2d dst, double x1, double y1, double x2, double y2) {
        return dst.set(Math.min(x1, x2), Math.min(y1, y2));
    }

    public static Vec2d minComps(Vec2d dst, double x1, double y1, Vec2d vector2) {
        return minComps(dst, x1, y1, vector2.x, vector2.y);
    }

    public static Vec2d minComps(Vec2d dst, Vec2d vector1, double x2, double y2) {
        return minComps(dst, vector1.x, vector1.y, x2, y2);
    }

    public static Vec2d minComps(Vec2d dst, Vec2d vector1, double xy2) {
        return minComps(dst, vector1.x, vector1.y, xy2, xy2);
    }

    public static Vec2d minComps(Vec2d dst, Vec2d vector1, Vec2d vector2) {
        return minComps(dst, vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public Vec2d setMinComps(double x1, double y1, double x2, double y2) {
        return minComps(this, x1, y1, x2, y2);
    }

    public Vec2d setMinComps(double x1, double y1, Vec2d vector2) {
        return minComps(this, x1, y1, vector2);
    }

    public Vec2d setMinComps(Vec2d vector1, double x2, double y2) {
        return minComps(this, vector1, x2, y2);
    }

    public Vec2d setMinComps(Vec2d vector1, double xy2) {
        return minComps(this, vector1, xy2);
    }

    public Vec2d setMinComps(Vec2d vector1, Vec2d vector2) {
        return minComps(this, vector1, vector2);
    }


    public static Vec2d maxComps(Vec2d dst, double x1, double y1, double x2, double y2) {
        return dst.set(Math.max(x1, x2), Math.max(y1, y2));
    }

    public static Vec2d maxComps(Vec2d dst, double x1, double y1, Vec2d vector2) {
        return maxComps(dst, x1, y1, vector2.x, vector2.y);
    }

    public static Vec2d maxComps(Vec2d dst, Vec2d vector1, double x2, double y2) {
        return maxComps(dst, vector1.x, vector1.y, x2, y2);
    }

    public static Vec2d maxComps(Vec2d dst, Vec2d vector1, double xy2) {
        return maxComps(dst, vector1.x, vector1.y, xy2, xy2);
    }

    public static Vec2d maxComps(Vec2d dst, Vec2d vector1, Vec2d vector2) {
        return maxComps(dst, vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public Vec2d setMaxComps(double x1, double y1, double x2, double y2) {
        return maxComps(this, x1, y1, x2, y2);
    }

    public Vec2d setMaxComps(double x1, double y1, Vec2d vector2) {
        return maxComps(this, x1, y1, vector2);
    }

    public Vec2d setMaxComps(Vec2d vector1, double x2, double y2) {
        return maxComps(this, vector1, x2, y2);
    }

    public Vec2d setMaxComps(Vec2d vector1, double xy2) {
        return maxComps(this, vector1, xy2);
    }

    public Vec2d setMaxComps(Vec2d vector1, Vec2d vector2) {
        return maxComps(this, vector1, vector2);
    }


    public Vec2d zero() {
        return this.set(0D);
    }

    public boolean isZero() {
        return x == 0D && y == 0D;
    }

    public Vec2d zeroThatLess(double x, double y) {
        if(Math.abs(this.x) < Math.abs(x))
            this.x = 0D;
        if(Math.abs(this.y) < Math.abs(y))
            this.y = 0D;
        return this;
    }

    public Vec2d zeroThatLess(float x, float y) {
        return this.zeroThatLess((double) x, (double) y);
    }

    public Vec2d zeroThatLess(int x, int y) {
        return this.zeroThatLess((double) x, (double) y);
    }

    public Vec2d zeroThatLess(float xy) {
        return this.zeroThatLess(xy, xy);
    }

    public Vec2d zeroThatLess(double xy) {
        return this.zeroThatLess(xy, xy);
    }

    public Vec2d zeroThatLess(int xy) {
        return this.zeroThatLess(xy, xy);
    }

    public Vec2d zeroThatLess(Vec2f vector) {
        return this.zeroThatLess(vector.x, vector.y);
    }

    public Vec2d zeroThatLess(Vec2d vector) {
        return this.zeroThatLess(vector.x, vector.y);
    }

    public Vec2d zeroThatLess(Vec2i vector) {
        return this.zeroThatLess(vector.x, vector.y);
    }

    public Vec2d zeroThatZero(double x, double y) {
        if(x == 0D)
            this.x = 0D;
        if(y == 0D)
            this.y = 0D;
        return this;
    }

    public Vec2d zeroThatZero(float x, float y) {
        return this.zeroThatZero((double) x, (double) y);
    }

    public Vec2d zeroThatZero(int x, int y) {
        return this.zeroThatZero((double) x, (double) y);
    }

    public Vec2d zeroThatZero(float xy) {
        return this.zeroThatZero(xy, xy);
    }

    public Vec2d zeroThatZero(double xy) {
        return this.zeroThatZero(xy, xy);
    }

    public Vec2d zeroThatZero(int xy) {
        return this.zeroThatZero(xy, xy);
    }

    public Vec2d zeroThatZero(Vec2f vector) {
        return this.zeroThatZero(vector.x, vector.y);
    }

    public Vec2d zeroThatZero(Vec2d vector) {
        return this.zeroThatZero(vector.x, vector.y);
    }

    public Vec2d zeroThatZero(Vec2i vector) {
        return this.zeroThatZero(vector.x, vector.y);
    }

    public Vec2d zeroThatBigger(double x, double y) {
        if(Math.abs(this.x) > Math.abs(x))
            this.x = 0D;
        if(Math.abs(this.y) > Math.abs(y))
            this.y = 0D;
        return this;
    }

    public Vec2d zeroThatBigger(float x, float y) {
        return this.zeroThatBigger((double) x, (double) y);
    }

    public Vec2d zeroThatBigger(int x, int y) {
        return this.zeroThatBigger((double) x, (double) y);
    }

    public Vec2d zeroThatBigger(float xy) {
        return this.zeroThatBigger(xy, xy);
    }

    public Vec2d zeroThatBigger(double xy) {
        return this.zeroThatBigger(xy, xy);
    }

    public Vec2d zeroThatBigger(int xy) {
        return this.zeroThatBigger(xy, xy);
    }

    public Vec2d zeroThatBigger(Vec2f vector) {
        return this.zeroThatBigger(vector.x, vector.y);
    }

    public Vec2d zeroThatBigger(Vec2d vector) {
        return this.zeroThatBigger(vector.x, vector.y);
    }

    public Vec2d zeroThatBigger(Vec2i vector) {
        return this.zeroThatBigger(vector.x, vector.y);
    }


    public double area() {
        return x * y;
    }


    public static double len2(double x, double y) {
        return x * x + y * y;
    }

    public static double len2(Vec2d vector) {
        return len2(vector.x, vector.y);
    }

    public static double len(double x, double y) {
        return Math.sqrt(len2(x, y));
    }

    public static double len(Vec2d vector) {
        return Math.sqrt(len2(vector.x, vector.y));
    }

    public double len2() {
        return len2(this);
    }

    public double len() {
        return len(this);
    }


    public Vec2d nor() {
        double len = this.len2();
        if(len == 0D || len == 1D)
            return this;
        
        len = 1D / Math.sqrt(len);
        return this.mul(len);
    }


    public Vec2d abs() {
        if(x < 0D) x *= -1D;
        if(y < 0D) y *= -1D;
        return this;
    }


    public static Vec2d lerp(Vec2d dst, double startX, double startY, double endX, double endY, double t) {
        return dst.set(
            Maths.lerp(startX, endX, t),
            Maths.lerp(startY, endY, t)
        );
    }

    public static Vec2d lerp(Vec2d dst, Vec2d start, Vec2d end, double t) {
        return lerp(dst, start.x, start.y, end.x, end.y, t);
    }

    public Vec2d lerp(double startX, double startY, double endX, double endY, double t) {
        return lerp(this, startX, startY, endX, endY, t);
    }

    public Vec2d lerp(Vec2d start, Vec2d end, double t) {
        return lerp(this, start, end, t);
    }


    public static double dot(double x1, double y1, double x2, double y2) {
        return x1 * x2 + y1 * y2;
    }

    public static double dot(Vec2d vector1, double x2, double y2) {
        return dot(vector1.x, vector1.y, x2, y2);
    }

    public static double dot(double x1, double y1, Vec2d vector2) {
        return dot(x1, y1, vector2.x, vector2.y);
    }

    public static double dot(Vec2d vector1, Vec2d vector2) {
        return dot(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public double dot(Vec2d vector) {
        return dot(this, vector);
    }

    public double dot(double x, double y) {
        return dot(this, x, y);
    }


    public static double crs(double x1, double y1, double x2, double y2) {
        return (x1 * y2) - (y1 * x2);
    }

    public static double crs(Vec2d vector1, double x2, double y2) {
        return crs(vector1.x, vector1.y, x2, y2);
    }

    public static double crs(double x1, double y1, Vec2d vector2) {
        return crs(x1, y1, vector2.x, vector2.y);
    }

    public static double crs(Vec2d vector1, Vec2d vector2) {
        return crs(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public double crs(Vec2d vector) {
        return crs(this, vector);
    }

    public double crs(double x, double y) {
        return crs(this, x, y);
    }


    public Vec2d frac() {
        x = Maths.frac(x);
        y = Maths.frac(y);
        return this;
    }


    public Vec2i signum(Vec2i dst) {
        return dst.set(Math.signum(x), Math.signum(y));
    }


    public static double rad(double x1, double y1, double x2, double y2) {
        final double cos = dot(x1, y1, x2, y2) / (len(x1, y1) * len(x2, y2));
        return Math.acos(Maths.clamp(cos, -1D, 1D));
    }

    public static double rad(Vec2d vector1, double x2, double y2) {
        return rad(vector1.x, vector1.y, x2, y2);
    }

    public static double rad(double x1, double y1, Vec2d vector2) {
        return rad(x1, y1, vector2.x, vector2.y);
    }

    public static double rad(Vec2d vector1, Vec2d vector2) {
        return rad(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public double rad(double x, double y) {
        return rad(this, x, y);
    }

    public double rad(Vec2d vector) {
        return rad(this, vector);
    }

    public static double deg(double x1, double y1, double x2, double y2) {
        return rad(x1, y1, x2, y2) * Maths.toDeg;
    }

    public static double deg(Vec2d vector1, double x2, double y2) {
        return deg(vector1.x, vector1.y, x2, y2);
    }

    public static double deg(double x1, double y1, Vec2d vector2) {
        return deg(x1, y1, vector2.x, vector2.y);
    }

    public static double deg(Vec2d vector1, Vec2d vector2) {
        return deg(vector1.x, vector1.y, vector2.x, vector2.y);
    }

    public double deg(double x, double y) {
        return deg(this, x, y);
    }

    public double deg(Vec2d vector) {
        return deg(this, vector);
    }


    public Vec2d rotateRad(double radians) {
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        return this.set((x * cos + y * sin), (x * -sin + y * cos));
    }

    public Vec2d rotate(double degrees) {
        return this.rotateRad(degrees * Maths.toRad);
    }


    public Vec2d mulMat3(float[] matrix) {
        return this.set(
            x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + matrix[Matrix3.m20],
            x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + matrix[Matrix3.m21]
        );
    }

    public Vec2d mulMat3(Matrix3f matrix) {
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


    public static double aspect(double x, double y) {
        return x / y;
    }

    public double aspect() {
        return aspect(x, y);
    }


    public Vec2d floor() {
        x = Maths.floor(x);
        y = Maths.floor(y);
        return this;
    }

    public Vec2d round() {
        x = Maths.round(x);
        y = Maths.round(y);
        return this;
    }

    public Vec2d ceil() {
        x = Maths.ceil(x);
        y = Maths.ceil(y);
        return this;
    }


    public Vec2f castFloat() {
        return new Vec2f(this);
    }

    public Vec2i castInt() {
        return new Vec2i(this);
    }


    public Vec3d to3D() {
        return new Vec3d(this);
    }


    public Vec2d clampLength(double max) {
        final double len = this.len();
        if(len <= max)
            return this;
        return this.nor().mul(max);
    }


    public Vec2d reduce(double reduceX, double reduceY) {
        final double len = this.len();
        return this.nor().mul(len - reduceX, len - reduceY);
    }


    public Vec2d copy() {
        return new Vec2d(this);
    }

    public static boolean equals(double x1, double y1, double x2, double y2) {
        return x1 == x2 && y1 == y2;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null || getClass() != object.getClass())
            return false;
        
        final Vec2d vec = (Vec2d) object;
        return Vec2d.equals(x, y, vec.x, vec.y);
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