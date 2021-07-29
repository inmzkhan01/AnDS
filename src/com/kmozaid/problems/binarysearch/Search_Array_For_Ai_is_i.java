package com.kmozaid.problems.binarysearch;

public class Search_Array_For_Ai_is_i {

    static int searchIndex(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == mid) {
                return mid;
            } else if (nums[mid] < mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(searchIndex(new int[]{-3, -1, 0, 2, 4, 7}));
    }
}
