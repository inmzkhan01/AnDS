package primitives;

public class Reverse_Bits {
    /**
     * Brute force.
     */
    public static int reverse1(int x) {
        final int HALF_WORD_SIZE = 16;
        final int MSB_INDEX = 30;
        for (int i = 0; i < HALF_WORD_SIZE; i++) {
            int j = MSB_INDEX - i;
            x = Swap_Bits.swapTwoBits2(x, i, j);
        }
        return x;
    }

    public static int reverse2(int x) {
        int revx = x;
        int bits = (int) (Math.log(x) / Math.log(2)) + 1;

        for (int i = 1; i < bits; i++) {
            x >>= 1;        // shift x one position right,
            revx <<= 1;     // shift copy-of-n one position left
            revx |= (x & 1);// give the LSB of n to copy-of-n
        }
        return revx & ((1 << bits) - 1); // clear all out of range most significant bits.
    }

    public static long reverse3(long x) {

        int[] precomputedReverse = new int[0xFFFF + 1];
        precomputedReverse[0] = 0;
        for (int i = 1; i < precomputedReverse.length; i++) {
            //precomputedReverse[i] = reverse2(i);
        }

        final int WORD_SIZE = 16;
        final int BIT_MASK = 0xFFFF;

        return precomputedReverse[(int) (x & BIT_MASK)] << (3 * WORD_SIZE)
                | precomputedReverse[(int) ((x >> WORD_SIZE) & BIT_MASK)] << (2 * WORD_SIZE)
                | precomputedReverse[(int) ((x >> (2 * WORD_SIZE)) & BIT_MASK)] << WORD_SIZE
                | precomputedReverse[(int) ((x >> (3 * WORD_SIZE)) & BIT_MASK)];
    }

    public static void main(String[] args) {
        int x = 0x7fffffff - 100;
        System.out.println("Original x: " + Integer.toBinaryString(x));
        System.out.println("Reversed x: " + Integer.toBinaryString(reverse1(x)));

        System.out.println("Original x: " + Integer.toBinaryString(x));
        System.out.println("Reversed x: " + Integer.toBinaryString(reverse2(x)));

        long y = 0x7fffffffffffffffL - 100;

        System.out.println("Original y: " + Long.toBinaryString(y));
        System.out.println("Reversed y: " + Long.toBinaryString(reverse3(y)));
    }
}
