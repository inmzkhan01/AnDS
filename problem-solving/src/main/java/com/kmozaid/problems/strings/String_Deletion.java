package com.kmozaid.problems.strings;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given a string and a dictionary HashSet, write a function to determine
 * the minimum number of characters to delete to make a word.
 * eg.
 * dictionary: [ "a" , "aa" , "aaa" ]
 * query: "abc"
 * output: 2
 */
public class String_Deletion {

    public static int delete(String query, HashSet dictionary) {
        Queue<String> queue = new LinkedList();
        Set<String> queueElements = new HashSet();

        queue.add(query);
        queueElements.add(query);

        while (!queue.isEmpty()) {
            String s = queue.remove();
            queueElements.remove(s);
            if (dictionary.contains(s)) return query.length() - s.length();

            for (int i = 0; i < s.length(); i++) {
                String sub = s.substring(0, i) + s.substring(i + 1);
                if (sub.length() > 0 && !queueElements.contains(sub)) {
                    queue.add(sub);
                    queueElements.add(sub);
                }
            }
        }
        return -1;
    }
}
