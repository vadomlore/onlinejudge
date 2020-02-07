package simle;

import java.util.*;
public class SplitString {
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int SPLIT_SIZE = 8;
        while(in.hasNext()){
            String s = in.nextLine();
            if(s == null || "".equals(s)) continue;
            int len = s.length();
            int groupCount =  len / SPLIT_SIZE;
            if(groupCount * SPLIT_SIZE < len) {
                groupCount += 1;
            }
            for(int i = 0; i < groupCount; i++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < SPLIT_SIZE; j++){
                    int index = i * SPLIT_SIZE + j;
                    if( index < len){
                        sb.append(s.charAt(index));
                    }else{
                        sb.append('0');
                    }
                }
                System.out.println(sb.toString());
            }
        }
    }

}