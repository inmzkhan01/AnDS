package com.kmozaid.problems.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {

    public static boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private static boolean isSymmetric(TreeNode x, TreeNode h) {
        if (x == null && h == null)
            return true;

        if (x != null && h != null && x.val == h.val) {
            return isSymmetric(x.left, h.right) && isSymmetric(x.right, h.left);
        }

        return false;
    }

    public static boolean isSymmetricIterative(TreeNode root) {
        if (null == root) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode x = queue.poll();
            TreeNode h = queue.poll();

            if (x == null && h == null)
                continue;

            if (x == null || h == null)
                return false;

            if (x.val != h.val)
                return false;

            queue.offer(x.left);
            queue.offer(h.right);
            queue.offer(x.right);
            queue.offer(h.left);
        }

        return true;
    }
}
