package ch01.dp;

import java.util.*;


public class GoSteps {

    //n行 m列
    public static int goSteps(int m, int n) {
        n = n + 1;
        m = m + 1;
        int[][] array = new int[n][m];
        for(int j = 0; j < m; j++){
            array[0][j] = 1;
        }
        for(int i = 0; i < n; i++){
            array[i][0] = 1;
        }
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++)
                array[i][j] = array[i][j-1] + array[i-1][j];
        }
        return array[n-1][m-1];
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int m = in.nextInt();
            int n = in.nextInt();
            System.out.println(goSteps(m, n));
        }
    }
}