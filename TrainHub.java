/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Fall 2017 
//PROJECT:          (Program 2)
//FILE:             (TrainHub.java)
//
//TEAM:    (individual)
//Author1: (Yunhao Lin,ylin278@wisc.edu, ylin278, 002)
//
/////////////////////////////////////////////////////////////////////////////

/**
 * This class represents a train hub and provides the common operations
 * needed for managing the incoming and outgoing trains.
 * 
 * It has a LinkedList of Train as a member variable and manages it.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 * 
 * @see LinkedList
 * @see Train
 * @see Config
 */
public class TrainHub {
	
	/** The internal data structure of a hub is a linked list of Trains */
	private LinkedList<Train> trains;

	/**
	 * Constructs and initializes TrainHub object
	 */
	public TrainHub(){
		// Initialize trainshub to be a linked List of trains
		this.trains = new LinkedList<Train>();
	}
	
	/**
	 * This method processes the incoming train.
	 * It iterates through each of the cargo car of the incoming train.
	 * If there is an outgoing train in the train list going to the 
	 * destination city of the cargo car, then it removes the cargo car 
	 * from the incoming train and adds the cargo car at the correct location 
	 * in the outgoing train.  The correct location is to become the first
	 * of the matching cargo cars, with the cargo cars in alphabetical order 
	 * by their cargo name.
	 * 
	 * If there is no train going to the destination city, 
	 * it creates a new train and adds the cargo to this train.
	 * 
	 * @param train Incoming train (list or cargo cars)
	 */
	public void processIncomingTrain(Train train){
		// Create Iterator to go through the incoming train
		LinkedListIterator<CargoCar> itr;
		
		// Used to store the train found in the train list
		Train trFound = null;
		
		// Used to store the cargo get from 
		CargoCar cargo = null;

		// The new train to new destination cargo holds
		Train newTrain = null;
		
		// When there is next train in the list keep going
		while(train.numCargoCars()>0){
			// Set cargo to be the first data in the first node
			cargo = train.getHeaderNode().getNext().getData();

			// Find a train with destination of the cargo
			trFound = findTrain(cargo.getDestination());

			// If couldn't find train, create new train and add cargo to it
			if(trFound == null){
				// Create a new Train go to the destination of cargo
				newTrain = new Train(cargo.getDestination());
				// Add the cargo to the train
				newTrain.add(cargo);
				// Add the new Train to the trains' list
				trains.add(newTrain);
			}
			
			// If found the train going to that destination
			else{
				// Initialize iterator to iterate through the train to dest
				itr = trFound.iterator();
				// Position to add the cargo in the train
				int pos = 0;
				
				// Iterate through the list
				while(itr.hasNext()){
					// Set a temporarily cargo car to to be the next car
				    CargoCar temp = itr.next();
				    
				    // Compare the name of the cargo with the temporarily one
				    int compare = cargo.getName().
				    		compareToIgnoreCase(temp.getName());
			
				    // When it's smaller than the temp one , break the loop
				    // with current position
					if(compare <= 0){
						break;
					}
					
					// Increase the position
					pos ++;
				}
				
				// Add cargo to that position
				trFound.add(pos,cargo);
			}
			// Remove the first cargo of that name
			train.removeCargo(cargo.getName());
		}
	}
	/**
	 * This method tries to find the train in the list of trains, departing to the given destination city.
	 * 
	 * @param dest Destination city for which train has to be found.
	 * @return  Pointer to the train if the train going to the given destination exists. Otherwise returns null.
	 */
	public Train findTrain(String dest){
		// Create 
		LinkedListIterator<Train> itr = trains.iterator();
		Train train = null;
		// When there is next train in the list of trains
		while(itr.hasNext()){
			// Temporarily Train tneed to be checked
			train = itr.next();
			// When the Destination for train has been found, return the train
			if(train.getDestination().equalsIgnoreCase(dest.trim())){
				return train;
			}
		}
		// Return null if there is no train going to destination
		return null;
	}
	
