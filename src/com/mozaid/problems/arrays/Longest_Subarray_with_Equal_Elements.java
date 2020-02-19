package com.mozaid.problems.arrays;

public class Longest_Subarray_with_Equal_Elements {

    public static int findSubarrayLength(int[] a) {
        int maxLen = 0;
        int len = 1;
        int s = 0, start = 0, end = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1]) {
                len += 1;
                if (len > maxLen) {
                    maxLen = len;
                    start = s;
                    end = i;
                }
            } else {
                len = 1;
                s = i;
            }
        }
        System.out.print("Start: " + start + " End: " + end + "\n");
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(findSubarrayLength(new int[]{1, 2, 2, 6, 6, 6, 6, 6, 4, 4, 3, 3, 3}));
    }

}
