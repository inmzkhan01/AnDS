package com.kmozaid.dsa.graphs.c.undirectedweighted;

import com.kmozaid.dsa.priorityqueue.MinPQ;
import com.kmozaid.dsa.unionfind.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;

public class MSTKruskal {

    private List<Edge> mst = new ArrayList<>();
    private double weight;

    public MSTKruskal(EdgeWeightedGraph G) {
        MinPQ<Edge> minPQ = new MinPQ<>(G.E());
        for (Edge e : G.edges()) {
            minPQ.insert(e);
        }

        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());

        while (!minPQ.isEmpty() && mst.size() < G.V() - 1) {
            Edge edge = minPQ.delMin();
            int v = edge.either(), w = edge.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.add(edge);
                weight += edge.weight();
            }
        }

    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }
    
}