	/**
	 * This method removes the first cargo car going to the given 
	 * destination city and carrying the given cargo.
	 * 
	 * @param dest Destination city
	 * @param name Cargo name
	 * @return If there is a train going to the the given destination and is carrying the given cargo, 
	 * it returns the cargo car. Else it returns null.
	 */
	public CargoCar removeCargo(String dest, String name){
		// The cargo car to return
		CargoCar car = null;
		
		// Train that goes to the destination we want
		Train train = findTrain(dest);
		
		// When the given cargo is found remove the cargo from the train
		if(train != null){
			car = train.removeCargo(name);
		}
		
		// Return the cargo car	
		return car;
	}
	
	/**
	 * This method iterates through all the trains in the list 
	 * and finds the sum of weights of given cargo in all trains.
	 * 
	 * @param name Name of the cargo
	 * @return Total weight of given cargo in all departing trains.
	 */
	public int getWeight(String name){
		// Generate iterator to iterate through the trains list
		LinkedListIterator<Train> itr = trains.iterator();
		
		// Integer variable to store the sum of certain cargo
		int sum  = 0;
		
		// When there is next train in the trains' list, create itertor
		// to go through each cargo car in the train
		while(itr.hasNext()){
			sum += itr.next().getWeight(name);
		}
		
		// Return the total weight of some kind of cargo 
		return sum;
	}
	
	/**
	 * This method is used to depart the train to the given destination. 
	 * To depart the train, one needs to delete the train from the list. 
	 * 
	 * @param dest Destination city for which corresponding train has to be departed/deleted.
	 * @return True if train to the given destination city exists. If not, then return false. 
	 */
	public boolean departTrain(String dest){
		// Generate iterator to iterate through the trains list
		LinkedListIterator<Train> trainItr = trains.iterator();
		Train train;

		// Used to track where are we at the list
		int pos = 0;
		
		// To detect if the train is departed
		boolean depart = false;
		
		// When there is next train keep going
		while(trainItr.hasNext()){
			
			// Set train to the next train
			train =  trainItr.next();
			
			// When the destination matches, remove train at that destination
			if(train.getDestination().equalsIgnoreCase(dest)){
				trains.remove(pos);
				// Set the train to be departed
				depart =true;
			}
			
			// increase position in the trains' list
			pos ++;
		}
		// Return to show if train is departed
		return depart;
	}
	/**
	 * This method deletes all the trains.
	 * 
	 * @return True if there was at least one train to delete. False if there was no train to delete.
	 */
	public boolean departAllTrains(){
		
		// Boolean variable that indicates train to delete
		boolean delete = false;
		
		// When there is next trian in the train list, keep going
		while(trains.size()>0){
			
			// Remove the train at that position
			trains.remove(0);
			// Change boolean to indicate train has been removed
			delete = true;
			// Increment the position 
			
		}
		// Return value that shows if there is train departed
		return delete;
	}

	/**
	 * Display the specific train for a destination.
	 * 
	 * @param dest Destination city for the train the to be displayed.
	 * @return True if train to the given destination city exists. If not, then return false.
	 */
	public boolean displayTrain(String dest){
		// Create iterator to go through the trains
		LinkedListIterator<Train> itr = trains.iterator();
		
		// Train to be displayed
		Train train = null;
		
		// When there is next in the Trains 
		while(itr.hasNext()){
			
			// Set the train to be displayed
			train = itr.next();
			
			// If the destination matches, display train
			if(train.getDestination().equalsIgnoreCase(dest)){
				// Make the train to string 
				System.out.println(train.toString());
				// When there is train to that destination, return tree
				return true;
			}
		}
		// If not return false
		return false;
	}

	/**
	 * This method is used to display all the departing trains in the train hub.
	 * Train should be displayed in the specified format. 
	 * 
	 * @return True if there is at least one train to print. False if there is no train to print.
	 */
	public boolean displayAllTrains(){
		// Create iterator to go through the trains
		LinkedListIterator<Train> itr = trains.iterator();
		
		// To indicate if there is train to be displayed
		Boolean inTrain = false;
		
		// Train to be displayed
		Train train = null;
		
		// When there is next train display it
		while(itr.hasNext()){
			// Train at current position in the train hub
			train = itr.next();
			// Make the train to string 
			System.out.println(train.toString());
			// When there is train to that destination, return tree
			inTrain = true;
		}
		// If not , return false
		return inTrain;
	}
	
