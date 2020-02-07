package ch01.strings;

import java.util.*;
public class StringSorting {
    //使用插入排序拍好序列
    static String sort(String s) {
        char[] chs = new char[s.length()];
        char[] alpha = new char[s.length()];
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            chs[i] = '\0';
            if (!('a' <= s.charAt(i) && s.charAt(i) <= 'z') && !('A' <= s.charAt(i) && s.charAt(i) <= 'Z')) {
                chs[i] = s.charAt(i);
            } else {
                alpha[cnt] = s.charAt(i);
                cnt++;
            }
        }

        for (int i = 1; i < cnt; i++) {
            for (int j = i; j > 0; ) {
                if (less(alpha[j], alpha[j - 1], j, j - 1)) {
                    char tmp = alpha[j - 1];
                    alpha[j - 1] = alpha[j];
                    alpha[j] = tmp;
                    j--;
                } else {
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0, j=0; i < s.length(); i++){
            if(chs[i] != '\0'){
                sb.append(chs[i]);
            }
            else{
                sb.append(alpha[j++]);
            }
        }
        return sb.toString();
    }



    public static boolean less(char a, char b, int ai, int bi){
        if(a == b) {
            return ai < bi;
        }
        if( 'a' <= a && a <='z' && 'a' <= b && b <= 'z'){
            return a < b;
        }
        if( 'A' <= a && a <='Z' && 'A' <= b && b <= 'Z'){
            return a < b;
        }

        if( 'A' <= a && a <='Z' && ('a' <= b && b <= 'z')){
            if('a' + a -'A' < b){
                return true;
            }
            else if('a' + a -'A' == b){
                return ai < bi;
            }

            else return false;
        }

        if( 'a' <= a && a <='z' && ('A' <= b && b <= 'Z')){
            if(a < 'a' + b -'A'){
                return true;
            }
            else if('a' + b -'A' == a){
                return ai < bi;
            }
            else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            String line = in.nextLine();
            System.out.println(sort(line));
        }
    }
}

