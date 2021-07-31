package com.dorr.tencent;

public class DivideExpectItSelf {
    // no divides yo use
    public static int[] productExceptSelf(int[] nums) {
        // left multiply
        int left = 1;
        int[] tmp = new int[nums.length + 1];
        int[] result = new int[nums.length];
        tmp[nums.length - 1] = nums[nums.length - 1];
        tmp[nums.length] = 1;
        for (int i = nums.length - 2; i > 0; i--) {
            // 初始化辅助数组
            tmp[i] = tmp[i + 1] * nums[i];

        }

        for (int i = 0; i < nums.length; i++) {
            result[i] = left * tmp[i + 1];
            left *= nums[i];

        }

        return result;


    }

    public static void main(String[] args) {


        int[] arr = {1, 2, 3, 4};

        for (int i : productExceptSelf(arr)) {
            System.out.println(i);
        }
    }
}
