package com.kmozaid.dsa.unionfind;

public class PathCompressionWeightedQuickUnion {

    private int[] id;
    private int[] sz;

    public PathCompressionWeightedQuickUnion(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            //Make every other node in path points to its grandparent. (thereby halving path length)
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;

        /*
        int root = i;
        while (root != id[root])
            root = id[root];
        while (i != root) {
            int newp = id[i];
            id[i] = root;
            i = newp;
        }
        return root;
        */
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (sz[i] + sz[j] == id.length) {
            System.out.println("All members are connected");
        }

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }


    public static void main(String[] args) {
        PathCompressionWeightedQuickUnion uf = new PathCompressionWeightedQuickUnion(5);

        uf.union(2, 3);
        uf.union(1, 4);
        System.out.println("connected(2, 4): " + uf.connected(2, 4));
        uf.union(2, 4);
        System.out.println("connected(2, 4): " + uf.connected(2, 4));
        uf.union(0, 3);
        System.out.println("connected(1, 3): " + uf.connected(1, 3));
        uf.union(1, 3);
    }

}