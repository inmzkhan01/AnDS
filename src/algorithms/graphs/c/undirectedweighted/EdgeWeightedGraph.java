package algorithms.graphs.c.undirectedweighted;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedGraph {

    private final int V;
    private int E;
    private List<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<Edge>[]) new List[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        List<Edge> list = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0)
                        list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }


}
