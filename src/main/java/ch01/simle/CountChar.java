package simle;

import java.util.*;

public class CountChar {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            char ch = in.nextLine().charAt(0);
            int count = 0;
            for(int i = 0; i < s.length();i++){
                if((byte)s.charAt(i) == ch){
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}