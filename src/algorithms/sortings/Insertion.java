package sortings;

import java.util.Arrays;

public class Insertion extends Sort {

    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++)
            for (int j = i; j > 0; j--)
                if (less(arr[j], arr[j - 1]))
                    exch(arr, j, j - 1);
                else break;
    }

    public static void main(String[] args) {
        Integer[] nums = {6, 3, 7, 1, 8, 9, 2, 5};
        Insertion.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

}