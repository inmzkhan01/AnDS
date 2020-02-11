package trees.bst;

import trees.TreeNode;

public class Next_Smaller_Number_Predecessor {

    class SimpleSolution {
        public TreeNode getSuccessor(TreeNode root, int data) {
            if (root == null) return null;

            TreeNode temp = null;
            while (root != null) {
                if (root.val < data) {
                    temp = root;
                    root = root.right;
                } else
                    root = root.left;
            }
            return temp;
        }
    }
}
