package com.mozaid.problems.slidingwindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
public class Maximum_Of_All_Subarrays_Of_Size_K_Dequeue {

    public static int[] maximumOfSubarrays(int[] arr, int k) {
        int n = arr.length;

        int[] result = new int[n - k + 1];
        int j = 0;

        Deque<Integer> deque = new LinkedList<>();

        int i;
        for (i = 0; i < k; i++) {
            // For every element, the previous smaller elements are useless so remove them from Q
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }

        for (; i < n; i++) {
            result[j++] = arr[deque.peek()];

            // Remove the elements which are out of this window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.removeFirst();
            }

            // Remove all elements smaller than the currently being added element (remove useless elements)
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.removeLast();
            }

            deque.addLast(i);
        }

        result[j] = arr[deque.peek()];

        return result;
    }

    public static void main(String[] args) {
        int a[] = {4, 3, 2, 5};
        int k = 3;
        System.out.println("Result: " + Arrays.toString(maximumOfSubarrays(a, k)));
    }

}
