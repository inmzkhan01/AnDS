package com.mozaid.dsa.sortings;

import java.util.Arrays;

/**
 * Optimized version
 */
public class MergeX extends Sort {

    public static void sort(Comparable[] a) {
        //Use it as source.
        Comparable[] aux = a.clone();

        sort(aux, a, 0, a.length - 1);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);

        if (less(src[mid], src[mid+1])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid + 1, hi);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                    dst[k] = src[j++];
            else if (j > hi)                dst[k] = src[i++];
            else if (less(src[j], src[i]))  dst[k] = src[j++];
            else                            dst[k] = src[i++];
        }

        assert isSorted(dst, lo, hi);
    }

    public static void main(String[] args) {
        Integer[] nums = {6, 3, 7, 1, 9, 2, 8, 5};
        MergeX.sort(nums);
        assert isSorted(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

}