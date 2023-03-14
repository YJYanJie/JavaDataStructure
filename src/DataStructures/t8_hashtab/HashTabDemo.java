package DataStructures.t8_hashtab;

import java.util.Scanner;

/**
 * Description: 哈希表
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/14 19:10
 */
public class HashTabDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next; //默认为 null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//哈希表的创建,管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    //构造器
    public HashTab(int size){
        this.size = size;
        //初始化 empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的 id，得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将 emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表,遍历 hashtab
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id){
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null){//找到
            System.out.printf("在第 %d 条链表中找到 id = %d\n", (empLinkedListNO + 1), id);
        }else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    //散列函数，简单的取模法
    public int hashFun(int id){
        return id % size;
    }
}



//创建 EmpLinkedList, 链表
class EmpLinkedList{
    //头指针，执行第一个 Emp，直接指向第一个 Emp
    private Emp head; //默认为 null

    //添加雇员到链表中
    public void add(Emp emp){
        //尾插法
        //如果是添加第一个雇员
        if (head == null){
            head = emp;
            return;
        }

        Emp temp = head;
        while (temp.next != null){
            temp = temp.next;
        }

        //退出时，将 emp 加入到链表中
        temp.next = emp;
    }

    //遍历链表信息
    public void list(int no){
        if (head == null){
            System.out.println("第 " + (no + 1) + " 链表为空");
            return;
        }

        System.out.print("第 " + (no + 1) + " 链表的信息为：");
        Emp cur = head; //辅助指针
        while (true){
            System.out.printf("=> id = %d name = %s\t", cur.id, cur.name);
            if (cur.next == null){ //cur 是最后一个结点
                break;
            }
            cur = cur.next;
        }
        System.out.println();
    }

    //根据 id 查找雇员
    //如果查找到，就返回 Emp， 否则就返回 null
    public Emp findEmpById(int id){
        //判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        Emp cur = head;
        //找到
        while (cur.id != id) {
            //退出
            if (cur.next == null) {
                //说明遍历完毕，也没有找到
                cur = null;
                break;
            }
            cur = cur.next;
        }
        return cur;
    }
}
