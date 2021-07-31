package com.dorr.ke;

import java.util.Stack;

public class TwoStringSum {
    public static String solve(String s, String t) {
        // write code here
        boolean flag = s.length() >= t.length();
        String longStr = flag? s:t;
        String shortStr = flag? t:s;
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        Stack<Integer> result = new Stack<Integer>();
        for (String s1 : longStr.split("")) {
            stack1.push(Integer.parseInt(s1));
        }
        for (String s1 : shortStr.split("")) {
            stack2.push(Integer.parseInt(s1));
        }
        int jin = 0;
        while (!stack1.isEmpty()){
            Integer s1 = stack1.pop();
            Integer s2 = 0;
            if (!stack2.isEmpty()){
                s2 = stack2.pop();
            }
            int res = s1 + s2 + jin;
            jin = res / 10;
            if (res >= 10){
                result.push(res - 10);
            }else {
                result.push(res);
            }
        }
        if (jin==1) result.push(1);
        StringBuilder str = new StringBuilder();
        while (!result.isEmpty()){
            str.append(result.pop());
        }
        return str.toString();



    }

    public static void main(String[] args) {

        System.out.println(solve("99","2"));
        System.out.println(11 / 10);

    }

}
