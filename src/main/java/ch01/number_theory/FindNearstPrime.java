package number_theory;


//任意一个偶数（大于2）都可以由2个素数组成，组成偶数的2个素数有很多种情况，本题目要求输出组成指定偶数的两个素数差值最小的素数对
public class FindNearstPrime {
    boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void solve(int n) {
        int mid = n / 2;
        int a = 0;
        int b = 0;
        for (int i = mid; 2 <= i; i--) {
            if (isPrime(i) && isPrime(n - i)) {
                a = i;
                b = n - i;
                break;
            }
        }
        System.out.println(a + " " + b);
    }


    public static void main(String[] args) {
        new FindNearstPrime().solve(20);
    }
}
