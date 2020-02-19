package com.mozaid.problems.trees.bst;

import com.mozaid.problems.trees.Traversals;
import com.mozaid.problems.trees.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

public class Construct_BST_From_Postorder {

    public static TreeNode bstFromPostorder(int[] postorder) {
        int n = postorder.length;
        return construct(postorder, n - 1, 0, new AtomicInteger(n - 1));
    }

    private static TreeNode construct(int[] postorder, int start, int end, AtomicInteger pIndex) {
        if (end > start)
            return null;

        int currPostIndex = pIndex.getAndDecrement();

        TreeNode node = new TreeNode(postorder[currPostIndex]);

        if (start == end)
            return node;

        int index = -1;

        for (int i = start; i >= end; i--) {
            if (postorder[i] < node.val) {
                index = i;
                break;
            }
        }

        node.right = construct(postorder, (currPostIndex - 1), (index == -1 ? start : (index + 1)), pIndex);

        node.left = construct(postorder, (index == -1 ? (end - 1) : index), end, pIndex);

        return node;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.binarySearchTree();
        TreeNode root2 = bstFromPostorder(Traversals.postorder(root));
        System.out.println(root);
        System.out.println(root2);
    }
}
