package com.dorr.ke;

import java.util.PriorityQueue;

public class KMaxElements {

    private static PriorityQueue<Integer> integers = new PriorityQueue<>((i, j) -> i - j);


    public static int findLastK(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {

            integers.add(nums[i]);
            if (integers.size() > k) {
                integers.poll();
            }


        }
        return integers.poll();

    }

    public static int findLastK2(int[] nums, int k) {

        if (k >= nums.length) {
            return -1;
        } else {
            quickSort(nums, 0, nums.length - 1);
//            return nums[nums.length - k];
            return nums[nums.length - k];
        }



    }

    public static void print(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + ",");
        }
        System.out.println();
    }


    public static void quickSort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int tmp = arr[low];
        int i = low;
        int j = high;
        while (i != j) {
            while (arr[j] >= tmp && j > i) {

                j--;
            }
            while (arr[i] <= tmp && i < j) {
                i++;
            }
            if (j > i) {
                int p = arr[j];
                arr[j] = arr[i];
                arr[i] = p;
            }
        }
        arr[low] = arr[i];
        arr[i] = tmp;

        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);

    }

    public static void main(String[] args) {

        int[] ints = {3, 2, 1, 5, 6, 4};
        System.out.println(findLastK2(ints, 2));
    }

}
