package trees.bst;

import trees.TreeNode;

public class Lowest_Common_Ancestor {

    public TreeNode LCA(TreeNode root, int a, int b) {
        if (root == null) return null;

        if (a < root.val && b < root.val)
            return LCA(root.left, a, b);

        if (a > root.val && b > root.val)
            return LCA(root.right, a, b);

        return root;
    }

    public TreeNode LCAIterative(TreeNode root, int a, int b) {
        TreeNode x = root;

        while (x != null) {
            if (a < root.val && b < root.val)
                x = x.left;
            else if (a > root.val && b > root.val)
                x = x.right;
            else
                break;
        }

        return x;
    }
}
