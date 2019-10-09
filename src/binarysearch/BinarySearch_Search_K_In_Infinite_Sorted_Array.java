package binarysearch;


public class BinarySearch_Search_K_In_Infinite_Sorted_Array {

    //Infinite Array Reader.
    static class ArrayReader {
        static int[] arr = {4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};

        static int get(int index) {
            if (index > arr.length) {
                return Integer.MAX_VALUE;
            }
            return arr[index];
        }
    }

    static int search(int key) {

        int start = 0;
        int end = 1;

        while (ArrayReader.get(end) < key) {
            int newStart = end + 1;
            end = end + (end - start + 1) * 2;
            start = newStart;
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key < ArrayReader.get(mid)) {
                end = mid - 1;
            } else if (key > ArrayReader.get(mid)) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Index: " + search(16));
    }

}