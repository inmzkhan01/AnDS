package com.kmozaid.problems.arrays;

import java.util.Arrays;
import java.util.Comparator;

//https://leetcode.com/problems/k-closest-points-to-origin/
public class K_Closest_Point_to_Origin {

    static int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                return (point1[0] * point1[0] + point1[1] * point1[1]) -
                        (point2[0] * point2[0] + point2[1] * point2[1]);
            }
        });

        int[][] output = new int[K][2];

        for (int i = 0; i < K; i++) {
            output[i] = points[i];
        }
        return output;
    }

    public static void main(String[] args) {
        Arrays.stream(kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2))
                .forEach(b -> System.out.println(Arrays.toString(b)));
    }


}
