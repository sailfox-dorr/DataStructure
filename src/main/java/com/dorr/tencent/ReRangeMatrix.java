package com.dorr.tencent;

import java.util.ArrayList;
import java.util.List;

public class ReRangeMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        int start = 0;
        int m = matrix[0].length;
        int n = matrix.length;
        ArrayList<Integer> result = new ArrayList<>();
        while (start <= (m + 1) / 2 && start < n) {
            System.out.println("start: " + start + " m: " + m + " n:" + n);

            for (int i = start; i < m; i++) {


                result.add(matrix[start][i]);
                System.out.println(matrix[start][i]);
                System.out.println("-----------");

            }
            if (start != n - 1) {
                for (int i = start + 1; i < n; i++) {
                    result.add(matrix[i][m - 1]);
                    System.out.println(matrix[i][m - 1]);
                }


                for (int i = m - 2; i >= start; i--) {

                    result.add(matrix[n - 1][i]);
                    System.out.println(matrix[n - 1][i]);
                }


                for (int i = n - 2; i > start; i--) {

                    result.add(matrix[i][start]);
                    System.out.println(matrix[i][start]);


                }
            }


            m--;
            n--;
            start++;


        }

        return result;


    }

    public static void main(String[] args) {

//[[1,2,3],[4,5,6],[7,8,9]]

        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        spiralOrder(arr);

    }
}
