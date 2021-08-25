package com.kmozaid.problems.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
            if (!marked[v] && dfs(graph, v, marked, onStack)) {
                return false;
            }
        }
        return true;
    }

    // Return true if a cycle exist.
    private boolean dfs(List<Integer>[] graph, int v, boolean[] marked, boolean[] onStack) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : graph[v]) {
            if (!marked[w]) {
                if (dfs(graph, w, marked, onStack))
                    return true;
            } else if (onStack[w]) {
                return true;
            }
        }
        onStack[v] = false;
        return false;
    }

}
