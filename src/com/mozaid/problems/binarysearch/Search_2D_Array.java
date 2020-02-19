package com.mozaid.problems.binarysearch;

public class Search_2D_Array {

    //https://leetcode.com/problems/search-a-2d-matrix/
    static boolean searchMatrix(int[][] matrix, int target) {
        if (null == matrix || 0 == matrix.length) return false;

        int rows = matrix.length, cols = matrix[0].length;

        int left = 0, right = rows * cols - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[mid / cols][mid % cols] == target) {
                return true;
            }
            if (matrix[mid / cols][mid % cols] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1, 1}}, 2));
        System.out.println(searchMatrix(new int[][]{{1, 2}}, 2));
    }

}