	/**
	 * Move all cargo cars that match the cargo name from a 
	 * source (src) train to a destination (dst) train.  
	 * 
	 * The matching cargo cars are added before the first cargo car
	 * with a name match in the destination train.
	 * 
	 * All matching cargo is to be moved as one chain of nodes and inserted
	 * into the destination train's chain of nodes before the first cargo matched node.
	 * 
	 * PRECONDITION: there is a source train and a destination train,
	 * and the source train of nodes has at least one node with
	 * matching cargo.  We will not test other conditions.
	 * 
	 * NOTE: This method requires direct access to the chain of nodes of a
	 * Train object.  Therefore, the Train class has a method in addition to 
	 * ListADT methods so that you can get direct access to header node 
	 * of the train's chain of nodes.   
	 *
	 * @param src a reference to a Train that contains at least one node with cargo.  
	 * 
	 * @param dst a reference to an existing Train.  The destination is the 
	 * train that will have the cargo added to it.  If the destination chain 
	 * does not have any matching cargo, add the chain at its correct location 
	 * alphabetically.  Otherwise, add the chain of cargo nodes before the
	 * first matching cargo node.
	 * 
	 * @param cargoName The name of cargo to be moved from one chain to another.
	 */
	public static void moveMultipleCargoCars(Train srcTrain, Train dstTrain, String cargoName) {
		// TODO Implement this method last.  It is not needed for other parts of program
		
		// get references to train header nodes
		
		// get references to train header nodes
		Listnode<CargoCar> srcHeader, dstHeader, prev, curr;
		srcHeader = srcTrain.getHeaderNode();
		dstHeader = dstTrain.getHeaderNode();
		
		Listnode<CargoCar> first_prev = null, first = null, last = null;
		boolean hasFound = false;
		boolean finish = false;
		// 1. Find references to the node BEFORE the first matching cargo node
		//    and a reference to the last node with matching cargo.
		
		// Set curr to be the header node
		curr = srcTrain.getHeaderNode();
		first_prev = curr;
		last = curr.getNext();
		
		// When the next item is not null keep going
		while(curr.getNext() != null){
			
			// if not found and the next is a match of name
			if(!hasFound && curr.getNext().getData().getName().equalsIgnoreCase(cargoName)){
				// change the node before found and the first found
				first_prev = curr;
				first = curr.getNext();
				
				// Make found to be true
				hasFound = true;
			}
			
			// if already found, then go to find the next not match
			if(hasFound && !curr.getNext().getData().getName().equalsIgnoreCase(cargoName)){
				// set last to be at curr
				last = curr;
				
				// leave the loop
				first_prev.setNext(last.getNext());
				// Set next of last to nothing
				last.setNext(null);
				finish = true; 
				break;
			}
			
			// update pointer
			curr = curr.getNext();
		}
	
		
		// if the list of the cargo car with the cargo name
		// set the next of  previous of the first car to null
		if (hasFound && !finish) {
			last = curr;
			first_prev.setNext(null);
		}
	
			// NOTE : We know we can find this cargo,
			//        so we are not going to deal with other exceptions here.
		
		// 2. Remove from matching chain of nodes from src Train
		//    by linking node before match to node after matching chain
		
		
		// 3-1. Find reference to first matching cargo in dst Train
		curr = dstHeader.getNext();
		prev = dstHeader;
		
		// When there is nothing in the train
		if(curr == null){
			prev.setNext(first);
		}
		
		while(curr !=null){
			
			// 3-2. If found, insert them before cargo found in dst
			if(curr.getData().getName().compareToIgnoreCase(cargoName) >= 0) {
				
				last.setNext(curr);
				prev.setNext(first);
				// Make it found
				hasFound = true;
				
				break;
			}
			if(curr.getNext() == null){
				curr.setNext(first);
				break;
			}
			
			// Update pointer
			curr = curr.getNext();
			prev = prev.getNext();
		}
		// 3-3. If no matching cargo, add them at the end of train
		if(!hasFound){
			curr.setNext(first);
		}

	}
}
