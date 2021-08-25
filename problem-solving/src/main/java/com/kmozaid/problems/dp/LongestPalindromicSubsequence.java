package com.kmozaid.problems.dp;

public class LongestPalindromicSubsequence {

    static class Recursive {
        static int LPS(String text) {
            return LPS(text.toCharArray(), 0, text.length() - 1);
        }

        static int LPS(char[] text, int i, int j) {
            if (i > j) {
                return 0;
            }
            if (i == j) {
                return 1;
            }
            if (text[i] == text[j]) {
                return 2 + LPS(text, i + 1, j - 1);
            }
            return Integer.max(LPS(text, i, j - 1), LPS(text, i + 1, j));
        }

        static String LPSString(String text) {
            return LPSString(text, 0, text.length() - 1);
        }

        static String LPSString(String text, int i, int j) {
            if (i > j) {
                return "";
            }
            if (i == j) {
                return String.valueOf(text.charAt(i));
            }
            if (text.charAt(i) == text.charAt(j)) {
                return text.charAt(i) + LPSString(text, i + 1, j - 1) + text.charAt(i);
            }

            String left = LPSString(text, i, j - 1);
            String right = LPSString(text, i + 1, j);
            return left.length() > right.length() ? left : right;
        }
    }

    static class RecursiveMemorization {
        static int longestPalindromeSubseq(String s) {
            return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
        }

        static int helper(String s, int i, int j, Integer[][] memo) {
            if (memo[i][j] != null) {
                return memo[i][j];
            }
            if (i > j) return 0;
            if (i == j) return 1;

            if (s.charAt(i) == s.charAt(j)) {
                memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
            } else {
                memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
            }
            return memo[i][j];
        }
    }

    static class DPSolution {
        static int longestPalindromeSubseq(String string) {
            char[] s = string.toCharArray();
            int n = string.length();

            int[][] dp = new int[n][n];

            // len 1
            for (int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }

            // len 2
            for (int i = 0; i < n - 1; i++) {
                if(s[i] == s[i+1])
                    dp[i][i+1] = 2;
                else
                    dp[i][i+1] = 1;
            }
            /*for (int i = 0, j = 1; i < n-1; i++, j++) {
                com.mozaid.problems.dp[i][j] = s[i] == s[j] ? 2 : 1;
            }*/

            // len 3 and up
            for (int len = 3; len <= n; len++) {

                for (int i = 0, j = len - 1; j < n; i++, j++) {

                    // better of without left or without right
                    int max = Math.max(dp[i + 1][j], dp[i][j - 1]);

                    if (s[i] == s[j]) {
                        // now check 2 plus without left and without right
                        max = Math.max(max, 2 + dp[i + 1][j - 1]);
                    }

                    dp[i][j] = max;
                }
            }

            return dp[0][n - 1];
        }
    }


    public static void main(String[] args) {
        System.out.println("LPS: " + Recursive.LPS("ABBDCACB"));
        System.out.println("LPS: " + Recursive.LPSString("ABBDCACB"));

        System.out.println("LPS: " + RecursiveMemorization.longestPalindromeSubseq("ABBDCACB"));
        System.out.println("LPS: " + DPSolution.longestPalindromeSubseq("ABBDCACB"));
    }
}
