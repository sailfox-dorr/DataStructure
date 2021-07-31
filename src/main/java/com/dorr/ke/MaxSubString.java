package com.dorr.ke;

public class MaxSubString {

    public static int solution(int[] arr, int init) {
        int res = arr[0];
        int sum = init;
        for (int i : arr) {
            if (sum > init) sum += i;
            else sum = i;
            res = Math.max(res, sum);
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] arr = {-1, -2, -3, 9, 2};
        System.out.println(solution(arr, -2));
    }
}
