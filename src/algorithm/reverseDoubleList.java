package algorithm;

import DataStructure.DoubleNode;

public class reverseDoubleList {
    public static void main(String[] args) {

    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null, nxt = null;
        while (head != null) {
            nxt = head.next;
            head.next = pre;
            head.last = nxt;
            pre = head;
            head = nxt;
        }
        return pre;
    }
}
