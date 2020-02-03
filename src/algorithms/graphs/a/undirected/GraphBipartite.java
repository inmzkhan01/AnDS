package graphs.a.undirected;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphBipartite {

    /**
     * Coloring -
     * 1 = White
     * 2 = Red
     */

    public static boolean bipartiteBfs(Graph G) {
        int[] color = new int[G.V()];

        for (int s = 0; s < G.V(); s++) {
            if (color[s] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(s);
                color[s] = 1;
                while (!queue.isEmpty()) {
                    int v = queue.poll();
                    for (int w : G.adj(v)) {
                        if (color[w] == 0) {
                            color[w] = 3 - color[v];
                            queue.offer(w);
                        } else if (color[w] == color[v]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean bipartiteDfsIterative(Graph G) {
        int[] color = new int[G.V()];

        for (int s = 0; s < G.V(); s++) {
            if (color[s] == 0) {
                Stack<Integer> stack = new Stack<>();
                stack.push(s);
                color[s] = 1;
                while (!stack.isEmpty()) {
                    int v = stack.pop();
                    for (int w : G.adj(v)) {
                        if (color[w] == 0) {
                            color[w] = 3 - color[v];
                            stack.push(w);
                        } else if (color[w] == color[v]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean bipartiteDfs(Graph G) {
        int[] color = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (color[v] == 0) {
                color[v] = 1;
                if (!dfs(G, v, color))
                    return false;
            }
        }
        return true;
    }

    private static boolean dfs(Graph G, int v, int[] color) {
        for (int w : G.adj(v)) {
            if (color[w] == 0) {
                color[w] = 3 - color[v];
                if (!dfs(G, w, color))
                    return false;
            } else if (color[w] == color[v]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is bipartite bfs: " + bipartiteBfs(Graph.bipartiteGraph()));
        System.out.println("Is bipartite dfs(iterative): " + bipartiteDfsIterative(Graph.bipartiteGraph()));
        System.out.println("Is bipartite dfs: " + bipartiteDfs(Graph.bipartiteGraph()));
        System.out.println("Is bipartite bfs: " + bipartiteBfs(Graph.eulerGraph()));
        System.out.println("Is bipartite dfs(iterative): " + bipartiteDfsIterative(Graph.uncyclicGraph()));
        System.out.println("Is bipartite dfs: " + bipartiteDfs(Graph.eulerGraph()));
    }
}
