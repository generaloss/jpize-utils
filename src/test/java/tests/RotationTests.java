package tests;

import jpize.util.math.matrix.Matrix3f;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec3f;
import org.junit.Assert;
import org.junit.Test;

public class RotationTests {

    private final static String COUNTER_CLOCKWISE = "counter-clockwise";
    private final static String CLOCKWISE = "clockwise";
    private final static String UNDEFINED = "undefined";

    private final Matrix3f matrix3 = new Matrix3f();
    private final Matrix4f matrix4 = new Matrix4f();
    private final Vec2f point2 = new Vec2f();
    private final Vec3f point3 = new Vec3f();


    private String check(double y) {
        return (y == 1D ? COUNTER_CLOCKWISE : y == -1D ? CLOCKWISE : UNDEFINED);
    }

    @Test
    public void matrix3_setRotation() {
        // setRotation
        matrix3.setRotation(90D);
        point2.set(1D, 0D).mulMat3(matrix3);
        Assert.assertEquals(CLOCKWISE, check(point2.y));
    }

    @Test
    public void matrix4_setRotationX() {
        matrix4.setRotationX(90D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        Assert.assertEquals(CLOCKWISE, check(point3.y));
    }

    @Test
    public void matrix4_setRotationY() {
        matrix4.setRotationY(90D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(CLOCKWISE, check(point3.z));
    }

    @Test
    public void matrix4_setRotationZ() {
        matrix4.setRotationZ(90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(CLOCKWISE, check(point3.y));
    }

    @Test
    public void matrix4_setRotationXYZ() {
        matrix4.setRotationXYZ(90D, 0D, 0D);
        point3.set(0D, 1D, 0D).mulMat4(matrix4);
        Assert.assertEquals(CLOCKWISE, check(point3.y));
    }

}
