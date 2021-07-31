package com.dorr.ke;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * [4,5,1,6,2,7,3,8],4
 * [1,2,3,4]
 */

public class SmallestNumberN {
    // 最小的k个数
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int len = input.length;

        if (k < 1 || input == null ||  len == 0 || k > len){
            return arr;
        }
        TreeSet<Integer> kSet = new TreeSet<Integer>();
        for (int i = 0; i < len; i++) {

            if (kSet.size() < k){
                kSet.add(input[i]);
            }else if (input[i] < kSet.last()){
                kSet.remove(kSet.last());
                kSet.add(input[i]);
            }
        }

        for (Integer integer : kSet) {
            arr.add(integer);
        }

        return arr;


    }
}
