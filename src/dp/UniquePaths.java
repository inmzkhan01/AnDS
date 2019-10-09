package dp;

//https://leetcode.com/problems/unique-paths/
//https://leetcode.com/problems/unique-paths-ii/
//https://leetcode.com/problems/unique-paths-iii
public class UniquePaths {

    static class Recursive {

        static int uniquePaths(int m, int n) {
            return helper(m - 1, n - 1);
        }

        static int helper(int i, int j) {
            if (i < 0 || j < 0) {
                return 0;
            }

            if (i == 0 && j == 0) {
                return 1;
            }
            return helper(i, j - 1) + helper(i - 1, j);
        }
    }



    static class Recursive2 {



        static int uniquePaths(int m, int n) {
            return uniquePaths(1, 1, m, n);
        }

        static int uniquePaths(int i, int j, int m, int n) {
            if (i > m || j > n) {
                return 0;
            }

            if (m == i && n == j) {
                return 1;
            }
            return uniquePaths(i, j + 1, m, n) + uniquePaths(i + 1, j, m, n);
        }


    }




    static class RecursiveMemorization {

        static int uniquePaths(int m, int n) {
            int[][] result = new int[m][n];
            return helper(m - 1, n - 1, result);
        }

        static int helper(int i, int j, int[][] result) {
            if (i < 0 || j < 0) {
                return 0;
            }

            if (result[i][j] == 0) {
                if (i == 0 && j == 0) {
                    return result[i][j] = 1;
                }
                return result[i][j] = helper(i, j - 1, result) + helper(i - 1, j, result);
            }
            return result[i][j];
        }
    }

    static class BottomUpDP {

        static int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];

            for (int row = 0; row < m; row++) {
                dp[row][0] = 1;
            }

            for (int col = 0; col < n; col++) {
                dp[0][col] = 1;
            }

            for (int row = 1; row < m; row++) {
                for (int col = 1; col < n; col++) {
                    dp[row][col] = dp[row][col - 1] + dp[row - 1][col];
                }
            }

            return dp[m - 1][n - 1];
        }
    }


    public static void main(String[] args) {
        System.out.println(Recursive.uniquePaths(3, 4));
        System.out.println(RecursiveMemorization.uniquePaths(3, 4));
        System.out.println(BottomUpDP.uniquePaths(3, 4));
        System.out.println(Recursive2.uniquePaths(2, 3));
    }
}
