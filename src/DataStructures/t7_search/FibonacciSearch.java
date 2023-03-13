package DataStructures.t7_search;

import java.util.Arrays;

/**
 * Description: 斐波那契(黄金分割法——0.618)查找
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/13 22:08
 */
public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println("index = " + fibSearch(arr, 1));
    }

    //需要使用到斐波那契数列，因此需要先获取到一个斐波那契数列
    //非递归方法得到一个斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++){
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;
    }

    //斐波那契查找算法 —— 非递归方式
    public static int fibSearch(int[] arr, int key){
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0; //存放 mid 值
        int f[] = fib(); //获取到斐波那契数列
        //获取到斐波那契分割数值的下标 k
        while (high > f[k] - 1){
            k++;
        }
        //因为 f[k] 的值可能大于 数组的长度，因此，需要使用 Arrays 类，构造新的数组，并执行 arr[]
        //不足的部分会使用 0 填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //实际上，使用 arr 数组最后的数进行填充 temp,使得其有序
        for (int i = high + 1; i < temp.length; i++){
            temp[i] = arr[high];
        }

        //使用 while 循环，找到需要的数 k
        while (low <= high){
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]){ //应该继续向数组的左边进行查找
                high = mid - 1;
                //说明：
                //1. 全部元素 = 前面的元素 + 后边的元素
                //2. f[k] = f[k - 1] + f[k - 2]
                // 因为前面有 f[k - 1] 个元素，所以可以继续拆分 f[k - 1] = f[k - 2] + f[k - 3]
                // 即 在 f[k - 1] 的前面继续查找 k--
                // 即下次循环 mid = f[k - 1 - 1] - 1
                k--;
            }else if (key > temp[mid]){ //应该向数组的右边进行查找
                low = mid + 1;
                //说明：
                //1. 全部元素 = 前面的元素 + 后边的元素
                //2. f[k] = f[k - 1] + f[k - 2]
                //3. 因为后面有 f[k - 2] 个元素，所以可以继续拆分 f[k - 2] = f[k -3] + f[k -4]
                //4. 即在 f[k - 2] 的前面可以继续查找 k -= 2
                //5. 即下次循环 mid = f[k - 1 - 2] - 1
                k -= 2;
            }else {
                //找到了
                //需要确定返回的是哪个下标
                if (mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
