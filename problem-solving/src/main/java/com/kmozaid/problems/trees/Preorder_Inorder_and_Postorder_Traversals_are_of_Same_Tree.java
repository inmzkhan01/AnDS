package com.kmozaid.problems.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Preorder_Inorder_and_Postorder_Traversals_are_of_Same_Tree {

    public static int solve(ArrayList<Integer> preorder, ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
        return checkTree(preorder, inorder, postorder, inorder.size()) ? 1 : 0;
    }

    private static boolean checkTree(List<Integer> preorder, List<Integer> inorder, List<Integer> postorder, int size) {
        if (size == 0)
            return true;

        if (preorder.get(0).equals(postorder.get(postorder.size() - 1))) {

            int count = -1; // Total number elements in left subtree.
            for (int i = 0; i < inorder.size(); i++) {
                if (inorder.get(i).equals(preorder.get(0))) {
                    count = i;
                    break;
                }
            }

            // Element isn't found in inorder list.
            if (count == -1)
                return false;

            boolean leftRes = checkTree(
                    preorder.subList(1, count + 1),
                    inorder.subList(0, count),
                    postorder.subList(0, count), count);

            boolean rightRes = checkTree(
                    preorder.subList(count + 1, preorder.size()),
                    inorder.subList(count + 1, inorder.size()),
                    postorder.subList(count, postorder.size() - 1), preorder.size() - count - 1);

            return leftRes && rightRes;
        }

        return false;
    }

    /*
    private static boolean checkTree(List<Integer> preorder, List<Integer> inorder, List<Integer> postorder, int size) {
        if (size == 0)
            return true;

        if (size == 1)
            return preorder.get(0).equals(inorder.get(0)) && inorder.get(0).equals(postorder.get(0));

        int count = -1; // Total number elements in left subtree.
        for (int i = 0; i < inorder.size(); i++) {
            if (inorder.get(i).equals(preorder.get(0))) {
                count = i;
                break;
            }
        }

        if (count == -1)
            return false;

        boolean leftRes = checkTree(
                preorder.subList(1, count + 1),
                inorder.subList(0, count),
                postorder.subList(0, count), count);

        boolean rightRes = checkTree(
                preorder.subList(count + 1, preorder.size()),
                inorder.subList(count + 1, inorder.size()),
                postorder.subList(count, postorder.size() - 1), preorder.size() - count - 1);

        return leftRes && rightRes;
    }
    */

    public static void main(String[] args) {
        ArrayList<Integer> preorder = (ArrayList<Integer>) Stream.of(1, 2, 4, 5, 3).collect(Collectors.toList());
        ArrayList<Integer> inorder = (ArrayList<Integer>) Stream.of(4, 2, 5, 1, 3).collect(Collectors.toList());
        ArrayList<Integer> postorder = (ArrayList<Integer>) Stream.of(4, 5, 2, 3, 1).collect(Collectors.toList());

        System.out.println("Are Traversals of same Tree: " + solve(preorder, inorder, postorder));
    }

}
