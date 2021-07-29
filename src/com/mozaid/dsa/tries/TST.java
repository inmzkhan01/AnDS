package com.mozaid.dsa.tries;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an symbol table of key-value pairs, with string keys and generic values.
 * This implementation uses a ternary search trie.
 */
public class TST<Value> {

    private TrieNode<Value> root;   // root of TST

    private static class TrieNode<Value> {
        private char c;                        // character
        private TrieNode<Value> left, mid, right;  // left, middle, and right subtries
        private Value val;                     // value associated with string
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        TrieNode<Value> x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    // return subtrie corresponding to given key
    private TrieNode<Value> get(TrieNode<Value> x, String key, int d) {
        if (x == null) return null;

        char c = key.charAt(d);
        if (c < x.c)                        return get(x.left, key, d);
        else if (c > x.c)                   return get(x.right, key, d);
        else if (d < key.length() - 1)      return get(x.mid, key, d + 1);
        else                                return x;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private TrieNode<Value> put(TrieNode<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new TrieNode<>();
            x.c = c;
        }
        if (c < x.c)                        x.left = put(x.left, key, val, d);
        else if (c > x.c)                   x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)      x.mid = put(x.mid, key, val, d + 1);
        else                                x.val = val;
        return x;
    }

    public Iterable<String> keys() {
        List<String> list = new ArrayList<>();
        collect(root, new StringBuilder(), list);
        return list;
    }

    // all keys in subtrie rooted at x with given prefix
    private void collect(TrieNode<Value> x, StringBuilder prefix, List<String> list) {
        if (x == null) return;
        collect(x.left, prefix, list);
        if (x.val != null) list.add(prefix.toString() + x.c);
        collect(x.mid, prefix.append(x.c), list);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, list);
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        TrieNode<Value> x = get(root, prefix, 0);
        if (x == null) return list;
        if (x.val != null) list.add(prefix);
        collect(x.mid, new StringBuilder(prefix), list);
        return list;
    }

    // returns the length of the longest string key in the subtrie rooted at x that is a prefix of the query string,
    // assuming the first d character match and we have already found a prefix match of length length
    public String longestPrefixOf(String query) {
        if (query.length() == 0) return null;
        int length = 0;
        TrieNode<Value> x = root;
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

}
