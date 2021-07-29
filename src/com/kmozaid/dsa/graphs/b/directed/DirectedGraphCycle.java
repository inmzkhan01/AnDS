package com.kmozaid.dsa.graphs.b.directed;

public class DirectedGraphCycle {

    public static boolean hasCycleDfs(Digraph G) {
        boolean[] marked = new boolean[G.V()];
        boolean[] onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v] && dfs(G, v, marked, onStack)) {
                return true;
            }
        }
        return false;
    }

    private static boolean dfs(Digraph G, int v, boolean[] marked, boolean[] onStack) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                if (dfs(G, w, marked, onStack))
                    return true;
            } else if (onStack[w]) {
                return true;
            }
        }
        onStack[v] = false;
        return false;
    }

}
