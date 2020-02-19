package com.mozaid.problems.trees;

import java.util.concurrent.atomic.AtomicInteger;

public class Construct_Binary_Tree_From_Preorder_and_Inorder {

    static class RecursiveI {

        public static TreeNode buildTree(int[] preorder, int[] inorder) {
            int n = inorder.length;
            return buildTree(preorder, inorder, 0, n - 1, new AtomicInteger(0));
        }

        private static TreeNode buildTree(int[] preorder, int[] inorder, int start, int end, AtomicInteger pIndex) {

            if (start > end) {
                return null;
            }

            TreeNode node = new TreeNode(preorder[pIndex.getAndIncrement()]);

            if (start == end)
                return node;

            int index = search(node.val, inorder, start, end);

            // Recursively build left first.
            node.left = buildTree(preorder, inorder, start, index - 1, pIndex);

            node.right = buildTree(preorder, inorder, index + 1, end, pIndex);

            return node;
        }

        private static int search(int num, int[] inorder, int start, int end) {
            int i;
            for (i = start; i < end; i++) {
                if (inorder[i] == num)
                    return i;
            }
            return i;
        }
    }

    static class RecursiveII {

        public static TreeNode buildTree(int[] preorder, int[] inorder) {
            int preStart = 0;
            int preEnd = preorder.length - 1;
            int inStart = 0;
            int inEnd = inorder.length - 1;

            return buildTree(preorder, preStart, preEnd, inorder, inStart, inEnd);
        }

        private static TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd || inStart > inEnd) {
                return null;
            }

            int val = preorder[preStart];
            TreeNode node = new TreeNode(val);

            //find parent element index from inorder
            int k = 0;
            for (int i = 0; i < inorder.length; i++) {
                if (val == inorder[i]) {
                    k = i;
                    break;
                }
            }

            node.left = buildTree(preorder, preStart + 1, preStart + (k - inStart), inorder, inStart, k - 1);

            node.right = buildTree(preorder, preStart + (k - inStart) + 1, preEnd, inorder, k + 1, inEnd);

            return node;
        }
    }


    public static void main(String[] args) {
        TreeNode root = TreeNode.binarySearchTree();
        TreeNode root2 = RecursiveI.buildTree(Traversals.preorder(root), Traversals.inorder(root));
        System.out.println(root);
        System.out.println(root2);

        System.out.println(RecursiveI.buildTree(new int[]{3, 2, 4, 1, 5}, new int[]{1, 2, 3, 4, 5}));
    }

}
