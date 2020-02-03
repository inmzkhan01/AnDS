package binarysearch;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class Find_Minimum_Rotated_Sorted_Array {

    static int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;

        if (nums[end] >= nums[0]) {
            return nums[0];
        }

        while (start <= end) {
            int mid = (start & end) + (start ^ end) / 2;

            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            if (nums[start] > nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        return -1;
    }


    static boolean isArraySame(int[] nums, int[] rotated) {
        int start = 0, end = nums.length - 1;

        int index = 0;

        while (start <= end) {
            int mid = (start & end) + (start ^ end) / 2;

            if (rotated[mid] > rotated[mid + 1]) {
                 index = mid+1;
                 break;
            }

            if (rotated[mid] < rotated[mid - 1]) {
                index = mid;
                break;
            }

            if (rotated[start] > rotated[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }

        int j = 0;


        while(j<nums.length) {

            if(nums[j] != rotated[index]) {
                return false;
            }

            index++;
            j++;

            index = index % nums.length;
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(isArraySame(new int[]{0, 1, 2, 3, 7,},  new int[]{2, 3, 7, 0, 1,}));


        System.out.println(findMin(new int[]{2, 3, 7, 0, 1,}));

        System.out.println(findMin(new int[]{6, 7, 2, 3, 4, 5}));

        System.out.println(findMin(new int[]{9, 0, 1, 2, 3, 4, 6}));

        System.out.println(findMin(new int[]{4, 5, 6, 7, 8, 1, 2}));

        System.out.println(findMin(new int[]{1, 10, 10, 10}));
    }
}