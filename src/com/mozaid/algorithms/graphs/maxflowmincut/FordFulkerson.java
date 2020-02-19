package com.mozaid.algorithms.graphs.maxflowmincut;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FordFulkerson {

    private boolean[] marked;     // marked[v] = true iff s->v path in residual graph
    private FlowEdge[] edgeTo;    // edgeTo[v] = last edge on shortest residual s->v path
    private double value;         // current value of max flow
    private List<Integer> minCut;

    /**
     * Compute a maximum flow and minimum cut in the network G from vertex s to vertex t.
     */
    public FordFulkerson(FlowNetwork G, int s, int t) {
        while (hasAugmentingPath(G, s, t)) {
            // compute bottleneck capacity
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }
            // augment flow
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
            value += bottle;
        }

        minCut = new ArrayList<>();
        for (int v = 0; v < G.V(); v++) {
            if (inCut(v)) {
                minCut.add(v);
            }
        }
    }

    /**
     * Returns the value of the maximum flow.
     */
    public double value() {
        return value;
    }

    /**
     * Returns true if the specified vertex is on the {@code s} side of the mincut.
     */
    public boolean inCut(int v) {
        return marked[v];
    }

    /**
     * Returns min cut vertices.
     */
    public List<Integer> minCut() {
        return minCut;
    }

    // is there an augmenting path?
    // if so, upon termination edgeTo[] will contain a parent-link representation of such a path
    // this implementation finds a shortest augmenting path (fewest number of edges),
    // which performs well both in theory and in practice
    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.V()];
        marked = new boolean[G.V()];

        // breadth-first search
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        marked[s] = true;
        while (!queue.isEmpty() && !marked[t]) {
            int v = queue.poll();

            for (FlowEdge e : G.adj(v)) {
                int w = e.other(v);

                // if residual capacity from v to w
                if (e.residualCapacityTo(w) > 0) {
                    if (!marked[w]) {
                        edgeTo[w] = e;
                        marked[w] = true;
                        queue.offer(w);
                    }
                }
            }
        }

        // is there an augmenting path?
        return marked[t];
    }

    public static void main(String[] args) {
        // create flow network with V vertices and E edges
        int V = 10;
        int E = 10;
        int s = 0, t = V - 1;
        FlowNetwork G = new FlowNetwork(V, E);
        StdOut.println(G);

        // compute maximum flow and minimum cut
        FordFulkerson maxflow = new FordFulkerson(G, s, t);
        StdOut.println("Max flow from " + s + " to " + t);
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if ((v == e.from()) && e.flow() > 0)
                    StdOut.println("   " + e);
            }
        }

        // print min-cut
        StdOut.println("Min cut: " + maxflow.minCut());

        StdOut.println("Max flow value = " + maxflow.value());
    }

}
