package com.mozaid.dsa.graphs.d.directedweighted;


import com.mozaid.dsa.stackqueue.ResizingArrayStack;

/**
 * Can find shortest path if no negative cycle.
 */
public class ShortestPathBellmanFord {

    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path

    public ShortestPathBellmanFord(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // Since the longest possible path without a cycle can be V-1 edges, the edges must be scanned V-1 times
        // to ensure that shortest path has been found for all nodes.
        for (int i = 1; i < G.V(); i++) {
            for (int v = 0; v < G.V(); v++) {
                for (DirectedEdge e : G.adj(v)) {
                    relax(e);
                }
            }
        }

        /*
         * If a graph contains negative cycle that is reachable from the source, then there is no shortest path.
         * Any path that has a point on the negative cycle can be made cheaper by one more walk around the negative cycle.
         *
         * A final scan of all the edges is performed to find negative cycle if exist.
         * If we get a shorter path, then there is a cycle.
         */
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                if (distTo[e.to()] > distTo[e.from()] + e.weight()) {
                    System.out.println("Negative Weight Cycle Found!!");
                    return;
                }
            }
        }
    }

    // relax edge e, but update if you find a *shorter* path
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        ResizingArrayStack<DirectedEdge> path = new ResizingArrayStack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

}
