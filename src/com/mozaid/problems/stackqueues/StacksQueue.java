package com.mozaid.problems.stackqueues;

import com.mozaid.algorithms.stackqueue.ResizingArrayStack;
import com.mozaid.algorithms.stackqueue.Stack;

public class StacksQueue<Item> {

    private Stack<Item> inbox;
    private Stack<Item> outbox;

    public StacksQueue() {
        inbox = new ResizingArrayStack<>();
        outbox = new ResizingArrayStack<>();
    }

    public void enqueue(Item item) {
        inbox.push(item);
    }

    public Item dequeue() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }
        }
        return outbox.pop();
    }

    public boolean isEmpty() {
        return inbox.isEmpty() && outbox.isEmpty();
    }

    public int size() {
        return inbox.size() + outbox.size();
    }

    public static void main(String[] args) {
        System.out.println("=======StacksBasedQueue Demo========");
        StacksQueue<Character> queue = new StacksQueue<>();
        queue.enqueue('M');
        queue.enqueue('Z');
        queue.enqueue('K');
        queue.enqueue('C');
        System.out.println(queue.dequeue());
        queue.enqueue('H');
        queue.enqueue('E');
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.isEmpty());
    }

}
