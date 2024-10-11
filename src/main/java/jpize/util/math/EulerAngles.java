package jpize.util.math;

import jpize.util.math.vector.Vec2d;
import jpize.util.math.vector.Vec3d;
import jpize.util.math.vector.Vec3f;

import java.util.Objects;

public class EulerAngles {

    public float yaw, pitch, roll;

    public EulerAngles() { }

    public EulerAngles(float yaw, float pitch) {
        this.set(yaw, pitch);
    }

    public EulerAngles(float yaw, float pitch, float roll) {
        this.set(yaw, pitch, roll);
    }

    public EulerAngles(EulerAngles eulerAngles) {
        this.set(eulerAngles);
    }


    public void constrain() {
        if(yaw >= 360){
            yaw -= 360;
        }else if(yaw <= -360)
            yaw += 360;
    }

    public void clampPitch() {
        pitch = Maths.clamp(pitch, -90, 90);
    }

    public Vec3f getDirection(Vec3f dst) {
        return directionOf(dst, yaw, pitch);
    }

    public Vec3f getDirectionHorizontal(Vec3f dst) {
        return dst.set(Maths.cosDeg(yaw), 0, Maths.sinDeg(yaw));
    }

    public EulerAngles setDirection(double x, double y, double z) {
        yaw = yawOfDirection(x, z) * Maths.toDeg;
        pitch = pitchOfDirection(x, y, z) * Maths.toDeg;
        return this;
    }

    public EulerAngles setDirection(Vec3d dir) {
        return this.setDirection(dir.x, dir.y, dir.z);
    }

    public EulerAngles setDirection(Vec3f dir) {
        return this.setDirection(dir.x, dir.y, dir.z);
    }


    public EulerAngles set(EulerAngles eulerAngles) {
        yaw = eulerAngles.yaw;
        pitch = eulerAngles.pitch;
        roll = eulerAngles.roll;
        return this;
    }

    public EulerAngles set(float yaw, float pitch, float roll) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
        return this;
    }

    public EulerAngles set(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        return this;
    }


    public EulerAngles add(float yaw, float pitch, float roll) {
        this.yaw += yaw;
        this.pitch += pitch;
        this.roll += roll;
        return this;
    }

    public EulerAngles add(float yaw, float pitch) {
        this.yaw += yaw;
        this.pitch += pitch;
        return this;
    }


    public EulerAngles lerp(EulerAngles start, EulerAngles end, float t) {
        yaw = Maths.lerp(start.yaw, end.yaw, t);
        pitch = Maths.lerp(start.pitch, end.pitch, t);
        roll = Maths.lerp(start.roll, end.roll, t);
        return this;
    }


    public EulerAngles copy() {
        return new EulerAngles(this);
    }


    @Override
    public String toString() {
        return yaw + ", " + pitch + ", " + roll;
    }


    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;

        final EulerAngles angles = (EulerAngles) object;
        return (this.yaw == angles.yaw && this.pitch == angles.pitch && this.roll == angles.roll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yaw, pitch, roll);
    }


    public static EulerAngles ofDirection(double x, double y, double z) {
        final float yaw = yawOfDirection(x, z) * Maths.toDeg;
        final float pitch = pitchOfDirection(x, y, z) * Maths.toDeg;
        return new EulerAngles(yaw, pitch);
    }

    public static float yawOfDirection(double x, double z) {
        return Mathc.atan2(-x, z);
    }

    public static float pitchOfDirection(double x, double y, double z) {
        return Mathc.atan2(y, Vec2d.len(x, z));
    }


    public static Vec3f directionOf(Vec3f dst, double yaw, double pitch) {
        final float pitchCos = Maths.cosDeg(pitch);
        return dst.set(
            -Maths.sinDeg(yaw) * pitchCos,
             Maths.sinDeg(pitch),
             Maths.cosDeg(yaw) * pitchCos
        );
    }

    public static Vec3f directionOf(Vec3f dst, double yaw) {
        return dst.set(Maths.cosDeg(yaw), 0, Maths.sinDeg(yaw));
    }

}
