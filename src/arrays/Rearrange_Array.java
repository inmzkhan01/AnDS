package arrays;

import java.util.Arrays;

/**
 * Rearrange given array as follows -
 * B[0] <= B[1] >= B[2] <= B[3] >= B[4] <= B[5]
 */
public class Rearrange_Array {


    public static void rearrange(int[] B) {
        for (int i = 1; i < B.length; ++i) {
            if (((i & 1) == 1 && B[i] < B[i - 1]) || ((i & 1) == 0 && B[i] > B[i - 1])) {
                swap(B, i, i - 1);
            }
        }
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        int[] A = {2, 6, 8, 10, 12, 14};
        rearrange(A);
        System.out.println(Arrays.toString(A));
    }
}
