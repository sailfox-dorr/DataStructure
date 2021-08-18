package com.hornor.solution;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(isSuShu("9"));
        if (in.hasNext()) {
            String input = in.next().replace(" ", "");
            String infix = input.contains(",")?",":"，";
            String[] nums = input.contains(",")?input.split(","):input.split("，");

            List<String> result = Arrays.stream(nums).filter(num -> isSuShu(num))
                    .sorted(new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return Integer.parseInt(o1) - Integer.parseInt(o2);
                        }
                    }).collect(Collectors.toList());

            StringBuilder s = new StringBuilder();
            for (String s1 : result) {
                s.append(s1).append(infix);
            }
            int len = s.toString().length();
            System.out.println(s.toString().substring(0, len - 1));

        }


    }


    public static boolean isSuShu(String number) {
        int num = Integer.parseInt(number);
        int target = (int) Math.ceil(Math.pow(Math.abs(num), 0.5));
        System.out.println(target);
        for (int i = 2; i <= target; i++) {
            if (num % i  == 0) return false;
        }
        return true;
    }


}
