package com.dorr.ke;

public class ColumnsNum {
//    建立状态转移方程
//            缓存并复用以往结果
//    按顺序从小往大算


    public static int solution(int m, int n){
        int[][] ints = new int[m][n];
        ints[0][1] = 1;
        ints[1][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                ints[i][j] = ints[i -1][j] + ints[i][j-1];
            }
        }

        return ints[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(solution(3, 3));
    }
}
