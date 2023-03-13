package DataStructures.t7_search;

import java.util.ArrayList;

/**
 * Description: 二分查找   —— 该数组有序
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/13 21:29
 */
public class BinarySearcch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1314};

        int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndex = " + resIndex);

        ArrayList<Integer> list = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndexList = " + list);
    }


    /**
     * 二分查找
     *
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要找的值
     * @return 如果找到就返回下标，否则返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        //当 left > right 时，说明没有找到
        if (left > right) return -1;

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            //右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            //左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    //完成课后思考题：
    /*
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     *
     * 思路分析：
     * 1. 在找到 mid 值时，不要马上返回
     * 2. 向 mid 索引值的左边扫描，将所有满足条件的元素下标，加入到集合中 ArrayList
     * 3. 向 mid 索引值的右边扫描，将所有满足条件的元素下标，加入到集合中 ArrayList
     * 4. 将 ArrayList 返回
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        //当 left > right 时，说明没有找到
        if (left > right) return new ArrayList<>();

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            //右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            //左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            /* 1. 在找到 mid 值时，不要马上返回
             * 2. 向 mid 索引值的左边扫描，将所有满足条件的元素下标，加入到集合中 ArrayList
             * 3. 向 mid 索引值的右边扫描，将所有满足条件的元素下标，加入到集合中 ArrayList
             * 4. 将 ArrayList 返回
             */
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //向左边扫描
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findVal) {
                //否则，将 temp 放入到 resIndexlist
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);
            //向右边扫描
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findVal) {
                //否则，将 temp 放入到 resIndexlist
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}
