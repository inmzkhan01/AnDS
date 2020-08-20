package com.mozaid.problems.linkedlist;

import java.util.Stack;

public class Palindrome_Linked_List {

    static class ReverseHalfListSolution {

        public static boolean isPalindrome(ListNode head) {
            if (head == null) return true;

            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode first = head;
            ListNode second = slow.next;
            slow.next = null;

            // Reverse second half
            ListNode prev = null;
            while (second != null) {
                ListNode next = second.next;
                second.next = prev;
                prev = second;
                second = next;
            }
            second = prev;

            while (first != null && second != null) {
                if (first.val != second.val)
                    return false;
                first = first.next;
                second = second.next;
            }
            return true;
        }

    }

    static class RecursiveSolution {

        static ListNode first = null;

        public static boolean isPalindrome(ListNode head) {
            first = head;
            ListNode node = head;
            return helper(node);
        }

        private static boolean helper(ListNode node) {
            if (node == null)
                return true;

            if (!helper(node.next))
                return false;

            boolean isPalindrome = node.val == first.val;
            first = first.next;
            return isPalindrome;
        }
    }

    static class StackSolution {

        public static boolean isPalindrome(ListNode head) {
            Stack<Integer> stack = new Stack<>();
            ListNode node = head;

            while (node != null) {
                stack.push(node.val);
                node = node.next;
            }

            node = head;
            while (!stack.isEmpty()) {
                if (stack.pop() != node.val)
                    return false;
                node = node.next;
            }
            return true;
        }
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(1))));
        System.out.println(RecursiveSolution.isPalindrome(head));
        System.out.println(StackSolution.isPalindrome(head));
        System.out.println(ReverseHalfListSolution.isPalindrome(head));
    }
}
