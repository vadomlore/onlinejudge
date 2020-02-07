package simle;

import java.util.ArrayList;

public class WordDictionaryOrder {
    public static int compare(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int i = 0,j = 0;
        while(i < m && j < n){
            if(s1.charAt(i) == s2.charAt(j)){
                i++;
                j++;
            }
            else if(s1.charAt(i) < s2.charAt(j)) return -1;
            else return 1;
        }
        if(m == n) return 0;
        else if(m < n) return -1;
        else return 1;
    }

    public static String s1(){
        return 10 + ", " + 10;
    }

    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("cap");
        arr.add("to");
        arr.add("cat");
        arr.add("card");
        arr.add("two");
        arr.add("too");
        arr.add("up");
        arr.add("boat");
        arr.add("boot");
        arr.sort(String::compareTo);
        System.out.println(arr);
        System.out.println(s1());
    }

}
