package com.scala.threadTree
 object  AVLTree{
     def main(args: Array[String]): Unit = {

         val arr: Array[Int] = Array(12, 56, 14, 5, 56, 52, 42, 5, 65)
         val tree: AVLTree[Int] = new AVLTree[Int]
         arr.foreach(tree.add)
         tree.infixForeach((x: Int) => print(x +"->"))
         println()
         println(tree.leftHeight())
         tree.add(16)
         tree.add(27)
         tree.infixForeach((x: Int) => print(x +"->"))

         println(tree.search(27).value)



     }
    /**
     *最难的时旋转
     *
     * 平衡因子 --> 左子树的高度 - 右子树的高度
     * -1  0  1
     *
     * 左左节点  右旋
     *
     * 右右节点  左旋
     *
     * 左右情况  -- > 先左旋  再右旋
     *
     * 右左情况  --> 先右旋   再左旋
     *
     * 在完成元素的添加后  进行平衡
     *
     */
//    override def leftHeight():Int= {
//        1
//    }

//    def rotate()={
        /*
        左子树 的 高度 - 右子树的 高度 < -1
        让右节点的左节点 成为当前接待你的 右节点 ， 右节点成为当前节点的父节点 ， 当前节点成为当前节点的左节点

        左左 ：
        左子树的


         */


    // 使用以上节点的方法即可
//    override def rightHeight():Int={
//        1
//    }
//    def height():Int={
//        /**
//         * 计算 树的高度
//         * 找到左右子树的最高高度 +1
//         */
//
//        var lh: Int = leftHeight()
//        var rh: Int = rightHeight()
//        lh.max(rh) +1
//    }
//    class Node(val v:T){
//
//        var value: T = v
//        var left:Node = _
//        var right:Node = _
//        var parent :Node = _
//
//    }





}

class AVLTree[T: Ordering]{
    var root: AVLNode[T] = _
    def add(value :T):Unit = {
        if (root ==null) root= new AVLNode[T](value)
        else {
            // 注意是先添加元素 然后引发 root 发生不平衡
            //才需要调整root 节点的值
            root.add(value)
            while (root.parent != null){
                root = root.parent
            }

        }
    }
    def search(v:T):AVLNode[T] ={
        if (root == null) null else root.search(v)
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
    class AVLNode[T :Ordering](a:T){
        def deleteMin(): T = {
            var minNode :AVLNode[T] = this
            while(minNode.leftNode != null){
                minNode = minNode.leftNode
            }
            // 删除最小的右子节点 ，并把值赋给当前节点
            minNode.delete(minNode.value)

            minNode.value


        }

        private val ord: Ordering[T] = implicitly [Ordering[T]]
        var value: T = a
        var leftNode :AVLNode[T] = _
        var rightNode :AVLNode[T] = _
        var parent :AVLNode[T] = _
        def  add(v :T) :Boolean={
            if (ord.lteq(v,value)) {
                if (leftNode==null) {
                    leftNode = new AVLNode[T](v)
                    leftNode.parent = this
                }
                else leftNode.add(v)

            }else  if (ord.lteq(value,v)){
                if (rightNode == null) {
                    rightNode = new AVLNode[T](v)
                    rightNode.parent = this
                }
                else rightNode.add(v)

            }else{
                println("节点存在")

            }
            rotate()
            true
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
            val lh: Int = this.leftHeight()
            val rh: Int = this.rightHeight()
            lh.max(rh) +1
        }
//
//        def height(): Int = {
//            val lh: Int = leftHeight()
//            val rh: Int = rightHeight()
//            lh.max(rh) + 1
//        }
//
//        def leftHeight(): Int = {
//            if (leftNode == null) 0
//            else leftNode.height()
//        }
//
//        def rightHeight(): Int = {
//            if (rightNode == null) 0
//            else rightNode.height()
//        }


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
                    val notNullNode: AVLNode[T] = if (leftNode !=  null) leftNode else rightNode
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
        def  search(v:T):AVLNode[T] ={
            val ord: Ordering[T] = implicitly [Ordering[T]]

            if (ord.equiv(v, value)) {
                this
            } else if (ord.lt(v, value)) {
                if (leftNode == null) null
                else leftNode.search(v)
            } else {
                if (rightNode == null) null
                else rightNode.search(v)
            }
        }
        def leftRotate() :Unit = {
            // 左旋 ： 将左子节点
            /*
            操作步骤 ： 左
             */
            val tmpRight: AVLNode[T] = this.rightNode
            // 将节点将挂在当前节点的右节点
            val tmpRightLeft: AVLNode[T] = this.rightNode.leftNode
            // 当前 的 parent 父节点
            val p: AVLNode[T] = this.parent
            //
            this.rightNode = tmpRightLeft


            // 判断当前节点是父节点的 左节点还是右节点
            if (p != null ) {
                if (p.leftNode == this) p.leftNode = tmpRight else p.rightNode = tmpRight
            }
            //将当前节点作为右子节点的左子节点
            this.parent = tmpRight
            if (tmpRightLeft != null) tmpRightLeft.parent = this

            tmpRight.leftNode = this

            tmpRight.parent = p

        }

        def rightRotate():Unit ={
            /*
            右旋 左子节点的 右子节点指向当前节点
                当前节点的 左子节点的右子节点指向 当前节点
             */
            //左子节点的 右子节点指向当前接待你的左
            val tmpLeft: AVLNode[T] = this.leftNode
            val p: AVLNode[T] = this.parent
            val tmpLeftRight: AVLNode[T] = this.leftNode.rightNode

            this.leftNode = tmpLeftRight

            tmpLeft.rightNode = this
            //判断当前接待你是上一个子树的 做接待您还是右节点
            // 否则说明是根接待你
            if (p != null){
                if (p.leftNode == this) p.leftNode= tmpLeft else p.rightNode = tmpLeft
            }
            if (tmpLeftRight != null) tmpLeftRight.parent = this
            this.parent = tmpLeft
            // 如果 是 null ，则说明调整之后的该节点已经是根节点了
            tmpLeft.parent = p



        }


        def rotate():Unit = {

            // 左左 即左树高度比右树高 > 1 且左子节点 的 左树 比右树要高
            if (this.leftHeight() -this.rightHeight() > 1 && this.leftNode.leftHeight() >this.leftNode.rightHeight()){

                this.rightRotate()
                return
            }
            // 右右
            if (this.leftHeight() - this.rightHeight() < -1 && this.rightNode.rightHeight() > this.rightNode.leftHeight() ){
                this.leftRotate()
                return
            }
            if (this.leftHeight() -this.rightHeight() >1 && this.leftNode.rightHeight() > this.leftNode.leftHeight()){
                //左右的情况
                this.leftNode.leftRotate()
                this.rightRotate()
                return
            }
            //右左情况
            if (this.rightHeight() - this.leftHeight() >1 && this.rightNode.leftHeight() > this.rightNode.rightHeight()){
                this.rightNode.rightRotate()
                this.leftRotate()
                return
            }
        }



        }



}


