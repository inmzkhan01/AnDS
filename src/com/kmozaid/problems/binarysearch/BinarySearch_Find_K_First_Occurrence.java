package com.kmozaid.problems.binarysearch;

public class BinarySearch_Find_K_First_Occurrence {

    static class Recursive {

        static int binarySearch(int[] nums, int key) {
            return binarySearch(nums, key, 0, nums.length - 1);
        }

        static int binarySearch(int[] nums, int key, int start, int end) {
            if (start > end || end < start) {
                return -1;
            }

            int mid = start + (end - start) / 2;

            if (mid == 0 || (key == nums[mid] && key > nums[mid-1])) {
                return mid;
            } else if (key > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

            return binarySearch(nums, key, start, end);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 6, 6, 6, 6, 6};
        System.out.println("First occurrence of 6: " + Recursive.binarySearch(nums, 6));
    }

}