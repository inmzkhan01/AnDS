package com.mozaid.problems.binarysearch;

/**
 * https://leetcode.com/problems/koko-eating-bananas/
 */
public class Koko_Eating_Bananas {

    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1;
        int hi = 1_000_000_000;

        while (lo < hi) {
            // Number of bananas to eat per hour
            int mid = lo + (hi - lo) / 2;

            int hours = 0;
            for (int p : piles) {
                if (p <= mid)
                    hours += 1;
                else {
                    hours += p / mid;
                    if (p % mid != 0)
                        hours += 1;
                }
            }

            if (hours > H)
                lo = mid + 1;
            else
                hi = mid;
        }

        return lo;
    }

    public static void main(String[] args) {
        Koko_Eating_Bananas koko = new Koko_Eating_Bananas();

        System.out.println("For ([3, 6, 7, 11], 8)       : " + koko.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.println("For ([30, 11, 23, 4, 20], 5) : " + koko.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        System.out.println("For ([30, 11, 23, 4, 20], 6) : " + koko.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));

        int[] a = {332484035, 524908576, 855865114, 632922376, 222257295, 690155293,
                112677673, 679580077, 337406589, 290818316, 877337160, 901728858,
                679284947, 688210097, 692137887, 718203285, 629455728, 941802184};

        System.out.println(koko.minEatingSpeed(a, 823855818));

        System.out.println(koko.minEatingSpeed(new int[]{312884470}, 968709470));

    }
}
