package DataStructures.t9_tree;

/**
 * Description:
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/15 11:20
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
//        arrayBinaryTree.preOrder(); // 1 2 4 5 3 6 7
//        arrayBinaryTree.infixOrder(0); // 4 2 5 1 6 3 7
        arrayBinaryTree.postOrder(0); // 4 5 2 6 7 3 1
    }
}

//编写一个 ArrayBinaryTree,实现顺序存储二叉树遍历
class ArrayBinaryTree{
    private int[] arr; //存储数据结点的数组

    public ArrayBinaryTree(int[] arr){
        this.arr = arr;
    }

    public void preOrder(){
        this.preOrder(0);
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    /**
     *
     * @param index 数组的下标
     */
    public void preOrder(int index){
        //如果数组为空，或者 arr.length = 0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能进行遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);

        //左子树递归
        if (index * 2 + 1 < arr.length) {
            preOrder(2 * index + 1);
        }

        //右子树递归
        if (index * 2 + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //中序遍历
    public void infixOrder(int index){
        //如果数组为空，或者 arr.length = 0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能进行遍历");
        }

        //左子树递归
        if (index * 2 + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }

        //输出当前这个元素
        System.out.println(arr[index]);

        //右子树递归
        if (index * 2 + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    //后序遍历
    public void postOrder(int index){
        //如果数组为空，或者 arr.length = 0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能进行遍历");
        }

        //左子树递归
        if (index * 2 + 1 < arr.length) {
            postOrder(2 * index + 1);
        }

        //右子树递归
        if (index * 2 + 2 < arr.length) {
            postOrder(2 * index + 2);
        }

        //输出当前这个元素
        System.out.println(arr[index]);
    }
}
