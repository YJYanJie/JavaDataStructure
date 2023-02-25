package DataStructures.t4_stack;

import java.net.PortUnreachableException;
import java.util.Scanner;

/**
 * Description: 数组模拟栈
 *
 * @author YJ
 * @version 1.0
 * @date 2023/2/25 17:21
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("输入有误");
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}

//定义一个 ArrayStack 表示栈
class ArrayStack{
    private int maxSize; //栈的大小
    private int[] stack; //数组，模拟栈
    private int top = -1; //栈顶，初始化为 -1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈-push
    public void push(int value){
        //先判断栈是否已满
        if(isFull()){
            System.out.println("栈满");
            return;
        }

        stack[++top] = value;
    }

    //出栈-pop，将栈顶的数据返回
    public int pop(){
        //先判断栈是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
//        return stack[top--];
    }

    //显示栈
    public void show(){
        if(isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i > -1; i--){
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
