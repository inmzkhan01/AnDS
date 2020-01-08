package arrays;

import java.util.Arrays;

public class Sorted_Array_Two_Sum_Problem {

    public static int[] twoSum(int[] a, int K) {
        int i = 0, j = a.length - 1;
        while (i < j) {
            int sum = a[i] + a[j];

            if (sum == K)
                return new int[]{i, j};

            if (sum < K)
                i++;
            else
                j--;
        }
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 6, 10, 14, 16, 20, 22}, 16)));
    }
}
