package strings;

/**
 * A best-case instance is a pattern that consists of m As and a text that consists of n Bs.
 * A worst-case instance is a pattern that consists of m As and a text that consists of n As.
 */
public class String_Searching_Algorithms {

    static class NaiveAlgorithm {

        static void search(String txt, String pat) {
            int N = txt.length();
            int M = pat.length();
            for (int i = 0; i <= N - M; i++) {
                int j;
                for (j = 0; j < pat.length(); j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }
                if (j == M) {
                    System.out.println("Pattern found at index " + i);
                }
            }
        }
    }

    static class OptimizedBruteForceSearch {

        static int search(String txt, String pat) {
            int i, N = txt.length();
            int j, M = pat.length();
            for (i = 0, j = 0; i < N && j < M; i++) {
                if (txt.charAt(i) == pat.charAt(j))
                    j++;
                else {
                    i -= j;    // Backup
                    j = 0;
                }
            }
            if (j == M)
                return i - M;
            else
                return -1;
        }
    }

    // Difficult
    static class Knuth_Morris_Pratt_KMP {

    }

    // Difficult
    static class Boyer_Moore {

    }

    public static void main(String[] args) {
        NaiveAlgorithm.search("AABAACAADAABAABA", "AABA");
    }
}
