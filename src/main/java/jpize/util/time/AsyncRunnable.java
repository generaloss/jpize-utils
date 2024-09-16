package jpize.util.time;

public class AsyncRunnable {

    private final Runnable runnable;
    private boolean interrupt;

    public AsyncRunnable(Runnable runnable){
        this.runnable = runnable;
    }

    public void runDelayed(long delayMillis){
        TimeUtils.sleepMillis(delayMillis);
        runnable.run();
    }

    public void runInterval(long delayMillis, long periodMillis){
        runDelayed(delayMillis);
        while(!Thread.interrupted() && !interrupt)
            runDelayed(periodMillis);
    }

    public void runDelayedAsync(long delayMillis){
        final Thread thread = new Thread(() -> runDelayed(delayMillis), "AsyncRunnable-Thread #" + this.hashCode());
        thread.setDaemon(true);
        thread.start();
    }

    public void runIntervalAsync(long delayMillis, long periodMillis){
        final Thread thread = new Thread(() -> runInterval(delayMillis, periodMillis), "AsyncRunnable-Thread #" + this.hashCode());
        thread.setDaemon(true);
        thread.start();
    }

    public void interrupt(){
        interrupt = true;
    }

}