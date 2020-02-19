package com.mozaid.problems.arrays;

import java.util.Deque;

public class Stack_Reverse {

    public static void reverse(Deque<Integer> stack) {
        if (stack.isEmpty()) return;

        int x = stack.pop();

        reverse(stack);
        insertAtBottom(stack, x);
    }

    private static void insertAtBottom(Deque<Integer> stack, int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            return;
        }

        int y = stack.pop();

        insertAtBottom(stack, x);
        stack.push(y);
    }

}
