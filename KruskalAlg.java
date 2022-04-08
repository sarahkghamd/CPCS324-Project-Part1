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

public class KruskalAlg extends MSTAlgorithm {

    public void kruskalMST(Graph graph) {
        //start time
        double startTime = System.currentTimeMillis();
        // to used in tracing
        String treeV = "";
        // change data type from ArrayList to LinkedList
        LinkedList<Edge>[] Edges = graph.getvertices().clone();
        PriorityQueue<Edge> pq = new PriorityQueue<>(graph.getEdgeNo(), Comparator.comparingInt(o -> o.getWeight()));

        //priority queue holds  all the edges
        //sort the edges by its weights
        for (LinkedList<Edge> allEdge : Edges) {
            for (int j = 0; j < allEdge.size(); j++) {
                pq.add(allEdge.get(j));
            }
        }
        /* System.out.println("\nSorted list of Edges:");
            for (Edge edge : pq) {
            System.out.println(edge.toString());
            }*/

        //create a parent []
        int[] parent = new int[graph.getVerticesNo()];

        //makeset
        makeSet(parent, graph);

        LinkedList<Edge> mst = new LinkedList<>();

        //process vertices - 1 edges
        int index = 0;
        while (index < graph.getVerticesNo() - 1 && !pq.isEmpty()) {
            Edge edge = pq.remove();
            //check if adding this edge creates a cycle
            int x_set = findParent(parent, edge.getSource().getLabel());
            int y_set = findParent(parent, edge.getDestination().getLabel());

            if (x_set == y_set) {
                //ignore, will create cycle
            } else {
                //add it to our final result
                mst.add(edge);
                treeV += edge.toString() + "\n";
                // System.out.println("\n Tree Vertex:");
                //  System.out.println(treeV);
                index++;
                union(parent, x_set, y_set);
            }

        }

        //finish time of the algorithm
        double ftime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Kruskal's Algorithm: " + (ftime - startTime) + " ms.");

        displayResultingMST(mst);
    }

    /**
     * makeSet method used in Kruskal's
     *
     * @param parent
     */
    private void makeSet(int[] parent, Graph graph) {
        //Make set: creating a new element with a parent pointer to itself.
        for (int i = 0; i < graph.getVerticesNo(); i++) {
            parent[i] = i;
        }
    }

    /**
     * union method used by Kruskal's
     *
     * @param parent
     * @param x first node
     * @param y second node
     */
    private void union(int[] parent, int x, int y) {
        int setXparent = findParent(parent, x);
        int setYparent = findParent(parent, y);
        //make x as parent of y
        parent[setYparent] = setXparent;
    }

    /**
     * findParent method used by union method
     *
     * @param parent parent list
     * @param vertex the vertex
     * @return
     */
    private int findParent(int[] parent, int vertex) {
        //chain of parent pointers from x upwards through the tree
        // until an element is reached whose parent is itself (ROOT)
        if (parent[vertex] == vertex) {
            return vertex;
        }
        return findParent(parent, parent[vertex]);
    }

}
