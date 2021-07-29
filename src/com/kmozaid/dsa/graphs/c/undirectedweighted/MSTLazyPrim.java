package com.kmozaid.dsa.graphs.c.undirectedweighted;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class MSTLazyPrim {

    private List<Edge> mst;
    private double weight;

    private boolean[] marked;
    private MinPQ<Edge> minPQ;

    public MSTLazyPrim(EdgeWeightedGraph G) {
        mst = new ArrayList<>();

        marked = new boolean[G.V()];
        minPQ = new MinPQ<>();

        visit(G, 0);

        while (!minPQ.isEmpty()) {
            Edge e = minPQ.delMin();
            int v = e.either(), w = e.other(v);

            if (marked[v] && marked[w])
                continue;

            mst.add(e);
            weight += e.weight();

            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)])
                minPQ.insert(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

}
