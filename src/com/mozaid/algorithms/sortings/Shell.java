package com.mozaid.algorithms.sortings;

import java.util.Arrays;

public class Shell extends Sort {

    public static void sort(Comparable[] arr) {
        int N = arr.length;

        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(arr[j], arr[j - h]))
                        exch(arr, j, j - h);
                    else break;
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {6, 3, 7, 1, 8, 9, 2, 5};
        Shell.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
