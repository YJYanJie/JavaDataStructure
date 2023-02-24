package com.yj.linkedlist;

import com.sun.corba.se.spi.ior.iiop.IIOPFactories;

import java.util.Stack;

/**
 * @author YJ
 * @create 2022-09-29 16:22
 * 单链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //先创建节点
        Node node1 = new Node(1, "宋江", "及时雨");
        Node node2 = new Node(2, "卢俊义", "玉麒麟");
        Node node3 = new Node(3, "吴用", "智多星");
        Node node4 = new Node(4, "林冲", "豹子头");
        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //添加节点到链表中
        singleLinkedList.add(node1);
        singleLinkedList.add(node4);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);

        //按照编号的顺序添加节点
//        singleLinkedList.addByOrder(node1);
//        singleLinkedList.addByOrder(node4);
//        singleLinkedList.addByOrder(node2);
//        singleLinkedList.addByOrder(node3);
//        singleLinkedList.addByOrder(node3);

        //显示链表
        System.out.println("原来链表的情况：");
        singleLinkedList.list();

//        //修改节点
//        Node newNode = new Node(2, "张三", "法外狂徒");
//        singleLinkedList.update(newNode);
//
//        //显示链表
//        System.out.println("修改后的链表情况");
//        singleLinkedList.list();
//
//        //删除一个饥饿点
//        singleLinkedList.delete(1);
//        singleLinkedList.delete(4);
//        singleLinkedList.delete(1);
//        System.out.println("删除后的链表情况");
//        singleLinkedList.list();
//
//        //单链表中有效节点的个数
//        System.out.println("单链表的有效节点个数=" + getLength(singleLinkedList.getHead()));
//
//        //找到单链表中倒数第 k 个节点
//        Node lastIndexNode = findLastIndexNode(singleLinkedList.getHead(), 3);
//        System.out.println("lastIndexNode = " + lastIndexNode);

        //反转链表
//        reverseList(singleLinkedList.getHead());
//        System.out.println("反转后的链表：");
//        singleLinkedList.list();

        //反向输出
        reversePrint(singleLinkedList.getHead());
    }

    //将链表从链表表尾输出信息
    //方式1：反转链表，再输出
    //方式2：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(Node headNode){
        //链表为空，返回
        if (headNode.next == null){
            return;
        }
        //创建栈
        Stack<Node> stack = new Stack<Node>();
        Node cur = headNode.next;
        //将链表的所有节点入栈
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        //从栈顶到栈底输出,pop 出栈
        while (stack.size() > 0){
            System.out.println(stack.pop()); //stack的特点是先进后出
        }
    }

    /*
     * 思路：
     * 1. 编写一个方法，接收 head 节点，同时接收一个 index
     * 2. index 表示是倒数第 index 个节点
     * 3. 先把从头到尾遍历，得到链表的总长度 getLength()
     * 4. 得到链表总长度后，从链表的第一个节点开始遍历，即需要执行 size - index 次 next 操作
     */
    /**
     * 得到链表的总长度 size,从链表的第一个节点开始遍历，需要执行 size - index 次 next 操作，即可找到所需要的节点
     * @param headNode  单链表的头节点
     * @param index  倒数第 index 个节点
     * @return 查找单链表中的倒数第 index 个节点
     */
    public static Node findLastIndexNode(Node headNode, int index){
        //如果链表为空，返回null
        Node res = null;
        if (headNode.next == null){
            return res;
        }

        //第一次遍历，得到单链表的长度，不算头节点的长度
        int size = getLength(headNode);
        //第二次遍历 (size - index + 1) 位置，就是倒数第 k 个节点
        if (index <= 0 || index > size){
            return res;
        }
        //定义辅助指针，用于遍历单链表
        res = headNode.next;
        //使用 for 循环定位倒数的 index 位置
        for (int i = 0; i < size - index; i++) {
            res = res.next;
        }
        return res;
    }


    /**
     * 获取到单链表的节点的个数(如果是带头节点的链表，需要不统计头节点)
     * @param headNode
     * @return 返回单链表的有效节点个数
     */
    public static int getLength(Node headNode){
        int length = 0;
        if (headNode.next == null){
            return length;
        }

        //定义一个辅助指针，进行遍历,没有统计头节点
        Node cur = headNode.next;
        while (cur != null){
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }

    //单链表的反转
    /*
     * 思路：
     * 1. 先定义一个节点 reverseHead = new Node();
     * 2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表的最前端:
     * 3. 原来的链表的 head.next = reverseHead.next;
     */
    public static void reverseList(Node headNode){
        //当前链表为空，或者只有一个节点，无需反转，直接返回
        if (headNode.next == null || headNode.next.next == null){
            return;
        }

        //定义一个辅助指针，用于遍历原来的链表
        Node cur = headNode.next;
        Node pre = null; //指向当前节点的下一个节点
        Node reverseHead = new Node(0, "", ""); //定义反转后链表的头节点
        //遍历原来的链表，每遍历一个节点，就将其取出，放在新的链表的 reverseHead 的最前端
        while (cur != null) {
            pre = cur.next; //先暂时保存当前节点的下一个节点
            cur.next = reverseHead.next; //将 cur 的下一个节点指针指向新的链表的最前端
            reverseHead.next = cur; //将 cur 连接到新的链表上
            cur = pre; // cur 后移
        }
        //将 head.next 指向 reverseHead.next，实现单链表的反转。
        headNode.next = reverseHead.next;
    }
}

