package ch01;

import java.util.Scanner;

public class IOUtil {
    public static String getLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int[][][] create3DArray(int p, int q, int r, int initValue){
        int[][][] array = new int[p][q][r];
        for(int i = 0; i < p; i++){
            for(int j = 0; j < q; j++){
                for (int k = 0; k < r; k++)
                    array[i][j][k] = initValue;
            }
        }
        return array;
    }
}
