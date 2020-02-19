package com.mozaid.problems.greedy;

// https://leetcode.com/problems/gas-station/
public class Gas_Station {

    static int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int gasInTank = 0;
        int diff = 0;

        for (int i = 0; i < gas.length; i++) {
            gasInTank = gasInTank + gas[i] - cost[i];
            if (gasInTank < 0) {
                diff = diff + gasInTank;
                start = i + 1;
                gasInTank = 0;
            }
        }

        return gasInTank+diff >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));

        System.out.println(canCompleteCircuit(new int[]{5, 1, 2, 3, 4}, new int[]{4, 4, 1, 5, 1}));

    }
}