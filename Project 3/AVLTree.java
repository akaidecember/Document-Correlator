/*************************************************************************************************************************************
Project-3 (CSC-130, #01)
Name: Anshul Kumar Shandilya
Description: This file contains the implementation for the AVLTree class
Date: 6/24/18
**************************************************************************************************************************************/

import java.util.*;

public class AVLTree<E extends Comparable<? super E>> extends BinarySearchTree<E>{
  
   //Attributes for the class AVlTree-----------------------------------------------------------------------------------
   
   //Behaviours for the class AVLTree-----------------------------------------------------------------------------------
      
   //Method to increment the data counter for a given element
   public void incCount(E data){
   
      super.incCount(data);
      overallRoot = balance(overallRoot);
   
   }

   //Method to check if tree is balanced
   public boolean isBalanced(BSTNode root){
      
      if(root == null)
         return true;
      else{
      
         int leftHeight = height(root.left);                                                                 //Get the height of the subtrees and then return their difference
         int rightHeight = height(root.right);
         return Math.abs(leftHeight - rightHeight) <= 1;
      
      }
   
   }
   
   //Method to balance the tree
   private BSTNode balance(BSTNode root){
   
      if(root == null)
         return null;
      
      if(height(root.left) - height(root.right) > 1){                                                   //If imbalanced (ie. the difference of the height of the subtrees is more than 1). 
                                                                                                          
         if(height(root.left.left) >= height(root.left.right)){
         
            root = singleRightRotation(root);                                                                     //Call for performing Single right Rotation                                    
            
         }
         else{
            
            root = doubleLeftRightRotation(root);                                                                 //Call for performing Double left Right Rotation
            
         }
       
      }
      else if(height(root.right) - height(root.left) > 1){
      
         if(height(root.right.right) >= height(root.right.left)){
         
            root = singleLeftRotation(root);                                                                      //Call for performing Single Left Rotation
            
         }
         else{
                     
            root = doubleRightLeftRotation(root);                                                                 //Call for performing double Left Right Rotation        
         
         }  
      
      }
      
      return root;
      
   }
   
   //Method for singleRightRotation
   private BSTNode singleRightRotation(BSTNode root){
   
      BSTNode node = root.left;
      root.left = node.right;
      node.right = root;
      return node;

   }
   
   //Method for single left rotation
   private BSTNode singleLeftRotation(BSTNode root){
   
      BSTNode node = root.right;
      root.right = node.left;
      node.left = root;
      return node;
   
   }

   //Method for Double left right rotation
   private BSTNode doubleLeftRightRotation(BSTNode node){
   
      node.left = singleLeftRotation(node.left);
      return singleRightRotation(node);
   
   }
   
   //Method for Double right left rotation
   private BSTNode doubleRightLeftRotation(BSTNode node){
   
      node.right = singleRightRotation(node.right);
      return singleLeftRotation(node);
      
   }

}