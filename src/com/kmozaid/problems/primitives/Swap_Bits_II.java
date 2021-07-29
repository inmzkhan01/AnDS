package com.kmozaid.problems.primitives;

/**
 * https://www.geeksforgeeks.org/swap-bits-in-a-given-number/
 * <p>
 * Given a number x and two positions (from the right side) in the binary representation of x,
 * write a function that swaps n bits at given two positions and returns the result.
 * It is also given that the two sets of bits do not overlap
 */
public class Swap_Bits_II {

    public static int swapBits(int x, int i, int j, int n) {

        /*
         * The expression ((1 << n) - 1) gives a number that contains last n bits set and other bits as 0.
         * We do & with this expression so that bits other than the last n bits become 0.
         * Move all bits of first set to rightmost side, set1
         * Move all bits of second set to rightmost side, set2
         */
        int set1 = (x >> i) & ((1 << n) - 1);
        int set2 = (x >> j) & ((1 << n) - 1);

        int xor = (set1 ^ set2);
        // Put the xor bits back to their original positions, creating bitmask.
        int bitMask = (xor << i) | (xor << j);

        return x ^ bitMask;
    }

    public static void main(String[] args) {
        System.out.println("Before Swap: " + Integer.toBinaryString(28));
        System.out.println("After Swap : " + Integer.toBinaryString(swapBits(28, 0, 3, 2)));
        System.out.println();

        System.out.println("Before Swap: " + Integer.toBinaryString(47));
        System.out.println("After Swap : " + Integer.toBinaryString(swapBits(47, 1, 5, 3)));
    }
}
