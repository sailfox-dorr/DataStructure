package com.dorr.ke;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Solutions {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 4, 5, 8, 11, 10, 6, 6, 7, 5}, 12));
    }

    public static ArrayList solution(int[] num, int target) {
        Arrays.sort(num);
        ArrayList result = new ArrayList<Point>();
        for (int i = 0; i < num.length - 1; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (num[i + 1] != num[i] && num[j] != num[j - 1] && (target - num[i]) == num[j]) {
                    result.add(new Point(num[i], num[j]));
                }
            }
        }
        for (int i = 0; i < num.length - 1; i++) {
            if (target % 2 == 0 && num[i] * 2 == target && num[i] == num[i + 1]) {
                result.add(new Point(num[i], num[i]));
                break;
            }
        }
        return result;
    }

}
