package jpize.util.math;

import jpize.util.Rect;
import jpize.util.math.axisaligned.AABoxBody;
import jpize.util.math.axisaligned.AARectBody;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec3f;

public class Intersector {

    public static boolean isPointInAARect(float pointX, float pointY, float minX, float minY, float maxX, float maxY) {
        return (pointX > minX && pointY > minY && pointX < maxX && pointY < maxY);
    }

    public static boolean isPointInRect(float pointX, float pointY, float x, float y, float width, float height) {
        return isPointInAARect(pointX, pointY, x, y, x + width, y + height);
    }


    public static boolean isPointOnAARect(float pointX, float pointY, float minX, float minY, float maxX, float maxY) {
        return (pointX >= minX && pointY >= minY && pointX <= maxX && pointY <= maxY);
    }

    public static boolean isPointOnRect(float pointX, float pointY, float x, float y, float width, float height) {
        return isPointOnAARect(pointX, pointY, x, y, x + width, y + height);
    }


    public static boolean isPointInQuad(float pointX, float pointY, float x1, float y1, float x2, float y2,
                                        float x3, float y3, float x4, float y4) {
        if(Vec2f.crs(x1 - x2, y1 - y2, x1 - pointX, y1 - pointY) >= 0F) return false;
        if(Vec2f.crs(x2 - x3, y2 - y3, x2 - pointX, y2 - pointY) >= 0F) return false;
        if(Vec2f.crs(x3 - x4, y3 - y4, x3 - pointX, y3 - pointY) >= 0F) return false;
        if(Vec2f.crs(x4 - x1, y4 - y1, x4 - pointX, y4 - pointY) >= 0F) return false;
        return true;
    }

    public static boolean isPointOnQuad(float pointX, float pointY, float x1, float y1, float x2, float y2,
                                        float x3, float y3, float x4, float y4) {
        if(Vec2f.crs(x1 - x2, y1 - y2, x1 - pointX, y1 - pointY) > 0F) return false;
        if(Vec2f.crs(x2 - x3, y2 - y3, x2 - pointX, y2 - pointY) > 0F) return false;
        if(Vec2f.crs(x3 - x4, y3 - y4, x3 - pointX, y3 - pointY) > 0F) return false;
        if(Vec2f.crs(x4 - x1, y4 - y1, x4 - pointX, y4 - pointY) > 0F) return false;
        return true;
    }


    public static boolean isPointInTriangle(float pointX, float pointY,
                                            float x1, float y1, float x2, float y2, float x3, float y3) {
        if(Vec2f.crs(x1 - x2, y1 - y2, x1 - pointX, y1 - pointY) >= 0F) return false;
        if(Vec2f.crs(x2 - x3, y2 - y3, x2 - pointX, y2 - pointY) >= 0F) return false;
        if(Vec2f.crs(x3 - x1, y3 - y1, x3 - pointX, y3 - pointY) >= 0F) return false;
        return true;
    }

    public static boolean isPointOnTriangle(float pointX, float pointY,
                                            float x1, float y1, float x2, float y2, float x3, float y3) {
        if(Vec2f.crs(x1 - x2, y1 - y2, x1 - pointX, y1 - pointY) > 0F) return false;
        if(Vec2f.crs(x2 - x3, y2 - y3, x2 - pointX, y2 - pointY) > 0F) return false;
        if(Vec2f.crs(x3 - x1, y3 - y1, x3 - pointX, y3 - pointY) > 0F) return false;
        return true;
    }


    public static boolean isPointOnPolygon(float pointX, float pointY, float... vertices) {
        boolean inside = false;

        for(int i = 0; i < vertices.length; i += 2) {
            final float x1 = vertices[i];
            final float y1 = vertices[i + 1];

            final int j = (i + 2) % vertices.length;
            final float x2 = vertices[j];
            final float y2 = vertices[j + 1];

            if(isPointOnSegment(pointX, pointY, x1, y1, x2, y2))
                return true;

            if((pointY < y1) != (pointY < y2) && (pointX < Math.max(x1, x2))) {
                final float intersectX = (pointY - y1) * (x2 - x1) / (y2 - y1) + x1;
                if (x1 == x2 || pointX < intersectX)
                    inside = !inside;
            }
        }
        return inside;
    }

    public static boolean isPointOnPolygon(Vec2f point, float... vertices) {
        return isPointOnPolygon(point.x, point.y, vertices);
    }

