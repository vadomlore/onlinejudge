package simle;


import java.util.*;


public class Main2 {

    // 111
    // 大写字母 0001  1
    // 小写字母 0010  2
    //数字     0100  4
    //其他字符     1000   8

    public static int numOfOne(int n){
        int count = 0;
        while(n > 0) {
            count += ((n & 1) == 1 ? 1 : 0);
            n  = n >> 1;
        }
        return count;
    }

    public static boolean containsEqualOrMoreThan3CategoryOfChars(String s){
        int bits = 0;
        for(int i = 0; i < s.length();i++){
            char ch = s.charAt(i);
            if( 'A' <= ch &&  ch <= 'Z'){
                bits |= 1;
            }

            else if( 'a' <= ch &&  ch <= 'z'){
                bits |= 2;
            }
            else if( '0' <= ch &&  ch <= '9'){
                bits |= 4;
            }
            else {
                bits |= 8;
            }
        }
        return numOfOne(bits) >= 3;
    }

    public static boolean lengthMoreThan(String s, int n){
        return n < s.length();
    }


    //是否具有长度超过3的字符串子串
    public static boolean hasDuplicateSubstr(String s, int low, int high){
        if(s.length() <= 3 ) return false;

        int startIndexA = low;
        int endIndexA = startIndexA + 3;

        if(endIndexA <= s.length()){
            String strA = s.substring(startIndexA, endIndexA);

            int startIndexB = high - 3;
            int endIndexB = high;

            while(endIndexA <= startIndexB ) {
                String strB = s.substring(startIndexB, endIndexB);
                if(strA.equals(strB)) return true;
                high--;
                startIndexB = high - 3;
                endIndexB = high;
            }
        }
        return false;
    }

    public static boolean hasDuplicateSubstr(String s){
        for(int i = 0; i < s.length(); i++) {
            if(hasDuplicateSubstr(s, i, s.length())) {
                return true;
            }
        }
        return false;
    }



    public static boolean validate(String s) {
        return containsEqualOrMoreThan3CategoryOfChars(s) &&
                lengthMoreThan(s, 8) &&
                !hasDuplicateSubstr(s);
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String passwd = in.nextLine();
            if(validate(passwd)){
                System.out.println("OK");
            }else{
                System.out.println("NG");
            }
        }
    }

}
