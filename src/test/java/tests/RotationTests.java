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


    private String checkRotation(double y) {
        return (y == 1D ? COUNTER_CLOCKWISE : y == -1D ? CLOCKWISE : UNDEFINED);
    }


    @Test
    public void vector2_angle() {
        Assert.assertEquals(45F, point2.set(1F, 1F).angleDeg(), 0F);
    }


    @Test
    public void vector2_rotate() {
        point2.set(1F, 0D).rotate(90D);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point2.y));
    }

    @Test
    public void vector3_rotateX() {
        point3.set(0F, 0D, 1D).rotateX(90D);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void vector3_rotateY() {
        point3.set(1D, 0D, 0D).rotateY(90D);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.z));
    }

    @Test
    public void vector3_rotateZ() {
        point3.set(-1D, 0D, 0D).rotateZ(90D);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void matrix3_setRotation() {
        matrix3.setRotation(90D);
        point2.set(1D, 0D).mulMat3(matrix3);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point2.y));
    }

    @Test
    public void matrix4_setRotationX() {
        matrix4.setRotationX(90D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void matrix4_setRotationY() {
        matrix4.setRotationY(90D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.z));
    }

    @Test
    public void matrix4_setRotationZ() {
        matrix4.setRotationZ(90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void matrix4_setRotationXYZ() {
        matrix4.setRotationXYZ(90D, 0D, 0D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));

        matrix4.setRotationXYZ(0D, 90D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.z));

        matrix4.setRotationXYZ(0D, 0D, 90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void matrix4_setRotationYXZ() {
        matrix4.setRotationYXZ(0D, 90D, 0D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));

        matrix4.setRotationYXZ(90D, 0D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.z));

        matrix4.setRotationYXZ(0D, 0D, 90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void matrix4_setRotationZYX() {
        matrix4.setRotationZYX(0D, 0D, 90D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));

        matrix4.setRotationZYX(0D, 90D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.z));

        matrix4.setRotationZYX(90D, 0D, 0D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void matrix4_rotateX() {
        matrix4.identity().rotateX(90D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void matrix4_rotateY() {
        matrix4.identity().rotateY(90D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.z));
    }

    @Test
    public void matrix4_rotateZ() {
        matrix4.identity().rotateZ(90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void matrix4_rotateXYZ() {
        matrix4.identity().rotateXYZ(90D, 0D, 0D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));

        matrix4.identity().rotateXYZ(0D, 90D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.z));

        matrix4.identity().rotateXYZ(0D, 0D, 90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void matrix4_rotateYXZ() {
        matrix4.identity().rotateYXZ(0D, 90D, 0D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));

        matrix4.identity().rotateYXZ(90D, 0D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.z));

        matrix4.identity().rotateYXZ(0D, 0D, 90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

    @Test
    public void matrix4_rotateZYX() {
        matrix4.identity().rotateZYX(0D, 0D, 90D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));

        matrix4.identity().rotateZYX(0D, 90D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.z));

        matrix4.identity().rotateZYX(90D, 0D, 0D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        Assert.assertEquals(COUNTER_CLOCKWISE, this.checkRotation(point3.y));
    }

}
