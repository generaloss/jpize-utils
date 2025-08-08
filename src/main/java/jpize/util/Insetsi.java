package jpize.util;

import java.io.Serializable;
import java.util.Objects;

public class Insetsi implements Serializable {

    public int top;
    public int left;
    public int bottom;
    public int right;

    public Insetsi(Insetsi insets) {
        this.set(insets);
    }

    public Insetsi(int top, int left, int bottom, int right) {
        this.set(top, left, bottom, right);
    }

    public Insetsi(int horizontal, int vertical) {
        this.set(horizontal, vertical);
    }

    public Insetsi(int all) {
        this.set(all);
    }

    public Insetsi() { }


    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public int getBottom() {
        return bottom;
    }

    public int getRight() {
        return right;
    }


    public Insetsi setTop(int top) {
        this.top = top;
        return this;
    }

    public Insetsi setLeft(int left) {
        this.left = left;
        return this;
    }

    public Insetsi setBottom(int bottom) {
        this.bottom = bottom;
        return this;
    }

    public Insetsi setRight(int right) {
        this.right = right;
        return this;
    }


    public Insetsi set(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        return this;
    }

    public Insetsi set(int horizontal, int vertical) {
        return this.set(vertical, horizontal, vertical, horizontal);
    }

    public Insetsi set(int all) {
        return this.set(all, all, all, all);
    }

    public Insetsi set(Insetsi insets) {
        return this.set(insets.top, insets.left, insets.bottom, insets.right);
    }

    public Insetsi reset() {
        return this.set(0, 0, 0, 0);
    }


    public Insetsi copy() {
        return new Insetsi(this);
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;
        final Insetsi insets = (Insetsi) object;
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