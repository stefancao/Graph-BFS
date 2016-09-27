/**
 * Course: EECS 114 Fall 2015
 *
 * First Name: Stefan
 * Last Name: Cao
 * Lab Section: 1A
 * email address: stefanc1@uci.edu
 *
 *
 * Assignment: lab8
 * Filename : Main.java
 *
 * I hereby certify that the contents of this file represent
 * my own original individual work. Nowhere herein is there 
 * code from any outside resources such as another individual,
 * a website, or publishings unless specifically designated as
 * permissible by the instructor or TA.
 */ 

public class Main {

	public static void main(String[] args) {
		
		if(args.length != 1){
			System.out.println("Please provide a file name");
			return;
		}

		// used for debugging
		//Graph newGraph = new Graph("input.txt");
			
		// for Testing
		Graph newGraph = new Graph(args[0]);

		// testing display method
		newGraph.display();
		
		// testing set_levels method
		newGraph.set_levels(newGraph.getSourceVertex());
		
		// testing is_connected method
		System.out.println("is_connected is: " + newGraph.is_connected(newGraph.getSourceVertex()));
		
		// testing display_levels method
		newGraph.display_levels();

	}
}
