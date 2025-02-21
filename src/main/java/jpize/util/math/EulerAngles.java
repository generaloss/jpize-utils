package jpize.util.math;

import jpize.util.math.vector.Vec2d;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec3d;
import jpize.util.math.vector.Vec3f;

import java.util.Objects;

public class EulerAngles {

    public float yaw;
    public float pitch;
    public float roll;

    public EulerAngles() { }

    public EulerAngles(float yaw, float pitch) {
        this.set(yaw, pitch);
    }

    public EulerAngles(double yaw, double pitch) {
        this.set(yaw, pitch);
    }

    public EulerAngles(float yaw, float pitch, float roll) {
        this.set(yaw, pitch, roll);
    }

    public EulerAngles(double yaw, double pitch, double roll) {
        this.set(yaw, pitch, roll);
    }

    public EulerAngles(EulerAngles eulerAngles) {
        this.set(eulerAngles);
    }


    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }


    public void constrain() {
        if(yaw >= 360F){
            yaw -= 360F;
        }else if(yaw <= -360F)
            yaw += 360F;
    }

    public void clampPitch() {
        pitch = Maths.clamp(pitch, -90F, 90F);
    }

    public Vec3f getDirection(Vec3f dst) {
        return EulerAngles.directionOf(dst, yaw, pitch);
    }

    public Vec3f getDirection() {
        return EulerAngles.directionOf(yaw, pitch);
    }

    public Vec3f getDirectionHorizontal(Vec3f dst) {
        return EulerAngles.directionOf(dst, yaw);
    }

    public Vec2f getDirectionHorizontal(Vec2f dst) {
        return EulerAngles.directionOf(dst, yaw);
    }

    public Vec3f getDirectionHorizontal() {
        return EulerAngles.directionOf(yaw);
    }

    public EulerAngles setDirection(double x, double y, double z) {
        yaw = EulerAngles.yawOfDirection(x, z) * Maths.TO_DEG;
        pitch = EulerAngles.pitchOfDirection(x, y, z) * Maths.TO_DEG;
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

    public EulerAngles set(double yaw, double pitch, double roll) {
        return this.set((float) yaw, (float) pitch, (float) roll);
    }

    public EulerAngles set(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        return this;
    }

    public EulerAngles set(double yaw, double pitch) {
        return this.set((float) yaw, (float) pitch);
    }


    public EulerAngles add(float yaw, float pitch, float roll) {
        this.yaw += yaw;
        this.pitch += pitch;
        this.roll += roll;
        return this;
    }

    public EulerAngles add(double yaw, double pitch, double roll) {
        return this.add((float) yaw, (float) pitch, (float) roll);
    }

    public EulerAngles add(float yaw, float pitch) {
        this.yaw += yaw;
        this.pitch += pitch;
        return this;
    }

    public EulerAngles add(double yaw, double pitch) {
        return this.add((float) yaw, (float) pitch);
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
        final float yaw = EulerAngles.yawOfDirection(x, z) * Maths.TO_DEG;
        final float pitch = EulerAngles.pitchOfDirection(x, y, z) * Maths.TO_DEG;
        return new EulerAngles(yaw, pitch);
    }

    public static float yawOfDirection(double x, double z) {
        return Mathc.atan2(z, x);
    }

    public static float pitchOfDirection(double x, double y, double z) {
        return Mathc.atan2(y, Vec2d.len(x, z));
    }


    public static Vec3f directionOf(Vec3f dst, double yaw, double pitch) {
        final float pitchCos = Maths.cosDeg(pitch);
        return dst.set(
            Maths.cosDeg(yaw) * pitchCos,
            Maths.sinDeg(pitch),
            Maths.sinDeg(yaw) * pitchCos
        );
    }

    public static Vec3f directionOf(double yaw, double pitch) {
        return EulerAngles.directionOf(new Vec3f(), yaw, pitch);
    }

    public static Vec3f directionOf(Vec3f dst, double yaw) {
        return dst.set(Maths.cosDeg(yaw), 0F, Maths.sinDeg(yaw));
    }

    public static Vec2f directionOf(Vec2f dst, double yaw) {
        return dst.set(Maths.cosDeg(yaw), Maths.sinDeg(yaw));
    }

    public static Vec3f directionOf(double yaw) {
        return EulerAngles.directionOf(new Vec3f(), yaw);
    }

}
