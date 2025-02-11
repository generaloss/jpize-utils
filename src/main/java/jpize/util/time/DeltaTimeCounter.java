package jpize.util.time;

import jpize.util.math.Maths;

public class DeltaTimeCounter {

    private long lastTime;
    private float deltaTime;

    public DeltaTimeCounter() {
        this.lastTime = System.nanoTime();
    }

    public void update() {
        final long currentTime = System.nanoTime();
        deltaTime = (currentTime - lastTime) / Maths.NANOS_IN_SECf;
        lastTime = currentTime;
    }

    public void reset() {
        lastTime = System.nanoTime();
        deltaTime = 0F;
    }

    public float get() {
        return deltaTime;
    }

}
