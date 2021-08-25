package com.kmozaid.problems.trees;

public class Tree_to_Doubly_Linked_List {

    static TreeNode head;
    static TreeNode tail;

    /**
     * Return head of the double linked list.
     */
    public static TreeNode treeToList(TreeNode root) {
        head = null;
        tail = null;
        buildDLL(root);
        return head;
    }

    private static void buildDLL(TreeNode node) {
        if (node == null)
            return;

        buildDLL(node.left);

        // Set head pointer to leftmost node.
        if (head == null)
            head = node;

        // Point tail's right to current node.
        if (tail != null)
            tail.right = node;

        // Point node's left to tail.
        node.left = tail;

        // Current node is now tail.
        tail = node;

        buildDLL(node.right);
    }


    public static void main(String[] args) {
        treeToList(TreeNode.binarySearchTree());

        TreeNode x = head;
        while (x != null) {
            System.out.print(x.val + " ");
            x = x.right;
        }
        System.out.println();

        x = tail;
        while (x != null) {
            System.out.print(x.val + " ");
            x = x.left;
        }
    }

}
