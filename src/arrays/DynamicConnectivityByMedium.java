package arrays;

import java.util.Scanner;

public class DynamicConnectivityByMedium {

    //UNION 4,3: 3,8: 6,5: 9,4: 2,1
    //CONNECTED 0,7: 8,9

    //UNION 5,0: 7,2: 6,1: 1,0:
    //CONNECTED 0,7

    //PATH: 0,1,2,5,6,7
    //PATH: 3,4,8,9

    public static void main(String[] args) {
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


    //######---Start of Quick Find Algorithm---#######

    static void unionQuickFind(int p, int q, int[] id) {
        int pID = id[p];
        int qID = id[q];
        for (int i=0; i<id.length; i++) {
            if(id[i] == pID) {
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
        if(rootP == rootQ){
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
        while(root != id[root]){
            root = id[root];
        }
        return root;
    }
    //######---End of Quick Union Algorithm---#######

    //***********---Start of Weighted Quick UnionAlgorithm---************
    static void unionWeightedQuickUnion(int p, int q, int[] id, int[] size) {
        int rootP = root(p, id);
        int rootQ = root(q, id);

        if(rootP == rootQ){
            return;
        }
        if(size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }
    //**************---End of Weighted Quick Union Algorithm---*********

}
