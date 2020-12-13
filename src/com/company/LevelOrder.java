package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrder {


    //initializing the data structures
    static TreeNode root = null;
    static Queue<TreeNode> queue = new LinkedList<>();
    static int index = 1;
    static boolean is_left = true;
    static TreeNode cur = null;

    //Class to create node in linked list
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //method to insert a node in the tree
    private static TreeNode insertNode(int element, int j) {
        //first element of array is the root element
        if (j == 0) {
            root = new TreeNode(element);
            queue.add(root);
            return root;
        }

        TreeNode node = null;
        //if element is not null then we add it to the queue
        if (element != -1) {
            node = new TreeNode(element);
            queue.add(node);
        }

        //creating a left child in the tree
        if (is_left) {
            cur = queue.peek();
            queue.poll();
            cur.left = node;
            is_left = false;
        }
        //creating a right child in the tree
        else {
            cur.right = node;
            is_left = true;
        }

        return root;

    }

    //method to print the level order
    static void printOrder(TreeNode root)
    {
        //Queue to store the nodes which are yet to print
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty())
        {
            TreeNode node = queue.poll();
            //print null on -1 value
            if(node.val == -1)
                System.out.print(" null ");
            else
                System.out.print(node.val+" ");


            //if left child is not null then we add left node
            if(node.left != null)
            {
                queue.add(node.left);
            }

            //if left node is null
            if(node.left == null && node.right != null)
            {
                queue.add(new TreeNode(-1));
            }

            //if right child is not null then we add right node
            if(node.right != null)
            {
                queue.add(node.right);
            }

            //if right node is null
            if(node.right == null && node.left != null)
            {
                queue.add(new TreeNode(-1));
            }


        }

    }

    //method to read file
    static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        //System.out.println(data);
        return data;
    }


    public static void main(String args[]) throws Exception {
        LevelOrder l =new LevelOrder();
        String currentDirectory = System.getProperty("user.dir");

        //method for reading data from file
        String data = l.readFileAsString(currentDirectory + "//data_level.txt");

        String[] test_cases = data.split("#####\n");

        for (int i = 0; i < test_cases.length; i++) {
            System.out.println();

            System.out.println("Test case: "+i);

            String[] str_arr = test_cases[i].split(" ");

            int[] int_arr = new int[str_arr.length];

            //inserting every node into the tree
            for (int j = 0; j < str_arr.length; j++) {
                int_arr[j] = Integer.parseInt(str_arr[j]);
                TreeNode r = insertNode(int_arr[j], j);
            }


            //calling method to print level order
            printOrder(root);

            //clearing root and queue for next test cases
            root = null;
            while (!queue.isEmpty())
                queue.poll();

        }


    }
}