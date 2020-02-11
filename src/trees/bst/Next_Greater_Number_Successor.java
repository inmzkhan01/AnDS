package trees.bst;

import trees.TreeNode;

import java.util.Stack;

public class Next_Greater_Number_Successor {

    class SimpleSolution {
        public TreeNode getSuccessor(TreeNode root, int data) {
            if (root == null) return null;

            TreeNode temp = null;
            while (root != null) {
                if (root.val > data) {
                    temp = root;
                    root = root.left;
                } else
                    root = root.right;
            }
            return temp;
        }
    }

    class Fastest {
        public TreeNode getSuccessor(TreeNode a, int b) {
            if (a == null)
                return null;

            TreeNode x = a;
            TreeNode ancestor = null;
            while (x != null) {
                if (x.val == b)
                    break;
                else if (x.val > b) {
                    ancestor = x;
                    x = x.left;
                } else
                    x = x.right;
            }
            if (x.right != null) {
                x = x.right;
                while (x.left != null)
                    x = x.left;
                return x;
            }
            return ancestor;
        }
    }

    class Editorial {
        public TreeNode getSuccessor(TreeNode a, int b) {
            Stack<TreeNode> st = new Stack<>();
            TreeNode cur = a;
            while (cur != null || !st.isEmpty()) {
                while (cur != null) {
                    st.push(cur);
                    cur = cur.left;
                }

                TreeNode temp = st.pop();
                if (temp.val > b) {
                    return temp;
                }
                cur = temp.right;
            }
            return null;
        }
    }

    public TreeNode getSuccessor(TreeNode root, int key) {

        TreeNode current = find(root, key);

        if (current == null)
            return null;

        if (current.right != null) {
            return findMin(current.right);
        } else {
            TreeNode successor = null;
            TreeNode ancestor = root;
            while (ancestor != current) {
                if (current.val < ancestor.val) {
                    successor = ancestor;
                    ancestor = ancestor.left;
                } else
                    ancestor = ancestor.right;
            }
            return successor;
        }
    }

    private TreeNode find(TreeNode node, int key) {
        while (node != null) {
            if (key < node.val)
                node = node.left;
            else if (key > node.val)
                node = node.right;
            else
                return node;
        }
        return null;
    }

    private TreeNode findMin(TreeNode node) {
        if (node == null)
            return null;

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
