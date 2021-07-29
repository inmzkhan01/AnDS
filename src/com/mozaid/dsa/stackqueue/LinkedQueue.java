package com.mozaid.dsa.stackqueue;

public class LinkedQueue<Item> {

    private Node first, last;

    private class Node {
        Item value;
        Node next;

        Node(Item value) {
            this.value = value;
        }
    }

    public LinkedQueue() {
        first = last = null;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node(item);

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public Item dequeue() {
        Node item = first;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item.value;
    }

    public boolean isEmpty() {
        return first == null;
    }

}
