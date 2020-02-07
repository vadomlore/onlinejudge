package simle;

import java.util.*;

public class UniqueNumber {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int[] array = new int[n];
            for(int i = 0; i < n; i++){
                array[i] = in.nextInt();
            }
            Arrays.sort(array);
            if(array.length  == 1) {
                System.out.println(array[0]);
            }

            /**
             * 思路：两个下标i,j
             *
             * i 指示是否将当前元素归入，j指向当前扫描的元素
             *
             * 若 a[i] != a[j] 归入; j =j+1; i = i+1;
             */
            int i = 0;
            for(int j = 1; j < n; j++){
                if(array[i] != array[j]){   //j可归入
                    i++;  //腾出1个空间
                    array[i] = array[j]; //可归入，归入元素
                    //j++;            //继续扫描
                }

                //由于在for循环中已经有j++迭代，所以可以简化
//                else{
//                    j++;  //不可归入，继续扫描
//                }
            }
            for(int k = 0; k <= i; k++){
                System.out.println(array[k]);
            }
        }
    }
}
