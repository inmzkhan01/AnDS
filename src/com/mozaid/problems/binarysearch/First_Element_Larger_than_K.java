package com.mozaid.problems.binarysearch;

public class First_Element_Larger_than_K {

    static int searchNextGreator(int[] nums, int k) {
        int ans = -1;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= k) {
                start = mid + 1;
            } else {
                ans = nums[mid];
                end = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(searchNextGreator(new int[]{4, 6, 8, 10, 12, 16, 18, 20}, 12));
    }
}
