package dp;

/**
 * Note: Don't try to find recursive solution!!!
 */
public class LongestPalindromicSubstring {

    // Time O(n^2) Space O(n^2)
    public static String longestPalindromeSubstring(String input) {
        int n = input.length();
        int palindromeBeginIndex = 0;
        int maxLength = 1;

        boolean palindrom[][] = new boolean[n][n];

        // len 1
        for (int i = 0; i < n; i++) {
            palindrom[i][i] = true;
        }

        // len 2
        for (int i = 0; i < n - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                palindrom[i][i + 1] = true;
                palindromeBeginIndex = i;
                maxLength = 2;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                if (input.charAt(i) == input.charAt(j) && palindrom[i + 1][j - 1]) {
                    palindrom[i][j] = true;
                    palindromeBeginIndex = i;
                    maxLength = len;
                }
            }
        }

        return input.substring(palindromeBeginIndex, maxLength + 1);
    }

    // Time O(n^2), Space O(1)
    static class ExpandAroundCentre {

        static String longestPalindrome(String s) {
            if (s.isEmpty()) {
                return null;
            }

            if (s.length() == 1) {
                return s;
            }

            String longest = s.substring(0, 1);
            for (int i = 0; i < s.length(); i++) {
                // get longest palindrome with center of i
                String tmp = helper(s, i, i);
                if (tmp.length() > longest.length()) {
                    longest = tmp;
                }

                // get longest palindrome with center of i, i+1
                tmp = helper(s, i, i + 1);
                if (tmp.length() > longest.length()) {
                    longest = tmp;
                }
            }

            return longest;
        }

        // Given a center, either one letter or two letter, Find longest palindrome
        static String helper(String s, int begin, int end) {
            while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
                begin--;
                end++;
            }
            return s.substring(begin + 1, end);
        }
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubstring("banana"));
        System.out.println(ExpandAroundCentre.longestPalindrome("banana"));
    }

}
