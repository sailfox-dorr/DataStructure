package com.dorr.tree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author wb
 * @param <T>
 *
 * 替罪羊树的定义：
 * 1.是一种二叉排序树
 * 2.根节点存储了树的节点总数n和上次重建后的节点个数n上次；
 * 3.总能保持宽松的α高度平衡，即h<= hα + 1;
 *
 * 对于排序二叉树的根节点：
 * α的高度平衡 :h <= hα (其中 hα = log（1/α）n = -(logn/logα))，也就是（AVL，红黑树）
 * 宽松α的高度平衡 :h <= hα + 1(其中hα = log（1/α）n = -(logn/logα))
 * α权重平衡：(n左  <= α*n) && (n右  <= α*n)
 *
 * 当n一定时：
 * α越小，树越稠密，插入效率越低，查询效率越高
 * α越大，树越稀疏，插入效率越高，查询效率越低
 *
 * 从上面的三个式子可以看出：
 * 	满足 α权重平衡的树一定满足 α的高度平衡；满足 α的高度平衡的树一定满足宽松 α的高度平衡；
 */
public class ScapegoatTree <T extends Comparable<T>>{
    private class Node{
        T data;
        Node parent;
        Node left;
        Node right;
        public Node(T data, Node parent, Node left, Node right){
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
        public String toString(){
            return "[data="+data+"]";
        }
    }
    //根节点
    private Node root;
    //上次修改的节点数
    private int lastModifyNodeCount = 0;
    //节点总数好像也要记
    private int NodeCount = 0;
    //阀值α
    private static final double threshold = 0.57;

