package com.scala.HerfmanTree




class Nodes  extends  Comparable[Nodes]{
    /*
    为了让Node 持续排序 ,实现Node 的comparaable 接口
     */
    var value :Int = _
    var  left:Nodes = _
    var  right :Nodes = _


    override def compareTo(o: Nodes): Int = - (this.value - o.value)
}
