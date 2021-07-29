package com.kmozaid.problems.trees;

import java.util.concurrent.atomic.AtomicInteger;

public class Construct_Binary_Tree_From_Postorder_and_Inorder {

    static class RecursiveI {

        public static TreeNode buildTree(int[] postorder, int[] inorder) {
            int n = inorder.length;
            return buildTree(postorder, inorder, 0, n - 1, new AtomicInteger(n - 1));
        }

        private static TreeNode buildTree(int[] postorder, int[] inorder, int start, int end, AtomicInteger pIndex) {
            if (start > end) {
                return null;
            }

            TreeNode node = new TreeNode(postorder[pIndex.getAndDecrement()]);

            if (start == end)
                return node;

            int index = search(node.val, inorder, start, end);

            // Recursively build right first.
            node.right = buildTree(postorder, inorder, index + 1, end, pIndex);

            node.left = buildTree(postorder, inorder, start, index - 1, pIndex);

            return node;
        }

        private static int search(int num, int[] inorder, int start, int end) {
            int i;
            for (i = start; i <= end; i++) {
                if (inorder[i] == num)
                    return i;
            }
            return i;
        }

    }

    static class RecursiveII {

        public static TreeNode buildTree(int[] postorder, int[] inorder) {
            int inStart = 0;
            int inEnd = inorder.length - 1;
            int postStart = 0;
            int postEnd = postorder.length - 1;

            return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
        }

        private static TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                                          int[] postorder, int postStart, int postEnd) {

            if (inStart > inEnd || postStart > postEnd)
                return null;

            TreeNode root = new TreeNode(postorder[postEnd]);

            int k = 0;
            for (int i = 0; i < inorder.length; i++) {
                if (inorder[i] == root.val) {
                    k = i;
                    break;
                }
            }

            root.left = buildTree(inorder, inStart, k - 1, postorder, postStart, postStart + k - (inStart + 1));

            root.right = buildTree(inorder, k + 1, inEnd, postorder, postStart + k - inStart, postEnd - 1);

            return root;
        }

    }


    public static void main(String[] args) {
        TreeNode root = TreeNode.binarySearchTree();
        TreeNode root2 = RecursiveI.buildTree(Traversals.postorder(root), Traversals.inorder(root));
        System.out.println(root);
        System.out.println(root2);
    }
}
