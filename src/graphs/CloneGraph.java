package graphs;

import java.util.*;
import graphs.GraphHelper.Node;

public class CloneGraph {

    public static void main(String[] args) {

        Node graphNode = GraphHelper.simpleConnectedGraph();
        System.out.println("Original: ");
        List<Node> originalGraphNodes = dfs(graphNode);

        for(Node k: originalGraphNodes) {
            print(k);
        }

        Node copiedGraph = deepCopy(graphNode);

        if(copiedGraph == graphNode) {
            System.err.println("Failed to make deep copy of graph");
            return;
        }

        System.out.println("Deep Copy: ");
        List<Node> nodes = dfs(copiedGraph);

        for(Node k: nodes) {
            print(k);
        }

    }

    static void print(GraphHelper.Node node) {
        System.out.println(node.val +"\t" + node + "\t neighbors: "+node.neighbors+"\t Values: "+node.neighbors.get(0).val+ ", "+node.neighbors.get(1).val);
    }

    static Node deepCopy(Node node) {
        Map<Integer, Node> nodeFactory = new HashMap<>();

        nodeFactory.computeIfAbsent(node.val, key -> new Node(node.val, new ArrayList<>()));

        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        bfs(visited, queue, nodeFactory);

        return nodeFactory.get(node.val);
    }

    static void bfs(Set<Integer> visited, Queue<Node> queue, Map<Integer, Node> nodeFactory) {
        while (!queue.isEmpty()) {
            Node d = queue.poll();
            for (Node neighbor : d.neighbors) {
                Node temp = nodeFactory.computeIfAbsent(neighbor.val, key -> new Node(neighbor.val, new ArrayList<>()));
                if (nodeFactory.get(d.val) != null) {
                    nodeFactory.get(d.val).neighbors.add(temp);
                }
                if (!visited.contains(neighbor.val) && !queue.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
            visited.add(d.val);
        }
    }

    static List<Node> dfs(Node node) {
        List<Node> nodes = new ArrayList<>();
        Set<Integer> visited = new LinkedHashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node d = stack.pop();
            nodes.add(d);
            for (Node neighbor : d.neighbors) {
                if (!visited.contains(neighbor.val) && !stack.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
            visited.add(d.val);
        }
        return nodes;
    }

}
