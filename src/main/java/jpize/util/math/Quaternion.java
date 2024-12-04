package jpize.util.math;

import jpize.util.math.vector.Vec3f;

public class Quaternion {

    public float w, x, y, z;

    public Quaternion() {
        this.w = 1F;
    }

    public Quaternion(float w, float x, float y, float z) {
        this.set(w, x, y, z);
    }

    public Quaternion(double w, double x, double y, double z) {
        this.set(w, x, y, z);
    }

    public Quaternion(Quaternion quaternion) {
        this.set(quaternion);
    }


    public float getW() {
        return w;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }


    public Quaternion set(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Quaternion set(double w, double x, double y, double z) {
        return this.set((float) w, (float) x, (float) y, (float) z);
    }

    public Quaternion set(Quaternion quaternion) {
        return this.set(quaternion.w, quaternion.x, quaternion.y, quaternion.z);
    }


    public Quaternion add(float w, float x, float y, float z) {
        this.w += w;
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Quaternion add(double w, double x, double y, double z) {
        return this.add((float) w, (float) x, (float) y, (float) z);
    }

    public Quaternion add(Quaternion quaternion) {
        return this.add(quaternion.w, quaternion.x, quaternion.y, quaternion.z);
    }


    public Quaternion mul(float w, float x, float y, float z) {
        this.w = this.w * w - this.x * x - this.y * y - this.z * z;
        this.x = this.w * x + this.x * w + this.y * z - this.z * y;
        this.y = this.w * y + this.y * w + this.z * x - this.x * z;
        this.z = this.w * z + this.z * w + this.x * y - this.y * x;
        return this;
    }

    public Quaternion mul(double w, double x, double y, double z) {
        return this.mul((float) w, (float) x, (float) y, (float) z);
    }

    public Quaternion mul(Quaternion quaternion) {
        return this.mul(quaternion.w, quaternion.x, quaternion.y, quaternion.z);
    }


    public Quaternion reset() {
        return this.set(1F, 0F, 0F, 0F);
    }


    public float len2() {
        return w * w + x * x + y * y + z * z;
    }

    public float len() {
        return Mathc.sqrt(this.len2());
    }


    public Quaternion nor() {
        float len = this.len2();
        if(len == 0F || len == 1F)
            return this;

        len = 1F / Mathc.sqrt(len);
        w *= len;
        x *= len;
        y *= len;
        z *= len;
        return this;
    }

    public Quaternion conjugate() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    public Quaternion reciprical() {
        return this.conjugate().nor();
    }


    public static Quaternion lerp(Quaternion dst, float startW, float startX, float startY, float startZ, float endW, float endX, float endY, float endZ, float t) {
        return dst.set(
            Maths.lerp(startX, endX, t),
            Maths.lerp(startY, endY, t),
            Maths.lerp(startZ, endZ, t),
            Maths.lerp(startW, endW, t)
        );
    }

    public static Quaternion lerp(Quaternion dst, Quaternion start, Quaternion end, float t) {
        return lerp(dst, start.x, start.y, start.z, start.w, end.x, end.y, end.z, end.w, t);
    }

    public Quaternion lerp(float startX, float startY, float startZ, float startW, float endX, float endY, float endZ, float endW, float t) {
        return lerp(this, startX, startY, startZ, startW, endX, endY, endZ, endW, t);
    }

    public Quaternion lerp(Quaternion start, Quaternion end, float t) {
        return lerp(this, start, end, t);
    }


    public Quaternion setRotationRad(float axisX, float axisY, float axisZ, double angle) {
        final double omega = 0.5D * angle;
        final float sin = Mathc.sin(omega);
        w = Mathc.cos(omega);
        x = axisX * sin;
        y = axisY * sin;
        z = axisZ * sin;
        return this;
    }

    public Quaternion setRotation(float axisX, float axisY, float axisZ, double angle) {
        return this.setRotationRad(axisX, axisY, axisZ, angle * Maths.toRad);
    }

    public Quaternion setRotationRad(Vec3f axis, double angle) {
        return this.setRotationRad(axis.x, axis.y, axis.z, angle);
    }

    public Quaternion setRotation(Vec3f axis, double angle) {
        return this.setRotationRad(axis, angle * Maths.toRad);
    }


    public Quaternion setRotationRad(double yaw, double pitch, double roll) {
        final double hy = (yaw   * 0.5D);
        final double hp = (pitch * 0.5D);
        final double hr = (roll  * 0.5D);

        final float chy = Mathc.cos(hy);
        final float shy = Mathc.sin(hy);
        final float chp = Mathc.cos(hp);
        final float shp = Mathc.sin(hp);
        final float chr = Mathc.cos(hr);
        final float shr = Mathc.sin(hr);

        final float chy_shp = (chy * shp);
        final float shy_chp = (shy * chp);
        final float chy_chp = (chy * chp);
        final float shy_shp = (shy * shp);

        x = (chy_shp * chr) + (shy_chp * shr);
        y = (shy_chp * chr) - (chy_shp * shr);
        z = (chy_chp * shr) - (shy_shp * chr);
        w = (chy_chp * chr) + (shy_shp * shr);
        return this;
    }

    public Quaternion setRotation(double yaw, double pitch, double roll){
        return this.setRotationRad(yaw * Maths.toRad, pitch * Maths.toRad, roll * Maths.toRad);
    }

    public Quaternion setRotation(EulerAngles angles) {
        return this.setRotation(angles.yaw, angles.pitch, angles.roll);
    }


    public Vec3f getDirection(Vec3f dst) {
        return dst.set(
            (x * z + y * w) * 2F,
            (y * z - x * w) * 2F,
            1F - (x * x + y * y) * 2F
        );
    }


    public int getGimbalPole() {
        final float t = (x * y + z * w);
        return (t > 0.5F) ? 1 : (t < -0.5F ? -1 : 0);
    }

    private float getYawRad(int gimbalPole) {
        if(gimbalPole == 0)
            return Mathc.atan2((x * z + y * w) * 2F, 1F - (x * x + y * y) * 2F);
        return 0F;
    }

    public float getYawRad() {
        return this.getYawRad(this.getGimbalPole());
    }

    private float getPitchRad(int gimbalPole) {
        if(gimbalPole == 0)
            return Mathc.asin(Maths.clamp(((x * w - y * z) * 2F),  -1F, 1F));
        return gimbalPole * Maths.halfPI;
    }

    public float getPitchRad() {
        return this.getPitchRad(this.getGimbalPole());
    }

    private float getRollRad(int gimbalPole) {
        if(gimbalPole == 0)
            return Mathc.atan2((x * y + z * w) * 2F, 1F - (x * x + z * z) * 2F);
        return Mathc.atan2(y, w) * gimbalPole * 2F;
    }

    public float getRollRad() {
        return this.getRollRad(this.getGimbalPole());
    }


    private float getYaw(int gimbalPole) {
        return this.getYawRad(gimbalPole) * Maths.toDeg;
    }

    public float getYaw() {
        return this.getYaw(this.getGimbalPole());
    }

    private float getPitch(int gimbalPole) {
        return this.getPitchRad(gimbalPole) * Maths.toDeg;
    }

    public float getPitch() {
        return this.getPitch(this.getGimbalPole());
    }

    private float getRoll(int gimbalPole) {
        return this.getRollRad(gimbalPole) * Maths.toDeg;
    }

    public float getRoll() {
        return this.getRoll(this.getGimbalPole());
    }


    public EulerAngles getEulerAngles(EulerAngles dst) {
        final int pole = this.getGimbalPole();
        return dst.set(this.getYaw(pole), this.getPitch(pole), this.getRoll(pole));
    }


    public Quaternion copy() {
        return new Quaternion(this);
    }

    @Override
    public String toString() {
        return "[w: " + w + ", x: " + x + ", y: " + y + ", z: " + z + "]";
    }


    public static Quaternion fromRotation(float x, float y, float z) {
        final Quaternion quaternionernion = new Quaternion();
        quaternionernion.mul(new Quaternion(Mathc.sin(x * 0.5F), 0F, 0F, Mathc.cos(x * 0.5F)));
        quaternionernion.mul(new Quaternion(0F, Mathc.sin(y * 0.5F), 0F, Mathc.cos(y * 0.5F)));
        quaternionernion.mul(new Quaternion(0F, 0F, Mathc.sin(z * 0.5F), Mathc.cos(z * 0.5F)));
        return quaternionernion;
    }

}
