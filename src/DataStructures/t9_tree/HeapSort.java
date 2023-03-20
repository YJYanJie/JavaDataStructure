package DataStructures.t9_tree;

import java.util.Arrays;

/**
 * Description: 堆排序
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/20 16:39
 */
public class HeapSort {
    public static void main(String[] args) {
        //升序：大顶堆
        //降序：小顶堆
        int[] arr = {4, 6, 8, 5, 9, 0, -1, 90, 89, 56, -999};
        heapSort(arr);
    }

    //堆排序的方法
    public static void heapSort(int[] arr){
//        System.out.println("堆排序！");
        int temp;
        //分步完成
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第一次" + Arrays.toString(arr)); // 4 9 8 5 6
//
//        adjustHeap(arr, 0, arr.length);
//        System.out.println("第二次" + Arrays.toString(arr)); // 9 6 8 5 4

        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--){
            adjustHeap(arr, i, arr.length);
//            System.out.println("第 " + i + " 次" + Arrays.toString(arr));
        }

        //将堆顶元素与末尾元素交换，将最大元素 "沉" 到数组末端
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行 调整 + 交换元素步骤，直至整个序列有序
        for (int j = arr.length - 1; j > 0; j--){
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;

            //重新调整结构
            adjustHeap(arr, 0, j);
        }

        System.out.println(Arrays.toString(arr));
    }


    /**
     * 将一个数组(二叉树)，调整成一个大顶堆
     * 功能：将以 i 对应的非叶子结点的树调整成大顶堆
     * @param arr 待调整的数组
     * @param i   非叶子结点在数组中的索引
     * @param length 表示对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i]; // 先取出当前元素的值，保存在临时变量
        //开始调整
        //说明：
        // 1. k = 2 * i + 1 是 i 结点的左子结点
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1){
            //找到该节点的左右子结点的最大值
            if (k + 1 < length && arr[k] < arr[k + 1]){ //说明左子结点的值小于右子结点的值
                k++;
            }
            //如果子结点大于父结点，
            if (arr[k] > temp){
                arr[i] = arr[k]; //将较大的值赋给当前结点
                i = k; // i 指向k，继续循环比较
            }else {
                break;
            }
        }
        //当 for 循环结束后，已经将 i 为父结点的树的最大值，放在了 最顶部
        arr[i] = temp;
    }
}
