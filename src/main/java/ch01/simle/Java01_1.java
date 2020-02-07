package simle;

import java.util.*;

public class Java01_1 {
    static  boolean existsSumOfAim(Integer[] array, int sum, int index, int aim){
        if(index == array.length) {
            return sum == aim;
        }

        //对接下来的元素有2种情况，要么选，要么不选
        return existsSumOfAim(array, sum, index + 1, aim) || //不选择的情况,直接跳过当前index
                existsSumOfAim(array, sum + array[index], index + 1, aim); //选择的情况将选择结果加入到sum中
    }

    static boolean existsSumofAim(Integer[] array, int aim){
        return existsSumOfAim(array, 0, 0, aim);
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            ArrayList<Integer> array = new ArrayList<Integer>();
            int n = Integer.parseInt(in.nextLine());
            int sum5 = 0;
            int sum3 = 0;
            String[] values = in.nextLine().split(" ");

            int remain = 0;
            for(int i = 0; i < n; i++){
                int value = Integer.parseInt(values[i]);
                if(value % 5 == 0) sum5 += value;
                else if(value % 3 == 0) sum3 += value;
                else{
                    array.add(value);
                    remain += value;
                }
            }

            Integer[] arr = array.toArray(new Integer[0]);
            /**
             * formula
             * remain = partionX + partionY
             * sum5 + partitionX = sum3 + (remain - partionX);
             */
            int remainValue = (sum3 - sum5) + remain;
            if((remainValue) % 2 != 0)  //数据分布到连个数组且相当,iff 该数位偶数even
            {
                System.out.println("false");
            }
            else{
                if(existsSumofAim(arr, remainValue / 2)){ //只需要找到和为剩余元素的一半即可
                    System.out.println("true");
                }else{
                    System.out.println("false");
                }
            }
        }
    }
}