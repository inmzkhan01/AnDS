package arrays;

import java.util.Arrays;

public class Rotate_Array_By_D_Elements {

    public static int[] rotate(int[] arr, int d) {
        int n = arr.length;

        for(int i=0; i<d; i++) {
            int x = arr[i];
            arr[i] = arr[n-d+i];
            arr[n-d+i] = x;
        }

       /*for(int i=0,j=d; i<d; i++,j++) {
            int x = arr[i];
            arr[i] = arr[j];
            arr[j] = x;
        }*/

        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22};

        System.out.println(Arrays.toString(rotate(arr,4)));
    }
}
