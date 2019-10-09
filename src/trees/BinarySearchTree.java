package trees;

import trees.Tree.Node;

//BST.
public class BinarySearchTree {

    public static void main(String[] args) {
        Node root = Tree.binarySearchTree();

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

        Node searched = search(root,150);
        System.out.println(searched);
    }

    /**
     * Left, Parent, Right
     *
     * @param root
     */
    static void inOrderTraversal(Node root) {
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
    static void preOrderTraversal(Node root) {
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
    static void postOrderTraversal(Node root) {
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
    static Node insert(int key, Node root) {
        if(root == null) {
            root = new Node(key);
            return root;
        }

        if(key < root.key) {
            root.left = insert(key, root.left);
        } else if(key > root.key) {
            root.right = insert(key, root.right);
        }
        return root;

       /* Node node = findNode(root, key);
        if (key < node.key) {
            node.left = new Node(key);
            return node.left;
        } else {
            node.right = new Node(key);
            return node.right;
        }*/
    }

    static Node findNode(Node root, int key) {
        if (key < root.key && root.left != null) {
            return findNode(root.left, key);
        } else if (key > root.key && root.right != null) {
            return findNode(root.right, key);
        } else {
            return root;
        }
    }


    static Node search(Node root, int key) {
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
