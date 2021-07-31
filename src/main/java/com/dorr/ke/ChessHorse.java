package com.dorr.ke;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class ChessHorse {
    public static int X;
    public static int Y;
    private Point travelPoint;
    ArrayList<int[][]> dps = new ArrayList<>();

    int[][] dp;
    boolean[][] visited;

    boolean finished = false;

    public static void main(String[] args) {
        new ChessHorse().travel(1, 1, 11, 11);

    }

    public ChessHorse() {
    }

    public void travel(int x, int y, int s, int t) {
        X = s;
        Y = t;
        dp = new int[X][Y];
        visited = new boolean[X][Y];
        System.out.println("----------------- 游戏开始  -----------------\n");
        long start = System.currentTimeMillis();


        this.travel(x, y, 1);
        for (int[] chess : this.dp) {
            for (int p : chess) {
                System.out.printf("%d\t", p);
            }
            System.out.println("\n");
        }
        long end = System.currentTimeMillis();
        long time = (end - start) / 1000;
        System.out.println("一共耗时： " + time + " 秒");
    }


    public void travel(int x, int y, int step) {
        Point currentPoint = new Point(x, y);
        visited[x][y] = true;
        dp[x][y] = step;
        LinkedList<Point> points = travelNext(currentPoint);
        sort(points);
        while (!points.isEmpty()) {
            travelPoint = points.remove(0);
            if (!visited[travelPoint.x][travelPoint.y]) {
                travel(travelPoint.x, travelPoint.y, step + 1);
            }
        }
        // 只要有一个 满足则表示程序已经完成了
        //Step = X*Y 只能结束子递归方法

        if (step < X * Y && !finished) {
            dp[x][y] = 0;
            visited[x][y] = false;
        } else {
            finished = true;
        }


    }

    public LinkedList<Point> travelNext(Point currentPoint) {
        LinkedList<Point> list = new LinkedList<Point>();
        Point p = new Point();
        // 棋子可以走位置1,x+2<X,y-1>=0
        if ((p.x = currentPoint.x + 2) < X && (p.y = currentPoint.y - 1) >= 0) {
            list.add(new Point(p));
        }
        // 棋子可以走位置2,x+1<X,y-2>=0
        if ((p.x = currentPoint.x + 1) < X && (p.y = currentPoint.y - 2) >= 0) {
            list.add(new Point(p));
        }
        // 棋子可以走位置3,x-1>=0,y-2>=0
        if ((p.x = currentPoint.x - 1) >= 0 && (p.y = currentPoint.y - 2) >= 0) {
            list.add(new Point(p));
        }
        // 棋子可以走位置4,x-2>=0,y-1>=0
        if ((p.x = currentPoint.x - 2) >= 0 && (p.y = currentPoint.y - 1) >= 0) {
            list.add(new Point(p));
        }
        // 棋子可以走位置5,x-2>=0,y+1<Y
        if ((p.x = currentPoint.x - 2) >= 0 && (p.y = currentPoint.y + 1) < Y) {
            list.add(new Point(p));
        }
        // 棋子可以走位置6,x-1>0,y+2<Y
        if ((p.x = currentPoint.x - 1) >= 0 && (p.y = currentPoint.y + 2) < Y) {
            list.add(new Point(p));
        }
        // 棋子可以走位置7,x+1<X,y+2<Y
        if ((p.x = currentPoint.x + 1) < X && (p.y = currentPoint.y + 2) < Y) {
            list.add(new Point(p));
        }
        // 棋子可以走位置8,x+2<X,y+1<Y
        if ((p.x = currentPoint.x + 2) < X && (p.y = currentPoint.y + 1) < Y) {
            list.add(new Point(p));
        }
        return list;


    }

    public void sort(LinkedList<Point> list) {
        list.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return travelNext(p1).size() - travelNext(p2).size();
            }
        });
    }


    public void resetDp(){
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                visited[i][j] = false;
                dp[i][j]=0;
            }
        }
    }

}
