package com.mozaid.problems.arrays;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 *
 *
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/189039/Detailed-intuition-behind-Deque-solution
 */
public class Shortest_Subarray_With_Sum_At_Least_K_Array_Can_Have_Negative {

    public static int shortestSubarray(int[] A, int K) {
        int N = A.length;
        long[] P = new long[N + 1];
        for (int i = 0; i < N; ++i)
            P[i + 1] = P[i] + (long) A[i];

        int ans = N + 1; // N+1 is impossible
        Deque<Integer> deque = new LinkedList();

        for (int y = 0; y < P.length; ++y) {

            while (!deque.isEmpty() && P[y] - P[deque.getFirst()] >= K)
                ans = Math.min(ans, y - deque.removeFirst());

            while (!deque.isEmpty() && P[y] <= P[deque.getLast()])
                deque.removeLast();

            deque.addLast(y);
        }

        return ans < N + 1 ? ans : -1;
    }


    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{84, -37, 32, 40, 95}, 167));

    }
}
