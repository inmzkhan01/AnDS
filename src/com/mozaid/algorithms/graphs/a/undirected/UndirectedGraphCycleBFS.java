package com.mozaid.algorithms.graphs.a.undirected;

import com.mozaid.algorithms.stackqueue.ResizingArrayStack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraphCycleBFS {

    private boolean[] marked;
    private int[] edgeTo;
    private ResizingArrayStack<Integer> cycle;

    public UndirectedGraphCycleBFS(Graph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v] && bfs(G, v)) {
                return;
            }
        }
    }

    public boolean bfs(Graph G, int s) {
        Arrays.fill(edgeTo, -1);
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.offer(s);
        while (!queue.isEmpty() && cycle == null) {

            int v = queue.poll();

            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    queue.offer(w);
                    edgeTo[w] = v;
                } else if (w != edgeTo[v]) { // If w is visited and not parent of current vertex, then there is a cycle.
                    cycle = new ResizingArrayStack<>();
                    for (int x = v; x != -1; x = edgeTo[x]) {
                        cycle.push(x);
                    }
                    cycle.push(w);
                    cycle.push(v);

                    break;
                }
            }
        }

        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        /**
         * Problem: Find a cycle.
         */
        UndirectedGraphCycleBFS cycleBfs = new UndirectedGraphCycleBFS(Graph.eulerGraph());
        System.out.println("Cycle using BFS: " + cycleBfs.cycle());
    }

}
