
package com.algorthms;

public class LinkedList {
    private Node head;
    private Node lastNode;

    public void appendToTail(Node n) {
        if (head == null){
            head = n;
            lastNode = head;
        }
        lastNode.setNext(n);
        lastNode = n;
    }
    
    public Node getHead() {
        return head;
    }
    
    public void print(){
        Node n = head;
        System.out.print("List: ");
        while (n != null){
            System.out.print(n.getData()+" ");
            n = n.getNext();
        }
        System.out.println();
    }

    public static class Node {

        private int data;
        private Node next;

        public Node(
                int data){
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}