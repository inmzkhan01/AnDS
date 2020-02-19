package com.mozaid.problems.trees.bst;

import com.mozaid.problems.trees.TreeNode;

import java.util.Arrays;

public class Predecessor_Successor_for_a_Given_Key {

    public int[] predecessorSuccessor(TreeNode root, int key) {
        int[] preSuc = new int[2];
        preSuc[0] = preSuc[1] = -1;
        predecessorSuccessor(root, key, preSuc);
        return preSuc;
    }

    private void predecessorSuccessor(TreeNode node, int key, int[] preSuc) {
        if (null == node) return;

        if (key < node.val) {
            preSuc[1] = node.val;
            predecessorSuccessor(node.left, key, preSuc);
        } else if (key > node.val) {
            preSuc[0] = node.val;
            predecessorSuccessor(node.right, key, preSuc);
        } else {
            TreeNode x;
            if (node.left != null) {
                // Find Predecessor
                x = node.left;
                while (x.right != null)
                    x = x.right;

                preSuc[0] = x.val;
            }

            if (node.right != null) {
                // Find Successor
                x = node.right;
                while (x.left != null)
                    x = x.left;

                preSuc[1] = x.val;
            }
        }
    }

    public static void main(String[] args) {
        Predecessor_Successor_for_a_Given_Key predSucFinder = new Predecessor_Successor_for_a_Given_Key();

        TreeNode BST = TreeNode.binarySearchTree();
        System.out.println(Arrays.toString(predSucFinder.predecessorSuccessor(BST, 130)));
    }
}
