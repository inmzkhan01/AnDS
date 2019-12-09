package linkedlist;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x, ListNode next) {
        this.val = x;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode head = this;
        while (head.next != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }
        sb.append(head.val);
        return sb.toString();
    }
}
