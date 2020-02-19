package com.mozaid.problems.dp;


// https://leetcode.com/problems/longest-increasing-subsequence
public class LongestIncreasingSubsequence {

    static int LIS(int[] arr) {
        int n = arr.length;

        int[] lis = new int[n];

        lis[0] = 1;

        for (int i = 1; i < n; i++) {

            int maxLisSoFar = 1;

            for (int j = 0; j < i; j++) {

                if (arr[j] < arr[i]) {
                    int localMax = lis[j] + 1;
                    if (localMax > maxLisSoFar) {
                        maxLisSoFar = localMax;
                    }
                }
            }
            lis[i] = maxLisSoFar;
            //Arrays.binarySearch()
        }

        return lis[n - 1];
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 3, 4, 8, 3, 6, 7};
        System.out.println(LIS(arr));
    }
}
