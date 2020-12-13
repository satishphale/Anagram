package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DirectedPaths {

    //Declaring the data structures which we are using int the program
    //V : Number of vertices in the graph
    static int V;
    //visited[] : used in the DFS traversal
    static boolean[] visited;
    //vertices[] : Linked list to create the vertices
    static LinkedList<Integer> vertices[];
    //variable to store the count of paths
    static  int path_count;
    //List to store the paths from the source to destination
    static List<Integer> paths;

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

    //Function to find the paths from src to dst
    private List DFS_Parent(int src, int dst) {
        //Initializing the data structures
        paths = new ArrayList<>();
        visited = new boolean[V];
        //Adding source into the path
        paths.add(src);
        //Calling function to find path from src to dst
        DFS(src, dst);
        return paths;
    }

    //Function to find the paths from the src to dst
    private List<Integer> DFS(int src, int dst) {

        //if src ie equal to dst then ww print the path
        if(src == dst) {
            path_count++;
            System.out.println(paths);
            return paths;
        }


        visited[src] = true;
        //iterating a child of given node
        Iterator<Integer> itr = vertices[src].listIterator();
        while (itr.hasNext())
        {
            int n = itr.next();
            if(!visited[n])
            {
                //adding a vertex into the path
                paths.add(n);
                //recursively calling the dfs to find the path
                DFS(n,dst);
                int index = paths.indexOf(n);
                //remove element from path once we printed the path
                paths.remove(index);
            }
        }
        //mark as unvisited after printing the path
        visited[src] = false;
        return paths;
    }

    //Function to read file
    private String readAsString(String s) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(s)));
        return data;
    }


    public static  void main(String args[]) throws IOException {
        DirectedPaths p = new DirectedPaths();
        //Getting the current directory path
        String currentDirectory = System.getProperty("user.dir");

        String data = p.readAsString(currentDirectory+"/data_paths.txt");

        //Splitting the test cases from input file using the delimitor
        String[] test_cases = data.split("@@@@@@@\n");

        //for every test case this code will run
        for(int i = 0 ; i<test_cases.length;i++)
        {
            System.out.println("Test case: "+(i+1));
            //creating set to find the unique vertices from the graph
            Set s = new HashSet();
            String test_case = test_cases[i];
            String[] input = test_case.split("#\n");
            String[] edges = input[0].split("\n");
            for (int j=0;j<edges.length;j++) {
                String[] pair = edges[j].split(" ");
                int a = Integer.parseInt(pair[0]);
                int b = Integer.parseInt(pair[1]);
                s.add(a);
                s.add(b);
            }
            V = s.size();
            vertices = new LinkedList[V];
            //method to create linked list of vertices
            p.init();

            //Adding every edge from the input in to graph(linked list)
            for (int j=0;j<edges.length;j++) {
                String[] pair = edges[j].split(" ");
                int a = Integer.parseInt(pair[0]);
                int b = Integer.parseInt(pair[1]);
                p.addEdge(a,b);
            }

            //Getting and storing <src,dst> pair from input file
            String[] pair = input[1].split(" ");
            int src = Integer.parseInt(pair[0]);
            int dst = Integer.parseInt(pair[1]);

            //initializing the array to store the status of every vertex
            visited = new boolean[V];

             paths = new ArrayList<>();
             System.out.println("Paths from vertex: "+ src +" to "+dst+" are: ");
            path_count = 0;
            //calling the dfs function to print the paths
             List paths = p.DFS_Parent(src,dst);
             //if path_count is zero then there is no path
            if (path_count==0)
                System.out.println("No Path");
        }
}


}
