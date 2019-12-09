package arrays;

import java.util.Stack;


//https://www.ideserve.co.in/learn/next-great-element-in-an-array
public class Stack_Next_greator_element_for_all_elements {

    public static void printNextGreater(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        System.out.println("Element\tNext-Greater");
        for(int i=1; i<arr.length; i++) {
            while(!stack.isEmpty() && stack.peek() < arr[i]) {
                System.out.println(stack.pop() + "\t\t"+ arr[i]);
            }
            stack.push(arr[i]);
        }

        while(!stack.isEmpty()) {
            System.out.println(stack.pop() + "\t\t"+ -1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {25,4,5,3,2,1,6};
        printNextGreater(arr);
    }
}
