package DataStructures.t5_recursion;

/**
 * Description:
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/3 15:17
 */
public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用 1 表示墙
        //上下全部置为 1
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //把左右全部置为 1
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板 1表示
        map[3][1] = 1;
        map[3][2] = 1;
        map[1][2] = 1;
//        map[2][2] = 1;

//        setWay(map, 1, 1);
        setWay2(map, 1, 1);
        //求最短路径，通过不同的策略，记录其路线大小，找到最小值即可。

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯来给小球找路
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j 从哪个位置开始找
     * @return 如果找到通路，就返回 true，否则返回 false
     */
    public static boolean setWay(int[][] map, int i, int j){
        /*
         * map 表示地图  i,j 表示从地图的哪个位置开始出发
         * 如果小球能到 map[6][5] 位置，则说明找到通路
         * 约定：当 map[i][j] 为 0 表示该点没有走过， 当为 1 表示墙，2 表示通路可以走，3表示该点已经走过，但是走不通
         * 在走迷宫时，需要确定一个策略(方法): 下 -> 右 -> 上 -> 左，如果该点走不通，再回溯
         */
        if (map[6][5] == 2){
            //说明通路已经找到，
            return true;
        }else {
            if (map[i][j] == 0){ // 如果当前这个点还没有走过
                //按照策略：下 -> 右 -> 上 -> 左
                map[i][j] = 2; // 假定该点可以走通
                if (setWay(map, i + 1, j)) { // 向下走
                    return true;
                }else if (setWay(map, i, j + 1)){ // 向右走
                    return true;
                }else if (setWay(map, i - 1, j)) { //向上走
                    return true;
                }else if (setWay(map, i, j - 1)){ //向左走
                    return true;
                }else {
                    //说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            }else { // 如果map[i][j] != 0 , 可能是 1， 2， 3
                return false;
            }
        }
    }

    //修改找路的策略，改成 上->右->下->左
    public static boolean setWay2(int[][] map, int i, int j) {
        if(map[6][5] == 2) { // 通路已经找到ok
            return true;
        } else {
            if(map[i][j] == 0) { //如果当前这个点还没有走过
                //按照策略 上->右->下->左
                map[i][j] = 2; // 假定该点是可以走通.
                if(setWay2(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) { //向下
                    return true;
                } else if (setWay2(map, i, j-1)){ // 向左走
                    return true;
                } else {
                    //说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] != 0 , 可能是 1， 2， 3
                return false;
            }
        }
    }
}
