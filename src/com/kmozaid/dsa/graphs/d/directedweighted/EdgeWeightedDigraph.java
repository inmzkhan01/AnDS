package com.kmozaid.dsa.graphs.d.directedweighted;

import edu.princeton.cs.algs4.ResizingArrayStack;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedDigraph {

    private int V;
    private int E;
    private List<DirectedEdge>[] adj;
    private int[] indegree;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        adj = (List<DirectedEdge>[]) new List[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
        this.indegree = new int[V];
    }

    public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++)
            this.indegree[v] = G.indegree(v);
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            ResizingArrayStack<DirectedEdge> reverse = new ResizingArrayStack<>();
            for (DirectedEdge e : G.adj[v]) {
                reverse.push(e);
            }
            for (DirectedEdge e : reverse) {
                adj[v].add(e);
            }
        }
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        indegree[w]++;
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public int indegree(int v) {
        return indegree[v];
    }

    public int outdegree(int v) {
        return adj[v].size();
    }

    Iterable<DirectedEdge> edges() {
        return null;
    }

}
