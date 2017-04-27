package com.algorithms.lists;

import com.algorthms.MyLinkedList;
import com.algorthms.Node;

/**
 * Given a number n and a list, partition the list such that:
 * 1. all numbers < n, appear before n
 * 2. all numbers < n appear before all numbers > n
 *  
 * @author riazuddin
 *
 */
public class PartitionList {

    private Node partitionList(Node head, int n) {
        if (head == null)
            return null;
        
        MyLinkedList smallerNumbers = new MyLinkedList(); 
        MyLinkedList bigNumbers = new MyLinkedList();
        Node newNode = null;
        
        Node node = head;
        while (node != null){
            newNode = new Node(n);
            if(node.getData() < n){
                smallerNumbers.appendToTail(newNode);
            }else{
                bigNumbers.appendToTail(newNode);
            }
            node = node.getNext();
        }
        
        smallerNumbers.appendToTail(bigNumbers.getHead());
        return smallerNumbers.getHead();
    }
}
