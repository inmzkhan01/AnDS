package com.kmozaid.problems.dp;

//https://leetcode.com/problems/ugly-number-ii/
public class UglyNumbersII {

    public int nthUglyNumber(int num) {
        int count = 1;
        int i = 2;
        while (count != num) {
            if (i % 5 == 0 || i % 3 == 0 || i % 2 == 0) {
                if (isUgly(i)) count++;
            }
            i++;
        }
        return i-1;
    }

    private boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }

        if (num % 2 != 0 && num % 3 != 0 && num % 5 != 0) {
            return false;
        }

        if (num % 2 == 0) {
            return isUgly(num / 2);
        } else if (num % 3 == 0) {
            return isUgly(num / 3);
        } else if (num % 5 == 0) {
            return isUgly(num / 5);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("10th Ugly Number " + new UglyNumbersII().nthUglyNumber(10));
    }

}
