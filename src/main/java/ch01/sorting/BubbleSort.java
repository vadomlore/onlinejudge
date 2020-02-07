package ch01.sorting;

import java.util.Arrays;

public class BubbleSort {

    public void bubble(int A[], int low, int high) {
        while(low < high -1) {
            if(A[low + 1] < A[low] ) {
                Utility.swap(A, low, low + 1);
            }
            low++;
        }
    }

    public void sort(int A[], int low, int high){   //high - low <=> 元素个数
        while(1 < high - low ) { //如果还有多余1个元素
            bubble(A, low, high--);
        }
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,2,1};
        new BubbleSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{1,3,2,4,5};
        new BubbleSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{2,1};
        new BubbleSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{1};
        new BubbleSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{};
        new BubbleSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
    }
}
