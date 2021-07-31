package com.dorr.ke;

public class ReverseLinkedList {

    public static ListNode ReverseList(ListNode head) {

        // 使用while

        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head, newHead = null;
        while (p != null) {
            ListNode next = p.next;
            // 当前节点的next为新头节点
            p.next = newHead;
            // 当前节点赋值为新头节点
            newHead = p;
            // 当前节点的next为新当前节点 顺移动一位
            p = next;

        }
        return newHead;


    }
    //使用recrusive
    public static ListNode reverseList(ListNode head){

        if(head == null || head.next == null){
            return head;
        }
        // 遍历到链表尾部
        ListNode newHead = reverseList(head.next);
        // 反转
        head.next.next = head;
        head.next = null;

        return newHead;
    }


    public static void main(String[] args) {
        ListNode listNode0 = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode0.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        print(listNode0);
        System.out.println();
        print(reverseList(listNode0));
        System.out.println();
        print(ReverseList(listNode0));
    }

    public static void print(ListNode node) {

        if (node == null ) {
            return;
        }

        System.out.println(node.val);
        print(node.next);


    }


    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

}
