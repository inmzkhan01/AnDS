package exercises;

import stackqueue.ResizingArrayQueue;
import stackqueue.ResizingArrayStack;

public class StackAndQueue {

    /**
     * Create queue using stacks.
     */
    public class StacksBasedQueue<Item> {

        private ResizingArrayStack<Item> inbox;
        private ResizingArrayStack<Item> outbox;

        public StacksBasedQueue() {
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

    }

    /**
     * Create Stack using queues.
     */
    public class QueuesBasedStack<Item> {

        private ResizingArrayQueue<Item> inbox;
        private ResizingArrayQueue<Item> outbox;

        public QueuesBasedStack() {
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

    }

    /**
     * https://www.geeksforgeeks.org/implement-two-stacks-in-an-array/
     */
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

    }


    public class Stacks {

        private int N;
        private int capacity;
        private int[] elements;
        private int[] heads;
        private int[] next;

        private int free;

        public Stacks(int N, int capacity) {
            this.N = N;
            this.capacity = capacity;

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


    }

    public static void main(String[] args) {
        System.out.println("=======StacksBasedQueue Demo========");
        StacksBasedQueue<Character> queue = new StackAndQueue().new StacksBasedQueue<>();
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

        System.out.println("\n=======QueuesBasedStack Demo========");

        QueuesBasedStack<Character> stack = new StackAndQueue().new QueuesBasedStack<>();
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
        System.out.println(queue.isEmpty());

        System.out.println("\n======TwoStacks Demo=========");

        TwoStacks twoStacks = new StackAndQueue().new TwoStacks(5);
        twoStacks.push1(5);
        twoStacks.push2(10);
        twoStacks.push2(15);
        twoStacks.push1(11);
        twoStacks.push2(7);
        System.out.println("Popped element from stack1 is " + twoStacks.pop1());
        twoStacks.push2(40);
        System.out.println("Popped element from stack2 is " + twoStacks.pop2());

        System.out.println("\n======NStacks Demo=========");

        Stacks nStacks = new StackAndQueue().new Stacks(3, 10);
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
