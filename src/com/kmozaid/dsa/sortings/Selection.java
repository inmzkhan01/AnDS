package com.kmozaid.dsa.sortings;

import java.util.Arrays;

public class Selection extends Sort {

    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[min])) {
                    min = j;
                }
            }
            exch(arr, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {6, 3, 7, 1, 8, 9, 2, 5};
        Selection.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}