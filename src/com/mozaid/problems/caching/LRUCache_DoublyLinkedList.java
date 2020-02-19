package com.mozaid.problems.caching;

import java.util.HashMap;
import java.util.Map;

public class LRUCache_DoublyLinkedList {

    private static class Node {
        Node next;
        Node prev;
        int key;
        int val;

        Node(int k, int v) {
            key = k;
            val = v;
        }
    }

    private Map<Integer, Node> cache;
    private int capacity;

    private Node head;
    private Node tail;

    public LRUCache_DoublyLinkedList(int capacity) {
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;

        this.capacity = capacity;
        cache = new HashMap<>();
    }

    public void set(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            node.val = value;
            moveToHead(node);
        } else {
            if (cache.size() == capacity) {
                node = removeFromTail();
                cache.remove(node.key);
            }

            node = new Node(key, value);
            cache.put(key, node);
            addToHead(node);
        }
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null)
            return -1;
        int val = node.val;
        moveToHead(node);
        return val;
    }

    // Insert node between head and it's next.
    private void addToHead(Node node) {
        Node headNext = head.next;

        head.next = node;
        node.prev = head;

        node.next = headNext;
        headNext.prev = node;
    }

    // Delete last node using tail pointer.
    private Node removeFromTail() {
        Node node = tail.prev;

        Node prev = node.prev;
        prev.next = tail;
        tail.prev = prev;

        return node;
    }

    private void moveToHead(Node node) {

        // Delete Node
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;

        addToHead(node);
    }
}
