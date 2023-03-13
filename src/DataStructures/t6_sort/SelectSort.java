package DataStructures.t6_sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 选择排序
 *      1. 选择排序一共有数组大小 - 1此排序
 *      2. 每一轮排序，又是一个循环，循环的规则：
 *              2.1 先假定当前数是最小的数
 *              2.2 然后和后面的每个数进行比较，如果发现比当前数更小的数，就重新确定最小数，并得到下标
 *              2.3 当遍历到数组的最后时，就得到本轮的最小数和下标
 *              2.4 进行交换
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/3 19:06
 */
public class SelectSort {
    public static void main(String[] args) {
//        int [] arr = {101, 34, 119, 1, -1, 90, 123};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
//        selectSort(arr);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));

        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

//        System.out.println("排序前");
        //System.out.println(Arrays.toString(arr));

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        selectSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);

    }

    //选择排序
    public static void selectSort(int[] arr) {
        // 101, 34, 119, 1, -1, 90, 123
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i; //为了交换数组中两个下标的值，用于记录另一个需要交换值的下标
            for (int j = i + 1; j < arr.length; j++) {
                //找到 arr[j] - arr[arr.length - 1]中的最小值
                //找到最小值及其索引
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;  // 重置 minIndex
                }
            }
            //判断确定的 minIndex 是否发生改变，如果发生改变，则找到比其更小的数，进行交换
            //将最小值与arr[i]交换
//            if (min < arr[i]){
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
