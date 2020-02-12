package algorithms.trees;

import java.util.Arrays;

class SegmentTree {

    private int[] tree; // The array that stores segment tree nodes
    private int[] arr;  // Store input array.
    private int n;      // Size of the input array.

    SegmentTree(int a[]) {
        n = a.length;

        //The max size of this array is about 2 * 2 ^ log2(n) + 1
        int maxSize = (int) (2 * Math.pow(2.0, Math.floor((Math.log((double) n) / Math.log(2.0)) + 1)));
        tree = new int[maxSize]; // Memory allocation
        arr = Arrays.copyOf(a, n);

        build(0, n - 1, 0);
    }

    // A recursive function that constructs Segment Tree for array[ss..se].
    // si is index of current node in segment tree
    int build(int ss, int se, int si) {
        // If there is one element in array, store it in current node of segment tree and return
        if (ss == se) {
            tree[si] = arr[ss];
            return tree[si];
        }

        // If there are more than one elements, then recur for left and right subtrees and store the sum of values in this node.
        int mid = ss + (se - ss) / 2;

        tree[si] = build(ss, mid, left(si)) + build((mid + 1), se, right(si));
        return tree[si];
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    public int getSum(int qs, int qe) {
        return getSum(0, n - 1, qs, qe, 0);
    }

    private int getSum(int ss, int se, int qs, int qe, int si) {
        // If segment of this node is a part of given range, then return the sum of the segment.
        if (qs <= ss && qe >= se)
            return tree[si];

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = ss + (se - ss) / 2;
        return getSum(ss, mid, qs, qe, left(si)) + getSum(mid + 1, se, qs, qe, right(si));
    }

    public void update(int i, int newVal) {
        // Get the difference between new value and old value
        int diff = newVal - arr[i];

        // Update the value in array
        arr[i] = newVal;

        // Update the values of nodes in segment tree
        update(0, n - 1, i, diff, 0);
    }


    private void update(int ss, int se, int i, int diff, int si) {
        // Base Case: If the input index lies outside the range of this segment
        if (i < ss || i > se)
            return;

        // If the input index is in range of this node, then update the value of the node and its children
        tree[si] = tree[si] + diff;

        if (se != ss) {
            int mid = ss + (se - ss) / 2;
            update(ss, mid, i, diff, left(si));
            update((mid + 1), se, i, diff, right(si));
        }
    }

    public static void main(String args[]) {
        int arr[] = {1, 3, 5, 7, 9, 11};

        // Build segment tree from given array
        SegmentTree tree = new SegmentTree(arr);

        // Print sum of values in array from index 1 to 3
        System.out.println("Sum of values in given range = " + tree.getSum(1, 3));

        // Update: set arr[1] = 10 and update corresponding segment tree nodes
        tree.update(1, 10);

        // Find sum after the value is updated
        System.out.println("Updated sum of values in given range = " + tree.getSum(1, 3));
    }
}
