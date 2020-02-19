package com.mozaid.problems.stackqueues;

import com.mozaid.algorithms.stackqueue.ResizingArrayQueue;

public class QueuesStack<Item> {

    private ResizingArrayQueue<Item> inbox;
    private ResizingArrayQueue<Item> outbox;

    public QueuesStack() {
        inbox = new ResizingArrayQueue<>();
        outbox = new ResizingArrayQueue<>();
    }

    public void push(Item item) {
        inbox.enqueue(item);

        while (!outbox.isEmpty()) {
            inbox.enqueue(outbox.dequeue());
        }

        ResizingArrayQueue<Item> temp = inbox;
        inbox = outbox;
        outbox = temp;
    }

    public Item pop() {
        if (outbox.isEmpty())
            throw new IllegalStateException("Stack is empty.");

        return outbox.dequeue();
    }

    public boolean isEmpty() {
        return inbox.isEmpty() && outbox.isEmpty();
    }

    public int size() {
        return inbox.size() + outbox.size();
    }

    public static void main(String[] args) {
        System.out.println("=======QueuesBasedStack Demo========");
        QueuesStack<Character> stack = new QueuesStack<>();
        stack.push('M');
        stack.push('Z');
        stack.push('K');
        stack.push('C');
        System.out.println(stack.pop());
        stack.push('H');
        stack.push('E');
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }
}
