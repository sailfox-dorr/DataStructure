package com.dorr.ke;

public class HuiWenString {

    public static int getLongestPalindrome(String A, int n) {

        // write code here
        boolean[][] dp = new boolean[n][n];
        int max = 1;
        for (int d = 0; d < n; d++) {
            for (int s = 0; s < n - d; s++) {
                int e = s + d;
                // 如果起止位置的字符串相同
                if (A.charAt(s) == A.charAt(e)) {
                    // 如果为 0 或者1 更新为true
                    if (d == 0 || d == 1) {
                        dp[s][e] = true;
                    } else {
                        //

                        dp[s][e] = dp[s + 1][e - 1];
                    }
                    if (dp[s][e]) {
                        max = Math.max(max, d + 1);
                    }
                }
            }
        }
        return max;




    }

    public static void main(String[] args) {
        System.out.println(getLongestPalindrome("abc1234321ab", 12));
    }
}
