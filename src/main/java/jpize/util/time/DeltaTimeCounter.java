package jpize.util.time;

import jpize.util.math.Maths;

public class DeltaTimeCounter {

    private long lastTime;
    private float deltaTime;

    public DeltaTimeCounter() {
        lastTime = System.nanoTime();
    }

    public void update() {
        final long currentTime = System.nanoTime();
        deltaTime = (currentTime - lastTime) / Maths.nanosInSecf;
        lastTime = currentTime;
    }

    public float get() {
        return deltaTime;
    }

}