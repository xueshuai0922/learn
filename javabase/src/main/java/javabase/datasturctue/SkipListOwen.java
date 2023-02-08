package javabase.datasturctue;

import java.util.Random;

/**
 * @author xueshuai
 * @date 2021/5/10 7:24
 * @description 实现链表
 *              1）node节点，保存数据，上下左右节点
 *              2）插入时候，均分创建节点
 *              3)查找节点数据，循环，
 */
public class SkipListOwen {

    private Node head,tail; //头尾节点

    private final double rate=0.5;
    private int maxLevel=0;

    public SkipListOwen(){
        head=new Node(Integer.MIN_VALUE);
        tail= new Node(Integer.MAX_VALUE);
        head.right=tail;
        tail.left=head;
    }
    public class Node{
        private Node left,right,up,down;
        int data;

        public Node(){
        }
        public Node(int data){
            this.data=data;
        }


        @Override
        public String toString() {
            return "node:"+data;
        }
    }

    /**
     * 从【最上层】的头节点 开始查找数据对应的底层节点
     * @param data
     * @return
     */
    private Node findNode(int data){
        Node node = head;
        //如果头节点的下个节点就是要找的值，那么直接返回这个节点
        if(node.right.data==data){
            return node.right;
        }
        //循环：查询节点右方，
        while(true){
            // 循环：节点右边数据和节点数据比较，找到靠近或者相等数据的节点
            while(node.right.data<=data && data<Integer.MAX_VALUE){
                node=node.right;
            }
            //直到这个节点的下面节点为空，跳出循环
            if(node.down==null){
                break;
            }
            node=node.down;
        }
        return node;
    }

    /**
     * 查找数据
     * @return
     */
    public Node search(int data){
        Node node = findNode(data);
        if(node.data==data){
            System.out.println("查找节点： "+node);
            return node;
        }
        else{
            System.out.println("未查找到节点： ");
            return  null;
        }
    }
    /**
     * 插入数据
     */
    public void insert(int data){
        //
        Node preNode = findNode(data);
        if(preNode.data==data){
            System.out.println("已经插入该数据，节点为： "+preNode);
        }
        Node node = new Node(data);
        //插入节点，对原有节点和新节点的左右进行维护
        appendNode(preNode, node);

        //判断节点是否 需要到上层节点去
        int currentLevel=0;
        Random random = new Random();
        double v = random.nextDouble();
        if(v>rate ){
            System.out.println("数据: "+data+" 节点层级上升");
            //上一层节点初始化
            if(currentLevel==maxLevel){
                addMaxLevel();

            }
            Node upperNode = new Node(data);
            appendNode(preNode,upperNode);
            node.up=upperNode;
            upperNode.down=node;

            currentLevel++;
        }


    }

    private void addMaxLevel() {
        maxLevel++;
        Node node1 = new Node(Integer.MIN_VALUE);
        Node node2 = new Node(Integer.MAX_VALUE);

        node1.right=node2;
        node2.left=node1;

        node1.down=head;
        head.up=node1;

        node2.down=tail;
        tail.up=node2;

        head=node1;
        tail=node2;
    }

    /**
     * 增加节点，维护节点左右的节点信息
     * @param preNode
     * @param node
     */
    private void appendNode(Node preNode, Node node) {
        node.left= preNode;
        node.right= preNode.right;

        preNode.right.left= node;
        preNode.right= node;
    }

    public static void main(String[] args) {
        SkipListOwen skipListOwen = new SkipListOwen();
        skipListOwen.insert(2);
        skipListOwen.insert(3);
        skipListOwen.insert(4);
        skipListOwen.insert(5);
        skipListOwen.insert(6);
        skipListOwen.insert(7);

        skipListOwen.search(5);
    }
}
