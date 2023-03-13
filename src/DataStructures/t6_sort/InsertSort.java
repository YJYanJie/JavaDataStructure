package DataStructures.t6_sort;

/**
 * Description: 插入排序
 *      1. 把 n 个待排序的元素看成一个有序表和一个无序表，开始时，有序表只有一个元素，无序表有 n-1 个元素
 *      2. 每次从无序表中取出第一个元素，将它插入到有序表中的适当位置，使之成为新的有序表
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/13 10:05
 */
public class InsertSort {

    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -1, 89};
        int[] arr = new int[80000];
        for(int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }

        long start1 = System.currentTimeMillis();
        //测试插入排序
        insertSort(arr);
        long end1 = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end1 - start1));

//        System.out.println(Arrays.toString(arr));
    }


    //插入排序
    public static void insertSort(int[] arr){

        int insertVal;
        int insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置已经找到，insertIndex + 1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

//            System.out.println("第" + (i + 1) + "轮插入");
//            System.out.println(Arrays.toString(arr));
        }
    }
}


