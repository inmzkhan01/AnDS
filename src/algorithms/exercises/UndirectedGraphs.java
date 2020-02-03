package exercises;

import graphs.a.undirected.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UndirectedGraphs {

    /**
     * Non-recursive depth-first search.
     * Implement depth-first search in an undirected graph without using recursion.
     */
    public static List<Integer> dfs(Graph G, int s) {
        List<Integer> result = new ArrayList<>();

        boolean[] marked = new boolean[G.V()];
        LinkedList<Integer> vertices = new LinkedList<>();
        vertices.add(s);

        while (!vertices.isEmpty()) {
            int v = vertices.removeFirst();
            if (!marked[v]) {
                marked[v] = true;
                result.add(v);
                for (int w : G.adj(v)) {
                    vertices.addFirst(w);

                    /* This is not necessary as marked vertex will not be visited again.
                    if(!marked[w]) {
                        vertices.addFirst(w);
                    }*/
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("DFS: " + dfs(Graph.uncyclicGraph(), 0));
    }
}
