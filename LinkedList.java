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
    private int numItems;
    
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
    	// Variable to store the size of the list
    	int size = size();
    	
    	// When the item passed in is null, throw IllegalArgumentException
    	if(item == null){
    		throw new IllegalArgumentException();
    	}
    	
    	// When usr wants to add item to position smaller than 0 or bigger than
    	// the size of list, throw IndexOutOfBoundsException
    	if(pos< 0 || pos>size){
    		throw new IndexOutOfBoundsException();
    	}
    	// Create new node to add to linked list
    	Listnode<E> newNode = new Listnode<E>(item);
    	
    	for(int i = 0; i < pos; ++i){
    		
    	}
    	
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
		//TODO implement this method
        return head;
	}

	/**
	 * Must return a reference to a LinkedListIterator for this list.
	 */
	public LinkedListIterator<E> iterator() {
	    return new LinkedListIterator<E>(head);
	}
}
