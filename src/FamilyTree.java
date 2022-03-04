/*  -------------------------------------------------------
 *  FamilyTree.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Course:	 COSC3117
 *  ID:      169657520
 *  Email:   mferlaino@algomau.ca
 *  Date:	 Nov. 27, 2019
 *  ------------------------------------------------------- */

// Imports
import java.util.ArrayList;

public class FamilyTree {
	/*
	 * --- Node Class ---
	 */
	private class Node extends Human{
		// Variables
		private Node left, right;
		private Human human;
		
		// No-arg Constructor
		public Node() {
			human = new Human();
			left = right = null;		
		}
		
		// Single-arg Constructor
		public Node(Human human) {
			this.human = human;
			left = right = null;	
		}
	}
	
	/*
	 * --- FamilyTreeClass Below ---
	 */
	// Variables
	private Node root;
	private int averageFitnessLevel = 0;
	private MarioGame game;
	
	// No-arg Constructor
	public FamilyTree() {
		root = null;
		game = new MarioGame();
		averageFitnessLevel = 0;
	}
	
	/*
	 * --- FamilyTree Methods ---   
	 * 1. evaluatePopulation()    
	 * 2. addToPopulation() 
	 * 3. isFound()
	 * 4. killHuman()	            
	 * 5. findHuman()                         
	 * 6. height()              
	 * 7. prePrint()            
	 * 8. inPrint()             
	 * 9. postPrint()         
	 */

	/*
	 * --- Public Methods ---
	 */
	// naturalSelection()
	public void naturalSelection() {
		naturalSelection(root);
	}
	
	// addToPopulation()
	public void addToPopulation(Human human) {
		root = addToPopulation(human, root);
	}

	// killHuman()
	public boolean killHuman(Human human) {
		if (findHuman(human) == null) 
			return false;
		
		root = killHuman(human, root);
		return true;
	}
	
	// findHuman()
	public Human findHuman(Human human) {
		Node returned = findHuman(human, root);
		return returned.human;
	}
	
	// height()
	public int height() {
		return height(root);
	}

	// getNaturallySelected()
	public ArrayList<Human> getNaturallySelected() {
		return getNaturallySelected(root, new ArrayList<Human>());
	}
	
	// getAverageFitnessLevel()
	public int getAverageFitnessLevel() {
		averageFitnessLevel = getTotalFitnessLevel() / getPopulationSize();
		return averageFitnessLevel;
	}
	
	// getTotalFitnessLevel()
	public int getTotalFitnessLevel() {
		return getTotalFitnessLevel(root);
	}
	
	// populationSize()
	public int getPopulationSize() {
		return getPopulationSize(root);
	}

	// prePrint()
	public void prePrint() {
		prePrint(root);
		System.out.println("null");
	}
	
	// inPrint()
	public void inPrint() {
		inPrint(root);
		System.out.println("nullity");
	}
		
	// postPrint()
	public void postPrint() {
		postPrint(root);
		System.out.println("nullity");
	}
		
	// getRoot()
	public Human getRoot() {
		return root.human;
	}
			
	/*
	 * --- Private Methods ---
	 */
	// naturalSelection()
	private void naturalSelection(Node root) {
		// Base Case 
		if (root == null) 
			return;

		root.human.setDirections(game.solveMap(root.human));
		root.human.setFitnessLevel(root.human.fitness(root.human));
		
		naturalSelection(root.right);
		naturalSelection(root.left);	
	}
	
	// addToPopulation()
	private Node addToPopulation(Human human, Node root) {
		// Base Cases
		if (root == null) {
			root = new Node(human);
			return root;
		}
		
		if (root.human.equals(human)) 
			return root;
		
		// Recursive Calls
		if (human.toString().compareTo(root.human.toString()) < 0) 
			root.left = addToPopulation(human, root.left);
		
		else if (human.toString().compareTo(root.human.toString()) > 0) 
			root.right = addToPopulation(human, root.right);
		
		return root;
	}
	
	// isFound()
	private boolean isFound(Human human1, Human human2) {		
		if (human1.getGeneration() != human2.getGeneration()) 
			return false;
		
		for (int i = 0; i < 10; i++) 
			for (int j = 0; j < 15; j++) 
				if (human1.getMap()[i][j] != human2.getMap()[i][j])
					return false;
		
		return true;
	}
		
