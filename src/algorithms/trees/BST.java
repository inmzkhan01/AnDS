package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/kth-smallest-element-in-bst-using-o1-extra-space/
 * <p>
 * https://www.geeksforgeeks.org/kth-largest-element-in-bst-when-modification-to-bst-is-not-allowed/
 */
public class BST<K extends Comparable<K>> {

    private Node root;

    private class Node {
        private K key;
        private Node left;
        private Node right;

        Node(K key) {
            this.key = key;
        }
    }

    public K get(K key) {
        Node x = get(root, key);
        return null == x ? null : x.key;
    }

    private Node get(Node x, K key) {
        if (null == x) return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x;
    }

    public void add(K key) {
        root = add(root, key);
    }

    private Node add(Node x, K key) {
        if (null == x)
            return new Node(key);

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = add(x.left, key);
        else if (cmp > 0)
            x.right = add(x.right, key);
        else
            x.key = key;

        return x;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (null == x) return x;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            if (null == x.left) return x.right;
            if (null == x.right) return x.left;

            Node t = x;

            // Replace with successor.
            x = min(t.right);

            // Delete the successor and fix x's right.
            x.right = deleteMin(t.right);

            // Restore x's left.
            x.left = t.left;

        }
        return x;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;

        return min(x.left);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;

        x.left = deleteMin(x.left);
        return x;
    }

    public List<K> inorder() {
        List<K> keys = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        Node x = root;

        while (x != null || !stack.isEmpty()) {

            while (x != null) {
                stack.push(x);
                x = x.left;
            }

            x = stack.pop();
            keys.add(x.key);
            x = x.right;
        }
        return keys;
    }


    public static void main(String[] args) {
        BST<Character> bst = new BST<>();
        bst.add('H');
        bst.add('D');
        bst.add('R');
        bst.add('A');
        bst.add('Z');
        bst.add('E');
        bst.add('M');
        bst.add('U');

        System.out.println("Inorder: " + bst.inorder());
    }


}
