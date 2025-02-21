package jpize.util.math.geometry;

import jpize.util.math.axisaligned.AARectBody;
import jpize.util.math.vector.Vec2d;
import jpize.util.math.vector.Vec2f;

public class Intersector {

    public static boolean isPointInRect(double pointX, double pointY, double x, double y, double width, double height) {
        return AARectBody.isPointIn(pointX, pointY, x, y, x + width, y + height);
    }

    public static boolean isPointOnRect(double pointX, double pointY, double x, double y, double width, double height) {
        return AARectBody.isPointOn(pointX, pointY, x, y, x + width, y + height);
    }


    public static boolean isPointInQuad(double pointX, double pointY, double x1, double y1, double x2, double y2,
                                        double x3, double y3, double x4, double y4) {
        if(Vec2d.crs(x1 - x2, y1 - y2, x1 - pointX, y1 - pointY) >= 0D) return false;
        if(Vec2d.crs(x2 - x3, y2 - y3, x2 - pointX, y2 - pointY) >= 0D) return false;
        if(Vec2d.crs(x3 - x4, y3 - y4, x3 - pointX, y3 - pointY) >= 0D) return false;
        if(Vec2d.crs(x4 - x1, y4 - y1, x4 - pointX, y4 - pointY) >= 0D) return false;
        return true;
    }

    public static boolean isPointOnQuad(double pointX, double pointY, double x1, double y1, double x2, double y2,
                                        double x3, double y3, double x4, double y4) {
        if(Vec2d.crs(x1 - x2, y1 - y2, x1 - pointX, y1 - pointY) > 0D) return false;
        if(Vec2d.crs(x2 - x3, y2 - y3, x2 - pointX, y2 - pointY) > 0D) return false;
        if(Vec2d.crs(x3 - x4, y3 - y4, x3 - pointX, y3 - pointY) > 0D) return false;
        if(Vec2d.crs(x4 - x1, y4 - y1, x4 - pointX, y4 - pointY) > 0D) return false;
        return true;
    }


    public static boolean isPointInTriangle(double pointX, double pointY,
                                            double x1, double y1, double x2, double y2, double x3, double y3) {
        if(Vec2d.crs(x1 - x2, y1 - y2, x1 - pointX, y1 - pointY) >= 0D) return false;
        if(Vec2d.crs(x2 - x3, y2 - y3, x2 - pointX, y2 - pointY) >= 0D) return false;
        if(Vec2d.crs(x3 - x1, y3 - y1, x3 - pointX, y3 - pointY) >= 0D) return false;
        return true;
    }

    public static boolean isPointOnTriangle(double pointX, double pointY,
                                            double x1, double y1, double x2, double y2, double x3, double y3) {
        if(Vec2d.crs(x1 - x2, y1 - y2, x1 - pointX, y1 - pointY) > 0D) return false;
        if(Vec2d.crs(x2 - x3, y2 - y3, x2 - pointX, y2 - pointY) > 0D) return false;
        if(Vec2d.crs(x3 - x1, y3 - y1, x3 - pointX, y3 - pointY) > 0D) return false;
        return true;
    }


    public static boolean isRangesOverlapping(double begin1, double end1, double begin2, double end2) {
        return (begin1 < end2 && end1 > begin2);
    }


    public static boolean getLineIntersectLine(Vec2f dst, double a1, double b1, double c1, double a2, double b2, double c2) {
        final double determinant = (a1 * b2 - a2 * b1);
        if(determinant == 0D)
            return false;

        if(dst != null)
            dst.set((b2 * c1 - b1 * c2), (a1 * c2 - a2 * c1)).div(determinant);

        return true;
    }

    public static boolean isLineIntersectLine(double a1, double b1, double c1, double a2, double b2, double c2) {
        final double determinant = (a1 * b2 - a2 * b1);
        return (determinant != 0D);
    }

    public static boolean getSegmentIntersectSegment(Vec2f dst,
                                                     float beginX1, float beginY1, float endX1, float endY1,
                                                     float beginX2, float beginY2, float endX2, float endY2) {
        final float dx1 = (endX1 - beginX1);
        final float dy1 = (endY1 - beginY1);
        final float dx2 = (endX2 - beginX2);
        final float dy2 = (endY2 - beginY2);

        final float denominator = (dx1 * dy2 - dx2 * dy1);
        if(denominator == 0F)
            return false; // collinear
        final boolean denomimatorPositive = (denominator > 0F);

        final float dx12 = (beginX1 - beginX2);
        final float dy12 = (beginY1 - beginY2);

        final float numerator1 = (dx1 * dy12 - dy1 * dx12);
        if((numerator1 < 0F) == denomimatorPositive)
            return false;

        final float numerator2 = (dx2 * dy12 - dy2 * dx12);
        if((numerator2 < 0F) == denomimatorPositive)
            return false;

        if(((numerator1 > denominator) == denomimatorPositive) || ((numerator2 > denominator) == denomimatorPositive))
            return false;

        final float t = (numerator2 / denominator);
        if(dst != null) {
            dst.x = (beginX1 + t * dx1);
            dst.y = (beginY1 + t * dy1);
        }
        return true;
    }

