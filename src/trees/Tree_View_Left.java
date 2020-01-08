package trees;

import java.util.LinkedList;
import java.util.Queue;

public class Tree_View_Left {

    static class NodeData {
        int level;
        TreeNode treeNode;

        public NodeData(TreeNode treeNode, int level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }

    public void printLeftView(TreeNode root) {
        int currentLevel = 0;

        Queue<NodeData> queue = new LinkedList<>();
        queue.offer(new NodeData(root, 1));

        while (!queue.isEmpty()) {
            NodeData nodeData = queue.poll();

            if (currentLevel < nodeData.level) {
                System.out.println(nodeData.treeNode.val);
                currentLevel = nodeData.level;
            }

            if (nodeData.treeNode.left != null) {
                queue.add(new NodeData(nodeData.treeNode.left, nodeData.level + 1));
            }

            if (nodeData.treeNode.right != null) {
                queue.add(new NodeData(nodeData.treeNode.right, nodeData.level + 1));
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.binarySearchTree();
        new Tree_View_Left().printLeftView(root);
    }
}
