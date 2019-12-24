package com.scala.arc

object  serchTree {
    def main(args: Array[String]): Unit = {

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

        // 使用谦虚的方式来查找 ，比较当前的节点 ，日过不是 ，就像做递归查找
    }



}
