import java.util.ArrayList;

// comment the heck out of everything here
// Programmer: Ru Ferguson
// 9/2/2020
// Description
/* Your Project 1: 

	With train() & unit 1 working

	30% --> Style -- 7.5% each numbered item 
		1. Principle of maximum re-use / code smallness
				Code is not repeated (eg. methods or loops are used appropriately)
		
		2. Principle of encapsulation
				Methods are no more than 35 lines long. 
				Data is accessed in classes via get/set
				Each method and class does or represents just one thing
		
		3. Principle of prototype iteration
				2 git commits on 2 separate days not including the day of the deadline 
		
		4. Comments and Naming Conventions
				Class header for each class/file -- name/date/description. My name should not be in the main class. 
				Classes and other variables are named descriptively. 
				Every method and function is commented with what it does and what each parameter is
	35% train() works
	30% unit test 1 works
	5% unit test 1 runs from the GUI as per Project #1 guidelines.
*/

public class ProbabilityGenerator<T> {
	
	ArrayList<T> alphabet;
	ArrayList<Integer> alphabet_counts;
	
	ProbabilityGenerator() {
		alphabet = new ArrayList<T>();
		alphabet_counts = new ArrayList<Integer>();
	}
	
	// it is training probability generator with new data
	void train(ArrayList<T> newTokens) {
		// you will code the training function that will add new Tokens to alphabet
		// do not erase alphabet counts
	}
	
	T generate() {
		T newToken = null;
		// do something here
		return newToken;
	}
	
	ArrayList<T> generate( int length ) {
		ArrayList<T> newSequence = new ArrayList<T>();
		
		for (int i = 0; i < length; i++) {
			newSequence.add(generate());
		}
		
		return newSequence;
	}

}
