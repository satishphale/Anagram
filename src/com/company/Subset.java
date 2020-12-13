package com.company;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Subset {


    //method to read from file
    private String readAsString(String directory) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(directory)));
        return data;
    }

    public static void main(String args[]) throws IOException {

        Subset sb = new Subset();

        //Getting the current directory path
        String currentDirectory = System.getProperty("user.dir");

        String data = sb.readAsString(currentDirectory + "/data_subset.txt");

        //Splitting the test cases from input file using the delimitor
        String[] test_cases = data.split("\n");

        //for every test case this code will run
        for (int i1 = 0; i1 < test_cases.length; i1++) {
            System.out.println("Test case: " + (i1 + 1));
            String[] input_line = test_cases[i1].split(" ");

            String[] input = input_line[0].split(",");
            int contains = Integer.parseInt(input_line[1]);
            System.out.println("Subsets which contains "+contains+" are:");

            int[] S = new int[input.length];

            for (int d = 0; d < input.length; d++)
                S[d] = Integer.parseInt(input[d]);


            int[] subset = new int[S.length];

            //method to get the subsets
            List<List<Integer>> subsets = sb.subsets(S,contains);


            for (List<Integer> subs : subsets) {
                System.out.println(subs);
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> subsets(int[] nums,int contains) {
        List<List<Integer>> list = new ArrayList<>();
        getSubsets(list, new ArrayList<>(), nums, 0,contains);
        return list;
    }


    //recursive function to store the subsets
    private static void getSubsets(List<List<Integer>> list, List<Integer> resultlist, int[] nums,int index,int contain)
    {
        //if the element is present then only we are adding into the output list
        if (resultlist.contains(contain)) {
            list.add(new ArrayList<Integer>(resultlist));
        }

        for (int i = index; i<nums.length;i++)
        {
            resultlist.add(nums[i]);
            // recursively calling the function to get subsets
            getSubsets(list,resultlist,nums,i+1,contain);
            resultlist.remove(resultlist.size()-1);
        }

    }
}
