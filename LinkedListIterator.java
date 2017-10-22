import java.util.Iterator;
import java.util.NoSuchElementException;
/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Fall 2017 
//PROJECT:          (Program 2)
//FILE:             (LinkedListIterator.java)
//
//TEAM:    (individual)
//Author1: (Yunhao Lin,ylin278@wisc.edu, ylin278, 002)
//
/////////////////////////////////////////////////////////////////////////////

/**
 * The iterator implementation for LinkedList.  The constructor for this
 * class requires that a reference to a Listnode with the first data
 * item is passed in.
 * 
 * If the Listnode reference used to create the LinkedListIterator is null,
 * that implies there is no data in the LinkedList and this iterator
 * should handle that case correctly.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 */
public class LinkedListIterator<T> implements Iterator<T> {
	
	private Listnode<T> curr;

	/**
	 * Constructs a LinkedListIterator when given the first node
	 * with data for a chain of nodes.
	 * 
	 * Tip: do not construct with a "blank" header node.
	 * 
	 * @param a reference to a List node with data. 
	 */
	public LinkedListIterator(Listnode<T> head) {
		// Initialize the current position to the one passed in
		this.curr = head;
	}
	
	/**
	 * Returns the next element in the iteration and then advances the
	 * iteration reference.
	 * 
	 * @return the next data item in the iteration that has not yet been returned 
	 * @throws NoSuchElementException if the iteration has no more elements 
	 */
	@Override
	public T next() {
		// When there is no more element, through NoSuchElementException 
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		
		// Generic data type to store whatefer is in the data element
		T item = curr.getData();
		
		// Update the pointer to the next listnode
		curr = curr.getNext();
		
		// Return the data stored in the current position
		return item;
	}
	
	/**
	 * Returns true if their are more data items to iterate through 
	 * for this list.
	 * 
	 * @return true if their are any remaining data items to iterator through
	 */
	@Override
	public boolean hasNext() {
		// return true if there is next data item in the list
		return (curr != null);
	}
       
    /**
     * The remove operation is not supported by this iterator
     * @throws UnsupportedOperationException if the remove operation is not 
     * supported by this iterator
     */
    @Override
	public void remove() {
	  throw new UnsupportedOperationException();
	}

}