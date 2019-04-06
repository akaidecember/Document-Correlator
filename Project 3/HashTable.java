/*************************************************************************************************************************************
Project-3 (CSC-130, #01)
Name: Anshul Kumar Shandilya
Description: Stub code for an implementation of a DataCounter that uses a hash 
             table as its backing data structure. We included this stub so that it's 
             very clear that HashTable works only with Strings, whereas the DataCounter 
             interface is generic. You need the String contents to write your hashcode 
             code.
Date: 6/24/18
**************************************************************************************************************************************/

public class HashTable implements DataCounter<String> {

   //Implementation of the Inner class Node
   public class Node{
   
      //Attribute for the inner class Node-------------------------------------------------------------------------
      
      private String data;
      private int count;
      private Node next;
      
      //Behaviours for the inner class Node-------------------------------------------------------------------------
      
      //Constructors for the inner class Node
      public Node(String data){
      
         this(data, null);
      
      }
      
      public Node(String data, Node next){
      
         this.data = data;
         this.count = 1;
         this.next = next;
      
      }
      
   }
   
   //Attributes for the outer class HashTable-------------------------------------------------------------------------------------------
   
   private static final int BUCKET_COUNT = 10;
   private Node[] buckets;
   private int size;
   
   //Behaviours for the outer class HashTable-------------------------------------------------------------------------------------------
   
   //Default constructor for the class HashTable
   public HashTable(){
   
      this(BUCKET_COUNT);
   
   }
   
   //Parameterized constructor for the class
   public HashTable(int bCount){
   
      this.buckets = new Node[bCount];
      size = 0;
   
   }
   
    /** {@inheritDoc} */
    //Method to get an array of all of the data counts in the DataCounter structure.
    public DataCount<String>[] getCounts() {
        
        DataCount<String>[] dataCounts = new DataCount[size];
        int i=0;
        for(Node node : buckets){                                                         //Traversing through the array to count the data
           while(node != null){
           
               dataCounts[i++] = new DataCount<>(node.data, node.count);
               node = node.next;
              
           } 
        }
     
         return dataCounts;
         
    }

    /** {@inheritDoc} */
    //Method to return the size
    public int getSize() {
        
        return size;
        
    }

    /** {@inheritDoc} */
    //Method to increment the count for a particular data element.
    public void incCount(String data) {
    
        if(calculateLoadFactor() > 0.50)                                               //Rehash the table if load factor is more than 0.5
            rehash();
        if(insert(buckets, data))                                                      //If insert is successful, increment the size
            size++;

    }
    
    //Method to insert into a hash table
    private boolean insert(Node[] buckets, String data){
    
         int hash = hashStr(data) % buckets.length;
         Node node = buckets[hash];
         
         if(node == null){
         
            buckets[hash] = new Node(data);
            return true;
         
         }
         else{
         
            while(node != null && !(node.data.equals(data)))
               node = node.next;
            
            if(node == null){
            
               buckets[hash] = new Node(data, buckets[hash]);
               return true;
            
            }
            else{
               
               node.count++;
               return false;
            
            }
            
         }
    
    }

   //Method to return the load factor of the hash table
   private double calculateLoadFactor(){
   
      return (double) size / (double) buckets.length;   
   }
   
   //Method to rehash the hash table (ie. create a bigger table and rehash all it's elements into the table again)
   private void rehash(){
   
      Node[] buckets = new Node[this.buckets.length*2];
      
      for(Node node : this.buckets){
      
         while(node != null){
         
           put(buckets, node.data, node.count);                                              //Put the elements in the table again
           node = node.next;  
          }
      }
      
      this.buckets = buckets;
         
   }
   
   //Method to put a corresponding element into it's bucket
    private void put(Node[] buckets, String data, int count) {
        
        int hash = hashStr(data) % buckets.length;                                            //Using the hash function
        Node node = buckets[hash];
        
        if(node != null){
    
            while(node != null && !node.data.equals(data)) 
                node = node.next;
            
            if(node != null)
                node.count = count;
            else{
            
                buckets[hash] = new Node(data, buckets[hash]);
                buckets[hash].count = count;
                
            }
        } 
        else{
        
            buckets[hash] = new Node(data);
            buckets[hash].count = count;
            
        }

    }
   
   //Method to return the hash function for inserting the elements into the hash table
   private int hashStr(String str){
   
      int sum = 0;
      
      for(int i = 0; i<str.length(); i++)
         sum += str.charAt(i) * BUCKET_COUNT * 3;                                               //The hash string is the sum of the ASCII value ot the individial string times the size of the bucket times a prime number (3 in this case)
         
      return sum;
   
   }
}
