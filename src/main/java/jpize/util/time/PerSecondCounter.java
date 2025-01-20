package jpize.util.time;

public class PerSecondCounter {

    private long lastTime;
    private int counter, rate;

    public PerSecondCounter() {
        this.lastTime = System.currentTimeMillis();
    }

    public void update() {
        final long currentTime = System.currentTimeMillis();

        final long difference = currentTime - lastTime;
        if(difference > 1000){
            lastTime = currentTime;

            rate = counter;
            counter = 0;
        }else
            counter++;
    }

    public void reset() {
        lastTime = System.currentTimeMillis();
        counter = 0;
        rate = 0;
    }

    public int get() {
        return rate;
    }

}
