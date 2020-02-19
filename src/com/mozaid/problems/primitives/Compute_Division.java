package com.mozaid.problems.primitives;

public class Compute_Division {

    /**
     * Please understand it in free time
     */
    public static int division(int x, int y) {
        int res = 0;
        while (x >= y) {
            int power = 1;
            while ((y << power) >= (y << (power - 1)) && (y << power) <= x)
                power++;

            res += 1 << (power - 1);
            x -= y << (power - 1);
        }
        return res;
    }

    public static int division_recursive(int x, int y) {
        if (x < y) return 0;
        return 1 + division_recursive(x - y, y);
    }

    public static int division_iterative(int x, int y) {
        int res = 0;
        while (x >= y) {
            ++res;
            x = x - y;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(division(16, 3));
        System.out.println(division_recursive(16, 3));
        System.out.println(division_iterative(16, 3));

    }

}
