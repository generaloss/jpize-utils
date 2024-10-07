package jpize.util;

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


    public void set(Insets insets) {
        this.set(insets.top, insets.left, insets.bottom, insets.right);
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
        return Float.compare(top, insets.top) == 0 && Float.compare(left, insets.left) == 0 && Float.compare(bottom, insets.bottom) == 0 && Float.compare(right, insets.right) == 0;
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