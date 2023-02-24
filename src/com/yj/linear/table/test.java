package com.yj.linear.table;

import java.util.HashMap;

/**
 * @author YJ
 * @create 2022-12-05 14:38
 */
public class test {
    public static void main(String[] args) {
        String a = null;
        String b = "ab2cce";

        System.out.println(safeEqual(a, b));

    }

    public static boolean safeEqual(String a, String b){
        if (a == null || b == null)
            throw new NullPointerException("有一个数据为空");

        if (a.length() != b.length())
            return false;

        int res = 0;

        for (int i = 0; i < a.length(); i++) {
            res |= a.charAt(i) ^ b.charAt(i); //^:异或操作
        }
        return res == 0;

//        HashMap<Integer, Integer> map = new HashMap(); // 这个就是创建一个 key - value 都是 Integer 类型的哈希表
    }
}
