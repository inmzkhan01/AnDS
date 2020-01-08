package arrays;

public class Stack_With_Max_Operation_Supported {

    class Stack {
        class Node {
            int val;
            int max;
            Node next;

            public Node(int val, int max) {
                this.val = val;
                this.max = max;
            }
        }

        Node head;

        public boolean isEmpty() {
            return null == head;
        }

        public void push(int v) {
            Node node;

            if (isEmpty())
                node = new Node(v, v);
            else
                node = new Node(v, Math.max(v, head.max));

            node.next = head;
            head = node;
        }

        public int pop() {
            if (isEmpty())
                throw new IllegalStateException("Stack is empty.");

            Node popped = head;
            head = head.next;
            return popped.val;
        }

        public int max() {
            if (isEmpty())
                throw new IllegalStateException("Stack is empty.");

            return head.max;
        }
    }


    public static void main(String[] args) {
        Stack stack = new Stack_With_Max_Operation_Supported().new Stack();
        stack.push(5);
        stack.push(2);
        stack.push(8);
        stack.push(1);

        while (!stack.isEmpty())
            System.out.println("Max: " + stack.max() + " Popped " + stack.pop());
    }
}
