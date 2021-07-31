package com.dorr.ke;

import java.util.*;

public class ThreeSum {
    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> arr = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int index = Arrays.binarySearch(nums, -nums[i] - nums[j]);
                if (index > j) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    tmp.add(nums[index]);
                    arr.add(tmp);
                }
            }
        }
        return arr;

    }

    public List<List<Integer>> threeSum(int[] nums) {

        if (nums.length < 3) {
            return Collections.emptyList();
        }
        List<List<Integer>> list = new ArrayList<>();
        TreeSet<String> set = new TreeSet<>();


        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int target = -nums[i] - nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[k] == target) {
                        ArrayList<Integer> arr = new ArrayList<>();
                        arr.add(nums[i]);
                        arr.add(nums[j]);
                        arr.add(nums[k]);

                        arr.sort((new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                return o1 - o2;
                            }
                        }));
                        String s = "";
                        for (Integer integer : arr) {
                            s = s + integer + "L";
                        }
                        set.add(s);
                    }
                }
            }
        }

        for (String s : set) {
            String[] split = s.split("L");
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(Integer.parseInt(split[0]));
            arr.add(Integer.parseInt(split[1]));
            arr.add(Integer.parseInt(split[2]));
            list.add(arr);
        }

        return list;
    }
}
