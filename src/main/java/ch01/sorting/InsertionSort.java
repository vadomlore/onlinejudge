package ch01.sorting;


import java.util.Arrays;

/**
 * 插入排序
 *
 *       *  |
 *     * *  |   * *
 *   * * *  |   * * *
 *   sorted    unsorted
 */
public class InsertionSort {


    public void sort(int A[], int low, int high){
        for (int i = low + 1; i < high; i++) {
            for(int j = i; 0 < j && A[j] < A[j - 1];j--){ // 如果j移动到0的位置,假设下标-1哨兵元素为最小值，则j元素不必在移动
                Utility.swap(A, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,2,1};
        new InsertionSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{1,3,2,4,5};
        new InsertionSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{2,1};
        new InsertionSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{1};
        new InsertionSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{};
        new InsertionSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
    }
}
