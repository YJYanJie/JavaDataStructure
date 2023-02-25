package DataStructures.t3_linkedlist;

/**
 * Description: 双向链表的实现
 *
 * @author YJ
 * @version 1.0
 * @date 2023/2/25 10:43
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        DoubleHeroNode hero1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode hero2 = new DoubleHeroNode(2, "卢俊义", "玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(3, "吴用", "智多星");
        DoubleHeroNode hero4 = new DoubleHeroNode(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        DoubleHeroNode newHeroNode = new DoubleHeroNode(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(4);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }
}

//定义一个双向链表
class DoubleLinkedList {
    // 初始化一个头节点，头节点不要动，不存放具体的暑假
    private final DoubleHeroNode head = new DoubleHeroNode(0,"","");

    //返回头节点
    public DoubleHeroNode getHead(){
        return head;
    }

    //遍历双向链表
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        DoubleHeroNode cur = head.next;
        while (cur != null){
            System.out.println(cur);
            cur = cur.next;
        }
    }

    //添加节点：尾插法
    public void add(DoubleHeroNode heroNode){
        DoubleHeroNode cur = head;
        while (cur.next != null){
            cur = cur.next;
        }
        // 当退出while循环时，cur就指向了链表的最后
        // 形成一个双向链表
        heroNode.pre = cur;
        cur.next = heroNode;
    }

    //修改节点,可以看到双向链表的节点内容修改和单向链表一样; 只是节点的类型修改
    public void update(DoubleHeroNode newHeroNode){
        // 判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点, 根据no编号
        // 定义一个辅助变量
        DoubleHeroNode temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { // 没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //从双向链表中删除节点
    public void del(int no){
        //判断当前链表是否为空
        if (head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }

        //1.直接找到要删除的节点
        DoubleHeroNode cur = head.next;
        boolean flag = false;
        while (cur != null){
            if (cur.no == no){
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag){
            //2.找到后进行删除操作
            cur.pre.next = cur.next;
            //如果是最后一个节点，就不需要执行下面这句话，否则会出现空指针异常
            if (cur.next != null) {
                cur.next.pre = cur.pre;
            }
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
}

//定义双向链表的节点
class DoubleHeroNode {
    public int no;
    public String name;
    public String nickname;
    public DoubleHeroNode pre; //指向上一个节点
    public DoubleHeroNode next; //指向下一个节点

    public DoubleHeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }


    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}