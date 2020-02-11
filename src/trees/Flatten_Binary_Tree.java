package trees;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class Flatten_Binary_Tree {

    public static void flatten(TreeNode root) {

        if(null == root)
            return;

        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                curr.left = curr.right;
                curr.right = null;
            } else {
                TreeNode leaf = curr.left;
                while (leaf.right != null) {
                    leaf = leaf.right;
                }

                while (leaf.left != null) {
                    leaf = leaf.left;
                }
                leaf.left = curr.right;
                curr.right = null;
            }
            curr = curr.left;
        }

        curr = root;

        while (curr.left != null) {
            curr.right = curr.left;
            curr.left = null;
            curr = curr.right;
        }

    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.binarySearchTree();
        System.out.println(root);
        flatten(root);
    }
}
