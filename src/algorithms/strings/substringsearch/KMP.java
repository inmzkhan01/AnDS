package algorithms.strings.substringsearch;

/**
 * Finds the first occurrence of a pattern string in a text string.
 * <p>
 * This implementation uses a version of the Knuth-Morris-Pratt substring search algorithm.
 * The version takes time proportional to (n + m*R) in the worst case,
 * where n is the length of the text string, m is the length of the pattern, and R is the alphabet size.
 * It uses extra space proportional to (m*R).
 */
public class KMP {

    private char[] pattern;    // the character array for the pattern
    private int[][] dfa;       // the KMP deterministic finite automaton

    public KMP(String pat) {
        this(pat.toCharArray(), 26);
    }

    public KMP(char[] pattern, int R) {
        this.pattern = pattern;

        // build DFA from pattern
        int m = pattern.length;
        dfa = new int[R][m];
        dfa[pattern[0]][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];      // Copy mismatch cases.
            dfa[pattern[j]][j] = j + 1;     // Set match case.
            x = dfa[pattern[j]][x];         // Update restart state.
        }
    }

    public int search(String txt) {
        return search(txt.toCharArray());
    }

    public int search(char[] text) {

        // simulate operation of DFA on text
        int m = pattern.length;
        int n = text.length;
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[text[i]][j];
        }
        if (j == m) return i - m;    // found
        return n;                    // not found
    }

}
