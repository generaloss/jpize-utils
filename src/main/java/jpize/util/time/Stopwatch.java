package jpize.util.time;

import jpize.util.math.Maths;

public class Stopwatch {

    private long lastTimeNanos, timeNanos;
    private boolean running, paused;

    public Stopwatch start() {
        if(running)
            return this;
        running = true;
        return this.reset();
    }

    public Stopwatch reset() {
        timeNanos = System.nanoTime();
        lastTimeNanos = timeNanos;
        return this;
    }

    public Stopwatch stop() {
        running = false;
        return this;
    }

    public Stopwatch pause() {
        paused = true;
        return this;
    }

    public Stopwatch resume() {
        paused = false;
        return this;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isRunning() {
        return running;
    }

    public Stopwatch setNanos(long nanos) {
        lastTimeNanos = (timeNanos - nanos);
        return this;
    }

    public Stopwatch setMillis(double millis) {
        return this.setNanos(Maths.round(millis * Maths.NANOS_IN_MSf));
    }

    public Stopwatch setSeconds(double seconds) {
        return this.setMillis(seconds * Maths.MSf);
    }

    public Stopwatch setMinutes(double minutes) {
        return this.setSeconds(minutes * 60D);
    }

    public Stopwatch setHours(double hours) {
        return this.setMinutes(hours * 60D);
    }

    public long getNanos() {
        if(!running)
            return 0L;
        if(!paused)
            timeNanos = System.nanoTime();
        return (timeNanos - lastTimeNanos);
    }

    public float getMillis() {
        return this.getNanos() / Maths.NANOS_IN_MSf;
    }

    public float getSeconds() {
        return this.getMillis() / Maths.MSf;
    }

    public float getMinutes() {
        return this.getSeconds() / 60F;
    }

    public float getHours() {
        return this.getMinutes() / 60F;
    }

}
