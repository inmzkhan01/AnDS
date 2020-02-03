package dp;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * https://leetcode.com/problems/decode-ways/
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays {

    static class Recursive {

        static int numDecodings(String s) {
            if (s == null || s.isEmpty())
                return 0;
            return numDecodings(s, s.length(), 0);
        }

        static int numDecodings(String s, int n, int i) {
            if (i == n)
                return 1;

            char ch = s.charAt(i);

            if (ch == '0')
                return 0;

            int res = numDecodings(s, n, i + 1);

            if (i < n - 1 && (ch == '1' || (ch == '2' && s.charAt(i + 1) < '7')))
                res += numDecodings(s, n, i + 2);
            return res;
        }

    }

    static class RecursiveII {

        static int numDecodings(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }

            Map<String, Integer> memo = new HashMap<>();

            return numDecodings(s, memo);
        }

        static int numDecodings(String s, Map<String, Integer> memo) {
            int len = s.length();

            if (len == 0) {
                return 1;
            }

            if (len == 1) {
                if (Integer.parseInt(""+s.charAt(0)) != 0) {
                    return 1;
                } else {
                    return 0;
                }
            }

            if (memo.containsKey(s)) {
                return memo.get(s);
            }

            int count = 0;

            if (Integer.parseInt(""+s.charAt(0)) != 0) {
                count = numDecodings(s.substring(1), memo);
            }

            if (Integer.parseInt(s.substring(0, 2)) >= 10 && Integer.valueOf(s.substring(0, 2)) <= 26) {
                count += numDecodings(s.substring(2), memo);
            }

            memo.put(s, count);

            return count;
        }

    }

    static int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int n = s.length();

        int[] dp = new int[n];

        dp[0] = s.charAt(0) != '0' ? 1 : 0;

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
        System.out.println(Recursive.numDecodings("16205"));
        System.out.println(RecursiveII.numDecodings("16205"));
        System.out.println(numDecodings("16205"));
    }
}
