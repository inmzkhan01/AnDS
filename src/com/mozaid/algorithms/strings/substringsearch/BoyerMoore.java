package com.mozaid.algorithms.strings.substringsearch;

/**
 * Finds the first occurrence of a pattern string in a text string.
 * This implementation uses the Boyer-Moore algorithm (with the bad-character rule, but not the strong good suffix rule).
 */
public class BoyerMoore {

    private char[] pattern;  // store the pattern as a character array
    private int[] right;     // the bad-character skip array

    public BoyerMoore(String pattern) {
        this(pattern.toCharArray(), 26);
    }

    public BoyerMoore(char[] pattern, int R) {
        this.pattern = pattern;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pattern.length; j++)
            right[pattern[j]] = j;
    }


    public int search(String txt) {
        return search(txt.toCharArray());
    }

    public int search(char[] text) {
        int m = pattern.length;
        int n = text.length;
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pattern[j] != text[i + j]) {
                    skip = Math.max(1, j - right[text[i + j]]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return n;   // not found
    }

}
