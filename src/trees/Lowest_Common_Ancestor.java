package trees;

import java.util.ArrayList;
import java.util.List;

public class Lowest_Common_Ancestor {

    public TreeNode LCA(TreeNode root, int a, int b) {
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

    private boolean findPath(TreeNode node, int n, List<TreeNode> path) {
        if (node == null) return false;

        if (node.val == n) return true;

        path.add(node);

        if (findPath(node.left, n, path)) return true;
        if (findPath(node.right, n, path)) return true;

        path.remove(path.size() - 1);
        return false;
    }


    public static void main(String[] args) {
        TreeNode root = TreeNode.binarySearchTree();

        Lowest_Common_Ancestor lcaFinder = new Lowest_Common_Ancestor();

        System.out.println(lcaFinder.LCA(root, 160, 200).val);
    }
}
