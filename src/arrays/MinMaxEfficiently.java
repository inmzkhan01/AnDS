package arrays;

import java.util.*;

public class MinMaxEfficiently {

    //Total comparisons require 2N-3
    static class NonEfficient {

        static void printMinMax(int[] nums) {
            int min, max;

            if(nums[0] < nums[1]) {
                min = nums[0];
                max = nums[1];
            } else {
                min = nums[1];
                max = nums[0];
            }

            int countComparison = 1;

            for (int i = 2; i < nums.length; i++) {
                if (nums[i] < min) {
                    min = nums[i];
                }
                if (nums[i] > max) {
                    max = nums[i];
                }
                countComparison += 2;
            }
            System.out.println("\nArray: " + Arrays.toString(nums));
            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
            System.out.println("Total Comparison needed: " + countComparison);
        }
    }


    static class Efficient {

        static void printMinMax(int[] nums) {
            int N = nums.length;

            int countComparison = 0;

            List<Integer> lowers = new ArrayList<>();
            List<Integer> highers = new ArrayList<>();

            for (int i = 1; i < N; i += 2) {
                countComparison++;
                if (nums[i] > nums[i - 1]) {
                    highers.add(nums[i]);
                    lowers.add(nums[i - 1]);
                } else {
                    highers.add(nums[i - 1]);
                    lowers.add(nums[i]);
                }
            }

            if(N % 2 == 1) {
                //countComparison += 2;
                if (nums[N-1] > nums[N-2]) {
                    highers.add(nums[N-1]);
                } else {
                    lowers.add(nums[N-1]);
                }
            }

            int min = Integer.MAX_VALUE;
            for (int num : lowers) {
                countComparison++;
                if (num < min) {
                    min = num;
                }
            }

            int max = Integer.MIN_VALUE;
            for (int num : highers) {
                countComparison++;
                if (num > max) {
                    max = num;
                }
            }

            System.out.println("\nArray: " + Arrays.toString(nums));
            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
            System.out.println("Total Comparison needed: " + countComparison);
        }
    }


    public static void main(String[] args) {
        int[] increasingNums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        NonEfficient.printMinMax(increasingNums);

        int[] decreasingNums = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        NonEfficient.printMinMax(decreasingNums);

        Efficient.printMinMax(increasingNums);
        Efficient.printMinMax(decreasingNums);
    }
}
