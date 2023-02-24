package com.yj.sparsearray;

import java.io.*;

/**
 * 稀疏数组的实现
 * @author YJ
 * @create 2022-09-29 11:02
 */

public class SparseAyyar {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11 * 11
        //0:表示没有棋子，1:表示黑棋；2:表示白棋
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组：");
        for (int[] rows : chessArr1){
            for (int data : rows){
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        //将二维数组 转 稀疏数组
        //1. 先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        //2. 创建稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //3. 给稀疏数组赋值，第一行存放的原始数组的 行数、列数、非0数据的个数
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        //4. 遍历二维数组，将非0的值存放到稀疏数组中
        int count = 0; //用于记录第几个非0数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    ++count;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        File file = new File("sparseArray.txt"); // 存放稀疏数组的文件
        FileWriter fw = null;

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("写入文件中的稀疏数组为：");

        try {
             fw = new FileWriter(file);
            for (int i = 0; i < sparseArr.length; i++) {
                for (int j = 0; j < sparseArr[i].length; j++) {
                    System.out.print(sparseArr[i][j] + "\t");
                    fw.write(sparseArr[i][j] + "\t");
                }
                fw.write("\n");
                System.out.println();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //读取文件 sparseArray.txt 中的稀疏数组
        int sparseArr2[][] = new int[sum + 1][3];
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line; //一行数据
            int row = 0; //行数

            //逐行读取
            while ((line = br.readLine()) != null){
                String[] temp = line.split("\t");
                for (int i = 0; i < temp.length; i++) {
                    sparseArr2[row][i] = Integer.parseInt(temp[i]);
                }
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("写出文件中的稀疏数组为：");
        for (int i = 0; i < sparseArr2.length; i++) {
            for (int j = 0; j < sparseArr2[i].length; j++) {
                System.out.print(sparseArr2[i][j] + "\t");
            }
            System.out.println();
        }

        //将稀疏数组 --> 原始的二维数组
        //1. 先读取稀疏数组的第一行，得到原始二维数组的大小
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2. 读取稀疏数组的后几行，并将值赋值给原始二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println();
        //输出转换之后的二维数组
        System.out.println("转换后的原始二维数组：");
        for (int[] rows : chessArr2){
            for (int data : rows){
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }
}
