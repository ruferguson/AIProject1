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
		for (int i = 0; i < newTokens.size() - 1; i++) {
			int index = alphabet.indexOf(newTokens.get(i));
			
			if (index == -1) {
				alphabet.add(newTokens.get(i));
				alphabet_counts.add(0);
			}      
			
			int tempCount = alphabet_counts.get(i) + 1;
			alphabet_counts.add(i, tempCount);
			}
		 /* NOTE: at some point you will want to normalize your counts – ie. divide by total
		    & get values that add to 1. In this version of the algorithm, we do this in
		    generate(). However, if working with larger sets of data, you may want to do this
		    in this step after each train & store normalized values to prevent overflow problems.
		    However, for this project and for assigned projects, this is unlikely, so feel free
		    to skip until you enter in an enormous amount of data yourself and the numbers don’t
		    add up. Live dangerously, is what I am saying */
	}
	
	/*
	T generate() {
		T newToken = null;
		
		// actual java code. yw!! Note that you will not be able to use processing code
		// inside your class unless you somehow reference your main class. 
		double totalNotes = 0;
		for (int i = 0; i < alphabet.size() - 1; i++) {
			totalNotes = totalNotes + alphabet.get(i);
		}
		
		ArrayList<Double> normalized = new ArrayList<Double>();
		for (int i = 0; i < alphabet_counts.size(); i++) {
			normalized.add((double) alphabet_counts.get(i) / totalNotes); 
		}

		
		float rIndex = (float) Math.random();
		if (rIndex < normalized.get(1)) {
			// then a
			System.out.println("a");
		} else if (rIndex < normalized.get(2)) {
			// then b
			System.out.println("b");
		} else if (rIndex < normalized.get(3)) {
			// then c
			System.out.println("c");
		} else if (rIndex < normalized.get(4)) {
			// then d
			System.out.println("d");
		} else if (rIndex < normalized.get(5)) {
			// then e
			System.out.println("e");
		} else if (rIndex < normalized.get(6)) {
			// then f
			System.out.println("f");
		} else if (rIndex < normalized.get(7)) {
			// then g
			System.out.println("g");
		}
		
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
	*/
}
