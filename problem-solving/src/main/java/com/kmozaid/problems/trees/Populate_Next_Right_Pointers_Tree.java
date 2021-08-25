package com.kmozaid.problems.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.interviewbit.com/problems/populate-next-right-pointers-tree/
 *
 * Solution is similar to Level Order Traversal
 */
public class Populate_Next_Right_Pointers_Tree {

    /**
     * Definition for binary tree with next pointer.
     */
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeLinkNode prev = null;
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeLinkNode node = queue.poll();

                if (prev != null)
                    prev.next = node;

                prev = node;

                if (node.left != null)
                    queue.offer(node.left);

                if (node.right != null)
                    queue.offer(node.right);
            }
        }
    }

}
