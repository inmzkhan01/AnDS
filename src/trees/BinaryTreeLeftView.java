package trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeLeftView {

    static class NodeData {
        int level;
        BinaryTreeNode treeNode;

        public NodeData(BinaryTreeNode treeNode, int level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }

    public void printLeftView(BinaryTreeNode root) {
        int currentLevel = 0;

        Queue<NodeData> queue = new LinkedList<>();
        queue.offer(new NodeData(root, 1));

        while (!queue.isEmpty()) {
            NodeData nodeData = queue.poll();

            if (currentLevel < nodeData.level) {
                System.out.println(nodeData.treeNode.key);
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
        BinaryTreeNode root = BinaryTreeNode.binarySearchTree();
        new BinaryTreeLeftView().printLeftView(root);
    }
}
