import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * This class provide methods for generating a Train.
 * 
 * COMPLETE THIS CLASS and HAND IN THIS FILE
 * 
 * @see Config
 */
public class TrainGenerator {
	
	/**
	 * Get a new train generated randomly.
	 * The constant variables for determining how many cargo, 
	 * what cargo and how heavy are in {@link Config}.
	 * 
	 * @return a train generated randomly
	 */
	public static Train getIncomingTrain(){
		Train incomingTrain = new Train("TrainHub");
		
		Random rand = new Random(System.currentTimeMillis());

		// How many freight cars
		int cartNum = rand.nextInt(Config.MAX_CART_NUM - Config.MIN_CART_NUM + 1) + Config.MIN_CART_NUM;

		for(int i=0;i<cartNum;i++){
			// What kind of cargo
			int loadIndex = rand.nextInt(Config.CARGO_ARRAY.length);
			String load = Config.CARGO_ARRAY[loadIndex];

			// How heavy
			int weight = rand.nextInt(Config.MAX_WEIGHT - Config.MIN_WEIGHT + 1) + Config.MIN_WEIGHT;

			// Where to
			int destIndex = rand.nextInt(Config.DEST_ARRAY.length);
			String dest = Config.DEST_ARRAY[destIndex];
			
			incomingTrain.add(new CargoCar(load, weight, dest));
		}
		
		return incomingTrain;
	}
	
	/**
	 * Get a new train generated from a file.
	 * Files for generating a train must have the format as follow
	 * <p>
	 * {destination},{cargo},{weight}<br>
	 * {destination},{cargo},{weight}<br>
	 * ...
	 * <p>
	 * where {destination} is a string among Config.DEST_ARRAY,
	 * {cargo} is a string among Config.CARGO_ARRAY,
	 * and {weight} is a string for any positive integer.
	 * <p>
	 * Ignore the line if it is not matched in this format. 
	 * See the sample in/outputs provided in the assignment description to get more details.
	 * 
	 * @param filename train input file name
	 * @return a train generated from a file
	 */
	public static Train getIncomingTrainFromFile(String filename) {
	
		
		// Create a reference to a train object for later return
		Train train = null;
		
		// String variable to store each line of train information
		String info = null;
		
		// Used to store what's after split
		String splited[];
		
		// Variable that holds the weight of a train
		int weight = 0;

		CargoCar c = null;
		try {
			// Create a file with filename imported
			File a = new File(filename);
			
			// Create a scanner to read the file
			Scanner scnr = new Scanner(a);

			train = new Train("TrainHub");
			// Read each line from file
			while(scnr.hasNextLine()){
				
				// Get information of each cargo car from file
				info = scnr.nextLine();

				// Split the line with comma
				splited = info.split(",");

				// If there is three elements, check if the third one is a integer
				if(splited.length == 3){
					try{
						// Change an String to int and assign to weight
						weight = Integer.parseInt(splited[2].trim()); 
						
						// When weight is small than 0, read the next line
						if(weight < 0 ){
							continue;
						}
						
						// Create a new Cargo car with information 
						c = new CargoCar(splited[1].trim(), weight,
								splited[0].trim());
						
					}catch(NumberFormatException e){
						// When cannot correctly transfer, go back to read next line
						continue;
					}
				}
				// If the line has more than 3 elements, skip to next line
				else{
					continue;
				}

				// Add the Cargo car to the train
				train.add(c);
			}
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Return the train read from file
		return train;
		
	}
}
