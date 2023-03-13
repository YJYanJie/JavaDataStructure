package DataStructures.t6_sort;

import java.util.Arrays;

/**
 * Description: 基数排序
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/13 13:35
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {

        //基数排序的代码
        //1. 得到数组中最大的数的位数
        int max = arr[0]; //假设第一个数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示 10 个桶，每个桶就是一个一维数组
        //为了防止在放入数据时，数据溢出，因此每个桶的大小定为 arr.length
        //基数排序：空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，定义一个一维数组来记录各个桶每次放入的数据个数
        int[] bucketElemCounts = new int[10];

        //使用循环
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素的对应位进行排序处理 ： 个、十、百...
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位的值
                int digitOfElem = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElem][bucketElemCounts[digitOfElem]] = arr[j];
                bucketElemCounts[digitOfElem]++;
            }
            //按照桶的顺序，取出数据(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            //遍历每一个桶，并将桶中的数据，放入到原数组
            for (int k = 0; k < bucket.length; k++) {
                //如果桶中有数据，才放入到原数组中
                if (bucketElemCounts[k] != 0) {
                    //说明 k 桶中有数据，循环第 k 个桶，放入数据
                    for (int l = 0; l < bucketElemCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                //第一轮处理后，需要将每个 bucketElemCounts[k] == 0
                bucketElemCounts[k] = 0;
            }
            System.out.println("第" + (i + 1) +"轮，对个位的排序处理 arr = " + Arrays.toString(arr));

        }


        /*
        //第一轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElem = arr[j] % 10;
            //放入到对应的桶中
            bucket[digitOfElem][bucketElemCounts[digitOfElem]] = arr[j];
            bucketElemCounts[digitOfElem]++;
        }
        //按照桶的顺序，取出数据(一维数组的下标依次取出数据，放入原来数组)
        int index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int k = 0; k < bucket.length; k++) {
            //如果桶中有数据，才放入到原数组中
            if (bucketElemCounts[k] != 0) {
                //说明 k 桶中有数据，循环第 k 个桶，放入数据
                for (int l = 0; l < bucketElemCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            //第一轮处理后，需要将每个 bucketElemCounts[k] == 0
            bucketElemCounts[k] = 0;
        }

        System.out.println("第一轮，对个位的排序处理 arr = " + Arrays.toString(arr));


        //第二轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的十位的值
            int digitOfElem = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElem][bucketElemCounts[digitOfElem]] = arr[j];
            bucketElemCounts[digitOfElem]++;
        }
        //按照桶的顺序，取出数据(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int k = 0; k < bucket.length; k++) {
            //如果桶中有数据，才放入到原数组中
            if (bucketElemCounts[k] != 0) {
                //说明 k 桶中有数据，循环第 k 个桶，放入数据
                for (int l = 0; l < bucketElemCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElemCounts[k] = 0;
        }

        System.out.println("第二轮，对个位的排序处理 arr = " + Arrays.toString(arr));

        //第三轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的十位的值
            int digitOfElem = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElem][bucketElemCounts[digitOfElem]] = arr[j];
            bucketElemCounts[digitOfElem]++;
        }
        //按照桶的顺序，取出数据(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int k = 0; k < bucket.length; k++) {
            //如果桶中有数据，才放入到原数组中
            if (bucketElemCounts[k] != 0) {
                //说明 k 桶中有数据，循环第 k 个桶，放入数据
                for (int l = 0; l < bucketElemCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElemCounts[k] = 0;
        }

        System.out.println("第三轮，对个位的排序处理 arr = " + Arrays.toString(arr));
         */
    }
}

