package com.kmozaid.dsa.sortings;

import java.util.Arrays;

public class Heap extends Sort {

    public static void sort(Comparable a[]) {
        int n = a.length;

        // Build Initial Heap
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }

        // Sort
        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;

            if (j < n && less(pq, j, j + 1)) j++;

            if (less(pq, j, k)) break;

            exch(pq, j, k);

            k = j;
        }
    }

    protected static boolean less(Comparable[] a, int v, int w) {
        return a[--v].compareTo(a[--w]) < 0;
    }

    protected static void exch(Comparable[] arr, int v, int w) {
        Comparable temp = arr[--v];
        arr[v] = arr[--w];
        arr[w] = temp;
    }

    public static void main(String[] args) {
        Integer[] nums = {6, 3, 7, 1, 8, 9, 2, 5};
        Heap.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

}