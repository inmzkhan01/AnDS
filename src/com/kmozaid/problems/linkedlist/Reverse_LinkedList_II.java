package com.kmozaid.problems.linkedlist;

public class Reverse_LinkedList_II {

    static class MySolution {

        static ListNode thirdListHead;
        static ListNode secondListTail;

        public static ListNode reverseBetween(ListNode head, int m, int n) {
            if (m == n)
                return head;

            ListNode firstListTail = null;
            ListNode secondListHead = head;
            thirdListHead = null;

            int position = 1;
            while (position != m) {
                firstListTail = secondListHead;
                secondListHead = secondListHead.next;
                position++;
            }

            if (firstListTail != null) {
                firstListTail.next = null;
            }

            secondListTail = secondListHead;
            secondListHead = reverse(secondListHead, position, n);

            if (firstListTail != null) {
                firstListTail.next = secondListHead;
            }

            if (secondListTail != null) {
                secondListTail.next = thirdListHead;
            }

            if (firstListTail == null)
                return secondListHead;

            return head;
        }

        private static ListNode reverse(ListNode node, int position, int n) {
            if (node == null || node.next == null || position == n) {
                if (node != null)
                    thirdListHead = node.next;

                return node;
            }

            ListNode newHead = reverse(node.next, position + 1, n);
            node.next.next = node;
            node.next = null;
            return newHead;
        }

    }

    static class LeetcodeSolutionI {

        static ListNode left;
        static boolean stop;

        public static ListNode reverseBetween(ListNode head, int m, int n) {
            left = head;
            stop = false;
            recurseAndReverse(head, m, n);
            return head;
        }

        private static void recurseAndReverse(ListNode right, int m, int n) {
            if (n == 1)
                return;

            right = right.next;

            if (m > 1)
                left = left.next;

            recurseAndReverse(right, m - 1, n - 1);

            if (left == right || right.next == left)
                stop = true;

            if (!stop) {
                int tmp = left.val;
                left.val = right.val;
                right.val = tmp;

                left = left.next;
            }

        }
    }

    static class LeetcodeSolutionII {

        public static ListNode reverseBetween(ListNode head, int m, int n) {
            if (head == null) return null;

            ListNode prev = null;
            ListNode curr = head;
            while (m > 1) {
                prev = curr;
                curr = curr.next;
                m--;
                n--;
            }

            // Because curr will be tail when sublist is reversed. Save pointer to prev to attach reverse sublist.
            ListNode con = prev;
            ListNode tail = curr;

            // Reverse sublist.
            while (n > 0) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                n--;
            }

            if (con != null) {
                con.next = prev;
            } else {
                head = prev;
            }

            tail.next = curr;
            return head;
        }

    }

    static class ReverseFirstNElementsLinkedList {

        static ListNode successor = null;

        public static ListNode reverseN(ListNode head, int n) {
            if (n == 1) {
                successor = head.next;
                return head;
            }
            ListNode newHead = reverseN(head.next, n - 1);
            head.next.next = head;
            head.next = successor;
            return newHead;
        }

    }

    static class ReverseBetween {

        public static ListNode reverseBetween(ListNode head, int m, int n) {
            if (m == 1) {
                return reverseN(head, n);
            }
            head.next = reverseBetween(head.next, m - 1, n - 1);
            return head;
        }

        private static ListNode successor = null;

        private static ListNode reverseN(ListNode head, int n) {
            if (n == 1) {
                successor = head.next;
                return head;
            }
            ListNode newHead = reverseN(head.next, n - 1);
            head.next.next = head;
            head.next = successor;
            return newHead;
        }

    }


    public static void main(String[] args) {
        System.out.println(ListNode.linkedList());
        System.out.println(MySolution.reverseBetween(ListNode.linkedList(), 2, 4));
        System.out.println(LeetcodeSolutionI.reverseBetween(ListNode.linkedList(), 2, 4));
        System.out.println(LeetcodeSolutionII.reverseBetween(ListNode.linkedList(), 2, 4));
        System.out.println("-------------");
        System.out.println(ReverseFirstNElementsLinkedList.reverseN(ListNode.linkedList(), 3));
        System.out.println(ReverseBetween.reverseBetween(ListNode.linkedList(), 2, 4));
    }

}
