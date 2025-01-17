package jpize.util.math;

import java.util.Random;

public class ExtRandom extends Random {

    public ExtRandom() { }

    public ExtRandom(long seed) {
        super(seed);
    }

    public ExtRandom(int seed) {
        super(seed);
    }


    public int nextInt(int start, int end) {
        return start + super.nextInt(end - start + 1);
    }

    public int nextInt(int max) {
        return super.nextInt() * max;
    }

    public float nextFloat(float start, float end) {
        return start + super.nextFloat() * (end - start);
    }

    public float nextFloat(float max) {
        return super.nextFloat() * max;
    }

    public long nextLong(long start, long end) {
        final long rand = super.nextLong();
        if(end < start){
            long t = end;
            end = start;
            start = t;
        }
        long bound = (end - start + 1L);
        final long randLow = rand & 0xFFFFFFFFL;
        final long boundLow = bound & 0xFFFFFFFFL;
        final long randHigh = (rand >>> 32);
        final long boundHigh = (bound >>> 32);
        return start + (randHigh * boundLow >>> 32) + (randLow * boundHigh >>> 32) + randHigh * boundHigh;
    }

    public long nextLong(long max) {
        return super.nextLong() * max;
    }

    public boolean nextBoolean(float chance) {
        return super.nextFloat() < chance;
    }

    public boolean nextBoolean(double chance) {
        return super.nextDouble() < chance;
    }

    public int nextSign() {
        return 1 | (super.nextInt() >> 31);
    }

}
