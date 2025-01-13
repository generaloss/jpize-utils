package jpize.util;

import jpize.util.math.vector.Vec4f;
import java.io.Serializable;
import java.util.Objects;

public class Insets implements Serializable {

    public float top;
    public float left;
    public float bottom;
    public float right;

    public Insets(Insets insets) {
        this.set(insets);
    }

    public Insets(int top, int left, int bottom, int right) {
        this.set(top, left, bottom, right);
    }

    public Insets(float horizontal, float vertical) {
        this.set(horizontal, vertical);
    }

    public Insets(float all) {
        this.set(all);
    }

    public Insets() { }


    public float getTop() {
        return top;
    }

    public float getLeft() {
        return left;
    }

    public float getBottom() {
        return bottom;
    }

    public float getRight() {
        return right;
    }


    public void setTop(float top) {
        this.top = top;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public void setRight(float right) {
        this.right = right;
    }


    public void set(float top, float left, float bottom, float right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    public void set(float horizontal, float vertical) {
        this.set(vertical, horizontal, vertical, horizontal);
    }

    public void set(float all) {
        this.set(all, all, all, all);
    }

    public void set(Insets insets) {
        this.set(insets.top, insets.left, insets.bottom, insets.right);
    }

    public void reset() {
        this.set(0F, 0F, 0F, 0F);
    }


    public Insets copy() {
        return new Insets(this);
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;
        final Insets insets = (Insets) object;
        return Vec4f.equals(top, left, bottom, right, insets.top, insets.left, insets.bottom, insets.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, left, bottom, right);
    }

    @Override
    public String toString() {
        return top + ", " + left + ", " + bottom + ", " + right;
    }

}