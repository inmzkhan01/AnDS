package com.mozaid.dsa.graphs.a.undirected;


import com.mozaid.dsa.stackqueue.ResizingArrayStack;

public class DepthFirstOrder {

    private boolean[] marked;
    private ResizingArrayStack<Integer> reversePost;

    public DepthFirstOrder(Graph G) {
        marked = new boolean[G.V()];
        reversePost = new ResizingArrayStack<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
        reversePost.push(v);
    }

    public Iterable<Integer> reversePostOrder() {
        return reversePost;
    }

    public static void main(String[] args) {
        DepthFirstOrder order = new DepthFirstOrder(Graph.acyclic());
        System.out.println(order.reversePostOrder());
    }

}
