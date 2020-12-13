package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SinkVertices {
    //Declaring the data structures which we are using int the program
    //V : Number of vertices in the graph
    static int V;
    //vertices[] : Linked list to create the vertices
    static LinkedList<Integer> vertices[];


    //function to initialize the the linked list of given nodes
    void init()
    {
        vertices = new LinkedList[V];
        for (int i=0;i<V;i++)
        {
            vertices[i] = new LinkedList<>();
        }
    }

    //Function to add the edge into the graph
    void addEdge(int src,int dst)
    {
        vertices[src].add(dst);
    }

    //Function to compute Sink vertices
    private List<Integer> countSink() {
        //Array to store non sink vertices
        int non_sink[] =new int[V];
        List<Integer> sink_count = new ArrayList<>();
        for (int i = 0;i<V;i++)
        {
            Iterator itr = vertices[i].listIterator();
            //storing non-sink vertices
            if (itr.hasNext())
                non_sink[i] = 1;

        }

        for (int i=0;i<V;i++)
        {
            //collecting sink vertices
            if (non_sink[i]==0)
                sink_count.add(i);
        }

        return sink_count;
    }


    //Function to read file
    private String readAsString(String s) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(s)));
        return data;
    }


    public static void main(String args[]) throws IOException {
        SinkVertices sk = new SinkVertices();
        //Getting the current directory path
        String currentDirectory = System.getProperty("user.dir");

        String data = sk.readAsString(currentDirectory + "/data_sink.txt");

        //Splitting the test cases from input file using the delimitor
        String[] test_cases = data.split("@@@@@@@\n");

        //for every test case this code will run
        for (int i = 0; i < test_cases.length; i++) {
            System.out.println("Test case: "+(i+1));
            //creating set to find the unique vertices from the graph
            Set s = new HashSet();
            String test_case = test_cases[i];
            String[] edges = test_case.split("\n");
            for (int j = 0; j < edges.length; j++) {
                String[] pair = edges[j].split(" ");
                int a = Integer.parseInt(pair[0]);
                int b = Integer.parseInt(pair[1]);
                s.add(a);
                s.add(b);
            }
            V = s.size();
            vertices = new LinkedList[V];
            //method to create linked list of vertices
            sk.init();

            //Adding every edge from the input in to graph(linked list)
            for (int j = 0; j < edges.length; j++) {
                String[] pair = edges[j].split(" ");
                int a = Integer.parseInt(pair[0]);
                int b = Integer.parseInt(pair[1]);
                sk.addEdge(a, b);
            }

            //Calling function to get the list of sink vertices
            List<Integer> sink_count = sk.countSink();
            System.out.println("Sink Vertex/Vertices are: "+sink_count);

        }
    }


}
