package com.kmozaid.problems.linkedlist;

public class Reverse_LinkedList {

    static class PrintReverse {
        public static void print(ListNode listNode) {
            if (null == listNode) return;
            print(listNode.next);
            System.out.print(listNode.val + " ");
        }
    }

    static class RecursiveSolutionI {

        public static ListNode reverseList(ListNode head) {
            return reverseList(head, null);
        }

        private static ListNode reverseList(ListNode node, ListNode prev) {
            if (node == null)
                return prev;

            ListNode next = node.next;
            node.next = prev;

            return reverseList(next, node);
        }
    }

    static class RecursiveSolutionII {

        public static ListNode reverseList(ListNode head) {
            if (head == null || head.next == null)
                return head;
            ListNode newHead = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }
    }

    static class IterativeSolution {

        public static ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }

    }

    public static void main(String[] args) {
        ListNode reversedList = RecursiveSolutionI.reverseList(ListNode.linkedList());
        print(reversedList);

        reversedList = RecursiveSolutionII.reverseList(ListNode.linkedList());
        print(reversedList);

        reversedList = IterativeSolution.reverseList(ListNode.linkedList());
        print(reversedList);
    }

    static void print(ListNode node) {
        System.out.println("\n=======");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }


}