	// killHuman()
	private Node killHuman(Human human, Node root) {
		Node newSubtree;
		
		// Base Case
		if (root == null) 
			return root;
		
		// Recursive Calls
		if (isFound(human, root.human)) 
			root = removeNode(human, root);
		
		else if (human.toString().compareTo(root.human.toString()) < 0) {
			newSubtree = killHuman(human, root.left);
			root.left = newSubtree;
		}
		
		else {
			newSubtree = killHuman(human, root.right);
			root.right = newSubtree;
		}
		
		return root;
	}
	
	// removeNode()
	private Node removeNode(Human human, Node root) {
		// Variables
		Node leftMostNode = new Node();
		
		/* -- 4 Cases To Consider --
		 * 1) The root is a leaf. 
		 * 2) The root has no left child.
		 * 3) The root has no right child. 
		 * 4) The root has two children. 
		 */
		if ((root.left == null) && (root.right == null)) 
			return null;
		
		else if (root.left == null) 
			return root.right;
		
		else if (root.right == null) 
			return root.left;
		
		else {
			leftMostNode = findLeftMost(root.right);
			root.human = leftMostNode.human;
			root.right = deleteLeftMost(root.right);
			return root;
		}
	}
	
	// findLeftMost()
	private Node findLeftMost(Node root) {
		// Base Case
		if (root.left == null) 
			return root;
		
		// Recursive Call
		return findLeftMost(root.left);
	}
	
	// deleteLeftMost()
	private Node deleteLeftMost(Node root) {
		// Base Case
		if (root.left == null) 
			return root.right;
		
		// Recursive Call
		else {
			root.left = deleteLeftMost(root.left);
			return root;
		}
	}
	
	// findHuman()
	private Node findHuman(Human human, Node root) {
		// Base Case
		if (root == null) 
			return null;
		
		if (root.human.equals(human)) 
			return root;
		
		// Recursive Call
		if (human.toString().compareTo(root.human.toString()) > 0) 
			return findHuman(human, root.right);
		
		return findHuman(human, root.left);
	}
	
	// height()
	private int height(Node root) {
		// Base Case
		if (root == null) 
			return 0;
		
		// Recursive Calls
		return Math.max(height(root.left), height(root.right)) + 1;
	}

	// getMostFit()
	private ArrayList<Human> getNaturallySelected(Node root, ArrayList<Human> fitPopulation) {
		// Base Case 
		if (root == null) 
			return fitPopulation;
		
		if (root.human.getFitnessLevel() < averageFitnessLevel) 
			fitPopulation.add(root.human);
		
		getNaturallySelected(root.right, fitPopulation);
		getNaturallySelected(root.left, fitPopulation);	
		return fitPopulation;
	}
	
	// getTotalFitnessLevel()
	private int getTotalFitnessLevel(Node root) {
		// Base Case 
		if (root == null) 
			return 0;
		
		// Recursive Traversal
		return root.human.getFitnessLevel() + getTotalFitnessLevel(root.left) + getTotalFitnessLevel(root.right);
	}
	
	// prePrint()
	private void prePrint(Node root) {
		// Base Case 
		if (root == null) 
			return;
		
		// Recursive Traversal
		System.out.print("(" + root.human + ") --> ");
		prePrint(root.left);
		prePrint(root.right);
	}
	
	// populationSize()
	private int getPopulationSize(Node root) {
		// Base Case 
		if (root == null) 
			return 0;
		
		// Recursive Traversal
		return 1 + getPopulationSize(root.left) + getPopulationSize(root.right);
	}
	
	// inPrint()
	private void inPrint(Node root) {
		// Base Case 
		if (root == null) 
			return;
		
		// Recursive Traversal
		inPrint(root.left);
		System.out.print("(" + root.human + ") --> ");
		inPrint(root.right);
	}
	
	// postPrint()
	private void postPrint(Node root) {
		// Base Case 
		if (root == null) 
			return;
		
		// Recursive Traversal
		postPrint(root.left);
		postPrint(root.right);
		System.out.print("(" + root.human + ") --> ");
	}
}
