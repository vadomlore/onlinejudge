package simle;

import java.util.*;

public class ReverseWord {


    public static ArrayList<String> reverseWords(String line) {
        ArrayList<String> arr = new ArrayList<>();

        String s = "";
        char prev = ' ';
        for(int i = 0; i < line.length(); i++) {
            char curr = line.charAt(i);
            if(!Character.isAlphabetic(prev) && Character.isAlphabetic(curr)){
                s += String.valueOf(curr);
            }
            else if((Character.isAlphabetic(prev) && Character.isAlphabetic(curr))){
                s += String.valueOf(curr);
            }
            else if((Character.isAlphabetic(prev) && !Character.isAlphabetic(curr))){
                arr.add(s);
                s = "";
            }
            prev = curr;
            if(i == line.length() - 1 && Character.isAlphabetic(curr)){
                arr.add(s);
            }
        }
        return arr;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            ArrayList<String> arr = reverseWords(s);
            for(int i = arr.size() - 1; i >=0; i--) {
                System.out.print(arr.get(i));
                if(i != 0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}