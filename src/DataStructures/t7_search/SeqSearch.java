package DataStructures.t7_search;

/**
 * Description: 顺序查找
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/13 15:10
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};
        int index = seqSearch(arr, 11);
        if (index == -1){
            System.out.println("没有找到");
        }else {
            System.out.println("找到，下标为 = " + index);
        }
    }


    /**
     * 实现的线性查找：找到一个满足条件的值，就返回
     * @param arr 需要查找的数组
     * @param value 需要查找的值
     * @return 索引位置
     */
    public static int seqSearch(int[] arr, int value){
        //线性查找就是逐一比对
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
