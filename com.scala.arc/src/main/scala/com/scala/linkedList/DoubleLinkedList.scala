package com.scala.linkedList
object DoubleLinkedList{
    def main(args: Array[String]): Unit = {
        val list: DoubleLinkedList[Int] = new DoubleLinkedList[Int]
        for (elem <- 1 to 10) {
            list.add(elem)
        }
        list.foreach(ele =>print(ele.value.toString +"->"))
        list.delete(5)
        println()
        list.foreach(ele =>print(ele.value.toString +"->"))
        list.delete(1)
        println()
        list.foreach(ele =>print(ele.value.toString +"->"))
    }
}

class DoubleLinkedList[T:Ordering] {
    var head:Nodes[T] = _
    var tail : Nodes[T] =_
    def add(value :T): Unit ={

        val node: Nodes[T] = new Nodes[T](value, null, null)
        if (this.head == null){
            this.head = node
        }else{
            node.pre = this.tail
            this.tail.next = node
        }
        this.tail = node

    }

    def find(value :T): Nodes[T] ={
        val order: Ordering[T] = implicitly [Ordering[T]]
        var tmp: Nodes[T] = head
        while(tmp != null){
            if (order.equiv(value,tmp.value)) return tmp
            tmp = tmp.next
        }
        null
    }


    def delete(value: T):Boolean ={
        val ord: Ordering[T] = implicitly [Ordering[T]]
        if (this.head.value == null) false
        else {
            val findNode: Nodes[T] = this.find(value)
            findNode match {
                case null =>  false
                case node =>
                    if (ord.equiv(node.value,head.value)) {
                        this.head = this.head.next

                    }else{
                        node.pre.next = node.next
                        node.next.pre = node.pre
                    }

                            true
            }


        }

    }
    def foreach(op: Nodes[T] =>Unit): Unit ={
        var tmp: Nodes[T] = head
        while (tmp.next != null){
            op(tmp)
            tmp = tmp.next
        }

    }
}

case class Nodes[T](value:T,var pre:Nodes[T],var next :Nodes[T]){
    override def toString: String = value.toString
}