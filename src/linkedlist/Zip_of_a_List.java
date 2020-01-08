package linkedlist;

/**
 * https://www.geeksforgeeks.org/rearrange-a-given-linked-list-in-place/
 * <p>
 * Zip of a list (a0 -> a1 -> a2 -> a3 -> a4) is (a0 -> a4 -> a1 -> a3 -> a2)
 */
public class Zip_of_a_List {

    public static ListNode zip(ListNode head) {

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Second half
        ListNode node2 = slow.next;

        // First half
        ListNode node1 = head;
        slow.next = null;

        node2 = reverse(node2);

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (node1 != null || node2 != null) {
            if (node1 != null) {
                curr.next = node1;
                curr = curr.next;
                node1 = node1.next;
            }

            if (node2 != null) {
                curr.next = node2;
                curr = curr.next;
                node2 = node2.next;
            }
        }

        head = dummy.next;
        return head;
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
        ListNode zipped = zip(head);
        while (zipped != null) {
            System.out.print(zipped.val + " ");
            zipped = zipped.next;
        }
    }
}
