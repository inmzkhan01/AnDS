package com.mozaid.dsa.graphs.b.directed;

import com.mozaid.dsa.stackqueue.ResizingArrayStack;

public class DirectedGraphCycleDFS {

    private boolean[] marked;                       // marked[v] = has vertex v been marked?
    private int[] edgeTo;                           // edgeTo[v] = previous vertex on path to v
    private boolean[] onStack;                      // onStack[v] = is vertex on the stack?
    private ResizingArrayStack<Integer> cycle;      // directed cycle (or null if no such cycle)

    public DirectedGraphCycleDFS(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) return;

                // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new ResizingArrayStack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

}
