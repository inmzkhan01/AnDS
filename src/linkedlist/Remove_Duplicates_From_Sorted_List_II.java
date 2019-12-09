package linkedlist;

public class Remove_Duplicates_From_Sorted_List_II {

    public static ListNode deleteDuplicates(ListNode head) {

        ListNode dummy = new ListNode(0);

        dummy.next = head;

        ListNode prev = head;

        ListNode curr = head.next;

        boolean repeat = false;

        while(curr != null) {
            if(prev.val == curr.val) {
                curr = curr.next;
                repeat = true;
            } else {
                curr = curr.next;

                if(!repeat) {
                    prev = prev.next;
                }
                prev.next = curr;
            }
        }

        if(repeat) {
            prev = null;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(1, new ListNode(3, new ListNode(3,
                new ListNode(4, new ListNode(4, new ListNode(5)))))));
        System.out.println(head);

        ListNode onceOccurrenceOnly = deleteDuplicates(head);

        System.out.println(onceOccurrenceOnly);
    }
}
