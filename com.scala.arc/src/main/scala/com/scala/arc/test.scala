package com.scala.arc

object test {
    var  a = 10
    def main(args: Array[String]): Unit = {
        deprecated(0)
    }


    def deprecated(a:Int): Unit ={
        if (a>2) return
        deprecated(a+1)
        println(a)
        deprecated(a+2)
    }


}
