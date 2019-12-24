package com.scala.heapSort

import scala.util.Random


object HeapSorts {
    def main(args: Array[String]): Unit = {
        val arr: Array[Int] = new Array[Int](10)
        for (elem <- 0 until  10) {
            arr(elem) = Random.nextInt(1000)

        }
        heap(arr)

    }
    def heapSort(arr:Array[Int],i :Int,length:Int): Unit ={
        // 1 . 将一个堆调整成一个大顶堆
        //从 arr.length-1 /2 位置开始 遍历整个数组生成一个大顶堆


    }
    def heap(array: Array[Int]):Unit={
        if (array == null || array.length <= 1) return;
        // 建堆。

        var  len: Int = array.length;
        /*
        对这个对做第一大顶堆化
         */
        for (i <- (array.length - 1) / 2 - 1 to 0 by -1) {
            //倒序的 时候已经将最大值上移了
            adjustHeap(array, i, array.length)
        }
        //对每一个堆左大顶堆化 ， 并将顶部元素和 下方的元素 进行比较
        // 总最大的堆位置进行遍历
        while (len > 1) {
            // 把堆顶和最后一个元素交换。
            swap(array, 0, len - 1);
            // 交换完之后，逻辑上去掉最后一个元素。
            len -=1;
            // 重新调整堆的顺序。
            adjustHeap(array, 0 , len);

            // 把每一趟排序的结果也输出一下。
//            println(array.mkString(", "));
        }
        println(array.mkString(", "));

    }


    def adjustHeap(array:Array[Int],s:Int,length: Int): Unit ={
        // 将堆调整为一个大顶堆
         var i: Int =  s
         while (true){
           var maxPosition: Int = i
           var leftChild:Int = 2 * i +1
           var rightChild: Int = 2 * i +2

           if (leftChild < length && array(leftChild) > array(maxPosition)){
               maxPosition = leftChild
           }
           if (rightChild < length && array(rightChild) > array(maxPosition)){
               maxPosition = rightChild
           }
           if (maxPosition == i){
                // 说明已经是堆顶了 不用再找了！
               return
           }
           else {

               swap(array,i,maxPosition)
               i = maxPosition

           }



       }


    }
    def swap( array: Array[Int],a :Int, b : Int): Unit ={
        var tmp = array(a)
        array(a) = array(b)
        array(b) = tmp
    }
}
