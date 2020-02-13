package trees.bst;

import trees.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Iterator {

    class LightIterator {

        private Stack<TreeNode> stack;

        public LightIterator(TreeNode root) {
            stack = new Stack<>();
            TreeNode node = root;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            if (!hasNext()) {
                return -1;
            }

            TreeNode node = stack.pop();
            int val = node.val;

            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            return val;
        }
    }

    class HeavyIterator {
        private List<Integer> nodes;
        private int n;
        private int i;

        public HeavyIterator(TreeNode root) {
            nodes = new ArrayList<>();
            inorder(root);
            n = nodes.size();
        }

        private void inorder(TreeNode node) {
            if (node == null)
                return;

            inorder(node.left);
            nodes.add(node.val);
            inorder(node.right);
        }

        public boolean hasNext() {
            return i < n;
        }

        public int next() {
            return nodes.get(i++);
        }
    }


}
