package com.kmozaid.problems.trees;

/**
 * Preorder: 50, 25, 12, 37, 30, 40, 75, 62, 60, 70, 87
 *  Postorder: 12, 30, 40, 37, 25, 60, 70, 62, 87, 75, 50
 *  1. The first element in the preorder traversal is the root of the tree.
 *     So, here 50 will be the root of the tree.
 *  2. We will find the index of element next to 50 i.e 25 in the postorder traversal.The index found is 4. Let this index is denoted by 'pos'.
 *  3. All the elements to the left of this index and element at this index( i.e from 0 to 4 index) will be in the left subtree of 50.
 *  4. And all the elements to the right of this index ( from 6 to 10) will be in the right subtree of 50.
 *
 *  Now, we will divide preorder and postorder array in two parts.
 *  One is for the left subtree and other is for the right subtree.
 *
 * Let presi: starting index for preorder array
 *     preei: ending index for preorder array
 *     postsi: starting index of postorder array
 *     postei: ending index of postorder array
 *     clc: Number of elements in the left subtree
 *
 * Clearly, clc = pos - postsi + 1;
 *
 * For left subtree:
 * Preorder array: from index presi + 1, presi + clc
 * Postorder array: from index postsi, pos
 *
 * For right subtree:
 * Preorder array: from index presi + clc + 1, preei
 * Postorder array: from index pos + 1, postei -1
 *
 *
 *  Using the above com.mozaid.problems.arrays, all the steps are recursively repeated.
 */
public class Construct_Binary_Tree_From_Preorder_and_Postorder {

    public static TreeNode constructFromPrePost(int[] pre, int[] post) {
        return construct(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    private static TreeNode construct(int[] pre, int presi, int preei, int[] post, int postsi, int postei) {
        if (presi > preei) {
            return null;
        }

        TreeNode node = new TreeNode(pre[presi]);

        if (presi == preei) {
            return node;
        }

        //Searching pre[presi + 1] in postorder array
        int pos = -1;
        for (int i = postsi; i <= postei; i++) {
            if (post[i] == pre[presi + 1]) {
                pos = i;
                break;
            }
        }

        //Number of elements in left subtree
        int clc = pos - postsi + 1;

        //Left subtree
        node.left = construct(pre, presi + 1, presi + clc, post, postsi, pos);

        //Right subtree
        node.right = construct(pre, presi + clc + 1, preei, post, pos + 1, postei - 1);

        return node;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.binarySearchTree();
        TreeNode root2 = constructFromPrePost(Traversals.preorder(root), Traversals.postorder(root));
        System.out.println(root);
        System.out.println(root2);
    }

}
