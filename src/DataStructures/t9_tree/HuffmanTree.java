package DataStructures.t9_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description: 哈夫曼树的实现
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/20 19:31
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);

        preOrder(root);
    }

    //编写前序遍历
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("是空树，不能遍历~~");
        }
    }

    //创建哈夫曼树的方法

    /**
     *
     * @param arr 需要创建成哈夫曼树的数组
     * @return 创建好后的哈夫曼树的 root 结点
     */
    public static Node createHuffmanTree(int[] arr) {
        //第一步，为了操作方便
        //1. 遍历 arr 数组
        //2. 将 arr 的每个元素构建成一个 Node
        //3. 将 Node 放入到 ArrayList 中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //处理过程是一个循环过程
        while (nodes.size() > 1) {

            //先排序（从小到大）
            Collections.sort(nodes);
//            System.out.println("nodes = " + nodes);

            //取出权值最小的两颗二叉树
            //(1) 取出权值最小的结点(二叉树)
            Node leftNode = nodes.get(0);
            //(2) 取出权值第二小的结点(二叉树)
            Node rightNode = nodes.get(1);

            //(3) 构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //(4) 从 ArrayList 删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //(5) 将 parent 加入到 nodes
            nodes.add(parent);
//        System.out.println("第一次处理后：" + nodes);
        }

        //返回哈夫曼树的结点
        return nodes.get(0);
    }
}


//创建结点类
//为了让 Node 对象支持排序Collections 集合排序
//让 Node 实现 Comparable 接口
class Node implements Comparable<Node>{
    int value; //结点权值
    Node left; //左子结点
    Node right; //右子结点

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 表示从小到大排序
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);

        if (this.left != null){
            this.left.preOrder();
        }

        if (this.right != null){
            this.right.preOrder();
        }
    }
}
