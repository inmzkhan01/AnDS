package graphs;

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

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}};

        System.out.println(numberOfIsland(matrix));
    }
}
