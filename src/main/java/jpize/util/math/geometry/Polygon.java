package jpize.util.math.geometry;

import jpize.util.array.ObjectList;
import jpize.util.math.vector.Vec2f;

public class Polygon {

    private final LineString vertices;
    private final ObjectList<LineString> holes;

    public Polygon() {
        this.vertices = new LineString();
        this.holes = new ObjectList<>();
    }

    public LineString vertices() {
        return vertices;
    }

    public ObjectList<LineString> holes() {
        return holes;
    }


    public Rect getBounds(Rect dst) {
        vertices.trim();
        return Rect.boundsOf(dst, vertices.array());
    }

    public Vec2f getCenterOfGravity(Vec2f dst) {
        vertices.trim();
        return getCenterOfGravity(dst, vertices.array());
    }
    
    public float getArea() {
        vertices.trim();
        float area = getArea(vertices.array());
        for(LineString hole: holes){
            hole.trim();
            area -= getArea(hole.array());
        }
        return area;
    }

    public boolean isPointOn(float x, float y) {
        vertices.trim();
        for(LineString hole: holes){
            hole.trim();
            if(isPointIn(x, y, hole.array()))
                return false;
        }
        return isPointOn(x, y, vertices.array());
    }

    public boolean isPointOn(Vec2f point) {
        return this.isPointOn(point.x, point.y);
    }

    public boolean isPointIn(float x, float y) {
        vertices.trim();
        for(LineString hole: holes){
            hole.trim();
            if(isPointOn(x, y, hole.array()))
                return false;
        }
        return isPointIn(x, y, vertices.array());
    }

    public boolean isPointIn(Vec2f point) {
        return this.isPointIn(point.x, point.y);
    }

    public boolean isIntersectPolygon(Polygon polygon) {
        vertices.trim();
        polygon.vertices.trim();
        return isIntersectPolygon(vertices.array(), polygon.vertices.array());
    }



