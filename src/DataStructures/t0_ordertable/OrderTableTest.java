package DataStructures.t0_ordertable;

/**
 * 顺序表的测试
 * @author YJ
 * @create 2022-12-04 19:10
 */
public class OrderTableTest {
    public static void main(String[] args) {
//        OrderTable table = new OrderTable();
//
//        table.add(0,5);
//        table.add(0,4);
//        table.add(0,3);
//        table.add(0,2);
//        table.add(0,1);
//        table.add(0,0);
//
//        //顺序表遍历
//        table.print();
//
//        table.add(0, 0);
//        table.print();
//
//        System.out.println(table.get(1));
//        System.out.println(table.indexOf(2));
//
//        System.out.println(table.remove(0));
//        table.print();

        OrderTableGeneric<Integer> arr = new OrderTableGeneric<Integer>(8);
        // 从数组尾部依次插入0-6
        for (int i = 0; i < 6; i++) {
            arr.addLast(i);
        }
        System.out.println(arr.toString());


        arr.addLast(6);
        System.out.println(arr.toString());
    }
}
