package com.mozaid.problems.primitives;

public class Uniform_Random_Number {

    public static int uniform_random_a_b(int a, int b) {
        int t = b - a + 1, res;

        do {
            res = 0;
            for (int i = 0; (1 << i) < t; ++i) {
                res = (res << 1) | ZeroOneRandom.random();
            }
        } while (res >= t);

        return res + a;
    }


    public static void main(String[] args) {
        int i = 0;

        while (i++ < 10)
            System.out.println(uniform_random_a_b(6, 12));
    }
}
