package arrays;

import java.util.Stack;

//https://www.geeksforgeeks.org/find-a-sorted-subsequence-of-size-3-in-linear-time/
public class Find_A_Sorted_Subsequence_of_Size_3 {

    public static void main(String[] args) {
        int[] arr = {12, 11, 10, 5, 6, 2, 30};
        subsequence(arr);
    }

    static void subsequence(int[] arr) {
        Stack<Integer> stack = new Stack();

        stack.push(arr[0]);

        for(int i=1; i<arr.length; i++) {
            if(stack.peek()>arr[i]) {
                while(!stack.isEmpty() && stack.peek() > arr[i]) {
                    stack.pop();
                }
            }


            stack.push(arr[i]);
        }
    }
}
