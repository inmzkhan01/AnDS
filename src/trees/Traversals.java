package trees;

import java.util.ArrayList;
import java.util.List;

public class Traversals {

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

    private static int[] toArray(List<Integer> list) {
        int[] inorder = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            inorder[i] = list.get(i);
        }
        return inorder;
    }

}
