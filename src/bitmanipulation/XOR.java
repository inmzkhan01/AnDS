package bitmanipulation;


/**
 *
 * x^y == y^x
 * 011 ^ 100 = 111;
 * 100 ^ 011 = 111;
 *
 * x^y^x == x^x^y
 * (011 ^ 100 ^ 011) == (011 ^ 011 ^ 100)
 *
 * XOR is commutative.
 *
 */
public class XOR {

    // This will work when array has other numbers repeated evenly.
    static int nonRepeatedNumber(int[] numbers) {
        int number = 0;
        for(int x : numbers) {
            number = number ^ x;
        }
        return number;
    }

    // https://www.geeksforgeeks.org/find-the-element-that-appears-once/
    static int elementThatAppearOnce(int[] nums) {

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(nonRepeatedNumber(new int[] {1, 1, 2, 3, 3}));
        System.out.println(nonRepeatedNumber(new int[] {1, 1, 3, 3, 2, 3, 3, 1, 1}));
        // Wrong result. Number are repeated oddly.
        System.out.println(nonRepeatedNumber(new int[] {12, 1, 12, 12, 1, 1, 2}));

        System.out.println(elementThatAppearOnce(new int[] {12, 1, 12, 12, 1, 1, 2}));
    }
}