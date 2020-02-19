package com.mozaid.problems.arrays;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stack_Sort {

    public static class NonRecursiveStackSort {

        public static Deque<Integer> sortStack(Deque<Integer> input) {
            Deque<Integer> tmpStack = new ArrayDeque<>();
            while (!input.isEmpty()) {
                int tmp = input.pop();
                // while temporary stack is not empty and top of stack is greater than temp
                while (!tmpStack.isEmpty() && tmpStack.peek() > tmp) {
                    input.push(tmpStack.pop());
                }
                tmpStack.push(tmp);
            }
            return tmpStack;
        }
    }

    public static class RecursiveStackSort {

        public static void sortStack(Deque<Integer> stack) {
            if (stack.isEmpty()) return;
            int x = stack.pop();

            // Sort remaining stack
            sortStack(stack);

            // Push the top item back in sorted stack
            sortedInsert(stack, x);
        }

        static void sortedInsert(Deque<Integer> stack, int x) {
            // Base case: Either stack is empty or newly inserted item is greater than top (more than all existing)
            if (stack.isEmpty() || x > stack.peek()) {
                stack.push(x);
                return;
            }

            // If top is greater, remove the top item and recur
            int temp = stack.pop();
            sortedInsert(stack, x);

            // Put back the top item removed earlier
            stack.push(temp);
        }
    }


    public static void main(String args[]) {
        Deque<Integer> input = new ArrayDeque<>();
        input.add(34);
        input.add(3);
        input.add(31);
        input.add(98);
        input.add(92);
        input.add(23);

        // This is the temporary stack
        Deque<Integer> tmpStack = NonRecursiveStackSort.sortStack(input);

        System.out.println("Sorted numbers are:");
        while (!tmpStack.isEmpty()) {
            System.out.print(tmpStack.pop() + " ");
        }
    }
}
