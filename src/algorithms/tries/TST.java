package algorithms.tries;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an symbol table of key-value pairs, with string keys and generic values.
 * This implementation uses a ternary search trie.
 */
public class TST<Value> {

    private int n;              // size
    private Node<Value> root;   // root of TST

    private static class Node<Value> {
        private char c;                        // character
        private Node<Value> left, mid, right;  // left, middle, and right subtries
        private Value val;                     // value associated with string
    }

    public int size() {
        return n;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        Node<Value> x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    // return subtrie corresponding to given key
    private Node<Value> get(Node<Value> x, String key, int d) {
        if (x == null) return null;

        char c = key.charAt(d);
        if (c < x.c)                        return get(x.left, key, d);
        else if (c > x.c)                   return get(x.right, key, d);
        else if (d < key.length() - 1)      return get(x.mid, key, d + 1);
        else                                return x;
    }

    public void put(String key, Value val) {
        if (!contains(key)) n++;
        root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<>();
            x.c = c;
        }
        if (c < x.c)                        x.left = put(x.left, key, val, d);
        else if (c > x.c)                   x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)      x.mid = put(x.mid, key, val, d + 1);
        else                                x.val = val;
        return x;
    }

    public String longestPrefixOf(String query) {
        if (query.length() == 0) return null;
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else {
                i++;
                if (x.val != null) length = i;
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    public Iterable<String> keys() {
        List<String> list = new ArrayList<>();
        collect(root, new StringBuilder(), list);
        return list;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) return list;
        if (x.val != null) list.add(prefix);
        collect(x.mid, new StringBuilder(prefix), list);
        return list;
    }

    // all keys in subtrie rooted at x with given prefix
    private void collect(Node<Value> x, StringBuilder prefix, List<String> list) {
        if (x == null) return;
        collect(x.left, prefix, list);
        if (x.val != null) list.add(prefix.toString() + x.c);
        collect(x.mid, prefix.append(x.c), list);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, list);
    }

    public Iterable<String> keysThatMatch(String pattern) {
        List<String> list = new ArrayList<>();
        collect(root, new StringBuilder(), 0, pattern, list);
        return list;
    }

    private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, List<String> list) {
        if (x == null) return;
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) collect(x.left, prefix, i, pattern, list);
        if (c == '.' || c == x.c) {
            if (i == pattern.length() - 1 && x.val != null) list.add(prefix.toString() + x.c);
            if (i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i + 1, pattern, list);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > x.c) collect(x.right, prefix, i, pattern, list);
    }


}
