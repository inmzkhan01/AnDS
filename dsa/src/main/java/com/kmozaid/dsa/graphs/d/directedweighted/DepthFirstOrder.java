package com.kmozaid.dsa.graphs.d.directedweighted;

import com.kmozaid.dsa.stackqueue.ResizingArrayStack;

/**
 * Topological Sort. Applicable only to DAG (Directed Acyclic Graph)
 * Reverse DFS postorder.
 */
public class DepthFirstOrder {

    private boolean[] marked;
    private ResizingArrayStack<Integer> reversePost;

    public DepthFirstOrder(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        reversePost = new ResizingArrayStack<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;

        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (!marked[w])
                dfs(G, w);
        }

        reversePost.push(v);
    }

    public Iterable<Integer> reversePostOrder() {
        return reversePost;
    }

}
