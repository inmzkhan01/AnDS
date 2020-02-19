package com.mozaid.problems.trees.bst;

import com.mozaid.problems.trees.TreeNode;

public class Print_Keys_in_Given_Range {

    public void printKeys(TreeNode node, int k1, int k2) {
        if (null == node) return;

        if (node.val > k1)
            printKeys(node.left, k1, k2);

        if (node.val >= k1 && node.val <= k2)
            System.out.print(node.val + " ");

        if (node.val < k2)
            printKeys(node.right, k1, k2);
    }

    public static void main(String[] args) {
        TreeNode bst = TreeNode.binarySearchTree();

        Print_Keys_in_Given_Range keysFinder = new Print_Keys_in_Given_Range();

        keysFinder.printKeys(bst, 10, 300);
        System.out.println();
        keysFinder.printKeys(bst, 80, 125);
    }
}