    public ScapegoatTree(){
        root = null;
    }
    public ScapegoatTree(T data){
        root = new Node(data, null, null, null);
        NodeCount ++;
    }
    public Node root(){
        return this.root;
    }
    /**
     * 将指定数据元素data添加到该替罪羊树
     * @param data：指定数据元素
     */
    public void add(T data){
        if(root == null){
            root = new Node(data, null, null, null);
            NodeCount ++;
        }
        else{
            //求得带加入节点的父节点
            Node parent = parent(data);
            if(parent != null){ //没有相同的数据元素节点
                int result = data.compareTo(parent.data);
                Node node = new Node(data, parent, null, null);
                if(result > 0){
                    parent.right = node;
                }else{
                    parent.left = node;
                }
                NodeCount ++;

				/*double ha = (-1) * Math.log10(NodeCount)/Math.log10(threshold);
				int deep_1 = deep_1(root);
				if(deep_1 <= ha){//满足阿尔法的高度平衡
					return;
				}else{*/
                //System.out.println("打破了树的阿尔法高度平衡:" + node.data);
                //这个地方有两种方案找出替罪羊节点。
                //1.从新插入的节点入手一层一层向上回溯直到找到第一个不满足α权重平衡的节点，即!((n左  <= α*n) && (n右  <= α*n))
                //但是这里需要记录每个节点下面的节点总数，这就需要在Node内部类中增加一个size域来保存它，从而增加了内存开销，也就是空间换时间吧.

                //2.从网上找的：从插入位置开始一层一层往上回溯的时候，对于每一层都进行一次判断h(v) > log(1/alpha )(size(tree))，
                //一直找到最后一层不满足该条件的层序号（也就是从根开始的第一层不满足该条件的层序号,或者说是最接近根的一层，当然也包括根本身），
                //然后从该层开始重构以该层为根的子树。这种方法的缺点是每次回溯比较都要计算一次h（v）。也就是遍历树了，虽然不一定是遍历整个树。但还是耗时啊。
                //上面式子的size（tree）我有两种理解：
                //	①size（tree）指的是整棵树的总节点数，如果是这样，那么它的替罪羊节点永远都会是根节点。原因如下：
                //	我们假设存在一点满足   h某节点  > log(1/alpha )(size(tree))，那么它的根节点也一定满足
                //				  h根 > h某节点  > log(1/alpha )(size(tree));
                //	又由于它是在找一个更接近根的替罪羊，所以替罪羊必定为根。这种做法也就是你不停地往树中插入元素时，当达到一定程度(h根 > log(1/alpha )(size(tree)))，
                //	就要对整棵树进行重构。
                //	②size（tree）指的是往上一层一层回溯时以某层为根节点的节点总数，如果是这样的话，那么它的替罪羊节点可能为根，也可能不为根。
                //	这种理解就需要记录每个节点下面的节点总数，这就需要在Node内部类中增加一个size域来保存以它为根的节点总数.
                //	我们假设存在一点刚好是根节点的右子节点满足  h右  > log(1/alpha )(size(tree右))
                //								h根 >= h右 + 1
                //								h根 < log(1/alpha )(size(tree右)+size(tree左) + 1)
                //	结合上面三个式子可以看出： log(1/alpha )(size(tree右)) + 1 < h右 + 1 <= h根 < log(1/alpha )(size(tree右)+size(tree左) + 1);
                //	即log(1/alpha)(size(tree右)*(1/alpha)) < log(1/alpha )(size(tree右)+size(tree左) + 1);
                //	又由于alpha的取值在[0.5, 1]，所以 上式就变成了 size(tree右)+size(tree右) < size(tree右)+size(tree左) + 1；
                //	即： size(tree右)<size(tree左)+1；很明显这种情况是可能出现的。
                //
                //	这里我采用第二种方案的第一种理解：因为我坚信替罪羊树定义的第二点：根节点存储了树的节点总数n和上次重建后的节点个数n上次；
                //	即除根节点外，其他节点不可以有除data、parent、left、right域的其他域。

                //找到替罪羊节点
                Node sgNode = scapegoatNode(node);
                if(sgNode == null){
                    return;
                }else{
                    System.out.println("打破了树的阿尔法高度平衡:" + node.data);
                    //存储替罪羊节点的父节点
                    Node prenodelink = sgNode.parent;
                    //暴力执法后的根节点
                    Node succnodelink = iDoNotKnow(sgNode);

                    //保存上次重构的节点总数
                    lastModifyNodeCount = NodeCount;

                    if(prenodelink == null){ //替罪羊节点是根节点
                        root = succnodelink;
                        succnodelink.parent = null;
                    }else{		//替罪羊节点不是根节点 。（ps：其实这里不用写，程序永远也进不来，因为我的做法导致了替罪羊节点必定是根节点）
                        succnodelink.parent = prenodelink;
                        if(sgNode == prenodelink.left){
                            prenodelink.left = succnodelink;
                        }else{
                            prenodelink.right = succnodelink;
                        }
                    }
                }
            }
        }
    }
    private Node iDoNotKnow(Node node){
        List<Node> nodes = clap(node);
        pickUp(nodes, 0, nodes.size() / 2, nodes.size() - 1);
        return nodes.get(nodes.size() / 2);
    }
    //拎起来
    private void pickUp(List<Node> nodes, int lstart, int index, int rend){
        if(nodes != null){
            Node current = nodes.get(index);
            current.left = current.right = null;  //这里的current.parent 不能赋为null
            //index索引处节点的左子节点
            if(lstart <= index - 1){
                Node lnext = nodes.get( (index + lstart) / 2);
                current.left = lnext;
                lnext.parent = current;
                //这个很重要，当某节点为重构树中的叶子节点时，一定要把它的左、右子节点赋为null；不然遍历的时候无穷无尽，导致stack异常
                if(lstart == index - 1){
                    lnext.left = lnext.right = null;
                }
                pickUp(nodes, lstart, (index + lstart) / 2, index - 1);
            }
            //index索引处节点的右子节点
            if(index + 1 <= rend){
                Node rnext = nodes.get((index + rend ) / 2 + 1);
                current.right = rnext;
                rnext.parent = current;

                if(rend == index + 1){
                    rnext.left = rnext.right = null;
                }
                pickUp(nodes, index + 1, (index + rend ) / 2 + 1, rend);
            }
        }
    }
    //暴力拍平 = 中序遍历
    private List<Node> clap(Node node){
        List<Node> nodes = new ArrayList<Node>();
        //Deque<Node> deque = new ArrayDeque<Node>();
        if(node.left != null){
            nodes.addAll(clap(node.left));
        }
        nodes.add(node);
        if(node.right != null){
            nodes.addAll(clap(node.right));
        }
        return nodes;
    }
    //找替罪羊
    private Node scapegoatNode(Node node){
        Node sgNode = null;
        Node current = node;
        double ha;
        int deep_1;
        while(current != null){
            ha = (-1) * Math.log10(NodeCount)/Math.log10(threshold); //替罪羊永远是根，其实没有必要回溯，只要对根进行判断。
            deep_1 = deep_1(current);
            if(deep_1 > ha){
                sgNode = current;
                current = current.parent;
            }else{
                current = current.parent;
            }
        }
        return sgNode;
    }
    //根据指定数据元素找他的父节点
    private Node parent(T data){
		/*if(root == null){ //就这个程序而言没必要判断吧，因为前面已经判断了一次。
			return null;
		}
		else{*/
        Node current = root;
        Node parent = current;
        int result = 0;
        while(current != null){
            parent = current;
            result = data.compareTo(current.data);
            if(result > 0){
                current = current.right;
            }else if(result < 0){
                current = current.left;
            }else{
                //这里什么也不写，这就导致了如果要加入的数据元素跟树中已存在的节点data域相同时，
                //不会加入该替罪羊树。
                return null;
            }
        }
        return parent;
    }
    //高度h
    private int deep_1(Node node){
        return deep(node) - 1;
    }
    //求以某节点为根的高度
    public int deep(Node node){
        if(node != null){
            if(node.left == null && node.right == null){
                return 1;
            }
            int leftDeep = deep(node.left);
            int rightDeep = deep(node.right);
            return leftDeep >= rightDeep ? leftDeep + 1 : rightDeep + 1;
        }
        return 0;
    }
    /**
     * 删除数据元素为data的节点
     * @param data
     *
     * 对于替罪羊树的删除有两种做法：
     * 1、向排序二叉树一样删除，然后进行一次     删除某节点后的节点总数n < 阀值α * 上次重建树的节点个数 lastModityNodeCount 判断。
     * 若满足，再次重构树。
     * 2.伪删除：替罪羊树的删除节点并不是真正的删除，而是惰性删除（即给节点增加一个已经删除的标记，删除后的节点与普通节点无异，只是不参与查找操作而已）
     * 很显然这种做法又要在Node内部类中新增加一个标记域。浪费空间。
     *
     * 这里我是第一种做法
     */
    public void remove(T data){
        Node node = find(data);
        if(node == null){
            return;
        }else{
            if(node.left == null && node.right == null){ //node为叶子节点
                if(node.parent == null){//node是根
                    root = null;
                }else{
                    if(node == node.parent.left){
                        node.parent.left = null;
                    }else{
                        node.parent.right = null;
                    }
                    node.parent = null;
                }
                NodeCount --;
            }else if(node.left != null && node.right == null){//node只有左子树
                if(node.parent == null){//node是根
                    root = node.left;
                    node.left.parent = null;
                    node.left = null;
                }else{
                    node.left.parent = node.parent;
                    if(node == node.parent.left){
                        node.parent.left = node.left;
                    }else{
                        node.parent.right = node.left;
                    }
                    node.parent = node.left = null;
                }
                NodeCount --;
            }else if(node.right != null && node.left == null){ //node只有右子节点
                if(node.parent == null){//node是根
                    root = node.right;
                    node.right.parent = null;
                    node.right = null;
                }else{
                    node.right.parent = node.parent;
                    if(node == node.parent.left){
                        node.parent.left = node.right;
                    }else{
                        node.parent.right = node.right;
                    }
                    node.parent = node.right = null;
                }
                NodeCount --;
            }else{
                //找到用于替换的后继节点
                Node succ = succNode(node);
                //替换数据
                node.data = succ.data;
                //删除替换的后继节点
                if(succ.parent == node){
                    node.right = succ.right;
                    if(succ.right != null){
                        succ.right.parent = node;
                    }
                    succ.parent = succ.right = null;
                }else{
                    succ.parent.left = succ.right;
                    if(succ.right != null){
                        succ.right.parent = succ.parent;
                    }
                    succ.parent = succ.right = null;
                }
                NodeCount --;
            }
            //删除过后看是否要重建整个树
            lastModifyNodeCount = lastModifyNodeCount == 0 ? NodeCount : lastModifyNodeCount;
            if(NodeCount < threshold*lastModifyNodeCount){
                Node newRoot = iDoNotKnow(root);

                root = newRoot;

                newRoot.parent = null;

                lastModifyNodeCount = NodeCount;
            }
        }
    }
    //
    public Node find(T data){
        Node current = root;
        int result;
        while(current != null){
            result = data.compareTo(current.data);
            if(result == 0){
                return current;
            }else if(result > 0){
                current = current.right;
            }else{
                current = current.left;
            }
        }
        return null;
    }
    /**
     * 某节点的后继节点(用于删除左、右子树不为空的节点)
     * @param node:某节点
     * @return：后继节点
     */
    public Node succNode(Node node){
        Node succ = null;
        int result;
        Node current = node;
        while(current != null){
            result = node.data.compareTo(current.data);
            if(result < 0){
                succ = current;
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return succ;
    }
    //广度优先遍历
    public List<Node> breadthFirstSearch(){
        return cBreadthFirstSearch(root);
    }
    private List<Node> cBreadthFirstSearch(Node node) {
        List<Node> nodes = new ArrayList<Node>();
        Deque<Node> deque = new ArrayDeque<Node>();
        if(node != null){
            deque.offer(node);
        }
        while(!deque.isEmpty()){
            Node first = deque.poll();
            nodes.add(first);
            if(first.left != null){
                deque.offer(first.left);
            }
            if(first.right != null){
                deque.offer(first.right);
            }
        }
        return nodes;
    }
    public static void main(String[] args) {
        ScapegoatTree<Integer> tree = new ScapegoatTree<Integer>();
        tree.add(40);
        System.out.println("加入40后："+tree.breadthFirstSearch());
        tree.remove(40);
        System.out.println("删除40后："+tree.breadthFirstSearch());
        System.out.println();

        tree.add(10);
        tree.add(8);
        tree.add(12);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(14);
        tree.add(16);
        System.out.println("加入16后："+tree.breadthFirstSearch());
        tree.add(18);
        System.out.println("加入18后："+tree.breadthFirstSearch());
        System.out.println();
        // 9*0.57 = 5.13

        tree.remove(14);
        tree.remove(16);
        System.out.println("删除14,16后："+tree.breadthFirstSearch());
        tree.remove(12);
        System.out.println("删除12后："+tree.breadthFirstSearch());
        tree.remove(18);
        System.out.println("删除18后："+tree.breadthFirstSearch());
    }
}
