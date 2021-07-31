package com.dorr.tencent;

public class MaxProfit {
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        int max = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (prices[j] > prices[i]) max = Math.max(max, prices[j] - prices[i]);
            }
        }

        return max;
    }

    public static int maxProfits(int[] prices){
        int length = prices.length;
        int[][] dp = new int[length][2];
        //  dp[i][0] 代表第i天的最大收益
        dp[0][0] = 0;
        //  dp[i][1] 代表第i天的最低价格

        dp[0][1] = -prices[0];
        for (int i = 1; i < length; i++) {
            dp[i][1] = Math.max(dp[i-1][1] , -prices[i]);
            dp[i][0] = Math.max(dp[i-1][0] , dp[i-1][1] + prices[i]);
        }

        return dp[length-1][0];


    }





    public static void main(String[] args) {
        int[] ints = {7,6,4,3,1};
        System.out.println(maxProfit(ints));
        System.out.println(maxProfits(ints));

    }
}