    public static Vec2f getCenterOfGravity(Vec2f dst, Vec2f... vertices) {
        float area = 0F;

        dst.zero();
        for(int i = 0; i < vertices.length; i++) {
            final Vec2f vertex1 = vertices[i];
            final Vec2f vertex2 = vertices[(i + 1) % vertices.length];

            final float sumy = (vertex1.y + vertex2.y);
            final float mulxy = (vertex1.x * vertex2.y - vertex2.x * vertex1.y);
            area += sumy * (vertex1.x - vertex2.x);
            dst.add(mulxy * (vertex1.x + vertex2.x), mulxy * sumy);
        }

        return dst.div(area * 3F);
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


    public static float getArea(Vec2f... vertices) {
        float area = 0F;

        for(int i = 0; i < vertices.length; i++) {
            final Vec2f vertex1 = vertices[i];
            final Vec2f vertex2 = vertices[(i + 1) % vertices.length];
            
            area += (vertex1.y + vertex2.y) * (vertex1.x - vertex2.x);
        }

        return Math.abs(area * 0.5F);
    }

    public static float getArea(float... vertices) {
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


    public static boolean isPointOn(float pointX, float pointY, Vec2f... vertices) {
        boolean inside = false;

        for(int i = 0; i < vertices.length; i++) {
            final Vec2f vertex1 = vertices[i];
            final Vec2f vertex2 = vertices[(i + 1) % vertices.length];

            if(Intersector.isPointOnSegment(pointX, pointY, vertex1, vertex2))
                return true;

            if(((pointY < vertex1.y) != (pointY < vertex2.y))) {
                final float intersectionX = (pointY - vertex1.y) * (vertex2.x - vertex1.x) / (vertex2.y - vertex1.y) + vertex1.x;
                if(pointX <= intersectionX)
                    inside = !inside;
            }
        }
        return inside;
    }

    public static boolean isPointOn(Vec2f point, Vec2f... vertices) {
        return isPointOn(point.x, point.y, vertices);
    }

    public static boolean isPointOn(float pointX, float pointY, float... vertices) {
        boolean inside = false;

        for(int i = 0; i < vertices.length; i += 2) {
            final float x1 = vertices[i];
            final float y1 = vertices[i + 1];

            final int j = (i + 2) % vertices.length;
            final float x2 = vertices[j];
            final float y2 = vertices[j + 1];

            if(Intersector.isPointOnSegment(pointX, pointY, x1, y1, x2, y2))
                return true;

            if((pointY < y1) != (pointY < y2)) {
                final float intersectX = (pointY - y1) * (x2 - x1) / (y2 - y1) + x1;
                if(pointX <= intersectX)
                    inside = !inside;
            }
        }
        return inside;
    }

    public static boolean isPointOn(Vec2f point, float... vertices) {
        return isPointOn(point.x, point.y, vertices);
    }


    public static boolean isPointIn(float pointX, float pointY, Vec2f... vertices) {
        boolean inside = false;

        for(int i = 0; i < vertices.length; i++) {
            final Vec2f vertex1 = vertices[i];
            final Vec2f vertex2 = vertices[(i + 1) % vertices.length];

            if(Intersector.isPointOnSegment(pointX, pointY, vertex1, vertex2))
                return false;

            if(((pointY < vertex1.y) != (pointY < vertex2.y))) {
                final float intersectionX = (pointY - vertex1.y) * (vertex2.x - vertex1.x) / (vertex2.y - vertex1.y) + vertex1.x;
                if(pointX <= intersectionX)
                    inside = !inside;
            }
        }
        return inside;
    }

    public static boolean isPointIn(Vec2f point, Vec2f... vertices) {
        return isPointIn(point.x, point.y, vertices);
    }

    public static boolean isPointIn(float pointX, float pointY, float... vertices) {
        boolean inside = false;

        for(int i = 0; i < vertices.length; i += 2) {
            final float x1 = vertices[i];
            final float y1 = vertices[i + 1];

            final int j = (i + 2) % vertices.length;
            final float x2 = vertices[j];
            final float y2 = vertices[j + 1];

            if(Intersector.isPointOnSegment(pointX, pointY, x1, y1, x2, y2))
                return false;

            if(((pointY < y1) != (pointY < y2))) {
                final float intersectionX = (pointY - y1) * (x2 - x1) / (y2 - y1) + x1;
                if(pointX <= intersectionX)
                    inside = !inside;
            }
        }
        return inside;
    }

    public static boolean isPointIn(Vec2f point, float... vertices) {
        return isPointIn(point.x, point.y, vertices);
    }


    public static boolean isIntersectPolygon(Vec2f[] verticesA, Vec2f... verticesB) {
        for(int i = 0; i < verticesA.length; i++){
            final Vec2f a1 = verticesA[i];
            final Vec2f a2 = verticesA[(i + 1) % verticesA.length];

            // check points 1
            if(isPointOn(a1, verticesB))
                return true;

            // check segments
            for(int j = 0; j < verticesB.length; j++){
                final Vec2f b1 = verticesB[j];
                final Vec2f b2 = verticesB[(j + 1) % verticesA.length];

                if(Intersector.isSegmentIntersectSegment(a1, a2, b1, b2))
                    return true;
            }
        }

        for(final Vec2f b : verticesB){
            // check points 2
            if(isPointOn(b, verticesA))
                return true;
        }

        return false;
    }

    public static boolean isIntersectPolygon(float[] verticesA, float... verticesB) {
        for(int i = 0; i < verticesA.length; i += 2){
            final float ax1 = verticesA[i];
            final float ay1 = verticesA[i + 1];

            final int k1 = (i + 2) % verticesA.length;
            final float ax2 = verticesA[k1];
            final float ay2 = verticesA[k1 + 1];

            // check points 1
            if(isPointOn(ax1, ay1, verticesB))
                return true;

            // check segments
            for(int j = 0; j < verticesB.length; j += 2){
                final float bx1 = verticesB[j];
                final float by1 = verticesB[j + 1];

                final int k2 = (j + 2) % verticesB.length;
                final float bx2 = verticesB[k2];
                final float by2 = verticesB[k2 + 1];

                if(Intersector.isSegmentIntersectSegment(ax1, ay1, ax2, ay2,  bx1, by1, bx2, by2))
                    return true;
            }
        }

        for(int j = 0; j < verticesB.length; j += 2){
            final float bx = verticesB[j];
            final float by = verticesB[j + 1];

            // check points 2
            if(isPointOn(bx, by, verticesA))
                return true;
        }

        return false;
    }

}
