package com.mozaid.dsa.strings;


import com.mozaid.dsa.sortings.Shuffle;

/**
 * Sorts an array of com.mozaid.problems.strings using 3-way radix quicksort
 */
public class Quick3string {

    public static void sort(String[] a) {
        Shuffle.shuffle(a);
        sort(a, 0, a.length - 1, 0);
    }

    // 3-way string quicksort a[lo..hi] starting at dth character
    private static void sort(String[] a, int lo, int hi, int d) {
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v)
                exch(a, lt++, i++);
            else if (t > v)
                exch(a, i, gt--);
            else
                i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt - 1, d);
        if (v >= 0)
            sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    // return the dth character of s, -1 if d = length of s
    private static int charAt(String s, int d) {
        if (d == s.length())
            return -1;
        return s.charAt(d);
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
