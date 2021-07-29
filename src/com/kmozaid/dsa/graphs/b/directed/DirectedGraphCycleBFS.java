package com.kmozaid.dsa.graphs.b.directed;

import com.kmozaid.dsa.stackqueue.ResizingArrayQueue;
import com.kmozaid.dsa.stackqueue.ResizingArrayStack;

public class DirectedGraphCycleBFS {

    private ResizingArrayStack<Integer> cycle;     // the directed cycle; null if digraph is acyclic

    public DirectedGraphCycleBFS(Digraph G) {

        // indegrees of remaining vertices
        int[] indegree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            indegree[v] = G.indegree(v);
        }

        // initialize queue to contain all vertices with indegree = 0
        ResizingArrayQueue<Integer> queue = new ResizingArrayQueue<>();
        for (int v = 0; v < G.V(); v++)
            if (indegree[v] == 0) queue.enqueue(v);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                indegree[w]--;
                if (indegree[w] == 0)
                    queue.enqueue(w);
            }
        }

        int[] edgeTo = new int[G.V()];

        int root = -1;
        for (int v = 0; v < G.V(); v++) {
            if (indegree[v] == 0)
                continue;
            else
                root = v;
            for (int w : G.adj(v)) {
                if (indegree[w] > 0) {
                    edgeTo[w] = v;
                }
            }
        }

        if (root != -1) {

            // find any vertex on cycle
            boolean[] visited = new boolean[G.V()];
            while (!visited[root]) {
                visited[root] = true;
                root = edgeTo[root];
            }

            // extract cycle
            cycle = new ResizingArrayStack<>();
            int v = root;
            do {
                cycle.push(v);
                v = edgeTo[v];
            } while (v != root);
            cycle.push(root);
        }
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasCycle() {
        return cycle != null;
    }
}
