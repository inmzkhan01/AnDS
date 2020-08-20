package com.mozaid.problems.graphs;

import com.mozaid.algorithms.graphs.a.undirected.Graph;

import java.util.*;

public class Find_Graph_Diameter {

    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public List<Integer> diameter(Graph graph) {
        int v = 0;
        bfs(graph, v);
        int w = farthestVertex(v);
        bfs(graph, w);
        int x = farthestVertex(w);

        List<Integer> diameter = new ArrayList<>();
        for (int p = x; p != w; p = edgeTo[p]) {
            diameter.add(p);
        }
        diameter.add(w);
        Collections.reverse(diameter);

        return diameter;
    }

    private void bfs(Graph graph, int s) {
        int N = graph.V();
        marked = new boolean[N];
        edgeTo = new int[N];
        distTo = new int[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        marked[s] = true;
        distTo[s] = 0;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    queue.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = 1 + distTo[v];
                }
            }
        }
    }

    private int farthestVertex(int s) {
        int farthestVertex = s;
        int farthestDistance = 0;
        for (int i = 0; i < distTo.length; i++) {
            if (distTo[i] > farthestDistance) {
                farthestDistance = distTo[i];
                farthestVertex = i;
            }
        }
        return farthestVertex;
    }

    public static void main(String[] args) {
        Find_Graph_Diameter diameterFinder = new Find_Graph_Diameter();
        List<Integer> diameter = diameterFinder.diameter(Graph.acyclic());
        System.out.println("Diameter is: " + diameter);
    }

}
