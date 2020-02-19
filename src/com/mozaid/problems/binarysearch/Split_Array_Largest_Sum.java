package com.mozaid.problems.binarysearch;

// https://leetcode.com/problems/split-array-largest-sum/
public class Split_Array_Largest_Sum {

    static int splitArray(int[] nums, int m) {
        int max = Integer.MIN_VALUE;
        long sum = 0;

        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        //Binary Search Bound
        long left = max;
        long right = sum;

        while (left < right) {
            long mid = left + (right - left) / 2;

            int count = 1;
            long total = 0;

            for (int num : nums) {
                total += num;
                if (total > mid) {
                    total = num;
                    count++;
                }
            }

            if (count > m) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        return (int) right;
    }

    public static void main(String[] args) {
        System.out.println(splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }

}
