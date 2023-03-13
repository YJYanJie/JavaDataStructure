package DataStructures.t6_sort;

import java.util.Arrays;

/**
 * Description: 归并排序(分治策略)
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/13 12:53
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length]; //需要额外的空间开销
        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println("归并排序后 = " + Arrays.toString(arr));
    }

    //分 + 合方法
    public static void mergeSort(int[] arr, int left, int rigth, int[] temp){
        if (left < rigth){
            int mid = (left + rigth) / 2; //中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, rigth, temp);
            //到合并时
            merge(arr, left, mid, rigth, temp);
        }
    }

    /**
     *  合并的方法
     * @param arr 待排序数组
     * @param left 左边有序序列的初始索引
     * @param mid  中间索引
     * @param right  右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left; //初始化 i，左边有序序列的初始索引
        int j = mid + 1; //初始化 j，右边有序序列的初始索引
        int t = 0; //temp 数组的当前索引

        //1. 先把左右的有序序列的数据按规则填充到 temp 数组中，直到左右两边的序列有一边处理完毕
        while (i <= mid && j <= right){ //继续进行比较
            //如果左边的有序序列的当前元素，小于等于 右边有序序列的当前元素
            //即将左边的当前元素，拷贝到 temp 数组
            //i,t 后移
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            }else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //2. 将有剩余数据的一边序列依次全部填充到 temp
        while (i <= mid) { //左边的有序序列还有剩余的元素，就全部填充到 temp
            temp[t++] = arr[i++];
        }
        while (j <= right){
            temp[t++] = arr[j++];
        }

        //3. 将 temp 数组的元素拷贝到 arr 中
        //注意：并不是每次都拷贝 temp 的所有元素
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){ //只合并 [left, right]的数据
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
