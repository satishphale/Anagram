package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Queue;

class MatrixBlocks
{

	//used in the queue operation(enqueue and dequeue) of each element
	//x:row number of element
	//y:column number of element
	class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// Below arrays details all 8 possible movements from a cell
	// (top, right, bottom, left and 4 diagonal moves)
	private static final int[] row = { -1, -1, -1, 0, 1, 0, 1, 1 };
	private static final int[] col = { -1, 1, 0, -1, -1, 1, 0, 1 };

	// Function to check if it is safe to go to position (x, y)
	// from current position. The function returns false if (x, y)
	// is not valid matrix coordinates
	public static boolean isSafe(int[][] mat, int x, int y,
							 boolean[][] processed)
	{
		return (x >= 0) && (x < processed.length) &&
				(y >= 0) && (y < processed[0].length) &&
				(mat[x][y] == 1 && !processed[x][y]);
	}

	public void BFS(int[][] mat, boolean[][] processed, int i, int j)
	{
		// create an empty queue and enqueue source node
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(i, j));

		// mark source node as processed
		processed[i][j] = true;

		// loop till queue is empty
		while (!q.isEmpty())
		{
			// pop front node from queue and process it
			int x = q.peek().x;
			int y = q.peek().y;
			q.poll();

			// check for all 8 possible movements from current cell
			// and enqueue each valid movement
			for (int k = 0; k < 8; k++)
			{
				// Skip if location is invalid or already processed
				if (isSafe(mat, x + row[k], y + col[k], processed))
				{
					processed[x + row[k]][y + col[k]] = true;
					q.add(new Pair(x + row[k], y + col[k]));
				}
			}
		}
	}

	public static String readFileAsString(String fileName)throws Exception
	{
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

	public static void main(String[] args) throws Exception {
		MatrixBlocks m = new MatrixBlocks();

		String currentDirectory = System.getProperty("user.dir");

		//method for reading data from file
		String data = m.readFileAsString(currentDirectory+"//data_mat.txt");

		//String Array to store the input edges
		String arr_data[] = data.split("@");
		int M=0;
		int N=0;
		//int[][] mat = new int[M][];

		for(int i =0;i<arr_data.length;i++)
		{
			String test_case = arr_data[i];

			String[] row = test_case.split("\n");
			//Number of rows in the input matrix
			M= row.length;
			//Number of rows in the input matrix
			N= row[0].split(" ").length;
			int[][] mat = new int[M][N];

			//Storing each input values into the matrix
			for(int j=0;j<M;j++)
			{
				String[] column = row[j].split(" ");
				N = column.length;
				for(int k=0;k<N;k++)
				{
					mat[j][k] = Integer.parseInt(column[k]);
				}

			}

			//displying the matrix on the console
			for(int k=0 ;k<M;k++)
			{
				for(int j=0;j<N;j++)
				{
					System.out.print(mat[k][j]);
				}
				System.out.println();

			}

			//2-D array to store the status of each node. whether it is
			//processed previously or not.
			boolean[][] processed = new boolean[M][N];

			int blocks = 0;
			for (int k = 0; k < M; k++)
			{
				for (int j = 0; j < N; j++)
				{
					// start BFS from each unprocessed node and
					// increment island count
					if (mat[k][j] == 1 && !processed[k][j])
					{
						m.BFS(mat, processed, k, j);
						blocks++;
					}
				}
			}

			System.out.print("Number of blocks are " + blocks);
			System.out.println();

		}


	}
}