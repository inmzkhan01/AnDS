package trees;

import java.util.LinkedList;
import java.util.Queue;

public class MinMaxDepth {

    /**
     * Max Depth.
     * The maximum depth of a binary tree is the number of nodes along the longest path from the root Person down to the farthest leaf Person.
     * <p>
     * NOTE : The path has to end on a leaf Person.
     */
    public int maxDepth(TreeNode node) {
        return height(node);
    }

    /**
     * Calculate Height
     */
    public int height(TreeNode node) {
        if (node == null)
            return 0;

        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Min Depth.
     * The minimum depth is the number of nodes along the shortest path from the root Person down to the nearest leaf Person.
     * <p>
     * NOTE : The path has to end on a leaf Person.
     */
    public int minDepth(TreeNode node) {
        if (node == null)
            return 0;

        if (node.left == null && node.right == null)
            return 1;

        if (node.left == null) {
            return 1 + minDepth(node.right);
        }

        if (node.right == null) {
            return 1 + minDepth(node.left);
        }

        return 1 + Math.min(minDepth(node.left), minDepth(node.right));
    }

    class BFS {
        public int minDepth(TreeNode root) {
            if (root == null)
                return 0;

            int depth = 1;

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                // for each level
                for (int i = 0; i < q.size(); i++) {
                    TreeNode node = q.poll();
                    if (node.left == null && node.right == null) {
                        return depth;
                    }
                    if (node.left != null) {
                        q.offer(node.left);
                    }
                    if (node.right != null) {
                        q.offer(node.right);
                    }
                }
                depth++;
            }
            return depth;
        }
    }


}
