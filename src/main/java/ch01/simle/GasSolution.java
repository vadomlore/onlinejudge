package simle;

public class GasSolution {
    public static class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            for(int i = 0; i < gas.length; i++) { // 从i号加油站出发
                if (canMoveAround(i, gas, cost)) {
                    return i;
                }
            }
            return -1;
        }

        public boolean canMoveAround(int i, int[] gas, int[] cost) {
            int sum = 0;
            int j = i;
            do {
                sum += gas[j];
                sum -= cost[j];
                if(sum < 0) {
                    return false;
                }
                j = (j + 1) % cost.length;
            } while (j != i);
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canCompleteCircuit(new int[]{2,3,4}, new int[]{3, 4, 3}));
    }
}
