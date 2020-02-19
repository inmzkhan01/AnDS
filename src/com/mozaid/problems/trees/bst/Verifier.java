package com.mozaid.problems.trees.bst;

import com.mozaid.problems.trees.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
public class Verifier {

    /**
     * Using Inorder traversal. (Efficient)
     */
    static class RecursiveInorder {

        private static TreeNode prev;

        public static boolean isValidBST(TreeNode root) {
            prev = null;
            return isBST(root);
        }

        private static boolean isBST(TreeNode x) {
            if (null == x) return true;

            if (!isBST(x.left))
                return false;

            if (prev != null && x.val <= prev.val)
                return false;

            prev = x;

            return isBST(x.right);
        }
    }

    static class IterativeInorder {

        public static boolean isValidBST(TreeNode root) {

            TreeNode x = root;

            Stack<TreeNode> stack = new Stack<>();
            TreeNode prev = new TreeNode(Integer.MIN_VALUE);

            while (!stack.isEmpty() || x != null) {
                while (x != null) {
                    stack.push(x);
                    x = x.left;
                }

                //If next element in Inorder Traversal is smaller than previous then it's not BST.
                x = stack.pop();

                if (x.val <= prev.val)
                    return false;

                prev = x;
                x = x.right;
            }
            return true;
        }
    }

    /**
     * Code copied From Leetcode
     * Using lower and upper bound. (Efficient)
     */
    static class Recursive {
        public static boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        private static boolean isValidBST(TreeNode x, Integer lower, Integer upper) {
            if (null == x) return true;

            if (null != lower && x.val < lower) return false;
            if (null != upper && x.val > upper) return false;

            if (!isValidBST(x.left, lower, x.val)) return false;
            if (!isValidBST(x.right, x.val, upper)) return false;

            return true;
        }
    }

    /**
     * Using min and max. (Efficient)
     */
    static class RecursiveMinMaxEasy {

        public int isValidBST(TreeNode A) {
            return isValidBST(A, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private int isValidBST(TreeNode node, int min, int max) {
            if(node == null)
                return 1;

            if(node.val > min && node.val < max)
                return isValidBST(node.left, min, node.val) & isValidBST(node.right, node.val, max);

            return 0;
        }
    }

    /**
     * Same as previous but condition changed.
     * Using min and max. (Efficient)
     * Doesn't pass only Leetcode two's test cases.
     */
    static class RecursiveMinMax {

        /* can give min and max value according to your code or can write a function to find min and max value of tree. */
        public static boolean isValidBST(TreeNode root) {
            return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private static boolean isBST(TreeNode x, int min, int max) {
            /* an empty tree is BST */
            if (x == null) return true;

            /* false if this node violates the min/max constraints */
            if (x.val < min || x.val > max)
                return false;

            /* otherwise check the subtrees recursively tightening the min/max constraints */
            // Allow only distinct values (plus one and minus one are important to rule out BST with duplicate)
            return isBST(x.left, min, x.val - 1) && isBST(x.right, x.val + 1, max);
        }
    }



    /**
     * Using left subtree's min and right subtree's max. (Very Bad),
     * This doesn't support duplicate tree with duplicate keys.
     * In below case, it will return true although it should return false;
     * 1
     * / \
     * 1   1
     * JFR!!! (Just for reference)
     */
    static class RecursiveSubtreesMinMax {

        public static boolean isValidBST(TreeNode root) {
            if (root == null) return true;

            if (root.left != null && max(root.left).val > root.val)
                return false;

            if (root.right != null && min(root.right).val < root.val)
                return false;

            if (!isValidBST(root.left) || !isValidBST(root.right))
                return false;

            return true;
        }

        private static TreeNode max(TreeNode x) {
            while (x.right != null)
                x = x.right;
            return x;
        }

        private static TreeNode min(TreeNode x) {
            while (x.left != null)
                x = x.left;
            return x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);

        TreeNode right = new TreeNode(4);
        root.right = right;

        right.left = new TreeNode(3);
        right.right = new TreeNode(6);

        System.out.println(RecursiveInorder.isValidBST(root));

        TreeNode root2 = new TreeNode(-2147483648);
        root2.left = new TreeNode(-2147483648);
        System.out.println(RecursiveInorder.isValidBST(root2));

    }
}
