package com.dorr.tencent;

import java.util.Stack;

public class Mulitply {
    public static void main(String[] args) {
        System.out.println(add("33", "10", 2));
        System.out.println(muliptyOneLetter("93", "3"));
        System.out.println(multply("123", "45665456"));
    }
    public static String multply(String s1, String s2) {
        if (isFullZero(s1) || isFullZero(s2)){
            return "0";
        }

        String result = "";

        for (int i = s2.length() - 1; i >= 0; i--) {
            String mulitplyi = muliptyOneLetter(s1, s2.substring(i, i + 1));
            result = add(result, mulitplyi, s2.length() - 1 - i);


        }
        return result;

    }

    public static String muliptyOneLetter(String s1, String c1) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> results = new Stack<>();
        for (int i = 0; i < s1.length(); i++) {
            stack1.push(Integer.parseInt(s1.substring(i, i + 1)));
        }
        int add = 0;
        int mul = Integer.parseInt(c1);
        while (!stack1.isEmpty()) {
            int res = mul * stack1.pop() + add;
            ;
            if (res / 10 >= 1) {
                add = res / 10;
                results.push(res % 10);
            } else {
                add = 0;
                results.push(res);
            }

        }
        if (add != 0) {
            results.push(add);
        }
        StringBuilder s = new StringBuilder();
        while (!results.isEmpty()) {
            s.append(results.pop());
        }
        return s.toString();


    }



    public static String add(String s1, String s2, int n) {
        if (s1 == "") {
            return s2;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> results = new Stack<>();
        for (int i = 0; i < s1.length(); i++) {
            stack1.push(Integer.parseInt(s1.substring(i, i + 1)));
        }
        for (int i = 0; i < s2.length(); i++) {
            stack2.push(Integer.parseInt(s2.substring(i, i + 1)));
        }
        for (int i = 0; i < n; i++) {
            stack2.push(0);
        }
        int add = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int i = 0;
            int j = 0;
            if (!stack1.isEmpty()) {
                i = stack1.pop();
            }
            if (!stack2.isEmpty()) {
                j = stack2.pop();
            }
            int res = i + j + add;
            if (res >= 10) {
                results.push(res - 10);
                add = 1;
            } else {
                results.push(res);
                add = 0;
            }

        }
        if (add == 1) {
            results.push(1);
        }
        StringBuilder s = new StringBuilder();
        while (!results.isEmpty()) {
            s.append(results.pop());
        }
        return s.toString();
    }

    public static boolean isFullZero(String s1) {
        for (int i = 0; i < s1.length(); i++) {
            if (!(s1.charAt(i) == '0')) {
                return false;
            }
        }
        return true;
    }


}
