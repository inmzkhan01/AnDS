package dp;

//https://leetcode.com/problems/jump-game/
//https://leetcode.com/problems/jump-game-ii/
public class JumpGame {

    //This is bad solution.
    static class TopDownDP {

        static boolean jump(int[] nums) {
            boolean[] dp = new boolean[nums.length];
            dp[0] = true;

            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j - i <= nums[i] && j < nums.length; j++) {
                    dp[j] = true;
                }
                if (!dp[i + 1]) {
                    break;
                }
            }
            return dp[nums.length - 1];
        }
    }

    static class Recursive {

        static boolean jump(int[] nums) {
            return jump(0, nums);
        }

        static boolean jump(int position, int[] nums) {
            if (position == nums.length - 1) {
                return true;
            }

            int farthestJump = Math.min(position + nums[position], nums.length - 1);

            for (int newPosition = position + 1; newPosition <= farthestJump; newPosition++) {
                if (jump(newPosition, nums)) {
                    return true;
                }
            }
            return false;
        }
    }


    static class RecursiveMemorization {
        enum Index {
            GOOD, BAD, UNKNOWN
        }

        static boolean jump(int[] nums) {
            Index[] memo = new Index[nums.length];
            for (int i = 0; i < memo.length; i++) {
                memo[i] = Index.UNKNOWN;
            }
            memo[memo.length - 1] = Index.GOOD;

            boolean canJump = jump(0, nums, memo);
            return canJump;
        }

        static boolean jump(int position, int[] nums, Index[] memo) {
            if (memo[position] != Index.UNKNOWN) {
                return memo[position] == Index.GOOD ? true : false;
            }

            int farthestJump = Math.min(position + nums[position], nums.length - 1);

            for (int nextJump = farthestJump; nextJump > position; nextJump--) {
                if (jump(nextJump, nums, memo)) {
                    memo[position] = Index.GOOD;
                    return true;
                }
            }
            memo[position] = Index.BAD;
            return false;
        }
    }

    //This is good solution.
    static class BottomUpDP {

        static boolean jump(int[] nums) {
            boolean[] memo = new boolean[nums.length];
            memo[memo.length - 1] = true;

            for (int position = nums.length - 2; position >= 0; position--) {

                int farthestJump = Math.min(position + nums[position], nums.length - 1);

                for (int newPosition = position + 1; newPosition <= farthestJump; newPosition++) {
                    if (memo[newPosition]) {
                        memo[position] = true;
                        break;
                    }
                }

            }
            return memo[0] == true;
        }
    }

    // This is best solution.
    static class Greedy {

        static boolean canJump(int[] nums) {
            int currGoodPosition = nums.length - 1;

            for (int i = nums.length - 1; i >= 0; i--) {
                if (i + nums[i] >= currGoodPosition) {
                    currGoodPosition = i;
                }
            }
            return currGoodPosition == 0;
        }
    }


    public static void main(String[] args) {
        /*int[] arr1 = {2, 3, 1, 1, 4};
        System.out.println("arr1 TopDownDP: " + TopDownDP.jump(arr1));
        System.out.println("arr1 Recursive: " + Recursive.jump(arr1));
        System.out.println("arr1 RecursiveMemorization: " + RecursiveMemorization.jump(arr1));
        System.out.println("arr1 BottomUpDP: " + BottomUpDP.jump(arr1));

        int[] arr2 = {3, 1, 2, 0, 4};
        System.out.println("\narr2 TopDownDP: " + TopDownDP.jump(arr2));
        System.out.println("arr2 Recursive: " + Recursive.jump(arr2));
        System.out.println("arr2 RecursiveMemorization: " + RecursiveMemorization.jump(arr2));
        System.out.println("arr2 BottomUpDP: " + BottomUpDP.jump(arr2));

        int[] arr3 = {2, 0};
        System.out.println("\narr3 TopDownDP: " + TopDownDP.jump(arr3));
        System.out.println("arr3 Recursive: " + Recursive.jump(arr3));
        System.out.println("arr3 RecursiveMemorization: " + RecursiveMemorization.jump(arr3));
        System.out.println("arr3 BottomUpDP: " + BottomUpDP.jump(arr3));

        int[] arr4 = {3, 2, 1, 0, 4};
        System.out.println("\narr4 TopDownDP: " + TopDownDP.jump(arr4));
        System.out.println("arr4 Recursive: " + Recursive.jump(arr4));
        System.out.println("arr4 RecursiveMemorization: " + RecursiveMemorization.jump(arr4));
        System.out.println("arr4 BottomUpDP: " + BottomUpDP.jump(arr4));

        int[] arr5 = {1, 5, 2, 1, 0, 2, 0};
        System.out.println("\narr5 TopDownDP: " + TopDownDP.jump(arr5));
        System.out.println("arr5 Recursive: " + Recursive.jump(arr5));
        System.out.println("arr5 RecursiveMemorization: " + RecursiveMemorization.jump(arr5));
        System.out.println("arr5 BottomUpDP: " + BottomUpDP.jump(arr5));

        int[] arr6 = {5, 4, 3, 2, 1, 0, 0};
        System.out.println("\narr6 TopDownDP: " + TopDownDP.jump(arr6));
        System.out.println("arr6 Recursive: " + Recursive.jump(arr6));
        System.out.println("arr6 RecursiveMemorization: " + RecursiveMemorization.jump(arr6));
        System.out.println("arr6 BottomUpDP: " + BottomUpDP.jump(arr6));*/

        int[] nums = {2, 4, 2, 1, 0, 2, 0};
        System.out.println("\narr5 RecursiveMemorization: " + RecursiveMemorization.jump(nums));
        System.out.println("nums BottomUpDP: " + BottomUpDP.jump(nums));
        System.out.println("nums greedy: " + Greedy.canJump(nums));
    }
}
