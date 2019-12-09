package trees;

public class BinaryTreeNode {

    public int key;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        if (this.left == null && this.right == null) {
            return String.valueOf(key);
        } else {
            String root = String.valueOf(key), left = "null", right = "null";
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
    public static BinaryTreeNode binarySearchTree() {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(50);
        root.right = new BinaryTreeNode(150);

        root.left.left = new BinaryTreeNode(30);
        root.left.right = new BinaryTreeNode(80);

        root.right.left = new BinaryTreeNode(120);
        root.right.right = new BinaryTreeNode(180);

        root.right.right.left = new BinaryTreeNode(160);
        root.right.right.right = new BinaryTreeNode(200);

        return root;
    }

}