//定义SingleLinkedList,即链表,管理节点 Node
class SingleLinkedList{
    //先初始化一个头节点，头节点不动，不存放具体的数据
    private Node head = new Node(0, "", "");

    //添加节点到链表中
    //思路：当不考虑节点的编号时
    //1. 找到当前链表的最后一个节点
    //2. 将最后这个节点的 next 指向新节点，新节点的 next 置为空
    public void add(Node node){
        //因为 head 节点不能动，因此需要一个辅助变量 temp，用来遍历整个链表
        Node temp = head;

        //遍历链表,找到链表的最后一个节点
        while (true){
            if (temp.next == null) { //找到最后一个节点
                break;
            }
            //如果没有找到，则 temp 后移
            temp = temp.next;
        }
        //退出 while 循环时，temp 就指向了链表的最后一个节点
        //将 temp(最后一个节点) 的 next 指向新的节点
        temp.next = node;
        node.next = null;
    }

    //第二种添加节点的方式，按照 Node 中的 no 的顺序来添加节点
    //(如果链表中已经存在相同的 no 时，则添加失败，并给出提示)
    public void addByOrder(Node node){
        //创建辅助指针 temp，temp 指向的是头节点，而不是头节点的 next
        //用 temp 来找到需要进行添加操作的位置，即找到的添加位置的前一个节点。
        Node temp = head;
        boolean flag = false; // flag标志添加的编号已经存在，默认为false

        while (true){
            if (temp.next == null){ //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > node.no){//此时找到了需要添加的位置，就在 temp 的后面插入。
                break;
            }else if (temp.next.no == node.no){//此时说明希望添加的 node 的编号已经存在
                flag = true;
                break;
            }
            //temp 后移
            temp = temp.next;
        }

        //判断是否编号已经存在
        if (flag){//不能添加，编号已经存在
            System.out.printf("添加失败，准备插入的英雄的编号 %d 已经存在\n", node.no);
            return;
        }else {
            //可以添加 node 节点
            node.next = temp.next;
            temp.next = node;
        }
    }

    //修改节点的信息，根据 no 编号来进行修改，即 no 编号不能修改。
    public void update(Node node){
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }

        //找到需要修改的节点，根据 no 编号
        Node temp = head.next;
        boolean flag = false; // 表示是否找到该节点

        while (true){
            if (temp == null){
                System.out.println("链表遍历结束，未找到需要修改节点");
                break;
            }
            if (temp.no == node.no){
                //找到需要修改的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //根据 flag,判断是否找到要修改的节点
        if (flag){
            temp.name = node.name;
            temp.nickname = node.nickname;
        }else {// 没有找到需要修改的节点
            System.out.printf("没有找到编号 %d 的节点，不能修改\n", node.no);
        }
    }

    //删除节点:根据 no 进行删除操作
    //思路：
    //1. 使用辅助指针 temp 找到待删除节点的前一个节点 —— (即使用temp.next.no 与 no 进行比较)
    //2. temp.next = temp.next.next;
    //3. 待删除的节点没有其他引用指向它，会被垃圾回收机制回收
    public void delete(int no){
        Node temp = head;
        boolean flag = false; // 标志是否找到待删除的节点

        while (true){
            if (temp.next == null){ //已经到链表的最后，退出遍历
                break;
            }
            if (temp.next.no == no){ //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }

        if (flag){ //此时找到了待删除节点的前一个节点
            //进行删除操作
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    //显示链表[遍历]
    public void list(){
        //先判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //头节点不能动，需要一个辅助变量 temp，用来遍历整个链表
        Node temp = head; // temp 指向的是头节点，而不是头节点的 next

        while (true){
            //是否到链表最后
            if (temp.next == null){
                System.out.println("链表达到最后!");
                break;
            }
            //temp 后移
            temp = temp.next;
            //输出节点的信息
            System.out.println(temp);
        }
    }

    //返回头节点
    public Node getHead(){
        return head;
    }
}

//定义一个Node,每一个 Node 对象就是一个节点
class Node{
    public int no;
    public String name;
    public String nickname;
    public Node next; // next 域

    //构造器
    public Node(int hNo, String hName, String hNickname){
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    //重写 toString()，方便显示
    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}
