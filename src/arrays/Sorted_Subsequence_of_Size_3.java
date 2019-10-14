package arrays;

import java.util.Arrays;

// https://leetcode.com/problems/increasing-triplet-subsequence/
public class Sorted_Subsequence_of_Size_3 {

    static class Increasing_Triplet_Subsequence {

        static boolean increasingTriplet(int[] nums) {
            int lisLength = 0;
            int[] lis = new int[nums.length];

            for (int num : nums) {
                int index = Arrays.binarySearch(lis, 0, lisLength, num);

                if (index < 0) {
                    index = -(index + 1);
                }

                lis[index] = num;

                if (index == lisLength) {
                    lisLength += 1;
                }
            }
            return lisLength >= 3;
        }
    }

    static void sortedSubsequence(int[] arr) {
        int n = arr.length;

        int[] smaller = new int[n];
        smaller[0] = -1;

        int min = 0;
        for (int i = 1; i < n; i++) {
            if (arr[min] <= arr[i]) {
                smaller[i] = min;
            } else {
                smaller[i] = -1;
                min = i;
            }
        }

        int[] greater = new int[n];
        greater[n - 1] = -1;

        int max = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[max] >= arr[i]) {
                greater[i] = max;
            } else {
                greater[i] = -1;
                max = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (smaller[i] != -1 && greater[i] != -1) {
                System.out.println(arr[smaller[i]] + ", " + arr[i] + ", " + arr[greater[i]]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 10, 5, 6, 2, 7};
        sortedSubsequence(arr);

        int[] arr1 = {1, 2, 3, 4};
        sortedSubsequence(arr1);

        System.out.println(Increasing_Triplet_Subsequence.increasingTriplet(new int[]{12, 11, 10, 5, 6, 2, 7}));
        System.out.println(Increasing_Triplet_Subsequence.increasingTriplet(new int[]{1, 2, 3, 4}));

    }


}