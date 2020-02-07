package sorting;

import java.util.Arrays;

public class QuickSort {

    //[low-------------) mid [mid + 1 -----------) high
    public void quickSort(int arr[], int low, int high){
        if(high - low < 2) return; //元素个数小于2个, 则直接返回
        int mid = partition(arr, low, high -1);
        quickSort(arr, low, mid);
        quickSort(arr, mid + 1, high);

    }

    public int partition(int arr[], int low, int high){
        int pivot = arr[low];
        int i = low; int j = high;
        while(i < j){
            while(i < j && pivot <= arr[j] ) j--;
            arr[i] = arr[j];
            while(i < j && arr[i] <= pivot ) i++;
            arr[j] = arr[i];
        }
        arr[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 4, 5};
        new QuickSort().quickSort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }

}
