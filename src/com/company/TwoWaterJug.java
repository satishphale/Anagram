package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class TwoWaterJug {

    //function to find the gcd of the two numbers
    private int gcd(int m,int n)
    {
        if (n==0)
            return m;
        else
            return gcd(n,m%n);
    }

    //Function to find the minimum steps
    private String minimumSteps(int m, int n, int x)
    {
        //We cannot measure water greater than max of two elements
        if (x> Math.max(m,n))
            return "Water cannot measure";

        //if x mod gcd(m,n) is not equal to zero then we cannot measure
        if (x%gcd(n>m?n:m,n>m?m:n)!=0)
            return "Water cannot measure";
        String s1 = new String();
        String s2 = new String();

        //We starts pouring from left jug
        s1 = pourLeft(m,n,x);

        ////We starts pouring from right jug
        s2 = pourRight(n,m,x);

        //we are returning the length of smallest configurations
        return s1.length()<s2.length()?s1:s2;
    }

    //method to pour from left
    private String pourLeft(int n, int m, int x) {
        StringBuffer s = new StringBuffer();
        s.append("(0,0)");
        int from = n;
        int to = 0;
        s.append(" ("+from+","+to+") ");

        while(from != x && to != x)
        {
            //max amount of water to pour
            int max = Math.min(from,m-to);

            //pouring max amount of water
            to = to + max;

            //removing max amount of water
            from = from - max;

            //adding a configuration to the string
            s.append(" ("+from+","+to+") ");

            //if reached to the final state then we exit the loop
            if (from == x || to ==x)
                break;

            //if jug is empty then we are pouring with water
            if (from == 0) {
                from = n;
                s.append(" ("+from+","+to+") ");
            }

            //if jug is full then we are emptying it
            if (to == m)
            {
                to = 0;
                s.append(" ("+from+","+to+") ");
            }

        }

        return s.toString();
    }

    ////method to pour from left
    private String pourRight(int n, int m, int x) {
        StringBuffer s = new StringBuffer();
        s.append("(0,0)");
        int from = n;
        int to = 0;
        s.append(" ("+to+","+from+") ");

        while(from != x && to != x)
        {

            int max = Math.min(from,m-to);

            to = to + max;
            from = from - max;

            s.append(" ("+to+","+from+") ");

            if (from == x || to ==x)
                break;

            if (from == 0) {
                from = n;
                s.append(" ("+to+","+from+") ");
            }

            if (to == m)
            {
                to = 0;
                s.append(" ("+to+","+from+") ");
            }

        }

        return s.toString();
    }

    //Function to read file
    private String readAsString(String s) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(s)));
        return data;
    }

    public static void main(String args[]) throws IOException {

        TwoWaterJug tw = new TwoWaterJug();

        //Getting the current directory path
        String currentDirectory = System.getProperty("user.dir");

        String data = tw.readAsString(currentDirectory + "/data_two_water_jug.txt");

        //Splitting the test cases from input file using the delimitor
        String[] test_cases = data.split("\n@@@@@@@\n");

        //for every test case this code will run
        for (int i = 0; i < test_cases.length; i++) {
            System.out.println("Test case: "+(i+1));
            //creating set to find the unique vertices from the graph
            String[] input = test_cases[i].split(" ");

            //intializing the variables with values from input file
            //first jar capacity
            int m = Integer.parseInt(input[0]);
            //second jar capacity
            int n = Integer.parseInt(input[1]);
            //amount of water that we want to measure
            int x = Integer.parseInt(input[2]);

            System.out.println("Input: "+" m: "+m+" n: "+n+" x: "+x);

            //calling function to get configurations
            String configurations = tw.minimumSteps(m,n,x);
            System.out.println("Output: "+configurations);

        }
}
}
