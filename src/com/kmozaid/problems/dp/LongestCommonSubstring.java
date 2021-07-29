package com.kmozaid.problems.dp;

public class LongestCommonSubstring {

    static String LCS(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];

        int maxLength = 0;
        int start = m;
        int end = m;

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(text1.charAt(i-1)==text2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        start = i - maxLength;
                        end = i;
                    }
                }
            }
        }

        for(int i=0;i<=m; i++) {
            for(int j=0; j<=n; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        return text1.substring(start, end);
    }

    public static void main(String[] args) {
        System.out.println("LCS : " + LCS("ABCDE", "BC"));
    }


}
