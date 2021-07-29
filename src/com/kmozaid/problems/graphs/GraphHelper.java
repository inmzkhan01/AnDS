package com.kmozaid.problems.graphs;

import java.util.ArrayList;
import java.util.List;

public class GraphHelper {

    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static Node simpleConnectedGraph() {

        Node one = new Node(1, new ArrayList<>());
        Node two = new Node(2, new ArrayList<>());
        Node three = new Node(3, new ArrayList<>());
        Node four = new Node(4, new ArrayList<>());

        one.neighbors.add(two);
        one.neighbors.add(four);

        two.neighbors.add(one);
        two.neighbors.add(three);

        three.neighbors.add(two);
        three.neighbors.add(four);

        four.neighbors.add(one);
        four.neighbors.add(three);
        return one;
    }
}
