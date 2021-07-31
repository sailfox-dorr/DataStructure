package com.dorr.ke;

public class NGroupLinkListReverse {
    public ListNode reverseKGroup(ListNode head, int k) {
        // write code here


        if (head == null || head.next == null || k <2) return head;
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode end = head;
        while (end!= null && end.next != null){
            ListNode nextStart = end.next;

        }
        return end;


    }
}
