package com.kmozaid.problems.arrays;

/**
 * https://www.geeksforgeeks.org/leaders-in-an-array/
 */
public class Leaders_In_Array {

    void printLeaders(int arr[], int size) {
        int max_from_right = arr[size - 1];

        // Rightmost element is always leader.
        System.out.print(max_from_right + " ");

        for (int i = size - 2; i >= 0; i--) {
            if (max_from_right < arr[i]) {
                max_from_right = arr[i];
                System.out.print(max_from_right + " ");
            }
        }
    }

    /* Driver program to test above functions */
    public static void main(String[] args) {
        Leaders_In_Array lead = new Leaders_In_Array();
        int arr[] = new int[]{16, 17, 4, 3, 5, 2};
        int n = arr.length;
        lead.printLeaders(arr, n);
    }
}
