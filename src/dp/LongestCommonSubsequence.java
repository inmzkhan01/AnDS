package dp;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence {

    /**
     * Basic Recursion solution.
     * @param S1
     * @param S2
     * @return
     */
    static int LCSRecursive(String S1, String S2) {
        int m = S1.length();
        int n = S2.length();

        return LCSRecursive(S1, S2, m - 1, n - 1);
    }

    private static int LCSRecursive(String S1, String S2, int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }

        if (S1.charAt(i) == S2.charAt(j)) {
            return LCSRecursive(S1, S2, i - 1, j - 1) + 1;
        }

        return Integer.max(LCSRecursive(S1, S2, i, j - 1), LCSRecursive(S1, S2, i - 1, j));
    }


    /**
     * Recursion with memorization i.e. Dynamic Programming solution. Top Down approach.
     * @param S1
     * @param S2
     * @return
     */
    static int LCSRecursiveWithMemorization(String S1, String S2) {
        int m = S1.length();
        int n = S2.length();

        int[][] memorizedResult = new int[m][n];

        return LCSRecursiveWithMemorization(S1, S2, m - 1, n-1, memorizedResult);
    }

    private static int LCSRecursiveWithMemorization(String S1, String S2, int i, int j, int[][] memorizedResult) {

        if (i < 0 || j < 0) {
            return 0;
        }

        if (memorizedResult[i][j] == 0) {
            if (S1.charAt(i) == S2.charAt(j)) {
                return memorizedResult[i][j] = LCSRecursiveWithMemorization(S1, S2, i - 1, j - 1, memorizedResult) + 1;
            } else {
                memorizedResult[i][j] = Math.max(LCSRecursiveWithMemorization(S1, S2, i - 1, j, memorizedResult),
                        LCSRecursiveWithMemorization(S1, S2, i, j - 1, memorizedResult));
            }
        }

        return memorizedResult[i][j];
    }

    /**
     * Dynamic programming solution with Bottom Up approach.
     * @param S1
     * @param S2
     * @return
     */
    static int LCSMemorization(String S1, String S2) {
        int m = S1.length();
        int n = S2.length();

        int dp[][] = new int[m][n];

        if(S1.charAt(0)==S2.charAt(0)) {
            dp[0][0] = 1;
        }

        for(int i=1; i<m; i++) {
            dp[i][0] =  S1.charAt(i)==S2.charAt(0) ? 1 : dp[i-1][0];
        }

        for(int j=1; j<n; j++) {
            dp[0][j] = S1.charAt(0)==S2.charAt(j) ? 1 : dp[0][j-1];
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if (S1.charAt(i) == S2.charAt(j)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Integer.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m-1][n-1];
    }


    /**
     * Best Solution : Dynamic programming solution with Bottom Up approach.
     * @param S1
     * @param S2
     * @return
     */
    static int LCSMemorizationAwesome(String S1, String S2) {
        int m = S1.length();
        int n = S2.length();

        int dp[][] = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Integer.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Space optimized : Dynamic programming solution with Bottom Up approach.
     * @param S1
     * @param S2
     * @return
     */
    static int LCSMemorizationSpaceOptimized(String S1, String S2) {
        int m = S1.length();
        int n = S2.length();

        int prev[] = new int[n+1];
        int curr[] = new int[n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j-1];
                } else {
                    curr[j] = Integer.max(curr[j-1], prev[j]);
                }
            }

            // Copy current row to previous row.
            System.arraycopy(curr, 0, prev, 0, n+1);
        }

        return curr[n];
    }


    /**
     * Print LCS : Dynamic programming solution with Bottom Up approach.
     * @param S1
     * @param S2
     * @return
     */
    static String printLCS(String S1, String S2) {
        final int m = S1.length();
        final int n = S2.length();

        int dp[][] = new int[m + 1][n + 1];
        String pointer[][] = new String[m+1][n+1];

        String diagonal = "diagonal";
        String left = "left";
        String top = "top";

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    pointer[i][j] = diagonal;
                } else {
                    dp[i][j] = Integer.max(dp[i][j-1], dp[i-1][j]);
                    if(dp[i][j] == dp[i][j-1]) {
                        pointer[i][j] = left;
                    } else {
                        pointer[i][j] = top;
                    }
                }
            }
        }

        String lcs = "";

        int a = m;
        int b = n;
        String pointerValue = pointer[a][b];
        while(pointerValue != null) {
            if(pointerValue == diagonal) {
                lcs = S1.charAt(a-1) + lcs;
                a--;
                b--;
            } else if(pointerValue == left) {
                b--;
            } else {
                a--;
            }

            pointerValue = pointer[a][b];
        }

        return lcs;
    }


    /**
     * Bottom up dynamic approach for lookup table and Recursive Top Down approach for LCS string.
     * @param text1
     * @param text2
     * @return
     */
    static String LCSString(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] lookup = new int[m + 1][n + 1];

        LCSLength(text1, text2, m, n, lookup);

        return LCS(text1, text2, m, n, lookup);
    }

    static int LCSLength(String text1, String text2, int m, int n, int[][] lookup) {
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    lookup[i][j] = 1 + lookup[i - 1][j - 1];
                } else {
                    lookup[i][j] = Integer.max(lookup[i][j - 1], lookup[i - 1][j]);
                }
            }
        }

        return lookup[m][n];
    }

    static String LCS(String text1, String text2, int m, int n, int[][] lookup) {
        if (m == 0 || n == 0) {
            return "";
        }

        if (text1.charAt(m - 1) == text2.charAt(n - 1)) {
            return LCS(text1, text2, m - 1, n - 1, lookup) + text1.charAt(m - 1);
        }

        if (lookup[m][n-1] > lookup[m-1][n]) {
            return LCS(text1, text2, m, n - 1, lookup);
        } else {
            return LCS(text1, text2, m - 1, n, lookup);
        }
    }

    /**
     * Bottom up dynamic approach for lookup table and Recursive Top Down approach for LCS string.
     * @param text1
     * @param text2
     * @return
     */
    static String LCSStringWithMemorization(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] lookup = new int[m + 1][n + 1];

        Map<String, String> resultLookup = new HashMap<>();

        LCSLength(text1, text2, m, n, lookup);

        return LCS(text1, text2, m, n, lookup, resultLookup);
    }

    static String LCS(String text1, String text2, int m, int n, int[][] lookup, Map<String, String> resultLookup) {
        if (m == 0 || n == 0) {
            return "";
        }

        String key = m + "|" + n;

        if (!resultLookup.containsKey(key)) {
            if (text1.charAt(m - 1) == text2.charAt(n - 1)) {
                resultLookup.put(key, LCS(text1, text2, m - 1, n - 1, lookup, resultLookup) + text1.charAt(m - 1));
            } else {
                if (lookup[m][n - 1] > lookup[m - 1][n]) {
                    resultLookup.put(key, LCS(text1, text2, m, n - 1, lookup, resultLookup));
                } else {
                    resultLookup.put(key, LCS(text1, text2, m - 1, n, lookup, resultLookup));
                }
            }
            return resultLookup.get(key);
        }
        return resultLookup.get(key);
    }


    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args) {
        String S1 = "acbaed";
        String S2 = "abcadf";
        //LCS: ACA

        System.out.println("Recursive: " + LCSRecursive(S1, S2));
        System.out.println("Top Down DP: " + LCSRecursiveWithMemorization(S1, S2));
        System.out.println("Bottom Up DP: " + LCSMemorization(S1, S2));
        System.out.println("Bottom Up DP: " + LCSMemorizationAwesome(S1, S2));
        System.out.println("Bottom Up DP: " + LCSMemorizationSpaceOptimized(S1, S2));
        System.out.println("Bottom Up DP: " + printLCS(S1, S2));
        System.out.println("Top Down: " + LCSString(S1, S2));
        System.out.println("Top Down: " + LCSStringWithMemorization(S1, S2));
    }

}