package com.dorr.ke;

public class MaxHuiWenString {

    public static int getLongestPalindrome(String A, int n) {
        // write code here

        if (n <= 1){
            return n;
        }
        char[] chars = A.toCharArray();
        int max = 1;
        for (int i = 1; i <= n -1  ; i++) {
            int len1 = 0 ;
            for (int j = 0; j <= Math.min(n - 2 -i , i) ; j++) {
//                System.out.println((i - j)  + " ==" +  (i + j +1));
                if (chars[i -j ] == chars[i + j + 1]){
                    len1 += 2;
                }else {
                    break ;
                }
            }
            int len2 = 1 ;
            OUT:
            for (int j = 1; j <= Math.min(n - 1 -i , i) ; j++) {
                System.out.println(i + " --" + "j");
                if (chars[i  - j ] == chars[i + j]){

                    len2 += 2;
                }else {
                    break ;
                }
            }
            max = Math.max(max , Math.max(len1,len2));
        }

        return max;

    }

    public static void main(String[] args) {
        System.out.println(getLongestPalindrome("cbc",3));
    }

}
