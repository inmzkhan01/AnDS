package com.mozaid.problems.caching;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache_LinkedHashMap {

    LinkedHashMap<Integer, Integer> cache;

    public LRUCache_LinkedHashMap(int capacity) {
        cache = new LinkedHashMap<Integer, Integer>(capacity, 100.0f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (cache.containsKey(key))
            return cache.get(key);
        return -1;
    }

    public void set(int key, int value) {
        cache.put(key, value);
    }

}
