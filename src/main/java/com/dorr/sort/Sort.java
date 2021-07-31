package com.dorr.sort;

public class Sort {

    public static int[] bubleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }

            }
        }
        return arr;

    }

    public static int[] selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {

                if (min > arr[j]) {
                    index = j;
                    min = arr[j];
                }
            }
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = min;
            }
        }

        return arr;
    }

    public static int[] insertSort(int[] arr) {
        // 从第二位开始insert

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                } else {
                    break;
                }
            }
        }

        return arr;
    }

    public static int[] shellSort(int[] arr) {
        // 插入排序的
        for (int i = arr.length / 2; i > 0; i /= 2) {
            // 控制循环步长
            for (int j = i; j < arr.length; j++) {
                //控制无序端的起点
                for (int k = j; k > 0 && k >= i; k -= i) {
                    if (arr[k] < arr[k - i]) {
                        int tmp = arr[k - 1];
                        arr[k - i] = tmp;
                        arr[k] = tmp;
                    }
                }

            }

        }
        return arr;

    }

    public static int[] mergeSort(int[] arr) {

        mergeSort(arr, 0, arr.length - 1);
        return arr;


    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (end > start) {
            mergeSort(arr, start, (start + end) / 2);
            mergeSort(arr, (start + end) / 2 + 1, end);

            // merge 两个排序好了的小数组
            int left = start;
            // 左数列起点
            int right = (start + end) / 2 + 1;
            int index = 0;
            int[] result = new int[end - start + 1];
            // 合并两个都存在数据的数列
            while (left <= (start + end) / 2 && right <= end) {
                if (arr[left] <= arr[right]) {
                    // 默认归并时候都是有序数列
                    result[index] = arr[left];
                    left++;
                } else {
                    result[index] = arr[right];
                    right++;

                }
                index++;
            }
            //当某个数列为空的时候
            while (left <= (start + end) / 2 || right <= end) {
                if (left <= (start + end) / 2) {
                    result[index] = arr[left];
                    left++;
                } else {
                    result[index] = arr[right];
                    right++;
                }
                index++;
            }
            // 最后将临时数据加入到原数列中
            for (int i = start; i <= end; i++) {

                arr[i] = result[i - start];
            }
        }
    }

    public static int[] quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;


    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int tmp = arr[low];
        int i = low;
        int j = high;
        while (i != j) {
            // 找到第一个小于tmp的数字
            while (arr[j] >= tmp && j > i) {
                j--;
            }
            //找到第一个大于tmp 的数字
            while (arr[i] <= tmp && j > i) {
                i++;
            }
            if (j > i) {
                int t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;

            }
            // 退出的时候 i = j
            arr[low] = arr[i];
            arr[i] = tmp;
            // tmp 左边的数字都小于tmp
            // 右边的都大于tmp

            quickSort(arr, low, i - 1);
            quickSort(arr, i + 1, high);


        }


    }

    public static int[] heapSort(int[] arr) {

        // ensure the smale
        for (int i = arr.length / 2 - 1; i >= 0; i--) {

            adjustHeap(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {

            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }

        return arr;
    }

    public static void adjustHeap(int[] arr, int i, int length) {

        int tem = arr[i];
        // can not be ensure tem is bigger than every element of the subHeap
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {

            if (j + 1 < length && arr[j] < arr[j + 1]) {
                // 左节点小于右节点 ， 指向右节点 拿到两个子节点中最大值
                j++;
            }
            if (arr[j] > tem) {
                // 大于父节点 交换
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = tem;
    }


    public static void main(String[] args) {
        int[] ints = {3, 2, 1, 5, 7, 6, 4, 6};
        print(bubleSort(ints));
        print(selectSort(ints));
        print(insertSort(ints));
        print(shellSort(ints));
        print(quickSort(ints));
        print(mergeSort(ints));
        print(heapSort(ints));
    }

    public static void print(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + ",");
        }
        System.out.println();
    }


}
