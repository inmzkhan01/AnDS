package com.kmozaid.dsa.trees;

public class IntervalTree {

    public static class Interval {
        int low;
        int high;

        public Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    private class ITreeNode {
        Interval ival;
        int max;
        ITreeNode left;
        ITreeNode right;

        public ITreeNode(Interval i) {
            ival = i;
            max = i.high;
        }
    }

    private ITreeNode root;

    public void insert(Interval i) {
        root = insert(root, i);
    }

    private ITreeNode insert(ITreeNode node, Interval i) {
        // Base case: tree is empty, new node becomes root
        if (node == null)
            return new ITreeNode(i);

        // If interval low smaller than node's low, then new interval goes to left subtree
        if (i.low < node.ival.low)
            node.left = insert(node.left, i);
        else
            node.right = insert(node.right, i);

        // Update the max value of this ancestor if needed
        if (node.max < i.high)
            node.max = i.high;

        return node;
    }

    // The main function that searches a given interval i in a given Interval tree.
    public Interval overlapSearch(Interval i) {
        return overlapSearch(root, i);
    }

    private Interval overlapSearch(ITreeNode node, Interval i) {
        // Base Case, tree is empty
        if (node == null) return null;

        // If given interval overlaps with root
        if (i.high >= node.ival.low && i.low <= node.ival.high)
            return node.ival;

        // If left child of root is present and max of left child is greater than or equal to given interval
        // then i may overlap with an interval is left subtree
        if (node.left != null && node.left.max >= i.low)
            return overlapSearch(node.left, i);

        // Else interval can only overlap with right subtree
        return overlapSearch(node.right, i);
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(ITreeNode node) {
        if (node == null) return;

        inorder(node.left);
        System.out.println("[" + node.ival.low + ", " + node.ival.high + "]" + " max = " + node.max);
        inorder(node.right);
    }

    public static void main(String[] args) {
        int[][] ivals = {{15, 20}, {10, 30}, {17, 19}, {5, 20}, {12, 15}, {30, 40}};

        IntervalTree iTree = new IntervalTree();

        int n = ivals.length;
        for (int i = 0; i < n; i++)
            iTree.insert(new Interval(ivals[i][0], ivals[i][1]));

        System.out.println("Inorder traversal of constructed Interval tree is");
        iTree.inorder();

        Interval x = new Interval(6, 7);
        System.out.println("Searching for interval [" + x.low + "," + x.high + "]");
        Interval res = iTree.overlapSearch(x);

        if (res == null)
            System.out.println("No Overlapping Interval");
        else
            System.out.println("Overlaps with [" + res.low + ", " + res.high + "]");

        x = new Interval(2, 4);
        System.out.println("Searching for interval [" + x.low + "," + x.high + "]");
        res = iTree.overlapSearch(x);

        if (res == null)
            System.out.println("No Overlapping Interval");
        else
            System.out.println("Overlaps with [" + res.low + ", " + res.high + "]");
    }
}
