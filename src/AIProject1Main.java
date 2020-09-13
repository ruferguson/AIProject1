/* Ru Ferguson
 * 8 September 2020
 * 
 * This program will play Mary Had a Little Lamb using the Kontakt MIDI Player and will store 
 * the notes used, the occurrences of each note, and the probabilities of each note occurring 
 * in ArrayLists using the ProbabilityGenerator class. 
 * 
 * Press "1" to activate the Unit Test 1 */

import processing.core.*;

import java.util.*; 

//importing the JMusic stuff
import jm.music.data.*;
import jm.JMC;
import jm.util.*;
import jm.midi.*;

import java.io.UnsupportedEncodingException;
import java.net.*;

//import javax.sound.midi.*;

//make sure this class name matches your file name, if not fix.
public class AIProject1Main extends PApplet {

	MelodyPlayer player; //play a midi sequence
	MidiFileToNotes midiNotes; //read a midi file
	ProbabilityGenerator<Integer> pitchGenerator;
	ProbabilityGenerator<Double> rhythmGenerator;

	public static void main(String[] args) {
		PApplet.main("AIProject1Main"); 
	}

	//setting the window size to 300x300
	public void settings() {
		size(300, 300);
	}

	//doing all the setup stuff
	public void setup() {
		fill(120, 50, 240);
		
		// create my generators for pitch and rhythm
		pitchGenerator = new ProbabilityGenerator<Integer>();
		rhythmGenerator = new ProbabilityGenerator<Double>();

		
		// returns a url
		String filePath = getPath("mid/MaryHadALittleLamb.mid"); // use Mary Had a Little Lamb for Unit Tests
		// String filePath = getPath("mid/gardel_por.mid"); // use this for probabilistic generation
		// playMidiFile(filePath);

		midiNotes = new MidiFileToNotes(filePath); //creates a new MidiFileToNotes -- reminder -- ALL objects in Java must 
												   //be created with "new". Note how every object is a pointer or reference. Every. single. one.

	    // which line to read in --> this object only reads one line (or ie, voice or ie, one instrument)'s worth of data from the file
		midiNotes.setWhichLine(0);

		// call the train function for both pitches and rhythms
		pitchGenerator.train(midiNotes.getPitchArray());
		rhythmGenerator.train(midiNotes.getRhythmArray());

		player = new MelodyPlayer(this, 100.0f);
		
		player.setup();

		// player.setMelody(midiNotes.getPitchArray());
		// player.setRhythm(midiNotes.getRhythmArray());
	
		player.setMelody(pitchGenerator.generate(20)); 
		player.setRhythm(rhythmGenerator.generate(20));
	}

	public void draw() {
	    player.play(); //play each note in the sequence -- the player will determine whether is time for a note onset

		textAlign(CENTER);
		textSize(18);
		fill(125, 0, 255);
		text("Press 1 for Unit Test 1", width/2, height*2/6); // display instructions to the user
		fill(150, 0, 255);
		text("Press 2 for Unit Test 2", width/2, height*3/6); 
		fill(175, 0, 255);
		text("Press 3 for Unit Test 3", width/2, height*4/6);
	}

	//this finds the absolute path of a file
	String getPath(String path) {

		String filePath = "";
		try {
			filePath = URLDecoder.decode(getClass().getResource(path).getPath(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	// this function is not currently called. you may call this from setup() if you want to test
	// this just plays the midi file -- all of it via your software synth. You will not use this
	// function in upcoming projects but it could be a good debug tool.
	void playMidiFile(String filename) {
		Score theScore = new Score("Temporary score");
		Read.midi(theScore, filename);
		Play.midi(theScore);
	}

	//this starts & restarts the melody.
	public void keyPressed() {
		MidiFileToNotes midiNotesMary; // read a midi file
		// returns a url
		String filePath = getPath("mid/MaryHadALittleLamb.mid");
		// playMidiFile(filePath);

		midiNotesMary = new MidiFileToNotes(filePath);
		//creates a new MidiFileToNotes -- reminder -- ALL objects in Java must 
		//be created with "new". Note how every object is a pointer or reference. Every. single. one.


		// which line to read in --> this object only reads one line (or ie, voice or ie, one instrument)'s worth of data from the file
		midiNotesMary.setWhichLine(0);


		if (key == ' ') {
			player.reset();
			println("Melody started!");
		} else if (key == '1') {
			// UNIT TEST 1
			System.out.println("Pitches:\n\n-----Probability Distribution-----\n");
			for (int i = 0; i < pitchGenerator.getAlphabetSize(); i++) {
				System.out.println("Token: " + pitchGenerator.getToken(i) + " | Probability: " +
				pitchGenerator.getProbability(i));
			}
			System.out.println("\n------------\n\nRhythms:\n\n-----Probability Distribution-----\n");
			for (int i = 0; i < rhythmGenerator.getAlphabetSize(); i++) {
				System.out.println("Token: " + rhythmGenerator.getToken(i) + " | Probability: " + 
				rhythmGenerator.getProbability(i));
			}
			System.out.println("\n------------\n");
		} else if (key == '2') {
			// UNIT TEST 2			
			System.out.println("Pitches:");
			System.out.println(pitchGenerator.generate(20));
			System.out.println("\nRhythms:");
			System.out.println(rhythmGenerator.generate(20));
		} else if (key == '3') {
			// UNIT TEST 3
			ProbabilityGenerator<Integer> melodyPitchGen = new ProbabilityGenerator<Integer>();
			ProbabilityGenerator<Double> melodyRhythmGen = new ProbabilityGenerator<Double>();
			ProbabilityGenerator<Integer> probDistPitchGen = new ProbabilityGenerator<Integer>();
			ProbabilityGenerator<Double> probDistRhythmGen = new ProbabilityGenerator<Double>();

			melodyPitchGen.train(midiNotes.getPitchArray());
			melodyRhythmGen.train(midiNotes.getRhythmArray());
			
			for (int i = 0; i < 9999; i++) {
				melodyPitchGen.generate(20);
				melodyRhythmGen.generate(20);
				probDistPitchGen.train(midiNotes.getPitchArray());
				probDistRhythmGen.train(midiNotes.getRhythmArray());
			}
			
			System.out.println("Probability of Generated Pitches after 10,000 iterations of 20 note melodies:\n" + 
					"\nPitches:\n\n-----Probability Distribution-----\n");
			for (int i = 0; i < probDistPitchGen.getAlphabetSize(); i++) {
				System.out.println("Token: " + probDistPitchGen.getToken(i) + " | Probability: " +
				probDistPitchGen.getProbability(i));
			}
			System.out.println("\n------------\n\n"
					+ "Probability of Generated Rhythms after 10,000 iterations of 20 note melodies:"
					+ "\n\nRhythms:\n\n-----Probability Distribution-----\n");
			for (int i = 0; i < probDistRhythmGen.getAlphabetSize(); i++) {
				System.out.println("Token: " + probDistRhythmGen.getToken(i) + " | Probability: " + 
				probDistRhythmGen.getProbability(i));
			}
			System.out.println("\n------------\n");
		} 
	}
}
