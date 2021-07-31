package com.dorr.ke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write code here\
        // 使用队列遍历实现

        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        if (root == null) return arr;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {

            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                // 队列第一个的左右
                if (queue.peek().left != null) {
                    queue.offer(queue.peek().left);
                }
                if (queue.peek().right != null) {
                    // 进队列
                    // peek 出队列
                    queue.offer(queue.peek().right);
                }
                // 出队第一个
                level.add(queue.poll().val);
            }
            arr.add(level);
        }
        return arr;

    }


    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        if (root == null) return arr;
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.add(root);
        int height = 1;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            boolean flag = height % 2 == 1;
            int size = flag ? stack1.size() : stack2.size();
            ArrayList<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                if (flag) {
                    if (stack1.peek().left != null) {
                        stack2.add(stack1.peek().left);
                    }
                    if (stack1.peek().right != null) {
                        stack2.add(stack1.peek().right);
                    }
                    level.add(stack1.pop().val);
                } else {
                    if (stack2.peek().right != null) {
                        stack1.add(stack2.peek().right);
                    }
                    if (stack2.peek().left != null) {
                        stack1.add(stack2.peek().left);
                    }
                    level.add(stack2.pop().val);
                }
            }
            arr.add(level);

            height++;
        }
        return arr;

    }


}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
}