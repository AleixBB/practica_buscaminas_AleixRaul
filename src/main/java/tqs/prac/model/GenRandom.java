package tqs.prac.model;

import java.util.Random;

public class GenRandom {
    
    protected Random random;

    public GenRandom() {
        this.random = new Random();
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }
}