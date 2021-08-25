package com.kmozaid.dsa.graphs.b.directed;

/**
 * Strongly Connected Components in Digraph
 */
public class ConnectedComponentsStronglyDigraph {

    private boolean[] marked;
    private int[] id;
    private int count;

    public ConnectedComponentsStronglyDigraph(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        // Run DFS on reverse of G to compute reverse postorder.
        DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());

        // Run DFS on G, considering vertices in order given by first dfs.
        for (int v : dfs.reversePostOrder()) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

}
