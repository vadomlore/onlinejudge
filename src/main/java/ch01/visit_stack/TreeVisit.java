package ch01.visit_stack;


import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class TreeVisit {

    @FunctionalInterface
    public interface Visitor<T>{
        void visit(TreeNode<T> treeNode);
    }

    public static class TreeNode<T> {
        T data;
        TreeNode parent;
        TreeNode lChild;
        TreeNode rChild;
    }

    public static class BinaryTree<T> {
        TreeNode<T> root;
        int size;
        int height;
    }

    public <T> void traversePreV2(TreeNode<T> x, Visitor<T> visitor) {
        Stack<TreeNode<T>> s = new Stack<>(); //辅助栈
        while (true){ //以右子树为单位，逐批访问节点
            visitAlongLeftBranch(x, visitor, s); //访问所有左子树
            if(s.empty()) break; //栈空退出
            x = s.pop();
        }
    }

    static <T> void visitAlongLeftBranch(TreeNode<T> pos, Visitor<T> visitor, Stack<TreeNode<T>> s) {
        while (pos != null) {
            visitor.visit(pos);
            s.push(pos.rChild); //栈LIFO特性 自底向上输出右子树
            pos = pos.lChild;
        }
    }

    static <T> void goAlongLeftBranch(TreeNode<T> pos, Visitor<T> visitor, Stack<TreeNode<T>> s) {
        while (pos != null) {
            s.push(pos); //栈LIFO特性 自底向上输出右子树
            pos = pos.lChild;
        }
    }



    public <T> void traverseInOrder(TreeNode<T> x, Visitor<T> visitor) {
        Stack<TreeNode<T>> s = new Stack<>(); //辅助栈

        while(true){
            goAlongLeftBranch(x, visitor, s); //从当前节点触发，逐批入栈
            if(s.empty()) break; //所有节点处理完
            x = s.pop(); //子树为空或已遍历(等效于空），故可以
            visitor.visit(x); //立即访问之
            x = x.rChild;// 转向其右子树(可能为空，留意处理手法)
        }
    }

    public <T> void levelTranverse(TreeNode<T> x, Visitor<T> visitor) {
        Queue<TreeNode<T>> q = new LinkedBlockingQueue<>(); //辅助栈
        if(x == null) return;
        q.offer(x);
        while(!q.isEmpty()){
            x = q.poll();
            visitor.visit(x); //立即访问之
            if(x.lChild != null) q.offer(x.lChild);
            if(x.rChild != null) q.offer(x.rChild);
        }
    }
}
