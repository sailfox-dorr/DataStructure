package com.scala.threadTree

/**
 *
 * 红黑树 节点带颜色的属性 -- > 二叉查找树   根节点是 黑的 叶子节点是黑的
 *
 * 没个 红色的节点必须有两个 黑色的子节点
 *
 * 从任意一个简单节点到 叶子节点的 简单路径都包括相同的黑色节点
 *
 * 红黑树的效率比avl 树要比较高   --> treeSet 底层
 *
 */

object SortedBinaryTree {
    def main(args: Array[String]): Unit = {
        val tree: SortedBinaryTree[Int] = initTree()
        tree.infixForeach(a => print(a +"->"))
        tree.add(25)
        println()
        tree.infixForeach(a => print(a +"->"))
        println()
        println(tree.height())
        println(tree.leftHeight())
        println(tree.rightHeight())
        println(tree.delete(41))
        println(tree.delete(22))
        tree.infixForeach(x => print(x + "-> "))

    }
    def initTree(): SortedBinaryTree[Int] ={
        val ints: Array[Int] = Array(22, 5, 56, 45, 24, 21, 3, 41, 4)
//        val ints: Array[Int] = Array(3,4,1)
        val tree: SortedBinaryTree[Int] = new SortedBinaryTree[Int]
        ints.foreach(tree.add)


        tree
    }
}


class SortedBinaryTree[T: Ordering]{
    var root: Node[T] = _
    def add(value :T):Unit = {
        if (root ==null) root= new Node[T](value) else root.add(value)
    }
    def height():Int = {
        if (root == null) 0 else root.height()
    }
    def delete(v:T ):Boolean={
        if (root == null)  false
        else if (root.value == v ) {
            if (root.leftNode == null && root.rightNode == null) root = null
            else if (root.leftNode != null && root.rightNode != null) root.value= root.rightNode.deleteMin()
            else root = if (root.leftNode != null) root.leftNode else  root.rightNode
            true
        }else{
            root.delete(v)
        }

    }
    def leftHeight():Int={
        if (root == null ) 0
        else if(root.leftNode == null) 1
        else  root.leftNode.height() + 1
    }
    def rightHeight():Int={
        if (root == null ) 0
        else if(root.rightNode == null) 1
        else  root.rightNode.height()+1
    }
    def infixForeach(op:T =>Unit): Unit ={
        if (root == null) return
        else {
            root.infixForeach(op)
        }
    }
     class Node[T :Ordering](a:T){
         def deleteMin(): T = {
             var minNode :Node[T] = this
             while(minNode.leftNode != null){
                 minNode = minNode.leftNode
             }
             // 删除最小的右子节点 ，并把值赋给当前节点
             minNode.delete(minNode.value)

             minNode.value


         }

         private val ord: Ordering[T] = implicitly [Ordering[T]]
        var value: T = a
        var leftNode :Node[T] = _
        var rightNode :Node[T] = _
        var parent :Node[T] = _
        def  add(v :T) :Boolean={
           if (ord.lteq(v,value)) {
               if (leftNode==null) {
                   leftNode = new Node[T](v)
                   leftNode.parent = this
               }
               else leftNode.add(v)
               true

           }else  if (ord.lteq(value,v)){
               if (rightNode == null) {
                   rightNode = new Node[T](v)
                    rightNode.parent = this
               }
               else rightNode.add(v)
               true
           }else{
               println("节点存在")
               false
           }
        }
        def infixForeach(op: T =>Unit): Unit ={
            if (leftNode != null) leftNode.infixForeach(op)
            op(value)
            if (rightNode != null) rightNode.infixForeach(op)
        }

         def leftHeight():Int={
             if (leftNode == null) 0 else  leftNode.height()

         }
         def rightHeight() :Int ={
             if (rightNode == null) 0 else  rightNode.height()
         }
         def height() :Int ={
             val lh: Int = leftHeight()
             val rh: Int = rightHeight()
             lh.max(rh) +1
         }


        def delete(v:T):Boolean={

            /*
            分三种情况  ： 左右子树 全是 null  一个 null     两个null--
             */
            if (ord.equiv(value,v)){
                //得到要删除的 点
                var isLeft :Boolean = true
                // 判断不是根节点
                if (this.parent != null && this.parent.rightNode != null && ord.equiv(this.parent.rightNode.value,v)){
                    isLeft = false
                }

                if (leftNode == null && rightNode == null){
                    if (isLeft) this.parent.leftNode = null else this.parent.rightNode = null
                }else if(leftNode != null && rightNode != null){

                    /*
                     将右侧的最小的 节点和当前节点 交换位置
                     最小节点的父节点的左子节点向null
                     最小接待你的 右节点 指向左节点

                     */
                    value = rightNode.deleteMin()
                }
                else {
                    /*
                    此时 一个 为null 一个不为 null
                     */
                    val notNullNode: Node[T] = if (leftNode !=  null) leftNode else rightNode
                    // 非空节点指向父节点
                    notNullNode.parent = this.parent
                    // 当前节点的父节点指向当前节点的子节点
                    if(isLeft) this.parent.leftNode = notNullNode else  this.parent.rightNode = notNullNode
                    // 虽然 this 仍然 指向该子节点 ，是不是仍然资源浪费？

                }
                // 此处一定是可以删除的
                true
            }
                /*
                为不存在的节点
                 */
            else if (ord.lt(v,value)){
                if (leftNode == null) return false
                else leftNode.delete(v)
            }
            else{
                if (rightNode == null) return false
                else rightNode.delete(v)
            }

        }

    }



}
