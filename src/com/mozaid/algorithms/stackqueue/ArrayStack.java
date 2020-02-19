package com.mozaid.algorithms.stackqueue;

public class ArrayStack<Item> {

    private Item[] a;
    private int n;

    public ArrayStack(int size) {
        a = (Item[]) new Object[size];
        n = 0;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        a[n++] = item;
    }

    public Item pop() {
        Item item = a[--n];
        a[n] = null;
        return item;
    }

    public Item peek() {
        return a[n - 1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

}