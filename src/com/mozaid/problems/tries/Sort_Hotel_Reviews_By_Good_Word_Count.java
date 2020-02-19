package com.mozaid.problems.tries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of reviews provided by the customers for different hotels and a string containing “Good Words”,
 * you need to sort the reviews in descending order according to their “Goodness Value” (Higher goodness value first).
 * We define the “Goodness Value” of a string as the number of “Good Words” in that string.
 * <p>
 * Note: Sorting should be stable. If review i and review j have the same “Goodness Value” then their original order would be preserved.
 * <p>
 * Input:
 * S = "cool_ice_wifi"
 * R = ["water_is_cool", "cold_ice_drink", "cool_wifi_speed"]
 * <p>
 * Output:
 * ans = [2, 0, 1]
 * Here, sorted reviews are ["cool_wifi_speed", "water_is_cool", "cold_ice_drink"]
 * <p>
 * https://www.interviewbit.com/problems/hotel-reviews/
 */
public class Sort_Hotel_Reviews_By_Good_Word_Count {

    public ArrayList<Integer> solve(String goodWords, ArrayList<String> reviews) {
        TrieSET trie = new TrieSET();

        for (String goodWord : goodWords.split("_")) {
            trie.add(goodWord);
        }

        List<Pair> indexCountPairList = new ArrayList<>();

        for (int i = 0; i < reviews.size(); i++) {
            String[] words = reviews.get(i).split("_");
            int count = 0;
            for (String word : words) {
                if (trie.contains(word))
                    count++;
            }
            indexCountPairList.add(new Pair(i, count));
        }

        Collections.sort(indexCountPairList);

        ArrayList<Integer> res = new ArrayList<>();
        for (Pair pair : indexCountPairList) {
            res.add(pair.index);
        }
        return res;
    }

    private class Pair implements Comparable<Pair> {
        int index;
        int count;

        Pair(int i, int c) {
            index = i;
            count = c;
        }

        public int compareTo(Pair other) {
            if (count == other.count)
                return index - other.index;
            else
                return other.count - count;
        }
    }

    private class TrieSET {
        private final int R = 256;  // alphabet

        private TrieNode root;      // root of trie

        // R-way trie node
        private class TrieNode {
            private TrieNode[] next = new TrieNode[R];
            private boolean isString;
        }

        public boolean contains(String key) {
            TrieNode x = get(root, key, 0);
            if (x == null) return false;
            return x.isString;
        }

        private TrieNode get(TrieNode x, String key, int d) {
            if (x == null) return null;

            if (d == key.length()) return x;

            char c = key.charAt(d);
            return get(x.next[c], key, d + 1);
        }

        public void add(String key) {
            root = add(root, key, 0);
        }

        private TrieNode add(TrieNode x, String key, int d) {
            if (x == null)
                x = new TrieNode();

            if (d == key.length()) {
                x.isString = true;
            } else {
                char c = key.charAt(d);
                x.next[c] = add(x.next[c], key, d + 1);
            }
            return x;
        }

    }


}
