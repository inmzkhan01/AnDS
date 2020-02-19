package com.mozaid.algorithms.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

    /**
     * Find key.
     */
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

    /**
     * Add key.
     */
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

    /**
     * Delete key
     */
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

    // Return deleted node right child.
    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;

        x.left = deleteMin(x.left);
        return x;
    }

    /**
     * Find maximum key.
     */
    public K max() {
        if (root == null) return null;
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    /**
     * Find minimum key.
     */
    public K min() {
        if (root == null) return null;
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    /**
     * Delete minimum key.
     */
    public void deleteMin() {
        if (null == root)
            return;
        root = deleteMin(root);
    }

    /**
     * Delete maximum key.
     */
    public void deleteMax() {
        if (null == root)
            return;
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (null == x.right)
            return x.left;

        x.right = deleteMax(x.right);
        return x;
    }

    /**
     * Key present.
     */
    public boolean contains(K key) {
        return null != get(key);
    }

    /**
     * Is BST empty?
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Get BST size.
     */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;

        return size(x.left) + size(x.right) + 1;
    }

    /**
     * Get BST size for key range
     */
    public int size(K lo, K hi) {
        if (lo.compareTo(hi) > 0)
            return 0;

        if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    /**
     * Get the number of keys in the subtree less than given key
     */
    public int rank(K key) {
        return rank(root, key);
    }

    private int rank(Node x, K key) {
        if (null == x)
            return 0;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            return rank(x.left, key);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(x.right, key);
        else
            return size(x.left);
    }

    /**
     * Return key at given Rank;
     */
    public K select(int k) {
        Node node = select(root, k);
        return null == node ? null : node.key;
    }

    private Node select(Node x, int k) {
        if (null == x) return null;

        int sz = size(x.left);

        if (k == sz)
            return x;
        else if (k < sz)
            return select(x.left, k);
        else
            return select(x.right, k - (sz + 1));
    }

    /**
     * Find the kth largest key in given BST
     */
    public K kthLargest(int k) {
        AtomicReference<K> ans = new AtomicReference<>();
        kthLargest(root, k, new AtomicInteger(0), ans);
        return ans.get();
    }

    public void kthLargest(Node node, int k, AtomicInteger count, AtomicReference<K> ans) {
        // Base cases, the second condition is important to avoid unnecessary recursive calls
        if (node == null || count.get() >= k)
            return;

        // Follow reverse inorder traversal so that the largest element is visited first
        kthLargest(node.right, k, count, ans);
        // Increment count of visited nodes and If count becomes k now, then this is the k'th largest
        if (count.incrementAndGet() == k) {
            ans.set(node.key);
            return;
        }
        kthLargest(node.left, k, count, ans);
    }

    /**
     * Find the kth smallest key in given BST using iterative reverse inorder.
     */
    public K kthLargest2(int k) {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }

            node = stack.pop();
            k--;
            if (k == 0)
                return node.key;

            node = node.left;
        }
        return null;
    }

    /**
     * Find the kth smallest key in given BST
     */
    public K kthSmallest(int k) {
        AtomicReference<K> ans = new AtomicReference<>();
        kthSmallest(root, k, new AtomicInteger(0), ans);
        return ans.get();
    }

    private void kthSmallest(Node node, int k, AtomicInteger count, AtomicReference<K> ans) {
        // Base cases, the second condition is important to avoid unnecessary recursive calls
        if (node == null || count.get() >= k)
            return;

        kthSmallest(node.left, k, count, ans);
        // Increment count of visited nodes and If count becomes k now, then this is the k'th smallest
        if (count.incrementAndGet() == k) {
            ans.set(node.key);
            return;
        }
        kthSmallest(node.right, k, count, ans);
    }

    /**
     * Find the kth smallest key in given BST using iterative inorder.
     */
    public K kthSmallest2(int k) {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            k--;
            if (k == 0)
                return node.key;

            node = node.right;
        }
        return null;
    }

    /**
     * Largest key less than or equal to key.
     */
    public K floor(K key) {
        Node floor = floor(root, key);
        return null == floor ? null : floor.key;
    }

    private Node floor(Node x, K key) {
        if (null == x)
            return null;

        int cmp = key.compareTo(x.key);

        if (cmp == 0)
            return x;

        if (cmp < 0)
            return floor(x.left, key);

        Node t = floor(x.right, key);

        if (null != t)
            return t;
        else
            return x;
    }

    /**
     * Smallest key greater than or equal to key.
     */
    public K ceiling(K key) {
        Node ceiling = ceiling(root, key);
        return null == ceiling ? null : ceiling.key;
    }

    private Node ceiling(Node x, K key) {
        if (null == x)
            return null;

        int cmp = key.compareTo(x.key);

        if (cmp == 0)
            return x;

        if (cmp > 0)
            return ceiling(x.right, key);

        Node t = ceiling(x.left, key);

        if (null != t)
            return t;
        else
            return x;
    }

    /**
     * Find key just greater than given key.
     */
    public K successor(K key) {
        return successor(root, key);
    }

    private K successor(Node x, K key) {
        K successor = null;
        while (x != null) {
            if (key.compareTo(x.key) < 0) {
                successor = x.key;
                x = x.left;
            } else
                x = x.right;
        }
        return successor;
    }

    /**
     * Find key just less than given key.
     */
    public K predecessor(K key) {
        return predecessor(root, key);
    }

    private K predecessor(Node x, K key) {
        K predecessor = null;
        while (x != null) {
            if (key.compareTo(x.key) > 0) {
                predecessor = x.key;
                x = x.right;
            } else
                x = x.left;
        }
        return predecessor;
    }

    /**
     * Get all keys.
     */
    public Iterable<K> keys() {
        List<K> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(Node x, List<K> list) {
        if (null == x)
            return;
        inorder(x.left, list);
        list.add(x.key);
        inorder(x.right, list);
    }

    /**
     * Get keys in given key range.
     */
    public Iterable<K> keys(K lo, K hi) {
        List<K> list = new ArrayList<>();
        inorder(root, lo, hi, list);
        return list;
    }

    private void inorder(Node x, K lo, K hi, List<K> list) {
        if (x == null)
            return;

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        // if lo is less than current node, go left
        if (cmplo < 0) inorder(x.left, lo, hi, list);

        // if current node within the range[lo, hi], add to the queue
        if (cmplo <= 0 && cmphi >= 0) list.add(x.key);

        // if hi is greater than current node, go right
        if (cmphi > 0) inorder(x.right, lo, hi, list);
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

        System.out.println("Inorder: " + bst.keys());
        System.out.println("Successor(E): " + bst.successor('F'));
        System.out.println("Predecessor(E): " + bst.predecessor('F'));
        System.out.println("kthLargest(4): " + bst.kthLargest(4));
        System.out.println("kthSmallest(4): " + bst.kthSmallest(4));
        System.out.println("kthLargest2(4): " + bst.kthLargest2(4));
        System.out.println("kthSmallest2(4): " + bst.kthSmallest2(4));
    }

}
