package jpize.util.time;

import jpize.util.math.Maths;

public class Sync {

    private long prevTime;
    private int frameTime;
    private boolean enabled;

    public Sync(double tps) {
        setTps(tps);
        enable(true);
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void enable(boolean enabled) {
        this.enabled = enabled;
    }


    public double getTps() {
        return (frameTime != 0) ? (Maths.nanosInSecf / frameTime) : 0;
    }

    public void setTps(double tps) {
        if(tps == 0)
            return;

        frameTime = (int) (Maths.msInSecf / tps); // Time between frames with a given number of ticks per second
        prevTime = System.currentTimeMillis();   // To calculate the time between frames
    }


    public void sync() {
        if(!enabled || frameTime == 0)
            return;

        final long deltaTime = System.currentTimeMillis() - prevTime; // Current time between frames
        if(deltaTime >= 0){

            final long sleepTime = frameTime - deltaTime; // Time to adjust tick per second
            if(sleepTime > 0)
                TimeUtils.delayMillis(sleepTime);
        }

        prevTime = System.currentTimeMillis();
    }

}