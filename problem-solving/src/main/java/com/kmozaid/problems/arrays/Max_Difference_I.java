package com.kmozaid.problems.arrays;

/**
 * https://www.geeksforgeeks.org/maximum-difference-between-two-elements/
 * <p>
 * Compute max (A[j]-A[i]) where 0<=i<j<=n-1
 */
public class Max_Difference_I {

    public static int maxDiff_brute_force(int[] a) {
        int maxDiff = Integer.MIN_VALUE;

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int diff = a[j] - a[i];
                if (diff > maxDiff)
                    maxDiff = diff;
            }
        }
        return maxDiff;
    }

    public static int maxDiff_by_using_maximum_subarray_routine(int[] a) {

        int[] diff = new int[a.length - 1];

        for (int i = 0; i < a.length - 1; i++) {
            diff[i] = a[i + 1] - a[i];
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int num : diff) {
            sum += num;

            if (sum > max)
                max = sum;

            if (sum < 0)
                sum = 0;
        }

        return max;
    }

    public static int maxDiff(int[] a) {
        int maxDiff = Integer.MIN_VALUE;
        int min = a[0];

        for (int i = 1; i < a.length; i++) {
            int diff = a[i] - min;
            if (diff > maxDiff)
                maxDiff = diff;

            if (a[i] < min)
                min = a[i];
        }

        return maxDiff;
    }

    /**
     * A robot needs to travel along a path that includes several ascents and descents.
     * When it goes up, it uses its battery to power the motor and when it descends, it
     * recovers the energy which is stored in the battery. The battery recharging process
     * is ideal: on descending, every Joule of gravitational potential energy converts to a
     * Joule of electrical energy which is stored in the battery. The battery has a limited
     * capacity and once it reaches this capacity, the energy generated in descending is lost
     * <p>
     * Design an algorithm that takes a sequence of n three-dimensional coordinates to be traversed,
     * and returns the minimum battery capacity needed to complete the journey.
     * The robot begins with a fully charged battery.
     */
    public static int findBatteryCapacity(int[] h) {
        int capacity = 0;
        int minHeight = Integer.MAX_VALUE;

        for (int height : h) {
            capacity = Math.max(capacity, height - minHeight);
            minHeight = Math.min(minHeight, height);
        }

        return capacity;
    }

    public static void main(String[] args) {
        System.out.println(maxDiff_brute_force(new int[]{10, 5, 6, 2, 7, 12, 11}));
        System.out.println(maxDiff_by_using_maximum_subarray_routine(new int[]{10, 5, 6, 2, 7, 12, 11}));
        System.out.println(maxDiff(new int[]{10, 5, 6, 2, 7, 12, 11}));
        System.out.println(findBatteryCapacity(new int[]{10, 5, 6, 2, 7, 12, 11}));
    }

}
