package com.kmozaid.problems.primitives;

public class Power {

     public static class Recursive {

        public static double myPow(double x, int n) {
            if(n < 0) {
                n = -n;
                x = 1.0/x;
            }
            return helper(x, n);
        }

        private static double helper(double x, int n) {
            if(n == 0)
                return 1;

            double res = helper(x, n >> 1);

            if((n & 1) == 0)
                return res * res;
            else
                return x * res * res;
        }

        public static void main(String[] args) {
            System.out.println(myPow(2.00000, 10));
            System.out.println(myPow(2.00000, -2));
            System.out.println(myPow(2.10000, 3));
        }
    }




}
