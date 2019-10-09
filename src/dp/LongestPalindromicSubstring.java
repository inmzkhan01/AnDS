package dp;

public class LongestPalindromicSubstring {

    public static String longestPalindromeSubstring(String input) {
        int n = input.length();
        int palindromeBeginIndex = 0;
        int maxLength = 1;

        boolean palindrom[][] = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            palindrom[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                palindrom[i][i + 1] = true;
                palindromeBeginIndex = i;
                maxLength = 2;
            }
        }

        for (int currLength = 3; currLength <= n; currLength++) {
            for (int i = 0; i < n - currLength + 1; i++) {
                int j = i + currLength - 1;
                if (input.charAt(i) == input.charAt(j) && palindrom[i + 1][j - 1]) {
                    palindrom[i][j] = true;
                    palindromeBeginIndex = i;
                    maxLength = currLength;
                }
            }
        }

        return input.substring(palindromeBeginIndex, maxLength + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubstring("banana"));
    }

}
