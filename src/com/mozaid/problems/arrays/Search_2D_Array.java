package com.mozaid.problems.arrays;

public class Search_2D_Array {

    //https://leetcode.com/problems/search-a-2d-matrix-ii/
    static boolean searchMatrixII(int[][] matrix, int target) {
        if (null == matrix || 0 == matrix.length) return false;

        int m = matrix.length, n = matrix[0].length;

        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            int num = matrix[i][j];
            if (num == target) {
                return true;
            }

            if (num < target) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(searchMatrixII(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 3));
        System.out.println(searchMatrixII(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 13));
        System.out.println(searchMatrixII(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 34));
    }

}