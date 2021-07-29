package com.kmozaid.problems.arrays;

import java.util.Arrays;

/**
 * Write a function that takes an array A of length n and an index i into
 * A, and rearranges the elements such that all elements less than A[i] appear first,
 * followed by elements equal to A[i], followed by elements greater than A[i]. Your
 * algorithm should have O(1) space complexity and O(n) time complexity
 */
public class Dutch_National_Flag_Problem {

    public static void solve(int[] a, int i) {
        int pivot = a[i];
        int smaller = 0, larger = a.length - 1;
        int equal = 0;

        while (equal <= larger) {
            if (a[equal] < pivot)
                swap(a, equal++, smaller++);
            else if (a[equal] > pivot)
                swap(a, equal, larger--);
            else
                equal++;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {6, 3, 7, 6, 1, 9, 6, 2, 8, 5};
        solve(a, 4);
        System.out.println(Arrays.toString(a));
    }


}
