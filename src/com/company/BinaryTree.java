package com.company;

public class BinaryTree {
    static boolean flag = true;
    static class Tree
    {
        Tree prev;
        Tree next;

        int data;
        Tree()
        {

        }
        Tree(int data)
        {
            this.data=data;
            this.prev = null;
            this.next =null;
        }
    }

    public static void main(String[] args)
    {
        BinaryTree bt = new BinaryTree();
        Tree root = new Tree(100);
        root.prev = new Tree(50);
        root.next = new Tree(200);
        root.prev.prev = new Tree(20);
        root.prev.prev.prev = new Tree(60);
        root.prev.next = new Tree(70);
        root.prev.next.prev = new Tree(60);
        root.prev.next.next = new Tree(75);
        root.next.prev = new Tree(110);
        root.next.next = new Tree(250);
        root.next.next.prev = new Tree(220);
        root.next.next.next = new Tree(280);

        bt.isBinary(root);
        if (flag==true)
            System.out.println("YES");
        else
            System.out.println("NO");

    }

    private void isBinary(Tree root) {
        if (root.next==null && root.prev==null) {
            //System.out.println("YES");
            return;
        }

        if (root.prev.data>root.data){
            flag = false;
            //System.out.println("NO");
            return;}
        System.out.println("1");
        isBinary(root.prev);

        if (root.next==null || root.prev==null) {
            //System.out.println("YES");
            return;
        }
        if (root==null)
            return;

        if (root.next.data<root.data){
            flag = false;
            //System.out.println("No");
            return;}
        System.out.println("2");
        isBinary(root.next);
        //flag = true;

    }
}
