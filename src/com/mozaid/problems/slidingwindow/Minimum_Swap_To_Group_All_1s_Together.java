package com.mozaid.problems.slidingwindow;

//https://www.geeksforgeeks.org/minimum-swaps-required-group-1s-together/
public class Minimum_Swap_To_Group_All_1s_Together {

    public static int minSwap(int[] arr) {
        int n = arr.length;

        int noOfOnes = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) {
                ++noOfOnes;
            }
        }



        int[] noOfOnesTillIndex = new int[n];

        if (arr[0] == 1) {
            noOfOnesTillIndex[0] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] == 1)
                noOfOnesTillIndex[i] = noOfOnesTillIndex[i - 1] + 1;
            else
                noOfOnesTillIndex[i] = noOfOnesTillIndex[i - 1];
        }

        // length of sub-array to check for
        int x = noOfOnes;

        // Number of 1's in first sub-array of size x.
        int maxOnes = noOfOnesTillIndex[x - 1];

        // Number of 1's in other sub-com.mozaid.problems.arrays of size x.
        for (int i = x; i < n; i++) {
            maxOnes = Math.max(maxOnes, noOfOnesTillIndex[i] - noOfOnesTillIndex[i - x]);
        }

        // calculate number of zeros in subarray of length x with maximum number of 1's
        int noOfZeroes = x - maxOnes;

        return noOfZeroes;
    }


    public static void main(String[] args) {
        System.out.println(minSwap(new int[]{1, 1, 0, 1, 0, 1}));
    }
}
