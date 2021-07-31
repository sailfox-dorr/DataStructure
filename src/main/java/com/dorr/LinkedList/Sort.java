package com.dorr.LinkedList;

public class Sort {
    // merge sort

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            //找到链表的中间节点

            ListNode slow = head;
            ListNode fast = head.next;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode next = slow.next;
            // 拆分为连个linkedList
            slow.next = null;

            ListNode left = sortList(head);
            ListNode right = sortList(next);

            ListNode res = new ListNode(0);
            ListNode curlNode = res;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    curlNode.next = left;
                    left = left.next;
                } else {
                    curlNode.next = right;
                    right = right.next;
                }

                curlNode = curlNode.next;

            }

            while (!(left == null && right == null)) {
                if (left == null) {
                    curlNode.next = right;
                    right = right.next;
                } else {
                    curlNode.next = left;
                    left = left.next;
                }

                curlNode = curlNode.next;
            }

            return res.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
