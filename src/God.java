/*  -------------------------------------------------------
 *  God.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Course:	 COSC3117
 *  ID:      169657520
 *  Email:   mferlaino@algomau.ca
 *  Date:	 Nov. 27, 2019
 *  ------------------------------------------------------- */

// Imports
import java.util.ArrayList;

// Imports
public class God{
	// Variables
	private final int COL = 15;
	private final int ROW = 10;	
	private int generation;
	private ArrayList<FamilyTree> populations;
	
	public final int[][] ORIGINAL_FATHER_MAP =  {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				 							     {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 4, 0, 1},
				 							     {2, 0, 0, 0, 0, 4, 0, 0, 1, 1, 1, 0, 0, 0, 1},
				 							     {1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
				 							     {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
				 							     {1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
				 							     {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},
				 							     {1, 4, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 3},
				 							     {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 1},
				 							     {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};	

	public final int[][] ORIGINAL_MOTHER_MAP =  {{1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
				 								 {1, 0, 1, 0, 0, 4, 0, 0, 1, 1, 1, 0, 4, 4, 1},
				 								 {2, 0, 0, 0, 0, 4, 0, 0, 1, 1, 1, 0, 0, 0, 1},
				 								 {1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
				 								 {1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1}, 
				 								 {1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
				 								 {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 4, 1}, 
				 								 {1, 4, 1, 1, 0, 0, 0, 1, 0, 4, 0, 0, 0, 0, 3}, 
				 								 {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 1}, 
				 								 {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

	// No-arg Constructor
	public God() {
		populations = new ArrayList<FamilyTree>();
		generation = 0;
	}
	
	
	/*
	 * --- Methods ---
	 * 1. clone()
	 * 2. crossover()
	 * 3. mutate()
	 * 4. reverseChromosomes()
	 * 5. fitness()
	 * 6. birthPopulation()
	 * 7. birthParent()
	 * 8. birthOptimalPopulation()
	 * 9. findOptimalHuman()
	 */

	// clone()
	public Human clone(Human human) {
		int[][] map = new int[ROW][COL];
		
		for (int i = 0; i < ROW; i++) 
			for (int j = 0; j < COL; j++) 
				map[i][j] = human.getMap()[i][j];
		
		return new Human(map, human.getGeneration());
	}
	
	// clone()
	public int[][] clone(int[][] map) {
		int[][] newArray = new int[ROW][COL];
		
		for (int i = 0; i < ROW; i++) 
			for (int j = 0; j < COL; j++) 
				newArray[i][j] = map[i][j];
		
		return newArray;
	}
	
	// isDiseased()
	public boolean isDiseased(Human human) {
		if (human.getMap()[2][0] != 0) 
			return true;
		
		else if (human.getMap()[7][COL-1] != 3) 
			return true;
		
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (i == 9 && human.getMap()[i][j] == 0 || i == 0 && human.getMap()[i][j] == 0) 
					return true;
				
				else if (j == 0 && human.getMap()[i][j] == 0 || j == 14 && human.getMap()[i][j] == 0 ) 
					return true;
			}
		}
		return false;
	}
	
	// cureDiseased()
	public Human cureDiseased(Human human) {
		if (human.getMap()[2][0] != 0) 
			human.getMap()[2][0] = 2;
		
		else if (human.getMap()[7][COL-1] != 3) 
			human.getMap()[7][COL-1] = 3;
		
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (i == 9 && human.getMap()[i][j] == 0 || i == 0 && human.getMap()[i][j] == 0) 
					human.getMap()[i][j] = 1;
				
				else if (j == 0 && human.getMap()[i][j] == 0 || j == 14 && human.getMap()[i][j] == 0 ) 
					human.getMap()[i][j] = 1;
			}
		}
		
		return human;
	}

	// crossover()
	public Human crossover(Human mother, Human father) {
		// Random Cut Points
		int firstCutPoint = 0 + (int)(Math.random() * (COL - 0 + 1));
		int secondCutPoint = 0;
		
		if (firstCutPoint + 4 < COL)
			secondCutPoint = firstCutPoint + 4;
		else 
			secondCutPoint = firstCutPoint - 4;
		
		// Birth child
		int[][] child = new int[ROW][COL];
		int[] chromosomes = new int[15];
		
		// Creating Child
		if (firstCutPoint < secondCutPoint) {
			for (int i = 0; i < ROW; i++) {
				// Grab chromosomes from mother
				for (int j = 0; j < chromosomes.length; j++) { 
					if (j > firstCutPoint && j < secondCutPoint) 
						continue;
					chromosomes[j] = mother.getMap()[i][j];
				}
							
				// Grab chromosomes from father from between the two cutpoints and add to the child
				for (int j = firstCutPoint; j < secondCutPoint; j++) 
					child[i][j] = father.getMap()[i][j];
				
				// Adding the random chromosomes
				for (int j = 0; j < COL; j++) {
					if (j <= firstCutPoint) 
						child[i][j] = chromosomes[j];
					
					else if (j >= secondCutPoint) 
						child[i][j] = chromosomes[j];				
				}
			}
		}
		
		// Creating Child
		else {
			for (int i = 0; i < ROW; i++) {
				// Grab chromosomes from mother
				for (int j = 0; j < chromosomes.length; j++) { 
					if (j > firstCutPoint && j < secondCutPoint) 
						continue;
					chromosomes[j] = mother.getMap()[i][j];
				}
							
				// Grab chromosomes from father from between the two cutpoints and add to the child
				for (int j = secondCutPoint; j < firstCutPoint; j++) 
					child[i][j] = father.getMap()[i][j];
				
				// Adding the random chromosomes
				for (int j = 0; j < COL; j++) {
					if (j <= secondCutPoint) 
						child[i][j] = chromosomes[j];
					
					else if (j >= firstCutPoint) 
						child[i][j] = chromosomes[j];				
				}
			}
		}
		
		// Diseased Human
		Human human = new Human(child, generation++);		
		if (isDiseased(human))
			cureDiseased(human);
		
		return human;
	}

	// mutate()
	public Human mutate(Human human) {
		// Random Cut Points
		int firstCutPoint = 0 + (int)(Math.random() * (COL - 0 + 1));
		int secondCutPoint = 0;
		
		if (firstCutPoint + 4 < COL)
			secondCutPoint = firstCutPoint + 4;
		else 
			secondCutPoint = firstCutPoint - 4;
		
		// Birth Mutation
		int[][] mutatedChildMap = clone(human.getMap());
			
		// Creating Child
		if (firstCutPoint < secondCutPoint) {
			for (int i = 0; i < ROW; i++) {
				// Grab chromosomes from child from before firstCutPoint
				for (int j = 0; j < firstCutPoint; j++) 
					mutatedChildMap[i][j] = human.getMap()[i][j];
							
				// Grab chromosomes from child from between the two cutpoints and add to the mutatedChild in reverse
				mutatedChildMap = reverseChromosomes(mutatedChildMap, firstCutPoint, secondCutPoint, i);
				
				// Grab chromosomes from father from after secondCutPoint
				for (int j = secondCutPoint; j < COL; j++) 
					mutatedChildMap[i][j] = human.getMap()[i][j];
			}
		}
		
		// Creating Child
		else {
			for (int i = 0; i < ROW; i++) {
				// Grab chromosomes from child from before firstCutPoint
				for (int j = 0; j < secondCutPoint; j++) 
					mutatedChildMap[i][j] = human.getMap()[i][j];
							
				// Grab chromosomes from child from between the two cutpoints and add to the mutatedChild in reverse
				mutatedChildMap = reverseChromosomes(mutatedChildMap, firstCutPoint, secondCutPoint, i);
				
				// Grab chromosomes from father from after secondCutPoint
				for (int j = firstCutPoint; j < COL; j++) 
					mutatedChildMap[i][j] = human.getMap()[i][j];
			}
		}
		
		return new Human(mutatedChildMap, human.getGeneration()+1);
	}
	
	// reverseChromosomes()
	public int[][] reverseChromosomes(int[][] mutatedChildMap, int firstCutPoint, int secondCutPoint, int row) {
		// Variables
		int[][] shiftedMiddle = clone(mutatedChildMap);
		int[] middleChromosomes;
		
		if (firstCutPoint < secondCutPoint) {
			int middleIndex = secondCutPoint - firstCutPoint;
			middleChromosomes = new int[middleIndex];
			
			// Strip middle
			for (int j = 0; j < middleChromosomes.length; j++) 
				middleChromosomes[j] = mutatedChildMap[row][middleIndex++];
			
			// Reverse middle 
			int i = 0;
			for (int j = middleChromosomes.length; j > 0; j--) 
				shiftedMiddle[row][j] = middleChromosomes[i++];
		}
		
		else {
			int middleIndex = firstCutPoint - secondCutPoint;
			middleChromosomes = new int[middleIndex];
			
			// Strip middle
			for (int j = 0; j < middleChromosomes.length; j++) 
				middleChromosomes[j] = mutatedChildMap[row][middleIndex++];
			
			// Reverse middle
			int i = 0;
			for (int j = middleChromosomes.length; j > 0; j--) 
				shiftedMiddle[row][j] = middleChromosomes[i++];
		}
		
		return shiftedMiddle;
	}
	
	// fitness()
	public int fitness(Human human) {		
		return 100 - human.getDirections().size();
	}
	
	// birthPopulation()
	public FamilyTree birthPopulation(int size) {
		// Starting Population
		FamilyTree population = new FamilyTree();
		Human mother = birthParent();
		Human father = birthParent();
		
		// Cure diseased parents
		if (isDiseased(mother)) 
			mother = cureDiseased(mother);
		else 
			father = cureDiseased(father);			
		
		population.addToPopulation(mother);
		population.addToPopulation(father);	
		
		// Initialize a population of 1000
		int mutationRate = (int)(size * 0.01);
		
		for (int i = 2; i < size; i++) {
			Human human = crossover(mother, father);
			
			// Cure diseased humans
			if (isDiseased(human)) 
				cureDiseased(human);
			
			// Out of a population of 1000 we mutate every 10th human to keep a mutation rate of 1%
			if (i % mutationRate == 0) {
				human = mutate(human);
				
				// Cure diseased humans
				if (isDiseased(human))
					cureDiseased(human);
				
				population.addToPopulation(human);
			}
			
			else 
				population.addToPopulation(human);
		}
		
		// Add to the populations list
		populations.add(population);
		
		return population;
	}
	
	// birthParent()
	public Human birthParent() {
		return crossover(new Human(ORIGINAL_FATHER_MAP, 0), new Human(ORIGINAL_MOTHER_MAP, 0));
	}

	// birthOptimalPopulation()
	public FamilyTree birthOptimalPopulation(ArrayList<Human> mostFitPeople) {
		FamilyTree optimalPopulation = new FamilyTree();

		for (int i = 0; i < mostFitPeople.size() - 1; i++) 
			optimalPopulation.addToPopulation(crossover(mostFitPeople.get(i), mostFitPeople.get(i+1)));		
		
		return optimalPopulation;
	}
	
	// findOptimalHuman()
	public Human findOptimalHuman() {
		// Create Initial Population
		FamilyTree population = birthPopulation(1000);
		
		// Add to legacy
		populations.add(population);

		// Evaluate current population
		population.naturalSelection();
		ArrayList<Human> mostFitPeople = population.getNaturallySelected();
		
		// Find the most fit human
		do {
			// Birth optimal population from most fit people
			population = birthOptimalPopulation(mostFitPeople);
			populations.add(population);
			
			if (mostFitPeople.size() == 0) {
				population = populations.get(populations.size() - 1);
				return population.getRoot();
			}
			
			if (mostFitPeople.size() == 1) 
				return mostFitPeople.get(0);
					
			System.out.println("Populations: " + populations.size());
			System.out.println("New Fit Population Size: " + population.getPopulationSize());
			System.out.println();
			
			// Evaluate optimal population
			population.naturalSelection();
			mostFitPeople = population.getNaturallySelected();
		} while(true);
	}
}
