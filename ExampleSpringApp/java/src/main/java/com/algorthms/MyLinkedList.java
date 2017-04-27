
package com.algorthms;

public class MyLinkedList {
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

}