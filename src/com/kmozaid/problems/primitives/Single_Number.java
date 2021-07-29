package com.kmozaid.problems.primitives;

// https://leetcode.com/problems/single-number/
public class Single_Number {

    public static int singleNumber(int[] nums) {
        int result = 0;

        for (int num : nums) {
            result = result ^ num;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));
    }
}
