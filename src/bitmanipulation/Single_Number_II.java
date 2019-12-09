package bitmanipulation;

/**
 * https://leetcode.com/problems/single-number-ii/discuss/43297/Java-O(n)-easy-to-understand-solution-easily-extended-to-any-times-of-occurance
 *
 * https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
 *
 * <p>
 * https://leetcode.com/problems/single-number-ii/
 */
public class Single_Number_II {

    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((nums[j] >> i) & 1) == 1) {
                    sum++;
                    sum = sum % 3;
                }
            }
            if (sum != 0) {
                ans = ans | (sum << i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 3, 2}));
        System.out.println(singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

}