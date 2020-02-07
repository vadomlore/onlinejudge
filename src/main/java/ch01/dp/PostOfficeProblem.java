package dp;

/**
 *
 * 题意：一条高速公路，有V个村庄，每个村庄均有一个唯一的坐标，选择P个村庄建邮局，问怎么选择，才能使每个村庄到其最近邮局的距离和最小？最后打印这个最小值。
 *
 *         思路：典型的DP问题。
 *
 *         当我们在v个村庄中只建一个邮局，可以推导出，只有邮局位于中间位置，距离和才最小；有一个特殊情况是，当村庄数为偶数，中间位置有两个村庄，经过计算，两个村庄的距离和相等，所以俩位置均可。
 *
 *         可以联想到，N个村庄建P个邮局，相当于每个邮局均有一个作用范围，该邮局位于其作用范围的中间位置，就是要找到一个k，使前k个村庄建P - 1个邮局，最后几个村庄建一个邮局的方案满足题意。
 *
 *         那么，状态转移方程就可以写成：
 *
 *         dp[v][p]：v个村庄建p个邮局的最小距离
 *
 *         dis[i][j]：第i个村庄到第j个村庄之间建1个邮局的最小距离
 *
 *         状态转移方程：dp[v][p] = min(dp[v][p], dp[k][p - 1] + dis[k + 1][v]）
 *             其中 p-1 <= k < v
 *
 *         还有一点，计算dis[i][j]时，dis[i][j - 1]已经计算出来，而且可以推导出无论j - 1为奇数还是偶数，dis[i][j]均可以写成dis[i][j - 1] + j距离i、j中点的距离。
 *
 *
 *         x--x---x---x---x--------x----x
 *         i----------k----k+1 ---------j
 */

public class PostOfficeProblem {
    int V;
    int P;
    int[] cordinate = new int[300]; // village 位置
    int[][] dis = new int[300][300]; // dis[i][j]表示第i个村庄到第j个村庄之间建立1个邮局的最小距离
    int[][] dp = new int[300][30]; //表示第i个村庄之前建立j个邮局的最小距离和

    public static int MAX = 0x3f3f3f;

    public static void memset(int[][] A, int value) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = value;
            }
        }
    }


    void solve(int v0, int p0, int[] arr) {
        V = v0;
        P = p0;
        cordinate = arr;
        for (int i = 1; i <= V - 1; i++) {
            for (int j = i + 1; j <= V; j++) {
                dis[i][j] = dis[i][j - 1] + cordinate[j] - cordinate[(i + j) / 2];
            }
        }
        memset(dp, MAX);

        //处理建立一个邮局的情况
        for (int i = 1; i <= V; i++) {
            dp[i][1] = dis[1][i];
        }

        for (int p = 2; p <= P; p++) { //共建立的邮局数j
            for (int v = p; v <= V; v++) { // 2 ~ i 个村庄共建j个邮局
                for (int k = p - 1 ; k < v ; k++) { // 2到k个村庄建 j - 1 个邮局
                    dp[v][p] = Math.min(dp[v][p], dp[k][p - 1] + dis[k + 1][v]);
                }
            }
        }
        System.out.println(dp[V][P]);
    }

    public static void main(String[] args) {
        new PostOfficeProblem().solve(10, 5, new int[]{-1, 1,2,3,6,7,9,11,22,44,50});

    }

}
