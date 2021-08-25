package com.kmozaid.dsa.tries;

import java.util.ArrayList;
import java.util.List;

/**
 * Trie implementation.
 */
public class TrieST<Value> {
    private static final int R = 256;    // alphabets

    private TrieNode root = new TrieNode();     // root of trie

    // R-way trie node
    private static class TrieNode {
        Object val;
        TrieNode[] next = new TrieNode[R];
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        TrieNode x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    private TrieNode get(TrieNode x, String key, int d) {
        if (x == null) return null;

        if (d == key.length()) return x;

        char c = key.charAt(d);
        return get(x.next[c], key, (d + 1));
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private TrieNode put(TrieNode x, String key, Value val, int d) {
        if (x == null)
            x = new TrieNode();

        if (d == key.length()) {
            x.val = val;
            return x;
        }

        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, (d + 1));
        return x;
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private TrieNode delete(TrieNode x, String key, int d) {
        if (x == null) return null;

        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }

    public Iterable<String> keys() {
        List<String> keys = new ArrayList<>();
        collect(root, "", keys);
        return keys;
    }

    private void collect(TrieNode x, String prefix, List<String> keys) {
        if (x == null) return;
        if (x.val != null)
            keys.add(prefix);
        for (int c = 0; c < R; c++) {
            collect(x.next[c], prefix + c, keys);
        }
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        List<String> keys = new ArrayList<>();
        TrieNode x = get(root, prefix, 0);
        collect(x, "", keys);
        return keys;
    }

    public String longestPrefixOf(String query) {
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        else return query.substring(0, length);
    }

    // returns the length of the longest string key in the subtrie rooted at x that is a prefix of the query string,
    // assuming the first d character match and we have already found a prefix match of given length (-1 if no such match)
    private int longestPrefixOf(TrieNode x, String query, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }

}
