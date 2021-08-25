package com.kmozaid.problems.trees;

import java.util.ArrayList;

/**
 * https://www.interviewbit.com/problems/inorder-traversal-of-cartesian-tree/
 * <p>
 * Given an inorder traversal of a cartesian tree, construct the tree.
 * <p>
 * Cartesian tree : is a heap ordered binary tree, where the root is greater than all the elements in the subtree.
 */
public class Construct_Cartesian_Tree {

    public TreeNode buildTree(ArrayList<Integer> A) {
        return buildTree(A, 0, A.size() - 1);
    }

    private TreeNode buildTree(ArrayList<Integer> inorder, int start, int end) {
        if (start > end)
            return null;

        int maxIndex = start;
        int maxValue = inorder.get(maxIndex);
        for (int i = start; i <= end; i++) {
            if (inorder.get(i) > maxValue) {
                maxIndex = i;
                maxValue = inorder.get(i);
            }
        }

        TreeNode node = new TreeNode(inorder.get(maxIndex));
        node.left = buildTree(inorder, start, maxIndex - 1);
        node.right = buildTree(inorder, maxIndex + 1, end);
        return node;
    }
}
