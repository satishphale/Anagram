package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class NewEulerianCycle {
    static int V;
    static LinkedList<Integer>[] vertices;


    public static void main() throws IOException {
        NewEulerianCycle e = new NewEulerianCycle();
        //Getting the current directory path
        String currentDirectory = System.getProperty("user.dir");

        String data = e.readAsString(currentDirectory + "/data_graph.txt");

        //Splitting the test cases from input file using the delimitor
        String[] test_cases = data.split("@@@@@@@\n");

        //for every test case this code will run
        for (int i = 0; i < test_cases.length; i++) {
            System.out.println("Test case: " + (i + 1));
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
            e.init();

            //Adding every edge from the input in to graph(linked list)
            for (int j = 0; j < edges.length; j++) {
                String[] pair = edges[j].split(" ");
                int a = Integer.parseInt(pair[0]);
                int b = Integer.parseInt(pair[1]);
                e.addEdge(a, b);
                e.addEdge(b, a);
            }

            System.out.println(isConnected());


        }
    }

    private String readAsString(String s) throws IOException {
        String output_str = new String(Files.readAllBytes(Paths.get(s)));
        return output_str;
    }

    private void addEdge(int a, int b) {
        vertices[a].add(b);
    }

    private void init() {
        for (int i = 0; i < V; i++) {
            vertices[i] = new LinkedList<>();
        }
    }

    private static boolean isConnected() {
        boolean[] visited = new boolean[V];
        DFS(0, vertices[0], visited);
        for (int i = 0; i < visited.length; i++)
            if (visited[i] == false)
                return false;
        return true;
    }

    private static void DFS(int source, LinkedList<Integer> list, boolean[] visited) {
        visited[source] = true;
        for (int i = 0; i < vertices[source].size(); i++) {
            int neighbor = vertices[source].get(i);
            DFS(neighbor, list, visited);
        }
    }




}
