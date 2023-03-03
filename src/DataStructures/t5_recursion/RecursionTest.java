package DataStructures.t5_recursion;

/**
 * Description: 递归回顾
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/3 14:50
 */
public class RecursionTest {

   /*
    * 递归调用规则：
    * 1. 当程序执行到一个方法时，就会开辟一个独立的空间(栈)
    * 2. 方法的局部变量是独立的，不会相互影响
    * 3. 如果方法中使用的是引用类型变量，就会共享该引用类型的数据
    * 4. 递归必须向退出递归的条件逼近，否则就是无限递归
    * 5. 当一个方法执行完毕，或者遇到 return，就会返回调用，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕
    */
   public static void main(String[] args) {
      // 通过打印问题，回顾递归的调用机制
      test(4);

      int res = factorial(3);
      System.out.println("res=" + res);
   }

   //打印问题
   public static void test(int n) {
      if (n > 2) {
         test(n - 1);
      } //else {
      System.out.println("n=" + n);
      // }
   }

   //阶乘问题
   public static int factorial(int n) {
      if (n == 1) {
         return 1;
      } else {
         return factorial(n - 1) * n; // 1 * 2 * 3
      }
   }
}