    public static boolean isPointInPolygon(float pointX, float pointY, float... vertices) {
        boolean inside = false;

        for(int i = 0; i < vertices.length; i += 2) {
            final float x1 = vertices[i];
            final float y1 = vertices[i + 1];

            final int j = (i + 2) % vertices.length;
            final float x2 = vertices[j];
            final float y2 = vertices[j + 1];

            if(isPointOnSegment(pointX, pointY, x1, y1, x2, y2))
                return false;

            if(((pointY <= y1) != (pointY <= y2))) {
                final float intersectionX = (pointY - y1) * (x2 - x1) / (y2 - y1) + x1;
                if(pointX <= intersectionX)
                    inside = !inside;
            }
        }
        return inside;
    }

    public static boolean isPointInPolygon(Vec2f point, float... vertices) {
        return isPointInPolygon(point.x, point.y, vertices);
    }


    public static boolean isPolygonsIntersect(float[] vertices1, float[] vertices2) {
        for(int i = 0; i < vertices1.length; i += 2){
            final float p1x1 = vertices1[i];
            final float p1y1 = vertices1[i + 1];

            final int k1 = (i + 2) % vertices1.length;
            final float p1x2 = vertices1[k1];
            final float p1y2 = vertices1[k1 + 1];

            // check points 1
            if(isPointOnPolygon(p1x1, p1y1, vertices2))
                return true;

            // check segments
            for(int j = 0; j < vertices2.length; j += 2){
                final float p2x1 = vertices2[j];
                final float p2y1 = vertices2[j + 1];

                final int k2 = (j + 2) % vertices2.length;
                final float p2x2 = vertices2[k2];
                final float p2y2 = vertices2[k2 + 1];

                if(isSegmentIntersectSegment(p1x1, p1y1, p1x2, p1y2,  p2x1, p2y1, p2x2, p2y2))
                    return true;
            }
        }

        for(int j = 0; j < vertices2.length; j += 2){
            final float p2x1 = vertices2[j];
            final float p2y1 = vertices2[j + 1];

            // check points 2
            if(isPointOnPolygon(p2x1, p2y1, vertices1))
                return true;
        }

        return false;
    }


    public static boolean isGapIntersectGap(float begin1, float end1, float begin2, float end2) {
        return (begin1 <= end2 && end1 >= begin2);
    }

    public static boolean isAARectIntersectAARect(float min1X, float min1Y, float max1X, float max1Y,
                                                  float min2X, float min2Y, float max2X, float max2Y) {
        return isGapIntersectGap(min1X, max1X, min2X, max2X) && isGapIntersectGap(min1Y, max1Y, min2Y, max2Y);
    }

    public static boolean isAARectIntersectAARect(AARectBody a, AARectBody b) {
        final Vec2f min1 = a.getMin();
        final Vec2f max1 = a.getMax();
        final Vec2f min2 = b.getMin();
        final Vec2f max2 = b.getMax();
        return isAARectIntersectAARect(min1.x, min1.y, max1.x, max1.y, min2.x, min2.y, max2.x, max2.y);
    }

    public static boolean isAABoxIntersectAABox(float min1X, float min1Y, float min1Z, float max1X, float max1Y, float max1Z,
                                                float min2X, float min2Y, float min2Z, float max2X, float max2Y, float max2Z) {
        return (
            isAARectIntersectAARect(min1X, min1Y, max1X, max1Y, min2X, min2Y, max2X, max2Y) &&
            isGapIntersectGap(min1Z, max1Z, min2Z, max2Z)
        );
    }

    public static boolean isAABoxIntersectAABox(AABoxBody a, AABoxBody b) {
        final Vec3f min1 = a.getMin();
        final Vec3f max1 = a.getMax();
        final Vec3f min2 = b.getMin();
        final Vec3f max2 = b.getMax();
        return isAABoxIntersectAABox(
            min1.x, min1.y, min1.z,
            max1.x, max1.y, max1.z,
            min2.x, min2.y, min2.z,
            max2.x, max2.y, max2.z
        );
    }


    public static boolean getLineIntersectLine(Vec2f dst, float a1, float b1, float c1, float a2, float b2, float c2) {
        final float determinant = (a1 * b2 - a2 * b1);
        if(determinant == 0F)
            return false;

        if(dst != null)
            dst.set((b2 * c1 - b1 * c2), (a1 * c2 - a2 * c1)).div(determinant);

        return true;
    }

