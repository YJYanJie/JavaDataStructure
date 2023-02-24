package DataStructures.t1_sparsearray;

/**
 * Description: 稀疏数组的实现
 *
 * @author YJ
 * @date 2023/2/24 15:27
 * @version 1.0
 */
public class SparseArray {

    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0:表示没有棋子   1：表示 黑子   2：表示 白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        // 输出原始的二维数组
        System.out.println("原始的二维数组为~~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //稀疏数组格式:
        /*
         *     row   col  val
         * 0   11    11    3         第一行：原始数组的行、列、和非0的值
         * 1   1     2     1
         * 2   2     3     2
         * 3   4     5     2
         */
        // 二维数组 -->>  稀疏数组的思路
        // 1. 遍历原始二维数组，得到有效数据的个数 sum，创建稀疏数组的大小 sparseArr int[sum + 1][3];
        int sum = 0;
        for (int[] ints : chessArr1) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组的第一行进行赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        // 2. 遍历原始二维数组组，将二维数组的有效数据存入到稀疏数组中
        int count = 1;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count++][2] = chessArr1[i][j];
                }
            }
        }

        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~~");
        for (int[] ints : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }
        System.out.println();

        // 稀疏数组 -->>  二维数组
        // 1. 读取稀疏数组的第一行，获得原始二维数组的行、列，创建与原始的二维数组大小一样的数组 int[row][col]
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        // 2. 读取稀疏数组的后几行数据，并将其赋值给原始二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");

        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 可以将得到的稀疏数组保存到磁盘中
        // 从磁盘中获取稀疏数组的内容
    }

}
