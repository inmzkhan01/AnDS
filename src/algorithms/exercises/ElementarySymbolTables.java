package algorithms.exercises;

import java.util.ArrayList;
import java.util.List;

public class ElementarySymbolTables {

    /**
     * 1. Java autoboxing and equals(). Consider two 𝚍𝚘𝚞𝚋𝚕𝚎 values 𝚊 and 𝚋 and their corresponding 𝙳𝚘𝚞𝚋𝚕𝚎 values 𝚡 and 𝚢.
     * <p>
     * Find values such that (𝚊==𝚋) is 𝚝𝚛𝚞𝚎 but 𝚡.𝚎𝚚𝚞𝚊𝚕𝚜(𝚢) is 𝚏𝚊𝚕𝚜𝚎.
     * Find values such that (𝚊==𝚋) is 𝚏𝚊𝚕𝚜𝚎 but 𝚡.𝚎𝚚𝚞𝚊𝚕𝚜(𝚢) is 𝚝𝚛𝚞𝚎.
     */
    public void exercise1() {

        double a = 0.0;
        double b = -0.0;
        Double x = new Double(a);
        Double y = new Double(b);
        System.out.println(a == b); //true
        System.out.println(x.equals(y)); //false

        a = Double.NaN;
        b = Double.NaN;
        x = new Double(a);
        y = new Double(b);
        System.out.println(a == b); //false
        System.out.println(x.equals(y)); //true
    }

    private class Node {
        private int key;
        private int val;
        private Node left;
        private Node right;
    }

    /**
     * 2. Check if a binary tree is a Binary Search Tree.
     * <p>
     * Given a binary tree where each 𝙽𝚘𝚍𝚎 contains a key, determine whether it is a binary search tree.
     * Use extra space proportional to the height of the tree.
     */
    /* can give min and max value according to your code or can write a function to find min and max value of tree. */
    public boolean exercise2(Node root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node x, int min, int max) {
        /* an empty tree is BST */
        if (x == null) return true;

        /* false if this node violates the min/max constraints */
        if (x.key < min || x.key > max)
            return false;

        /* otherwise check the subtrees recursively tightening the min/max constraints */
        // Allow only distinct values (plus one and minus one are important to rule out BST with duplicate)
        return isBST(x.left, min, x.key - 1) && isBST(x.right, x.key + 1, max);
    }

    /**
     * 3. Inorder traversal with constant extra space.
     * Design an algorithm to perform an inorder traversal of a binary search tree using only a constant amount of extra space
     */
    public Iterable<Integer> inorder(Node root) {
        List<Integer> keys = new ArrayList<>();
        Node x = root;

        while(x != null) {
            if(x.left == null) {
                keys.add(x.key);
                x = x.right;
            } else {
                // Find the inorder predecessor of current.
                Node pre = x.left;
                while(pre.right != null && pre.right != x) {
                    pre = pre.right;
                }

                if(pre.right == null) {
                    // Make current as the right child of its inorder predecessor
                    pre.right = x;
                    x = x.left; 
                } else {
                    // Revert the changes made in the 'if' part to restore the original tree
                    pre.right = null;
                    keys.add(x.key); // Add parent.
                    x = x.right;
                }

            }
        }

        return keys;
    }

    /**
     * 4. Create a symbol table with key being user and value being another symbol table with website as key and visit counts as value.
     */

    public static void main(String[] args) {
        ElementarySymbolTables exercises = new ElementarySymbolTables();
        exercises.exercise1();
    }
}
