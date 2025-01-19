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


    public Insets setTop(float top) {
        this.top = top;
        return this;
    }

    public Insets setLeft(float left) {
        this.left = left;
        return this;
    }

    public Insets setBottom(float bottom) {
        this.bottom = bottom;
        return this;
    }

    public Insets setRight(float right) {
        this.right = right;
        return this;
    }


    public Insets set(float top, float left, float bottom, float right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        return this;
    }

    public Insets set(float horizontal, float vertical) {
        return this.set(vertical, horizontal, vertical, horizontal);
    }

    public Insets set(float all) {
        return this.set(all, all, all, all);
    }

    public Insets set(Insets insets) {
        return this.set(insets.top, insets.left, insets.bottom, insets.right);
    }

    public Insets reset() {
        return this.set(0F, 0F, 0F, 0F);
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