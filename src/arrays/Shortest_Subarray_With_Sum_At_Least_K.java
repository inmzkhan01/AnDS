package arrays;

// https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
public class Shortest_Subarray_With_Sum_At_Least_K {

    static int shortestSubarray(int[] A, int K) {
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

        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }


    public static void main(String[] args) {
        //System.out.println(shortestSubarray(new int[]{1, 2, 2, 4, 5}, 9));

        //System.out.println(shortestSubarray(new int[]{1, 2}, 4));

        //System.out.println(shortestSubarray(new int[]{2, -1, 2}, 3));

        System.out.println(shortestSubarray(new int[]{84, -37, 32, 40, 95}, 167));

    }

    /**
     *
     * 1. Create sumArray having total sum till each index.
     * 2. Create a window of elements of at least K sum
     * 2a. if windows sum is smaller then previous window then update shortest.
     * 3. Try to remove an element from start of window
     * 3a. If windows sum is still at least K sum then update shortest.
     *
     */
}
