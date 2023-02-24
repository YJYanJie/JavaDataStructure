package com.yj.queue;

import java.util.Scanner;

/**
 * @author YJ
 * @create 2022-09-29 14:14
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列
        System.out.println("测试数组模拟环形队列的案例：");
        CircleArray queue = new CircleArray(3);
        char key = ' '; //接受用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0); //接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数据：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

//使用数组模拟环形队列代码实现
/*
 * 思路如下：
 * 1. front 变量指向队列的第一个元素位置；front 初始值为0
 * 2. rear 变量指向队列的最后一个元素位置的后一个位置,因为希望空出一个空间做为约定； rear 初始值为0
 * 3. 队满的条件：(rear + 1) % maxSize == front;
 * 4. 队空的条件：rear == front;
 * 5. 队列中有效的数据的个数：(rear + maxSize - front) % maxSize
 */
class CircleArray {
    private int maxSize; // 表示数组的最大容量
    private int front; // front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素  front 初始值为0
    private int rear; // rear 指向队列最后一个元素位置的下一个位置。  rear 初始值为0
    private int[] arr; // 该数组用于存放数据, 模拟队列

    //创建队列的构造器
    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //首先判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满，不能添加数据！");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将 rear 后移，需要考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空，不能取出数据!");
        }
        //直接取出数据
        int value = arr[front];
        //front 后移，需要取模
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空，没有数据!");
            return;
        }

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d \n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，不是取出数据
    public int headQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据！");
        }
        return arr[front];
    }

}
