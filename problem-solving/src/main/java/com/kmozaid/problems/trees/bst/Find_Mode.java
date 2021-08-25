package com.kmozaid.problems.trees.bst;

import com.kmozaid.problems.trees.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/
 */
public class Find_Mode {

    TreeNode prev;
    int count = 1;
    int max = 0;

    public int[] findMode(TreeNode root) {
        if (null == root) return new int[0];

        prev = null;

        List<Integer> modes = new ArrayList<>();

        inOrder(root, modes);

        int[] res = new int[modes.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = modes.get(i);
        }
        return res;
    }

    private void inOrder(TreeNode node, List<Integer> modes) {

        if (null == node)
            return;

        inOrder(node.left, modes);

        if (null != prev) {
            if (node.val == prev.val) {
                count++;
            } else {
                count = 1;
            }
        }
        if (count > max) {
            max = count;
            modes.clear();
            modes.add(node.val);
        } else if (count == max) {
            modes.add(node.val);
        }

        prev = node;

        inOrder(node.right, modes);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);

        Find_Mode findModeObj = new Find_Mode();
        int[] modes = findModeObj.findMode(root);
        System.out.println(Arrays.toString(modes));
    }
}
