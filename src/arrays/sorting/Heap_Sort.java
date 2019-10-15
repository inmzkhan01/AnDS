package arrays.sorting;

import java.util.Arrays;

public class Heap_Sort {

    public static int[] sort(int[] arr) {
        int n = arr.length;

        for(int i=n/2-1; i>=0; i--) {
            heapify(arr, n, i);
        }

        for(int i=n-1; i>=0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    public static void heapify(int[] arr, int n, int index) {
        int largest = index;
        int l = 2*index + 1;
        int r = 2*index + 2;

        if(l<n && arr[l] > arr[largest]) {
            largest = l;
        }

        if(r<n && arr[r] > arr[largest]) {
            largest = r;
        }

        if(largest != index) {
            int temp = arr[index];
            arr[index] =  arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int arr[] = { 6,1,4,8,5,7,0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}


