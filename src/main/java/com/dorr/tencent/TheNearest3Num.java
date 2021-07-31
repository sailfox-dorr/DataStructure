package com.dorr.tencent;

import java.util.Arrays;

public class TheNearest3Num {
//

    public static int threeSumClosest(int[] nums, int target) {
        // double pre

        int sum = 0;
        double sub = Integer.MAX_VALUE;
        int len = nums.length;
        if (len == 3) return nums[0] + nums[1] + nums[2];
//     降序排列
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            int num1 = i;
            int num2 = i + 1;
            int num3 = len - 1;
            while (num2 < num3) {
                sum = nums[num1] + nums[num2] + nums[num3] - target;
                if (sum == 0) return target;
                sub = Math.abs(sum) > Math.abs(sub) ? sub : sum;
                if (sum > 0) num3--;
                else num2++;
            }
        }

        return (int) (target + sub);


    }

    public static void main(String[] args) {
        int[] arr = {-1,2,1,-4};
        System.out.println(threeSumClosest(arr, 1));
    }
}
