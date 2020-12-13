package com.company;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

public class PalindromicTree {

    //initializing the data structures
    static TreeNode root = null;
    static Queue<TreeNode> queue = new LinkedList<>();
    static int index = 1;
    static boolean is_left = true;
    static TreeNode cur = null;


    //Class to create a node in linked list
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

    //method to check whether the tree is palindromic or not
    private static boolean isPalindrome(TreeNode a, TreeNode b) {
        //if both are empty
        if (a == null && b == null)
            return true;

        // If only one is empty
        if (a == null || b == null)
            return false;

        //if value are equal then we recursively call left subtree of a
        //and right subtree of b
        return a.val == b.val && isPalindrome(a.left, b.right) && isPalindrome(a.right, b.left);

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


    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        //System.out.println(data);
        return data;
    }

    public static void main(String args[]) throws Exception {


        PalindromicTree f = new PalindromicTree();
        String currentDirectory = System.getProperty("user.dir");

        //method for reading data from file
        String data = f.readFileAsString(currentDirectory + "//data_pal.txt");

        String[] test_cases = data.split("#####\n");

        for (int i = 0; i < test_cases.length; i++) {

            String[] str_arr = test_cases[i].split(" ");

            int[] int_arr = new int[str_arr.length];

            //inserting every element into the tree
            for (int j = 0; j < str_arr.length; j++) {
                int_arr[j] = Integer.parseInt(str_arr[j]);
                TreeNode r = insertNode(int_arr[j], j);
            }

            //creating two trees a and b to find that are mirror images or not
            TreeNode a = root.left;
            TreeNode b = root.right;
            System.out.println(isPalindrome(a, b));

            //cleaning root and queue, for the next test cases
            root = null;
            while (!queue.isEmpty())
                queue.poll();

        }

    }


}
