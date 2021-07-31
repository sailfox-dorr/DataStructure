package com.dorr.dynamicschedule;

public class MaxSubArr {

    public int maxSubArray(int[] nums) {

        int max = 0;
        int[][] dp = new int[nums.length][nums.length];
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        for (int start = 0; start < nums.length; start++) {
            for (int end = start; end < nums.length; end++) {
                if (start == end) {
                    dp[start][end] = nums[start];
                    max = Math.max(max, dp[start][end]);
                } else {
                    dp[start][end] = dp[start][end - 1] + nums[end];
                    max = Math.max(Math.max(dp[start][end], dp[start][end - 1]), max);
                }

            }
        }

        return max;

    }
}
