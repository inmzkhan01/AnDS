package primitives;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GCD {

    public static int gcdByModulus(int a, int b) {
        if (b == 0)
            return a;
        return gcdByModulus(b, a % b);
    }

    /**
     * It is very slow—its time complexity is O(max(x; y)), which
     * is exponential in the size of the input. (Expressed in binary, the numbers x and y,
     * require dlg xe and dlg ye bits respectively.) As an example, if the input is x = 2^n,
     * y = 2, the algorithm makes 2^(n-1) recursive calls.
     */
    public static int gcdBySubtraction(int a, int b) {
        // Everything divides 0
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        // base case
        if (a == b)
            return a;

        /*
        // a is greater
        if (a > b)
            return gcd2(a - b, b);
        return gcd2(a, b - a);
        */

        return gcdBySubtraction(max(a, b) - min(a, b), min(a, b));
    }

    /**
     * Efficient.
     * The time complexity is proportional to the sum of the number of bits in x and y, i.e., O(log x + log y)).
     */
    public static long gcd(long x, long y) {
        if (x == 0) {
            return y;
        } else if (y == 0) {
            return x;
        } else if (isEven(x) && isEven(y)) { // x and y are even.
            return gcd(x >> 1, y >> 1) << 1;
        } else if (isEven(x) && isOdd(y)) { // x is even , and y is odd.
            return gcd(x >> 1, y);
        } else if (isOdd(x) && isEven(y)) { // x is odd, and y is even.
            return gcd(x, y >> 1);
        } else if (x > y) { // both x and y are odd, and x > y.
            return gcd(x - y, y);
        }
        return gcd(x, y - x); // both x and y are odd, and x <= y.
    }

    private static boolean isEven(long x) {
        return (x & 1) == 0;
    }

    private static boolean isOdd(long x) {
        return !isEven(x);
    }

    public static void main(String[] args) {
        System.out.println(gcdByModulus(98, 56));
        System.out.println(gcdBySubtraction(98, 56));
        System.out.println(gcd(98, 56));
    }
}
