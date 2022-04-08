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

public class Graph {

    int verticesNo;//number of vertices of the graph
    int edgeNo;//number of edges of the graph
    boolean isDigraph;
    LinkedList<Edge>[] vertices;//representing the list of vertices of a graph

    Graph(int verticesNo, int edgeNo) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        vertices = new LinkedList[verticesNo];
        //initialize adjacency lists for all the verts
        for (int i = 0; i < verticesNo; i++) {
            vertices[i] = new LinkedList<>();
        }
    }

    public Graph(int verticesNo, int edgeNo, boolean isDigraph, LinkedList<Edge>[] vertices) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        this.isDigraph = isDigraph;
        this.vertices = vertices;

    }

    public int getVerticesNo() {
        return verticesNo;
    }

    public LinkedList<Edge>[] getvertices() {
        return vertices;
    }

    public int getEdgeNo() {
        return edgeNo;
    }

    public void addEdge(Vertex source, Vertex destination, int weight) {//source: v, destination(target): u, vertex weight: w
        Edge edge = new Edge(source, destination, weight);
        vertices[source.getLabel()].addFirst(edge);

        edge = new Edge(destination, source, weight);
        vertices[destination.getLabel()].addFirst(edge); //for undirected graph

    }

    public void make_graph(Graph graph) {
        // instance of Random class
        Random random = new Random();
        // ensure that all vertices are connected
        for (int i = 0; i < verticesNo - 1; i++) {
            int RandomNum = random.nextInt(10) + 1;
            Vertex sourceV = new Vertex(i);
            sourceV.setLabel(i);
            Vertex destinationV = new Vertex(i + 1);
            addEdge(sourceV, destinationV, RandomNum);

        }
        int remaning = edgeNo - (verticesNo - 1);

        for (int i = 0; i < remaning; i++) {
            Vertex source = new Vertex(random.nextInt(graph.verticesNo));
            Vertex Destination = new Vertex(random.nextInt(graph.verticesNo));
            if (Destination.getLabel() == source.getLabel() || isConnected(source.getLabel(), Destination.getLabel(), graph.vertices)) { // to avoid self loops and duplicate edges
                i--;
                continue;
            }
            // generate random weights in range 0 to 20
            int weight = random.nextInt(20) + 1;
            // add edge to the graph
            addEdge(source, Destination, weight);

        }
    }

    public boolean isConnected(int Source, int Destination, LinkedList<Edge>[] allEdges) {
        for (LinkedList<Edge> i : allEdges) {
            for (Edge edge : i) {
                if ((edge.source.getLabel() == Source && edge.destination.getLabel() == Destination) || (edge.source.getLabel() == Destination && edge.destination.getLabel() == Source)) {
                    return true;
                }
            }
        }
        return false;
    }

}
