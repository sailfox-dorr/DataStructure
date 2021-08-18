package com.dorr.ke;

import java.awt.Point;
import java.util.Comparator;
import java.util.LinkedList;

public class HorseTravel {
    private int X;    // 棋盘的横坐标
    private int Y;    // 棋盘的纵坐标
    private int[][] checkerboard;    // 自定义二维数组棋盘
    private boolean[][] visited;    // 判断棋子是否访问过
    private boolean finished;        // 判断递归是否完成
    private Point p;                // 递归取出的棋子位置

    // 构造函数初始化对象
    public HorseTravel(int x, int y) {
        X = x;
        Y = y;
        checkerboard = new int[x][y];
        visited = new boolean[x][y];
    }

    public static void main(String[] args) {
        System.out.println("----------------- 游戏开始  -----------------\n");
        long start = System.currentTimeMillis();
        HorseTravel chess = new HorseTravel(6, 6);        // 初始化棋盘
        chess.traceback(0, 0, 1);                    // 递归+回溯,完成棋盘走法
        // 打印棋盘结果
        for (int[] x : chess.checkerboard) {
            for (int y : x) {
                System.out.printf("%d\t", y);
            }
            System.out.println("\n");
        }
        long end = System.currentTimeMillis();
        long time = (end - start) / 1000;
        System.out.println("一共耗时： " + time + " 秒");
    }


    public void traceback(int x, int y, int step) {
        checkerboard[x][y] = step;    // 将当前的步数记录在棋盘上
        visited[x][y] = true;        // 将当前位置标记为已访问过
        LinkedList<Point> list = next(new Point(x, y));        // 当前点所有可能步数的集合
        sort(list);                    // 贪心算法升序排序优化
        // 循环遍历集合，直到为空跳出循环
        while (!list.isEmpty()) {
            p = list.remove(0);    // 取出下一个可以走的位置
            // 判断该点是否已经访问过
            if (!visited[p.x][p.y]) {
                traceback(p.x, p.y, step + 1);    // 递归
            }
        }
        // 回溯
        if (step < X * Y && !finished) {
            checkerboard[x][y] = 0;        // 棋盘步数重置
            visited[x][y] = false;        // 访问记录重置
        } else {
            finished = true;
        }
    }

    /**
     * 将当前棋子的下一个位置的所有位置存入list中
     *
     * @param curPoint 当前棋子
     * @return list        棋子下一个位置所有可能的集合
     */
    public LinkedList<Point> next(Point curPoint) {

        LinkedList<Point> list = new LinkedList<Point>();
        Point p = new Point();
        // 棋子可以走位置1,x+2<X,y-1>=0
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y - 1) >= 0) {
            list.add(new Point(p));
        }
        // 棋子可以走位置2,x+1<X,y-2>=0
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y - 2) >= 0) {
            list.add(new Point(p));
        }
        // 棋子可以走位置3,x-1>=0,y-2>=0
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y - 2) >= 0) {
            list.add(new Point(p));
        }
        // 棋子可以走位置4,x-2>=0,y-1>=0
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y - 1) >= 0) {
            list.add(new Point(p));
        }
        // 棋子可以走位置5,x-2>=0,y+1<Y
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y + 1) < Y) {
            list.add(new Point(p));
        }
        // 棋子可以走位置6,x-1>0,y+2<Y
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y + 2) < Y) {
            list.add(new Point(p));
        }
        // 棋子可以走位置7,x+1<X,y+2<Y
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y + 2) < Y) {
            list.add(new Point(p));
        }
        // 棋子可以走位置8,x+2<X,y+1<Y
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y + 1) < Y) {
            list.add(new Point(p));
        }
        return list;
    }

    /**
     * 算法优化：正序排序
     * 贪心算法：将当前棋子下下位置的集合按照个数进行排序
     * 优化思想：先将可能多的下下位置的子先下了，再回溯下少位置的子
     * @param list
     */
    public void sort(LinkedList<Point> list) {
        list.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return next(p1).size() - next(p2).size();
            }
        });
    }


}