    public static boolean isLineIntersectLine(float a1, float b1, float c1, float a2, float b2, float c2) {
        final float determinant = (a1 * b2 - a2 * b1);
        return (determinant != 0F);
    }

    public static boolean getSegmentIntersectSegment(Vec2f dst,
                                                     float beginX1, float beginY1, float endX1, float endY1,
                                                     float beginX2, float beginY2, float endX2, float endY2) {
        final float dx1 = (endX1 - beginX1);
        final float dy1 = (endY1 - beginY1);
        final float dx2 = (endX2 - beginX2);
        final float dy2 = (endY2 - beginY2);

        final float denominator = (dx1 * dy2 - dx2 * dy1);
        if(denominator == 0)
            return false; // collinear
        final boolean denomimatorPositive = (denominator > 0);

        final float dx12 = (beginX1 - beginX2);
        final float dy12 = (beginY1 - beginY2);

        final float numerator1 = (dx1 * dy12 - dy1 * dx12);
        if((numerator1 < 0) == denomimatorPositive)
            return false;

        final float numerator2 = (dx2 * dy12 - dy2 * dx12);
        if((numerator2 < 0) == denomimatorPositive)
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
        if(denominator == 0)
            return false; // collinear
        final boolean denomimatorPositive = (denominator > 0);

        final float dx12 = (beginX1 - beginX2);
        final float dy12 = (beginY1 - beginY2);

        final float numerator1 = (dx1 * dy12 - dy1 * dx12);
        if((numerator1 < 0) == denomimatorPositive)
            return false;

        final float numerator2 = (dx2 * dy12 - dy2 * dx12);
        if((numerator2 < 0) == denomimatorPositive)
            return false;

        return ((numerator1 > denominator) != denomimatorPositive) &&
                ((numerator2 > denominator) != denomimatorPositive);
    }

    public static boolean isSegmentIntersectSegment(Vec2f begin1, Vec2f end1, Vec2f begin2, Vec2f end2) {
        return isSegmentIntersectSegment(begin1.x, begin1.y, end1.x, end1.y, begin2.x, begin2.y, end2.x, end2.y);
    }

    public static boolean isPointOnSegment(float pointX, float pointY, float ax, float ay, float bx, float by) {
        final float crossproduct = (pointY - ay) * (bx - ax) - (pointX - ax) * (by - ay);

        if(Math.abs(crossproduct) > 0.0001)
            return false;

        final float dotproduct = (pointX - ax) * (bx - ax) + (pointY - ay) * (by - ay);
        if(dotproduct < 0)
            return false;

        final float squaredlengthba = (bx - ax) * (bx - ax) + (by - ay) * (by - ay);
        return !(dotproduct > squaredlengthba);
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


    public static float getRayIntersectAABox(Ray3f ray, AABoxBody aabb) {
        final float x1 = (aabb.getMin().x - ray.origin().x) / ray.directory().x;
        final float x2 = (aabb.getMax().x - ray.origin().x) / ray.directory().x;

        final float y1 = (aabb.getMin().y - ray.origin().y) / ray.directory().y;
        final float y2 = (aabb.getMax().y - ray.origin().y) / ray.directory().y;

        final float z1 = (aabb.getMin().z - ray.origin().z) / ray.directory().z;
        final float z2 = (aabb.getMax().z - ray.origin().z) / ray.directory().z;

        final float max = Maths.max(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));
        final float min = Maths.min(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));

