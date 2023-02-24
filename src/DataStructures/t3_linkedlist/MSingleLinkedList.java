package DataStructures.t3_linkedlist;

/**
 * 单链表带头结点的非循环实现
 * @author YJ
 * @create 2022-12-05 12:32
 */
public class MSingleLinkedList<E> {

    private Node head; //头结点
    private int size; //单链表的表长

    public MSingleLinkedList(){
        this.head = new Node(); //初始化时，创建一个头结点，没有数据，头节点不动，不存放具体的数据
    }

    //单链表的添加操作
    //任意指定位置插入
    public void add(int index, E data){
        //判断索引位置是否合法
        if (index < 0 || index > size()){
            throw new IllegalArgumentException("索引位置有误");
        }

        //头插法
        if (index == 0){
            addFirst(data);
            return;
        }

        //尾插法
        if (index == size){
            addLast(data);
            return;
        }

        //找到需要插入下标结点的前一个结点
        Node node = indexOf(index - 1);

        //进行结点的插入
        //创建新结点
        Node newNode = new Node(data);
        newNode.next = node.next;
        node.next = newNode;
        size++;
    }

    //找到指定位置的结点
    public Node indexOf(int index){
        //判断索引位置是否合法
        if (index < 0 || index > size){
            throw new IllegalArgumentException("索引位置有误");
        }

        Node cur = head.next; //从首元结点开始
        int count = 1;

        while (cur != null && count < index){
            count++;
            cur = cur.next;
        }
        return cur;
    }

    //头插法
    public void addFirst(E data){
        Node node = new Node(data); //需要插入的结点

        //第一次插入结点
//        if (head.next == null){
//            head.next = node;
//            size++;
//            return;
//        }

        //不是第一次插入结点
        node.next = head.next;
        head.next = node;
        size++;
    }

    //尾插法
    public void addLast(E data){
        Node node = new Node(data);

        //找到尾节点
        Node r = head;
        while (r.next != null){
            r = r.next;
        }

        //在尾节点后插入结点
        r.next = node;
        size++;
    }

    //删除
    //删除第一次出现关键字为 key 的结点
    public void remove(E data){

        //如果要删除头结点
        if (head.next.data.equals(data)){
            head.next = head.next.next;
            size--;
            return;
        }

        //找到要删除的结点的前驱结点
        Node prev = findPrev(data);
        if (prev == null){
            System.out.println("没有你要删除的结点");
            return;
        }

        //开始删除
        prev.next = prev.next.next;
        size--;

    }

    public void remove(int index){
        //可以删除头结点
        if (index < 0 || index > size){
            throw new IllegalArgumentException("删除位置不合理");
        }

        Node cur = head;
        int count = 0;
        while (cur.next != null && count < index - 1){ //找到第 index - 1 个元素
            cur = cur.next;
            ++count;
        }
        if (cur.next == null || count > index - 1) //删除 1~n 的位置
            throw new IllegalArgumentException("删除位置不合理");

        cur.next = cur.next.next;
        size--;

    }

    //找到删除结点的前一个结点
    public Node findPrev(E data){
        //遍历整个链表
//        Node node = head.next;
//        Node pre = node;
//
//        while (node != null){
//            if (node.data.equals(data)){
//                return pre;
//            }
//            pre = node;
//            node = node.next;
//        }
//        return null;

        //直接调用写好的方法即可
        return indexOf(findKey(data) - 1);
    }

    //删除所有值为 key 的结点

    //查询
    public int findKey(E data){
        //遍历整个链表
        Node node = head.next;
        int res = 0;

        while (node != null){
            res++;
            if (node.data.equals(data)){
                return res;
            }
            node = node.next;
        }
        return -1;
    }


    //单链表的表长
    public int size(){
        return size; //不算头结点的链表长度
    }

    public int sizeWhile(){
        Node cur = head.next;
        int count = 0;

        while (cur != null){
            count++;
            cur = cur.next;
        }

        return count;
    }

    //清空单链表
    public void clear(){
        //两种方式
        //方式一：暴力法
//        head.next = null;
//        head.data = null;

        //方式二：循环置空
//        Node node = head;
//        while (node != null){
//            Node next = node.next;
//            node.next = null; //指针域置空
//            node.data = null; //数据域置空
//            node = next;
//        }
        //从首元结点开始释放结点
        Node p = head.next;
        Node q;
        while (p != null){
            q = p.next; //q指向p的下一个结点
            p.next = null;
            p.data = null;
            p = q;
        }
        head.next = null; //头结点的指针域置为空

        size = 0;
    }

    //单链表的打印
    public void print(){
        if (isEmpty()){
            System.out.println("单链表为空");
            return;
        }
        System.out.println(head.next);
    }

    //判断单链表是否为空
    public boolean isEmpty(){
        return head.next == null;
    }
}
