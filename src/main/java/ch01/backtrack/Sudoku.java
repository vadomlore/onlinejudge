package ch01.backtrack;

import java.util.Scanner;

public class Sudoku {

    public void solve(char[][] board){
        helper(board);
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if(board[i][col] == num) return false;
            if(board[row][i] == num) return false;
            //3*(row / 3)行是第i组
            if(board[3*(row / 3) + i / 3][3 * (col/3) + i%3] == num)
                return false;
        }
        return true;
    }

    private boolean helper(char[][] board){
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '0') {
                    for(char num = '1'; num <= '9'; num++){
                        if(isValid(board, i, j, num)){
                            board[i][j] = num;
                            if(helper(board)) return true;
                            board[i][j] = '0';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[9][9];
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board[i][j] = (in.nextInt() + "").charAt(0);
                }
            }
            System.out.println();
            new Sudoku().solve(board);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println(board[i][8]);
            }
        }

    }

}
