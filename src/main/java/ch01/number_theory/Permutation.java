package number_theory;
//　递归法求解的思路是先固定第一个元素，求剩下的全排列，求剩下的全拍列时，固定剩余元素中的第一个元素，再求剩下元素的全排列，直到就剩一个元素停止。
//
//        　　例如求集合{a，b，c，d}的全排列。
//
//        　　1、固定元素a求{b，c，d}元素的全排列
//
//        　　　　（1）、固定元素b求{c，d}的全排列
//
//        　　　　　　1）、固定元素c ，得到一个排列方式（a，b，c，d）
//
//        　　　　　　2）、固定元素d，得到一种排列方式（a，b，d，c）
//
//        　　　　（2）、固定元素c求{b，d}的全排列
//
//        　　　　　　1）、固定元素b，得到一个排列方式（a，c，b，d）
//
//        　　　　　　2）、固定元素d，得到一种排列方式（a，c，d，b）
//
//        　　　　（3）、固定元素d求{b，c}的全排列
//
//        　　　　　　1）、固定元素b，得到一个排列方式（a，d，b，c）
//
//        　　　　　　2）、固定元素c，得到一种排列方式（a，d，c，b）
//
//        　　经过上述步骤即可得到以a为第一个元素的全排列，再分别将b，c，d固定为第一元素重复上面过程即可得到{a，b，c，d}的全排列
public class Permutation {

    public static void swap(int[] arr, int i, int j){ //交换两个元素
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static int count = 0;

    public void permutation(int[] arr, int start, int end){
        if(start == end) { // 平凡子问题 递归基
            for(int i = 0; i <= end; i++){
                System.out.print(arr[i]);
            }
            System.out.println();
            count++;
        }

        for(int i = start; i <=end; i++){
            swap(arr, i, start);
            permutation(arr, start + 1, end);
            swap(arr, i, start);
        }

    }

    public static void main(String[] args) {
        new Permutation().permutation(new int[]{1,2,3,4}, 0,3);
        System.out.println("count:"+ count);
    }
}
