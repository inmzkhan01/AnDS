package com.kmozaid.problems.strings;

import java.util.*;

/**
 * Given a list of com.mozaid.problems.strings, write a function to get the kth most frequently occurring string.
 * <p>
 * eg.
 * kthMostFrequent({ "a" , "b" , "c" , "a" , "b" , "a" }, 0 ) = "a"
 * kthMostFrequent({ "a" , "b" , "c" , "a" , "b" , "a" }, 1 ) = "b"
 * kthMostFrequent({ "a" , "b" , "c" , "a" , "b" , "a" }, 2 ) = "c"
 * kthMostFrequent({ "a" , "b" , "c" , "a" , "b" , "a" }, 3 ) = null
 */
public class Kth_Most_Frequent_String {

    public static String kthMostFrequent(String[] strings, int k) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (String s : strings) {
            map.compute(s, (key, val) -> val == null ? 1 : val + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        if (list.size() > k) return list.get(k).getKey();
        return null;
    }
}
