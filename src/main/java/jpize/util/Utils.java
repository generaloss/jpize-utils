package jpize.util;


import java.io.Closeable;
import java.io.IOException;
import java.util.function.*;

public class Utils {

    public static <T> T make(T object, Consumer<? super T> consumer) {
        consumer.accept(object);
        return object;
    }

    public static <T> T[] makeArr(T[] array, IntFunction<T> function) {
        for(int i = 0; i < array.length; i++)
            array[i] = function.apply(i);
        return array;
    }


    public static String osGeneralizePath(String path) {
        return path.replace("\\", "/");
    }


    public static StackTraceElement[] getStackTrace(){
        return Thread.currentThread().getStackTrace();
    }

    public static StackTraceElement getStackTrace(int element){
        return getStackTrace()[element];
    }


    public static void close(Closeable closeable) {
        try{
            if(closeable != null)
                closeable.close();
        }catch(IOException ignored){ }
    }

    public static void close(AutoCloseable closeable) {
        try{
            if(closeable != null)
                closeable.close();
        }catch(Exception ignored){ }
    }


    public static void wait(Object object) {
        try{
            object.wait();
        }catch(InterruptedException ignored) { }
    }

    public static void wait(Object object, long millis) {
        try{
            object.wait(millis);
        }catch(InterruptedException ignored) { }
    }

    public static void wait(Object object, long timeoutMillis, int nanos) {
        try{
            object.wait(timeoutMillis, nanos);
        }catch(InterruptedException ignored) { }
    }

}
