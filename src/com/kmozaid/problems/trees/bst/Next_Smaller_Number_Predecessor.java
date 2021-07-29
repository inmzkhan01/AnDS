package com.kmozaid.problems.trees.bst;

import com.kmozaid.problems.trees.TreeNode;

public class Next_Smaller_Number_Predecessor {

    class SimpleSolution {
        public TreeNode getPredecessor(TreeNode root, int data) {
            if (root == null) return null;

            TreeNode temp = null;
            while (root != null) {
                if (root.val < data) {
                    temp = root;
                    root = root.right;
                } else
                    root = root.left;
            }
            return temp;
        }
    }
}
