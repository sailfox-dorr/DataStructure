package com.dorr.ke;

public class ReverseString {
    public void reverseString(char[] s) {

        int start = 0;
        int end = s.length - 1;
        while (end > start) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;

        }


    }

    public static String reverseWords(String s) {

        int start = 0;
        int end = 0;

        String res = "";

        while (end <= s.length()) {
            while (end < s.length() && !(s.charAt(end) == ' ')) {
                end++;
            }

            int tmp = end;
            while (end > 0 && end >= start) {
                res = res + s.substring(end - 1, end);
                end--;
            }
            if (tmp < s.length()) {
                res = res + " ";
            } else {
                break;
            }
            start = tmp + 2;
            end = tmp + 2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("I love u".length());
        System.out.println(reverseWords("I love u"));

    }

}
