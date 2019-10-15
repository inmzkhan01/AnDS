package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Dynamic_Connectivity {


    static class OrignalSolution {
        // 4,3: 3,8: 6,5: 9,4: 2,1
        //CONNECTED 0,7: 8,9
        // 5,0: 7,2: 6,1: 1,0:
        //CONNECTED 0,7

        //PATH: 0,1,2,5,6,7
        //PATH: 3,4,8,9
        static void union(int x, int y, int[] arr) {
            if (arr[x] == -1 && arr[y] == -1) {
                arr[x] = y;
                arr[y] = y;
            } else if (arr[x] == -1) {
                arr[x] = x;
                int valueAtY = arr[y];
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == valueAtY) {
                        arr[i] = x;
                    }
                }
            } else if (arr[y] == -1) {
                arr[y] = y;
                int valueAtX = arr[x];
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == valueAtX) {
                        arr[i] = y;
                    }
                }
            } else {
                int valueAtY = arr[y];
                int valueAtX = arr[x];
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == valueAtX) {
                        arr[i] = valueAtY;
                    }
                }
            }
        }

        static void connected(int x, int y, int[] arr) {
            if (arr[x] != -1 && arr[y] != -1 && arr[x] == arr[y]) {
                System.out.println(String.format("(%s,%s) are connected.", x, y));
            } else {
                System.out.println(String.format("(%s,%s) are not connected.", x, y));
            }
        }

        static void run() {
            System.out.println("########Dynamic Connectivity########");
            System.out.println("Union method connects two elements");
            System.out.println("Connected method checks if two objects are elements or not.");

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter total number of elements: ");
            int n = sc.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = -1;
            }

            boolean run = true;
            while (run) {
                System.out.println("\nChoose an option: ");
                System.out.println("\"1\" for union");
                System.out.println("\"2\" for connected");
                System.out.println("\"0\" for exit");
                System.out.print("Enter your input ?: ");

                int action = sc.nextInt();

                int x, y;
                switch (action) {
                    case 1:
                        System.out.print("\nEnter space separated two elements: ");
                        x = sc.nextInt();
                        y = sc.nextInt();
                        union(x, y, arr);
                        break;
                    case 2:
                        System.out.print("\nEnter space separated two elements: ");
                        x = sc.nextInt();
                        y = sc.nextInt();
                        connected(x, y, arr);
                        break;
                    case 0:
                        run = false;
                        break;
                    default:
                        System.out.println("\nInvalid input, please try again...");
                }
            }
        }
    }


    static class QuickUnionQuickFind {

        //######---Start of Quick Find Algorithm---#######

        static void unionQuickFind(int p, int q, int[] id) {
            int pID = id[p];
            int qID = id[q];
            for (int i = 0; i < id.length; i++) {
                if (id[i] == pID) {
                    id[i] = qID;
                }
            }
        }

        static void connectedQuickFind(int p, int q, int[] id) {
            if (id[p] == id[q]) {
                System.out.println(String.format("(%s,%s) are connected.", p, q));
            } else {
                System.out.println(String.format("(%s,%s) are not connected.", p, q));
            }
        }

        //######---End of Quick Find Algorithm---#######


        //######---Start of Quick UnionAlgorithm---#######

        static void unionQuickUnion(int p, int q, int[] id) {
            int rootP = root(p, id);
            int rootQ = root(q, id);
            if (rootP == rootQ) {
                return;
            }
            id[rootP] = rootQ;
        }

        static void connectedQuickUnion(int p, int q, int[] id) {
            int rootP = root(p, id);
            int rootQ = root(q, id);
            if (rootP == rootQ) {
                System.out.println(String.format("(%s,%s) are connected.", p, q));
            } else {
                System.out.println(String.format("(%s,%s) are not connected.", p, q));
            }
        }

        // Return root of p.
        static int root(int p, int[] id) {
            int root = p;
            while (root != id[root]) {
                root = id[root];
            }
            return root;
        }
        //######---End of Quick Union Algorithm---#######

        //***********---Start of Weighted Quick UnionAlgorithm---************
        static void unionWeightedQuickUnion(int p, int q, int[] id, int[] size) {
            int rootP = root(p, id);
            int rootQ = root(q, id);

            if (rootP == rootQ) {
                return;
            }
            if (size[rootP] < size[rootQ]) {
                id[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                id[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }
        //**************---End of Weighted Quick Union Algorithm---*********

        static void run() {
            System.out.println("########Dynamic Connectivity By Medium########");
            System.out.println("Union: method connects two elements");
            System.out.println("Connected: method checks if two objects are elements or not.");

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter total number of elements: ");
            int n = sc.nextInt();

            int[] id = new int[n];
            int[] size = new int[n];
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
                size[i] = 1;
            }

            boolean run = true;
            while (run) {
                System.out.println("\nSelect an option: ");
                System.out.println("\"1\" Union");
                System.out.println("\"2\" Connected");
                System.out.println("\"0\" Exit");
                System.out.print("? ");
                int command = sc.nextInt();

                System.out.print("\nEnter space separated two elements: ");
                int p = sc.nextInt();
                int q = sc.nextInt();

                switch (command) {
                    case 1:
                        //unionQuickFind(p, q, id);
                        //unionQuickUnion(p, q, id);
                        unionWeightedQuickUnion(p, q, id, size);
                        break;
                    case 2:
                        //connectedQuickFind(p, q, id);
                        connectedQuickUnion(p, q, id); //Connection is same for Weighted-Quick-Union algorithm.
                        break;
                    case 0:
                        run = false;
                        break;
                    default:
                        System.out.println("\nInvalid input, please try again...");
                }
            }
        }
    }


    static class PathCompression {

        private int[] id;    // id[i] = parent of i

        public PathCompression(int n) {
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
        }

        public void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if (rootP == rootQ) return;
            id[rootP] = rootQ;
        }

        public boolean connected(int p, int q) {
            boolean connected = root(p) == root(q);
            if (connected) {
                System.out.println(p + "," + q + " : connected");
            } else {
                System.out.println(p + "," + q + " : not connected");
            }
            return connected;
        }

        private int root(int p) {
            int root = p;
            while (root != id[root])
                root = id[root];
            while (p != root) {
                int newp = id[p];
                id[p] = root;
                p = newp;
            }
            return root;
        }

        static void run() {
            PathCompression dynamicConnectivity = new PathCompression(10);
            dynamicConnectivity.union(4, 3);
            dynamicConnectivity.union(3, 8);
            dynamicConnectivity.union(6, 5);
            dynamicConnectivity.union(9, 4);
            dynamicConnectivity.union(2, 1);
            //dynamicConnectivity.connected(8,9);
            System.out.println(Arrays.toString(dynamicConnectivity.id));
            dynamicConnectivity.connected(0, 7);
            dynamicConnectivity.union(5, 0);
            dynamicConnectivity.union(7, 2);
            dynamicConnectivity.union(6, 1);
            dynamicConnectivity.union(1, 0);
            dynamicConnectivity.connected(0, 7);
            System.out.println(Arrays.toString(dynamicConnectivity.id));
        }

    }

    public static void main(String[] args) {
        OrignalSolution.run();
        QuickUnionQuickFind.run();
        PathCompression.run();
    }

}
