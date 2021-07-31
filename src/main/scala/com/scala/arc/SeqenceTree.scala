package com.scala.arc

object SeqenceTree{

    def main(args: Array[String]): Unit = {
        val ints: Array[Int] = Array(1, 2, 3, 4, 5, 6)
//        new SeqenceTree(ints).preOrder(0)
        val ints1: Array[Int] = Array(7, 5, 64, 2, 8, 34, 9, 72,6, 46)

        val tree1: SeqenceTree = new SeqenceTree
        val node: Node = tree1.addNodes(ints1)
        tree1.threadNodes(node)
        tree1.infix()






    }
}

class SeqenceTree() {
    val arr=Array()
    var root :Node= _
    var pre :Node = _

    def  infix(): Unit ={
        infixOrder(this.root)
    }
    def infixOrder(no: Node): Unit ={
        println(no)
        var node :Node = null
        if (no == null) return
        while (node != null){
            while (node.leftType == 0){node = node.left}
            println(node)
            while (node.rightType == 1){
                node = node.right
                println(node)
            }
            node = node.right

        }



    }

    def threadNodes(node:Node): Unit ={
        //编写现所化现所属的方法
        //中序遍历现所属

        if (node ==null) return
        //向左遍历,现所化
        //线索化当前的节点 ，需要一个前驱节点
        threadNodes(node.left)

        if (node.left ==null){
            node.left=pre
            //改变节点的状态
            //type = 0表示指向左子树
            node.leftType = 1
            //最左的前驱节点就是一个null

        }


        if (pre != null  && pre.right == null){
            //pre的后继节点是 右指针所在的节点
            pre.right = node
            pre.rightType =1
        }
        //移动完成后，将指针后移
        pre = node

        //中间的处理步骤
        //如果当前节点的左指针为空 ，使其 做指针指向 pre,siugai type

        threadNodes(node.right)

    }



    //顺序存储二叉树
    //二叉树以数组的方式来进行存储
    /*
             1
          2    3
        4  5  6  7
        以上数字对应数组的索引

        规律1 ： 第 n个元素的左子节点是数组的第一个元素

        特点  ： 第 个元素的左子节点是2 * n +1
                右子节点四 2 *  n +2
                第n个元素的父节点是 n-1/2


                顺序二叉树通常考虑完全二叉树

     */
    //给一个数组Array(1,2,3,4,5,6) 前序遍历的方式
    def preOrder(): Unit ={
        preOrder(0)
    }



     private  def preOrder(index:Int):Unit={


        if (arr == null ||  arr.length ==0){
            println("数组为空")
            return
        }
        println(arr(index))
        if ((index*2+1< arr.length)){
            preOrder(index*2+1)
        }
        if (index *2 +2<arr.length){
            preOrder(index*2 +2)
        }

    }
    def addNode(node:Node): Unit ={
        if (root == null){
            root = node
        }else{
            root.addNode(node)
        }
    }
    def addNodes(arr:Array[Int]): Node ={
        for (elem <- arr) {
            addNode(new Node(elem))
        }
        root

    }


}

class  Node(valu:Int){
    //使用中序遍历对数组进行到树种插入
    val value :Int =valu
    var left :Node =  _
    var right :Node = _
    var leftType:Int =_
    var rightType:Int = _

    //二叉排序树删除

    def searchParent(node: Node):Node={
        if (node == null){
            throw new Exception
        }
        //首先判断当前节点的左后石佛偶是这个值
        if ((this.left != null && this.left.value ==node.value)||(this.right != null && this.right.value ==node.value
            )){
           return this
        }else{
            if (this.left != null && node.value < this.value ){
                return  this.left.searchParent(node)
            }
            if (this.right != null && node.value > this.value ){
                return this.right.searchParent(node)
            }
            null
        }




    }
    def search( va :Int): Node={
        //找到该节点
        //根据值来进行查找
        //判断房前的节点时候需要删除
        if (va ==this.valu){
            return this
        }
        else if(va < this.valu){
            if (this.left == null){
                return null
            }
            else {
                return this.left.search(va)
            }
        }else{
            if (this.right == null){
                return  null
            }else{
                return this.right.search(va)
            }

        }
    }

    def  addNode(node:Node): Unit ={

        if (node == null){
            //如果节点为空，不做处理
            return
        }

        if(node.value <=  this.value){
            if (this.left==null){
                this.left = node

            }else{
                this.left.addNode(node)

            }
        }

        if (node.value >this.value){
            if (this.right == null){
                this.right = node
            }else{
                this.right.addNode(node)
            }
        }


    }

    override def toString: String = s"value : $value"

    def infixOrder(): Unit ={
        //中序遍历
        if (this.left !=null){
           this.left.infixOrder()
        }
        println(this)
        if (this.right != null){
            this.right.infixOrder()
        }

    }
}
