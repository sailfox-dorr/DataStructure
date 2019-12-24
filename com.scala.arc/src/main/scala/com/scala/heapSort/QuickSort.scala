package com.scala.heapSort

import scala.util.Random

object QuickSort{
    def main(args: Array[String]): Unit = {
//        val arr: Array[Int] = Array(12, 45, 4, 543, 24, 25)
//        new QuickSort[Int].quickSort(arr,0,arr.length-1)
//        println(arr.mkString(","))
        val q: QuickSort[Node] = new QuickSort[Node]
        val arr: Array[Int] = q.getArr(5)
        q.quickSort(arr)
        println(arr.mkString("->"))
        println(q.count)
        println(quick(arr).mkString(", "))


        def quick(arr: Array[Int]): Array[Int] = {
            /*
            函数式
             */
            arr match {
                case Array(a, rest@_*) =>
                    (quick(rest.filter((_: Int) > a).toArray) :+ a )++ quick(rest.filter((_: Int) <= (a)).toArray)
                case _=> Array[Int]()
                    //当分割到数组为空的时候，加一个空数组


            }
        }
    }


}

class QuickSort[T:Ordering] {

    var  count :Int = _
    lazy  val ord: Ordering[T] = implicitly [Ordering[T]]
    def quickSort(arr:Array[Int]): Unit ={
        this.quickSort(arr,0,arr.length-1)
    }
    def getArr(num :Int): Array[Int] ={
        val s: Int = num.toString.length - 1
        var a: Int = 1
        for (_ <- 2 until s) {
            a *= 10
        }
        println(a , s )

        val arr: Array[Int] = new Array[Int](num)
        for (elem <- 0 until num) {
            arr(elem) = Random.nextInt(10000)

            if (arr(elem).toString.length==4 && arr(elem) % a == 1){
                count += 1
            }
        }
        arr

    }
    def quickSort(arr:Array[Int],left: Int,right: Int): Unit ={
        if (left >= right) return
        val mid: Int = quickPartition(arr, left, right)
        quickSort(arr,left,mid-1)
        quickSort(arr,mid+1,right)



    }
    def quickPartition(arr:Array[Int],left: Int,right: Int):Int={
        var low: Int = left
        var high: Int = right
        val p: Int = arr(left)
        /*
        重复将对进行分组
        将大于某一个数字的放左边，小于的放右边，组后形成 分组内的无序的状态
         最后将起始位置的 值和high位置的值进行交换和分解
         */
        while (low < high){
            // 每交换一次 low 和 higher 各自交换一次
            while (low <= high && arr(low) >=p){
                low += 1
            }
            while (high >= low && arr(high)<= p){
                high -= 1
            }
            if (low < high){
                swap(arr,low,high)
            }
        }
        swap(arr,left,high)
        high


    }

    def swap(arr:Array[Int],a:Int,b:Int): Unit ={
        val tmp: Int= arr(a)

        arr(a) = arr(b)
        arr(b) = tmp
    }
}

case class Node(a:Int) extends Ordered[Node]{
    override def toString: String = a.toString

    override def compare(that: Node): Int = this.a - that.a
}

