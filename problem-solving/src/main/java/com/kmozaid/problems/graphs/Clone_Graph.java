package com.kmozaid.problems.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Clone_Graph {

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    static class BFS {

        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            UndirectedGraphNode copyNode = new UndirectedGraphNode(node.label);

            Map<UndirectedGraphNode, UndirectedGraphNode> originalCopyMap = new HashMap<>();
            originalCopyMap.put(node, copyNode);

            Queue<UndirectedGraphNode> queue = new LinkedList<>();
            queue.offer(node);

            while (!queue.isEmpty()) {
                UndirectedGraphNode original = queue.poll();
                UndirectedGraphNode copied = originalCopyMap.get(original);

                for (UndirectedGraphNode neighbor : original.neighbors) {
                    if (originalCopyMap.containsKey(neighbor)) {
                        copied.neighbors.add(originalCopyMap.get(neighbor));
                    } else {
                        UndirectedGraphNode clonedNeighbor = new UndirectedGraphNode(neighbor.label);
                        copied.neighbors.add(clonedNeighbor);

                        originalCopyMap.put(neighbor, clonedNeighbor);
                        queue.add(neighbor);
                    }
                }
            }

            return copyNode;
        }
    }

    static class DFS {

    }


}
