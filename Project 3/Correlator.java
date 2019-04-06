/*************************************************************************************************************************************
Project-3 (CSC-130, #01)
Name: Anshul Kumar Shandilya
Description: This file contains the implementation for the Document Correlator
Date: 6/24/18
**************************************************************************************************************************************/

import java.io.IOException;                                                       
import java.util.*;

public class Correlator extends WordCount{

   //Attributes of the class Correlator------------------------------------------------------------------------------------------------
   
   private double frequency;
   private String data;
   
   //Behaviours of the class Correlator------------------------------------------------------------------------------------------------
   
   //Setter methods for attributes
   public void setFrequency(double value){
   
      frequency = value;
   
   }
   
   public void setData(String value){
   
      data = value;
   
   }
   
   //Getter methods
   public double getFrequency(){
   
      return this.frequency;
   
   }
   
   public String getData(){
   
      return this.data;
   
   }
   
   //Default constructor for the class Correlator
   public Correlator(){
   
      frequency = 0.0;
      data = "";
   
   }
   
   //Parameterized constructor for the class Correlator
   public Correlator(double val1, String val2){
   
      frequency = val1;
      data = val2;
   
   }
   
   //The main method for the class Correlator
   public static void main(String[] args){
   
      //If the given argument is not exactly 3
      if(args.length != 3){
      
            System.out.printf("Input in the format : [-b | -a | -h] <filename1> <filename2>\n");
            System.out.println("-b : use Binary Search Tree");
            System.out.println("-a : use AVL Tree");
            System.out.println("-h : use Hash table");
      
      }
      
      //Declaring arrays of type DataCount to store the Data from the specified file
      DataCount<String>[] count1 ;
      DataCount<String>[] count2;
      
      //Try block incase the file reading mechanism has some problem
      try{
      
         long start = 0, end = 0;
         
         start = System.nanoTime();
         
         count1 = countWords(args[0], args[1]);
         count2 = countWords(args[0], args[2]);
         
         end = System.nanoTime();
         
         double result = (end - start)/1000000.0;
         
         //Printing out the time factor for the selected data structure
         System.out.printf("\n The time taken by your selected data structure is : "+ result + "ms.\n\n");
         
         //Printing out the difference metric of the two files
         System.out.printf("\n The difference metric for the files %s and %s is : %f\n", args[1], args[2], diff(count1,count2));
      
      }
      catch(IOException e){
      
         //Will print if there was a problem reading the file
         System.out.printf("Uh oh! An error occured with the file.");
      
      }
   
   }
   
   //Method to count and return the total number of words in an array of dataCounts
   private static int totalCount(DataCount<String>[] dataCounts){
   
      int count = 0;
      
      for(DataCount<String> dataCount : dataCounts)
         count += dataCount.count;
         
      return count;
   
   }
   
   //Method to calculate the frequencies
   private static ArrayList<Correlator> getFrequency(DataCount<String>[] count){

      ArrayList<Correlator> ar = new ArrayList<Correlator>();                          //Creating an ArrayList of objects of the type Correlator
      double total = (double)totalCount(count);                                        //Calculating the total number of words in the given document or file
      
      for(DataCount<String> dataCount : count ){                                       //For each loop to ierate through the array dataCount
      
         double freq = (double) dataCount.count / total;                               //Getting the frequency of a data from the file
         if(freq < 0.01 && freq > 0.0001){
            System.out.format("%s - %f\n", dataCount.count, freq);
            Correlator obj = new Correlator(freq, dataCount.data);                     //Storing the frequency with the data in an object of type Correlator
            ar.add(obj);                                                               //Inserting the object into the ArrayList
      
         }
         
      }
      return ar;                                                                       //Returning the array list

      
   }
   
   //Method to calculate the difference metric between the data counts of the two documents
   private static double diff(DataCount<String>[] dataCounts1, DataCount<String>[] dataCounts2){
   
      System.out.printf("\n\nFrequencies for the first file------------------------------------------------------------------------------------\n");
      ArrayList<Correlator> ar1 = getFrequency(dataCounts1); 
      
      System.out.printf("\n\nFrequencies for the second file-----------------------------------------------------------------------------------\n");
      ArrayList<Correlator> ar2 = getFrequency(dataCounts2);
      
      double sum = 0;
      
      //Iterative loop for comparing the words in both of the files, and if the words are existant in both the files, it will get the difference of their frequencies
      for(int i = 0; i < ar1.size(); i++){
      
         for(int j = 0; j < ar2.size(); j++){
         
            if(ar1.get(i).data.equals(ar2.get(j).data)){
            
               double difference = Math.abs(ar1.get(i).frequency - ar2.get(j).frequency);
               sum += difference * difference;                                                     //Variable to store the total difference metric 
               break;
            
            }  
         
         }
      
      }
   
      return sum;                                                                                  //Returning the difference metric
      
   }
}