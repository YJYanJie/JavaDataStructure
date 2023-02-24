package DataStructures.t2_queue;

import java.awt.image.LookupOp;
import java.util.Scanner;

/**
 * Description: 数组模拟队列
 *
 * @author YJ
 * @version 1.0
 * @date 2023/2/24 16:17
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' '; //控制用户输入
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
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
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


//使用数组模拟队列:数组使用一次就不能用， 没有达到复用的效果            将此数组改成环形的队列，即使用 % 符号实现
class ArrayQueue {
    private int maxSize; // 数组的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 该数组用于存放数据

    //创建队列的构造器
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部的前一个位置，并不包含
        rear = -1; // 指向队列尾部（即就是队列的最后一个数据）
    }


    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否已满
        if (isFull()){
            System.out.println("队列满，不能添加数据~");
            return;
        }

        //添加元素
        arr[++rear] = n;
    }

    //数据出队
    public int getQueue(){
        //判断队列是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("队列为空，不能取出数据~");
        }
        return arr[++front];
    }

    //显示队列中的所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据~~");
            return;
        }
        for (int i = 0; i < arr.length; i++){
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    //显示队头数据，并非取出头部数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据~~");
        }

        return arr[front + 1];
    }
}
