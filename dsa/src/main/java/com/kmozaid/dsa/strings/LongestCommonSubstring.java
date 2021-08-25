package com.kmozaid.dsa.strings;

import java.util.Arrays;

/**
 * Compute the longest common substring that appears in two given com.mozaid.problems.strings.
 * This implementation computes the suffix array of each string and applies a merging operation to determine the longest common substring.
 */
public class LongestCommonSubstring {

    public static String lcs(String s, String t) {
        SuffixArray suffix1 = new SuffixArray(s);
        SuffixArray suffix2 = new SuffixArray(t);

        // find longest common substring by "merging" sorted suffixes
        String lcs = "";
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            int p = suffix1.index(i);
            int q = suffix2.index(j);
            String x = lcp(s, p, t, q);
            if (x.length() > lcs.length())
                lcs = x;

            if (compare(s, p, t, q) < 0)
                i++;
            else
                j++;
        }
        return lcs;
    }

    // return the longest common prefix of suffix s[p..] and suffix t[q..]
    private static String lcp(String s, int p, String t, int q) {
        int n = Math.min(s.length() - p, t.length() - q);
        for (int i = 0; i < n; i++) {
            if (s.charAt(p + i) != t.charAt(q + i))
                return s.substring(p, p + i);
        }
        return s.substring(p, p + n);
    }

    // compare suffix s[p..] and suffix t[q..]
    private static int compare(String s, int p, String t, int q) {
        int n = Math.min(s.length() - p, t.length() - q);
        for (int i = 0; i < n; i++) {
            if (s.charAt(p + i) != t.charAt(q + i))
                return s.charAt(p + i) - t.charAt(q + i);
        }
        if (s.length() - p < t.length() - q)
            return -1;
        else if (s.length() - p > t.length() - q)
            return +1;
        else
            return 0;
    }


    public static String lcs2(String s, String t) {
        int n = s.length();
        String[] suffixesS = new String[n];
        for (int i = 0; i < n; i++) {
            suffixesS[i] = s.substring(i);
        }
        Arrays.sort(suffixesS);

        int m = s.length();
        String[] suffixesT = new String[n];
        for (int i = 0; i < n; i++) {
            suffixesT[i] = t.substring(i);
        }
        Arrays.sort(suffixesT);

        int i = 0, j = 0;

        String lcs = "";
        while (i < n && j < m) {
            String x = lcp(suffixesS[i], suffixesT[j]);
            if (x.length() > lcs.length())
                lcs = x;

            if (compare(s, t) < 0)
                i++;
            else
                j++;
        }
        return lcs;
    }

    private static String lcp(String s1, String s2) {
        int n = Math.min(s1.length(), s2.length());
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) return s1.substring(0, i);
        }
        return s1;
    }

    private static int compare(String s1, String s2) {
        int n = Math.min(s1.length(), s2.length());
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i))
                return s1.charAt(i) - s2.charAt(i);
        }
        if (s1.length() < s2.length())
            return -1;
        else if (s1.length() > s2.length())
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) {
        String s = "ABCDEF";
        String t = "MCDEG";

        System.out.println("LCS using SuffixArray class: " + lcs(s, t));
        System.out.println("LCS using String[] suffixes array: " + lcs(s, t));
    }

}
