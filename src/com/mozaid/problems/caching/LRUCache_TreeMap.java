package com.mozaid.problems.caching;

import java.util.TreeMap;

public class LRUCache_TreeMap {

    private static int t;

    private static final class Node {
        int key, val, time;

        Node(int key, int val, int time) {
            this.key = key;
            this.val = val;
            this.time = time;
        }
    }

    private TreeMap<Integer, Node> keyMap;
    private TreeMap<Integer, Node> timeMap;
    private final int capacity;

    public LRUCache_TreeMap(int capacity) {
        keyMap = new TreeMap<>();
        timeMap = new TreeMap<>();
        this.capacity = capacity;
        t = 0;
    }

    public void set(int key, int value) {
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            updateTime(node);
            node.val = value;
        } else {
            if (keyMap.size() == capacity) {
                Node node = timeMap.firstEntry().getValue();
                timeMap.remove(node.time);
                keyMap.remove(node.key);
            }

            Node node = new Node(key, value, t++);
            keyMap.put(node.key, node);
            timeMap.put(node.time, node);
        }
    }

    public int get(int key) {
        if (!keyMap.containsKey(key)) return -1;
        Node node = keyMap.get(key);
        updateTime(node);
        return node.val;
    }

    private void updateTime(Node node) {
        timeMap.remove(node.time);
        timeMap.put(node.time = t++, node);
    }

}
