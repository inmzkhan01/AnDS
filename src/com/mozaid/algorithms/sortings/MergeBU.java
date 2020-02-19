package com.mozaid.algorithms.sortings;

import java.util.Arrays;

public class MergeBU extends Sort {

    public static void sort(Comparable[] a) {
        int N = a.length;

        Comparable[] aux = new Comparable[N];

        for (int sz = 1; sz < N; sz *= 2)
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                int mid = lo + sz - 1;
                int hi = Math.min((lo + sz + sz - 1), N - 1);
                merge(a, aux, lo, mid, hi);
            }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid)                    a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[i], aux[j]))  a[k] = aux[i++];
            else                            a[k] = aux[j++];
        }

        assert isSorted(a, lo, hi);
    }

    public static void main(String[] args) {
        Integer[] nums = {6, 3, 7, 1, 9, 2, 8, 5};
        MergeBU.sort(nums);
        assert isSorted(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

}