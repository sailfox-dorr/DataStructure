package com.dorr.ke;

public class IslandNumber {

//    public int solve(char[][] grid) {
//        // write code here
//        int height = grid.length;
//        int length = grid[0].length;
//        int[][] dp = new int[height][length];
//        dp[0][0] = grid[0][0] == '0' ? 0 : 1;
//
//        // 初始化dp
//        for (int i = 1; i < length; i++) {
//            if (grid[0][i] == '0') dp[0][i] = dp[0][i - 1];
//            else if (grid[0][i] == '1' && grid[0][i - 1] == '1') dp[0][i] = dp[0][i - 1];
//            else if (grid[0][i] == '1' && grid[0][i - 1] == '0') dp[0][i] = dp[0][i - 1] + 1;
//        }
//        for (int i = 1; i < height; i++) {
//            if (grid[i][0] == '0') dp[i][0] = dp[i - 1][0];
//            else if (grid[i][0] == '1' && grid[i - 1][0] == '1') dp[i][0] = dp[i - 1][0];
//            else if (grid[i][0] == '1' && grid[i - 1][0] == '0') dp[i][0] = dp[i - 1][0] + 1;
//        }
//
//        for (int i = 1; i < height; i++) {
//            for (int j = 1; j < length; j++) {
//                char c0 = grid[i - 1][j - 1];
//                char c1 = grid[i - 1][j];
//                char c2 = grid[i][j - 1];
//                char c3 = grid[i][j];
//                int value = grid[i - 1][j] + grid[i][j - 1] - grid[i - 1][j - 1];
//
//                if (c1 == '0' && c2 == '0' && c3 == '1') {
//                    dp[i][j] = value + 1;
//                }else if (c1 == '1' && c2 == '1'){
//                    if (c0 == '1' || c3 == '1'){
//                        dp[i][j] = value - 1;
//                    }else {
//                        dp[i][j] = value ;
//                    }
//                }else {
//                    dp[i][j] = value ;
//                }
//
//            }
//        }
//        return dp[height - 1][length - 1];
//    }

    public int solve(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j ++) {
                if (grid[i][j] == '1') {
                    ++num_islands;
                    dfs(grid, i, j);
                }
            }
        }


        return num_islands;
    }

    private void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';//将原本为1的元素修改为'0'
        // 上下左右四个方向分别去找
        dfs(grid, r - 1, c); //遍历上下左右四个方向
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

}
