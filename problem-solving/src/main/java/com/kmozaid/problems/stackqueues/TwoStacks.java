package com.kmozaid.problems.stackqueues;

public class TwoStacks {

    int size;
    int head1, head2;
    int arr[];

    TwoStacks(int n) {
        arr = new int[n];
        size = n;
        head1 = 0;
        head2 = size - 1;
    }

    public boolean isEmpty() {
        return head1 == 0 && head2 == size - 1;
    }

    public boolean isFull() {
        return head1 > head2 || head1 == size - 1 || head2 < 0;
    }

    // Method to push an element x to stack1
    public void push1(int x) {
        overflowCheck();
        arr[head1++] = x;
    }

    // Method to push an element x to stack2
    public void push2(int x) {
        overflowCheck();
        arr[head2--] = x;
    }

    // Method to pop an element from first stack
    public int pop1() {
        underflowCheck();
        return arr[--head1];
    }

    // Method to pop an element from second stack
    public int pop2() {
        underflowCheck();
        return arr[++head2];
    }

    private void overflowCheck() {
        if (isFull())
            throw new IllegalStateException("Stack Overflow");
    }

    private void underflowCheck() {
        if (isEmpty())
            throw new IllegalStateException("Stack Underflow");
    }

    public static void main(String[] args) {
        System.out.println("======TwoStacks Demo=========");

        TwoStacks twoStacks = new TwoStacks(5);
        twoStacks.push1(5);
        twoStacks.push2(10);
        twoStacks.push2(15);
        twoStacks.push1(11);
        twoStacks.push2(7);
        System.out.println("Popped element from stack1 is " + twoStacks.pop1());
        twoStacks.push2(40);
        System.out.println("Popped element from stack2 is " + twoStacks.pop2());
    }
}
