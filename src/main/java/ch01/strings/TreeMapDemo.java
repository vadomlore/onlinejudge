package ch01.strings;

import java.util.*;


public class TreeMapDemo {

    public static class CharacterPair{
        char c;
        int num;
        public CharacterPair(char c, int num) {
            this.c = c;
            this.num = num;
        }
    }



    public static void stringStatistics(String s) {
        Map<Character, CharacterPair> map = new HashMap<>();

        for(int i  = 0 ;i < s.length(); i++){
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c, new CharacterPair(c, 1));
            }
            else{
                map.put(c, new CharacterPair(c, map.get(c).num + 1));
            }
        }

        ArrayList<CharacterPair> pair = new ArrayList<>();
        map.values().forEach(x->{
            pair.add(x);
        });


        Collections.sort(pair, (o1, o2)->{
            if(o1.num != o2.num) {
                return o2.num - o1.num;
            }
            return o1.c - o2.c;
        });


        for(int i = 0; i < pair.size(); i++){
            System.out.printf("%c", pair.get(i).c);
        }
        System.out.println();
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            stringStatistics(s);
        }
    }
}