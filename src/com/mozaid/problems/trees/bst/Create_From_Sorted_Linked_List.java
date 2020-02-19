package com.mozaid.problems.trees.bst;

import com.mozaid.problems.trees.TreeNode;

/**
 * https://www.geeksforgeeks.org/sorted-linked-list-to-balanced-bst/
 */
public class Create_From_Sorted_Linked_List {

    static class LinkedNode {
        int val;
        LinkedNode next;

        LinkedNode(int val) {
            this.val = val;
        }

        LinkedNode(int val, LinkedNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static LinkedNode head;

    public static TreeNode BST(LinkedNode linkedNode) {
        head = linkedNode;
        int count = countNode(head);
        return BST(count);
    }

    private static TreeNode BST(int n) {
        if (n <= 0) return null;

        TreeNode left = BST(n / 2);

        TreeNode parent = new TreeNode(head.val);
        parent.left = left;

        head = head.next;

        parent.right = BST(n - n / 2 - 1);
        return parent;
    }

    private static int countNode(LinkedNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public static void main(String[] args) {
        LinkedNode linkedNode = new LinkedNode(1);
        linkedNode.next = new LinkedNode(2, new LinkedNode(3, new LinkedNode(4, new LinkedNode(5, new LinkedNode(6)))));
        TreeNode BST = BST(linkedNode);
        boolean isBST = Verifier.RecursiveInorder.isValidBST(BST);
        System.out.println("BST " + isBST);
    }
}
