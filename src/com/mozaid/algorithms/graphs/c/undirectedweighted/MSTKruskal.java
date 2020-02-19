package com.mozaid.algorithms.graphs.c.undirectedweighted;

import com.mozaid.algorithms.priorityqueue.MinPQ;
import com.mozaid.algorithms.unionfind.WeightedQuickUnionUF;

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

        check(G);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(EdgeWeightedGraph G) {

        // check that it is acyclic
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new WeightedQuickUnionUF(G.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }

}