    public static boolean getSegmentIntersectSegment(Vec2f dst, Vec2f begin1, Vec2f end1, Vec2f begin2, Vec2f end2) {
        return getSegmentIntersectSegment(dst, begin1.x, begin1.y, end1.x, end1.y, begin2.x, begin2.y, end2.x, end2.y);
    }

    public static boolean isSegmentIntersectSegment(float beginX1, float beginY1, float endX1, float endY1,
                                                    float beginX2, float beginY2, float endX2, float endY2) {

        final float dx1 = (endX1 - beginX1);
        final float dy1 = (endY1 - beginY1);
        final float dx2 = (endX2 - beginX2);
        final float dy2 = (endY2 - beginY2);

        final float denominator = (dx1 * dy2 - dx2 * dy1);
        if(denominator == 0F)
            return false; // collinear
        final boolean denomimatorPositive = (denominator > 0F);

        final float dx12 = (beginX1 - beginX2);
        final float dy12 = (beginY1 - beginY2);

        final float numerator1 = (dx1 * dy12 - dy1 * dx12);
        if((numerator1 < 0F) == denomimatorPositive)
            return false;

        final float numerator2 = (dx2 * dy12 - dy2 * dx12);
        if((numerator2 < 0F) == denomimatorPositive)
            return false;

        return ((numerator1 > denominator) != denomimatorPositive) &&
                ((numerator2 > denominator) != denomimatorPositive);
    }

    public static boolean isSegmentIntersectSegment(Vec2f begin1, Vec2f end1, Vec2f begin2, Vec2f end2) {
        return isSegmentIntersectSegment(begin1.x, begin1.y, end1.x, end1.y, begin2.x, begin2.y, end2.x, end2.y);
    }

    public static boolean isPointOnSegment(double pointX, double pointY, double ax, double ay, double bx, double by) {
        final double crossproduct = (pointY - ay) * (bx - ax) - (pointX - ax) * (by - ay);

        if(Math.abs(crossproduct) > 0.0001D)
            return false;

        final double dotproduct = (pointX - ax) * (bx - ax) + (pointY - ay) * (by - ay);
        if(dotproduct < 0D)
            return false;

        final double squaredlengthba = (bx - ax) * (bx - ax) + (by - ay) * (by - ay);
        return !(dotproduct > squaredlengthba);
    }

    public static boolean isPointOnSegment(double pointX, double pointY, Vec2f a, Vec2f b) {
        return isPointOnSegment(pointX, pointY, a.x, a.y, b.x, b.y);
    }

    public static boolean isPointOnSegment(Vec2f point, double ax, double ay, double bx, double by) {
        return isPointOnSegment(point.x, point.y, ax, ay, bx, by);
    }

    public static boolean isPointOnSegment(Vec2f point, Vec2f a, Vec2f b) {
        return isPointOnSegment(point.x, point.y, a.x, a.y, b.x, b.y);
    }


    public static float getPointToSegmentDistance(float pointX, float pointY, float ax, float ay, float bx, float by) {
        final float segmentX = (bx - ax);
        final float segmentY = (by - ay);

        final float pointToAX = (pointX - ax);
        final float pointToAY = (pointY - ay);

        final float segmentDotPointToA = (segmentX * pointToAX + segmentY * pointToAY);
        final float segmentLen2 = (segmentX * segmentX + segmentY * segmentY);
        final float t = (segmentLen2 == 0F) ? 0F : (segmentDotPointToA / segmentLen2);

        if(t < 0F){
            // point is closer to A
            return Vec2f.len(pointToAX, pointToAY);
        }else if(t > 1F){
            // point is closer to B
            return Vec2f.dst(pointX, pointY, bx, by);
        }else{
            // project point on the segment
            final float projectionX = (ax + t * segmentX);
            final float projectionY = (ay + t * segmentY);
            return Vec2f.dst(pointX, pointY, projectionX, projectionY);
        }
    }

    public static float getPointToSegmentDistance(float pointX, float pointY, Vec2f a, Vec2f b) {
        return getPointToSegmentDistance(pointX, pointY, a.x, a.y, b.x, b.y);
    }

    public static float getPointToSegmentDistance(Vec2f point, float ax, float ay, float bx, float by) {
        return getPointToSegmentDistance(point.x, point.y, ax, ay, bx, by);
    }

    public static float getPointToSegmentDistance(Vec2f point, Vec2f a, Vec2f b) {
        return getPointToSegmentDistance(point.x, point.y, a.x, a.y, b.x, b.y);
    }

}
