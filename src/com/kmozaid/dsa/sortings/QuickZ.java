package com.kmozaid.dsa.sortings;

import java.util.Arrays;

public class QuickZ {

    static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = findPivot(arr, left, right);

            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    static int findPivot(int[] arr, int left, int right) {
        int pivot = right;

        int i = left - 1;

        for (int j = left; j < right; j++) {

            if (arr[j] < arr[pivot]) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, pivot);

        return i + 1;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 9, 7, 6, 1, 0, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
