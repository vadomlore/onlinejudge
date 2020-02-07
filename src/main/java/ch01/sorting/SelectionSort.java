package sorting;


import ch01.sorting.Utility;

import java.util.Arrays;
/**
 * 选择排序      *
 *              *   *
 *       *  |   * * *
 *     * *  |   * * *
 *   * * *  |   * * *
 *   sorted    unsorted
 */
public class SelectionSort {




    /**
     * 返回最小值的index
     * @param A
     * @param low
     * @param high
     * @return
     */
    public int selectMin(int A[], int low, int high) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = low; i < high; i++) {
            if (A[i] < min) {
                min = A[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void sort(int A[], int low, int high){
        for (int i = low; i < high; i++) {
            int j = selectMin(A, i, high);
            Utility.swap(A, i, j);
        }
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,2,1};
        new SelectionSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{1,3,2,4,5};
        new SelectionSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{2,1};
        new SelectionSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{1};
        new SelectionSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{};
        new SelectionSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
    }
}
