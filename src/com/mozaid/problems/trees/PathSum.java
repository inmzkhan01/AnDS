package com.mozaid.problems.trees;

import java.util.ArrayList;
import java.util.List;

public class PathSum {

    /**
     * Has path sum ?
     */
    public int hasPathSum(TreeNode node, int B) {
        return hasPathSum(node, B, 0) ? 1 : 0;
    }

    private boolean hasPathSum(TreeNode node, int B, int sum) {
        if (node == null)
            return false;

        sum = sum + node.val;

        if (sum == B && node.left == null && node.right == null)
            return true;

        return hasPathSum(node.left, B, sum) || hasPathSum(node.right, B, sum);
    }

    class Editorial {
        public int hasPathSum(TreeNode node, int B) {
            if (node == null)
                return 0;

            if (node.val == B && node.left == null && node.right == null)
                return 1;

            return hasPathSum(node.left, B - node.val) | hasPathSum(node.right, B - node.val);
        }
    }

    /**
     * Find all the paths with given sum.
     */
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode node, int B) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        pathSum(node, B, 0, new ArrayList<>(), result);
        return result;
    }

    private void pathSum(TreeNode node, int B, int sum, List<Integer> list, List<ArrayList<Integer>> result) {
        if (node == null)
            return;

        sum = sum + node.val;

        list.add(node.val);

        if (node.left == null && node.right == null && sum == B)
            result.add(new ArrayList<>(list));

        pathSum(node.left, B, sum, list, result);
        pathSum(node.right, B, sum, list, result);
        list.remove(list.size() - 1);
    }

}
