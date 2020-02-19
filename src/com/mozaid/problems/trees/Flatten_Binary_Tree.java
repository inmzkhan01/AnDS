package com.mozaid.problems.trees;

import java.util.Stack;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class Flatten_Binary_Tree {

    /* This is best */
    class Simple {
        TreeNode prev;

        public TreeNode flatten(TreeNode root) {
            prev = null;
            dfs(root);
            return root;
        }

        private void dfs(TreeNode node) {
            if (node == null)
                return;

            dfs(node.right);
            dfs(node.left);
            node.right = prev;
            node.left = null;
            prev = node;
        }
    }

    /* This is same as above but not using global variable */
    class Editorial {

        public TreeNode flatten(TreeNode root) {
            return flatten(root, null);
        }

        private TreeNode flatten(TreeNode node, TreeNode tail) {
            if (node == null)
                return tail;

            tail = flatten(node.right, tail);
            node.right = flatten(node.left, tail);
            node.left = null;
            return node;
        }
    }

    /* This is amazing */
    class Iterative {
        public void flatten(TreeNode root) {
            if (root == null) return;

            Stack<TreeNode> stk = new Stack<>();
            stk.push(root);
            while (!stk.isEmpty()) {
                TreeNode curr = stk.pop();
                if (curr.right != null)
                    stk.push(curr.right);
                if (curr.left != null)
                    stk.push(curr.left);
                if (!stk.isEmpty())
                    curr.right = stk.peek();

                curr.left = null;
            }
        }
    }

    /* This is not in-place.*/
    class Lightweight {

        TreeNode head = new TreeNode(0);
        TreeNode cur = head;

        public TreeNode flatten(TreeNode a) {
            //preorder traversal
            preorder(a);
            return head.right;
        }

        public void preorder(TreeNode a) {
            if (a == null) {
                return;
            }
            cur.right = new TreeNode(a.val);
            cur = cur.right;
            preorder(a.left);
            preorder(a.right);
        }
    }



    class SimpleII {

        public void flatten(TreeNode node) {
            // base condition - return if Person is NULL or if it is a leaf Person
            if (node == null || (node.left == null && node.right == null)) {
                return;
            }

            // if root->left exists then we have to make it root->right
            if (node.left != null) {

                // move left recursively
                flatten(node.left);

                // store the Person Person->right
                TreeNode tmpRight = node.right;
                node.right = node.left;
                node.left = null;

                // find the position to insert the stored value
                TreeNode t = node.right;
                while (t.right != null) {
                    t = t.right;
                }

                // insert the stored value
                t.right = tmpRight;
            }

            // now call the same function for Person->right
            flatten(node.right);
        }
    }



    class IterativeII {

        public TreeNode flatten(TreeNode node) {
            Stack<TreeNode> st = new Stack<>();
            TreeNode ans = node;
            while (node != null || !st.isEmpty()) {
                // Save the right Person.
                if (node.right != null) {
                    st.push(node.right);
                }
                // set right to left and left to null.
                node.right = node.left;
                node.left = null;
                // If left child was null then right is null
                if (node.right == null && !st.isEmpty()) {
                    node.right = st.pop();
                }
                // Iterate
                node = node.right;
            }
            return ans;
        }
    }


}
