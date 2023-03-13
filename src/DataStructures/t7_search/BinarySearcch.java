package DataStructures.t7_search;

/**
 * Description: 二分查找   —— 该数组有序
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/13 21:29
 */
public class BinarySearcch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1314};

        int resIndex = binarySearch(arr, 0, arr.length - 1, -11);
        System.out.println("resIndex = " + resIndex);
    }


    /**
     * 二分查找
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要找的值
     * @return 如果找到就返回下标，否则返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal){

        //当 left > right 时，说明没有找到
        if (left > right) return -1;

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal){
            //右递归
            return binarySearch(arr, mid + 1, right, findVal);
        }else if (findVal < midVal){
            //左递归
            return binarySearch(arr, left, mid - 1, findVal);
        }else {
            return mid;
        }

    }
}
