package jpize.util;

import java.util.Random;

public class ExtRandom extends Random {

    public ExtRandom() { }

    public ExtRandom(long seed) {
        super(seed);
    }

    public boolean nextBoolean(float chance) {
        return (super.nextFloat() < chance);
    }

    public boolean nextBoolean(double chance) {
        return (super.nextDouble() < chance);
    }

    public int nextSign() {
        return (1 | (super.nextInt() >> 31));
    }

}
