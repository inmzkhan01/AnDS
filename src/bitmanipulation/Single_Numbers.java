package bitmanipulation;

import java.util.Arrays;

//https://leetcode.com/problems/single-number-iii/
public class Single_Numbers {
    static int[] singleNumber(int[] nums) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int xor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xor ^= nums[i];
        }

        int bit = xor & ~(xor - 1);

        int xorWith = 0;
        int xorWithOut = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((bit & nums[i]) > 0) {
                xorWith ^= nums[i];
            } else {
                xorWithOut ^= nums[i];
            }
        }
        return new int[]{xorWith, xorWithOut};
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        System.out.println(Arrays.toString(singleNumber(nums)));
    }
}