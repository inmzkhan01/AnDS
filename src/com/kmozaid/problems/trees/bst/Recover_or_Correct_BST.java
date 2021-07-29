package com.kmozaid.problems.trees.bst;

import com.kmozaid.problems.trees.TreeNode;

public class Recover_or_Correct_BST {

    static class ThreePointersInorderMethod {

        static TreeNode first, second;
        static TreeNode prev;

        public static TreeNode recoverTree(TreeNode root) {
            first = second = prev = null;

            inorder(root);

            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
            return root;
        }

        private static void inorder(TreeNode node) {
            if (node == null)
                return;

            inorder(node.left);

            if (prev != null && prev.val > node.val) {
                if (first == null) {
                    first = prev;
                    second = node;
                } else {
                    second = node;
                }
            }

            prev = node;

            inorder(node.right);
        }

    }

    /**
     *
     */
    static class MorrisInorderMethod {

        public static TreeNode recoverTree(TreeNode root) {
            TreeNode first = null, second = null;
            TreeNode prev = null;

            TreeNode node = root;

            while (node != null) {
                if (node.left != null) {
                    // connect threading for node
                    TreeNode tmp = node.left;
                    while (tmp.right != null && tmp.right != node) {
                        tmp = tmp.right;
                    }

                    // the threading already exists
                    if (tmp.right != null) {
                        tmp.right = null;

                        if (prev != null && prev.val > node.val) {
                            if (first == null) {
                                first = prev;
                                second = node;
                            } else {
                                second = node;
                            }
                        }
                        prev = node;

                        node = node.right;
                    } else {
                        // construct the threading
                        tmp.right = node;
                        node = node.left;
                    }
                } else {
                    if (prev != null && prev.val > node.val) {
                        if (first == null) {
                            first = prev;
                            second = node;
                        } else {
                            second = node;
                        }
                    }
                    prev = node;

                    node = node.right;
                }
            }

            if (first != null && second != null) {
                int tmp = first.val;
                first.val = second.val;
                second.val = tmp;
            }

            return root;
        }

    }


}
