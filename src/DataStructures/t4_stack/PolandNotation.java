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

        //中缀表达式 -> 后缀表达式
        //1. 1+((2+3)×4)-5 => 转成  1 2 3 + 4 × + 5 –
        //2. 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)×4)-5"  -> 中缀的表达式对应的List
        //   即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的List -> 后缀表达式对应的List
        //   即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  ->  ArrayList [1,2,3,+,4,*,+,5,–]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList); // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式对应的List" + suffixExpreesionList); //ArrayList [1,2,3,+,4,*,+,5,–]

        System.out.printf("expression=%d", calculate(suffixExpreesionList)); // ?



        //定义一个逆波兰表达式
        // (3+4)*5-6  -> 3 4 + 5 * 6 -
        // 4 * 5 - 8 + 60 + 8 / 2  -> 4 5 * 8 - 60 + 8 2 / +  == 64
        // 逆波兰表达式的数字和符号使用空格分隔
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1. 先将 3 4 + 5 * 6 - 放入 ArrayList 中
        //2. 将 ArrayList 传递给一个方法，在该方法中配合栈完成计算
//        List<String> list = getListString(suffixExpression);
//        System.out.println("rpnList=" + list);
//        int res = calculate(list);
//        System.out.println("计算的结果是=" + res);
    }

    /*
     * 中缀表达式 -> 后缀表达式：
     *      1. 初始化两个栈：运算符栈 s1 和存储中间结果的栈 s2
     *      2. 从左至右扫描中缀表达式
     *      3. 遇到操作数时，将其压入栈 s2 中
     *      4. 遇到操作符时，比较其与 s1 栈顶运算符的优先级
     *          4.1 如果 s1 为空，或栈顶运算符为 左括号 "("，则直接将此运算符入栈
     *          4.2 否则，若优先级比栈顶运算符的高，也将该运算符入栈 s1
     *          4.3 否则，将 s1 栈顶的运算符弹出栈并压入到 s2 中，再次转到 4.1 与 s1 中新的栈顶运算符相比较
     *      5. 遇到括号时：
     *          5.1 如果是左括号 "("，则直接压入 s1 栈
     *          5.2 如果是右括号 ")"，则依次弹出 s1 栈顶的运算符，并压入 s2，直至遇到左括号为止，此时将这一对括号丢弃
     *      6. 重复步骤 2 至 5，直到表达式的最右边
     *      7. 将 s1 中剩余的运算符依次弹出并压入 s2
     *      8. 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    //将中缀表达式的list -> 后缀表达式的list
    public static List<String> parseSuffixExpreesionList(List<String> list){
        //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  ->  ArrayList [1,2,3,+,4,*,+,5,–]

        //1 定义两个栈
        Stack<String> s1 = new Stack<>(); //符号栈
        //说明：因为s2 这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        List<String> s2 = new ArrayList<>(); //存储中间结果 s2

        //2 从左向右遍历中缀表达式
        for (String item: list){
            //3 如果是数字，则直接压入 s2 中
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                //5.1 如果是左括号，则入栈 s1
                s1.push(item);
            }else if (item.equals(")")){
                //5.2 如果是右括号，则依次将 s1 的栈顶运算符压入 s2 中，直至遇到左括号为止，且不保存该对括号
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                //将与 item 对应的 ( 弹出 s1
                s1.pop();
            }else {
                //4 遇到操作符时，比较其与 s1 栈顶运算符的优先级
                //当 item 的优先级小于或等于 s1 栈顶运算符的优先级，将 s1 栈顶的运算符弹出栈并压入到 s2 中，再次转到 4.1 与 s1 中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(item) <= Operation.getValue(s1.peek())){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //7. 将 s1 中剩余的运算符依次弹出并压入 s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }

        //8. 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
        // s2 是一个 List，因此按顺序输出就是对应的后缀表达式对应的List
        return s2;
    }


    //方法：将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        char c;
        int j = 0;
        String str;//对多位数的拼接
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c < 48 || c > 57){
                ls.add(String.valueOf(c));
            }else {
                str = "" + c;
                //判断下一个字符是否是数字，如果是则拼接，直到不是数字为止
                while (i < s.length() - 1){
                    c = s.charAt(i + 1);
                    if (c >= 48 && c <= 57){
                        str += c;
                        ++i;
                    }else {
                        break;
                    }
                }
                ls.add(str);
            }
        }
        return ls;
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


//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation{
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }
}