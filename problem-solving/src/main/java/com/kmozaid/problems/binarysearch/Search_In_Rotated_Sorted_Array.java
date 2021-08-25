package com.kmozaid.problems.binarysearch;

public class Search_In_Rotated_Sorted_Array {

    static int binarySearch(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target == nums[mid]) {
                return mid;
            }

            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(binarySearch(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(binarySearch(new int[]{5, 1, 3}, 3));
    }
}