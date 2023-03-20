package DataStructures.t9_tree.threadedbinarytree;

/**
 * Description: 线索化二叉树
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/20 15:00
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //中序线索二叉树
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();


        //测试: 以 10 号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10 号结点的前驱结点是 ="
                + leftNode); //3
        System.out.println("10 号结点的后继结点是="
                + rightNode); //1


        //当线索化二叉树后，不能使用原来的遍历方式,会出现死循环
//        threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}

//定义 ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree{
    private HeroNode root;

    //为了实现线索化，需要一个指向当前结点的前驱结点的指针
    //pre 保存前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root){
        this.root = root;
    }


    public void threadedNodes(){
        this.threadedNodes(root);
    }


    //遍历线索化二叉树
    public void threadedList(){
        HeroNode node = root;
        while (node != null){
            //循环的找到 leftType == 1 的结点，第一个找到就是 8 结点
            //后面随着遍历而变化，因为当 leftType == 1时，说明该结点是按线索化处理后的有效结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1){
                //获取当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历的结点
            node = node.getRight();
        }
    }


    //对二叉树进行中序线索化的方法
    /**
     *
     * @param node 需要线索化的结点
     */
    public void threadedNodes(HeroNode node){
        if (node == null){
            return;
        }

        //一、先线索化左子树
        //需要一个前驱结点，指向当前结点的上一个结点
        threadedNodes(node.getLeft());

        //二、线索化当前结点[重点]
        //处理当前结点的前驱结点
        if (node.getLeft() == null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向的是前驱结点
            node.setLeftType(1);
        }
        //处理当前结点的后继节点:为什么要在下一次处理？
        if (pre != null && pre.getRight() == null){
            //让前驱结点的右指针，指向当前结点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        //三、线索化右子树
        threadedNodes(node.getRight());
    }


    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    //前序遍历查找
    public HeroNode postOrderSearch(int no){
        if (root != null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }

    //二叉树的删除结点操作
    public void delNode(int no){
        if (root != null){
            //如果只有 root 一个结点，就需要立即判断 root 是不是就是要删除的结点
            if (root.getNo() == no){
                root = null;
            }else {
                //递归删除
                root.delNode(no);
            }
        }else {
            System.out.println("空树，无法删除");
        }
    }
}


//先创建 HeroNode 结点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left; //默认为null
    private HeroNode right; //默认为null
    //说明：
    //1. 如果 leftType == 0 表示指向的是左子树，如果 1 则表示指向前驱结点
    //2. 如果 rightType == 0 表示指向的是右子树，如果 1 则表示指向后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this); //先输出父节点
        //递归左子树
        if (this.left != null){
            this.left.preOrder();
        }
        //递归右子树
        if (this.right != null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        //左子树递归
        if (this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归右子树
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        //左子树递归
        if (this.left != null){
            this.left.postOrder();
        }
        //递归右子树
        if (this.right != null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历");
        //比较当前节点
        if (this.no == no){
            return this;
        }
        //1. 则判断当前节点的左子节点是否为空,如果不为空，则递归前序查找
        HeroNode res = null;
        if (this.left != null){
            res = this.left.preOrderSearch(no);
        }
        if (res != null){
            return res;
        }

        //右子节点递归
        if (this.right != null){
            res = this.right.preOrderSearch(no);
        }
        return res;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        HeroNode res = null;
        //先对左子节点进行递归查找
        if (this.left != null){
            res = this.left.infixOrderSearch(no);
        }
        if (res != null){
            return res;
        }

        System.out.println("进入中序遍历");
        //和当前节点比较
        if (this.no == no){
            return this;
        }

        //和右子节点进行递归查找
        if (this.right != null){
            res = this.right.infixOrderSearch(no);
        }
        return res;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        HeroNode res = null;
        //先对左子节点进行递归查找
        if (this.left != null){
            res = this.left.postOrderSearch(no);
        }
        if (res != null){
            return res;
        }

        //和右子节点进行递归查找
        if (this.right != null){
            res = this.right.postOrderSearch(no);
        }
        if (res != null){
            return res;
        }

        System.out.println("进入后序遍历");
        //和当前节点比较
        if (this.no == no){
            return this;
        }
        return res;
    }

    /*
     * 二叉树删除结点：
     * 1. 如果删除的结点是叶子结点，则删除该结点
     * 2. 如果删除的结点是非叶子结点，则删除该子树
     *
     * 1. 因为二叉树是单向的，所以需要判断当前结点的子结点是否是删除结点，而不能去判断当前这个结点是不是需要删除结点
     * 2. 如果当前结点的左子结点不为空，且左子结点就是要删除结点，就将 this.left = null，并且返回(结束递归删除)
     * 3. 如果当前结点的右子结点不为空，且右子结点就是要删除结点，就将 this.right = null，并且返回(结束递归删除)
     * 4. 如果第 2 步和第 3 步没有找到删除结点，就向左子树进行递归删除
     * 5. 如果第 4 步也没有删除结点，就向右子树进行递归删除
     * 6. 如果树是空树，如果只有一个 root 结点，则等价于将二叉树置空
     */
    public void delNode(int no){
        //2. 如果当前结点的左子结点不为空，且左子结点就是要删除结点，就将 this.left = null，并且返回(结束递归删除)
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }

        //3. 如果当前结点的右子结点不为空，且右子结点就是要删除结点，就将 this.right = null，并且返回(结束递归删除)
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }

        //4. 对左子树进行递归删除
        if (this.left != null){
            this.left.delNode(no);
        }

        //5. 对右子树进行递归删除
        if (this.right != null){
            this.right.delNode(no);
        }
    }
}
