package com.kmozaid.problems.linkedlist;

/**
 * Given a linked list, and an input n, write a function that returns the nth-to-last element of the linked list.
 * list = 1 -> 2 -> 3 -> 4 -> 5 -> null
 * nthToLast( list , 0 ) = 5
 * nthToLast( list , 1 ) = 4
 * nthToLast( list , 4 ) = 1
 * nthToLast( list , 5 ) = null
 */
public class Return_Nth_to_the_Last_Element {

    public static ListNode nthToLast(ListNode node, int n) {
        ListNode curr = node;
        ListNode follower = node;

        // Forward curr by n. If you reach the end of the list then it is
        // shorter than n, so you can't possible have an nth-to-last node.
        for (int i = 0; i < n; i++) {
            if (curr == null) return null;
            curr = curr.next;
        }

        // If length is exactly n, the nth-to-last node would be null.
        if (curr == null) return null;

        // Move both nodes forward in unison until curr is at the end of the list.
        while (curr.next != null) {
            curr = curr.next;
            follower = follower.next;
        }

        return follower;
    }
}
