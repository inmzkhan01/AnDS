package com.kmozaid.problems.binarysearch;


import java.util.Arrays;

// https://leetcode.com/problems/longest-increasing-subsequence
public class Longest_Increasing_Subsequence {

    static int LIS(int[] nums) {
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
        return lisLength;
    }

    public static void main(String[] args) {
        System.out.println(LIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}