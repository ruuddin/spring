package com.algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algorthms.TreeNode;

/**
 * Description: Given a binary tree, write a method to print the tree level by level.
        (e.g)
        Tree:
              1
             / \
            3   5
           / \   \
          2   4   7
         / \   \
        9   6   8
         
        ==========
        Expected Output:
        1
        35
        247
        968
 * @author riazuddin
 *
 */
public class PrintTreeByLevel {
    public static void main(String[] args) {

        TreeNode n = new TreeNode(10, new TreeNode(15, null, null), new TreeNode(-1, null, null));
        Queue<TreeNode> list = new LinkedList<>();
        
        list.add(n);
        while (!list.isEmpty()) {
            int levels = list.size();
            while(levels > 0){
                TreeNode node = list.poll();
                System.out.print(node.getData() + " ");
    
                if (node.getLeft() != null)
                    list.offer(node.getLeft());
                if (node.getRight() != null)
                    list.offer(node.getRight());
                levels--;
            }
            System.out.println();
        }
    }
}
