package graphs.b.directed;

import java.util.ArrayList;
import java.util.List;

public class Digraph {

    private final int V;
    private int E;
    private List<Integer>[] adj;
    private int[] indegree;

    public Digraph(int V) {
        this.V = V;
        adj = (List<Integer>[]) new List[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
        indegree = new int[V];
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    public Iterable<Integer> adj(int v) {
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


    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

}
