package com.mozaid.problems.trees.bst;

import com.mozaid.problems.trees.TreeNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSum {

    /**
     * This modify the given tree.
     */
    static class Method1 {

        private static TreeNode head;
        private static TreeNode tail;

        public static int[] findPair(TreeNode root, int sum) {
            if (root == null)
                return new int[0];

            head = null;
            tail = null;
            convertToDLL(root);

            while (head != tail) {
                int pairSum = head.val + tail.val;

                if (pairSum == sum)
                    return new int[]{head.val, tail.val};

                if (pairSum < sum)
                    head = head.right;
                else
                    tail = tail.left;
            }
            return new int[0];
        }

        private static void convertToDLL(TreeNode node) {
            if (node == null)
                return;

            convertToDLL(node.left);

            if (head == null)
                head = node;

            if (tail != null)
                tail.right = node;

            node.left = tail;

            tail = node;

            convertToDLL(node.right);
        }
    }

    /**
     * This is best solution, takes O(n) time and O(n) space.
     */
    static class Editorial {

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
    }


    public static void main(String[] args) {
        int[] pair = Method1.findPair(TreeNode.binarySearchTree(), 270);
        System.out.println("Pair: " + Arrays.toString(pair));

        pair = Editorial.findPair(TreeNode.binarySearchTree(), 270);
        System.out.println("Pair: " + Arrays.toString(pair));
    }
}
