package trees;

public class SumPathNumbers {

    public int sumNumbers(TreeNode node) {
        return sumNumbers(node, 0);
    }

    private int sumNumbers(TreeNode node, int num) {
        if (node == null)
            return 0;

        num = (num * 10 + node.val) % 1003;

        if (node.left == null && node.right == null) {
            return num;
        }

        int num1 = sumNumbers(node.left, num);
        int num2 = sumNumbers(node.right, num);
        return (num1 + num2) % 1003;
    }
}
