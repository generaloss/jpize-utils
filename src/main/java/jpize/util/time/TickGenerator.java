package jpize.util.time;

public class TickGenerator {

    private final Sync sync;
    private boolean interrupt;

    public TickGenerator(float tickRate) {
        this.sync = new Sync(tickRate);
    }


    public Sync getSync() {
        return sync;
    }

    public void setTickRate(float tickRate) {
        sync.setRate(tickRate);
    }


    public void start(Tickable tickable) {
        interrupt = false;
        while(!Thread.interrupted() && !interrupt){
            tickable.tick();
            sync.sync();
        }
    }

    public void startAsync(Tickable tickable) {
        final Thread thread = new Thread(() -> start(tickable));
        thread.setDaemon(true);
        thread.start();
    }

    public void stop() {
        interrupt = true;
    }

}
