package algorithms.tries;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an ordered set of strings alphabet.
 */
public class TrieSET {
    private static final int R = 26;        // alphabet

    private Node root;      // root of trie

    // R-way trie node
    private static class Node {
        private Node[] next = new Node[R];
        private boolean isString;
    }

    public boolean contains(String key) {
        Node x = get(root, key, 0);
        if (x == null) return false;
        return x.isString;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;

        if (d == key.length()) return x;

        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void add(String key) {
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null)
            x = new Node();

        if (d == key.length()) {
            x.isString = true;
        } else {
            char c = key.charAt(d);
            x.next[c] = add(x.next[c], key, d + 1);
        }
        return x;
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            x.isString = false;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.isString) return x;
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

    private void collect(Node x, String prefix, List<String> results) {
        if (x == null) return;
        if (x.isString)
            results.add(prefix);
        for (char c = 0; c < R; c++) {
            collect(x.next[c], prefix + c, results);
        }
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        Node x = get(root, prefix, 0);
        collect(x, prefix, results);
        return results;
    }

    /**
     * Returns the string in the set that is the longest prefix of query string or null if no such string.
     */
    public String longestPrefixOf(String query) {
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        return query.substring(0, length);
    }

    // returns the length of the longest string key in the subtrie rooted at x that is a prefix of the query string,
    // assuming the first d character match and we have already found a prefix match of length length
    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.isString) length = d;
        if (d == query.length())
            return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d + 1, length);
    }

}
