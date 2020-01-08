package primitives;

public class Count_Set_Bit_in_a_Integer {

    /**
     * Subtracting 1 from a decimal number flips all the bits after the rightmost set bit(which is 1) including the rightmost set bit.
     * for example :
     * 10 in binary is 00001010
     * 9 in binary is 00001001
     * 8 in binary is 00001000
     * 7 in binary is 00000111
     * So if we subtract a number by 1 and do bitwise & with itself (n & (n-1)), we unset the rightmost set bit.
     * If we do n & (n-1) in a loop and count the no of times loop executes we get the set bit count.
     */
    static class Brian_Kernighan_Algorithm {
        static int countSetBit(int n) {
            int count = 0;
            while (n > 0) {
                count++;
                n &= (n - 1);
            }
            return count;
        }
    }

    static class Brian_Kernighan_Algorithm_Recursive {
        static int countSetBit(int n) {
            if (n == 0) return 0;

            return 1 + countSetBit(n & (n - 1));
        }
    }

    static class EPI_Algorithm {
        static int countSetBit(int n) {
            int count = 0;
            while (n > 0) {
                count++;

                //Right most set bit and all other bit of y are 0s.
                int y = (n & ~(n - 1));

                //int y = (n & -n); //IntelliJ suggestion.

                // To unset right most bit.
                n = n ^ y;
            }
            return count;
        }
    }

    // Using shift operator : iterative
    static class SimpleMethod {
        static int countSetBit(int n) {
            int count = 0;
            while (n > 0) {
                count += (n & 1);
                n >>= 1;
            }
            return count;
        }
    }

    // Using shift operator : recursive
    static class RecursiveMethod {
        static int countSetBit(int n) {
            if (n == 0) return 0;

            return (n & 1) + countSetBit(n >> 1);
        }
    }


    public static void main(String[] args) {
        System.out.println(Brian_Kernighan_Algorithm.countSetBit(9));
        System.out.println(Brian_Kernighan_Algorithm_Recursive.countSetBit(9));
        System.out.println(SimpleMethod.countSetBit(9));
        System.out.println(RecursiveMethod.countSetBit(9));
        System.out.println(EPI_Algorithm.countSetBit(9));
    }

}
