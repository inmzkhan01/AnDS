package stackqueue;

public class LinkedStack<Item> {

    private Node first;
    private int n;

    public LinkedStack() {
        first = null;
        n = 0;
    }

    private class Node {
        Item value;
        Node next;

        Node(Item value) {
            this.value = value;
        }
    }

    public void push(Item item) {
        Node node = new Node(item);
        node.next = first;
        first = node;
        n++;
    }

    public Item pop() {
        Node node = first;
        first = first.next;
        n--;
        return node.value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public static void main(String args[]) {
        LinkedStack<String> stack = new LinkedStack();
        stack.push("5");
        stack.push("6");
        stack.push("0");
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }

}
