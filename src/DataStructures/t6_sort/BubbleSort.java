package DataStructures.t6_sort;

/**
 * Description: 冒泡排序
 *      1. 一共进行数组的大小 - 1次循环
 *      2. 每一趟排序的次数在逐渐的减少
 *      3. 如果发现在某躺排序中，没有发生一次交换，可以提前结束冒泡排序（优化）
 * @author YJ
 * @version 1.0
 * @date 2023/3/3 18:32
 */
public class BubbleSort {
    public static void main(String[] args) {

//        int arr[] = {3, 9, -1, 10, -2};

        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        int[] arr1 = new int[arr.length];
        System.arraycopy(arr, 0, arr1, 0, arr1.length);

//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));

        //输出时间
//
//        Date date1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1Str = simpleDateFormat.format(date1);
//        System.out.println("排序前的时间为：" + date1Str);
        long start1 = System.currentTimeMillis();
        //测试冒泡排序
        bubbleSort(arr);
        long end1 = System.currentTimeMillis();
        System.out.println("未优化时耗费时间：" + (end1 - start1));

//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));

        long start2 = System.currentTimeMillis();
        //优化后
        bubbleSort1(arr1);
        long end2 = System.currentTimeMillis();
        System.out.println("优化后耗费时间：" + (end2 - start2));

        System.out.println();
    }

    public static void bubbleSort(int[] arr){
        //冒泡排序的时间复杂度：O(n^2)
        //如果在某趟排序中，没有发生一次交换，就可以提前结束冒泡排序
//        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]){
//                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

//            if (flag){ //发生过交换
//                flag = false; //进行下一趟的排序
//            }else { //没有发生过交换
//                break; //结束冒泡排序
//            }
        }
    }

    public static void bubbleSort1(int[] arr){
        //如果在某趟排序中，没有发生一次交换，就可以提前结束冒泡排序
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]){
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (flag){ //发生过交换
                flag = false; //进行下一趟的排序
            }else { //没有发生过交换
                break; //结束冒泡排序
            }
        }
    }
}
