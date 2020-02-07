import java.util.Scanner;
import java.util.Stack;

//输入0-9 [0-99以内的加减法]按等号后会得出结果
public class SimpleExpression {



    public static void readNumber(char c, boolean isPrevDigit, Stack<Integer> result){
        if(result.isEmpty() || !isPrevDigit){
            result.push(c-'0');
        }
        else {
            result.push((result.pop() - '0') * 10 + (c - '0'));
        }
    }

    public static void calculate(Stack<Integer> result, Stack<Character> operatorStack){
        char op = operatorStack.pop();
        int b = result.pop();
        int a = result.pop();
        if(op == '+')
            result.push(a + b);
        else if(op == '-')
            result.push(a - b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack<Character> operatorStack = new Stack<Character>();
        Stack<Integer> resultStack = new Stack<Integer>();
        int result = 0;
        boolean isPrevDigit = true;
        String st = scanner.nextLine();
        for(int i = 0; i < st.length(); i++){
            char c = st.charAt(i);
            if(Character.isDigit(c)){
                readNumber(c, isPrevDigit, resultStack);
                isPrevDigit = true;
            }
            else if(c == '+' || c == '-'){
                isPrevDigit = false;
                if(!operatorStack.isEmpty()){
                    calculate(resultStack, operatorStack);
                }
                operatorStack.push(c);
            }
            else if(c == '='){
                isPrevDigit = false;
                if(!operatorStack.isEmpty()){
                    calculate(resultStack, operatorStack);
                }
                result = resultStack.pop();
            }
        }
        System.out.println(result);
    }
}
