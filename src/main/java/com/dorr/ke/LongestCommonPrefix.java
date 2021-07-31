package com.dorr.ke;

public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {

        if (strs.length == 1) return strs[0];

        String shortestString = findShortestString(strs);
        int start = 0;
        int end = 0;
        OUT:
        for (int s = 0; s < shortestString.length(); s++) {
            for (String str : strs) {
                if (!str.equals(shortestString) && str.charAt(s) != shortestString.charAt(s)) {
                    end = s;
                    break OUT;
                }
            }
            end++;
        }
        if (start == end) {
            return "";
        }


        return shortestString.substring(start, end);

    }

    public static String findShortestString(String[] strs) {
        String s = strs[0];
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < s.length()) {
                s = strs[i];
            }
        }

        return s;

    }


    public static void main(String[] args) {

        String[] strings = new String[]{"AB", "A"};
        System.out.println(findShortestString(strings));
        System.out.println((longestCommonPrefix(strings)));
        System.out.println("sada".charAt(0) != "dog".charAt(0));
    }


}
