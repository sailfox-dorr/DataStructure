package com.dorr.ke;

import java.util.Stack;

public class StackImpQueue {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {

        stack1.push(node);

    }

    public int pop() {
        // 队列先到先出
        // 栈 先到后出  ⑧前一个栈的全部数据pop到另一个栈中 该栈顺序
        // pop 即可

        if (stack1.isEmpty() && stack2.isEmpty()){
            throw new RuntimeException("Queue is empty");
            }
        if (stack2.isEmpty()){
            // 如果stack2 不空的话直接pop
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();

    }
}
