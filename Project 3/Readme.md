Anshul Kumar Shandilya
Instructor, Jagannadha Chidella
CSC 130, Section #01
6/24/2018

---------------------------------------------------------------------------READ_ME------------------------------------------------------------------------------------------------

1. Who is in your group?
A. I'm working alone in this project.

2. How long did the project take?
A. Although this is a rough estimate, this project took me about 16 - 18 hours to complete.

3. Before you started, which data structure did you expect would be the fastest?
A. Before starting, I expected the Hash Table Data Structure to be the fastest. This is so because the AVL tree implementation would be slow because of the balancing 
	that has to take place if the tree is unbalanced.

4. Which data structure is the fastest? Why were you right or wrong?
A. After testing out the codes the same way that I tested them in my previous project, it turns out that BST stands the fastest. This was a mystery at first, but then I realized 
	that considering the fact that Hash tables use up more memory than BST as they might have unused buckets, or spaces in it's array, whereas a BST will not cause such a wastage.
	But I was wrong because in my mind, I was thinking about the assess time of an element from the hash table which is O(1), or constant. For this reason, I thoght that the hash
	table would be	faster in this case, but no. It turned out that BSTs are faster.
	
5. In general, which DataCounter dictionary implementation was "better": trees or hash tables? Note that you will need to define "better" (ease of coding, ease of debugging,
	memory usage, disk access patterns, runtime for average input, runtime for all input, etc).
A. In general, I think that implementing hash tables was much easier in terms of visualizing, but BST and AVL trees were easier to code as I recently had done a project on them.
	Even then, it was really easy to code the hash tables data structure. Now, coming in terms of performance, AVL Tree was in the last place for the reason I speculated above, which
	is the balanceing operation of the structure. This makes it slower when compared to the other two. I also have stated above that Hash tables use more memory than the trees because
	the tables might have unused locations, or buckets (which possibly might never be used).
	
6. Are there cases in which a particular data structure performs really well or badly in the correlator? Enumerate the cases for each data structure.
A. I tested the correlator class with each data structure individually. I did this by using System.nanoTime() function.At first, for the coorelation program, I tested out using the 
	avl tree. The entire process took 2771.66806 ms (for AVL). Next, I tested out the Binary Search Tree, which took 80.633219 ms (for BST). Finally, I tested out the hash table data 
	data structure, which took 113.988741 ms (for hash table). With this collected data, I can say that AVL tree performed considerably worse for the correlation program, taking 
	the most amount of time, and BST was the fastest, or the best for coorelation, taking the least amount of time.

7. Give a one to two paragraph explanation of whether or not you think Bacon wrote Shakespeare's plays based on the data you collected. No fancy statistical analysis here 
	(formal analysis comes later); keep it fun and simple.
A. The metric difference between the two documents by Bacon and Shakespeare yeilded to be : 0.000580. This is a significantly a small value. Thus, we can conclude that the "distance"
	between the files is not much and with this, I think that it is safe to assume that Bacon did write Shakespeare's works.

9. What did you enjoy about this assignment? What did you hate? Could we have done anything better?
A. I really enjoyed the overall project. All the projects from your class are different from whatever I have done in the past. Your projects make me think out of the box, and also
	I think that your projects are more relevant to real life applications of the concepts that we learn in the class. There is not much to hate about the projects, except the debugging
	part, which can be annoying (but that's me). However one part that could have been done better is the fact that the instructions for the asignment were very broad and it took me a while
	to figure out what I actually had to do for the project itself. The instructions could have been more concise. Otherwise, i have no complaints.
	
