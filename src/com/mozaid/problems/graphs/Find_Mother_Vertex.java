package com.mozaid.problems.graphs;

import com.mozaid.algorithms.graphs.b.directed.Digraph;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/find-a-mother-vertex-in-a-graph/
 */
public class Find_Mother_Vertex {

    public static int findMother(Digraph G) {
        boolean[] marked = new boolean[G.V()];

        int lastFinished = 0;
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v, marked);
                lastFinished = v;
            }
        }

        Arrays.fill(marked, false);

        dfs(G, lastFinished, marked);

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                return -1;
        }

        return lastFinished;
    }

    private static void dfs(Digraph digraph, int v, boolean[] marked) {
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked[w])
                dfs(digraph, w, marked);
        }
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(4, 1);
        g.addEdge(6, 4);
        g.addEdge(5, 6);
        g.addEdge(5, 2);
        g.addEdge(6, 0);

        System.out.println("A mother vertex is " + findMother(g));
    }
}
