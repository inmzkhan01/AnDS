package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class Traversal_Inorder {

    public static List<Integer> inOrderTraversalUsingStack(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode x = root;

        while (!stack.isEmpty() || x != null) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
            x = stack.pop();
            nodes.add(x.val);
            x = x.right;
        }
        return nodes;
    }

    public static List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();

        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                nodes.add(curr.val);
                curr = curr.right;
            } else {
                // Find the inorder predecessor of curr.
                TreeNode pre = curr.left;
                while (pre.right != null && pre.right != curr)
                    pre = pre.right;

                if (pre.right == null) {
                    // Make curr as the right child of its inorder predecessor
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    // Revert the changes made in the 'if' part to restore the original tree
                    pre.right = null;
                    nodes.add(curr.val); // Add parent.
                    curr = curr.right;
                }

            }
        }

        return nodes;
    }




    public static void main(String[] args) {
        /**
         *  1
         *   \
         *   2
         *  /
         *  3
         */
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        right.left = new TreeNode(3);
        root.right = right;
        System.out.println(inorderTraversalMorris(root));
        System.out.println(inOrderTraversalUsingStack(root));
    }

}
