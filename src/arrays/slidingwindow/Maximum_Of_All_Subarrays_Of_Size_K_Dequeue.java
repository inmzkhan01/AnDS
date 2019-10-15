package arrays.slidingwindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Maximum_Of_All_Subarrays_Of_Size_K_Dequeue {

    public static int[] maximumOfSubarrays(int[] arr, int k) {
        int n = arr.length;

        int[] result = new int[n-k+1];
        int j = 0;

        Deque<Integer> deque = new LinkedList<>();

        int i;
        for(i=0; i<k; i++) {
            while(!deque.isEmpty() && arr[deque.peek()] < arr[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }

        for(;i<n;i++) {
            result[j++] = arr[deque.peekFirst()];

            while(!deque.isEmpty() && arr[deque.peek()] < arr[i]) {
                deque.removeLast();
            }

            while(!deque.isEmpty() && deque.peekFirst() < i-k+1) {
                deque.removeFirst();
            }

            deque.addLast(arr[i]);
        }

        result[j] = arr[deque.pop()];

        return result;
    }

    public static void main(String[] args) {
        int a[] = { 4,3, 2, 1};
        int k = 3;
        System.out.println(0%2);
        System.out.println(1%2);

        System.out.println("Result: "+ Arrays.toString(maximumOfSubarrays(a,k)));
    }

}
