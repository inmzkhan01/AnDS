package trees;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Construct_Binary_Tree_From_Postorder_and_Inorder {

    static class RecursiveI {

        public static TreeNode buildTree(int[] postorder, int[] inorder) {
            int n = inorder.length;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(postorder, new AtomicInteger(n - 1), 0, n - 1, map);
        }

        private static TreeNode buildTree(int[] postorder, AtomicInteger pIndex, int start, int end, Map<Integer, Integer> map) {
            if (start > end) {
                return null;
            }

            TreeNode node = new TreeNode(postorder[pIndex.getAndDecrement()]);

            int index = map.get(node.val);

            // Recursively build right first.
            node.right = buildTree(postorder, pIndex, index + 1, end, map);

            node.left = buildTree(postorder, pIndex, start, index - 1, map);

            return node;
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
