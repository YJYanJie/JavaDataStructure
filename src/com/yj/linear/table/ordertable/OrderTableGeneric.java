package com.yj.linear.table.ordertable;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 顺序表的泛型实现
 * @author YJ
 * @create 2022-12-04 20:14
 */
public class OrderTableGeneric<E> {

    private E[] data; //存储E类型元素的数组，存放数据
    private int size; //记录数组的元素个数
    private static double RESIZE_RATIO = 0.8; //默认的扩容系数;当数组的元素个数大于等于数组的容量*系数时，进行扩容

    public OrderTableGeneric(){
        this(10); //默认创建大小为10的数组
    }

    public OrderTableGeneric(int capacity){
        //Java中不支持泛型数组，可以通过对 new Object[capacity] 进行转型来达到泛型的目的
        data = (E[])new Object[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    //判断顺序表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //向数组中索引为index的位置插入一个元素e
    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("索引位置错误");

        //添加元素之前，先判断是否需要进行扩容;
        if (size >= (int)(data.length * RESIZE_RATIO))
            resize(2 * data.length); //扩容为原来的2倍

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 动态扩容机制
     * @param newCapacity 新的顺序表大小
     */
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i]; //拷贝数据

        data = newData;
    }

    //头部插入法
    public void addFitsr(E e){
        add(0, e);;
    }

    //尾部插入元素
    public void addLast(E e){
        add(size, e);
    }

    /**
     * 获取对应 index 位置的元素
     * @param index 索引位置
     * @return
     */
    public E get(int index){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("索引位置有误");
        return data[index];
    }

    /**
     * 修改对应 index 位置元素为 e
     * @param index 索引位置
     * @param e 修改值
     */
    public void set(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("索引位置有误");
        data[index] = e;
    }

    /**
     * 查询数组是否存在元素 e
     * @param e 需要查询的元素
     * @return
     */
    public boolean isExist(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查询元素 e 的索引位置
     * @param e 需要查询的元素
     * @return
     */
    public int indexOf(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    //删除对应位置的元素,并返回该元素
    public E remove(int index){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("索引位置有误");
        //删除index位置的元素: 并将其后面的元素前移,覆盖掉 index 位置上的元素即可
        E res = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null; //size位置置为空

        //还需要进行缩容;缩容到原来的 1/2
        if (size <= data.length / 2)
            resize(data.length / 2);

        return res;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = indexOf(e);
        if (index != -1)
            remove(index);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
