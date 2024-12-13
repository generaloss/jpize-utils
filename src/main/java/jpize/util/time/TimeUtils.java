package jpize.util.time;

import jpize.util.math.Maths;

import java.util.function.BooleanSupplier;

public class TimeUtils {

    public static void sleep(long millis, int nanos) {
        try{
            Thread.sleep(millis, nanos);
        }catch(InterruptedException ignored){ }
    }

    public static void sleepMillis(long millis) {
        try{
            Thread.sleep(millis);
        }catch(InterruptedException ignored){ }
    }

    public static void sleepNanos(int nanos) {
        try{
            Thread.sleep(0, nanos);
        }catch(InterruptedException ignored){ }
    }

    public static void sleepSeconds(float seconds) {
        final double ms = seconds * 1000;
        final double ns = Maths.frac(ms) * Maths.nanosInMsf;
        sleep((long) ms, (int) ns);
    }


    public static void delayNanos(long nanos) {
        final long current = System.nanoTime();
        while(System.nanoTime() - current < nanos)
            Thread.onSpinWait();
    }

    public static void delayMillis(long millis) {
        final long current = System.currentTimeMillis();
        while(System.currentTimeMillis() - current < millis)
            Thread.onSpinWait();
    }

    public static void delaySeconds(float seconds) {
        delayMillis((long) (seconds * 1000));
    }


    public static void waitFor(BooleanSupplier supplier) {
        while(!supplier.getAsBoolean())
            Thread.onSpinWait();
    }

    public static void waitFor(BooleanSupplier supplier, long timeoutMillis) {
        final long oldMillis = System.currentTimeMillis();
        while(!supplier.getAsBoolean() && System.currentTimeMillis() - oldMillis < timeoutMillis)
            Thread.onSpinWait();
    }

    public static void waitFor(BooleanSupplier supplier, long timeoutMillis, Runnable timeoutRunnable) {
        waitFor(supplier, timeoutMillis);
        if(!supplier.getAsBoolean())
            timeoutRunnable.run();
    }

}
