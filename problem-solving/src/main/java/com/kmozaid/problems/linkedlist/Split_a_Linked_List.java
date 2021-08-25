package com.kmozaid.problems.linkedlist;

/**
 * Given a linked list, write a function to split the list into two equal halves
 *
 * divide( 1 -> 2 -> 3 -> 4 )       = 1 -> 2 , 3 -> 4
 * divide( 1 -> 2 -> 3 -> 4 -> 5 )  = 1 -> 2 -> 3 , 4 -> 5
 */
public class Split_a_Linked_List {

    public static ListNode split(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Second half
        ListNode head2 = slow.next;

        // First half
        ListNode head1 = head;
        slow.next = null;

        return head2;
    }

}
