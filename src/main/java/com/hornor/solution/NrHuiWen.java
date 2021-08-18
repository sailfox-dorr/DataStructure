package com.hornor.solution;

import java.util.Scanner;
import java.util.Stack;

public class NrHuiWen {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 2; i <= 16; i++) {
            String s = transformIntoNRStr(n, i);
            if (isHuiWenStr(s)){
                System.out.println(s);
                System.out.println(i);
            }
        }


    }

    public static String transformIntoNRStr(int num, int r) {
        StringBuilder result = new StringBuilder();
        int tmp = num;
        int len = 1;
        while (true) {
            tmp = tmp / r;
            if (tmp >= r) {
                len++;
            } else break;
        }
        int reserve = num;
        for (int i = len; i >= 0; i--) {
            int divide = reserve / (int) Math.pow(r, i);
            reserve = reserve %  (int) Math.pow(r, i);
            result.append(divide);
        }
        return result.toString();

    }


    public static boolean isHuiWenStr(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 1) return true;
        else if (chars.length % 2 == 1) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < (chars.length - 1) / 2; i++) {
                stack.push(chars[i]);
            }
            for (int i = (chars.length + 1) / 2; i <= chars.length - 1; i++) {
                if (chars[i] != stack.pop()) return false;
            }
            return true;
        } else {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < chars.length / 2; i++) {
                stack.push(chars[i]);
            }
            for (int i = chars.length / 2; i <= chars.length - 1; i++) {
                if (chars[i] != stack.pop()) return false;
            }
            return true;
        }

    }
}
