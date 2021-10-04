/**
 * @author Joohak Lee 108699180 Recitation 1
 * <dd><code>OrganismNode</code><dd> represents a single node in the tree, each node will be a single species.
 */

public class OrganismNode {
	private String name;
	private boolean isPlant;
	private boolean isHerbivore;
	private boolean isCarnivore;
	private OrganismNode left;
	private OrganismNode middle;
	private OrganismNode right;
	
	public OrganismNode() {
		isPlant = false;
		isCarnivore = false;
		isHerbivore = false;
		name = "";
	}
	/**
	 * setter to set the name of the organism
	 * @param name
	 * the name of the organisms
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * retrieves the name of the organisms
	 * @return
	 * the string that is the name of the organisms
	 */
	public String getName() {
		return name;
	}
	/**
	 * sets the organism as a plant
	 */
	public void setIsPlant() {
		this.isPlant = true;
	}
	/**
	 * retrieves whether the organism is a plant or not
	 * @return
	 * true if the organisms is a plant and false if the organisms is not a plant
	 */
	public boolean getIsPlant() {
		return isPlant;
	}
	/**
	 * sets the organism as a herbivore
	 */
	public void setIsHerbivore() {
		this.isHerbivore = true;
	}
	/**
	 * retrieves whether the organism is a herbivore or not
	 * @return
	 * true if the organisms is a herbivore and false if the organisms is not a herbivore
	 */
	public boolean getIsHerbivore() {
		return isHerbivore;
	}
	/**
	 * sets the organism as a carnivore
	 */
	public void setIsCarnivore() {
		this.isCarnivore = true;
	}
	/**
	 * retrieves whether the organism is a carnivore or not
	 * @return
	 * true if the organisms is a herbivore and false if the organisms is not a herbivore
	 */
	public boolean getIsCarnivore() {
		return isCarnivore;
	}
	/**
	 * retrieves the left Node below of the current node
	 * @return
	 * the OrganismNode on the left node 
	 */
	public OrganismNode getLeft() {
		return left;
	}
	/**
	 * sets the left node as a given node
	 * @param left
	 * a new node to set the left node as
	 */
	public void setLeft(OrganismNode left) {
		this.left = left;
	}
	/**
	 * retrieves the middle node below the cucrnet node
	 * @return
	 * the middle node 
	 */
	public OrganismNode getMiddle() {
		return middle;
	}
	/**
	 * sets the middle node below a current node with a given node
	 * @param middle
	 */
	public void setMiddle(OrganismNode middle) {
		this.middle = middle;
	}
	/**
	 * retrives the right node below a current node
	 * @return
	 * the right node
	 */
	public OrganismNode getRight() {
		return right;
	}
	/**
	 * retrieves the right node below a current node
	 * @param right
	 * a node to replace the right node with
	 */
	public void setRight(OrganismNode right) {
		this.right = right;
	}
	/**
	 * adds child node as a prey node
	 * @param preyNode
	 * child node to replace the aprent node with 
	 * @throws PositionNotAvailableException
	 * throw an error when all the child nodes are not full (not taken)
	 * @throws IsPlantException
	 * throw an error when the current cursor is a plant
	 * @throws DietMismatchException
	 * throw an error when the diet does not match
	 */
	public void addPrey(OrganismNode preyNode) throws PositionNotAvailableException, IsPlantException, DietMismatchException{
		if(this.getIsPlant() == true) {
			throw new IsPlantException();
		}
		else {
			if(this.getLeft() != null && this.getMiddle() != null && this.getRight() != null) {
				throw new PositionNotAvailableException();
			}
			else {
				if(this.getIsHerbivore() == true && this.getIsCarnivore() == false && preyNode.getIsPlant() == false) {
					throw new DietMismatchException();
				}else if (this.getIsCarnivore() == true && this.getIsHerbivore() == false && preyNode.getIsPlant() == true) {
					throw new DietMismatchException();
				}else {
					if(this.getLeft() == null) {
						this.left = preyNode;
					}
					else if (this.getMiddle() == null) {
						this.middle = preyNode;
					}
					else if (this.getRight() == null) {
						this.right = preyNode;
					}
				}
			}
		}
	}
}
