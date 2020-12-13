package com.company;
//importing the required packages
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class LargestPath {

    //Declaring the data structures which we are using int the program
    //V : Number of vertices in the graph
    static int V;
    //queue: used in the BFS Traversal
    Queue<Integer> queue = new LinkedList<>();
    //visited[] : used in the BFS traversal
    static boolean[] visited;
    //vertices[] : Linked list to create the vertices
    static LinkedList<Integer> vertices[];
    //dis[] : weights array used in finding the longest path
    static int[] dis;


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


     //BFS traversal Function
     private String getEndpoint(int i,boolean b2) {

        //Add root element into the queue
        queue.add(i);

        //initializing the weights array in the second BFS Traversal
        if(b2==true) {
            dis = new int[V];
            // mark all distance with -1
            Arrays.fill(dis, -1);
            dis[i] = 0;
        }
        visited[i] = true;
        StringBuffer str= new StringBuffer();

        //this code will run while queue is not empty
        while (!queue.isEmpty()) {
            int element = queue.poll();
            str.append(Integer.toString(element));
            str.append(" ");

            //Iterate over all child of popped element from queue
            Iterator<Integer> itr = vertices[element].listIterator();
            while (itr.hasNext()) {
                int n = itr.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                    //update the weight in dis array
                    if(b2==true)
                        dis[n] = dis[element] + 1;
                }
            }
        }

      //returning the BFS Traversal string
        return str.toString();
    }

    //Function to print the path
    private String printPath(int index,int weight)//0,9
    {
        int num = dis[index];
        if (num!=-1){
            int new_weight = weight-1;
            Iterator<Integer> itr = vertices[index].listIterator();
            int n=0;

            //Checking for all child of given node
            while (itr.hasNext()) {
                n = itr.next();

                //if current weight == (previous_weight-1) then we recursively calling the printPath function
                if (dis[n]==new_weight) {
                    printPath(n,dis[n]);
                    System.out.print(n+"->");
                }

            }
        }
        return "";
    }

    //Function to read file
    private String readAsString(String s) throws IOException {
    String data = new String(Files.readAllBytes(Paths.get(s)));
    return data;
    }


    public static void main(String[] args) throws IOException {
        LargestPath l = new LargestPath();
        //Getting the current directory path
        String currentDirectory = System.getProperty("user.dir");

        String data = l.readAsString(currentDirectory+"/data_graph.txt");

        //Splitting the test cases from input file using the delimitor
        String[] test_cases = data.split("@@@@@@@\n");

        //for every test case this code will run
        for(int i = 0 ; i<test_cases.length;i++)
        {
            System.out.println("Test case: "+(i+1));
            //creating set to find the unique vertices from the graph
            Set s = new HashSet();
            String test_case = test_cases[i];
            String[] edges = test_case.split("\n");
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
            l.init();

            //Adding every edge from the input in to graph(linked list)
            for (int j=0;j<edges.length;j++) {
                String[] pair = edges[j].split(" ");
                int a = Integer.parseInt(pair[0]);
                int b = Integer.parseInt(pair[1]);
                l.addEdge(a,b);
                l.addEdge(b,a);
            }

            //initializing the array to store the status of every vertex
            visited = new boolean[V];

            //creating a random element from given vertices list for Source of BFS traversal
            Random random = new Random();
            int source =  random.nextInt(V-1);
            System.out.print("Starting point of graph traversal is: "+source+"\n");

            //Calling a BFS traversal from the random start vertex
            String endpoint = l.getEndpoint(source,false);

            //resetting the visited array for second BFS traversal
            visited = new boolean[V];

            //Getting the last element of first BFS traversal
            String[] bfs = endpoint.split(" ");
            System.out.println("One of endpoint of path is "+Integer.parseInt(bfs[V-1]));

            //Calling the second BFS with the start vertex as the last vertex of first BFS
            String longest = l.getEndpoint(Integer.parseInt(bfs[V-1]),true);

            //Finding the maximum weight and index of weight
            int max=0,index=0;
            for (int i2=0;i2<dis.length;i2++) {
                if (dis[i2]>max){
                    max = dis[i2];
                    index = i2;
                }
            }
            System.out.print("Maximum nodes in the path are: "+(max+1));
            System.out.println();
            System.out.print("Path is as follows: ");

            //Calling printPath function to print the largest path from maximum weight
            l.printPath(index,max);
            //Printing the last element in the path
            System.out.print(index+"\n\n");

        }

    }


}
