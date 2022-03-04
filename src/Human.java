/*  -------------------------------------------------------
 *  Human.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Course:	 COSC3117
 *  ID:      169657520
 *  Email:   mferlaino@algomau.ca
 *  Date:	 Nov. 27, 2019
 *  ------------------------------------------------------- */

// Imports
import java.util.ArrayList;

public class Human extends God{
	// Variables
	private int[][] map;
	private int generation, fitnessLevel;
	private ArrayList<String> directions;
	
	// No-arg Constructor
	public Human() {
		map = null;
		generation = fitnessLevel = 0;
	}
	
	// Double-arg Constructor
	public Human(int[][] map, int generation) {
		this.map = map;	
		this.generation = generation;
	}
	
	/*
	 * --- Methods ---
	 * 1. cloneHuman()
	 * 2. describeHuman()
	 * 3. getMap()
	 * 4. getGeneration()
	 * 5. setGeneration()
	 * 6. setMap()
	 */
	
	// cloneHuman()
	public Human cloneHuman(Human human) {
		return new Human(human.getMap(), human.getGeneration());
	}
	
	// describeHuman()
	public void describeHuman() {
		System.out.println("Generation: " + generation);
		System.out.println("Fitness Level: " + fitnessLevel);
		System.out.println("Appearance: " + fitnessLevel);
		int[][] humanMap = map;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) 
				System.out.print(humanMap[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	// getFitnessLevel()
	public int getFitnessLevel() {
		return fitnessLevel;
	}
			
	// getMap()
	public int[][] getMap() {
		return map;
	}

	// getGeneration()
	public int getGeneration() {
		return generation;
	}

	// getDirections()
	public ArrayList<String> getDirections() {
		return directions;
	}
	
	// setGeneration()
	public void setGeneration(int generation) {
		this.generation = generation;
	}
	
	// setMap()
	public void setMap(int[][] map) {
		this.map = map;
	}	

	// setFitnessLevel()
	public void setFitnessLevel(int fitnessLevel) {
		this.fitnessLevel = fitnessLevel;
	}
	
	// setDirections
	public void setDirections(ArrayList<String> directions) {
		this.directions = directions;
	}
}