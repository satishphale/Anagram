package com.company;

import java.util.Scanner;

class ValidParen
{
	//recursive function for the generation of parenthesis
	static void printParen(char str[], int pos, int n, int open, int close)
	{
		//if closing parenthesis is equal to the value of n then we print the string
		if(close == n)
		{
			for(int i=0;i<str.length;i++) 
				System.out.print(str[i]); 
			System.out.println();
			return; 
		} 
		else
		{
			//Adding closing parenthesis to the string
			if(open > close) { 
				str[pos] = ')';
				printParen(str, pos+1, n, open, close+1);

			} 
			//Adding opening parenthesis to string
			if(open < n) {
				str[pos] = '(';
				printParen(str, pos+1, n, open+1, close);

			} 
		} 
	} 

	//method to find the valid parenthesis
	static void Parenthesis(char str[], int n)
	{ 
		if(n > 0) 
		printParen(str, 0, n, 0, 0);
		return; 
	} 

	public static void main (String[] args) 
	{
		System.out.println("Enter the value of n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		//Creating a string of length 2n
		char[] str = new char[2 * n]; 
		Parenthesis(str, n);
	} 
} 
