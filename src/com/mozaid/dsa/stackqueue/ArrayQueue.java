package com.mozaid.dsa.stackqueue;

public class ArrayQueue<Item> {

    private Item items[];
    private int first;
    private int last;
    private int size;
    private int capacity;

    public ArrayQueue(int N) {
        this.items = (Item[]) new Object[N];
        this.capacity = N;
    }

    public void enqueue(Item item) {
        if (isFull())
            throw new IllegalStateException("Queue is full.");

        items[last] = item;
        last = (last + 1) % capacity;
        size++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty.");

        Item item = items[first];
        items[first] = null;
        first = (first + 1) % capacity;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue(3);
        queue.enqueue(4);
        queue.enqueue(2);
        queue.enqueue(5);
        queue.dequeue();
        queue.enqueue(8);
        System.out.println("Is Full: " + queue.isFull());
        System.out.println("Size: " + queue.size());
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
