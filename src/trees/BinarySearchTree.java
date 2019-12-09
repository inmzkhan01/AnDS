package trees;


//BST.
public class BinarySearchTree {

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.binarySearchTree();

        inOrderTraversal(root);
        System.out.println();

        preOrderTraversal(root);
        System.out.println();

        postOrderTraversal(root);
        System.out.println();

        System.out.println(root);

        insert(20, root);
        insert(115, root);

        System.out.println(root);

        BinaryTreeNode searched = search(root, 150);
        System.out.println(searched);
    }

    /**
     * Left, Parent, Right
     *
     * @param root
     */
    static void inOrderTraversal(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.key + " ");
        inOrderTraversal(root.right);
    }

    /**
     * Parent, Left, Right
     *
     * @param root
     */
    static void preOrderTraversal(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.key + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * Left, Right, Parent
     *
     * @param root
     */
    static void postOrderTraversal(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.key + " ");
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
    static BinaryTreeNode insert(int key, BinaryTreeNode root) {
        if(root == null) {
            root = new BinaryTreeNode(key);
            return root;
        }

        if(key < root.key) {
            root.left = insert(key, root.left);
        } else if(key > root.key) {
            root.right = insert(key, root.right);
        }
        return root;

       /* NodeData node = findNode(root, key);
        if (key < node.key) {
            node.left = new NodeData(key);
            return node.left;
        } else {
            node.right = new NodeData(key);
            return node.right;
        }*/
    }

    static BinaryTreeNode findNode(BinaryTreeNode root, int key) {
        if (key < root.key && root.left != null) {
            return findNode(root.left, key);
        } else if (key > root.key && root.right != null) {
            return findNode(root.right, key);
        } else {
            return root;
        }
    }


    static BinaryTreeNode search(BinaryTreeNode root, int key) {
        if (null == root || root.key == key) {
            return root;
        }

        if (key < root.key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

}
