package com.kmozaid.problems.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Four_Sum_Problem {

    /**
     * 4-SUM. Given an array a[] of n integers, the 4-SUM problem is to determine
     * if there exist distinct indices i, j, k, and l such that a[i] + a[j] = a[k] + a[l].
     * Design an algorithm for the 4-SUM problem that takes time proportional to n^2.
     */

    public static boolean fourSum(int[] a) {
        int n = a.length;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = a[i] + a[j];
                for (int k = 0; k < n; k++) {
                    if (set.contains(sum))
                        return true;
                    else
                        set.add(sum);
                }
            }
        }
        return false;
    }

    // Works in n^3
    public static int[][] fourSum2(int[] a) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                int sum = a[i] + a[j];
                Map<Integer, Integer> map = new HashMap<>();
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j)
                        continue;
                    if (map.containsKey(sum - a[k]))
                        return new int[][]{new int[]{i, j}, new int[]{map.get(sum - a[k]), k}};
                    else
                        map.put(a[k], k);
                }
            }
        }
        return new int[0][0];
    }
}
