/**
 * @author Joohak Lee 108699180 Recitation 1
 * This class implements the ternary tree of <dd><code>OrganismNode</code><dd> objects, and  
 * allow various operations such as insertion, removal, printing, moving the cursor reference, and more.
 */

public class OrganismTree {
	private OrganismNode root;
	private OrganismNode cursor;
	/**
	 * Constructor that creates a new OrganismTree with apexPredator as the root.
	 * @param apexPredator
	 * The apex predator of the food pyramid. This must be an animal, as plants cannot be predators. 
	 * @throws IsPlantException
	 * throws an error when the apexPredator is a plant.
	 */
	public OrganismTree(OrganismNode apexPredator) throws IsPlantException {
		root = apexPredator;
		cursor = root;
	}
	/**
	 * Moves the cursor back to the root of the tree.
	 */
	public void cursorReset() {
		cursor = root;
	}
	/**
	 *  retrieves the cursor Node
	 * @return
	 *  the node which is a curosor
	 */
	public OrganismNode getCursor() {
		return cursor;
	}
	/**
	 * Moves cursor to one of cursor’s children.
	 * @param name
	 * he name of the node to be moved to.
	 * @throws IllegalArgumentException
	 * Thrown if name does not reference a direct, valid child of cursor.
	 */
	public void moveCursor(String name) throws IllegalArgumentException{
		if(!cursor.getLeft().getName().equals(name) && !cursor.getMiddle().getName().equals(name) && 
				!cursor.getRight().getName().equals(name)) {
			throw new IllegalArgumentException("Error: This prey does not exist for this predator.");
		}
		else {
			OrganismNode newRoot = new OrganismNode();
			newRoot = cursor;
			if(cursor.getLeft().getName().equals(name)) {
				cursor = cursor.getLeft();
			}
			else if(cursor.getMiddle().getName().equals(name)) {
				cursor = cursor.getMiddle();
			}
			else if (cursor.getRight().getName().equals(name)) {
				cursor = cursor.getRight();
			}
		}
	}
	/**
	 * Returns a String including the organism at cursor and all its possible prey
	 * @return
	 * A String containing the name of the cursor, and all the cursor’s possible prey.
	 * @throws IsPlantException
	 * IsPlantException: Thrown if the cursor currently references a plant object. 
	 */
	public String listPrey() throws IsPlantException{
		if(cursor.getIsPlant()) {
			throw new IsPlantException();
		}
		else {
			String organisms = "";
			if(cursor.getLeft() != null) {
				organisms = cursor.getName() + " -> " + cursor.getLeft().getName();
			}
			if (cursor.getMiddle() != null) {
				organisms = cursor.getName() + " -> " + cursor.getLeft().getName() + ", " + cursor.getMiddle().getName();
			}
			if (cursor.getRight() != null) {
				organisms = cursor.getName() + " -> " + cursor.getLeft().getName() + ", " + cursor.getMiddle().getName() + ", " + 
				cursor.getRight().getName();
			}
			return organisms;
		}
	}
	/**
	 * Returns a String containing the path of organisms that leads from the apex predator (root) to the organism at cursor. 
	 * @return
	 * A String containing the food chain from the apex predator to the cursor.
	 */
	public String listFoodChain() {
		OrganismNode newCursor = root;
		String [] names = new String [10]; 
		int count = 0;
		String foodChain = "";
		Searching(newCursor, names, count, cursor);
		for(int i = 0; i < names.length; i++) {
			if(names[i+1] == null) {
				foodChain += names[i];
				break;
			}
			if(names[i] != null) {
				foodChain += names[i] + " -> ";
			}
		}
		return foodChain;
	}
	/**
	 * Search method to get the food chians for the listFoodChain method
	 * @param node
	 * the root node of the tree
	 * @param names
	 * String array to contain the name of the child nodes
	 * @param count
	 * integer that counts how many childs it will have
	 * @param cursor
	 * the location of the current cursor (end location)
	 * @return
	 * false if not found, true if found.
	 */
	public boolean Searching(OrganismNode node, String [] names, int count,OrganismNode cursor) {
		if(node == null) {
			return false;
		}
		names[count] = node.getName();
		if (node == cursor) {
			return true;
		}
		else if(Searching(node.getLeft(), names , count + 1, cursor)) {
			return true;
		}
		else if(Searching(node.getMiddle(), names, count +1,cursor)) {
			return true;
		}
		else if (Searching(node.getRight(), names ,count +1,cursor)) {
			return true;
		}
		else {
			names[count] = null;
			count--;
			return false;
		}
	}
	/**
	 * Prints out a layered, indented tree by performing a preorder traversal starting at cursor.
	 */
	public void printOrganismTree() {
		OrganismNode newCursor = new OrganismNode();
		newCursor = cursor;
		int tab = 0;
		System.out.println("| - " + newCursor.getName());
		printOrganismTree(newCursor.getLeft(),tab+1);
		printOrganismTree(newCursor.getMiddle(),tab+1);
		printOrganismTree(newCursor.getRight(),tab+1);
		}
	/**
	 * Prints out a layered, indented tree by performing a preorder traversal starting at cursor.
	 * @param node
	 * node of the cursor to start at 
	 * @param tab
	 * integer to show how many tabs to place in front
	 */
	public void printOrganismTree(OrganismNode node, int tab) {
		if(node == null) {
			return;
		}
		if(node.getLeft() != null || node.getMiddle() != null || node.getRight() != null) {
			for(int i = 0; i < tab;i ++) {
				System.out.print("\t");
			}
			System.out.println("| - " + node.getName());
		}
		else {
			for(int i = 0; i < tab; i++) {
				System.out.print("\t");
			}
			System.out.println("- " + node.getName());
		}
		printOrganismTree(node.getLeft(), ++tab);
		tab --;
		printOrganismTree(node.getMiddle(), ++tab);
		tab --;
		printOrganismTree(node.getRight(), ++tab);
		tab --;
		return;
	}
	/**
	 * Returns a list of all plants currently at cursor and beneath cursor in the food pyramid. 
	 * @return
	 * A String containing a list of all the plants in the food pyramid.
	 */
	public String listAllPlants() {
		OrganismNode newCursor = cursor;
		String [] names = new String [1000]; 
		int count = 0;
		String plantList = "";
		plantSearching(newCursor, names, count);
		return plantList;
	}
	/**
	 * Searches the tree to find the plants
	 * @param node
	 * cursor node where the search will start at 
	 * @param names
	 * String array to contain the names of the plants
	 * @param count
	 * integer that shows how many plants are in the list
	 * @return
	 * false, if the node is not a plant, true if it's a plant.
	 */
	public boolean plantSearching(OrganismNode node, String [] names, int count) {
		if(node == null) {
			return false;
		}
		if(node.getIsPlant() == true) {
			System.out.print(node.getName() + ", ");
		}
		if(plantSearching(node.getLeft(), names, count)) {
			return true;
		}
		else if(plantSearching(node.getMiddle(), names, count)) {
			return true;
		}
		else if (plantSearching(node.getRight(), names ,count)) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Creates a new animal node with a specific name and diet and adds it as a child of the cursor node.
	 * @param name
	 * The name of the child node.
	 * @param isHerbivore
	 * isHerbivore value depending on whether the animal consumes plants.
	 * @param isCarnivore
	 * isCarnivore value depending on whether the animal consumes other animals.
	 * @throws IllegalArgumentException
	 * Thrown if name references an exact name with one of its would-be siblings.
	 * @throws PositionNotAvailableException
	 * Thrown if there is no available child position for a new node to be added.
	 * @throws DietMismatchException
	 * Thrown if the diet does not match
	 * @throws IsPlantException
	 * Thrown if the cursor is at the plant node
	 */
	public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) 
			throws IllegalArgumentException, PositionNotAvailableException, DietMismatchException, IsPlantException{
		if((cursor.getLeft() != null &&name.equals(cursor.getLeft().getName())) || (cursor.getMiddle() != null && 
				name.equals(cursor.getMiddle().getName())) || (cursor.getRight() != null && name.equals(cursor.getRight().getName()))) {
			throw new IllegalArgumentException();
		}
		else {
			OrganismNode animal = new OrganismNode();
			animal.setName(name);
			if(isHerbivore == true) {
				animal.setIsHerbivore();
			}
			if(isCarnivore == true) {
				animal.setIsCarnivore();
			}
			try {
				cursor.addPrey(animal);
			} catch (IsPlantException e) {
				throw new IsPlantException();
			} catch (PositionNotAvailableException e) {
				throw new PositionNotAvailableException();
			}
		}
	}
	/**
	 * Creates a new plant node with a specific name and adds it as a child of the cursor node.
	 * @param name
	 * The name of the child node. 
	 * @throws IllegalArgumentException
	 *  Thrown if name references an exact name with one of its would-be siblings.
	 * @throws PositionNotAvailableException
	 * Thrown if there is no available child position for a new node to be added.
	 * @throws DietMismatchException
	 * Thrown if the diet does not match
	 * @throws IsPlantException
	 * Thrown if the cursor is at the plant node
	 */
	public void addPlantChild(String name) throws IllegalArgumentException, PositionNotAvailableException, DietMismatchException, IsPlantException{
		if((cursor.getLeft() != null && name.equals(cursor.getLeft().getName())) || (cursor.getMiddle() != null && 
				name.equals(cursor.getMiddle().getName())) || (cursor.getRight() != null && name.equals(cursor.getRight().getName()))) {
			throw new IllegalArgumentException();
		}
		else {
			OrganismNode plant = new OrganismNode();
			plant.setName(name);
			plant.setIsPlant();
			try {
				cursor.addPrey(plant);
			} catch (IsPlantException e) {
				throw new IsPlantException();
			}
		}
	}
	/**
	 * Removes the child node of cursor with name name, and properly shifts the deleted node’s other siblings if necessary. 
	 * @param name
	 * The name of the node to be deleted.
	 * @throws IllegalArgumentException
	 * Thrown if name does not reference a direct child of the cursor.
	 */
	public void removeChild(String name)throws IllegalArgumentException{
		if (cursor.getLeft() == null) {
			throw new IllegalArgumentException();
		}
		if(cursor.getLeft() != null && (!name.equals(cursor.getLeft().getName())) 
				&& (cursor.getMiddle() != null && !name.equals(cursor.getMiddle().getName())) 
				&& (cursor.getRight() != null && !name.equals(cursor.getRight().getName()))) {
			throw new IllegalArgumentException();
		}
		else {
			if(name.equals(cursor.getLeft().getName())) {
				cursor.setLeft(cursor.getMiddle());
				cursor.setMiddle(cursor.getRight());
				cursor.setRight(null);
			}
			else if(name.equals(cursor.getMiddle().getName())) {
				cursor.setMiddle(cursor.getRight());
				cursor.setRight(null);
			}
			else {
				cursor.setRight(null);
			}
		}
	}
}
