package com.mozaid.problems.trees;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSum {

    public static int[] findPair(TreeNode root, int sum) {
        Set<Integer> set = new HashSet<>();
        return inOrder(root, sum, set);
    }

    private static int[] inOrder(TreeNode node, int sum, Set<Integer> set) {
        if (node == null)
            return null;

        int[] pair = inOrder(node.left, sum, set);

        if (pair != null)
            return pair;

        if (set.contains(sum - node.val))
            return new int[]{sum - node.val, node.val};

        set.add(node.val);

        return inOrder(node.right, sum, set);
    }

    public static void main(String[] args) {

        int[] pair = findPair(TreeNode.binarySearchTree(), 270);
        System.out.println("Pair: " + Arrays.toString(pair));
    }
}
