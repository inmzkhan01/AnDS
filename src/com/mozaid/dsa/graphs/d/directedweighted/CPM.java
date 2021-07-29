package com.mozaid.dsa.graphs.d.directedweighted;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * It solves the parallel precedence-constrained job scheduling problem via the <em>critical path method</em>.
 * It reduces the problem to the longest-paths problem in edge-weighted DAGs. It builds an edge-weighted digraph
 * (which must be a DAG) from the job-scheduling problem specification, finds the longest-paths tree, and
 * computes the longest-paths lengths (which are precisely the start times for each job)
 */
public class CPM {

    public static void main(String[] args) {
        // number of jobs
        int n = StdIn.readInt();

        // source and sink
        int source = 2 * n;
        int sink = 2 * n + 1;

        // build network
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * n + 2);
        for (int i = 0; i < n; i++) {
            double duration = StdIn.readDouble();
            G.addEdge(new DirectedEdge(source, i, 0.0));
            G.addEdge(new DirectedEdge(i + n, sink, 0.0));
            G.addEdge(new DirectedEdge(i, i + n, duration));

            // precedence constraints
            int m = StdIn.readInt();
            for (int j = 0; j < m; j++) {
                int precedent = StdIn.readInt();
                G.addEdge(new DirectedEdge(n + i, precedent, 0.0));
            }
        }

        // compute longest path
        LongestPathAcyclic lp = new LongestPathAcyclic(G, source);

        // print results
        StdOut.println(" job   start  finish");
        StdOut.println("--------------------");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i + n));
        }
        StdOut.printf("Finish time: %7.1f\n", lp.distTo(sink));
    }

}