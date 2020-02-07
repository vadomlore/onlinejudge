package sorting;

import java.util.Arrays;

public class MergeSort {


    /**
     * 使用了3个数字，可以优化成只是用一个额外的数组
     * @param A
     * @param low
     * @param mid
     * @param high
     */
    public void merge0(int A[], int low, int mid, int high) {
        int[] ai = new int[mid - low];
        int[] bi = new int[high - mid];
        int[] temp = new int[high - low];
        int aIndex = 0;
        int bIndex = 0;

        int k = 0;
        for (int i = low; i < mid; i++) {
            ai[aIndex++] = A[i];
        }
        for (int i = mid; i < high; i++) {
            bi[bIndex++] = A[i];
        }

        int i = 0; int j = 0;

        while(i < ai.length && j < bi.length) { //"&&" 表示需要两个条件都满足, 如果退出循环表示至少有一个条件不满足
            if(ai[i] < bi[j]){
                temp[k] = ai[i];
                i++;
            }
            else{
                temp[k] = bi[j];
                j++;
            }
            k++;
        }
        while (i < ai.length) {
            temp[k++] = ai[i++];
        }
        while (j < bi.length) {
            temp[k++] = bi[j++];
        }
        for (k = 0; k < temp.length; k++) {
            A[low + k] = temp[k];
        }
    }


    /**
     * 只使用一个临时数组，p, q 标记左右数组起始位置
     * @param A
     * @param low
     * @param mid
     * @param high
     */
    public void merge(int A[], int low, int mid, int high) {

        int[] temp = new int[high - low];
        int p = low; //虚拟p数组 的起始下标
        int q = mid; //虚拟q数组的起始下标

        int cnt = 0;
        for(int k = low; k < high; k++){ //总共执行的访问次数
            if(mid <= p ) { // p数组已经访问完毕
                temp[cnt++] = A[q++];
            }
            else if(high <= q ) { // q数组已经访问完毕
                temp[cnt++] = A[p++];
            }
            else if (A[p] < A[q]) { //选择较小元素
                temp[cnt++] = A[p++];
            } else{
                temp[cnt++] = A[q++];
            }
        }

        for (int k = low, j = 0; k < high; k++) {
            A[k] = temp[j++];
        }
    }

    public void sort(int A[], int low, int high){
        if(high - low < 2) return;
        int mid = (low + high) >> 1;
        sort(A, low, mid);
        sort(A, mid, high);
        merge(A, low, mid, high);
    }

    public static void main(String[] args) {
        int[] A = new int[]{5,4,3,2,1};
        new MergeSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{1,3,2,4,5};
        new MergeSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{2,1};
        new MergeSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{1};
        new MergeSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
        A = new int[]{};
        new MergeSort().sort(A, 0, A.length);
        System.out.println(Arrays.toString(A));
    }
}
