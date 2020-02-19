package com.mozaid.problems.arrays;

import java.util.Stack;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class Trapping_Rain_Water {

    static class BruteForce {

        public static int trap(int[] height) {
            int water = 0;

            for (int i = 0; i < height.length; i++) {
                int maxLeft = 0, maxRight = 0;

                for (int j = i; j >= 0; j--) {
                    maxLeft = Math.max(maxLeft, height[j]);
                }

                for (int j = i; j < height.length; j++) {
                    maxRight = Math.max(maxRight, height[j]);
                }

                water += (Math.min(maxLeft, maxRight) - height[i]);
            }

            return water;
        }

    }

    static class DP {

        public static int trap(int[] height) {
            int n = height.length;

            int[] maxLeft = new int[n];
            maxLeft[0] = height[0];
            for (int i = 1; i < n; i++) {
                maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
            }

            int[] maxRight = new int[n];
            maxRight[n - 1] = height[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                maxRight[i] = Math.max(maxRight[i + 1], height[i]);
            }

            int water = 0;
            for (int i = 0; i < height.length; i++) {
                water += Math.min(maxLeft[i], maxRight[i]) - height[i];
            }

            return water;
        }

    }

    static class TwoPointer {
        public static int trap(int[] height) {
            if (height.length == 0)
                return 0;
            int water = 0;
            int i = 0;
            int j = height.length - 1;
            int leftMax = 0;
            int rightMax = 0;

            while (i < j) {
                if (height[i] < height[j]) {
                    if (height[i] >= leftMax)
                        leftMax = height[i];
                    else
                        water += leftMax - height[i];
                    i++;
                } else {
                    if (height[j] >= rightMax)
                        rightMax = height[j];
                    else
                        water += rightMax - height[j];
                    j--;
                }
            }

            return water;
        }
    }

    static class StackSolution {

        public static int trap(int[] height) {
            int water = 0;
            Stack<Integer> stack = new Stack<>();
            int i = 0;
            while (i < height.length) {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int top = stack.pop();
                    if (stack.isEmpty())
                        break;
                    int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                    int distance = i - stack.peek() - 1;    // Distance between the bars.
                    water += boundedHeight * distance;
                }
                stack.push(i++);
            }

            return water;
        }

    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Brute Force: " + BruteForce.trap(height));
        System.out.println("DP: " + DP.trap(height));
        System.out.println("Two Pointer: " + TwoPointer.trap(height));
        System.out.println("Stack: " + StackSolution.trap(height));
    }


}
