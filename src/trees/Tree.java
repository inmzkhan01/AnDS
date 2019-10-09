package trees;

public class Tree {

    public static class Node {
        public int key;
        public Node left;
        public Node right;

        public Node(int key) {
            this.key = key;
        }

        @Override
        public String toString() {
            if (this.left == null && this.right == null) {
                return String.valueOf(key);
            } else {
                String root = "null", left = "null", right = "null";
                root = String.valueOf(key);
                if (this.left != null) {
                    left = this.left.toString();
                }
                if (this.right != null) {
                    right = this.right.toString();
                }
                return root + " (" + left + ", " + right + ")";
            }
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
    public static Node binarySearchTree() {
        Node root = new Node(100);
        root.left = new Node(50);
        root.right = new Node(150);

        root.left.left = new Node(30);
        root.left.right = new Node(80);

        root.right.left = new Node(120);
        root.right.right = new Node(180);

        root.right.right.left = new Node(160);
        root.right.right.right = new Node(200);

        return root;
    }
}
