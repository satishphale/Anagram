package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class LinkedListOperation {

    //Class to create a node in the linked list
    private class Node {
        int data;
        Node next;

        Node() {
            this.data = 0;
            this.next = null;
        }

        Node(int data) {
            this.data = data;
            this.next = null;
        }

    }

    //method to insert the nodes in the sorted order
    private Node insert(Node root, int num) {
        Node tmp = root;

        Node newNode = new Node(num);
        if (tmp==null) {
            root = newNode;
            printList(root);
            return root;
        }
        else{
            if (root.data>newNode.data) {
                newNode.next = root;
                tmp =newNode;
            }
            else{

                Node tmp1=null;
            while (root.data<=newNode.data && root.next != null) {
                tmp1 = root;
                root = root.next;
            }
            if (root.data<=newNode.data && root.next == null)
            {
                tmp1 = root;
                root =null;
            }
            tmp1.next=newNode;
            newNode.next=root;


            }
        }

        printList(tmp);
        return tmp;
    }


    //method to delete the element from the linked list
    private Node delete(Node root, int num) {
        Node tmp = root;
        Node res = tmp;

        if (tmp != null && tmp.data==num)
        {
            tmp = tmp.next;
            printList(tmp);
            return tmp;
        }

        Node prev = null;
        while (tmp != null && tmp.data != num)
        {
            prev = tmp;
            tmp = tmp.next;
        }
        //if (tmp != null && tmp.data==num)
          //  tmp = tmp.next;

        if (tmp == null)
        {
            System.out.println("NULL");
            
            return res;
        }

        prev.next = tmp.next;
        printList(root);
        return root;
    }


    //method to find the median of the linked list
    private Node median(Node root) {
        Node tmp1 = root;
        Node tmp2 = root;
        float med = 0;

        if (root==null)
            System.out.println("NULL");

        while (tmp2.next != null && tmp2.next.next != null) {
            tmp1 = tmp1.next;
            tmp2 = tmp2.next.next;
        }


        if (tmp2.next == null) {
            med = tmp1.data;
            System.out.println(med);

            return tmp1;
        }

        if (tmp2.next.next == null) {
            med = (float)(tmp1.data + tmp1.next.data) / 2;
            System.out.println(med);

            return tmp1;
        }


        //printList(root);
        return tmp1;
    }


    //method to print the linked list
    private void printList(Node root)
    {
        Node tmp = root;//mergeSort(root);
        while(tmp != null)
        {
            System.out.print(tmp.data+ " ");
            tmp = tmp.next;
        }
        System.out.println();

    }


    //method to read from file
    private String readAsString(String directory) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(directory)));
        return data;
    }

    public static void main(String[] args) throws IOException {

        LinkedListOperation l = new LinkedListOperation();

        Node root =null;
        //Getting the current directory path
        String currentDirectory = System.getProperty("user.dir");

        String data = l.readAsString(currentDirectory + "/data_linked.txt");

        //Splitting the test cases from input file using the delimitor
        String[] test_cases = data.split("\n");

        //for every test case this code will run
        for (int i1 = 0; i1 < test_cases.length; i1++) {
            System.out.println("Test case: " + (i1 + 1));
            String[] input = test_cases[i1].split("");

            int n = input.length;
            int i = 0;
            int j = 0;
            String operation;
            StringBuilder sb = new StringBuilder();
            int num;

            while (j != n) {
                if (input[j].equals("M")) {
                    l.median(root);
                    i = j + 1;
                    j += 1;
                    continue;
                }
                while (!input[j].equals("A") && !input[j].equals("M") && !input[j].equals("D"))
                    j++;
                operation = input[j];
                for (int k = i; k < j; k++)
                    sb.append(input[k]);

                num = Integer.parseInt(sb.toString());

                if (operation.equals("A"))
                    root = l.insert(root, num);
                if (operation.equals("D"))
                    root = l.delete(root, num);

                i =j+1;
                j +=1;
                sb = new StringBuilder();
            }

            root = null;
        }
    }
}