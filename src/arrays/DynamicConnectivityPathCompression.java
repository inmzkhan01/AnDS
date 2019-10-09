package arrays;

import java.util.Arrays;

public class DynamicConnectivityPathCompression {

    private int[] id;    // id[i] = parent of i

    public DynamicConnectivityPathCompression(int n) {
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
        if(connected) {
            System.out.println(p+","+q+" : connected");
        } else {
            System.out.println(p+","+q+" : not connected");
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


    public static void main(String[] args) {
        DynamicConnectivityPathCompression dynamicConnectivity = new DynamicConnectivityPathCompression(10);
        dynamicConnectivity.union(4,3);
        dynamicConnectivity.union(3,8);
        dynamicConnectivity.union(6,5);
        dynamicConnectivity.union(9,4);
        dynamicConnectivity.union(2,1);
        //dynamicConnectivity.connected(8,9);
        System.out.println(Arrays.toString(dynamicConnectivity.id));
        dynamicConnectivity.connected(0,7);
        dynamicConnectivity.union(5,0);
        dynamicConnectivity.union(7,2);
        dynamicConnectivity.union(6,1);
        dynamicConnectivity.union(1,0);
        dynamicConnectivity.connected(0,7);
        System.out.println(Arrays.toString(dynamicConnectivity.id));
    }


}
