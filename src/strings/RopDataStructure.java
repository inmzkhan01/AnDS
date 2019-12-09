package strings;

public class RopDataStructure {

    static int LEAF_LEN = 2;

    static class Rope {
        Rope left, right, parent;
        char[] chars;
        int count;
    }

    static Rope createRopeRootNode(String str) {
        Rope root = new Rope();
        createRopeNode(root, null, str.toCharArray(), 0, str.length() - 1);
        return root;
    }

    private static void createRopeNode(Rope root, Rope parent, char[] chars, int left, int right) {
        root.parent = parent;
        if (right - left > LEAF_LEN) {
            root.count = (right - left) / 2;
            root.left = new Rope();
            root.right = new Rope();

            int mid = (left + right) / 2;
            createRopeNode(root.left, root, chars, left, mid);
            createRopeNode(root.right, root, chars, mid + 1, right);
        } else {
            root.count = right - left;
            root.chars = new char[root.count+1];
            for (int i = left, j = 0; i <= right; i++) {
                root.chars[j++] = chars[i];
            }
        }
    }

    static void printRopeNode(Rope node) {
        if(null == node) return;

        if(node.left == null && node.right == null) {
            for(char c: node.chars) {
                System.out.print(c);
            }
        }

        printRopeNode(node.left);
        printRopeNode(node.right);
    }


    public static void main(String[] args) {
        String str = "This is rope data structure implementation";
        Rope root1 = createRopeRootNode(str);
        printRopeNode(root1);
    }

}