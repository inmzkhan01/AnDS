package dp;

//https://leetcode.com/problems/decode-ways/
public class DecodeWays {

    static int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int n = s.length();

        int[] dp = new int[n];

        dp[0] = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 1; i < n; i++) {
            int digit = Integer.valueOf(s.substring(i, i + 1));

            if (digit > 0) {
                dp[i] = dp[i - 1];
            }

            int number = Integer.valueOf(s.substring(i - 1, i + 1));

            if (number >= 10 && number <= 26) {
                dp[i] = i >= 2 ? dp[i] + dp[i - 2] : dp[i] + 1;
            }

        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("23"));
    }
}
