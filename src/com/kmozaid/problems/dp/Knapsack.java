package com.kmozaid.problems.dp;

public class Knapsack {

    private class Recursive {
        // Returns the maximum value that can be put in a knapsack of capacity W
        int knapsack(int W, int wt[], int val[], int n) {
            // Base Case
            if (n == 0 || W == 0)
                return 0;

            // If weight of the nth item is more than Knapsack capacity W, then
            // this item cannot be included in the optimal solution
            if (wt[n - 1] > W)
                return knapsack(W, wt, val, n - 1);

                // Return the maximum of two cases:
                // (1) nth item included
                // (2) not included
            else
                return Math.max(val[n - 1] + knapsack(W - wt[n - 1], wt, val, n - 1), knapsack(W, wt, val, n - 1));
        }
    }

    private class DP {

        // Returns the maximum value that can be put in a knapsack of capacity W
        int knapsack(int W, int wt[], int val[], int n) {
            int K[][] = new int[n + 1][W + 1];

            // Build table K[][] in bottom up manner
            for (int i = 1; i <= n; i++) {
                for (int w = 1; w <= W; w++) {
                    int item = i - 1;
                    if (wt[item] > w)
                        K[i][w] = K[i - 1][w];
                    else
                        K[i][w] = Math.max(val[item] + K[i - 1][w - wt[item]], K[i - 1][w]);

                }
            }
            return K[n][W];
        }
    }

    public static void main(String args[]) {
        int val[] = new int[]{60, 100, 120};
        int wt[] = new int[]{10, 20, 30};
        int W = 50;
        System.out.println(new Knapsack().new Recursive().knapsack(W, wt, val, val.length));
        System.out.println(new Knapsack().new DP().knapsack(W, wt, val, val.length));
    }
}
