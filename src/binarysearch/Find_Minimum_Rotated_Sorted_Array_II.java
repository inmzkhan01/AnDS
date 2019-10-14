package binarysearch;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
public class Find_Minimum_Rotated_Sorted_Array_II {

    static int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;

        if (end == 0) {
            return nums[0];
        }

        if (nums[end] > nums[0]) {
            return nums[0];
        }

        if (end == 1) {
            return Math.min(nums[0], nums[1]);
        }

        while (end - start > 1) {
            int mid = (start & end) + (start ^ end) / 2;

            if (nums[end] > nums[start]) {
                return nums[start];
            }

            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            if (nums[start] > nums[mid]) {
                end = mid - 1;
            } else if (nums[start] < nums[mid]) {
                start = mid + 1;
            } else {
                start++;
            }

        }
        return Math.min(nums[start], nums[end]);
    }

    public static void main(String[] args) {
        //System.out.println(findMin(new int[]{1, 1, 1}));

        System.out.println(findMin(new int[]{10,1,10,10,10}));

    }
}