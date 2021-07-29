package com.mozaid.dsa.graphs.a.undirected;

import java.util.List;

/**
 * Euler cycle. An Euler cycle in a graph is a cycle (not necessarily simple) that uses every edge in the graph exactly one.
 * Show that a connected graph has an Euler cycle if and only if every vertex has even degree.
 * Design a linear-time algorithm to determine whether a graph has an Euler cycle, and if so, find one.
 */
public class EulerianCycle {

    public static boolean hasEulerCycle(Graph G) {
        for (int v = 0; v < G.V(); v++) {
            if ((Graph.degree(G, v) & 1) != 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> findEulerCycle(Graph G) {

        return null;
    }


}
