package jpize.util;

import java.io.Serializable;
import java.util.Objects;

public class Insetsf implements Serializable {

    public float top;
    public float left;
    public float bottom;
    public float right;

    public Insetsf(Insetsf insets) {
        this.set(insets);
    }

    public Insetsf(int top, int left, int bottom, int right) {
        this.set(top, left, bottom, right);
    }

    public Insetsf(float horizontal, float vertical) {
        this.set(horizontal, vertical);
    }

    public Insetsf(float all) {
        this.set(all);
    }

    public Insetsf() { }


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


    public Insetsf setTop(float top) {
        this.top = top;
        return this;
    }

    public Insetsf setLeft(float left) {
        this.left = left;
        return this;
    }

    public Insetsf setBottom(float bottom) {
        this.bottom = bottom;
        return this;
    }

    public Insetsf setRight(float right) {
        this.right = right;
        return this;
    }


    public Insetsf set(float top, float left, float bottom, float right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        return this;
    }

    public Insetsf set(float horizontal, float vertical) {
        return this.set(vertical, horizontal, vertical, horizontal);
    }

    public Insetsf set(float all) {
        return this.set(all, all, all, all);
    }

    public Insetsf set(Insetsf insets) {
        return this.set(insets.top, insets.left, insets.bottom, insets.right);
    }

    public Insetsf reset() {
        return this.set(0F, 0F, 0F, 0F);
    }


    public Insetsf copy() {
        return new Insetsf(this);
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;
        final Insetsf insets = (Insetsf) object;
        return !(top != insets.top || left != insets.left || bottom != insets.bottom || right != insets.right);
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