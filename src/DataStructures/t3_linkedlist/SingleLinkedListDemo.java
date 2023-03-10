package DataStructures.t3_linkedlist;

import java.util.Stack;

/**
 * Description:
 *
 * @author YJ
 * @version 1.0
 * @date 2023/2/24 18:21
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);

        //按顺序添加
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero3);

        // 测试一下单链表的反转功能
        System.out.println("原来链表的情况~~");
        singleLinkedList.list();

        System.out.println("反转单链表~~");
        reverseList(singleLinkedList.getHead());
		singleLinkedList.list();

//        System.out.println("测试逆序打印单链表, 没有改变链表的结构~~");
//        reversePrint(singleLinkedList.getHead());

        //测试修改节点的代码
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
//        singleLinkedList.update(newHeroNode);
//        System.out.println("修改后的链表情况~~");

        //删除一个节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//        System.out.println("删除后的链表情况~~");
//        singleLinkedList.list();

        //测试一下看看是否得到了倒数第K个节点
//        HeroNode res = findLastIndexNode(singleLinkedList,3);
//        System.out.println("res=" + res);

    }

    /**
     * 新浪面试题：查找单链表中的倒数第 k 个结点
     * @param singleLinkedList 单链表
     * @param k 表示是倒数第 index 个节点
     * @return node
     */
    public static HeroNode findLastIndexNode(SingleLinkedList singleLinkedList, int k){
        //1. 获取链表的总长度
        int size = singleLinkedList.getLength();

        //2，倒数第 k 个节点 = 第 size - k + 1 个节点
        int index = size - k;
        return singleLinkedList.indexOf(index);
    }

    /**
     * 腾讯面试题：单链表的反转
     *
     * @param head 头节点
     */
    public static void reverseList(HeroNode head){
        //链表为空或者只有一个节点，则不需要处理
        if (head.next == null || head.next.next == null){
            return;
        }

        //创建新节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        HeroNode cur = head.next;
        HeroNode next; //当前节点的下一个节点
        //遍历原链表，每遍历一个节点，将其取出，并放在新链表的最前端
        while (cur != null){
            next = cur.next; //保存当前节点的下一个节点

            //将节点插入到新的头节点位置
            cur.next = reverseHead.next;
            reverseHead.next = cur;

            cur = next;
        }
        //遍历完链表后，将原链表的头节点，指向反转后的链表
        head.next = reverseHead.next;
    }

    /**
     * 百度面试题：逆序输出单链表
     *      1. 反转单链表，再次输出   ->  会破坏原有链表的结构
     *      2. 利用栈结构，将各个节点压入栈中，然后利用栈的先进后出的特点，实现逆序打印的效果
     * @param head 头节点
     */
    public static void reversePrint(HeroNode head){
        //链表为空
        if (head.next == null){
            return;
        }
        //创建一个栈，将链表的各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null){
            stack.add(cur);
            cur = cur.next;
        }

        //遍历栈，并输出
        while (stack.size() > 0){
            System.out.println(stack.pop()); //栈的特点：先进后出
        }
    }
}

//定义单链表 SingleLinkedList 管理节点
class SingleLinkedList {
    //初始化一个头节点,头节点不要动，不存放具体的数据
    private final HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单链表中,尾插法
     * 思路：不考虑编号排序时
     * 1. 找到当前节点的最后节点
     * 2. 将最后这个节点的next 指向 新的节点
     *
     * @param head 头节点
     */
    public void add(HeroNode head) {
        // 因为 head 节点不能动，需要一个辅助变量 temp
        HeroNode lastNode = getLast();
        // 得到单链表的最后一个节点，将最后这个节点的 next 指向 新的节点
        lastNode.next = head;
    }

    /**
     * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
     * (如果有这个排名，则添加失败，并给出提示)
     * @param heroNode 需要添加的节点
     */
    public void addByOrder(HeroNode heroNode){
        //头节点不能动，需要辅助变量，找到需要添加的位置的前一个节点，否则无法插入
        HeroNode temp = head;
        boolean flag = false; // 标志添加的编号是否存在，默认为false
        while (true){
            if (temp.next == null){
                //链表达到最后
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到，找到需要添加位置的前一个节点位置
                //找到第一个比需要添加的节点的编号大的节点的前一个节点位置即可
                break;
            }else if (temp.next.no == heroNode.no){
                //说明需要添加的 heroNode 的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next; //后移
        }
        //判断 flag
        if (flag){
            //说明编号已经存在，提示信息
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        }else {
            //插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }


    /**
     * 修改节点的信息，根据no编号修改，即no编号不能改
     *      说明： 根据 newHeroNode 的 no 来修改即可
     * @param newHeroNode 需要修改的节点
     */
    public void update(HeroNode newHeroNode){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空~");
            return;
        }

        //找到需要修改的节点
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true){
            if (temp == null){
                //遍历到链表最后
                break;
            }

            if (temp.no == newHeroNode.no){
                //说明找到需要修改的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //根据 flag 判断是否找到需要修改的节点
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else { //没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 根据 no 删除节点
     * @param no 编号
     */
    public void del(int no) {
        //1. 找到需要删除的节点的前一个节点
        HeroNode temp = head;
        boolean flag = false; //是否找到需要删除的节点
        while (true){
            if (temp.next == null){
                //已经到链表的最后
                break;
            }
            if (temp.next.no == no){
                //找到删除的节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next; //temp后移
        }
        //判断flag
        if(flag) { //找到
            //可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }


    /**
     * 找到单链表的尾节点
     *
     * @return 返回链表的尾节点
     */
    private HeroNode getLast() {
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }

    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (temp != null) {
            //输出节点的信息
            System.out.println(temp);
            //temp后移
            temp = temp.next;
        }
    }

    //获取链表的长度
    public int getLength(){
        //不统计头节点
        HeroNode cur = head.next;
        int len = 0;

        while (cur != null){
            len++;
            cur = cur.next;
        }
        return len;
    }

    //找到指定位置的节点信息
    public HeroNode indexOf(int index){
        //判断如果链表为空，返回null
        if(head.next == null) {
            return null;//没有找到
        }

        int size = getLength();
        //判断索引是否合法
        if (index < 0 || index > size){
            return null;
        }

        //从首元节点开始遍历
        HeroNode cur = head.next;
        int count = 0;

        while (cur != null && count < index){
            count++;
            cur = cur.next;
        }

        return cur;
    }
}


//定义一个HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}