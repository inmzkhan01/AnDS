package com.mozaid.dsa.graphs.a.undirected;

import com.mozaid.dsa.stackqueue.ResizingArrayStack;
import com.mozaid.dsa.stackqueue.Stack;

import java.util.Iterator;

public class NonrecursiveDFS {

    private boolean[] marked;
    private int[] edgeTo;

    public NonrecursiveDFS(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        //dfs(G, s);
        dfs2(G, s);
    }

    private void dfs(Graph G, int s) {
        Stack<Integer> stack = new ResizingArrayStack<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!marked[v]) {
                marked[v] = true;
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        stack.push(w);
                    }
                }
            }
        }
    }

    private void dfs2(Graph G, int s) {
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        Stack<Integer> stack = new ResizingArrayStack<>();
        marked[s] = true;
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                System.out.printf("check %d\n", w);
                if (!marked[w]) {
                    // discovered vertex w for the first time
                    marked[w] = true;
                    edgeTo[w] = v;
                    stack.push(w);
                    System.out.printf("dfs(%d)\n", w);
                }
            } else {
                System.out.printf("%d done\n", v);
                stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        new NonrecursiveDFS(Graph.acyclic(), 0);
    }
}
