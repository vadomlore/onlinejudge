package simle;

import java.net.InetAddress;
import java.util.*;

public class Java01 {

     static boolean existsSumOfAim(Integer[] array, int sum, int index, int aim){
        if(index == array.length) {
            return sum == aim;
        }

        //对接下来的元素有2种情况，要么选，要么不选
        return existsSumOfAim(array, sum, index + 1, aim) || //不选择的情况,直接跳过当前index
                existsSumOfAim(array, sum + array[index], index + 1, aim); //选择的情况将选择结果加入到sum中
    }

    static boolean  existsSumofAim(Integer[] array, int aim){
        return existsSumOfAim(array, 0, 0, aim);
    }

    public static void main(String[] args){
        System.out.println("a".substring(1));

        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            ArrayList<Integer> array = new ArrayList<Integer>();
            int n = Integer.parseInt(in.nextLine());
            int sum5 = 0;
            int sum3 = 0;
            String[] values = in.nextLine().split(" ");

            int total = 0;
            for(int i = 0; i < n; i++){
                int value = Integer.parseInt(values[i]);
                total += value;
                if(value % 5 == 0) sum5 += value;
                else if(value % 3 == 0) sum3 += value;
                else{
                    array.add(value);
                }
            }
            Integer[] arr = array.toArray(new Integer[0]);
            int remainValue = total - sum5 - sum3;
            if((remainValue) % 2 != 0)
            {
                System.out.println("false");
            }
            else{
                System.out.println(existsSumofAim(arr, remainValue));
                String s = "";
                char  c = '2';

            }
        }


    }


}