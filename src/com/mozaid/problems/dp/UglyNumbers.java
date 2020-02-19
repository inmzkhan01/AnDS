package com.mozaid.problems.dp;

//https://leetcode.com/problems/ugly-number/
public class UglyNumbers {

    static boolean isUgly(int num) {

        if (num < 1) return false;

        while (num % 5 == 0) num /= 5;
        while (num % 3 == 0) num /= 3;
        while (num % 2 == 0) num /= 2;

        return num == 1;
    }

    static boolean isUgly2(int num) {
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
            return isUgly2(num / 2);
        } else if (num % 3 == 0) {
            return isUgly2(num / 3);
        } else if (num % 5 == 0) {
            return isUgly2(num / 5);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("Is 12 ugly: " + isUgly(12));
        System.out.println("Is 13 ugly: " + isUgly2(13));
    }

}