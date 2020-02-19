package com.mozaid.algorithms.sortings;

public class QuickSelect extends Sort {

    public static Comparable select(Comparable[] a, int k) {
        Shuffle.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }
        return a[k];
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        //hi is pivot
        int i = lo - 1, j = hi;

        while (true) {
            while (less(a[++i], a[hi]))
                if (i == hi) break;

            while (less(a[hi], a[--j]))
                if (j == lo) break;

            if (i >= j) break;

            exch(a, i, j);
        }

        exch(a, j, hi);

        return j;
    }

    public static void main(String[] args) {
        Integer[] nums = {6, 3, 7, 1, 9, 2, 8, 5};
        Integer item = (Integer) QuickSelect.select(nums, 4);
        System.out.println(item);
    }
}
