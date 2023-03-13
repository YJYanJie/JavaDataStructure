package DataStructures.t6_sort;

/**
 * Description: 希尔排序:使用步长将数据分组，对每个分组进行插入排序，直至步长为 1 后进行插入排序后结束
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/13 10:37
 */
public class ShellSort {

    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);

        int[] arr = new int[80000];
        for(int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }

        long start1 = System.currentTimeMillis();
        //测试插入排序
        shellSort2(arr);
        long end1 = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end1 - start1));

    }


    //希尔排序 —— 交换法
    public static void shellSort(int[] arr){
        //根据逐步分析，使用循环处理
        int temp;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共 gap 组，每组有 个元素)，步长为 gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("希尔排序的" + (++count) + "轮=" + Arrays.toString(arr));
        }

        /*
        //希尔排序的第一轮排序
        //因为第一轮排序，将 10 个数据分成了 10/2 = 5 组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素(共5组，每组有2个元素)，步长为5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        System.out.println("第一轮希尔排序");
        System.out.println(Arrays.toString(arr));

        //第二轮
        //因为第二轮排序，将 10 个数据分成了 5/2 = 2 组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素(共5组，每组有2个元素)，步长为5
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }

        System.out.println("第二轮希尔排序");
        System.out.println(Arrays.toString(arr));


        //第三轮
        //因为第三轮排序，将 10 个数据分成了 2/2 = 1 组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素(共5组，每组有2个元素)，步长为5
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第三轮希尔排序");
        System.out.println(Arrays.toString(arr));

         */
    }

    //对交换式的希尔排序进行优化 -> 移位法
    public static void shellSort2(int[] arr){
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2){
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++){
                int j = i;
                temp = arr[j];
                if (arr[j] < arr[j - gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出 while 循环后，就给 temp 找到了插入的位置
                    arr[j] = temp;
                }
            }
        }
//        System.out.println(Arrays.toString(arr));
    }

}
