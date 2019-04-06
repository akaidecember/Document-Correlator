/*************************************************************************************************************************************
Project-3 (CSC-130, #01)
Name: Anshul Kumar Shandilya
Description: This file contains the implementation for the WordCount class
Date: 6/24/18
**************************************************************************************************************************************/

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.*;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {
    
    //Main method for the class WordCount
    public static void main(String[] args){
        
        if(args.length != 3){
            
            System.out.printf("Input in the format : [-b | -a | -h] [-frequency | -num_unique] <filename>\n");
            System.out.println("-b : use Binary Search Tree");
            System.out.println("-a : use AVL Tree");
            System.out.println("-h : use Hash table");
            System.out.println("-frequency : Print the word frequency pairs ");
            System.out.println("-num_unique : Print the number of unique words in the files");
            
        }
        
        try{
            
            switch(args[1]){
                    
                case "-frequency":
                    countWordFrequencies(countWords(args[0],args[2]), WordCount::sortByDescendingCount);          //Calling method to obtain the frequencies
                    break;
                
                case "-num_unique":
                    countUniqueWords(countWords(args[0],args[2]));                                                //Calling method to obtain the number of unique words
                    break;
                    
                default: 
                    
                    System.out.printf("The second argument is invalid");
                    break;
                    
            }
            
        }catch(IOException e){
            
            System.out.println("Uh oh! an error occured with the file.");
            
        }
        
    }

    //Method to count the number of words in a document
    protected static DataCount<String>[] countWords(String ds, String fileName) throws IOException{
        
        FileWordReader f = new FileWordReader(fileName);
        DataCounter<String> wordCounter;
        
        //Conditional statements to check the choice of data structure by the user
        if(ds.equals("-h"))
            wordCounter = new HashTable();
        else if(ds.equals("-a"))
            wordCounter = new AVLTree<>();
        else if(ds.equals("-b"))
            wordCounter = new BinarySearchTree<>();
        else{
            
            System.out.printf("Invalid input, using Binary Search Tree by default");
            wordCounter = new BinarySearchTree<>();
        }
        
        String word;                                                                               //Count the words
        while((word = f.nextWord()) != null)
            wordCounter.incCount(word);
        
        return wordCounter.getCounts();
        
    }
    
    //Method to print the word counts
    protected static <E> void printWordCounts(DataCount<E>[] dataCounts){
        
        Arrays.stream(dataCounts).forEach(count -> {                                               //Streaming array 
                  System.out.format("%d %s\n", count.count, count.data);
              });
    }
    
    //Method to count and printthe word frequencies in the given document
    protected static void countWordFrequencies(DataCount<String>[] dataCounts, BiConsumer<DataCount<String>[],Comparator<DataCount<String>>> sort){
        
        sort.accept(dataCounts, (count1, count2) -> count2.count - count1.count);                  //First sorting the array w.r.t. the frequencies
        sort.accept(dataCounts, (count1, count2) -> count1.data.compareTo(count2.data));           //Then sorting the array w.r.t. lexographically
        
        System.out.printf("\n The frequencies are : \n");
        printWordCounts(dataCounts);
        
    }
    
    //Method to print the unique words in the given array/doc
    protected static void countUniqueWords(DataCount<String>[] dataCounts){
        
        System.out.printf("The number of unique words are : %d", dataCounts.length);
        
    }

    /**
     *Used insertion sorting method for the descending sort of the given arrays
     * 
     * @param counts array to be sorted.
     */
    private static <E> void sortByDescendingCount(DataCount<E>[] dataCounts, Comparator<DataCount<E>> comparator) {
        
        insertionSort(dataCounts, 0, dataCounts.length-1, comparator);                                      //Calling the insertion sort method 
        
    }
    
    //Internal method to perform insertion sort
    private static <E> void insertionSort(DataCount<E>[] dataCounts, int low, int high, Comparator<DataCount<E>> comparator){
    
         for(int i=low + 1; i<=high; i++){
         
            DataCount<E> key = dataCounts[i];
            int j=i-1;
            
            while(j >= 0 && comparator.compare(key, dataCounts[j]) < 0)
               dataCounts[j+1] = dataCounts[j--];
               
            dataCounts[j+1] = key;
         
         }
    
    }
}
