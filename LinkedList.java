import java.util.Iterator;

/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Fall 2017 
//PROJECT:          (Program 2)
//FILE:             (LinkedList.java)
//
//TEAM:    (individual)
//Author1: (Yunhao Lin,ylin278@wisc.edu, ylin278, 002)
//
/////////////////////////////////////////////////////////////////////////////

/**
 * An Iterable list that is implemented using a singly-linked chain of nodes
 * with a header node and without a tail reference.
 * 
 * The "header node" is a node without a data reference that will 
 * reference the first node with data once data has been added to list.
 * 
 * The iterator returned is a LinkedListIterator constructed by passing 
 * the first node with data.
 * 
 * CAUTION: the chain of nodes in this class can be changed without
 * calling the add and remove methods in this class.  So, the size()
 * method must be implemented by counting nodes.  This counting must
 * occur each time the size method is called.  DO NOT USE a numItems field.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 */
public class LinkedList<E> implements ListADT<E> {


	//	 TODO: YOU MUST IMPLEMENT THE LINKED LIST CLASS AS FOLLOWS:
	//	 
	//	 It must be a SINGLY-LINKED chain of ListNode<E> nodes
	//	 It must have a "header node" ("dummy node" without data)
	//	 It must NOT have a tail reference
	//	 It must NOT keep a number of data items
	//       NOTE: in this program, the chains of nodes in this program may be 
	//   	 changed outside of the LinkedList class, so the actual data count 
	//       must be determined each time size is called.
	//
	//	 It must return a LinkedListIterator<E> as its iterator.
	//	 
	//	 Note: The "header node"'s data reference is always null and 
	//	 its next references the node with the first data of the list.
	//	 
	//	 Be sure to implement this LinkedList<E> using Listnode
	//	       you must use LinkedListIterator<E> instead of Iterator<E>
	//	

    // Need further change
    private Listnode<E> head;
    
    /**
     * Construct new Linkedlist with header node
     */
    public LinkedList(){
    	//Initialize a header node with null reference
    	head  = new Listnode<E>(null);
    }
    
    /**
	 * Adds a data item to the end of the List.
	 * 
	 * @param item the item to add
	 * @throws IllegalArgumentException if item is null 
	 */
    public void add(E item){
    	// If item doesn't exit throw illegalArugementException
    	if(item == null){
    		throw new IllegalArgumentException();
    	}
    	// Create new Listnode that contains info to add
    	Listnode<E> newNode = new Listnode<E>(item);

    	// Listnode to loop through the list to find the end
    	Listnode<E> curr  = head;

    	// Loop through the Linked list to find the end of the list
    	while(curr.getNext() != null){
    		curr = curr.getNext();
    	}
    	// Set the next node at the end of the list to be the new node
    	curr.setNext(newNode);
    }
    
    /**
	 * Adds a data item at position pos in the List, moving the items originally 
	 * in positions pos through size() - 1 one place to the right to make room.
	 * 
	 * @param pos the position at which to add the item
	 * @param item the item to add
	 * @throws IllegalArgumentException if item is null 
	 * @throws IndexOutOfBoundsException if pos is less than 0 or greater 
	 * than size()
	 */
    public void add(int pos, E item){

    	// When the item passed in is null, throw IllegalArgumentException
    	if(item == null){
    		throw new IllegalArgumentException();
    	}

    	// When usr wants to add item to position smaller than 0 or bigger than
    	// the size of list, throw IndexOutOfBoundsException
    	if(pos< 0 || pos>size()){
    		throw new IndexOutOfBoundsException();
    	}

    	// Create new node to add to linked list
    	Listnode<E> newNode = new Listnode<E>(item);

    	// Listnode to indicate the current location in the list
    	Listnode<E> curr = head;

    	// Iterate through the list to the one before the position to add
    	for(int i = 0; i < pos; ++i){
    		curr = curr.getNext();
    	}

    	// When the next position is not null, add new node between two nodes
    	if(curr.getNext() != null){
    		newNode.setNext(curr.getNext());
    		curr.setNext(newNode);   		
    	}
    	// else set the next node after newnode to null
    	else{
    		add(item);
    	}
    }
    
