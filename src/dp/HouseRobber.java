package dp;

public class HouseRobber {

    //https://leetcode.com/problems/house-robber/
    static class HouseRobberI {

        static int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int dp[] = new int[nums.length];
            dp[0] = nums[0];

            if (nums.length >= 2) {
                dp[1] = Math.max(nums[0], nums[1]);
            }

            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }

            return dp[nums.length - 1];
        }
    }

    //https://leetcode.com/problems/house-robber-ii
    static class HouseRobberII {
        static int rob(int[] nums) {

            if (nums == null || nums.length == 0) {
                return 0;
            }

            if (nums.length == 1) {
                return nums[0];
            }

            int n = nums.length;
            int[] withStart = new int[n-1];
            int[] withEnd = new int[n-1];

            System.arraycopy(nums, 0, withStart, 0, n-1);
            System.arraycopy(nums, 1, withEnd, 0, n-1);

            return Math.max(HouseRobberI.rob(withStart), HouseRobberI.rob(withEnd));
        }
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,1};
        System.out.println(HouseRobberI.rob(arr));
        System.out.println(HouseRobberII.rob(arr));

        int[] arr2 = {1,2};
        System.out.println(HouseRobberII.rob(arr2));

        int[] arr3 = {2};
        System.out.println(HouseRobberII.rob(arr3));
    }
}
