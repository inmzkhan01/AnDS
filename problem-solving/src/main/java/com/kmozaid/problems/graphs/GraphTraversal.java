package com.kmozaid.problems.graphs;

import com.kmozaid.problems.graphs.GraphHelper.Node;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphTraversal {

    public static void main(String[] args) {
        Node graphNode = GraphHelper.simpleConnectedGraph();
        bfs(graphNode);
        //bfsRecursive(graphNode)  This is not possible. Recursion is based on stack and BFS require queue which is opposite to stack.

        dfs(graphNode);
        dfsRecursive(graphNode);
    }

    static void bfs(Node node) {
        System.out.println("\nBFS using Queue:");
        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node d = queue.poll();
            System.out.print(" "+d.val);
            for (Node neighbor : d.neighbors) {
                if (!visited.contains(neighbor.val) && !queue.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
            visited.add(d.val);
        }
        System.out.println("\n");
    }

    static void dfs(Node node) {
        System.out.println("\nDFS using Stack:");
        Set<Integer> visited = new LinkedHashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node d = stack.pop();
            System.out.print(" "+d.val);
            for (Node neighbor : d.neighbors) {
                if (!visited.contains(neighbor.val) && !stack.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
            visited.add(d.val);
        }
    }

    static void dfsRecursive(Node node) {
        System.out.println("\nDFS using Recursion:");
        Set<Integer> visited = new HashSet<>();
        visitNode(node, visited);
        System.out.println("\n");
    }

    static void visitNode(Node node, Set<Integer> visited) {
        if(visited.contains(node.val)) {
            return;
        }
        System.out.print(" "+node.val);
        visited.add(node.val);
        for(Node n : node.neighbors) {
            visitNode(n, visited);
        }
    }
}
