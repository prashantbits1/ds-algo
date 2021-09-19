package linkedList;

public class LinkedList {
	
  /**
   * 	
   * This Node class represents:
   * 
   *    --------
   *   |  data  |-->next
   *    --------
   */
  class Node{
	  int data;
	  Node next;
	  // Constructor to create new node
	  Node(int data){
		  this.data = data;
		  this.next = null;
	  }
  }
  
  Node head;
  
  // we can insert the data into LinkedList in three ways: 1- From the front, 2- After a given node, 3- At the end of LinkedList
  
  /**
   * This function inserts the data from front into LinkedList.
   *  Here the arrow(->) represents next pointer.
   *  1- Given LL:                                         head-> data1 -> data2 -> null
   *  2- Create new Node and insert data:                  dataN->null
   *  3- Initialize the newNode node next pointer by head: dataN-> head =: dataN -> data1 -> data2 -> null
   *  4- Re-point head pointer to newNode:                 head -> dataN =: head -> dataN -> data1 -> data2 ->  null 
   * 
   *  --head
   *  |  |
   *  |  |(delete this)       -----     ----
   *  |   ------------------>| d1  |-->| d2 |-->null
   *  |                       -----     ----
   *  |                       /.\  
   *  |                        |
   *  |                      ----    
   *   -------------------->| dN |     
   *                         ----
   * @param data
   */
	public void add(int data) {
		// Step-1: Create the new node and insert the data
		Node newNode = new Node(data);

		// Step-2: Point the newNode next pointer to head
		newNode.next = head;

		// Step-3: Move the head pointer to newNode
		head = newNode;
	}

 /**
   * This function inserts the data in-between into LinkedList.
   *  Here the arrow(->) represents next pointer.
   *  1- Given LL:                                             head-> data1 -> data2 -> null
   *  2- Create new Node and insert data:                      dataN->null
   *  3- Initialize the newNode node next pointer by prevNode(prevNode=data1): dataN.next-> prevNode.next =: head -> data1 -> data2 -> null
   *                                                                                                                            |
   *                                                                                                                   dataN----
   *    
   *  4- Re-point prevNode pointer to newNode:                 prevNode.next -> dataN =: head -> data1   data2 ->  null
   *                                                                                               |       /.\
   *                                                                                              \./       |
   *                                                                                              dataN-----
   *                                                                                               
   */
	public void insertAfter(Node prevNode, int data) {
		if (prevNode == null)
			return;
		Node newNode = new Node(data);
		newNode.next = prevNode.next;
		prevNode.next = newNode;
	}

/**
   * This function inserts the data at the end of LinkedList.
   *  Here the arrow(->) represents next pointer.
   *  1- Given LL:                                                          head-> data1 -> data2 -> null
   *  2- Create new Node and insert data:                                   dataN->null
   *  3- Traverse till lastNode which is lastNode data2.next=:              head -> data1 -> data2 -> null
   *                                                                                             |
   *                                                                                  lastNode----
   *    
   *  4- Re-point lastNode next pointer to newNode: lastNode.next->dataN =: head -> data1 -> data2 ->  null
   *                                                                                            |       /.\
   *                                                                                           \./       |
   *                                                                                           dataN-----
   *                                                                                               
   */
	public void append(int data) {
		// Step-1: Create a new node and insert the data
		Node newNode = new Node(data);
		// Step-2: Check if LinkedList is empty, make new node as head
		if (head == null) {
			head = newNode;
			return;
		}
		// Step-3: Else Traverse till last node and assign the lastNode next to newNode
		Node lastNode = head;
		while (lastNode.next != null) {
			lastNode = lastNode.next;
		}
		lastNode.next = newNode;
	}
 
}
