package DataStructures.t3_linkedlist;

/**
 * Description:   约瑟夫问题
 *
 * @author YJ
 * @version 1.0
 * @date 2023/2/25 16:12
 */
public class Josephu {
    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);// 加入5个小孩节点
        circleSingleLinkedList.showBoy();

        //测试一把小孩出圈是否正确
        circleSingleLinkedList.JosephuBoy(10, 20, 125); // 2->4->1->5->3
        //String str = "7*2*2-5+1-5+3-3";
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个 first ，指向第一个节点，没有编号
    private Boy first = null;

    /**
     * 创建一个指定数字大小的环形链表
     * @param nums 指定数字大小
     */
    public void addBoy(int nums){
        //数据校验
        if (nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy cur = null;
        //完成环形链表的创建
        for (int i = 1; i <= nums; i++) {
            //创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1){
                //如果是第一个节点，则
                first = boy;
                first.setNext(first); // 构成环
                cur = first;
            }else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy; //cur后移
            }
        }

    }

    //遍历当前的环形链表
    public void showBoy(){
        if (first == null){
            System.out.println("没有任何数据~");
            return;
        }

        Boy cur = first;
        while (true){
            System.out.printf("小孩的编号 %d \n", cur.getNo());
            if (cur.getNext() == first)
                break;
            cur = cur.getNext();
        }
    }

    /**
     * 根据用户的输入，计算出小孩的出队的顺序
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum  表示数几下
     * @param nums  表示最初有多少小孩在圈中
     */
    public void JosephuBoy(int startNo, int countNum, int nums){
        //数据校验
        if (first == null || startNo < 1 || startNo > nums || countNum < 0){
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        Boy cur = first;
        while (cur.getNext() != first) {
            //将cur指向当前环形链表的最后一个节点
            cur = cur.getNext();
        }
        //小孩报数前，将 fitst 和 cur 指向当前报数的小孩位置， 即移动 startNo - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            cur = cur.getNext();
        }
        System.out.print("小孩出圈顺序:\t");
        //将 first 和 cur 同时移动 countNum - 1 次，即 first 指向需要出圈的节点
        //此时环形链表中只有一个节点，结束循环
        while (cur != first) {
            //让 first 和 helper 指针同时 的移动 countNum - 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                cur = cur.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("%d-->", first.getNo());
            //删除 first 指向的节点
            first = first.getNext();
            cur.setNext(first);
        }
        //此时就剩最后一个节点
        System.out.printf("%d \n", first.getNo());
    }
}

//创建节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}