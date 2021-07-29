package com.kmozaid.problems.trees;

import java.util.*;

public class Traversals {

    private static int[] toArray(List<Integer> list) {
        int[] inorder = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            inorder[i] = list.get(i);
        }
        return inorder;
    }

    /**
     * Inorder Recursive.
     */
    public static int[] inorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return toArray(list);
    }

    private static void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    /**
     * Inorder Iterative.
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            inorder.add(node.val);
            node = node.right;
        }
        return inorder;
    }

    /**
     * In this traversal, we first create links to Inorder successor and print the data using these links, and finally revert the changes to restore original tree.
     * <p>
     * Initialize current as root
     * While current is not NULL
     * If current does not have left child
     * a) Print current’s data
     * b) Go to the right, i.e., current = current->right
     * Else
     * a) Make current as right child of the rightmost node in current’s left subtree
     * b) Go to this left child, i.e., current = current->left
     * <p>
     * Although the tree is modified through the traversal, it is reverted back to its original shape after the completion.
     */
    public static List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        TreeNode node = root;

        while (node != null) {
            if (node.left != null) {
                // connect threading for node
                TreeNode tmp = node.left;
                while (tmp.right != null && tmp.right != node) {
                    tmp = tmp.right;
                }

                // the threading already exists
                if (tmp.right != null) {
                    tmp.right = null;
                    result.add(node.val);   // Add to result
                    node = node.right;
                } else {
                    // construct the threading
                    tmp.right = node;
                    node = node.left;
                }
            } else {
                result.add(node.val);   // Add to result
                node = node.right;
            }
        }

        return result;
    }

    /**
     * Preorder Recursive.
     */
    public static int[] preorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return toArray(list);
    }


    private static void preorder(TreeNode node, List<Integer> list) {
        if (node == null) return;

        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }

    /**
     * Preorder Iterative.
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return preorder;
    }

    /**
     * Postorder Iterative.
     */
    public static int[] postorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return toArray(list);
    }

    private static void postorder(TreeNode node, List<Integer> list) {
        if (node == null) return;

        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }

    /**
     * Postorder Iterative.
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            postorder.add(node.val);

            if (node.left != null)
                stack.push(node.left);

            if (node.right != null)
                stack.push(node.right);
        }
        Collections.reverse(postorder);
        return postorder;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode left = null;
        TreeNode right = null;
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.left == null && node.right == null) {
                postorder.add(node.val);
            } else {
                if (node.left != null) {
                    left = node.left;
                    node.left = null;
                }
                if (node.right != null) {
                    right = node.right;
                    node.right = null;
                }

                stack.push(node);

                if (right != null) {
                    stack.push(right);
                    right = null;
                }
                if (left != null) {
                    stack.push(left);
                    left = null;
                }
            }
        }
        return postorder;
    }

    /**
     * BFS
     */
    public List<Integer> bfs(TreeNode root) {
        if (root == null)
            return null;

        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode node;

        while (!queue.isEmpty()) {
            node = queue.remove();
            result.add(node.val);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }

        return result;
    }

    /**
     * BFS, Level Order Traversal.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode node;

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                node = queue.poll();
                level.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            res.add(level);
        }
        return res;
    }

    /**
     * Zig Zag Level Order Traversal, Efficient, implemented using only one queue.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean zigzag = false;

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int count = queue.size();   // This is important, It avoids visiting next level nodes.
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();

                if (zigzag) {
                    level.add(0, node.val);
                } else {
                    level.add(node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
            zigzag = !zigzag;   // Toggle level
        }
        return res;
    }

    /**
     * Zig Zag Level Order Traversal, implemented using two stacks.
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> sta = new Stack<>();
        Stack<TreeNode> stb = new Stack<>();
        sta.push(root);

        List<Integer> list;
        TreeNode node;

        while (!sta.isEmpty() || !stb.isEmpty()) {
            list = new ArrayList<>();
            while (!sta.isEmpty()) {
                node = sta.pop();
                list.add(node.val);
                if (node.left != null) {
                    stb.push(node.left);
                }
                if (node.right != null) {
                    stb.push(node.right);
                }
            }
            if (!list.isEmpty())
                res.add(list);

            list = new ArrayList<>();
            while (!stb.isEmpty()) {
                node = stb.pop();
                list.add(node.val);
                if (node.right != null) {
                    sta.push(node.right);
                }
                if (node.left != null) {
                    sta.push(node.left);
                }
            }
            if (!list.isEmpty())
                res.add(list);
        }

        return res;
    }

    /**
     * Zig Zag Level Order Traversal, DFS implementation.
     */
    public List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode curr, List<List<Integer>> res, int level) {
        if (curr == null) return;

        if (res.size() <= level) {
            List<Integer> newLevel = new ArrayList<>();
            res.add(newLevel);
        }

        List<Integer> collection = res.get(level);
        if (level % 2 == 0)
            collection.add(curr.val);
        else
            collection.add(0, curr.val);

        dfs(curr.left, res, level + 1);
        dfs(curr.right, res, level + 1);
    }

    public static void main(String[] args) {
        char ch = 'z';
        System.out.println(ch - 'a');
    }

}
