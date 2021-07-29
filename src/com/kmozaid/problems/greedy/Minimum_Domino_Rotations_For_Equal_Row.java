package com.kmozaid.problems.greedy;

// https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
public class Minimum_Domino_Rotations_For_Equal_Row {

    static int minDominoRotations(int[] A, int[] B) {
        if (null == A || null == B || A.length != B.length || 1 == A.length) return 0;

        int[] aCount = new int[7];
        int[] bCount = new int[7];
        int[] commonCount = new int[7];

        for (int i = 0; i < A.length; i++) {
            aCount[A[i]]++;
            bCount[B[i]]++;
            if (A[i] == B[i]) {
                commonCount[A[i]]++;
            }
        }

        for (int i = 1; i < 7; i++) {
            if (aCount[i] + bCount[i] - commonCount[i] == A.length) {
                return Math.min(A.length - aCount[i], A.length - bCount[i]);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] A = {2, 1, 2, 4, 2, 2};
        int[] B = {5, 2, 6, 2, 3, 2};
        System.out.println(minDominoRotations(A, B));

        A = new int[]{2, 3, 2, 2, 3, 2};
        B = new int[]{3, 2, 3, 3, 2, 2};
        System.out.println(minDominoRotations(A, B));

        A = new int[]{3, 5, 1, 2, 3};
        B = new int[]{3, 6, 3, 3, 4};
        System.out.println(minDominoRotations(A, B));

        A = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        B = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(minDominoRotations(A, B));

        A = new int[]{1, 2, 3, 4, 6};
        B = new int[]{6, 6, 6, 6, 5};
        System.out.println(minDominoRotations(A, B));

        A = new int[]{1, 1, 2, 1, 1, 1, 1, 1, 1};
        B = new int[]{1, 2, 1, 1, 2, 1, 1, 1, 1};
        System.out.println(minDominoRotations(A, B));

        A = new int[]{1, 2, 1, 1, 1, 2, 2, 2};
        B = new int[]{2, 1, 2, 2, 2, 2, 2, 2};
        System.out.println(minDominoRotations(A, B));

        A = new int[]{2, 2, 2, 1, 1, 2, 3, 4};
        B = new int[]{1, 1, 1, 2, 2, 2, 2, 2};
        System.out.println(minDominoRotations(A, B));

    }

}
