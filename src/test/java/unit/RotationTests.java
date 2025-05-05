package unit;

import jpize.util.math.EulerAngles;
import jpize.util.math.Quaternion;
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
    private final EulerAngles eulerAngles = new EulerAngles();
    private final Quaternion quaternion = new Quaternion();


    private void checkIsCounterClockwise(double axis) {
        Assert.assertEquals(1D, axis, 0.001D);
    }


    @Test
    public void quaternion_setRotation() {
        quaternion.setRotation(0F, 1F, 0F, 90D);
        matrix4.setQuaternion(quaternion);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.z);
    }

    @Test
    public void quaternion_eulerAngles() {
        eulerAngles.set(90D, 90D);
        quaternion.setRotation(eulerAngles);
        matrix4.setQuaternion(quaternion);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void quaternion_getEulerAngles() {
        eulerAngles.set(70D, 80D, 90D);
        quaternion.setRotation(eulerAngles);
        quaternion.getEulerAngles(eulerAngles);
        Assert.assertEquals(70F, eulerAngles.yaw, 0.001F);
        Assert.assertEquals(80F, eulerAngles.pitch, 0.001F);
        Assert.assertEquals(90F, eulerAngles.roll, 0.001F);
    }


    @Test
    public void vector2_angle() {
        Assert.assertEquals(45F, point2.set(1F, 1F).angle(), 0F);
    }


    @Test
    public void vector2_rotate() {
        point2.set(1F, 0D).rotate(90D);
        this.checkIsCounterClockwise(point2.y);
    }

    @Test
    public void vector3_rotateX() {
        point3.set(0F, 0D, 1D).rotateX(90D);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void vector3_rotateY() {
        point3.set(1D, 0D, 0D).rotateY(90D);
        this.checkIsCounterClockwise(point3.z);
    }

    @Test
    public void vector3_rotateZ() {
        point3.set(-1D, 0D, 0D).rotateZ(90D);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void matrix3_setRotation() {
        matrix3.setRotation(90D);
        point2.set(1D, 0D).mulMat3(matrix3);
        this.checkIsCounterClockwise(point2.y);
    }

    @Test
    public void matrix4_setRotationX() {
        matrix4.setRotationX(90D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void matrix4_setRotationY() {
        matrix4.setRotationY(90D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.z);
    }

    @Test
    public void matrix4_setRotationZ() {
        matrix4.setRotationZ(90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void matrix4_setRotationXYZ() {
        matrix4.setRotationXYZ(90D, 0D, 0D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);

        matrix4.setRotationXYZ(0D, 90D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.z);

        matrix4.setRotationXYZ(0D, 0D, 90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void matrix4_setRotationYXZ() {
        matrix4.setRotationYXZ(0D, 90D, 0D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);

        matrix4.setRotationYXZ(90D, 0D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.z);

        matrix4.setRotationYXZ(0D, 0D, 90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void matrix4_setRotationZYX() {
        matrix4.setRotationZYX(0D, 0D, 90D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);

        matrix4.setRotationZYX(0D, 90D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.z);

        matrix4.setRotationZYX(90D, 0D, 0D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void matrix4_rotateX() {
        matrix4.identity().rotateX(90D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void matrix4_rotateY() {
        matrix4.identity().rotateY(90D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.z);
    }

    @Test
    public void matrix4_rotateZ() {
        matrix4.identity().rotateZ(90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void matrix4_rotateXYZ() {
        matrix4.identity().rotateXYZ(90D, 0D, 0D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);

        matrix4.identity().rotateXYZ(0D, 90D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.z);

        matrix4.identity().rotateXYZ(0D, 0D, 90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void matrix4_rotateYXZ() {
        matrix4.identity().rotateYXZ(0D, 90D, 0D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);

        matrix4.identity().rotateYXZ(90D, 0D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.z);

        matrix4.identity().rotateYXZ(0D, 0D, 90D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

    @Test
    public void matrix4_rotateZYX() {
        matrix4.identity().rotateZYX(0D, 0D, 90D);
        point3.set(0D, 0D, 1D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);

        matrix4.identity().rotateZYX(0D, 90D, 0D);
        point3.set(1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.z);

        matrix4.identity().rotateZYX(90D, 0D, 0D);
        point3.set(-1D, 0D, 0D).mulMat4(matrix4);
        this.checkIsCounterClockwise(point3.y);
    }

}
