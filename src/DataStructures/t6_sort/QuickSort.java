package DataStructures.t6_sort;

import java.util.Arrays;

/**
 * Description: 快速排序
 *      1. 找到一个基准数据
 *      2. 通过一趟排序将要排序的数据分割成独立的两部分
 *      3. 比基准数据小的在其左边，比基准数据大的在其右边
 *      4. 然后在按照此方法对这两部分的数据分别进行快速排序
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/13 11:10
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        arr = new int[]{49, 59, 88, 37, 98, 97, 68, 54, 31, 3};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));

//        int[] arr = new int[8];
//        for(int i = 0; i < 8; i++) {
//            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
//        }

        long start1 = System.currentTimeMillis();
        //测试插入排序
        quickSort(arr, 0, arr.length - 1);
        long end1 = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end1 - start1));
        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left; //左下标
        int r = right; //右下表
        int mid = (left + right) / 2;
        int pivot = arr[mid]; //中轴值
        int temp = 0; //临时变量
        //while 循环的目的是让比 pivot 值小的放在左边，比 pivot 值大的放在右边
        while (l < r){
            //从 pivot 的左边开始找，直至找到第一个大于等于 pivot，才退出
            while (arr[l] < pivot){
                l++;
            }

            //从 pivot 的右边开始找，直至找到第一个小于等于 pivot，才退出
            while (arr[r] > pivot){
                r--;
            }

            //如果 l >= r 说明 pivot 的左右两边的值，已经按照左边全部是比 pivot 值小，右边全部比 pivot 值大
            if (l >= r){
                break;
            }

            //进行交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后， pivot == arr[l]，r--,前移
            if (arr[l] == pivot){
                r -= 1;
            }

            //如果交换完后， pivot == arr[r]，l++,后移
            if (arr[r] == pivot){
                l += 1;
            }
        }

        //如果 l == r，必须 l++, r--, 否则会出现栈溢出
        if (l == r){
            l += 1;
            r -= 1;
        }

        //进行左递归
        if (left < r){
            quickSort(arr, left, r);
        }

        //进行右递归
        if (right > l){
            quickSort(arr, l, right);
        }

    }

    public static void quickSort1(int[] arr, int left, int right){
        if (left >= right) return;
        int pivot = arr[(left + right) >> 1], l = left, r = right;
        int temp;
        while (l < r){
            while (arr[l] < pivot) l++;
            while (arr[r] > pivot) r--;
            if (l < r){
                temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }
        quickSort(arr, left, l - 1);
        quickSort(arr, l, right);
    }
}
