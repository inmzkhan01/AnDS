package algorithms.graphs.a.undirected;

import algorithms.stackqueue.ResizingArrayStack;

public class UndirectedGraphCycleDFS {

    private boolean[] marked;
    private int[] edgeTo;
    private ResizingArrayStack<Integer> cycle;

    public UndirectedGraphCycleDFS(Graph G) {
        if (hasSelfLoop(G)) return;
        if (hasParallelEdges(G)) return;

        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v, -1);
            }
        }
    }

    public void dfs(Graph G, int v, int parent) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            // short circuit if cycle already found
            if (cycle != null) return;

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w, v);
            } else if (w != parent) { // If w is visited and not parent of current vertex, then there is a cycle.
                cycle = new ResizingArrayStack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    private boolean hasSelfLoop(Graph G) {
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    cycle = new ResizingArrayStack<>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }

        }
        return false;
    }

    private boolean hasParallelEdges(Graph G) {
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                // if we see w again in this loop iteration then parallel edges exist.
                if (marked[w]) {
                    cycle = new ResizingArrayStack<>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
                marked[w] = true;
            }

            // Reset marked array.
            for (int w : G.adj(v)) {
                marked[w] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /**
         * Problem: Find a cycle.
         */
        UndirectedGraphCycleDFS cycleDfs = new UndirectedGraphCycleDFS(Graph.eulerGraph());
        System.out.println("Cycle using DFS: " + cycleDfs.cycle());
    }

}
