package com.company;

import java.nio.file.*;
import java.util.*;
import java.io.*;
class NotAnagram
{



    public static void topKFrequent(int[] nums, int k) throws IOException {
        // O(1) time
        //if (k == nums.length) {
        //return nums;
        //}

        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Map.Entry<Integer,Integer>> heap = new PriorityQueue<>(
                (n1, n2) -> n1.getValue().equals(n2.getValue())?Integer.compare(n2.getKey(),n1.getKey()):Integer.compare(n2.getValue(),n1.getValue()));


        for(Map.Entry<Integer,Integer> entry:count.entrySet())
        {
            heap.add(entry);
        }

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        String currentDirectory = System.getProperty("user.dir");

        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(currentDirectory+"//data_output.txt"));
        for (int i=0;i<k;i++) {
            //heap.add(n);
            //if (heap.size() > k) heap.poll();

            System.out.println(heap.poll().getKey());
            outputWriter.write(heap.poll().getKey()+" ");

        }

        outputWriter.flush();

        outputWriter.close();

        // 3. build an output array
        // O(k log k) time
        //int[] top = new int[k];

        //if(k<=heap.size()){
        //for(int i = k - 1; i >= 0; --i) {
        //   top[i] = heap.poll();
        // }}
        //e/lse
        //{
        //System.out.println("Not sufficient frequent elements");
        //return new int[1];
        //}
        //return top;
    }

    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        System.out.println(data);
        return data;
    }




    public static void main(String[] args) throws Exception
    {


        String currentDirectory = System.getProperty("user.dir");

        String data = readFileAsString(currentDirectory+"//data.txt");


        String arr_data[] = data.split(" ");

        //int arr[] = new int[arr_data[0].length()];


        String str_arr[] = arr_data[0].split("");

        int num_arr[] = new int[str_arr.length];


        for(int i=0;i<num_arr.length;i++)
        {
            num_arr[i] = Integer.parseInt(str_arr[i]);

        }

        String abc = arr_data[1];
        //System.out.println("@@@@@@@@@@@@");
        //int k = Integer.parseInt(arr_data[1]);
        //System.out.println(abc;
        //System.out.println("@@@@@@@@@@@@");
        //int top[] =
        topKFrequent(num_arr, Integer.parseInt(abc));

        //for(int i=0;i<top.length;i++)
        //System.out.print(top[i]);



    }


}
	