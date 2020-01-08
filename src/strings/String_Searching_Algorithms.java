package strings;

public class String_Searching_Algorithms {

    static class NaiveAlgorithm {

        static void search(String txt, String pat) {
            for (int i = 0; i <= txt.length() - pat.length(); i++) {
                int j;
                for (j = 0; j < pat.length(); j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                if (j == pat.length()) {
                    System.out.println("Pattern found at index " + i);
                }
            }
        }
    }

    /**
     * We can do this if all characters of pattern are different.
     */
    static class OptimizedNaiveAlgorithm {

        static void search(String txt, String pat) {
            for (int i = 0; i <= txt.length() - pat.length(); ) {
                int j;
                for (j = 0; j < pat.length(); j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                if (j == pat.length())
                    System.out.println("Pattern found at index " + i);
                else if (j == 0)
                    i = i + 1;
                else
                    i = i + j;
            }
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
