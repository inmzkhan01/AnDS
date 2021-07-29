package com.kmozaid.problems.binarysearch;

public class Square_Root_Of_32_Bit_Unsigned_Number {

    static int squareRoot(long num) {
        int begin = 0;
        int end = 65536;
        while (begin + 1 < end) {
            int mid = begin + (end - begin) / 2;
            long midSqr = (long) mid * mid;
            if (midSqr == num) {
                return mid;
            } else if (midSqr > num) {
                end = mid;
            } else {
                begin = mid;
            }
        }
        return begin;
    }

    public static void main(String[] args) {
        System.out.println(squareRoot(1));
        System.out.println(squareRoot(81));
        System.out.println(squareRoot(12));
    }
}
