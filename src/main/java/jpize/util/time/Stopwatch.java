package jpize.util.time;

import jpize.util.math.Maths;

public class Stopwatch {

    private long lastTimeNanos, timeNanos;
    private boolean running, paused;

    public Stopwatch start() {
        if(running)
            return this;
        running = true;
        return reset();
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
        return setNanos(Maths.round(millis * Maths.nanosInMsf));
    }

    public Stopwatch setSeconds(double seconds) {
        return setMillis(seconds * Maths.msInSecf);
    }

    public Stopwatch setMinutes(double minutes) {
        return setSeconds(minutes * 60);
    }

    public Stopwatch setHours(double hours) {
        return setMinutes(hours * 60);
    }

    public long getNanos() {
        if(!running)
            return 0;
        if(!paused)
            timeNanos = System.nanoTime();
        return (timeNanos - lastTimeNanos);
    }

    public float getMillis() {
        return getNanos() / Maths.nanosInMsf;
    }

    public float getSeconds() {
        return getMillis() / Maths.msInSecf;
    }

    public float getMinutes() {
        return getSeconds() / 60F;
    }

    public float getHours() {
        return getMinutes() / 60F;
    }

}
