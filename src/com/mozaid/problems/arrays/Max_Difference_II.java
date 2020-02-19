package com.mozaid.problems.arrays;


/**
 * For each of the following, A is an integer array of length n.
 * (1.) Compute the maximum value of (A[j0] - A[i0]) + (A[j1] - A[i1]), subject to i0 < j0 < i1 < j1.
 * (2.) Compute the maximum value of (Sigma t=0 to k) (A[jt]-A[it]), subject to i0 < j0 < i1 < j1 ... < ik-1 < jk-1.
 * Here k is a fixed input parameter.
 * (3.) Repeat Problem (2.) when k can be chosen to be any value from 0 to n/2.
 */
public class Max_Difference_II {

    public static int problem1_solution1(int[] a) {
        int n = a.length;

        int maxDiffSum = Integer.MIN_VALUE;

        for (int j = 1; j <= n - 2; j++) {
            int diffSum = maxDiff(a, 0, j) + maxDiff(a, j + 1, n - 1);
            if (diffSum > maxDiffSum)
                maxDiffSum = diffSum;
        }
        return maxDiffSum;
    }

    private static int maxDiff(int[] a, int i, int j) {
        int maxDiff = Integer.MIN_VALUE;
        int min = a[i++];

        while (i <= j) {
            int diff = a[i] - min;
            if (diff > maxDiff)
                maxDiff = diff;

            if (a[i] < min)
                min = a[i];

            i++;
        }

        return maxDiff;
    }

    public static void main(String[] args) {
        System.out.println(problem1_solution1(new int[]{5, 10, 6, 8, 7, 12, 11}));
    }


}
