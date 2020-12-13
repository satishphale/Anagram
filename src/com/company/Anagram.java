package com.company;

import java.nio.file.*;
import java.util.*; 
import java.io.*;
class Anagram
{

public static String readFileAsString(String fileName)throws Exception 
  { 
    String data = ""; 
    data = new String(Files.readAllBytes(Paths.get(fileName))); 
    return data; 
  } 

public static void main(String args[]) throws Exception
{
	String currentDirectory = System.getProperty("user.dir");
	
	String data = readFileAsString(currentDirectory+"//data_anagram.txt"); 
	
	String test_data[] = data.split("\n");
		
		for(int i =0;i<test_data.length;i++)
		{

	String arr_data[] = test_data[i].split(" ");
	Set<String> distinct_string = new HashSet<String>();
	
	for(String str : arr_data)
	{
		//System.out.print(str.toString());
		char[] sorted_str = str.toCharArray();
		//System.out.print(sorted_str);
		Arrays.sort(sorted_str);
		//System.out.print(sorted_str);
		String str_new = new String(sorted_str);
		
		distinct_string.add(str_new);
		
	}
	
	
	for (String value : distinct_string);
            //System.out.print(value 
                             //+ ",@ ");
	System.out.println(distinct_string);
	System.out.println(distinct_string.size());


}
}
}
