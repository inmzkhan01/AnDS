package com.kmozaid.problems.graphs;

public class Number_Of_Islands {

    static class UnionFindMethod {
        int count; //no of component
        int[] parent;
        int[] rank;

        UnionFindMethod(int[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int positionInParent = ((i * n) + j);
                    if (grid[i][j] == 1) {
                        parent[positionInParent] = positionInParent;
                        ++count;
                    }
                    rank[positionInParent] = 0;
                }
            }
        }


        private int find(int p) {
            if (p != parent[p]) parent[p] = find(parent[p]);
            return parent[p];
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP != rootQ) {
                if (rank[rootP] < rank[rootQ]) {
                    parent[rootP] = rootQ;
                } else if (rank[rootQ] < rank[rootP]) {
                    parent[rootQ] = rootP;
                } else {
                    parent[rootQ] = rootP;
                    rank[rootP] += 1;
                }
                count--;
            }
        }

        public static int numberOfIsland(int[][] grid) {
            UnionFindMethod unionFindMethod = new UnionFindMethod(grid);
            int m = grid.length;
            int n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        grid[i][j] = 0;
                        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                            unionFindMethod.union(i * n + j, (i - 1) * n + j);
                        }
                        if (i + 1 < m && grid[i + 1][j] == 1) {
                            unionFindMethod.union(i * n + j, (i + 1) * n + j);
                        }
                        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                            unionFindMethod.union(i * n + j, i * n + j - 1);
                        }
                        if (j + 1 < n && grid[i][j + 1] == 1) {
                            unionFindMethod.union(i * n + j, i * n + j + 1);
                        }
                    }
                }
            }
            return unionFindMethod.count;
        }

    }

    static class DFSMethod {

        static int numberOfIsland(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int nr = grid.length;
            int nc = grid[0].length;
            int num_islands = 0;
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == 1) {
                        ++num_islands;
                        dfs(grid, r, c);
                    }
                }
            }
            return num_islands;
        }

        static void dfs(int[][] grid, int r, int c) {
            int nr = grid.length;
            int nc = grid[0].length;
            if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == 0) {
                return;
            }
            grid[r][c] = 0;
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);
        }

    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}};

        System.out.println(UnionFindMethod.numberOfIsland(matrix));

        int[][] matrix2 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}};

        System.out.println(DFSMethod.numberOfIsland(matrix2));
    }
}
