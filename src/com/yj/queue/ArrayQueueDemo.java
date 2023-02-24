package com.yj.queue;

import java.util.Scanner;

/**
 * @author YJ
 * @create 2022-09-29 13:46
 * 用数组实现队列，目前实现方法，数组只能使用一次，不能重复使用 --> 优化方案：将数组模拟成环形队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接受用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0); //接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数据：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try{
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    }catch (Exception e){
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


//使用模拟队列——编写 ArrayQueue
class ArrayQueue{
    private int maxSize; // 表示数组的最大容量
    private int front; // 指向队头
    private int rear; // 指向队尾
    private int[] arr; // 该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        front = -1; // 指向队列头部的前一个位置。
        rear = -1; // 指向队列尾部，指向队尾的数据(即就是队列最后一个数据)
    }

    //判断队列是否已满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //首先判断队列是否已满
        if(isFull()){
            System.out.println("队列已满，不能添加数据！");
            return;
        }
        rear++; //队尾后移
        arr[rear] = n; //赋值给队尾
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("队列为空，不能取出数据!");
        }
        front++; // 队尾后移
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列为空，没有数据!");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d \n", i, arr[i]);
        }
    }

    //显示队列的头数据，不是取出数据
    public int headQueue(){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据！");
        }

        return arr[front + 1];
    }

}
