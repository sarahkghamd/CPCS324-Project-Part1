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

public class MHPrimAlg extends MSTAlgorithm {

    public void primMinHeap(Graph graph) {
        //start time
        double startTime = System.currentTimeMillis();
        boolean[] Heap = new boolean[graph.getVerticesNo()];
        Edge[] resultSet = new Edge[graph.getVerticesNo()];
        //keys[] used to check if min heap update is required
        int[] key = new int[graph.getVerticesNo()];
        //create heapNode for all the vertices
        heapNode[] heapNodes = new heapNode[graph.getVerticesNo()];
        for (int i = 0; i < graph.getVerticesNo(); i++) {
            heapNodes[i] = new heapNode();
            heapNodes[i].node = i;
            heapNodes[i].key = Integer.MAX_VALUE;
            resultSet[i] = new Edge();
            resultSet[i].setParent(new Vertex(-1));//
            Heap[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        //decrease the key for the first index
        heapNodes[0].key = 0;

        //add all the vertices to the MinHeap
        MinHeap minHeap = new MinHeap(graph.getVerticesNo());
        //add all the vertices to priority queue
        for (int i = 0; i < graph.getVerticesNo(); i++) {
            minHeap.insert(heapNodes[i]);
        }

        do {
            //extract the min
            heapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extractedNode.node;
            Heap[extractedVertex] = false;

            //iterate through all the adjacent vertices
            LinkedList<Edge> list = graph.getvertices()[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is present in heap
                if (Heap[edge.getDestination().getLabel()]) {
                    int destination = edge.getDestination().getLabel();
                    int newKey = edge.getWeight();
                    //check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        toDecreaseKey(minHeap, newKey, destination);
                        //update the parent node for destination
                        resultSet[destination].setParent(new Vertex(extractedVertex));
                        resultSet[destination].weight = newKey;
                        key[destination] = newKey;
                    }
                }
            }
        } while (!minHeap.isEmpty());
        //finish time of the algorithm
        double ftime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Prim's Algorithm (Using Min Heap): " + (ftime - startTime) + " ms.");
        //print mst

        displayResultingMST(resultSet, graph);
    }

//------------------------------------------------------------------------------
    /**
     * toDecreaseKey method used by prim's min-heap method
     *
     * @param mH the min-heap object
     * @param newKey the new node key
     * @param vertex vertex of the node
     */
    private void toDecreaseKey(MinHeap mH, int newKey, int vertex) {

        //get the index which key's needs a decrease;
        int index = mH.decreaseKey[vertex];

        //get the node and update its value
        heapNode node = mH.minHeap[index];
        node.key = newKey;
        mH.bubbleUp(index);
    }

    //-------------------------------class HeapNode---------------------------------
    /**
     * class HeapNode to have node objects in the Min-heap
     *
     */
    class heapNode {

        int node;

        int key;
    }

//------------------------class MinHeap has all methods needed------------------
    /**
     * class MinHeap each heap has capacity , size and is composed of nodes
     */
    class MinHeap {

        int capacity;

        int Size; //The current size

        heapNode[] minHeap;

        int[] decreaseKey; //to decrease the key
//---------------------------------MinHeap constructor--------------------------

        /**
         * MinHeap constructor
         *
         * @param capacity the max capacity of the heap
         */
        public MinHeap(int capacity) {
            this.capacity = capacity;
            minHeap = new heapNode[capacity + 1];
            decreaseKey = new int[capacity];
            minHeap[0] = new heapNode();
            minHeap[0].key = Integer.MIN_VALUE;
            minHeap[0].node = -1;
            Size = 0;
        }
//-------------------Method to insert heapNode----------------------------------

        /**
         * insert heapNode
         *
         * @param Node the node we want to insert
         */
        public void insert(heapNode Node) {
            Size++;
            int idx = Size;
            minHeap[idx] = Node;
            decreaseKey[Node.node] = idx;
            bubbleUp(idx);
        }
//-------------------------------To bubbleUp------------------------------------

        /**
         * bubbleUp method
         *
         * @param Position the position of the node ,a process to heapify
         */
        public void bubbleUp(int Position) {
            int parentIdx = Position / 2;
            int currentIdx = Position;
            while (currentIdx > 0 && minHeap[parentIdx].key > minHeap[currentIdx].key) {
                heapNode currentNode = minHeap[currentIdx];
                heapNode parentNode = minHeap[parentIdx];
                //to swap positions 
                decreaseKey[currentNode.node] = parentIdx;
                decreaseKey[parentNode.node] = currentIdx;
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx / 2;
            }
        }
//-------------------------------To exttract the min node-----------------------

        /**
         * get the min node of the heap
         *
         * @return the min node
         */
        public heapNode extractMin() {
            heapNode min = minHeap[1];
            heapNode lastNode = minHeap[Size];
            //to put the last node at the top and update the decreaseKey[]
            decreaseKey[lastNode.node] = 1;
            minHeap[1] = lastNode;
            minHeap[Size] = null;
            sinkDown(1);
            Size--;
            return min;
        }
//-------------------------------To sink down the min heap---------------------

        /**
         * sink down the min heap ,a process to heapify
         *
         * @param T node position
         */
        public void sinkDown(int T) {
            int theSmallest = T;
            int leftChild = 2 * T;
            int rightChild = 2 * T + 1;
            if (leftChild < heapSize() && minHeap[theSmallest].key
                    > minHeap[leftChild].key) {
                theSmallest = leftChild;
            }
            if (rightChild < heapSize() && minHeap[theSmallest].key
                    > minHeap[rightChild].key) {
                theSmallest = rightChild;
            }
            if (theSmallest != T) {

                heapNode smallestNode = minHeap[theSmallest];
                heapNode TNode = minHeap[T];

                //swap the positions
                decreaseKey[smallestNode.node] = T;
                decreaseKey[TNode.node] = theSmallest;
                swap(T, theSmallest);
                sinkDown(theSmallest);
            }
        }
//-------------------------------To Check if heap is empty----------------------

        /**
         * Check if heap is empty
         *
         * @return boolean true or false
         */
        public boolean isEmpty() {
            return Size == 0;
        }
//-------------------------------To get the min Heap Size-----------------------

        /**
         * Get the Min-Heap Size
         *
         * @return integer
         */
        public int heapSize() {
            return Size;
        }
//------------------------------- swap method to use it in sinkDown-------------

        /**
         * Swap method used in sinkDown
         *
         * @param x first node
         * @param y second node
         */
        public void swap(int x, int y) {
            heapNode temp = minHeap[x];
            minHeap[x] = minHeap[y];
            minHeap[y] = temp;
        }
//------------------------------- To display min heap node----------------------

        /**
         * Display min heap nodes
         */
        public void display() {
            int i = 0;
            while (i <= Size) {
                System.out.println(" " + minHeap[i++].node
                        + "   key   " + minHeap[i].key);
            }
            System.out.println("--------------------------------");
        }

    }
}
