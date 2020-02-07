package sorting;


public class QuickSelection {
    //找第k大的元素，减而治之
    int quickSelection(int[] vec, int k){

        for(int lo = 0, hi = vec.length - 1; lo < hi;) {
            int i = lo, j = hi;
            int pivot = vec[lo];
            while(i < j) {
                while(i < j && pivot <=vec[j])
                    j--;
                vec[i] = vec[j];
                while(i < j && pivot <=vec[i])
                    i++;
                vec[j] = vec[i];
            }
            vec[i] = pivot;
            if(k <= i) hi = i - 1;
            if(i <= k) lo = i + 1;
        }
        return vec[k];
    }


    //Majority 众数

    //在集合中若有一半以上元素同为m, 则称之为众数

    //例如{1,2,3,3,3} 3 是众数
    //{1,2,3,3} 3不是众数


    boolean majority(int[] A, int[] maj){
        return majEleCheck(A, maj[0] = majCandidate(A));
    }

    public boolean majEleCheck(int[] A, int maj) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == maj) {
                count++;
            }
            if(count > A.length / 2) return true;
        }
        return false;
    }


    /**
     * 这个是数量最多的元素
     * @param A
     * @return
     */
    int majCandidate(int[] A) {
        int maj = 0;
        for (int c = 0, i = 0; i < A.length; i++) {
            if(0 == c) {
                maj = A[i]; c = 1;
            }
            else{
                c = maj == A[i] ? c+1 : c-1;
            }
        }
        return maj;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 3};
        int[] maj = new int[1];
        boolean has = new QuickSelection().majority(arr, maj);
        System.out.println("has:" + has + "  maj:" + maj[0]);

    }

}
