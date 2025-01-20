package jpize.util.array;

import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class ArrayUtils {

    public static <T extends Array> T[] concatenate(T[] a, T[] b) {
        final T[] c = (T[]) Array.newInstance(a
            .getClass()
            .getComponentType(), a.length + b.length);

        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);

        return c;
    }

    public static <T extends Array> T[] concatenate(T[] a, T[]... other) {
        for(T[] b: other)
            a = concatenate(a, b);
        return a;
    }


    public static boolean contains(int[] arr, int target) {
        for(int e: arr)
            if(e == target)
                return true;
        return false;
    }

}
