package sortings;

abstract class Sort {

    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected static void exch(Comparable[] arr, int v, int w) {
        Comparable temp = arr[v];
        arr[v] = arr[w];
        arr[w] = temp;
    }

    protected static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++)
            if (less(a[i + 1], a[i]))
                return false;
        return true;
    }
}