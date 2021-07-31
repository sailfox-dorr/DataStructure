package com.dorr.ke;

public class LastKNodeOfALinkList {
    public ListNode FindKthToTail (ListNode pHead, int k) {
        // write code here
        ListNode res = pHead;
        ListNode pre = pHead;
        for (int i = 0; i < k; i++) {
            if (pre != null){
                pre = pre.next;
            }else {
                return null;
            }
        }
        while (pre != null){
            res = res.next;
            pre = pre.next;
        }
        return res;
    }
}
