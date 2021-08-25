package com.kmozaid.problems.slidingwindow;

import java.util.Arrays;
import java.util.Stack;

// https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k-using-stack-in-on-time
public class Maximum_Of_All_Subarrays_Of_Size_K_Stack {

    public static int[] maximumOfSubarrays(int[] a, int k) {

        int n = a.length;

        // max_upto array stores the index
        // upto which the maximum element is a[i]
        // i.e. max(a[i], a[i + 1], ... a[max_upto[i]]) = a[i]
        int[] max_upto = new int[n];

        int m = 0;
        int[] maxs = new int[n-k+1];

        // Update max_upto array similar to
        // finding next greater element
        Stack<Integer> s = new Stack<>();
        s.push(0);
        for (int i = 1; i < n; i++) {
            while (!s.empty() && a[s.peek()] < a[i]) {
                max_upto[s.peek()] = i - 1;
                s.pop();
            }
            s.push(i);
        }
        while (!s.empty()) {
            max_upto[s.peek()] = n - 1;
            s.pop();
        }
        System.out.println("max_upto: "+Arrays.toString(max_upto));

        int j = 0;
        for (int i = 0; i <= n - k; i++) {

            // j < i is to check whether the
            // jth element is outside the window
            while (j < i || max_upto[j] < i + k - 1) {
                j++;
            }
            maxs[m++] = a[j];
        }
        return maxs;
    }

    public static void main(String[] args) {
        int a[] = { 4,3, 2, 1};
        int k = 2;
        System.out.println("Result: "+Arrays.toString(maximumOfSubarrays(a,k)));
    }

}
