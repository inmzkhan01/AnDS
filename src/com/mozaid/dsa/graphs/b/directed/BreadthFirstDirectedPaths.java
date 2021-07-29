package com.mozaid.dsa.graphs.b.directed;

import java.util.*;

public class BreadthFirstDirectedPaths {

    private final boolean marked[];
    private final int edgeTo[];
    private final int distTo[];

    public BreadthFirstDirectedPaths(Digraph G, int s) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.distTo = new int[G.V()];

        bfs(G, s);
    }

    public BreadthFirstDirectedPaths(Digraph G, Iterable<Integer> sources) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.distTo = new int[G.V()];

        bfs(G, sources);
    }

    // BFS from single source.
    private void bfs(Digraph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        marked[s] = true;
        distTo[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    queue.offer(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    // BFS from multiple sources.
    private void bfs(Digraph G, Iterable<Integer> sources) {
        Queue<Integer> queue = new LinkedList<>();
        for (int s : sources) {
            queue.offer(s);
            marked[s] = true;
            distTo[s] = 0;
        }
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    queue.offer(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        List<Integer> path = new ArrayList<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.add(x);
        }
        path.add(x);

        Collections.reverse(path);

        return path;
    }

}
