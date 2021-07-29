package com.kmozaid.problems.trees.bst;

import com.kmozaid.problems.trees.TreeNode;

/**
 * https://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
 */
public class Create_From_Sorted_Array {

    public static TreeNode BST(int[] a) {
        return BST(a, 0, a.length - 1);
    }

    private static TreeNode BST(int[] a, int lo, int hi) {
        if (lo > hi) return null;

        int mid = lo + (hi - lo) / 2;

        TreeNode parent = new TreeNode(a[mid]);
        parent.left = BST(a, lo, mid - 1);
        parent.right = BST(a, mid + 1, hi);
        return parent;
    }

    public static void main(String[] args) {
        TreeNode bst = BST(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("Is BST " + Verifier.RecursiveMinMax.isValidBST(bst));
    }
}
