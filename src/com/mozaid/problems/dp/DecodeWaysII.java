package com.mozaid.problems.dp;

/**
 * https://leetcode.com/problems/decode-ways-ii/
 *
 *
 * <p>
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
 * <p>
 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 * <p>
 * Also, since the answer may be very large, you should return the output mod 109 + 7.
 * <p>
 *
 *     https://leetcode.com/problems/decode-ways-ii/solution/
 */
public class DecodeWaysII {

    static int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int n = s.length();

        int[] dp = new int[n];

        dp[0] = s.charAt(0) == '*' ? 9 : (s.charAt(0) == '0' ? 1 : 0);

        for (int i = 1; i < n; i++) {
            int singleDigitNum = Integer.parseInt(s.substring(i, i + 1));
            int doubleDigitsNum = Integer.parseInt(s.substring(i - 1, i + 1));

            if (singleDigitNum >= 1 && singleDigitNum <= 9) {
                dp[i] += dp[i - 1];
            }

            if (doubleDigitsNum >= 10 && doubleDigitsNum <= 26) {
                dp[i] += i >= 2 ? dp[i - 2] : 1;
            }

        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("23"));
    }
}
