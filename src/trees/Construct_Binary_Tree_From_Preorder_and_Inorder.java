package trees;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Construct_Binary_Tree_From_Preorder_and_Inorder {

    static class RecursiveI {

        public static TreeNode buildTree(int[] preorder, int[] inorder) {
            int n = inorder.length;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(preorder, new AtomicInteger(0), 0, n - 1, map);
        }

        private static TreeNode buildTree(int[] preorder, AtomicInteger pIndex, int start, int end, Map<Integer, Integer> map) {
            if (start > end) {
                return null;
            }

            TreeNode node = new TreeNode(preorder[pIndex.getAndIncrement()]);

            int index = map.get(node.val);

            // Recursively build left first.
            node.left = buildTree(preorder, pIndex, start, index - 1, map);

            node.right = buildTree(preorder, pIndex, index + 1, end, map);

            return node;
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
    }

}
