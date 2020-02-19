package com.mozaid.problems.dp;

/**
 * fib(0): 0, fib(1): 1, fib(2): 1, fib(3): 2, fib(4): 3, fib(5): 5, fib(6): 8 ...
 */
public class FibonacciNumber {

    static class Recursive {
        static int fib(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            return fib(n - 1) + fib(n - 2);
        }
    }

    /**
     * Top down approach.
     */
    static class RecursiveWithMemorization {
        static int fib(int n) {
            int[] cache = new int[n + 1];
            cache[0] = 0;
            cache[1] = 1;
            return fib(n, cache);
        }

        static int fib(int n, int[] cache) {
            if (n == 0 || n == 1) {
                return cache[n];
            }
            if (cache[n] != 0) {
                return cache[n];
            }
            return cache[n] = fib(n - 1, cache) + fib(n - 2, cache);
        }
    }

    /**
     * Bottom Up Approach.
     */
    static class DynamicProgrammingBottomUp {
        static int fib(int n) {
            if(n<2) {
                return n;
            }
            int fib[] = new int[n+1];
            fib[0] = 0;
            fib[1] = 1;

            for(int i=2; i<=n; i++) {
                fib[i] = fib[i-1] + fib[i-2];
            }
            return fib[n];
        }
    }

    /**
     * Optimized bottom-up dynamic Fibonacci solution
     *
     */
    static class DPBottomUp {
        static int fib(int n) {
            if(n<2) {
                return n;
            }
            int n1=0;
            int n2=1;
            for(int i=2; i<n; i++) {
                int c = n1 + n2;
                n1 = n2;
                n2 = c;
            }
            return n1 + n2;
        }
    }

    public static void main(String[] args) {
        System.out.println("Recursive.fib(6): " + Recursive.fib(6));
        System.out.println("RecursiveWithMemorization.fib(6): " + RecursiveWithMemorization.fib(6));
        System.out.println("DynamicProgrammingBottomUp.fib(6): " + DynamicProgrammingBottomUp.fib(6));
        System.out.println("DPBottomUp.fib(6): " + DPBottomUp.fib(6));
    }
}