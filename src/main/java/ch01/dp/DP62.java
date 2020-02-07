package ch01.dp;

import ch01.IOUtil;

import java.util.Scanner;

/**
 *
 * 统计区间[L,R]不含数字4和连续62的数字数量
 * 0<L<=R<1000000,多组样例,L和R都为0时结束
 *
 * 如14不行,162不行,612可以
 *
 * 思路:暴力搜索减枝
 * 因为4和62会有多种情况,剪纸可以起到很大作用
 * 把问题简化为写一个搜索表,求[0,x]之间符合条件的数量
 *  数位DP 不要62;首先对x进行拆分
 *  存放在数组中,方便取出某一位
 *  Pos表示当前是第几位
 *  Six表示
 */

public class DP62 {

    static int[] dig = new int[10];

    static int solve(int x){
        if(x < 0 ) return 0;
        int cnt = 0;
        while(x > 0) {
            dig[cnt++] = x %10;
            x /= 10;
        }
        return dfs(cnt - 1, false, true);
    }

    /**
     * @param pos 表示当前是第几位
     * @param six 表示前一位是否是6
     * @param flag 表示前几位是否已经是临界大小,比如x是123,如果前两位已经取了12,第三位就不能超过3
     * @return
     */
    static int dfs(int pos, boolean six, boolean flag) {
        if(pos < 0) return 1;
        int ans = 0;
        for(int i = 0; i < 10; i++) {
            if(dig[pos] < i && flag) break; // 当前为数已经不能约束下标,不在继续遍历 如 123,当前是3,那么i=4就不能继续往下进行
            if( i == 2 && six) continue;
            if(i == 4) continue;
            ans += dfs(pos - 1, i == 6, flag && i == dig[pos]);
        }
        return ans;
    }

    //改进使用记忆化搜索
    //显然可以把dfs的参数作为状态,Dp[i][j][k]
    //pos表示i,six表示j,flag为k的状态
    //dfs2
    static int solve2(int x){
        if(x < 0 ) return 0;
        int cnt = 0;
        int[][][] dp = IOUtil.create3DArray(20, 2, 2, -1);
        while(x > 0) {
            dig[cnt++] = x %10;
            x /= 10;
        }
        return dfs2(cnt - 1, false, true, dp);
    }

    static int dfs2(int pos, boolean six, boolean flag, int dp[][][]) {
        if(pos < 0) return 1;
        int ans = 0;
        int j = six ? 1 : 0;
        int k = flag ? 1 : 0;

        if(dp[pos][j][k] != -1) {
            return dp[pos][j][k];
        }
        for(int i = 0; i < 10; i++) {
            if(dig[pos] < i && flag) break; // 当前为数已经不能约束下标,不在继续遍历 如 123,当前是3,那么i=4就不能继续往下进行
            if( i == 2 && six) continue;
            if(i == 4) continue;
            ans += dfs(pos - 1, i == 6, flag && i == dig[pos]);
        }
        return dp[pos][j][k] = ans;
    }
    //改进使用记忆化搜索
    //显然可以把dfs的参数作为状态,Dp[i][j][k]
    //pos表示i,six表示j,flag为k的状态

    public static void main(String[] args) {
        int L;
        int R;
        String s;
        while (!"".equals(s = IOUtil.getLine())) {
            String[] str = s.split(" ");
            L = Integer.parseInt(str[0]);
            R = Integer.parseInt(str[1]);
            System.out.println("===solve1===");
            long t1 = System.currentTimeMillis();
            System.out.println(solve(R) - solve(L - 1));
            long t2 = System.currentTimeMillis();
            System.out.println("time: " + (t2 - t1));


            System.out.println("===solve2===");
            t1 = System.currentTimeMillis();
            System.out.println(solve2(R) - solve2(L - 1));
            t2 = System.currentTimeMillis();
            System.out.println("time: " + (t2 - t1));

        }
    }
}
