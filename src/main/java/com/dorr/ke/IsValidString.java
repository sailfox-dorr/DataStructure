package com.dorr.ke;

import java.util.Stack;

public class IsValidString {
    /**
     *
     * @param s string字符串
     * @return bool布尔型
     */

    public boolean isValid(String s) {
        // write code here
        // 栈 的特点 ： 先进后出
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }

}
