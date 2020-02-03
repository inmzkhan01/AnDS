package linkedlist;

public class Print_Reverse {

    public static void print(ListNode listNode) {
        if (null == listNode) return;

        print(listNode.next);
        System.out.print(listNode.val + " ");
    }

    public static void main(String[] args) {
        print(ListNode.linkedList());
    }
}
