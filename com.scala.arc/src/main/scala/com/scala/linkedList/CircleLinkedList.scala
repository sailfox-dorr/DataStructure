package com.scala.linkedList


object CircleLinkedList{
    def main(args: Array[String]): Unit = {
        print(100)
    }
    @scala.annotation.tailrec
    def print(value :Int): Unit ={
        println(value)
        print(value)
    }
}
class CircleLinkedList {
    def delete(): Unit ={

    }


    case class myNode[T: Ordering]()

}
