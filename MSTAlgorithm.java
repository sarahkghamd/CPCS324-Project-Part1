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

public class MSTAlgorithm {

    LinkedList<Edge>[] MSTResultList; //stores the parent of the vertex and the weight needed by the MST algorithm
    //Graph graph;

    /**
     * print Graph method
     *
     * @param edgeList list of the edges in the graph
     */
    public void displayResultingMST(LinkedList<Edge> edgeList) {
        int cost = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            cost += edge.getWeight();
        }
        System.out.println("Minimum Spanning Tree Cost = " + cost);
    }

    public void displayResultingMST(Edge[] resultSet, Graph graph) {
        int totalWeight = 0, i = 0;

        while (i < graph.getVerticesNo()) {
            totalWeight += resultSet[i++].weight;

        }
        System.out.println("Minimum Spanning Tree Cost= " + totalWeight);
    }

}
