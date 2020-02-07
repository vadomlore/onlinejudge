package simle;

import java.util.*;

public class HexToDeciminal{

    public static Map<Character, Integer> map = new HashMap();
    static {
        map.put('0', 0);
        map.put('1', 1);
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);
        map.put('A', 10);
        map.put('B', 11);
        map.put('C', 12);
        map.put('D', 13);
        map.put('E', 14);
        map.put('F', 15);
    }

    public static int hexToDeciminal(String s, int base){
        s = s.substring(2, s.length());
        int sum = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            sum += map.get(s.charAt(i)) * ((int)Math.pow(base, n - i - 1));
        }
        return sum;
    }

    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        while(in.hasNext()){
//            String s = in.nextLine();
//            System.out.println(hexToDeciminal(s, 16));
//        }

        System.out.println("a".substring(1));
        List<String> a = new ArrayList<>();
        a.add("3");
        a.add("4");
        String s = String.join(";", (List)a);
        System.out.println(s);
        System.out.println(Math.ceil(10));
    }
}