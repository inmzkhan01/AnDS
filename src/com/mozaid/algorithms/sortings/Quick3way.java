package com.mozaid.algorithms.sortings;

import java.util.Arrays;

/**
 * 3-way partitioning - useful in duplicate keys.
 * <p>
 * The goal of 3-way partitioning is to speed up quick-sort in the presence of duplicate keys.
 */
public class Quick3way extends Sort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);

            if (cmp < 0)       exch(a, i++, lt++);
            else if (cmp > 0)  exch(a, i, gt--);
            else               i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        Integer[] nums = {6, 3, 7, 6, 1, 9, 6, 2, 8, 5};
        Quick3way.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

}