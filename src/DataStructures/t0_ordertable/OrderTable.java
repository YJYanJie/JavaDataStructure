package DataStructures.t0_ordertable;

/**
 * 顺序表的实现
 *
 * @author YJ
 * @create 2022-12-04 19:05
 */
public class OrderTable {
    // 存储数据的数组，如果存储其他类型，声明为 Object
    private int[] data;
    private int size; //顺序表的当前元素个数

    //初始化线性表
    public OrderTable(int size) {
        data = new int[size];
    }

    public OrderTable() {
        //默认顺序表大小为10
        this(10);
    }

    /**
     * 顺序表是否为空
     *
     * @return true：为空；false：不为空
     */
    public boolean isEmpty() {
        return this.size > 0;
    }

    /**
     * 顺序表的元素个数
     *
     * @return
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 顺序表插入元素
     *
     * @param index 索引位置 index
     * @param e     要添加的元素 e
     */
    public void add(int index, int e) {
        //判断索引位置 i 是否有效;需要顺序插入，不可以跳着插入元素
        if (index < 0 || index > size) {
            throw new RuntimeException("插入位置不对");
        }
        if(size == data.length)
            throw new IllegalArgumentException("顺序表已满");

        //添加元素，从index位置开始，后面的所有元素后移
        for (int i = size; i > index; --i) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    //遍历
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;  // 这里不可以直接return capacity，因为capacity不是成员变量。
    }

    /**
     * 获取对应下标的元素
     *
     * @param index 索引下标位置
     * @return 返回 index 对应位置的元素
     */
    public Object get(int index) {
        if (index > data.length - 1) {
            throw new RuntimeException("查找位置不对");
        }
        return data[index];
    }

    //查找当前元素的索引位置

    /**
     * 根据元素数值逐一和数组元素中的值进行对比，如果数组当中有此数值，则直接返回，否则返回-1
     *
     * @param e
     * @return
     */
    public int indexOf(int e) {
        //遍历当前顺序表
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return i;
        }
        return -1;
    }

//    根据下标删除元素

    /**
     * 根据下标删除元素，并返回删除元素的值
     *
     * @param index 索引位置
     * @return 删除元素
     */
    public Object remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Woring index");

        Object ret = data[index];
        //从index开始覆盖数组
        for (int i = index; i < size + 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return ret;
    }

    /**
     * 从数组删除元素e
     *
     * @param e 需要删除的元素
     */
    public void removeElement(int e) {
        int index = indexOf(e);
        if (index != -1) {
            remove(index);
        }
    }

    //重写 toString()
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size - 1; i++) {
            res.append(data[i]);
            if (i != size) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
