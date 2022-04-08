/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sarah
 */

import java.util.*;

public class PQPrimAlg extends MSTAlgorithm {

    void PQ_Prim(Graph graph) {
        double startTime = System.currentTimeMillis();
        // Comparator lambda function that enables the priority queue to store the nodes
        // based on the cost in the ascending order.
        Comparator<Edge> NodeCostComparator = (obj1, obj2) -> {
            return obj1.getWeight() - obj2.getWeight();
        };

        // Priority queue stores the object node-cost into the queue with 
        // the smallest cost node at the top.
        PriorityQueue<Edge> pq = new PriorityQueue<>(NodeCostComparator);

        // The cost of the source node to itself is 0
        Vertex v = new Vertex();
        v.setLabel(0);
        pq.add(new Edge(v, 0));

        boolean added[] = new boolean[graph.getVerticesNo()];
        Arrays.fill(added, false);

        int mst_cost = 0;

        while (!pq.isEmpty()) {

            // Select the item <node, cost> with minimum cost
            Edge item = pq.peek();
            pq.remove();

            int node = item.getDestination().getLabel();
            int cost = item.getWeight();

            // If the node is node not yet added to the minimum spanning tree, add it and increment the cost.
            if (!added[node]) {
                mst_cost += cost;
                added[node] = true;

                // Iterate through all the nodes adjacent to the node taken out of priority queue.
                // Push only those nodes (node, cost) that are not yet present in the minumum spanning tree.
                for (Edge pair_node_cost : graph.getvertices()[node]) {
                    int adj_node = pair_node_cost.getDestination().getLabel();
                    if (added[adj_node] == false) {
                        pq.add(pair_node_cost);
                    }
                }
            }
        }
        //finish time of the algorithm
        double ftime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Prim's Algorithm (Using Priority Queue): " + (ftime - startTime) + " ms.");
        System.out.println("Minimum Spanning Tree Cost= " + mst_cost);
        System.out.println("------------------------------------------------------");
    }
}
