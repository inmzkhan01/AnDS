package com.mozaid.problems.trees.bst;

import com.mozaid.problems.trees.TreeNode;

import java.util.Stack;

public class Kth_Smallest {

    public TreeNode kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode x = root;

        while (x != null || !stack.isEmpty()) {

            while (x != null) {
                stack.push(x);
                x = x.left;
            }

            x = stack.pop();
            k--;

            if (k == 0) {
                return x;
            }

            x = x.right;
        }

        return null;
    }

    public static void main(String[] args) {
        TreeNode bst = TreeNode.binarySearchTree();

        Kth_Smallest finder = new Kth_Smallest();

        System.out.println(finder.kthSmallest(bst, 6).val);
    }
}
