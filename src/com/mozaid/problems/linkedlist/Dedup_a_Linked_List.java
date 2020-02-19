package com.mozaid.problems.linkedlist;

import java.util.HashSet;

/**
 * dedup( 1 -> 2 -> 3 -> 2 -> 1 ) = 1 -> 2 -> 3
 */
public class Dedup_a_Linked_List {

    public void removeDups(ListNode n) {
        while (n != null) {
            ListNode curr = n;
            while (curr.next != null) {
                if (curr.next.val == n.val) {
                    curr.next = curr.next.next;
                } else {
                    curr = curr.next;
                }
            }
            n = n.next;
        }
    }

    public void removeDups1(ListNode n) {
        HashSet<Integer> nodes = new HashSet<Integer>();
        ListNode prev = null;
        while (n != null) {
            if (nodes.contains(n.val)) {
                prev.next = n.next;
            } else {
                nodes.add(n.val);
                prev = n;
            }
            n = n.next;
        }
        prev.next = null;
    }

}
