package binarysearch;

public class Binary_Search {

    static class Recursive {

        static int binarySearch(int[] nums, int num) {
            return binarySearch(nums, num, 0, nums.length - 1);
        }

        static int binarySearch(int[] nums, int num, int start, int end) {
            if (start > end || end < start) {
                return -1;
            }

            int mid = start + (end - start) / 2;

            if (nums[mid] == num) {
                return mid;
            } else if (nums[mid] < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

            return binarySearch(nums, num, start, end);
        }
    }


    static class Iterative {

        static int binarySearch(int[] nums, int num) {
            int start = 0, end = nums.length - 1;

            while (start <= end) {
                int mid = start + (end - start) / 2;

                if (nums[mid] == num) {
                    return mid;
                } else if (nums[mid] < num) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};

        System.out.println("Recursive.binarySearch(22): " + Recursive.binarySearch(nums, 22));
        System.out.println("Recursive.binarySearch(22): " + Recursive.binarySearch(nums, 23));
        System.out.println("Recursive.binarySearch(22): " + Recursive.binarySearch(nums, -2));

        System.out.println("Iterative.binarySearch(22): " + Iterative.binarySearch(nums, 22));
        System.out.println("Iterative.binarySearch(22): " + Iterative.binarySearch(nums, 23));
        System.out.println("Iterative.binarySearch(22): " + Iterative.binarySearch(nums, -2));

    }
}
