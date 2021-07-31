package com.scala.heapSort
import scala.util.control.Breaks._
object HeapSort {
    //大顶推  ==> 父节点大于左右子节点
    def main(args: Array[String]): Unit = {
        //堆排序的思想: 把数组构建为一个大顶堆
        //通过调整非叶子节点 完后大顶堆的创建
        /**
         思路 :
          把无序的序列先构建成 一个堆
         //升序 大顶堆
         将堆顶的元素和末尾元素交换 ，将最大的元素沉到末尾顶端



         */
        val ints: Array[Int] = Array(6, 2, 5, 3, 9, 6,1,2,3,5,4,1,2)
        println(ints(0))
        println(adjustHeap(ints, 0, ints.length).mkString(","))
       // println(heapSort(ints).mkString(","))

    }

    def heapSort(): Unit ={

    }
    //小顶堆 : 每个子节点的大小均小于子节点
    /**
     *
     * @param arr  待调整的数组
     * @param i   表示非叶子节点的数组在数组中的索引
     * @param length  表示对多少个元素的数组进行调整（数组的长度在逐渐减少）
     */

    def adjustHeap(arr:Array[Int], i:Int,length:Int): Array[Int] ={
        //对数组进行调整
        var tmpi: Int = i
        var tmp :Int = arr(tmpi)
        //找处左子节点的位子
        var k: Int = tmpi * 2 +1
        breakable{
            while (k < length){
                //左子节点小于右子节点， 所以左+1
                //至最后一定能保证右边的是
                if ( k +1 < length && arr(k) < arr(k+1)){ k = k + 1}

                if(arr(k) > tmp){
                    arr(i) = arr(k)
                  // 每一个都将右树的值和arr(tmpi) 进行比较 , 最后得到的一定是一个 大顶堆啊
                    //最后进行交换
                    //把i 这一个根节点 的子树调整成了
                    tmpi = k
                    k = 2 * k + 1
                }else{
                    //                break
                    break()
                }

            }
        }

        /*
        结束后将以i 为 父节点 的 i 转化为一个大顶堆
         */
        // 将tmp的值和堆顶的元素呼唤
        arr(tmpi) = tmp
        arr




    }

    def heapSort(array: Array[Int]): Array[Int] ={
        //要求将数组进行升序排列
        println("heapSort!")
        for (elem <- (array.length/2  - 1) to 0 by -1) {
            //对非叶子节点进行交换
            adjustHeap(array, elem,array.length)
        }
        for (j  <- (array.length/2  - 1) to 0 by -1) {
            //对非叶子节点进行交换
            //对子树进行递归
            var temp: Int = array(j)
            array(j) = array(0)
            array(0) = temp
            adjustHeap(array, 0,j)
        }
        array






    }



}
class  HeapSort{


}
