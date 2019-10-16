package binarysearch;

import java.util.Arrays;

// https://leetcode.com/problems/russian-doll-envelopes/
public class Russian_Doll_Envelops {

    static int maxEnvelopes(int[][] envelopes) {

        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int[] sortedSubsequence = new int[envelopes.length];
        int maxEnvelopes = 0;

        for (int i = 0; i < envelopes.length; i++) {
            int num = envelopes[i][1];
            int start = 0;
            int end = maxEnvelopes;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (sortedSubsequence[mid] < num) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            sortedSubsequence[start] = num;
            if (start == maxEnvelopes) {
                maxEnvelopes += 1;
            }
        }
        return maxEnvelopes;
    }

    public static void main(String[] args) {
        System.out.println(maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
    }

}