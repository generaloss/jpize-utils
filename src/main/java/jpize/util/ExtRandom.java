package jpize.util;

import java.util.Random;

public class ExtRandom extends Random {

    public ExtRandom() { }

    public ExtRandom(long seed) {
        super(seed);
    }


    public byte nextByte() {
        return (byte) super.next(8);
    }

    public short nextShort() {
        return (short) super.next(16);
    }


    public boolean nextBoolean(float chance) {
        return (super.nextFloat() < chance);
    }

    public boolean nextBoolean(double chance) {
        return (super.nextDouble() < chance);
    }


    public int nextSign() {
        return (1 | (this.nextInt() >> 31));
    }


    public void nextShorts(short[] shorts) {
        final int len = shorts.length;
        int i = 0;

        while(i < len) {
            final int rnd = super.nextInt();
            shorts[i++] = (short) rnd;
            shorts[i++] = (short) (rnd >> 16);
        }
    }

    public void nextInts(int[] ints) {
        final int len = ints.length;
        for(int i = 0; i < len; i++)
            ints[i] = super.nextInt();
    }

    public void nextLongs(long[] longs) {
        final int len = longs.length;
        for(int i = 0; i < len; i++)
            longs[i] = super.nextLong();
    }

    public void nextFloats(float[] floats) {
        final int len = floats.length;
        for(int i = 0; i < len; i++)
            floats[i] = super.nextFloat();
    }

    public void nextDoubles(double[] doubles) {
        final int len = doubles.length;
        for(int i = 0; i < len; i++)
            doubles[i] = super.nextDouble();
    }

}
