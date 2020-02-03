package trees;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        if (this.left == null && this.right == null) {
            return String.valueOf(val);
        } else {
            String root = String.valueOf(val), left = "null", right = "null";
            if (this.left != null) {
                left = this.left.toString();
            }
            if (this.right != null) {
                right = this.right.toString();
            }
            return root + " (" + left + ", " + right + ")";
        }
    }

    /**
     *            100
     *          /     \
     *        50       150
     *      /   \     /   \
     *    30    80   120  180
     *                   /  \
     *                 160  200
     *
     * @return
     */
    public static TreeNode binarySearchTree() {


        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(50);
        root.right = new TreeNode(150);

        root.left.left = new TreeNode(30);
        root.left.right = new TreeNode(80);

        root.right.left = new TreeNode(120);
        root.right.right = new TreeNode(180);

        root.right.right.left = new TreeNode(160);
        root.right.right.right = new TreeNode(200);

        return root;
    }

    public static void main(String[] args) {
        binarySearchTree();
    }

}
