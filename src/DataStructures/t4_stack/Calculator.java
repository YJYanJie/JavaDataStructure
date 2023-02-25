package DataStructures.t4_stack;

/**
 * Description: 使用栈实现计算器（中缀表达式）
 *
 * @author YJ
 * @version 1.0
 * @date 2023/2/25 17:51
 */
public class Calculator {
    public static void main(String[] args) {
        /*
         * 完成表达式的运算
         * 1. 定义两个栈，一个存放数字 numStack，一个存放符号 operStack
         * 2. 通过一个 index(索引)，遍历表达式
         * 3. 如果 index 位置上的内容是数字，存放到数字栈(numStack);
         * 4. 如果是符号，分如下情况：
         *      4.1 如果当前符号栈为空，直接入栈(operStack)
         *      4.2 如果当前符号栈不为空，就进行比较
         *              如果当前的操作符的优先级小于或等于栈中的操作符，则从数栈中 pop 两个数，从符号栈中 pop 一个符号，进行运算，将得到的结果入数栈，然后将当前的操作符入符号栈
         *              如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
         *  5. 当表达式扫描完毕，就顺序的从数栈和符号栈中 pop 出相应的数和符号，并进行计算
         *  6. 最后在数栈只有一个数字，就是表达式的结果
         */
        String expression = "71*2*2-5+1-5+3-4";

        //1. 定义两个栈，符号栈 operStack；数栈 numStack
        ArrayStack2 operStack = new ArrayStack2(10);
        ArrayStack2 numStack = new ArrayStack2(10);

        //定义需要的相关变量
        int index = 0; // 用于扫面表达式
        int num1;
        int num2;
        char oper; //栈中的符号
        int res; //计算结果
        char ch; //扫描表达式的字符
        StringBuilder keepNum = new StringBuilder(); //用于拼接多位数

        //开始扫描表达式 expression
        for (int i = 0; i < expression.length(); i++) {
            //依次得到 expression 中的每一个字符
            ch = expression.charAt(i);
            //判断当前字符是数字还是符号,然后做相应的处理
            if (operStack.isOper(ch)) {
                //如果是运算符，查看当前栈是否为空，如果为空，直接入栈
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    //如果不为空，则比较表达式的符号与栈 pop 出的字符的优先级大小
                    if (operStack.priority(ch) <= operStack.priority((char) operStack.peek())) {
                        // 表达式的符号 小于或等于 栈 pop 出的字符的优先级，则从数栈中获取两个数字，从符号栈中获取一个符号，进行运算，
                        // 运算后的结果存入数栈中，并将扫描表达式的符号存入符号栈中
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        res = numStack.cal(num1, num2, operStack.pop());
                        //将运算后的结果存入数栈中
                        numStack.push(res);
                        //然后将符号入符号栈
                        operStack.push(ch);
                    } else {
                        // 表达式的符号 大于 栈 pop 出的字符的优先级，则直接入栈
                        operStack.push(ch);
                    }
                }
            } else {
                //如果是数字的话，则入数栈
//                numStack.push(ch - 48); // '1' -> 1 = ch - 48

                // 处理多位数时，不能发现一个 数就立即入栈，需要得到整个多位数，再进行入栈
                // 需要看当前扫描表达式的符号的后一位字符是否是数字，如果是则继续扫描，如果是符号则将多位数入栈
                // 使用一个字符串用于保存多位数
                keepNum.append(ch);

                //如果 ch 已经是表达式的最后一个字符时，直接入栈
                if (i == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum.toString()));
                } else {
                    //判断下一个字符是不是字符
                    if (operStack.isOper(expression.charAt(i + 1))) {
                        //如果后一位是运算符，则将多位数进行入栈
                        numStack.push(Integer.parseInt(keepNum.toString()));
                        keepNum = new StringBuilder(); //情况字符串
                    }
                }
            }
        }

        //表达式扫描完毕之后，就顺序的从 数栈和符号栈中 pop 出相应的数和符号，并进行运算
        while (!operStack.isEmpty()) {
            //如果符号栈为空，则计算到最后的结果, 数栈中只有一个数字【结果】
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = (char) operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        //将数栈的最后数，pop出，就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, res2);
    }
}


//创建 ArrayStack2 表示栈
class ArrayStack2 {
    private final int maxSize; //栈的大小
    private final int[] stack; //数组，模拟栈
    private int top = -1; //栈顶，初始化为 -1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否已满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        stack[++top] = value;
    }

    //出栈-pop，将栈顶的数据返回
    public int pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
//        return stack[top--];
    }

    //显示栈
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，由程序员确定，使用数字表示，数字越大则优先级越高
    public int priority(char oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假定表达式只有 +, - , * , /
        }
    }

    //判断当前字符是否是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //返回当前栈的栈顶的值，并不出栈
    public int peek() {
        return stack[top];
    }
}