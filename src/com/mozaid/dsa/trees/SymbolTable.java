package com.mozaid.dsa.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * There are two other implementation of Symbol Tables. 1. UnorderedST : Can be easily implemented
 * using linked list. 2. OrderedST : Can be easy implemented using two sorted com.mozaid.problems.arrays, one for keys
 * and other for values
 * <p>
 * This is binary search tree based Symbol Table which out perform above two STs.
 */
public class SymbolTable<K extends Comparable<K>, V> {

    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left;
        private Node right;
        private int count;

        Node(K key, V val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }

        @Override
        public String toString() {
            if (this.left == null && this.right == null) {
                return String.valueOf(this.key);
            } else {
                String root = String.valueOf(this.key), left = "null", right = "null";
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

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val) {
        if (null == x)
            return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;

        x.count = 1 + size(x.left) + size(x.right);

        return x;
    }

    public V get(K key) {
        Node x = root;

        while (x != null) {
            int cmp = key.compareTo(x.key);

            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.val;
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (null == x)
            return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            if (null == x.left) return x.right;
            if (null == x.right) return x.left;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node min(Node x) {
        Node min = null;
        while (null != x) {
            min = x;
            x = x.left;
        }
        return min;
    }

    public boolean contains(K key) {
        return null != get(key);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.count;
    }

    public int size(K lo, K hi) {
        if (lo.compareTo(hi) > 0)
            return 0;

        if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    public K max() {
        if (root == null) return null;
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public K min() {
        if (root == null) return null;
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
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
     * Get the number of keys in the subtree less than given key.
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
     * Return key at given Rank.
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
     * Delete minimum element.
     */
    public void deleteMin() {
        if (null == root)
            return;
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;

        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Delete maximum element.
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
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

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
        SymbolTable<Character, Integer> st = new SymbolTable<>();

        System.out.println("Empty: " + st.isEmpty());

        st.put('H', 5);
        st.put('D', 9);
        st.put('R', 0);
        st.put('A', 3);
        st.put('G', 4);
        st.put('J', 6);
        st.put('Z', 2);
        st.put('E', 1);
        st.put('M', 7);
        st.put('C', 8);
        st.put('U', 10);


        System.out.println("E's Val: " + st.get('E'));

        System.out.println("Size: " + st.size());

        System.out.println("Contains: " + st.contains('C'));

        System.out.println("Min: " + st.min());

        System.out.println("Max: " + st.max());

        System.out.println("Keys:" + st.keys());

        System.out.println("floor(T): " + st.floor('T'));

        System.out.println("ceiling(T): " + st.ceiling('T'));

        System.out.println("rank(T): " + st.rank('H'));

        System.out.println("select(5): " + st.select(5));

        System.out.println("BST now: " + st.root);

        System.out.println("Deleting min...");
        st.deleteMin();

        System.out.println("Deleting max...");
        st.deleteMax();

        System.out.println("BST now: " + st.root);

        System.out.println("Adding A");
        st.put('A', 3);
        System.out.println("Adding Z");
        st.put('Z', 2);

        System.out.println("BST now: " + st.root);

        System.out.println("Deleting R");
        st.delete('R');

        System.out.println("BST now: " + st.root);

        System.out.println("Deleting D");
        st.delete('D');

        System.out.println("BST now: " + st.root);

        System.out.println("keys('C', 'M'): " + st.keys('C', 'M'));
    }

}
