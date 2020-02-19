package com.mozaid.problems.graphs;

import com.mozaid.algorithms.graphs.b.directed.Digraph;
import com.mozaid.algorithms.stackqueue.ResizingArrayStack;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class Find_Tasks_Order_With_Precedence_Constraint {

    public static Iterable<Integer> findTasksOrder(List<Integer>[] precedenceProcesses) {
        int n = precedenceProcesses.length;
        Digraph digraph = new Digraph(n);
        for (int i = 0; i < n; i++) {
            for (int w : precedenceProcesses[i]) {
                digraph.addEdge(i, w);
            }
        }
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();
        boolean[] marked = new boolean[n];
        int v;
        for (v = 0; v < n; v++) {
            if (digraph.indegree(v) == 0)
                break;
        }
        dfs(digraph, v, marked, stack);
        return stack;
    }

    private static void dfs(Digraph digraph, int v, boolean[] marked, ResizingArrayStack<Integer> stack) {
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked[w])
                dfs(digraph, w, marked, stack);
        }
        stack.push(v);
    }

    public static void main(String[] args) {
        List<Integer>[] jobsPrecedence = (List<Integer>[]) new List[7];
        jobsPrecedence[0] = asList(5, 2, 1);
        jobsPrecedence[1] = asList(4);
        jobsPrecedence[2] = emptyList();
        jobsPrecedence[3] = asList(6, 5, 4, 2);
        jobsPrecedence[4] = emptyList();
        jobsPrecedence[5] = asList(2);
        jobsPrecedence[6] = asList(4, 0);

        for (int task : findTasksOrder(jobsPrecedence)) {
            System.out.print(task + " ");
        }
    }
}
