/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 */

import java.util.Stack;

/**
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 *
 *
 */
public class Calculation
{




//    public char[][] priority = new char[][]{
//            //             当前扫描运算符
//            //     '+', '-', '(', ')', '$'
//            // '+'  >    >    <    >    >
//            // '-'  >    >    <    >    >
//            // '('  <    <    <    =   ' '
//            // ')' ' '   ' '  ' '  ' ' ' '
//            // '$'  <    <    <   ' '   '='
//            //栈顶运算符
//    };

    public static final char[][] priority = new char[128][128];
    static {

        priority['+']['+'] = '>';
        priority['+']['-'] = '>';
        priority['+']['('] = '<';
        priority['+'][')'] = '>';
        priority['+']['$'] = '>';

        priority['-']['+'] = '>';
        priority['-']['-'] = '>';
        priority['-']['('] = '<';
        priority['-'][')'] = '>';
        priority['-']['$'] = '>';

        priority['(']['+'] = '<';
        priority['(']['-'] = '<';
        priority['(']['('] = '<';
        priority['('][')'] = '=';
        priority['(']['$'] = ' ';

        priority[')']['+'] = ' ';
        priority[')']['-'] = ' ';
        priority[')']['('] = ' ';
        priority[')'][')'] = ' ';
        priority[')']['$'] = ' ';

        priority['$']['+'] = '<';
        priority['$']['-'] = '<';
        priority['$']['('] = '<';
        priority['$'][')'] = ' ';
        priority['$']['$'] = '=';
    }

    Stack<Integer> result = new Stack<>();
    Stack<Character> oper = new Stack<>();

    int flag = 1; // 上一次输入是否是数字

    public void readNumber(char n){
        if(result.isEmpty() || flag == 0){
            result.push(n - '0');
        } else {
            result.push(result.pop() * 10 + (n - '0'));
        }
    }

    private int calc(char op, int a, int b){
        if(op == '+') {
            return a + b;
        }
        else if(op == '-') {
            return a  - b;
        }
        throw new UnsupportedOperationException("UnsupportedOperation");
    }

    public int calculation(String s){
        oper.push('$');
        s += '$';
        int i = 0;
        while(!oper.isEmpty()){
            char c = s.charAt(i);
            if(Character.isSpaceChar(c)){
                i++;
                continue;
            }
            if(Character.isDigit(c)) {
                readNumber(c);
                i++;
            } else{
                if(priority[oper.peek()][c] == '<') {
                    oper.push(c);
                    i++;
                }else if(priority[oper.peek()][c] == '>') {
                    int b = result.pop();
                    int a = result.pop();
                    char op = oper.pop();
                    result.push(calc(op, a, b));
                }else if(priority[oper.peek()][c] == '='){
                    oper.pop();
                    i++;
                }
            }
            flag = Character.isDigit(c) ? 1 : 0;
        }
        return result.pop();
    }

    public static void main(String[] args) {
        System.out.println(new Calculation().calculation("1 + 1"));
    }

}
