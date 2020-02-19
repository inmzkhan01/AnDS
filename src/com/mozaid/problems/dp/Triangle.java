package com.mozaid.problems.dp;

import java.util.List;

import static java.util.Arrays.asList;

//https://leetcode.com/problems/triangle/
public class Triangle {

     public int minimumSum(List<List<Integer>> triangle) {
        Integer[][] result = new Integer[triangle.size()][];
        for(int row = 0; row < triangle.size(); row++) {
            result[row] = new Integer[triangle.get(row).size()];
        }
        return minimumSum(triangle, 0, 0, result);
    }

    private int minimumSum(List<List<Integer>> triangle, int row, int col, Integer[][] result) {
        if(result[row][col] == null) {
            int curr = triangle.get(row).get(col);

            //Leaf row.
            if(row == triangle.size() - 1) {
                return result[row][col] = curr;
            }

            int firstAdjacent = minimumSum(triangle, row + 1, col, result);
            int secondAdjacent = minimumSum(triangle, row + 1, col+1, result);

            return result[row][col] = curr + Math.min(firstAdjacent, secondAdjacent);
        }
        return result[row][col];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = asList(asList(2), asList(3,4), asList(6,5,7),  asList(4,1,8,3));
        System.out.println(new Triangle().minimumSum(triangle));
    }

}
