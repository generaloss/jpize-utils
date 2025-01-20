package jpize.util.math.axisaligned;

import jpize.util.math.vector.Vec2f;

import java.util.Collection;

public class AARectCollider {

    public static boolean isIntersects(Vec2f movement, AARectBody body1, AARectBody... otherBodies) {
        body1 = body1.copy();
        body1.position().add(movement);

        for(AARectBody body2: otherBodies)
            if(body1.intersect(body2))
                return true;

        return false;
    }

    public static boolean isIntersects(Vec2f movement, AARectBody body1, Collection<AARectBody> otherBodies) {
        body1 = body1.copy();
        body1.position().add(movement);

        for(AARectBody body2: otherBodies)
            if(body1.intersect(body2))
                return true;

        return false;
    }


    public static Vec2f getClippedMovement(Vec2f movement, AARectBody body1, AARectBody... otherBodies) {
        // If movement == 0, return 0
        if(movement.isZero() || otherBodies == null)
            return movement;

        // Copy body for safe addition to body.position.X for correct calculation movementY
        body1 = body1.copy();

        // Calculate
        final float movementX = minClipX(movement.x, body1, otherBodies);
        body1.position().x += movementX;
        final float movementY = minClipY(movement.y, body1, otherBodies);

        // Return calculated movement
        return new Vec2f(movementX, movementY);
    }


    private static float minClipX(float movementX, AARectBody body1, AARectBody[] otherBodies) {
        // Iterate other bodies
        for(AARectBody body2: otherBodies){
            if(movementX == 0)
                break;

            // Minimize movement
            movementX = clipX(movementX, body1, body2);
        }
        return movementX;
    }

    private static float minClipY(float movementY, AARectBody body1, AARectBody[] otherBodies) {
        for(AARectBody body2: otherBodies){
            if(movementY == 0)
                break;

            movementY = clipY(movementY, body1, body2);
        }
        return movementY;
    }


    public static Vec2f getClippedMovement(Vec2f movement, AARectBody body1, Collection<AARectBody> otherBodies) {
        // If movement == 0, return 0
        if(movement.isZero() || otherBodies == null)
            return movement;

        // Copy body for safe addition to body.position.X for correct calculation movementY
        body1 = body1.copy();

        // Calculate
        final float movementX = minClipX(movement.x, body1, otherBodies);
        body1.position().x += movementX;
        final float movementY = minClipY(movement.y, body1, otherBodies);

        // Return calculated movement
        return new Vec2f(movementX, movementY);
    }


    private static float minClipX(float movementX, AARectBody body1, Collection<AARectBody> otherBodies) {
        // Iterate other bodies
        for(AARectBody body2: otherBodies){
            if(movementX == 0)
                break;

            // Minimize movement
            movementX = clipX(movementX, body1, body2);
        }
        return movementX;
    }

    private static float minClipY(float movementY, AARectBody body1, Collection<AARectBody> otherBodies) {
        for(AARectBody body2: otherBodies){
            if(movementY == 0)
                break;

            movementY = clipY(movementY, body1, body2);
        }
        return movementY;
    }


    private static float clipX(float movementX, AARectBody body1, AARectBody body2) {
        // Ensure that the bodies intersect on the other axis and that collision is possible
        if(body1.getMax().y > body2.getMin().y && body1.getMin().y < body2.getMax().y){

            // When moving positively:
            if(movementX > 0){
                // Find body1 and body2 sides between which the distance to the collision is calculated
                final float body1Side = Math.max(body1.getMin().x, body1.getMax().x);
                final float body2Side = Math.min(body2.getMin().x, body2.getMax().x);
                final float distance = body2Side - body1Side;

                // If the collision distance is less than planned to move them
                if(distance >= 0 && distance < movementX)
                    // Return the distance as a move
                    return distance;

                // When moving negatively:
            }else{
                // Find body1 and body2 sides between which the distance to the collision is calculated
                final float body1Side = Math.min(body1.getMin().x, body1.getMax().x);
                final float body2Side = Math.max(body2.getMin().x, body2.getMax().x);
                final float distance = body2Side - body1Side;

                // If the collision distance is less than planned to move them (-distance < -movementX  =  distance > movementX)
                if(distance <= 0 && movementX < distance)
                    // Return the distance as a move
                    return distance;
            }
        }

        // If the movementX is less than the collision distance - do nothing
        return movementX;
    }

    private static float clipY(float movementY, AARectBody body1, AARectBody body2) {
        if(body1.getMax().x > body2.getMin().x && body1.getMin().x < body2.getMax().x){

            if(movementY > 0){
                final float body1Side = Math.max(body1.getMin().y, body1.getMax().y);
                final float body2Side = Math.min(body2.getMin().y, body2.getMax().y);
                final float dist = body2Side - body1Side;

                if(dist >= 0 && movementY > dist)
                    return dist;

            }else{
                final float body1Side = Math.min(body1.getMin().y, body1.getMax().y);
                final float body2Side = Math.max(body2.getMin().y, body2.getMax().y);
                final float dist = body2Side - body1Side;

                if(dist <= 0 && movementY < dist)
                    return dist;
            }
        }

        return movementY;
    }

}
