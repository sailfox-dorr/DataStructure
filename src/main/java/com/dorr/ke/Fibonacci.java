package com.dorr.ke;

public class Fibonacci {
    public int Fibonacci(int n) {
        if(n == 0){
            return 0;
        }
        else if(n == 1){
            return 1;
        }else{
            int[] arr = new int[n];
            arr[0] = 0;
            arr[1] = 1;
            for (int i = 2; i < n +1; i++) {
                arr[i] = arr[i-1] + arr[i-2];
            }
            return arr[n];

        }



    }
}
