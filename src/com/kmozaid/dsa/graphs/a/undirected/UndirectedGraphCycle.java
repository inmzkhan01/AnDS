package com.kmozaid.dsa.graphs.a.undirected;


import com.kmozaid.dsa.unionfind.QuickUnion;

import java.util.*;

public class UndirectedGraphCycle {

    public static boolean hasCycleBfs(Graph G) {
        boolean[] marked = new boolean[G.V()];

        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                int[] parent = new int[G.V()];
                Arrays.fill(parent, -1);
                Queue<Integer> queue = new LinkedList<>();
                marked[s] = true;
                queue.offer(s);
                while (!queue.isEmpty()) {
                    int v = queue.poll();
                    for (int w : G.adj(v)) {
                        if (!marked[w]) {
                            marked[w] = true;
                            parent[w] = v;
                            queue.offer(w);
                        } else if (w != parent[v]) {
                            return true;
                        }
                    }
                }
            }

        }

        return false;
    }

    public static boolean hasCycleDfs(Graph G) {
        boolean[] marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v] && dfs(G, v, -1, marked)) {
                return true;
            }
        }
        return false;
    }

    private static boolean dfs(Graph G, int v, int parent, boolean[] marked) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                if (dfs(G, w, v, marked))
                    return true;
            } else if (w != parent) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCycleUF(Graph G) {
        QuickUnion uf = new QuickUnion(G.V());
        Set<String> edges = new HashSet<>();

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (!edges.contains(v + "|" + w) && !edges.contains(w + "|" + v)) {
                    edges.add(v + "|" + w);
                    edges.add(w + "|" + v);
                    if (uf.connected(v, w)) {
                        return true;
                    } else {
                        uf.union(v, w);
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Has cycle dfs: " + hasCycleDfs(Graph.eulerGraph()));
        System.out.println("Has cycle bfs: " + hasCycleBfs(Graph.eulerGraph()));
        System.out.println("Has cycle uf: " + hasCycleUF(Graph.eulerGraph()));

        System.out.println("Has cycle dfs: " + hasCycleDfs(Graph.acyclic()));
        System.out.println("Has cycle bfs: " + hasCycleBfs(Graph.acyclic()));
        System.out.println("Has cycle uf: " + hasCycleUF(Graph.acyclic()));
    }
}
