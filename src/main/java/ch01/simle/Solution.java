package simle;

import java.util.ArrayList;

class Solution {
    ArrayList<Integer> ints = new ArrayList<>();
    public boolean isHappy(int n) {
        ints.clear();
        while(true){
            n = combine(n);
            if(n == 1) {
                return true;
            }
            if(ints.contains(n) || n == 0) {
                return false;
            }
            ints.add(n);
        }
    }
    
    public int combine(int n) {
        int sum = 0;
        while(n != 0) {
            int x = n % 10;
            sum += x*x;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isHappy(19));
    }
}