package com.kmozaid.problems.stackqueues;

public class NStacks {

    private int[] elements;
    private int[] heads;
    private int[] next;

    private int free;

    public NStacks(int N, int capacity) {

        elements = new int[capacity];
        heads = new int[N];
        next = new int[capacity];

        // Initialize all stacks as empty.
        for (int i = 0; i < heads.length; i++) {
            heads[i] = -1;
        }

        // Initialize all spaces as free.
        for (int i = 0; i < next.length - 1; i++) {
            next[i] = i + 1;
        }
        next[next.length - 1] = -1;   // -1 is used to indicate end of free list.

        free = 0;
    }

    public boolean isEmpty(int sn) {
        return heads[sn] == -1;
    }

    private boolean isFull() {
        return free == -1;
    }

    public void push(int sn, int element) {
        if (isFull())
            throw new IllegalStateException("Stack is full.");

        // Free index.
        int curr = free;

        // Next free slots.
        free = next[curr];

        next[curr] = heads[sn]; // Store Previous head of stack sn in next.
        heads[sn] = curr;        // heads is now current of stack sn.

        elements[curr] = element;
    }

    public int pop(int sn) {
        if (isEmpty(sn))
            throw new IllegalStateException("Stack is empty.");

        // head index of stack sn.
        int curr = heads[sn];

        // Store back previous head of stack sn which is stored in next.
        heads[sn] = next[curr];

        // Curr becomes free.
        next[curr] = free;
        free = curr;

        return elements[curr];
    }

    public static void main(String[] args) {
        NStacks nStacks = new NStacks(3, 10);
        nStacks.push(2, 15);
        nStacks.push(2, 45);
        nStacks.push(2, 28);

        nStacks.push(1, 17);
        nStacks.push(1, 49);
        nStacks.push(1, 39);
        nStacks.push(1, 16);

        nStacks.push(0, 11);
        nStacks.push(0, 9);
        nStacks.push(0, 7);

        System.out.println("Popped element from stack 2 is " + nStacks.pop(2));
        System.out.println("Popped element from stack 1 is " + nStacks.pop(1));
        System.out.println("Popped element from stack 0 is " + nStacks.pop(0));
    }
}
