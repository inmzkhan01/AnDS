package com.kmozaid.problems.trees.bst;

import com.kmozaid.problems.trees.Traversals;
import com.kmozaid.problems.trees.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

public class Construct_BST_From_Preorder {

    public static TreeNode bstFromPreorder(int[] preorder) {
        return construct(preorder, 0, preorder.length - 1, new AtomicInteger(0));
    }

    private static TreeNode construct(int[] preorder, int start, int end, AtomicInteger pIndex) {
        if (start > end)
            return null;

        int currPreIndex = pIndex.getAndIncrement();

        TreeNode node = new TreeNode(preorder[currPreIndex]);

        if (start == end)
            return node;

        int index = -1;

        for (int i = start; i <= end; i++) {
            if (preorder[i] > node.val) {
                index = i;
                break;
            }
        }

        node.left = construct(preorder, (currPreIndex + 1), (index == -1 ? end : index - 1), pIndex);

        node.right = construct(preorder, (index == -1 ? (end + 1) : index), end, pIndex);

        return node;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.binarySearchTree();
        TreeNode root2 = bstFromPreorder(Traversals.preorder(root));
        System.out.println(root);
        System.out.println(root2);
    }
}
