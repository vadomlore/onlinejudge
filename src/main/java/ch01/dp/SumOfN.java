package ch01.dp;

import java.util.Stack;

/**
 *
 *
 *
 * n个数求和的可能 (0 1 背包问题变体)
 *
 * 如 1 1 2 3 5 7
 * 和为 4 有多少种
 * 1 1 2
 * 1 3
 * 1 3
 * 总共三种
 *
 * knap(k, c) = {
 *  knap(k - 1 , c)  //第k件太重 即当前背包剩余容量小于 决定是否要取的物品体积(c < v[k]) [---------- c ----v[k]--- ]
 *
 *  max{
 *        knap(k - 1, c -v(k)) + w(k) 取
 *        knap(k - 1, c) 不取
 *      }
 *  }
 *
 * sumOfN(k, c) = {
 *  sumOfN(k - 1 , c)  //第k个数大于当前c
 *  sumOfN(k - 1 , c) + 1 //第k个数等于当前c
 *                     //第k个数小于当前c
 *  sum {
 *        sumOfN(k - 1, c - v(k))  //取第k个数
 *        sumOfN(k - 1, c)  //不取第k个数
 *      }
 *  }
 *
 *
 */

/**
 * tips 借助stack可以跟踪递归调用过程;
 * 关键点是在执行递归操作之前入栈, 执行递归操作之后出栈
 */
public class SumOfN {

    static Stack<Integer> stack = new Stack();
    public static int doSum(int c, int[] array, int k){
        if(k < 0 ) {
            return 0;
        }

        if(c < array[k]){ //c小于第k个数,就要跳过第k个数
            return doSum(c, array, k - 1);
        }

        else if(c == array[k]) {
            stack.push(array[k]);
            System.out.println(stack);
            stack.pop();
            return doSum(c, array, k - 1) + 1;
        }

        else {
            stack.push(array[k]);
            int sum1 = doSum(c - array[k], array, k - 1); //取第k个数
            stack.pop();
            int sum2 = doSum(c, array, k - 1);  //不取第k个数
            return sum1 + sum2;
        }
    }


    public static int sumOfN(int value, int[] array){
        return doSum(value, array, array.length - 1);
    }

    public static void main(String[] args) {
        int n = sumOfN(4, new int[]{ 1, 1, 1, 2, 3, 5, 7});

        System.out.println("total number:" + n);
    }
}
