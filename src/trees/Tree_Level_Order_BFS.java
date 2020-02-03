package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree_Level_Order_BFS {

    public List<TreeNode> traverse(TreeNode tree) {
        if (tree == null) return null;
        List<TreeNode> result = new ArrayList<>();
        Queue<TreeNode> toVisit = new LinkedList<>();
        toVisit.add(tree);
        while (!toVisit.isEmpty()) {
            TreeNode curr = toVisit.remove();
            result.add(curr);
            if (curr.left != null)
                toVisit.add(curr.left);
            if (curr.right != null)
                toVisit.add(curr.right);
        }

        return result;
    }
}
