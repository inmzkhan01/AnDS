package com.mozaid.dsa.trees;

import java.util.NoSuchElementException;

public class RedBlackBST<K extends Comparable<K>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private K key;
        private boolean color;
        private Node left;
        private Node right;

        Node(K key, boolean color) {
            this.key = key;
            this.color = color;
        }

        @Override
        public String toString() {
            if (this.left == null && this.right == null) {
                return key + (this.color ? ":R" : ":B");
            } else {
                String root = key + (this.color ? ":R" : ":B"), left = "null", right = "null";
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

    public boolean isEmpty() {
        return root == null;
    }

    public K get(K key) {
        Node h = root;

        while (h != null) {
            int cmp = key.compareTo(h.key);

            if (cmp < 0)
                h = h.left;
            else if (cmp > 0)
                h = h.right;
            else
                return h.key;
        }

        return null;
    }

    public void add(K key) {
        root = add(root, key);
        root.color = BLACK;
    }

    private Node add(Node h, K key) {
        if (null == h)
            return new Node(key, RED);

        int cmp = key.compareTo(h.key);

        if (cmp < 0)
            h.left = add(h.left, key);
        else if (cmp > 0)
            h.right = add(h.right, key);
        else
            h.key = key;

        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);

        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);

        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        return h;
    }


    private boolean isRed(Node h) {
        if (null == h)
            return BLACK;

        return h.color == RED;
    }

    private Node rotateLeft(Node h) {
        // Need to return h' right.
        Node x = h.right;

        // After rotation, h will become x left, we need to move x.left to h.right to keep BST invariant.
        h.right = x.left;

        x.left = h;

        x.color = h.color;
        h.color = RED;

        return x;
    }

    private Node rotateRight(Node h) {
        // Need to return h' left
        Node x = h.left;

        // After rotation, h will become x' right, we need to move x.right' to h.left' to keep BST invariant.
        h.left = x.right;

        x.right = h;

        x.color = h.color;
        h.color = RED;

        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //*********** Below code is copied from edu.princeton.cs.algs4.RedBlackBST class ***********//
    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Find minimum.
     */
    public K min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    /**
     * Delete minimum.
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * Delete node.
     */
    public void delete(K key) {

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, K key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.right = deleteMin(h.right);
            } else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        return h;
    }

    public static void main(String[] args) {
        /*RedBlackBST<Character> bst = new RedBlackBST<>();
        bst.add('S');
        bst.add('E');
        bst.add('A');
        bst.add('R');
        bst.add('C');
        bst.add('H');
        bst.add('X');
        bst.add('M');
        bst.add('P');
        bst.add('L');*/

        RedBlackBST<Integer> intBST = new RedBlackBST<>();
        intBST.add(1);
        intBST.add(2);
        intBST.add(3);
        intBST.add(4);
        intBST.add(5);
        intBST.add(6);
        intBST.add(7);
        System.out.println(intBST.root);
    }

}