    /**
	 * Returns true iff item is in the List (i.e., there is an item x in the List 
	 * such that x.equals(item))
	 * 
	 * @param item the item to check
	 * @return true if item is in the List, false otherwise
	 */
	public boolean contains(E item) {
		// Create Iterator to iterate through the list
		Iterator<E> itr = iterator();

		// When there is nex item in the list, check the data. 
		while(itr.hasNext()){
			// If found a match return true indicates match found
			if(itr.next().equals(item)){
				return true;
			}
		}
		// If no match found in the list return false;
		return false;
	}
	
	/**
	 * Returns the item at position pos in the List.
	 * 
	 * @param pos the position of the item to return
	 * @return the item at position pos
	 * @throws IndexOutOfBoundsException if pos is less than 0 or greater than
	 * or equal to size()
	 */
	public E get(int pos) {
		
		E item = null;
		// When usr wants to add item to position smaller than 0 or bigger than
		// or equal to size of list, throw IndexOutOfBoundsException
		if(pos<0 || pos >= size()){
			throw new IndexOutOfBoundsException();
		}

		// Create Iterator to iterate through the list
		Iterator<E> itr = iterator();

		// Iterate to the position and get the data item at that position
		for(int i = 0; i<= pos; ++i){
			item  = itr.next();
		}

		// Return the data item 
		return item;
	}

   /**
	 * Returns true iff the List is does not have any data items.
	 * 
	 * @return true if the List is empty, false otherwise
	 */
	public boolean isEmpty() {

		// Listnode to indicate the current position 
		Listnode<E> curr = head;

		// Go through the list and check every data item of the listnode
		for(int i = 0; i< size(); ++i){
			// if one of them is not empty, return false
			if(curr.getNext().getData() != null){
				return false;
			}
			// If not empty, upadate the pointer
			curr = curr.getNext();
		}
		// return boolean variable that indicates the emptiness of the list
		return false;
	}

	/**
	 * Removes and returns the item at position pos in the List, moving the items 
	 * originally in positions pos+1 through size() - 1 one place to the left to 
	 * fill in the gap.
	 * 
	 * @param pos the position at which to remove the item
	 * @return the item at position pos
	 * @throws IndexOutOfBoundsException if pos is less than 0 or greater than
	 * or equal to size()
	 */
	public E remove(int pos) {
		
		// Listnode to indicate the current position 
		Listnode<E> curr = head;
		
		// Item to be returned
		E item = null;
		
		// When usr wants to add item to position smaller than 0 or bigger than
    	// or equal to size of list, throw IndexOutOfBoundsException
		if(pos < 0 || pos >= size()){
			throw new IndexOutOfBoundsException();
		}

		// Go to the node before the node at the position 
		for(int i=0; i < pos; ++i){
			curr = curr.getNext();
		}

		// Get the item from position
		item = curr.getNext().getData();

		// Set the listnode before the position node points to the one after 
		// position node
		curr.setNext(curr.getNext().getNext());

		return item;
	}



	/**
	 * Returns the number of items in the List.
	 * 
	 * @return the number of items in the List
	 */
	public int size(){
		// Variable to store the size of the list
		int size = 0;
		// Listnode to indicate current location in the list
		Listnode<E> curr = head;

		// Loop through the list until the next one is null to find the size
		while(curr.getNext() != null){
			curr = curr.getNext();
			// Increment size
			++ size;
		}
		// Return the size of the list
		return size;
	}

	/** 
	 * Returns a reference to the header node for this linked list.
	 * The header node is the first node in the chain and it does not 
	 * contain a data reference.  It does contain a reference to the 
	 * first node with data (next node in the chain). That node will exist 
	 * and contain a data reference if any data has been added to the list.
	 * 
	 * NOTE: Typically, a LinkedList would not provide direct access
	 * to the headerNode in this way to classes that use the linked list.  
	 * We are providing direct access to support testing and to 
	 * allow multiple nodes to be moved as a chain.
	 * 
	 * @return a reference to the header node of this list. 0
	 */
    public Listnode<E> getHeaderNode() {
    	// Return the header node of the list
    	return head;
    }

    /**
     * Must return a reference to a LinkedListIterator for this list.
     */
    public LinkedListIterator<E> iterator() {
    	return new LinkedListIterator<E>(head.getNext());
    }
}
