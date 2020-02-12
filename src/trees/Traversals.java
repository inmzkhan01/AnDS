package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Traversals {

    /**
     * Inorder Recursive.
     */
    public static int[] inorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return toArray(list);
    }

    private static void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    /**
     * Inorder Iterative.
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            inorder.add(node.val);
            node = node.right;
        }
        return inorder;
    }

    /**
     * Preorder Recursive.
     */
    public static int[] preorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return toArray(list);
    }


    private static void preorder(TreeNode node, List<Integer> list) {
        if (node == null) return;

        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }

    /**
     * Preorder Iterative.
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return preorder;
    }

    /**
     * Postorder Iterative.
     */
    public static int[] postorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return toArray(list);
    }

    private static void postorder(TreeNode node, List<Integer> list) {
        if (node == null) return;

        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }

    /**
     * Postorder Iterative.
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            postorder.add(node.val);

            if (node.left != null)
                stack.push(node.left);

            if (node.right != null)
                stack.push(node.right);
        }
        Collections.reverse(postorder);
        return postorder;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode left = null;
        TreeNode right = null;
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.left == null && node.right == null) {
                postorder.add(node.val);
            } else {
                if (node.left != null) {
                    left = node.left;
                    node.left = null;
                }
                if (node.right != null) {
                    right = node.right;
                    node.right = null;
                }

                stack.push(node);

                if (right != null) {
                    stack.push(right);
                    right = null;
                }
                if (left != null) {
                    stack.push(left);
                    left = null;
                }
            }
        }
        return postorder;
    }

    private static int[] toArray(List<Integer> list) {
        int[] inorder = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            inorder[i] = list.get(i);
        }
        return inorder;
    }

}
