package com.dorr.ke;

public class TwoDivide {
    public static int search(int[] nums, int target) {
        // write code here
        if (nums.length < 1 || target < nums[0] || target > nums[nums.length - 1]) return -1;
        int left = 0;
        int right = nums.length - 1;
        int mid;

        while (right > left) {
            mid = (left + right) / 2;
            if (target > nums[mid]) {
                left = mid +1 ;
            } else {
               right = mid;
            }
        }
        return nums[left] == target ? left :-1;
    }

    public static void main(String[] args) {

        int[] data = {1, 4, 4, 4, 5};
        System.out.println(search(data, 4));

    }

}
