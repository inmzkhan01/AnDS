package trees;

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode node) {
        if (node == null) return null;

        TreeNode left = invertTree(node.left);
        TreeNode right = invertTree(node.right);
        node.left = right;
        node.right = left;
        return node;
    }

    public TreeNode invertTree2(TreeNode node) {
        if (node == null) return null;

        TreeNode l = node.left;
        TreeNode r = node.right;
        node.left = invertTree2(r);
        node.right = invertTree2(l);
        return node;
    }

    public TreeNode invertTree3(TreeNode node) {
        if (node == null) return null;

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        invertTree3(node.left);
        invertTree3(node.right);
        return node;
    }

    public TreeNode invertTree4(TreeNode node) {
        if (node == null) return null;

        invertTree4(node.left);
        invertTree4(node.right);

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        return node;
    }

}
