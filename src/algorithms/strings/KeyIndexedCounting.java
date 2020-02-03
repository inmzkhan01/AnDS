package strings;

import java.util.Arrays;

public class KeyIndexedCounting {

    private static final int R = 256;   // extended ASCII alphabet size

    // Rearranges the array of characters in ascending order.
    public static void sort(char[] a) {
        int n = a.length;
        char[] aux = new char[n];

        // compute frequency counts
        int[] count = new int[R + 1];
        for (int i = 0; i < n; i++)
            count[a[i] + 1]++;

        // compute cumulates
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];

        // move data
        for (int i = 0; i < n; i++)
            aux[count[a[i]]++] = a[i];

        // copy back
        for (int i = 0; i < n; i++)
            a[i] = aux[i];
    }

    public static void main(String[] args) {
        char[] a = {'h', '@', 'e', 'c', '%', 'a', 'e', 'n', '$'};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
