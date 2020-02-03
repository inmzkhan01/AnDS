package primitives;

public class Swap_Bits {

    public static int swapTwoBits1(int x, int i, int j) {
        int ithBit = (x >> i) & 1;
        int jthBit = (x >> j) & 1;

        //XOR the two bits
        int xor = (ithBit ^ jthBit);
        // Put the xor bit back to their original positions */
        int bitMask = (xor << i) | (xor << j);

        System.out.println("Mask: " +Integer.toBinaryString(bitMask));
        return x ^ bitMask;
    }

    public static int swapTwoBits2(int x, int i, int j) {
        // Extract the i-th and j-th bits, and see if they differ.
        if (((x >> i) & 1) != ((x >> j) & 1)) {
            // i-th and j-th bits differ. We will swap them by flipping their values.
            // Select the bits to flip with bitMask. Since x^1 = 0 when x = 1 and 1 when x = 0, we can perform the flip XOR.
            int bitMask = (1 << i) | (1 << j);
            //System.out.println("Mask: " +Integer.toBinaryString(bitMask));
            x ^= bitMask;
        }
        return x;
    }

    public static void main(String[] args) {
        int x = 73;
        System.out.println("Number: " + Integer.toBinaryString(x));
        System.out.println("Result 1: " + Integer.toBinaryString(swapTwoBits1(x, 1, 6)));
        System.out.println("Result 2: " + Integer.toBinaryString(swapTwoBits2(x, 1, 6)));
    }
}