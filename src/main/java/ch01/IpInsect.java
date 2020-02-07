import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IpInsect {

    public static List<Long> decToBin(long v){
        List<Long> b = new LinkedList<>();
        int count = 0;
        while(v != 0) {
            b.add(0, v % 2);
            count++;
            v /= 2;
        }
        for(int i = 0; i < 8-count; i++){
            b.add(0, 0L);
        }
        return b;
    }

    public static long binToInt(List<Long> b){
        long sum = 0;
        long base = 1;
        for(int i = b.size() - 1; i>=0; i--){
            if(b.get(i) == 1){
                sum = sum + base;
            }
            base *= 2;
        }
        return sum;
    }



    public static long ipToInt(String s){
        List<Long> bits = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < s.length()){
            if(s.charAt(i) != '.'){
                sb.append(s.charAt(i));
            }
            else{
                long v = Long.parseLong(sb.toString());
                bits.addAll(decToBin(v));
                sb = new StringBuilder();
            }
            i++;
        }
        long v = Long.parseLong(sb.toString());
        bits.addAll(decToBin(v));
        assert bits.size() == 32;
        return binToInt(bits);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        System.out.println(ipToInt("192.168.1.123"));
    }
}
