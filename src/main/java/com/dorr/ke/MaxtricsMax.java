package com.dorr.ke;

public class MaxtricsMax {

    public static int minPathSum(int[][] matrix) {
        // write code here
        // 不是方阵
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        int max = 1;
        dp[0][0] = matrix[0][0];
        // 初始化dp 数组

        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + matrix[i][0];

        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + matrix[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + matrix[i][j];


            }
        }
        return dp[m-1][n -1];

    }

    public static void main(String[] args) {

        int[][] arr = {{1,3,5,9},{8,1,3,4},{5,0,6,1},{8,8,4,0}};
        System.out.println(minPathSum(arr));


    }
}
