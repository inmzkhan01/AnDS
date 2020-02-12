package trees;

public class Tree_to_Doubly_Linked_List {

    public static class Node {
        int value;
        Node left;
        Node right;
    }

    public static class InorderTraversalMethod {

        static Node prev;

        public static Node treeToList(Node root) {
            prev = null;

            fixedLeftPtr(root);

            fixedRightPtr(root);

            return root;
        }

        private static void fixedLeftPtr(Node node) {
            if (node == null)
                return;

            fixedLeftPtr(node.left);
            node.left = prev;
            prev = node;
            fixedLeftPtr(node.right);
        }

        private static Node fixedRightPtr(Node node) {
            while (node.right != null) {
                node = node.right;
            }

            while (node != null && node.left != null) {
                Node left = node.left;
                left.right = node;

                // Backward
                node = node.left;
            }

            // he leftmost Person is head of linked list, return it
            return node;
        }
    }

}
