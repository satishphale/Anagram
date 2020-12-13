import  java.util.*;
import java.nio.file.*;

public class BipartiteGraph
{


	//public Queue<Integer> queue;

	public Queue<Integer> queue = new LinkedList<Integer>();


	public static final int NO_COLOR = 0;
	public static final int RED = 1;
	public static final int BLUE = 2;

	public boolean isBipartite(int adjacencyMatrix[][], int numberOfVertices)
	{
		int[] colored = new int[numberOfVertices ];
		for (int vertex = 0; vertex < numberOfVertices; vertex++)
		{
			colored[vertex] = NO_COLOR;
		}

		//Selecting source element for traversal
		int source = 1;
		colored[source] = RED;
		queue.offer(source);

		int element, neighbour;
		while (!queue.isEmpty())
		{
			element = queue.remove();
			neighbour = 0;
			while (neighbour < numberOfVertices)
			{
				//if adjacent vertices have same color then return false
				if (adjacencyMatrix[element][neighbour] == 1 && colored[element]== colored[neighbour])
				{
					return false;
				}
				//if edge is present and not colored
				if (adjacencyMatrix[element][neighbour] == 1 && colored[neighbour]== NO_COLOR)
				{
					colored[neighbour] = (colored[element] == RED ) ? BLUE :RED;
					queue.add(neighbour);
				}
				neighbour++;
			}
		}
		return true;
	}


	//method for reading data from file
	public static String readFileAsString(String fileName)throws Exception
	{
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

	public static void main(String args[]) throws Exception
	{
		String currentDirectory = System.getProperty("user.dir");

		String data = readFileAsString(currentDirectory+"//data_graph.txt");

		String test_data[] = data.split("@");
		int M=0;
		int N=0;
		//int[][] mat = new int[M][];

		for(int t =0;t<test_data.length;t++)
		{
		//String Array to store the input edges
		String arr_data[] = test_data[t].split("\n");

		//Declared a Set to get unique vertices from the input edges
		Set<Integer> distinct_string = new HashSet<Integer>();

		//Storing unique vertex number in a set
		for(int i =0 ; i<arr_data.length;i++)
		{
			String pair[] = arr_data[i].split(" ");
			distinct_string.add(Integer.parseInt(pair[0]) );
			//System.out.println(Integer.parseInt(pair[0]) );
			distinct_string.add(Integer.parseInt(pair[1]) );
			//System.out.println(Integer.parseInt(pair[1]) );
		}

		System.out.println("Number of vertices are: ");
		System.out.println(distinct_string.size());
		System.out.println(distinct_string);

		//Size of a set is a total number of vertex present in the given graph
		int vertices = distinct_string.size();


		//Adjacency matrix to store the edges
		int[][] adj_matrix = new int[vertices][vertices];


		//Declared map to store the index of the vertex number(n)
		Map<Integer, Integer> count = new HashMap();
		int index = 0;
		for (int n: distinct_string) {
			count.put(n, index);
			index = index+1;
		}


		//Assigning values to the adjacency matrix for the given edges
		for(int i =0 ; i<arr_data.length;i++)
		{
			String pair[] = arr_data[i].split(" ");
			int j = Integer.parseInt(pair[0]) ;
			int k = Integer.parseInt(pair[1]) ;

			j = count.get(j);
			k = count.get(k);

			adj_matrix[j][k]=1;
			adj_matrix[k][j]=1;
		}



		for(int j=0;j<vertices;j++)
		{
			for(int k=0;k<vertices;k++)
			{
				System.out.print(adj_matrix[j][k]);
			}
			System.out.println();
		}


		BipartiteGraph bipartiteBfs = new BipartiteGraph();

		//method for checking whether is bipartite or not
			if(bipartiteBfs.isBipartite(adj_matrix,vertices))
				System.out.println("Given Graph is Bipartite");
			else
				System.out.println("Given Graph is Not Bipartite");




	}
	}
}