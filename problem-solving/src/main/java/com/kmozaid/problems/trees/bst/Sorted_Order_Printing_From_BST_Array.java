package com.kmozaid.problems.trees.bst;

/**
 * https://www.geeksforgeeks.org/sorted-order-printing-of-an-array-that-represents-a-bst/
 */
public class Sorted_Order_Printing_From_BST_Array {

    public void printSorted(int[] a) {
        printSorted(a, 0, a.length - 1);
    }

    private void printSorted(int[] a, int start, int end) {
        if (start > end) return;

        printSorted(a, 2 * start + 1, end);
        System.out.print(a[start] + " ");
        printSorted(a, 2 * start + 2, end);
    }

    public static void main(String[] args) {
        Sorted_Order_Printing_From_BST_Array sortedOrder = new Sorted_Order_Printing_From_BST_Array();
        sortedOrder.printSorted(new int[]{4, 2, 5, 1, 3});
    }

}
