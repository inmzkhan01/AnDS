package dp;

import java.util.HashMap;
import java.util.Map;

public class JumpGameII {

    static class Recursive {

        static int jump(int[] nums) {
            Map<String, Integer> memo = new HashMap<>();
            return minimumJump(nums, 0, nums.length - 1, memo);
        }

        static int minimumJump(int[] nums, int start, int end, Map<String, Integer> memo) {

            if (start == end) {
                return 0;
            }

            if (nums[start] == 0) {
                return Integer.MAX_VALUE;
            }

            String key = start + "|" + end;

            if (memo.containsKey(key)) {
                return memo.get(key);
            }

            int minJumps = Integer.MAX_VALUE;

            for (int i = start + 1; i <= end; i++) {
                if (i < (start + nums[start] + 1)) {
                    memo.put(key, minimumJump(nums, i, end, memo));
                    int jumps = memo.get(key);
                    if (jumps < minJumps) {
                        minJumps = jumps + 1;
                    }
                }
            }

            return minJumps;
        }

    }


    public static void main(String[] args) {
        int[] nums = {1,2,1,1,1};
        System.out.println("\nRecursive: " + Recursive.jump(nums));
    }
}
