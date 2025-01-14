package jpize.util.math;

import jpize.util.array.FloatList;
import jpize.util.math.axisaligned.AABoxBody;
import jpize.util.math.axisaligned.AARectBody;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec3f;

import java.util.*;

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


    public static boolean isPointInPolygon(float pointX, float pointY, float... vertices) {
        boolean inside = false;
        boolean insideX = false;

        for(int i = 0; i < vertices.length; i += 2) {
            final float x1 = vertices[i];
            final float y1 = vertices[i + 1];

            final int j = (i + 2) % vertices.length;
            final float x2 = vertices[j];
            final float y2 = vertices[j + 1];

            if(((pointY < y1) != (pointY <= y2))) {
                insideX = !insideX;
                final float intersectionX = (pointY - y1) * (x2 - x1) / (y2 - y1) + x1;
                if(pointX + (insideX ? 0 : 1) <= intersectionX)
                    inside = !inside;
            }
        }
        return inside;
    }

    public static boolean isPointOnPolygon(float pointX, float pointY, float... vertices) {
        boolean inside = false;
        boolean insideX = false;

        for(int i = 0; i < vertices.length; i += 2) {
            final float x1 = vertices[i];
            final float y1 = vertices[i + 1];

            final int j = (i + 2) % vertices.length;
            final float x2 = vertices[j];
            final float y2 = vertices[j + 1];

            if(((pointY <= y1) != (pointY < y2))) {
                insideX = !insideX;
                final float intersectionX = (pointY - y1) * (x2 - x1) / (y2 - y1) + x1;
                if(pointX + (insideX ? 1 : 0) <= intersectionX)
                    inside = !inside;
            }
        }
        return inside;
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


    public static int getPointsOrientation(float ax, float ay, float bx, float by, float cx, float cy) {
        return Mathc.signum((by - ay) * (cx - bx) - (bx - ax) * (cy - by));
    }

    public static boolean getLineIntersectLine(Vec2f dst, float a1, float b1, float c1, float a2, float b2, float c2) {
        final float determinant = (a1 * b2 - a2 * b1);
        if(determinant == 0F)
            return false;

        if(dst != null)
            dst.set((b2 * c1 - b1 * c2), (a1 * c2 - a2 * c1)).div(determinant);

        return true;
    }

    public static boolean getSegmentIntersectSegment(Vec2f dst,
                                               float beginX1, float beginY1, float endX1, float endY1,
                                               float beginX2, float beginY2, float endX2, float endY2) {

        final int o1 = getPointsOrientation(beginX1, beginY1, endX1, endY1,  beginX2, beginY2);
        final int o2 = getPointsOrientation(beginX1, beginY1, endX1, endY1,  endX2, endY2);
        final int o3 = getPointsOrientation(beginX2, beginY2, endX2, endY2,  beginX1, beginY1);
        final int o4 = getPointsOrientation(beginX2, beginY2, endX2, endY2,  endX1, endY1);

        // general case
        if(o1 != o2 && o3 != o4) {
            final float a1 = (endY1 - beginY1);
            final float b1 = (beginX1 - endX1);
            final float c1 = (a1 * beginX1 + b1 * beginY1);

            final float a2 = (endY2 - beginY2);
            final float b2 = (beginX2 - endX2);
            final float c2 = (a2 * beginX2 + b2 * beginY2);

            if(getLineIntersectLine(dst,  a1, b1, c1,  a2, b2, c2))
                return true;
        }

        // special cases
        return (
            o1 == 0 && isOnSegment(beginX1, beginY1, beginX2, beginY2, endX1, endY1) ||
            o2 == 0 && isOnSegment(beginX1, beginY1, endX2,   endY2,   endX1, endY1) ||
            o3 == 0 && isOnSegment(beginX2, beginY2, beginX1, beginY1, endX2, endY2) ||
            o4 == 0 && isOnSegment(beginX2, beginY2, endX1,   endY1,   endX2, endY2)
        );
    }

    public static boolean isLineIntersectLine(float a1, float b1, float c1, float a2, float b2, float c2) {
        final float determinant = (a1 * b2 - a2 * b1);
        return (determinant != 0F);
    }

    public static boolean isSegmentIntersectSegment(float beginX1, float beginY1, float endX1, float endY1,
                                                    float beginX2, float beginY2, float endX2, float endY2) {

        final int o1 = getPointsOrientation(beginX1, beginY1, endX1, endY1,  beginX2, beginY2);
        final int o2 = getPointsOrientation(beginX1, beginY1, endX1, endY1,  endX2, endY2);
        final int o3 = getPointsOrientation(beginX2, beginY2, endX2, endY2,  beginX1, beginY1);
        final int o4 = getPointsOrientation(beginX2, beginY2, endX2, endY2,  endX1, endY1);

        // general case
        if(o1 != o2 && o3 != o4) {
            final float a1 = (endY1 - beginY1);
            final float b1 = (beginX1 - endX1);
            final float c1 = (a1 * beginX1 + b1 * beginY1);

            final float a2 = (endY2 - beginY2);
            final float b2 = (beginX2 - endX2);
            final float c2 = (a2 * beginX2 + b2 * beginY2);

            if(isLineIntersectLine(a1, b1, c1,  a2, b2, c2))
                return true;
        }

        // special cases
        return (
                o1 == 0 && isOnSegment(beginX1, beginY1, beginX2, beginY2, endX1, endY1) ||
                o2 == 0 && isOnSegment(beginX1, beginY1, endX2,   endY2,   endX1, endY1) ||
                o3 == 0 && isOnSegment(beginX2, beginY2, beginX1, beginY1, endX2, endY2) ||
                o4 == 0 && isOnSegment(beginX2, beginY2, endX1,   endY1,   endX2, endY2)
        );
    }

    private static boolean isOnSegment(float ax, float ay, float bx, float by, float cx, float cy) {
        return (
            bx <= Math.max(ax, cx) &&
            bx >= Math.min(ax, cx) &&
            by <= Math.max(ay, cy) &&
            by >= Math.min(ay, cy)
        );
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

    public static float[] getPolygonsIntersection(float[] vertices1, float[] vertices2) {
        final List<Vec2f> verticesList = new ArrayList<>();
        final Vec2f dst_point = new Vec2f();

        for(int i = 0; i < vertices1.length; i += 2){
            final float p1x1 = vertices1[i];
            final float p1y1 = vertices1[i + 1];

            final int k1 = (i + 2) % vertices1.length;
            final float p1x2 = vertices1[k1];
            final float p1y2 = vertices1[k1 + 1];

            // check points 1
            if(isPointOnPolygon(p1x1, p1y1, vertices2))
                verticesList.add(new Vec2f(p1x1, p1y1));

            // check segments
            for(int j = 0; j < vertices2.length; j += 2){
                final float p2x1 = vertices2[j];
                final float p2y1 = vertices2[j + 1];

                final int k2 = (j + 2) % vertices2.length;
                final float p2x2 = vertices2[k2];
                final float p2y2 = vertices2[k2 + 1];

                if(getSegmentIntersectSegment(dst_point, p1x1, p1y1, p1x2, p1y2,  p2x1, p2y1, p2x2, p2y2)) {
                    verticesList.add(new Vec2f(dst_point.x, dst_point.y));
                }
            }
        }

        for(int j = 0; j < vertices2.length; j += 2){
            final float p2x1 = vertices2[j];
            final float p2y1 = vertices2[j + 1];

            // check points 2
            if(isPointOnPolygon(p2x1, p2y1, vertices1))
                verticesList.add(new Vec2f(p2x1, p2y1));
        }

        if(verticesList.isEmpty())
            return new float[0];

        for(int i = 0; i < verticesList.size(); i++){
            final Vec2f value = verticesList.get(i);
            if(value.x == -0) value.x = 0;
            if(value.y == -0) value.y = 0;
            verticesList.set(i, value);
        }

        final Set<Vec2f> verticesSet = new HashSet<>(verticesList);
        verticesList.clear();
        verticesList.addAll(verticesSet);

        verticesList.sort(Comparator.comparingDouble((p) -> Math.atan2(p.y, p.x)));

        final FloatList vertices = new FloatList();
        for(Vec2f point: verticesList)
            vertices.add(point.x, point.y);
        return vertices.arrayTrimmed();
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

}
