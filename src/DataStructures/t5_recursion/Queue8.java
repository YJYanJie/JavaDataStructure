package DataStructures.t5_recursion;

/**
 * Description: 八皇后问题：在 8 * 8 的棋盘上，摆放 8 个皇后，使任意两个皇后都不能处于同一行、同一列或同一斜线上，总计多少种摆法
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/3 15:52
 */
public class Queue8 {
    //定义一个 max 表示共有多少个皇后
    int max = 8;
    //定义数组 array，保存皇后放置位置的结果，
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        //测试一把 ， 8皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
    }

    //放置第n个皇后
    private void check(int n){
        if (n == max){ // n == 8时，其实8个皇后依然放好
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后 n，放到该行的第 1 列
            array[n] = i;
            //判断当前皇后是否会出现冲突
            if (judge(n)){ // 不冲突
                //接着放 n + 1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行 array[n] = i; 即将第 n 个皇后，后移放置
        }
    }

    //查看当我们放置第 n 个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突
    /**
     *
     * @param n 表示第 n 个皇后
     * @return 返回是否出现冲突 true 表示不冲突， false 表示冲突
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示判断第 n 个皇后是否和第 i 个皇后在同一条斜线
            // n = 1 i = 0      -> array[i] = 0 array[n] = 1
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){ //同一列 || 同一条斜线
                return false;
            }
        }
        return true;
    }



    //打印皇后拜访的位置结果
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
