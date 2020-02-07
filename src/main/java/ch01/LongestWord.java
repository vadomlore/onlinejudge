import java.util.Scanner;

public class LongestWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = "";
        while((s = in.nextLine())!= null){
            int count = 0;
            int n = s.length();
            while( 0 <= --n && s.charAt(n) != ' ') {
                count++;
            }
            System.out.println(count);
        }
    }
}
