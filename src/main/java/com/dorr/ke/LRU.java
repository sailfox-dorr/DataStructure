package com.dorr.ke;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRU {
    Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
    LinkedList<Integer> list = new LinkedList<Integer>();

    public int[] LRU(int[][] operators, int k) {
        // write code here
        // key 是有顺序排列的


        for (int[] operator : operators) {

            int key = operator[1];
            switch (operator[0]) {
                case 1:
                    // 表示set 操作
                    int value = operator[2];
                    if (map.size() < k) {
                        map.put(key, value);
                    } else {
                        Iterator<Integer> iterator = map.keySet().iterator();
                        map.remove(iterator.next());
                        map.put(key, value);
                    }
                    break;
                case 2:
                    if (map.containsKey(key)){
                        Integer val = map.get(key);
                        list.add(val);
                        // linked 是有顺序的
                        map.remove(key);
                        map.put(key,val);
                    }else {
                        list.add(-1);
                    }
                    break;
                default:
            }
        }
        int[] ints = new int[list.size()];
        int i = 0;
        for (Integer integer : list) {
            ints[i++] = integer;
        }
        return ints;

    }

    public static void main(String[] args) {

    }
}
