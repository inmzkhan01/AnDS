package dp;

public class LongestPalindromicSubsequence {

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
            return text.charAt(i) + LPSString(text, i + 1, j - 1) + text.charAt(j);
        }

        String left = LPSString(text, i, j - 1);
        String right = LPSString(text, i + 1, j);
        return left.length() > right.length() ? left : right;
    }

    public static void main(String[] args) {
        System.out.println("LPS: " + LPS("ABBDCACB"));
        System.out.println("LPS: " + LPSString("ABBDCACB"));
    }
}
