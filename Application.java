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

public class Application {

    static Scanner in = new Scanner(System.in);

    public static void main(String args[]) {

        System.out.println("\t Compare Different Minimum Spanning Tree Algorithms \n");
        System.out.println("Compare between:");
        System.out.println("\t1- Kruskal's Algorithm & Prim's Algorithm (based on Priority Queue)\n"
                + "\t2- Prim's Algorithm (based on Min Heap)& Prim's Algorithm(based on Priority Queue)");
        System.out.print("Enter your choice (1 or 2): ");
        int choice1 = in.nextInt();
        System.out.println("Test  cases : (where n is the number of vertices "
                + "and m is the number of edges: ");
        System.out.println(" 1:  n=1,000 ,  m=10,000");
        System.out.println(" 2:  n=1,000 ,  m=15,000");
        System.out.println(" 3:  n=1,000 ,  m=25,000");
        System.out.println(" 4:  n=5,000 ,  m=15,000");
        System.out.println(" 5:  n=5,000 ,  m=25,000");
        System.out.println(" 6:  n=10,000 , m=15,000");
        System.out.println(" 7:  n=10,000 , m=25,000");
        System.out.println(" 8:  n=20,000 , m=200,000");
        System.out.println(" 9:  n=20,000 , m=300,000");
        System.out.println("10:  n=50,000 , m=1,000,000");
        System.out.print("Enter your choice: ");
        int choice2 = in.nextInt();

        switch (choice2) {
            case 1:
                createGraph(1000, 10000, choice1);
                break;
            case 2:
                createGraph(1000, 15000, choice1);
                break;
            case 3:
                createGraph(1000, 25000, choice1);
                break;
            case 4:
                createGraph(5000, 15000, choice1);
                break;
            case 5:
                createGraph(5000, 25000, choice1);
                break;
            case 6:
                createGraph(10000, 15000, choice1);
                break;
            case 7:
                createGraph(10000, 25000, choice1);
                break;
            case 8:
                createGraph(20000, 200000, choice1);
                break;
            case 9:
                createGraph(20000, 300000, choice1);
                break;
            case 10:
                createGraph(50000, 1000000, choice1);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }

    }

    public static void createGraph(int vertNum, int EdNum, int choice) {
        Graph graph = new Graph(vertNum, EdNum);
        System.out.println("Is the graph directed?\n Enter (true) or (false) ");
        String isdi = in.next();
        if (isdi.equalsIgnoreCase("true")) {
            System.out.println(" Prim’s and Kruskal’s  MST Algorithms don't work on directed graphs");
            System.out.println(" Would you like to quit(enter quit) ,or continue considering the graph is undirected (enter cont) ");
            String contOrQuit = in.next();
            if (contOrQuit.equalsIgnoreCase("quit")) {
                System.exit(0);
            }

        } else {
            graph.make_graph(graph);
        }
        KruskalAlg x = new KruskalAlg();
        PQPrimAlg y = new PQPrimAlg();
        MHPrimAlg z = new MHPrimAlg();

        switch (choice) {

            case 1:
                x.kruskalMST(graph);
                y.PQ_Prim(graph);

                break;
            case 2:
                y.PQ_Prim(graph);
                z.primMinHeap(graph);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }

    }

}
    
    

