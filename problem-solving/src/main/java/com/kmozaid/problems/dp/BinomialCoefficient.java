package com.kmozaid.problems.dp;

public class BinomialCoefficient {

    //nCr = (n!/((n-r)! * r!)) = (n-1)Cr + (n-1)C(r-1)

    static class Recursive {

        static int binomialCoefficient(int n, int r) {
            if (n == r || r == 0) {
                return 1;
            }
            return binomialCoefficient(n - 1, r) + binomialCoefficient(n - 1, r - 1);
        }
    }

    static class RecursiveMemorization {

        static int binomialCoefficient(int n, int r) {
            int[][] coefficient = new int[n + 1][r + 1];
            coefficient[r][r] = 1;
            for (int i = 0; i <= n; i++) {
                coefficient[i][0] = 1;
                if (i <= r) {
                    coefficient[i][i] = 1;
                }
            }

            for(int i=0; i<=n; i++) {
                for(int j=0; j<=r; j++) {
                    System.out.print(coefficient[i][j]+" ");
                }
                System.out.println();
            }

            System.out.println();

            int result = binomialCoefficient(n, r, coefficient);

            for(int i=0; i<=n; i++) {
                for(int j=0; j<=r; j++) {
                    System.out.print(coefficient[i][j]+" ");
                }
                System.out.println();
            }

            return result;
        }

        static int binomialCoefficient(int n, int r, int[][] coefficient) {
            if (n < 0 || r < 0) {
                return 0;
            }
            if (coefficient[n][r] != 0) {
                return coefficient[n][r];
            }
            coefficient[n - 1][r] = binomialCoefficient(n - 1, r, coefficient);
            coefficient[n - 1][r - 1] = binomialCoefficient(n - 1, r - 1, coefficient);
            return coefficient[n][r] = coefficient[n - 1][r] + coefficient[n - 1][r - 1];
        }
    }

    public static void main(String[] args) {
        //System.out.println(Recursive.binomialCoefficient(4, 2));
        System.out.println(RecursiveMemorization.binomialCoefficient(4, 2));
        System.out.println();
        System.out.println(RecursiveMemorization.binomialCoefficient(5, 2));
        System.out.println();
        System.out.println(RecursiveMemorization.binomialCoefficient(7, 4));
    }

}