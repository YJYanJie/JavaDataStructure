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

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果：" +  Arrays.toString(huffmanCodeBytes));
        System.out.println("长度 = " + huffmanCodeBytes.length);

        //测试 byteToBitString() 方法
//        System.out.println(byteToBitString((byte) 2));
        byte[] decode = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println(new String(decode));

//        System.out.println(contentBytes.length); //40
//
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println("nodes = " + nodes);
//
//
//        System.out.println("哈夫曼树");
//        Node huffmanTree = createHuffmanTree(nodes);
////        System.out.println("前序遍历");
////        preOrder(huffmanTree);
//
//        //是否生成了对应的哈夫曼编码
//        getCodes(huffmanTree);
//        System.out.println("生成哈夫曼编码表：" + huffmanCodes);
//
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);

    }


    //完成数据的解压
    /*
     * 1. 将 HuffmanCodeByte = [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     *    重新转成 哈夫曼编码对应的二进制字符串
     * 2. 将二进制字符串对应哈夫曼编码重新转成最开始的字符串
     */

    /**
     * 将一个 byte 转成一个二进制的字符串
     * @param b byte
     * @param flag 标志是否需要补高位，如果是 true，表示需要步高位，否则不补, 如果是最后一个字节，无须不高位
     * @return 该 byte 对应的二进制的字符串，(注意是补码返回)
     */
    private static String byteToBitString(boolean flag, byte b){
        int temp = b;
        //如果是正数，需要补高位
        if (flag) {
            temp |= 256; //按位与
        }

        String str = Integer.toBinaryString(temp); //返回的是 temp 对应的二进制的补码

//        System.out.println("str = " + str);
        if (flag) {
            return str.substring(str.length() - 8); //取 str 的最后8位
        }else {
            return str;
        }
    }

    //完成对压缩数据的解码

    /**
     *
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanBytes 哈夫曼编码处理过的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){
        //1. 先得到 huffmanBytes 对应的二进制的字符串
        StringBuilder stringBuilder1 = new StringBuilder();
        //将 byte 数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++){
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder1.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        //2. 把字符串按照指定的哈夫曼编码进行解码
        //把哈夫曼编码表进行调换，因为反向查询 97 -> 100, 100 -> a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }

        //3. 创建一个集合，存放 byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder1.length();){
            int count = 1; //小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag){
                //取出一个 '1' '0'
                String key = stringBuilder1.substring(i, i + count); //i 不动，让 count 移动，直到匹配到一个字符
                b = map.get(key);
                if(b == null){ //说明没有匹配到
                    count++;
                }else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        //当 for 循环结束后，list 中就存放了所有的字符
        byte[] resByte = new byte[list.size()];
        for (int i = 0; i < resByte.length; i++){
            resByte[i] = list.get(i);
        }

        return resByte;
    }


    //使用一个方法，将前面的方法封装起来，便于调用
    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过哈夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes){
        //1. 将 bytes 转成 List
        List<Node> nodes = getNodes(bytes);
        //2. 根据 nodes，创建哈夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        //3. 根据哈夫曼树得到哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        //4. 经过哈夫曼编码处理后的字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }


    //方法：将字符串对应的 byte[] 数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的 byte[]
    /**
     *
     * @param bytes 原始字符串对应的 byte[]
     * @param huffmanCodes 生成的哈夫曼编码 map
     * @return 返回哈夫曼编码处理后的 byte[]
     * 举例：String content = "i like like like java do you like a java";  byte[] contentBytes = content.getBytes();
     * 返回的是 "10101000101111111100100010111111110010001011111111001001010011011100011100000110111010001111001010
     * 00101111111100110001001010011011100"     对应的 byte[] huffmanCodeBytes, 即 8 位对应一个 byte，放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] = 10101000(补码) => byte [推导 反码(补码的符号位不变 - 1) -> 原码(反码的符号位不变，其他位取反)] -> -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        //1. 利用 huffmanCodes 将 bytes 转成 哈夫曼编码对应的字符串
        StringBuilder sb = new StringBuilder();
        //遍历 bytes 数组
        for (byte b : bytes){
            sb.append(huffmanCodes.get(b));
        }
//        System.out.println("sb = " + sb.toString());

        //2. 将得到的字符串转成 byte[] 数组
        //统计返回 byte[] huffmanCodeBytes 的长度
        // int len = (sb.length() + 7) / 8;
        int len;
        if (sb.length() % 8 == 0){
            len = sb.length() / 8;
        }else {
            len = sb.length() / 8 + 1;
        }
        //创建 存储压缩后的 byte 数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; //记录第几个 byte
        for (int i = 0; i < sb.length(); i += 8){ //因为是每 8 位对应一个 byte，步长 + 8
            String strByte;
            if (i + 8 > sb.length()){ //不够 8 位
                strByte = sb.substring(i);
            }else {
                strByte = sb.substring(i, i + 8);
            }
            //将 strByte 转成一个 byte，放入 huffmanCodeBytes 数组
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeBytes;
    }


    //生成哈夫曼树对应的哈夫曼编码
    //思路：
    //1. 将哈夫曼编码表存放在 Map<Byte, String>
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2. 在生成哈夫曼编码表时，需要拼接路径，定义一个 StringBuilder，存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方面，重载 getCodes
    private static Map<Byte, String> getCodes(Node root){
        if (root == null){
            return null;
        }
        //处理 root 的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理 root 的右子树
        getCodes(root.right, "1", stringBuilder);

//        getCodes(root, "", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入的 node 结点的所有叶子结点对应的哈夫曼编码，放入到 huffmanCodes 集合中
     * @param node 传入结点
     * @param code 路径，左子结点：0   右子结点：1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将 code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null){ //如果 node == null 不处理
            //判断当前 node 是叶子结点还是非叶子结点
            if (node.data == null){ //非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            }else { //叶子结点
                //将表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
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
