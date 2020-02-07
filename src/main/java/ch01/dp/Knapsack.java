package ch01.dp;

/**
 *
 * - list of items
 *   each with size Si & Value Wi
 * - knapsack of size S
 * - max sum of values of subset of items of total size <= S
 *
 *
 * c还剩多少空间的情况下,取走前k个物品
 *
 * knap(k, c) = {
 *  knap(k - 1 , c)  //第k件太重 即当前背包剩余容量小于 决定是否要取的物品体积(c < v[k]) [---------- c ----v[k]--- ]
 *
 *  max {
 *        knap(k - 1, c - v(k)) + w(k) 取
 *        knap(k - 1, c) 不取
 *      }
 *  }
 *
 *
 *
 * 01 背包问题
 *  物品编号 0 1 2 3
 *  物品价值 2 3 4 8
 *  物品体积 3 4 5 6
 *  最多装入多少价值的东西
 *  8
 */


public class Knapsack {

    public static int v[] = new int[]{2, 3, 4, 5, 9}; //物品体积
    public static int w[] = new int[]{3, 4, 5, 8, 10}; //物品价值

    public static int knap(int k, int c){
        if(k < 0){
            return 0;
        }
        if(c < v[k]) { // 当前物品体积超过容量则不选
            return knap(k - 1, c);
        }
        else { //当前物品体积超过容量则没有超过容量 选和不选的最大值
            return Math.max(knap(k-1, c - v[k]) + w[k], knap(k - 1, c));
        }
    }

    /**
     * 动态规划计算
     * @param k
     * @param c
     * @return
     */

    /**
     *  0 1 2 3 ... 21  c
     *0 0 0 0 0 0 0 0
     *1 0
     *2 0
     *3 0
     *4 0
     *
     *w
     *
     */
    public static int v1[] = new int[]{0, 2, 3, 4, 5, 9}; //物品体积
    public static int w1[] = new int[]{0, 3, 4, 5, 8, 10}; //物品价值

    public static int[][] dp = new int[6][21];
    static{
        for(int j = 0 ; j < 21; j++) {
            dp[0][j] = 0;
        }
        for(int i = 0 ; i < 6; i++) {
            dp[i][0] = 0;
        }
    }

    public static int knap2(int n, int capacity){
        if(n < 0){
            return 0;
        }
        for(int k = 1; k < n + 1; k++){ //取走前k个物品
            for(int c = 1; c < capacity + 1; c++){ //背包容量
                if(c < v1[k]){ // 当前物品体积超过容量则不选
                   dp[k][c] = dp[k - 1][c];
                }
                else{
                    dp[k][c] = Math.max(dp[k-1][c - v1[k]] + w1[k], dp[k - 1][c]);
                }
            }
        }
        return dp[5][20];
    }



    public static void main(String[] args) {
        System.out.println(knap(4, 20));
        System.out.println(knap2(5, 20));
    }

}
