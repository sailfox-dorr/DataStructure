package com.dorr.ke;

public class RemoveNNode {

    public ListNode removeNthFromEnd (ListNode head, int n) {
        // write code here
        if (head == null){
            return head;
        }
        boolean removeHead = false;
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null){
            // 头节点为一处 以及返回让他的next
            return head.next;
        }
        while (fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }


        // 删除slow.next 节点
        slow.next = slow.next.next;
        return head;




    }
}
