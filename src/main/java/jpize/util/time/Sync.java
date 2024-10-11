package jpize.util.time;

import jpize.util.math.Maths;

public class Sync {

    private long prevTime;
    private int targetDeltaTime;
    private boolean enabled;

    public Sync(double rate) {
        this.setRate(rate);
        this.enable(true);
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void enable(boolean enabled) {
        this.enabled = enabled;
    }


    public double getRate() {
        return (targetDeltaTime == 0 ? 0D : Maths.nanosInSecf / targetDeltaTime);
    }

    public void setRate(double rate) {
        if(rate == 0)
            return;

        targetDeltaTime = (int) (Maths.msInSecf / rate); // time between frames with a given number of ticks per second
        prevTime = System.currentTimeMillis();           // to calculate the time between frames
    }


    public void sync() {
        if(!enabled || targetDeltaTime == 0)
            return;

        final long deltaTime = System.currentTimeMillis() - prevTime; // current time between frames
        if(deltaTime >= 0){
            final long sleepTime = (targetDeltaTime - deltaTime); // time to adjust tick per second
            if(sleepTime > 0)
                TimeUtils.delayMillis(sleepTime);
        }

        prevTime = System.currentTimeMillis();
    }

}