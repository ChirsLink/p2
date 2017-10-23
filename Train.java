/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Fall 2017 
//PROJECT:          (Program 2)
//FILE:             (Train.java)
//
//TEAM:    (individual)
//Author1: (Yunhao Lin,ylin278@wisc.edu, ylin278, 002)
//
/////////////////////////////////////////////////////////////////////////////
/**
 * This class represents a train.  It has a destination and a linked list 
 * of CargoCar objects.  It implements Iterable<CargoCar> by returning 
 * a direct access iterator to its linked list of cargo cars. 
 * 
 * Several methods, such as getDestination(), removeCargo() and getWeight(), 
 * are provided to manage a train object. 
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 * 
 * @see LinkedList
 * @see CargoCar
 */
public class Train implements Iterable<CargoCar> {

	private String destination;
	private LinkedList<CargoCar> train;
	
	/**
	 * Constructs Train with its destination.
	 * 
	 * @param dest train destination
	 */
	public Train(String dest){
		this.destination = dest.trim();
		train = new LinkedList<CargoCar>();
	}
	
	/**
	 * Get the destination of this train.
	 * 
	 * @return train destination
	 */
	public String getDestination(){
		return this.destination;
	}
	
	/**
	 * Set a new destination for this train.
	 * 
	 * @param newDest new train destination
	 */
	public void setDestination(String newDest){
		this.destination = newDest.trim();
	}
	
	/**
	 * Get the total weight of a cargo in this train.
	 * 
	 * @param the name of the cargo to sum
	 * @return total weight of specified cargo in this train
	 */
	public int getWeight(String cargoName){
        // Variable to store weight
		int totalWeight = 0;
        
		// Iterator to the train linked list that has cargo cars
        LinkedListIterator<CargoCar> itr = iterator();
       
        // When there is next cargo in the train keep checking name
        while(itr.hasNext()){
        	
        	// Store the cargo car from train
        	CargoCar c = itr.next();
        	
        	// If the name of cargo matches, add weight to total weight
        	if(c.getName().equalsIgnoreCase(cargoName)){
        		totalWeight += c.getWeight();
        	}
        }
        
        // Return the total weight of a certain name of cargo
        return totalWeight;
                
	}
	
	
	/**
	 * Add cargo car to the end of the train
	 * 
	 * @param cargoCar the object need to be added to the end of car
	 */
	public void add(CargoCar cargoCar) {
		// Add cargoCar at the end of the train
		train.add(cargoCar);
	}

	/**
	 * Add cargo car as specified position 
	 * @param pos the position at which to add the item
	 * @param newCargo the cargo car to add
	 */
	public void add(int pos, CargoCar newCargo) {
		train.add(pos, newCargo);
	}
	
	/**
	 * Remove the first CargoCar from this train which has the same cargo name 
	 * with the argument. If there are multiple CargoCar objects with the same 
	 * name, remove the first one.
	 * 
	 * @param The name of the cargo that you wish to remove.
	 * @return removed CargoCar object if you successfully removed a cargo, 
	 * otherwise null
	 */
	public CargoCar removeCargo(String cargoName){
		// Position to remove the Cargo
		int pos = 0;
		
		// Cargo car to be removed
		CargoCar c = null;
		
		// Iterator used to go through the list
		LinkedListIterator<CargoCar> itr = train.iterator();
		
		// When there is next train 
		while(itr.hasNext()){
			// Get the name of that cargo in the train
			String name  = itr.next().getName();
			
			// if the name of the cargo matches
			if(name.equalsIgnoreCase(cargoName)){
				// remove that cargo and break the loop
				c = train.remove(pos);
				break;
			}
			// in crease the position
			++pos;
		}
		// return the cargo car
		return c;
	}
	
	/**
	 * Returns a iterator that iterate through the train linked list
	 * @return itr a linkedlistIterator for the train
	 */
	public LinkedListIterator<CargoCar> iterator() {
		// Get the iterator of the train and return it
		return train.iterator(); 
	}

	/**
	 * Returns the number of cargo cars on this train.  
	 * 
	 * CAUTION: the number of actual cars on a train can be changed external
	 * to the Train type.  Make sure this returns a current count of the 
	 * cargo cars on this train.  Tip: call a LinkedList method from here
	 * and make sure that the LinkedList method iterates to count cars.
	 * 
	 * @return the number of cargo cars on this train.
	 */
	public int numCargoCars() {
		// Return Integer that holds the number of cargo cars in a train
		return train.size();
	}

	/**
	 * Returns a reference to the header node from the linked list of
	 * CargoCar nodes.
	 * 
	 * CAUTION: Returning this node allows other
	 * code to change the contents of this train without
	 * calling train methods.  
	 * 
	 * It is being returned in this program to facilitate our testing
	 * and for moving sub-chains of nodes from one train to another.  
	 * THIS METHOD MAY ONLY BE CALLED BY moveMultipleCargoCars()
	 * of the TrainHub class.
	 * 
	 * @return the header node of the chain of nodes from the linked list.
	 */
	public Listnode<CargoCar> getHeaderNode() {
		return train.getHeaderNode();															
	}

	/**
	 * Returns Train with a String format as following.
	 * <p>
	 * {ENGINE_START}{destination}{ENGINE_END}{CARGO_LINK}{cargo}:{weight}{CARGO_LINK}{cargo}:{weight}...
	 * <p>
	 * By default, {ENGINE_START} = ( , {ENGINE_END} = ) and {CARGO_LINK} = -> (defined in {@link Config}).
	 * So if you did not modify Config class, it will generate a String with following format.
	 * <p>
	 * ({destination})->{cargo}:{weight}->{cargo}:{weight}...
	 * 
	 * DO NOT EDIT THIS METHOD
	 * 
	 * @return train as a string format
	 */
	@Override
	public String toString(){
		String builtStr = "";
		
		builtStr += Config.ENGINE_START+this.destination+ Config.ENGINE_END;

		LinkedListIterator<CargoCar> itr = train.iterator();
		while(itr.hasNext()){
			CargoCar item = itr.next();
			builtStr += Config.CARGO_LINK + item.getName() +":" + item.getWeight();
		}
		
		return builtStr;
	}

}
