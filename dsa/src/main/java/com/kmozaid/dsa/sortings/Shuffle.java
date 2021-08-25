package com.kmozaid.dsa.sortings;

import java.util.Arrays;
import java.util.Random;

public class Shuffle extends Sort {

    public static void shuffle(Comparable[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int r = random.nextInt(i + 1);
            exch(arr, i, r);
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        Shuffle.shuffle(nums);
        System.out.println(Arrays.toString(nums));
    }
}
