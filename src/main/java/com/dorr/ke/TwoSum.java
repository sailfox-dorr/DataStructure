package com.dorr.ke;

public class TwoSum {

    /**
     * @param numbers int整型一维数组
     * @param target  int整型
     * @return int整型一维数组
     */
    public static int[] twoSum(int[] numbers, int target) {
        // write code here
        int[] arr = new int[2];
        for (int i = 0; i < numbers.length - 1; i++) {
            int num1 = numbers[i];
            int num2 = target - num1;
            for (int j = i + 1; j < numbers.length; j++) {
                if(numbers[j] == num2){
                    arr[0] = i + 1;
                    arr[1] = j + 1;
                    return arr;
                }

            }
        }
        return null;

    }

    public static void main(String[] args) {

        int[] arr = {1,4,9,15};

        System.out.println(twoSum(arr , 13) [0]);
    }
}
