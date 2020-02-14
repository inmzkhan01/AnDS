package tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Find shortest unique prefix to represent each word in the list.
 *
 * Example:
 *
 * Input: [zebra, dog, duck, dove]
 * Output: {z, dog, du, dov}
 * where we can see that
 * zebra = z
 * dog = dog
 * duck = du
 * dove = dov
 */
public class Shortest_Unique_Prefix {

    public ArrayList<String> prefix(ArrayList<String> words) {
        TrieSET trie = new TrieSET();

        for (String word : words) {
            trie.add(word);
        }

        ArrayList<String> prefixes = new ArrayList<>();
        for (String word : words) {
            prefixes.add(trie.prefix(word));
        }

        return prefixes;
    }

    private class TrieSET {

        TrieNode root = new TrieNode();

        private class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            int count;
        }

        void add(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                node = node.children.computeIfAbsent(ch, thisChar -> new TrieNode());
                node.count++;
            }
        }

        String prefix(String word) {
            StringBuilder prefix = new StringBuilder();

            TrieNode node = root;

            for (char ch : word.toCharArray()) {
                prefix.append(ch);
                node = node.children.get(ch);
                if (node.count == 1)
                    return prefix.toString();
            }

            return null;
        }
    }

}
