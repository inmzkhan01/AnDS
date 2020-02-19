package com.mozaid.problems.backtracking;

// https://leetcode.com/problems/sudoku-solver/
public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        canSolve(board, 0, 0);
    }

    private boolean canSolve(char[][] board, int row, int col) {
        if (col == board[row].length) {
            col = 0;
            row++;
            if (row == board.length) {
                return true;
            }
        }

        if (board[row][col] != '.') {
            return canSolve(board, row, col + 1);
        }

        for (int i = 1; i <= board.length; i++) {
            char placingChar = (char) (i + '0');
            if (canPlaceChar(board, row, col, placingChar)) {
                board[row][col] = placingChar;
                if (canSolve(board, row, col + 1)) {
                    return true;
                }
                board[row][col] = '.';
            }
        }
        return false;
    }

    private boolean canPlaceChar(char[][] board, int row, int col, char placingChar) {
        //Check Row
        for (int i = 0; i < board[0].length; i++) {
            if (board[row][i] == placingChar) {
                return false;
            }
        }

        //Check Column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == placingChar) {
                return false;
            }
        }

        //Check Box
        int boxSize = (int) Math.sqrt(board.length);

        int topLeftRowIndex = (row / boxSize) * boxSize;
        int topLeftColIndex = (col / boxSize) * boxSize;

        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                if (board[topLeftRowIndex + i][topLeftColIndex + j] == placingChar) {
                    return false;
                }
            }
        }
        return true;
    }



    public boolean isValidSudoku(char[][] board) {
        return isValidSudoku(board, 0, 0);
    }

    private boolean isValidSudoku(char[][] board, int row, int col) {
        if (col == board[row].length) {
            col = 0;
            row++;
            if (row == board.length) {
                return true;
            }
        }

        if (board[row][col] == '.') {
            return isValidSudoku(board, row, col + 1);
        }

        if (isInvalidPlacement(board, row, col)) {
            return false;
        }
        return isValidSudoku(board, row, col + 1);
    }

    private boolean isInvalidPlacement(char[][] board, int row, int col) {
        char placedChar = board[row][col];

        //Check Row
        for (int i = 0; i < board.length; i++) {
            if (i != col && board[row][i] == placedChar) {
                return true;
            }
        }

        //Check Column
        for (int i = 0; i < board.length; i++) {
            if (i != row && board[i][col] == placedChar) {
                return true;
            }
        }

        //Check Box
        int boxSize = (int) Math.sqrt(board.length);

        int boxTopLeftRowIndex = (row / boxSize) * boxSize;
        int boxTopLeftColIndex = (col / boxSize) * boxSize;

        for (int i = 0; i < boxSize; i++) {
            int boxRow = boxTopLeftRowIndex + i;
            for (int j = 0; j < boxSize; j++) {
                int boxCol = boxTopLeftColIndex + j;
                if (boxRow != row && boxCol != col && board[boxRow][boxCol] == placedChar) {
                    return true;
                }
            }
        }

        return false;
    }

}
