package com.dorr.ke;

public class LCS {

    public static String LCS1(String str1, String str2) {
        // write code here
        boolean flag = str1.length() >= str2.length();
        String longStr = flag ? str1 : str2;
        String shortStr = flag ? str2 : str1;
        //
        for (int i = shortStr.length() - 1; i > 0; i--) {
            for (int j = 0; j < shortStr.length() - i; j++) {
                if (longStr.contains(shortStr.substring(j, i + j))) {
                    return shortStr.substring(j, i + j);
                }

            }
        }
        return null;

    }

    public static String LCS2(String str1, String str2) {
        // write code here
        boolean flag = str1.length() >= str2.length();
        String longStr = flag ? str1 : str2;
        String shortStr = flag ? str2 : str1;
        //
        String max = null;
        String[][] dp =
                new String[longStr.length()][shortStr.length()];
        for (int i = 0; i < longStr.length(); i++) {
            for (int j = 0; j < shortStr.length(); j++) {
                if (longStr.charAt(i) != shortStr.charAt(j)){
                    dp[i][j] = null;
                }else {

                }

            }
        }

        return null;

    }

    public static void main(String[] args) {
        System.out.println(LCS1("1AB2345CD", "12345EF"));
    }

}
