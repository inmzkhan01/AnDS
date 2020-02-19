package com.mozaid.problems.graphs.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 */
public class Rotten_Oranges {

    public static int orangesRotting(int[][] grid) {
        // LEFT, RIGHT, TOP, BOTTOM
        final int directions[][] = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        final int m = grid.length;
        final int n = grid[0].length;

        int freshOranges = 0;
        Queue<Cell> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2)
                    queue.offer(new Cell(i, j));
                else if (grid[i][j] == 1)
                    freshOranges++;
            }
        }

        if (freshOranges == 0)
            return 0;

        int minimumTime = 0;

        while (!queue.isEmpty()) {
            boolean rottenAny = false;
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                Cell cell = queue.poll();
                for (int[] direction : directions) {
                    int x = cell.i + direction[0];
                    int y = cell.j + direction[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(new Cell(x, y));
                        if (!rottenAny) {
                            minimumTime++;
                            rottenAny = true;
                        }
                        freshOranges--;
                    }
                }
            }

        }

        return freshOranges == 0 ? minimumTime : -1;
    }


    private static class Cell {
        int i;
        int j;

        Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {

        int arr1[][] = {{2, 1, 0, 2, 1},
                {1, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}};

        int[][] arr2 = {{2, 1, 0, 2, 1},
                {0, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}};

        System.out.println("Grid1 Minimum Time: " + orangesRotting(arr1));
        System.out.println("Grid2 Minimum Time: " + orangesRotting(arr2));
    }
}
