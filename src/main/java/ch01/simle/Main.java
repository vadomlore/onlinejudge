package simle;
import java.util.*;


// 使用计数 + 小顶堆 + 删除数据
public class Main {


    public static class CharFrequency {

        public char c;
        public int count;
        public CharFrequency(char c, int count){
            this.c = c;
            this.count = count;
        }
    }

    public static String removeLeastFrequencyChars(String s){
        if(s == null || s.equals("")) return "";
        PriorityQueue<CharFrequency> minHeap = new PriorityQueue<>(20,new Comparator<CharFrequency>(){
            @Override
            public int compare(CharFrequency o1, CharFrequency o2){
                return o1.count - o2.count;
            }
        } );
        int[] charFrequencys = new int[26];
        for(int i = 0; i < 26; i++){
            charFrequencys[i] = 0;
        }
        for(int i = 0; i < s.length();i++){
            charFrequencys[s.charAt(i) - 'a']++;
        }

        //构建小顶堆
        for(int i = 0; i < charFrequencys.length;i++){
            if( charFrequencys[i] != 0){
                minHeap.offer(new CharFrequency( (char)(i + 'a'), charFrequencys[i]));
            }
        }

        CharFrequency chy = minHeap.poll();
        if(chy != null) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < s.length();i++){
                if(s.charAt(i) != chy.c){
                    sb.append(s.charAt(i));
                }
            }
            s = sb.toString(); //删除字符串中最小值
            int cnt = chy.count; //记录频率最小值
            while(!minHeap.isEmpty() && minHeap.peek().count == cnt){
                chy = minHeap.poll();
                sb = new StringBuilder();
                for(int i = 0; i < s.length();i++){
                    if(s.charAt(i) != chy.c){
                        sb.append(s.charAt(i));
                    }
                }
                s = sb.toString();
            }
            return s;
        }
        return "";
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            System.out.println(removeLeastFrequencyChars(s));
            System.out.println(Math.max(1,2));
        }
    }
}