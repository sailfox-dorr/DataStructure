package com.dorr.tencent;

public class BestTime {

    public static int maxProfit(int[] prices) {

        int profit = 0;
        int length = prices.length;
        for (int i = 1; i < length; i++) {
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        }
        return profit;

    }

    public static int maxProfits(int[] prices) {

        int length = prices.length;
        int[] dp = new int[length];
        dp[0] = 0;

        for (int i = 1; i < length; i++) {
            if (prices[i] > prices[i - 1]) dp[i] = dp[i - 1] + prices[i] - prices[i - 1];
            else dp[i] = dp[i - 1];
        }
        return dp[length - 1];
    }

    public static void main(String[] args) {
        int[] ints = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(ints));
        System.out.println(maxProfits(ints));

    }
}
