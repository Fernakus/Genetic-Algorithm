/*  -------------------------------------------------------
 *  MarioGame.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Course:	 COSC3117
 *  ID:      169657520
 *  Email:   mferlaino@algomau.ca
 *  Date:	 Nov. 27, 2019
 *  ------------------------------------------------------- */

// Import
import java.util.ArrayList;

// Suppress
@SuppressWarnings("unused")

public class MarioGame{
	// Variables
	private int currRow, currCol;
	private final int SPACE = 0;
	private final int OBSTACLE = 1;
	private final int EXIT = 3;
	private final int MONSTER = 4;	
	
	private final int UP = 1;
	private final int DOWN = 2;
	private final int LEFT = 3;
	private final int RIGHT = 4;
	
	private final int COL = 15;
	private final int ROW = 10;

	// Objects
	private God god;
	private ArrayList<String> directions;
	
	// No-arg Constructor
	public MarioGame() {
		// Instantiate
		god = new God();	
	
		// Assign
		currRow = 2;
		currCol = 0;
	}
	
	/*
	 * --- Methods ---
	 * 1. move()
	 * 3. validMove()
	 * 3. solveMap()
	 * 4. isEqual()
	 * 5. findCurrPosition()
	 * 6. print()
	 */
	
	// move()
	private boolean move(int direction, int rowIndex, int colIndex, int[][] map) {	
		switch (direction) {
			case UP:
				if (!validMove(UP, rowIndex, colIndex, map))
					return false;
					
				map[rowIndex-1][colIndex] = map[rowIndex][colIndex];
				map[rowIndex][colIndex] = SPACE;
				return true;
			
			case DOWN:
				if (!validMove(DOWN, rowIndex, colIndex, map))
					return false;
				
				map[rowIndex+1][colIndex] = map[rowIndex][colIndex];
				map[rowIndex][colIndex] = SPACE;
				return true;

			case LEFT:
				if (!validMove(LEFT, rowIndex, colIndex, map))
					return false;
				
				map[rowIndex][colIndex-1] = map[rowIndex][colIndex];
				map[rowIndex][colIndex] = SPACE;
				return true;

			
			case RIGHT:
				if (!validMove(RIGHT, rowIndex, colIndex, map))
					return false;
				
				map[rowIndex][colIndex+1] = map[rowIndex][colIndex];
				map[rowIndex][colIndex] = SPACE;
				return true;
		}		
		return false;
	}
	
	// validMove()
	private boolean validMove(int direction, int rowIndex, int colIndex, int[][] map) {
		switch (direction) {
			case UP:
				if (rowIndex == 0 || map[rowIndex-1][colIndex] == OBSTACLE || map[rowIndex-1][colIndex] == MONSTER) 
					return false;				
				return true;
				
			case DOWN:
				if (rowIndex == ROW-1 || map[rowIndex+1][colIndex] == OBSTACLE || map[rowIndex+1][colIndex] == MONSTER) 
					return false;				
				return true;
				
			case LEFT:
				if (colIndex == 0 || map[rowIndex][colIndex-1] == OBSTACLE || map[rowIndex][colIndex-1] == MONSTER) 
					return false; 
				return true;
				
			case RIGHT:
				if (colIndex == COL-1 || map[rowIndex][colIndex+1] == OBSTACLE || map[rowIndex][colIndex+1] == MONSTER) 
					return false;
				return true;
		}		
		return false;
	}
	
	// solveMap()
	public ArrayList<String> solveMap(Human human) {		
		// Solving via Brute Force
		ArrayList<String> moves = new ArrayList<String>();
		int[][] map = god.clone(human.getMap());
		int[][] saveMap = god.clone(map);
		
		int chances = 0;
		
		do {
			// Find mario's current position
			findCurrPosition(map);
						
			// Prevents duplicate moves
			int move = 1 + (int)(Math.random() * (4 - 1 + 1));
			
			// Move is valid
			if (move(move, currRow, currCol, map)) {
				findCurrPosition(map);
				if (currRow == 7 && currCol == 13) {
					// Final move
					move(RIGHT, currRow, currCol, map);

					moves.add("east");	
					break;
				}

				else {	
					switch(move) {
						case UP:
							moves.add("north");	
							break;
							
						case DOWN:
							moves.add("south");	
							break;
							
						case LEFT:
							moves.add("west");	
							break;
						
						case RIGHT:
							moves.add("east");	
							break;
					}
				}
			}
			
			// Redo
			if (chances++ > 1000) {
				human = god.clone(god.mutate(human));
				map = god.clone(human.getMap());
				moves = new ArrayList<String>();
				chances = 0;
			}
		}while(true);

		return moves;
	}
	
	// undoPrevMove()
	private void undoPrevMove(String move, int[][] map) {
		switch (move) {
			case "north":
				findCurrPosition(map);
				move(UP, currRow+1, currCol, map);
				break;
				
			case "south":
				findCurrPosition(map);
				move(DOWN, currRow-1, currCol, map);
				break;
				
			case "east":
				findCurrPosition(map);
				move(RIGHT, currRow, currCol-1, map);
				break;
				
			case "west":
				findCurrPosition(map);
				move(LEFT, currRow, currCol+1, map);
				break;
		}
	}
	
	// isEqual()
	private boolean isEqual(int[][] map1, int[][] map2) {
		for (int i = 0; i < ROW; i++) 
			for (int j = 0; j < COL; j++) 
				if (map1[i][j] != map2[i][j])
					return false;
		return true;
	}
	
	// findCurrPosition()
	private void findCurrPosition(int[][] map) {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (map[i][j] == 2) {
					currRow = i;
					currCol = j;
					return;
				}
			}
		}
	}
	
	// getMoves()
	public ArrayList<String> getMoves() {
		return directions;
	}
	
	// print()
	public void print(int[][] map) {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) 
				System.out.print(map[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	// print()
	public void printStarsMap(int[][] map) {
		String[][] newMap = new String[ROW][COL];
		
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) 
				if (map[i][j] == 1 || map[i][j] == 4)
					newMap[i][j] = "*";
				else
					newMap[i][j] = map[i][j] + "";
		}
		
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) 
				System.out.print(newMap[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
}