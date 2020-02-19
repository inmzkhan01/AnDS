package com.mozaid.problems.primitives;

/**
 * Write a function that performs base conversion. Specifically, the input
 * is an integer base b1, a string s, representing an integer x in base b1, and another integer base
 * b2; the output is the string representing the integer x in base b2. Assume 2<= b1; b2 <=16.
 * Use “A” to represent 10, “B” for 11, ... and “F” for 15.
 * <p>
 * For example, if the string is “615”, b1 = 7 and b2 = 13, then x = 306, and the final result is “1A7”.
 */
public class ConvertCase {

    public static String convert_base(String s, int b1, int b2) {
        boolean neg = s.charAt(0) == '-';
        int x = 0;
        for (int i = (neg ? 1 : 0); i < s.length(); ++i) {
            x *= b1;
            x += Character.isDigit(s.charAt(i)) ? s.charAt(i) - '0' : s.charAt(i) - 'A' + 10;
        }

        String ans = "";

        while (x > 0) {
            int r = x % b2;
            ans += (r >= 10 ? String.valueOf((char) ('A' + r - 10)) : r);
            x /= b2;
        }

        if (ans.isEmpty()) { // special case: s is 0.
            ans += "0";
        }
        if (neg) { // s is a negative number.
            ans += "-";
        }

        return new StringBuffer(ans).reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convert_base("615", 7, 13));
    }

}
