package com.kmozaid.problems.dp;

//https://leetcode.com/problems/jump-game-ii/
public class JumpGameII {

    static class Recursive {

        static int jump(int[] nums, int position, int step, int min) {
            if (position >= nums.length - 1)
                return Math.min(step, min);

            if (nums[position] == 0)
                return nums.length;

            for (int i = 1; i <= nums[position]; i++) {
                int jumps = jump(nums, position + i, step + 1, min);
                if (jumps < min)
                    min = jumps;
            }
            return min;

        }

        static int jump(int[] nums) {
            return jump(nums, 0, 0, nums.length);
        }
    }

    static int jump(int[] nums) {
        int jump = 0, currEnd = 0, currFarthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            currFarthest = Math.max(currFarthest, i + nums[i]);

            if (i == currEnd) {
                jump++;
                currEnd = currFarthest;
            }
        }

        return jump;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 1, 1};
        System.out.println("\nRecursive: " + Recursive.jump(nums));
        System.out.println("\nGreedy: " + jump(nums));
    }
}
