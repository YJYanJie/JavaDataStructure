package DataStructures.t4_stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Description: 逆波兰表达式（后缀表达式）
 *
 * @author YJ
 * @version 1.0
 * @date 2023/2/25 22:23
 */
public class PolandNotation {
    public static void main(String[] args) {
        //定义一个逆波兰表达式
        // (3+4)*5-6  -> 3 4 + 5 * 6 -
        // 4 * 5 - 8 + 60 + 8 / 2  -> 4 5 * 8 - 60 + 8 2 / +  == 64
        // 逆波兰表达式的数字和符号使用空格分隔
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";

        //思路
        //1. 先将 3 4 + 5 * 6 - 放入 ArrayList 中
        //2. 将 ArrayList 传递给一个方法，在该方法中配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("rpnList=" + list);
        int res = calculate(list);
        System.out.println("计算的结果是=" + res);
    }

    // 将一个逆波兰表达式，依次将数据和运算符放入到 ArrayList 中

    /**
     * 将一个逆波兰表达式，放入到 ArrayList 中
     * @param suffixExpression 逆波兰表达式
     * @return 存放逆波兰表达式的每个字符的 list
     */
    public static List<String> getListString(String suffixExpression){
        //将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, split);
        return list;
    }

    /*
     * 完成对逆波兰表达式的运算
     *      1. 从左至右扫描，将 3 和 4 压入堆栈
     *      2. 遇到 + 运算符，因此弹出 4 和 3，计算出 3 + 4 的值，得7，再将7入栈
     *      3. 将5入栈
     *      4. 接下来是 * 运算符，因此弹出 5 和 7，计算 7*5=35，将 35 入栈
     *      5. 将 6 入栈
     *      6. 最后是 - 运算符，计算 35 - 6 = 29，即得出最后结果
     */
    public static int calculate(List<String> ls){
        //创建一个栈，只需要一个栈
        Stack<String> stack = new Stack<>();

        //遍历 ls
        for (String item: ls){
            //使用正则表达式来确定取出得是数字
            if (item.matches("\\d+")){ //匹配得是多位数
                //入栈
                stack.push(item);
            }else {
                //pop 两个数，并进行运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                //将 res 入栈
                stack.push("" + res);
            }
        }
        //最后留在stack中得数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}
