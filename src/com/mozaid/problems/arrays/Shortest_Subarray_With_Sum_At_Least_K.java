package com.mozaid.problems.arrays;

// https://leetcode.com/problems/minimum-size-subarray-sum/
public class Shortest_Subarray_With_Sum_At_Least_K {

    public static int minSubArrayLen(int K, int[] A) {
        if (A == null || A.length == 0) return 0;
        int N = A.length;
        int shortest = Integer.MAX_VALUE;

        int[] sumAtIndex = new int[N];
        sumAtIndex[0] = A[0];

        for (int i = 1; i < N; i++) {
            sumAtIndex[i] = sumAtIndex[i - 1] + A[i];
        }

        int start = 0, end = 0;

        while (end < N) {
            int localSum;
            if (start == 0) {
                localSum = sumAtIndex[end];
            } else {
                localSum = sumAtIndex[end] - sumAtIndex[start - 1];
            }

            if (localSum >= K) {
                if ((end - start + 1) < shortest) {
                    shortest = (end - start + 1);
                }

                start++;

                while (start <= end && localSum >= K) {
                    localSum = sumAtIndex[end] - sumAtIndex[start - 1];
                    if (localSum >= K && (end - start + 1) < shortest) {
                        shortest = (end - start + 1);
                    }
                    start++;
                }
            }
            end++;
        }

        return shortest == Integer.MAX_VALUE ? 0 : shortest;
    }


    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));

    }

}
