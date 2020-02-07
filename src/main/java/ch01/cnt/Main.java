package ch01.cnt;

import java.util.*;

public class Main {
     public static int maxLengthOfOne(byte b) {
         int maxCount = 0;
         int thisCount = 0;
         while(b > 0) {
             if((b & 1) == 0){ //末尾是0 则清除计数
                 thisCount = 0;
             }
             else{ //末尾是1
                 thisCount++; //增加计数
                 if(maxCount < thisCount) { //maxCount记录到目前为止累计最多连续1的个数  
                     maxCount = thisCount; 
                 }
             }
             b = (byte)(b >> 1);
         }
         return maxCount;
     }
    
     public static int numOfOne(int v){
         int sum = 0;
         while( v  > 0) {
             sum += ((v & 1) == 1)? 1 : 0;
             v = v >> 1;             
         }
         return sum;
     }
      public static void main(String[] args){
           Scanner in = new Scanner(System.in);
           while(in.hasNext()){
               byte  v = in.nextByte();
               System.out.println(maxLengthOfOne(v));
           }
      }
}
