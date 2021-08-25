package com.kmozaid.problems.trees;

import java.util.ArrayList;
import java.util.List;

public class Lowest_Common_Ancestor {

    /**
     * This assume that both given nodes are present in tree.
     */
    static class OnePass {

        public static TreeNode LCA(TreeNode root, int a, int b) {
            return findLCA(root, a, b);
        }

        private static TreeNode findLCA(TreeNode node, int a, int b) {
            if (node == null)
                return null;
            if (node.val == a || node.val == b)
                return node;
            TreeNode L = findLCA(node.left, a, b);
            TreeNode R = findLCA(node.right, a, b);
            if (L != null && R != null) return node; // If a, b are on both sides
            return L != null ? L : R; // either one of a, b is on one side OR a, b is not in L&R subtrees
        }
    }

    /**
     * Best, this doesn't assume that both given nodes are present in tree.
     */
    static class OnePassII {

        public static TreeNode LCA(TreeNode root, int a, int b) {
            boolean[] found = new boolean[2];
            TreeNode node = findLCA(root, a, b, found);
            if (found[0] && found[1])
                return node;
            return null;
        }

        private static TreeNode findLCA(TreeNode node, int a, int b, boolean[] found) {
            if (node == null)
                return null;

            TreeNode temp = null;

            if (node.val == a) {
                found[0] = true;
                temp = node;
            }

            if (node.val == b) {
                found[1] = true;
                temp = node;
            }

            if (temp != null)
                return node;

            TreeNode L = findLCA(node.left, a, b, found);
            TreeNode R = findLCA(node.right, a, b, found);
            if (L != null && R != null) return node; // If a, b are on both sides
            return L != null ? L : R; // either one of a, b is on one side OR a, b is not in L&R subtrees
        }
    }

    /**
     * This uses extra space with two pass.
     */
    static class Simple {
        public static TreeNode LCA(TreeNode root, int a, int b) {
            List<TreeNode> pathToA = new ArrayList<>();
            List<TreeNode> pathToB = new ArrayList<>();

            if (!findPath(root, a, pathToA) || !findPath(root, b, pathToB))
                return null;

            int i;
            for (i = 0; i < pathToA.size() && i < pathToB.size(); i++) {
                if (pathToA.get(i).val != pathToB.get(i).val) {
                    break;
                }
            }

            return pathToA.get(i - 1);
        }

        private static boolean findPath(TreeNode node, int n, List<TreeNode> path) {
            if (node == null)
                return false;

            if (node.val == n)
                return true;

            path.add(node);

            if (findPath(node.left, n, path))
                return true;
            if (findPath(node.right, n, path))
                return true;

            path.remove(path.size() - 1);

            return false;
        }
    }

    /**
     * This uses extra space with one pass.
     */
    static class SimpleII {

        public static TreeNode LCA(TreeNode root, int a, int b) {
            List<TreeNode> pathToA = new ArrayList<>();
            List<TreeNode> pathToB = new ArrayList<>();
            boolean[] found = new boolean[2];
            findPath(root, a, b, pathToA, pathToB, found);

            if (found[0] && found[1]) {
                int i;
                for (i = 0; i < pathToA.size() && i < pathToB.size(); i++) {
                    if (pathToA.get(i).val != pathToB.get(i).val) {
                        break;
                    }
                }

                return i > 0 ? pathToA.get(i - 1) : null;
            }
            return null;
        }

        private static void findPath(TreeNode node, int a, int b, List<TreeNode> pathA, List<TreeNode> pathB, boolean[] found) {
            if (node == null || (found[0] && found[1]))
                return;


            if (!found[0]) {
                pathA.add(node);
                if (node.val == a) {
                    found[0] = true;
                }
            }

            if (!found[1]) {
                pathB.add(node);
                if (node.val == b) {
                    found[1] = true;
                }
            }

            if (!found[0] || !found[1]) {
                findPath(node.left, a, b, pathA, pathB, found);
                findPath(node.right, a, b, pathA, pathB, found);

                if (!found[0])
                    pathA.remove(pathA.size() - 1);

                if (!found[1])
                    pathB.remove(pathB.size() - 1);
            }

        }
    }


    public static void main(String[] args) {
        TreeNode root = TreeNode.binarySearchTree();
        System.out.println(OnePass.LCA(root, 160, 200));
        System.out.println(OnePassII.LCA(root, 160, 200));
        System.out.println(Simple.LCA(root, 160, 200));
        System.out.println(SimpleII.LCA(root, 160, 200));
    }
}
