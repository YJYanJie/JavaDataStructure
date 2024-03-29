package DataStructures.t9_tree;

/**
 * Description:
 *
 * @author YJ
 * @version 1.0
 * @date 2023/3/14 20:38
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明，先手动创建二叉树，后面使用递归创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试
//        System.out.println("前序遍历");
//        binaryTree.preOrder(); // 1 2 3 4       1 2 3 5 4
//
//        System.out.println("中序遍历");
//        binaryTree.infixOrder(); // 2 1 3 4     2 1 5 3 4
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder(); // 2 4 3 1      2 5 4 3 1

        //前序遍历查找：进行 4 次比较
//        System.out.println("前序遍历查找");
//        HeroNode res = binaryTree.preOrderSearch(5);
//        if (res != null){
//            System.out.printf("找到了，信息为 no=%d  name=%s", res.getNo(), res.getName());
//        }else {
//            System.out.printf("没有找到 no=%d 的人", 5);
//        }

        //中序遍历查找：进行 3 次比较
//        System.out.println("中序遍历查找");
//        HeroNode res = binaryTree.infixOrderSearch(5);
//        if (res != null){
//            System.out.printf("找到了，信息为 no=%d  name=%s", res.getNo(), res.getName());
//        }else {
//            System.out.printf("没有找到 no=%d 的人", 5);
//        }

        //后序遍历查找：进行 2 次比较
//        System.out.println("后序遍历查找");
//        HeroNode res = binaryTree.postOrderSearch(5);
//        if (res != null){
//            System.out.printf("找到了，信息为 no=%d  name=%s", res.getNo(), res.getName());
//        }else {
//            System.out.printf("没有找到 no=%d 的人", 5);
//        }

        //删除结点
        System.out.println("删除前,前序遍历");
        binaryTree.preOrder(); // 1,2,3,5,4
//        binaryTree.delNode(5);
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder(); // 1,2,3,4
    }
}

//定义一个二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
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