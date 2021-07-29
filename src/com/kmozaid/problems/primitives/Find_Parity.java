package com.kmozaid.problems.primitives;

public class Find_Parity {

    /**
     * The brute-force algorithm iteratively tests the value of each bit while tracking the number of 1ss seen so far.
     * Since we only care if the number of 1s is even or odd, we can store the number modulo 2.
     */
    public static short parity1(long x) {
        long result = 0;
        while (x != 0) {
            result ^= (x & 1);
            x >>= 1;
        }
        return (short) result;
    }

    /**
     * By counting set bits.
     */
    public static short parity2(long x) {
        short result = 0;
        while (x != 0) {
            result ^= 1;
            x &= (x - 1); // Drops the lowest set bit of x.
        }
        return result;
    }

    /**
     * Using lookup table.
     */
    public static short parity3(long x) {

        int[] precomputedParity = new int[0xFFFF + 1];
        precomputedParity[0] = 0;
        for (int i = 1; i < precomputedParity.length; i++) {
            precomputedParity[i] = precomputedParity[i >> 1] ^ (i & 1);
        }

        final int WORD_SIZE = 16;
        final int BIT_MASK = 0xFFFF;

        return (short) (precomputedParity[(int) ((x >> (3 * WORD_SIZE)) & BIT_MASK)]
                ^ precomputedParity[(int) ((x >> (2 * WORD_SIZE)) & BIT_MASK)]
                ^ precomputedParity[(int) ((x >> WORD_SIZE) & BIT_MASK)]
                ^ precomputedParity[(int) (x & BIT_MASK)]);
    }

    public static short parity(long x) {
        x ^= x >> 32;
        x ^= x >> 16;
        x ^= x >> 8;
        x ^= x >> 4;
        x ^= x >> 2;
        x ^= x >> 1;
        return (short) (x & 1);
    }


    public static void main(String[] args) {
        System.out.println(parity1(15));
        System.out.println(parity2(15));
        System.out.println(parity3(15));
        System.out.println(parity(15));
    }
}

