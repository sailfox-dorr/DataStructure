package com.dorr.ke;

public class MostVolume {

    public static int maxArea(int[] height) {

        if (height.length < 2) {
            return 0;
        }

        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return max;

    }

    public static int maxArea2(int[] height) {
        int start = 0;
        int end = height.length - 1;

        int max = 0;
        while (end > start) {

            max = Math.max(max, (end - start) * Math.min(height[start], height[end]));
            // 小于则找一个大一点的start
            if (height[start] < height[end]) {
                start++;
            } else end--;
        }

        return max;

    }


    public static void main(String[] args) {
        int[] ints = new int[]{4, 3, 2, 1, 4};
        System.out.println(maxArea(ints));
        System.out.println(maxArea2(ints));
    }
}
