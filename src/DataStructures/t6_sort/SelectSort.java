package DataStructures.t6_sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Description: 选择排序
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
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;  // 重置 minIndex
                }
            }
            //将最小值与arr[i]交换
//            if (min < arr[i]){
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
