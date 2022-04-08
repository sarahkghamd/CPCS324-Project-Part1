
import java.util.ArrayList;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sarah
 */
public class Vertex {

    int label; //a number that represents the vertex label
    boolean isVisited; //set to true if the current vertex is visited within a certain operation like graph traversal.
    ArrayList<Edge> adjList; //represents the adjacency list of a vertex within a class.

    public Vertex(int label) {
        this.label = label;
        this.isVisited = false;

    }

    public Vertex() {
        adjList = new ArrayList<Edge>();

    }

    public int getLabel() {
        return label;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public ArrayList<Edge> getAdjList() {
        return adjList;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setAdjList(ArrayList<Edge> adjList) {
        this.adjList = adjList;
    }

}
