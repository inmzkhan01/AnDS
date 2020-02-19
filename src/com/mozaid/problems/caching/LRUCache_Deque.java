package com.mozaid.problems.caching;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache_Deque {

    private Map<Integer, Integer> data;
    private Deque<Integer> keys;
    private int capacity;

    public LRUCache_Deque(int capacity) {
        this.capacity = capacity;
        this.data = new HashMap<>();
        this.keys = new LinkedList<>();
    }

    public int get(int key) {
        Integer value = data.get(key);
        if(null != value) {
            keys.remove(key);
            keys.addLast(key);
            return value;
        }
        return  -1;
    }

    public void put(int key, int value) {

        boolean isKeyPresent = keys.contains(key);

        if(keys.size() == capacity && !isKeyPresent) {
            data.remove(keys.pollFirst());
        }

        if(isKeyPresent) {
            keys.remove(key);
        }

        data.put(key, value);
        keys.addLast(key);
    }

    public static void main(String[] args) {
        LRUCache_Deque cache = new LRUCache_Deque(2);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
