package DataStructures.t10_huffmancode;

import java.util.*;

/**
 * Description: 哈夫曼编码
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/20 20:29
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length); //40

        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes = " + nodes);


        System.out.println("哈夫曼树");
        Node huffmanTree = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        preOrder(huffmanTree);
    }

    //前序遍历
    private static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("哈夫曼树为空");
        }
    }

    //

    /**
     *
     * @param bytes 接收字节数组
     * @return 返回 List 形式，
     */
    private static List<Node>  getNodes(byte[] bytes){
        //1. 创建一个 ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //2. 遍历 bytes，统计每个 byte 出现的次数：map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes){
            Integer count = counts.get(b);
            if (count == null){ //Map 还没有这个字符的数据
                counts.put(b, 1);
            }else {
                counts.put(b, count + 1);
            }
        }

        //3. 把 Map 中的每个键值对转成一个 Node 对象，并加入到 nodes 集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    //通过 List 创建对应的哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            //排序：从小到大
            Collections.sort(nodes);

            //取出前两个值
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //创建一个新结点，没有 data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理的两个结点从 List 中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新结点加入到 List 中
            nodes.add(parent);
        }
        //返回哈夫曼树的根节点
        return nodes.get(0);
    }
}

//创建Node，存放数据和权值
class Node implements Comparable<Node>{
    Byte data; // 存放数据本身 'a' = 97  ' ' = 32
    int weight; // 权值，字符的出现次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
