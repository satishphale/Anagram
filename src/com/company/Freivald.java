package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

class Freivald
{
	public static int counts = 0;

	private static boolean isFreivald(int [][] a, int [][] b, int [][] c) {

		int N = b.length;
		int[] r =new int[N];
		//creating vector of 0/1
		for (int i = 0; i < N; i++) {
			Random rand = new Random(); //instance of random class
			int upperbound = 1000;
			//generate random values from 0-1000
			int int_random = rand.nextInt(upperbound);
			r[i] = int_random % 2;
		}


		int[] br = new int[N];
		Arrays.fill(br,0);

		//calculating br
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				br[i] = br[i] + b[i][j] * r[j];

		int[] cr = new int[N];
		Arrays.fill(cr,0);

		//calculating cr
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				cr[i] = cr[i] + c[i][j] * r[j];

		int[] axbr = new int[N];
		Arrays.fill(axbr,0);

		//calculating a * br
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				axbr[i] = axbr[i] + a[i][j] * br[j];

		//check whether all values of a * br - cr is equal to zero or not
		for (int i=0;i<N; i++)
			if (axbr[i]-cr[i] != 0) {
				//System.out.println("false");
				return false;
			}

		//System.out.println("true");

		return true;
	}

	//function for checking result is valid or not, if valid then add 1 to count
	public static boolean isValid(int[][] a,int[][] b,int[][] c,int k)
	{
		for (int i =0;i<k;i++)
		{
			if(isFreivald(a,b,c)==true)
				counts = counts + 1;
		}
		return true;
	}
	
	public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        //System.out.println(data);
        return data;
    }
	
	public static void main(String args[]) throws Exception {
		
		Freivald f = new Freivald();
		String currentDirectory = System.getProperty("user.dir");

		//method for reading data from file
		String data = f.readFileAsString(currentDirectory+"//data_freivald.txt");

		//String Array to store the input edges
		String arr_data[] = data.split("########################################################################################################################################################################\n");


		for(int i =0;i<arr_data.length;i++)
		{
		
			System.out.println("Test case: "+i);
			String test_case = arr_data[i];

			String[] matrices = test_case.split("@\n");




			String[] r= matrices[0].split("\n");
			int M = r.length;
			int N = r[0].split(" ").length;

			int a[][] = new int[M][N];
			int b[][] = new int[M][N];
			int c[][] = new int[M][N];

			for(int mat= 0;mat<matrices.length;mat++)
			{
				String[] rows = matrices[mat].split("\n");
				//int M = rows.length;
				//int N = rows[0].split(" ").length;

				//Storing values in a Matrix
				if (mat == 0)
					for (int row = 0; row < M; row++) {
						String[] columns = rows[row].split(" ");
						N = columns.length;
						for (int col = 0; col < N; col++) {
							a[row][col] = Integer.parseInt(columns[col]);
						}

					}
				//Storing values in b matrix
				else if(mat == 1)
					for (int row = 0; row < M; row++) {
						String[] columns = rows[row].split(" ");
						N = columns.length;
						for (int col = 0; col < N; col++) {
							b[row][col] = Integer.parseInt(columns[col]);
						}

					}
				else
					//Storing values in c matrix
					for (int row = 0; row < M; row++) {
						String[] columns = rows[row].split(" ");
						N = columns.length;
						for (int col = 0; col < N; col++) {
							c[row][col] = Integer.parseInt(columns[col]);
						}

					}
			}

			//Checking for 10 iterations
			int k = 10;
			double prob = 0.0000;


			boolean res = isValid(a, b, c, k);

			System.out.println("For K = 10");
			System.out.println("Number of times AB = C "+counts);
			int ne = k - counts;
			System.out.println("Number of times AB != C "+ ne);
			counts=0;

			System.out.println();

			//Checking for 100 iterations
			int k1 = 100;
			boolean res1 = isValid(a, b, c, k1);

			System.out.println("For K = 100");
			System.out.println("Number of times AB = C "+counts);
			int ne1 = k1 - counts;
			System.out.println("Number of times AB != C "+ ne1);
			counts=0;
			System.out.println();
			System.out.println();

		}
			
			
			
		
	}



}