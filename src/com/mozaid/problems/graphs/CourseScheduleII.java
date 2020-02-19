package com.mozaid.problems.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseScheduleII {

    int i = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //Vertices.
        int V = numCourses;

        // Build Graph
        List<Integer>[] graph = (List<Integer>[]) new List[V];
        for (int v = 0; v < V; v++) {
            graph[v] = new ArrayList<>();
        }
        for (int[] edge : prerequisites) {
            graph[edge[0]].add(edge[1]);
        }

        boolean[] marked = new boolean[V];
        boolean[] onStack = new boolean[V];
        for (int v = 0; v < V; v++) {
            if (!marked[v] && hasCycleDFS(graph, v, marked, onStack)) {
                return new int[0];
            }
        }

        Arrays.fill(marked, false);

        int[] courseOrder = new int[V];

        for (int v = 0; v < V; v++) {
            if (!marked[v])
                dfs(graph, v, marked, courseOrder);
        }

        return courseOrder;
    }

    // Return true if a cycle exist.
    private boolean hasCycleDFS(List<Integer>[] graph, int v, boolean[] marked, boolean[] onStack) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : graph[v]) {
            if (!marked[w]) {
                if (hasCycleDFS(graph, w, marked, onStack))
                    return true;
            } else if (onStack[w]) {
                return true;
            }
        }
        onStack[v] = false;
        return false;
    }

    private void dfs(List<Integer>[] graph, int v, boolean[] marked, int[] courseOrder) {
        marked[v] = true;
        for (int w : graph[v]) {
            if (!marked[w])
                dfs(graph, w, marked, courseOrder);
        }
        courseOrder[i++] = v;
    }

}