        return (min < 0F || max > min) ? -1F : max;
    }

    public static boolean isRayIntersectAABox(Ray3f ray, AABoxBody aabb) {
        return getRayIntersectAABox(ray, aabb) != -1F;
    }

    public static float getRayIntersectTriangle(Ray3f ray, float x1, float y1, float z1,
                                                float x2, float y2, float z2, float x3, float y3, float z3) {
        // edge1 = v2 - v1
        final float edge1x = (x2 - x1);
        final float edge1y = (y2 - y1);
        final float edge1z = (z2 - z1);

        // edge2 = v3 - v1
        final float edge2x = (x3 - x1);
        final float edge2y = (y3 - y1);
        final float edge2z = (z3 - z1);

        // h = cross(direction, edge2)
        final float hx = (ray.directory().y * edge2z - ray.directory().z * edge2y);
        final float hy = (ray.directory().z * edge2x - ray.directory().x * edge2z);
        final float hz = (ray.directory().x * edge2y - ray.directory().y * edge2x);

        // a = 1 / dot(h, edge1)
        final float a = (1F / (hx * edge1x + hy * edge1y + hz * edge1z));
        if(a == 0F)
            return -1F;

        // s = origin - v1
        final float sx = (ray.origin().x - x1);
        final float sy = (ray.origin().y - y1);
        final float sz = (ray.origin().z - z1);

        // u = a * dot(h, s)
        final float u = (a * (hx * sx + hy * sy + hz * sz));
        if(u < 0F || u > 1F)
            return -1F;

        // q = cross(s, edge1)
        final float qx = (sy * edge1z - sz * edge1y);
        final float qy = (sz * edge1x - sx * edge1z);
        final float qz = (sx * edge1y - sy * edge1x);

        // a * dot(q, direction)
        final float v = (a * (qx * ray.directory().x + qy * ray.directory().y + qz * ray.directory().z));
        if(v < 0F || u + v > 1F)
            return -1F;

        // t = a * dot(q, edge2) * len(direction)
        final float t = (a * (qx * edge2x + qy * edge2y + qz * edge2z) * ray.length());
        return (t > 0F) ? t : -1F;
    }

    public static boolean isRayIntersectTriangle(Ray3f ray, float x1, float y1, float z1,
                                                 float x2, float y2, float z2, float x3, float y3, float z3) {
        return getRayIntersectTriangle(ray, x1, y1, z1, x2, y2, z2, x3, y3, z3) != -1F;
    }

    public static float getRayIntersectTriangle(Ray3f ray, Vec3f v1, Vec3f v2, Vec3f v3) {
        return getRayIntersectTriangle(ray, v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, v3.x, v3.y, v3.z);
    }

    public static boolean isRayIntersectTriangle(Ray3f ray, Vec3f v1, Vec3f v2, Vec3f v3) {
        return getRayIntersectTriangle(ray, v1, v2, v3) != -1F;
    }


    public static float getRayIntersectQuad(Ray3f ray,
                                            float x1, float y1, float z1, float x2, float y2, float z2,
                                            float x3, float y3, float z3, float x4, float y4, float z4) {
        final float result = getRayIntersectTriangle(ray, x1, y1, z1, x2, y2, z2, x3, y3, z3);
        if(result != -1F)
            return result;
        return getRayIntersectTriangle(ray, x3, y3, z3, x4, y4, z4, x1, y1, z1);
    }

    public static boolean isRayIntersectQuad(Ray3f ray,
                                             float x1, float y1, float z1, float x2, float y2, float z2,
                                             float x3, float y3, float z3, float x4, float y4, float z4) {
        return getRayIntersectQuad(ray, x1, y1, z1, x2, y2, z2, x3, y3, z3, x4, y4, z4) != -1F;
    }

    public static float getRayIntersectQuad(Ray3f ray, Vec3f v1, Vec3f v2, Vec3f v3, Vec3f v4) {
        return getRayIntersectQuad(ray, v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, v3.x, v3.y, v3.z, v4.x, v4.y, v4.z);
    }

    public static boolean isRayIntersectQuad(Ray3f ray, Vec3f v1, Vec3f v2, Vec3f v3, Vec3f v4) {
        return getRayIntersectQuad(ray, v1, v2, v3, v4) != -1F;
    }


    public static float getRayIntersectMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices,
                                            int stride, int positionAttributeOffset) {
        final Vec3f v1 = new Vec3f();
        final Vec3f v2 = new Vec3f();
        final Vec3f v3 = new Vec3f();

        for(int i = 0; i < indices.length; ){
            int offset1 = (indices[i++] * stride + positionAttributeOffset);
            int offset2 = (indices[i++] * stride + positionAttributeOffset);
            int offset3 = (indices[i++] * stride + positionAttributeOffset);

            v1.set(vertices[offset1++], vertices[offset1++], vertices[offset1]).mulMat4(mat);
            v2.set(vertices[offset2++], vertices[offset2++], vertices[offset2]).mulMat4(mat);
            v3.set(vertices[offset3++], vertices[offset3++], vertices[offset3]).mulMat4(mat);

            final float result = getRayIntersectTriangle(ray, v1, v2, v3);
            if(result != -1F)
                return result;
        }
        return -1F;
    }

    public static float getRayIntersectMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices, int stride) {
        return getRayIntersectMesh(ray, mat, vertices, indices, stride, 0);
    }

    public static float getRayIntersectMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices) {
        return getRayIntersectMesh(ray, mat, vertices, indices, 3);
    }

    public static boolean isRayIntersectMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices,
                                             int stride, int positionAttributeOffset) {
        return getRayIntersectMesh(ray, mat, vertices, indices, stride, positionAttributeOffset) != -1F;
    }

    public static boolean isRayIntersectMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices, int stride) {
        return isRayIntersectMesh(ray, mat, vertices, indices, stride, 0);
    }

    public static boolean isRayIntersectMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices) {
        return isRayIntersectMesh(ray, mat, vertices, indices, 3);
    }


    public static float getRayIntersectQuadMesh(Ray3f ray, Matrix4f matrix, float[] vertices, int[] indices,
                                                int stride, int positionAttributeOffset) {
        final Vec3f v1 = new Vec3f();
        final Vec3f v2 = new Vec3f();
        final Vec3f v3 = new Vec3f();
        final Vec3f v4 = new Vec3f();

        for(int i = 0; i < indices.length; ){
            int offset1 = (indices[i++] * stride + positionAttributeOffset);
            int offset2 = (indices[i++] * stride + positionAttributeOffset);
            int offset3 = (indices[i++] * stride + positionAttributeOffset);
            int offset4 = (indices[i++] * stride + positionAttributeOffset);

            v1.set(vertices[offset1++], vertices[offset1++], vertices[offset1]).mulMat4(matrix);
            v2.set(vertices[offset2++], vertices[offset2++], vertices[offset2]).mulMat4(matrix);
            v3.set(vertices[offset3++], vertices[offset3++], vertices[offset3]).mulMat4(matrix);
            v4.set(vertices[offset4++], vertices[offset4++], vertices[offset4]).mulMat4(matrix);

            final float result = getRayIntersectQuad(ray, v1, v2, v3, v4);
            if(result != -1F)
                return result;
        }
        return -1F;
    }

    public static float getRayIntersectQuadMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices, int stride) {
        return getRayIntersectQuadMesh(ray, mat, vertices, indices, stride, 0);
    }

    public static float getRayIntersectQuadMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices) {
        return getRayIntersectQuadMesh(ray, mat, vertices, indices, 3);
    }

    public static boolean isRayIntersectQuadMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices,
                                                 int stride, int positionAttributeOffset) {
        return getRayIntersectQuadMesh(ray, mat, vertices, indices, stride, positionAttributeOffset) != -1F;
    }

    public static boolean isRayIntersectQuadMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices, int stride) {
        return isRayIntersectQuadMesh(ray, mat, vertices, indices, stride, 0);
    }

    public static boolean isRayIntersectQuadMesh(Ray3f ray, Matrix4f mat, float[] vertices, int[] indices) {
        return isRayIntersectQuadMesh(ray, mat, vertices, indices, 3);
    }


    public static float getPolygonArea(float... vertices) {
        float area = 0F;

        for(int i = 0; i < vertices.length; i += 2) {
            final float x1 = vertices[i];
            final float y1 = vertices[i + 1];

            final int j = (i + 2) % vertices.length;
            final float x2 = vertices[j];
            final float y2 = vertices[j + 1];

            area += (y1 + y2) * (x1 - x2);
        }

        return Math.abs(area * 0.5F);
    }

    public static Vec2f getPolygonCenterOfGravity(Vec2f dst, float... vertices) {
        float area = 0F;

        dst.zero();
        for(int i = 0; i < vertices.length; i += 2) {
            final float x1 = vertices[i];
            final float y1 = vertices[i + 1];

            final int j = (i + 2) % vertices.length;
            final float x2 = vertices[j];
            final float y2 = vertices[j + 1];

            final float sumy = (y1 + y2);
            final float mulxy = (x1 * y2 - x2 * y1);
            area += sumy * (x1 - x2);
            dst.add(mulxy * (x1 + x2), mulxy * sumy);
        }

        return dst.div(area * 3F);
    }

    public static Rect getPolygonBounds(Rect rect, float... vertices) {
        rect.setPosition(Float.MAX_VALUE);
        rect.setSize(0F);

        for(int i = 0; i < vertices.length; i += 2) {
            final float x = vertices[i];
            final float y = vertices[i + 1];
            rect.set(
                    Math.min(rect.x, x),
                    Math.min(rect.y, y),
                    Math.max(rect.width, x),
                    Math.max(rect.height, y)
            );
        }

        rect.setSize(rect.width - rect.x, rect.height - rect.y);
        return rect;
    }

}
