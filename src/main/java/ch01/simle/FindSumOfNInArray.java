package simle;

/**
 * 在数组中找到是否存在累加和为m组合
 */
public class FindSumOfNInArray {

    boolean existsSumOfAim(int[] array, int sum, int index, int aim){
        if(index == array.length) {
            return sum == aim;
        }

        //对接下来的元素有2种情况，要么选，要么不选
        return existsSumOfAim(array, sum, index + 1, aim) || //不选择的情况,直接跳过当前index
                existsSumOfAim(array, sum + array[index], index + 1, aim); //选择的情况将选择结果加入到sum中
    }

    boolean existsSumofAim(int[] array, int aim){
        return existsSumOfAim(array, 0, 0, aim);
    }

    //判断总数存在区间不大于所有正元素的最大值，不小于所有负元素的最小值
    //  minSum(seq) <= t <= maxSum(seq)

    public static void main(String[] args) {
        int[] array = new int[]{1, -5, 5, 1};
        System.out.println(new FindSumOfNInArray().existsSumofAim(array, 1));
        System.out.println(new FindSumOfNInArray().existsSumofAim(array, 2));
        System.out.println(new FindSumOfNInArray().existsSumofAim(array, 6));
        System.out.println(new FindSumOfNInArray().existsSumofAim(array, -5));
        System.out.println(new FindSumOfNInArray().existsSumofAim(array, 5));
        System.out.println(new FindSumOfNInArray().existsSumofAim(array, -4));
        System.out.println(new FindSumOfNInArray().existsSumofAim(array, 3));
    }
}
