package com.kmozaid.dsa.sortings;

import java.util.Arrays;

public class Bubble {

    static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 9, 7, 6, 1, 0, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
