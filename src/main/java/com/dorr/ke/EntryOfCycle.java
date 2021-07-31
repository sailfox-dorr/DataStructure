package com.dorr.ke;

public class EntryOfCycle {
    //那么我们可以知道fast指针走过a+b+c+b
    //slow指针走过a+b
    //那么2*(a+b) = a+b+c+b
    //所以a = c
    //那么此时让slow回到起点，fast依然停在z，两个同时开始走，一次走一步
    //那么它们最终会相遇在y点，正是环的起始点

    public ListNode detectCycle(ListNode head) {
        // 数学算式计算
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                slow = head;
                while (fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;

            }

        }

        return null;

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
