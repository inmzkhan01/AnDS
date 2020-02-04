package algorithms.sortings;

import java.util.Arrays;

public class Quick extends Sort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        //lo is pivot
        int pivot = lo;

        int i = lo, j = hi + 1;

        while (true) {
            while (less(a[++i], a[pivot]))
                if (i == hi) break;

            while (less(a[pivot], a[--j]))
                if (j == lo) break;

            if (i >= j) break;

            exch(a, i, j);
        }

        exch(a, pivot, j);

        return j;
    }

    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        Shuffle.shuffle(nums);
        Quick.sort(nums);
        assert isSorted(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}