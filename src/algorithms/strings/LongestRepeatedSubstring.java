package strings;

import java.util.Arrays;

/**
 * Finds the longest repeated substring of the specified string
 */
public class LongestRepeatedSubstring {

    public static String lrs(String text) {
        int n = text.length();
        SuffixArray sa = new SuffixArray(text);
        String lrs = "";
        for (int i = 1; i < n; i++) {
            int length = sa.lcp(i);
            if (length > lrs.length()) {
                lrs = text.substring(sa.index(i), sa.index(i) + length);
            }
        }
        return lrs;
    }

    public static String lrs2(String text) {
        int n = text.length();
        String[] suffixes = new String[n];

        for (int i = 0; i < n; i++)
            suffixes[i] = text.substring(i);

        Arrays.sort(suffixes);

        String lrs = "";
        for (int i = 0; i < n - 1; i++) {
            int length = lcp(suffixes[i], suffixes[i + 1]);
            if (length > lrs.length()) {
                lrs = suffixes[i].substring(0, length);
            }
        }
        return lrs;
    }

    private static int lcp(String s1, String s2) {
        int n = Math.min(s1.length(), s2.length());

        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) return i;
        }

        return n;
    }
}
