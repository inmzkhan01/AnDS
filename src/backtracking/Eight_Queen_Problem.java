package backtracking;

import java.util.HashSet;
import java.util.Set;

public class Eight_Queen_Problem {
    public static void solve(int[][] board) {
        Set<Integer> visited = new HashSet<>();

        solve(board, 0, visited);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean solve(int[][] board, int row, Set<Integer> visited) {
        if (row == board.length) {
            return true;
        }
        for (int j = 0; j < board.length; j++) {
            if (!visited.contains(row) && canPlace(board, row, j)) {
                board[row][j] = 1;
                visited.add(row);
                if (solve(board, row + 1, visited)) {
                    return true;
                }
                visited.remove(row);
                board[row][j] = 0;
            }
        }
        return false;
    }

    private static boolean canPlace(int[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i < board.length && j < board.length; i++, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board = new int[8][8];
        solve(board);
    }

}
