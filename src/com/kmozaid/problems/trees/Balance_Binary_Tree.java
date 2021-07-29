package com.kmozaid.problems.trees;

import java.util.LinkedList;
import java.util.Queue;

public class Balance_Binary_Tree {

    public int isBalanced(TreeNode node) {
        return balanced(node) == -1 ? 0 : 1;
    }

    private int balanced(TreeNode node) {
        if (node == null)
            return 0;

        int left = balanced(node.left);
        if (left < 0)
            return -1;

        int right = balanced(node.right);
        if (right < 0)
            return -1;

        if (Math.abs(left - right) > 1)
            return -1;

        return Math.max(left, right) + 1;
    }

    static class Editorial {

        public int isBalanced(TreeNode node) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(node);
            while (!q.isEmpty()) {
                node = q.poll();
                if (Math.abs(height(node.left) - height(node.right)) > 1) {
                    return 0;
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            return 1;
        }

        private int height(TreeNode node) {
            if (node == null) {
                return 0;
            }
            return Math.max(height(node.left), height(node.right)) + 1;
        }

    }

}
