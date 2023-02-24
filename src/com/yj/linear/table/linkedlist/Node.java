package com.yj.linear.table.linkedlist;

/**
 * 链表的结点
 * @author YJ
 * @create 2022-12-05 12:29
 */
public class Node<E> {
    public E data; //结点的数据域
    public Node next; //结点的指针域,指向下一个结点的地址

    public Node(){}

    public Node(E data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
