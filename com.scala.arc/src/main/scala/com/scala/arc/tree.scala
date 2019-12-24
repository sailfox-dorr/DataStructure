package com.scala.arc

object tree {


    def main(args: Array[String]): Unit = {

        //二叉树的示意图

        val root: HeroNude = new HeroNude(1, "num1")
        val h1: HeroNude = new HeroNude(2, "num2")
        val h2: HeroNude = new HeroNude(3, "num3")
        val h3: HeroNude = new HeroNude(4, "num4")
        val h4: HeroNude = new HeroNude(5, "num5")
        root.left=h1
        root.right=h2
        h1.left=h3
        h3.right=h4
        val tree1: binartTree = new binartTree
        tree1.root=root
//        tree1.preOrder()
//        tree1.preSearch(5)
//        tree1.infixSearch(2)
//        tree1.preDel(2)
        tree1.preDel(6)
        tree1.preOrder()


        // 二叉树的删除节点
        // 如果删除的是叶子节点 ，删除 子节点 ，否则删除该子树

    }

}

class HeroNude(hNo:Int,hName:String){
    val no: Int = hNo
    val name: String = hName

    var left :HeroNude = _
    var right :HeroNude = _

    override def toString: String = s"name:$name,no:$no"
    def oneNode(): Unit ={
        ( this.left==null) ^ (this.right==null)


    }
    def deleteNode(no:Int): Unit ={
//          if (this.left !=null && this.left.no ==no && ){



    }

                                                                  def delete(no:Int): Unit ={
        //首先比较该节点的做子节点
        if (this.left !=null && this.left.no==no){
            this.left=null
            return
        }
        if (this.right !=null && this.right.no==no){
            this.right=null
            return
        }

        if (this.left !=null){this.left.delete(no)
        }
        if (this.right != null){
            this.right.delete(no)
        }


    }

    def infixSearch(no:Int):HeroNude={
        var resNode:HeroNude = null
        if (this.left!=null){
            resNode = this.left.infixSearch(no)
        }
        if (resNode != null){
            return resNode
        }
        if (this.no == no){
            return this
        }
        if (this.right != null){
            resNode = this.right.preSearch(no)
        }
        resNode


    }
    def preSearch(no:Int): HeroNude ={

        if (no == this.no){
            return this
        }
        var resNode:HeroNude = null
        if(this.left!= null){
            resNode = this.left.preSearch(no)
        }
        if (resNode != null){
            return resNode
        }
        if(this.right!=null){
            resNode = this.right.preSearch(no)
        }
        resNode

        }

    def preorder(): Unit ={
        //先把当前节点输出
        println(no,name)
        //向做递归，输出左子树
        if(this.left != null){
            this.left.preorder()
        }

        if (this.right != null){
            this.right.preorder()
        }
    }

}
class binartTree{
    var root:HeroNude = _
    def  preDel(no:Int): Unit ={
       if (root != null ){
            if (root.no ==no){
                root=null
                return
            }
           else {
                root.delete(no)
                return
            }
       }
        println("该节点不存在")
    }
    def preOrder(): Unit ={
        if (root != null){
            root.preorder()
        }else{
        print("二叉树为空")
        }
    }

    def  preSearch(no :Int): Unit ={
        if (root!=null){
            val nude: HeroNude = root.preSearch(no)
            if (nude == null) println("没找到") else println(nude)
        }else print("空数")
    }

    def  infixSearch(no : Int){
        if (root !=null){
            val nude: HeroNude = root.infixSearch(no)
            if (nude== null) println("没找到") else println(nude)
        }
        else {
            println("空树")
        }
    }
}