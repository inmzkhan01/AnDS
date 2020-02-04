package algorithms.graphs.a.undirected;

import java.util.*;

/**
 * BFS can compute the shortest path(fewest edges from s to all vertices
 * because it examines vertices in increasing distance from s.
 * <p>
 * how do we know that it computes shortest path?
 * Ans: the queue always contains some vertices of distance k from the source,
 * followed by zero or more vertices of distance k+1. So they're on a queue,
 * we're processing them in first and first out order.
 */
public class BreadthFirstPaths {

    private final boolean marked[];
    private final int edgeTo[];
    private final int distTo[];
    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.distTo = new int[G.V()];
        this.s = s;

        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
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

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        List<Integer> path = new ArrayList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.add(x);
        }
        path.add(s);

        Collections.reverse(path);

        return path;
    }


}
