package jpize.util.array;

import java.lang.reflect.Array;
import java.util.Objects;

public class ArrayUtils {

    public static boolean contains(byte[] array, byte target) {
        for(byte element: array)
            if(element == target)
                return true;
        return false;
    }

    public static boolean contains(short[] array, short target) {
        for(short element: array)
            if(element == target)
                return true;
        return false;
    }

    public static boolean contains(int[] array, int target) {
        for(int element: array)
            if(element == target)
                return true;
        return false;
    }

    public static boolean contains(long[] array, long target) {
        for(long element: array)
            if(element == target)
                return true;
        return false;
    }

    public static boolean contains(float[] array, float target) {
        for(float element: array)
            if(element == target)
                return true;
        return false;
    }

    public static boolean contains(double[] array, double target) {
        for(double element: array)
            if(element == target)
                return true;
        return false;
    }

    public static boolean contains(char[] array, char target) {
        for(char element: array)
            if(element == target)
                return true;
        return false;
    }

    public static boolean contains(boolean[] array, boolean target) {
        for(boolean element: array)
            if(element == target)
                return true;
        return false;
    }

    public static boolean contains(Object[] array, Object target) {
        for(Object element: array)
            if(Objects.equals(element, target))
                return true;
        return false;
    }


    @SuppressWarnings("unchecked")
    public static <T extends Array> T[] concatenate(T[] a, T[] b) {
        final T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length + b.length);
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    @SafeVarargs
    public static <T extends Array> T[] concatenate(T[] a, T[]... other) {
        for(T[] b: other)
            a = concatenate(a, b);
        return a;
    }

}
