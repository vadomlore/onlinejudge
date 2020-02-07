package ch01;

import java.util.Stack;

public class ValidIPAddress {
    //给定输入ip地址字符串,给出所有合法的ip地址输出

    Stack<Integer> result = new Stack<>();

    public void showAllValidIPAddress(String s){
        if(s.length() < 4 || s.length() > 12) {
            return;
        }
        doShowIp(s, 4, s.length() - 1, result);
    }

    int parseSubNet(String value){
        int v = Integer.parseInt(value);
        if(0 <= v && v <= 255){
            return v;
        }
        return -1;
    }

    /**
     *
     * @param s 字符串
     * @param n 问题的规模 初始化为4
     * @param reverseIndex 当前逆向扫描指向的字符串
     * @param traceStack 追踪栈
     */
    void doShowIp(String s, int n, int reverseIndex, Stack<Integer> traceStack){

        if(n == 0 && reverseIndex < 0){ //所有正确的情况
                StringBuilder sb = new StringBuilder();
                System.out.println(traceStack);
                return;
        }
        else {
            if(0 <= reverseIndex){
                doSituation(s, n, reverseIndex,  1, traceStack);
            }
            if(1 <= reverseIndex) {
                doSituation(s, n, reverseIndex, 2, traceStack);
            }
            if(2 <= reverseIndex) {
                doSituation(s, n, reverseIndex, 3, traceStack);
            }
        }
    }

    private void doSituation(String s, int n, int reverseIndex, int situation, Stack<Integer> ipAddress) {
        String sb0 = s.substring(reverseIndex - situation + 1, reverseIndex + 1);
        int value = parseSubNet(sb0);
        if(value != - 1){
            ipAddress.push(value);
            doShowIp(s, n - 1, reverseIndex - situation, ipAddress);
            ipAddress.pop();  //如果遍历的错误分支,需要排控stack
        }
    }

    //255.255.255.25 5

    // Reduce and Conquer 减而治之

    // Divide and Conquer 分而治之


    //(4段)

    // (3段) + 1 位

    //       (2段)   1 位 + 1位

                //       (1段)  1 +  1 位 + 1位
                    //       (0段)  1 + 1 +  1 位 + 1位
                    //       (0段)  2 + 1 +  1 位 + 1位
                    //       (0段)  3 + 1 +  1 位 + 1位
  //  当0段有任何数据,就找到了一个解


                //       (1段)  2 +  1 位 + 1位

                //       (1段)  3 + 1 位 + 1位

    //       (2段)   2 位 + 1位

    //       (2段)   3 位 + 1位

    // (3段) + 2 位

    // (3段) + 3 位


    public static void main(String[] args) {
        new ValidIPAddress().showAllValidIPAddress("10101101101");

//        new ValidIPAddress().showAllValidIPAddress("101 011 01 101");
//
//        new ValidIPAddress().showAllValidIPAddress("101 01  101 101");
//
//        new ValidIPAddress().showAllValidIPAddress("10 101  101 101");
    }

}
