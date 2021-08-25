package com.kmozaid.java.quizzes;

import java.util.Arrays;

public class CodeChefChallenge1 {

    public static void main(String[] args) {
        print(7);
    }

    private static void print(int n) {
        int[] res = new int[100];
        int i = 0;
        while (n > 0) {
            res[i] = n / 2;
            n = n % 2;
            i++;
        }

        System.out.println(Arrays.toString(res));
    }
}
