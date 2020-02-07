package ch01.dp;

import java.util.Scanner;

public class HeChangDuixing {
    public int solve(int[] array) {
        int[] up = new int[array.length];
        int[] down = new int[array.length];



        for (int i = 0; i < array.length; i++) {
            up[i] = 1;
            for(int j = 0; j < i; j++){
                if(array[j] < array[i] && up[i] < up[j] + 1) //当前元素最大升序序列数
                    up[i] = up[j] + 1;
            }
        }

        for (int i = array.length - 1; i >=0; i--) {
            down[i] = 1;
            for(int j = array.length - 1; j > i; j--){
                if(array[j] < array[i] && down[i] < down[j] + 1) //逆向当前元素最大升序序列数
                    down[i] = down[j] + 1;
            }
        }

        int max=0;
        for(int i=0;i< array.length;i++){
            if(max < up[i] + down [i])
                max=up[i]+down[i];
        }
        return array.length-max+1;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int n = in.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(new HeChangDuixing().solve(arr));
        }
    }

}
