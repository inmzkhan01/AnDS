package com.kmozaid.dsa.sortings;

import java.util.Arrays;

public class HeapR extends Sort {

    public static void sort(Comparable[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            exch(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(Comparable[] arr, int n, int index) {
        int largest = index;
        int l = 2 * index + 1;
        int r = 2 * index + 2;

        if (l < n && less(arr[largest], arr[l])) {
            largest = l;
        }

        if (r < n && less(arr[largest], arr[r])) {
            largest = r;
        }

        if (largest != index) {
            exch(arr, index, largest);
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {6, 3, 7, 1, 8, 9, 2, 5};
        HeapR.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
