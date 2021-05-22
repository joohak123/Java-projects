/**
 * @author Joohak Lee 108699180 Recitation 1
 * This class should contain an <dd><code>OrganismTree</code><dd>member variable named tree that will
 * serve as the tree for the user to interact with.
 * hosts main method.
 * Upon the user start of the program, the user should be asked about the apex predator to get information on its name and diet.
 * This information for the apex predator cannot be changed after the user has entered it. 
 */

import java.util.Scanner;
public class FoodPyramid {
	private static OrganismTree tree;
	public FoodPyramid() {
	}
	public static void main(String [] args) {
		boolean quit = false;
		Scanner input = new Scanner(System.in);
		System.out.print("What is the name of the apex predator?: ");
		String apexName = input.nextLine();
		System.out.print("Is the organism an herbivore / a carnivore / an omnivore? (H / C / O): ");
		String feature = input.nextLine();
		feature = feature.toUpperCase();
		boolean featureTest = false;
		if(feature.equals("H")){
			featureTest = true;
		}
		if(feature.equals("C")) {
			featureTest = true;
		}
		if(feature.equals("O")) {
			featureTest = true;
		}
		while(featureTest == false) {
			System.out.println("please enter H, C or O!!");
			System.out.print("Is the organism an herbivore / a carnivore / an omnivore? (H / C / O): ");
			feature = input.nextLine();
			feature = feature.toUpperCase();
			if(feature.equalsIgnoreCase("H")){
				featureTest = true;
			}
			else if(feature.equalsIgnoreCase("C")) {
				featureTest = true;
			}
			else if(feature.equalsIgnoreCase("O")) {
				featureTest = true;
			}
		}
		System.out.println("Constructing food pyramid. . .");
		OrganismNode apexAnimal = new OrganismNode();
		apexAnimal.setName(apexName);
		if(feature.equals("H")) {
			apexAnimal.setIsHerbivore();
		}
		else if(feature.equals("C")) {
			apexAnimal.setIsCarnivore();
		}
		else if(feature.equals("O")) {
			apexAnimal.setIsCarnivore();
			apexAnimal.setIsHerbivore();
		}
		try {
			tree = new OrganismTree(apexAnimal);
		} catch (IsPlantException e) {
		}
		while(quit == false) {
			System.out.println("\n"+ "Menu:");
			System.out.println(String.format("%-4s%s", "(PC)", " - Create New Plant Child"));
			System.out.println(String.format("%-4s%s", "(AC)", " - Create New Animal Child"));
			System.out.println(String.format("%-4s%s", "(RC)", " - Remove Child"));
			System.out.println(String.format("%-4s%s", "(P)", " - Print Out Cursor's Prey"));
			System.out.println(String.format("%-4s%s", "(C)", " - Print Out Food Chain"));
			System.out.println(String.format("%-4s%s", "(F)", " - Print Out Food Pyramid at Cursor"));
			System.out.println(String.format("%-4s%s", "(LP)", " - List All Plants Supporting Cursor"));
			System.out.println(String.format("%-4s%s", "(R)", " - Reset Cursor to Root"));
			System.out.println(String.format("%-4s%s", "(M)", " - Move Cursor to Child"));
			System.out.println(String.format("%-4s%s", "(Q)", " - Quit"));
			System.out.print("\nPlease select an option: ");
			String option = input.nextLine();
			option = option.toUpperCase();
			switch(option){
			case "PC":
					System.out.print("What is the name of the organism?: ");
					String name = input.nextLine();
					try {
						tree.addPlantChild(name);
						System.out.println("\nA(n) "+ name + " has successfully been added as prey for the " + tree.getCursor().getName() + "!");
						option = "";
						break;
					} catch (DietMismatchException e) {
						System.out.println("\nERROR: This prey cannot be added as it does not match the diet of the predator.");
						option = "";
						break;
					} catch (IllegalArgumentException e) {
						System.out.println("\nERROR: This prey already exists for this predator.");
						option = "";
						break;
					} catch (PositionNotAvailableException e) {
						System.out.println("\nERROR: There is no more room for more prey for this predator.");
						option = "";
						break;
					} catch (IsPlantException e) {
						System.out.println("\nERROR: THe cursor is at a plant node. Plants cant have preys.");
						option = "";
						break;
					}
			case "AC":
				System.out.print("What is the name of the organism?: ");
				String name2 = input.nextLine();
				System.out.print("Is the organism an herbivore / a carnivore / an omnivore? (H / C / O):");
				String test = input.nextLine();
				test = test.toUpperCase();
				boolean isHerbivore = false, isCarnivore = false;
				if(test.equals("H")) {
					isHerbivore = true;
				}
				else if(test.equals("C")) {
					isCarnivore = true;
				}
				else if(test.equals("O")) {
					isHerbivore = true;
					isCarnivore = true;
				}
				else {
					System.out.println("\nOrganism has to be either an herbivore / a carnivore/ an omnivore!!");
					option = "";	
					break;
				}
				try {
					tree.addAnimalChild(name2,isHerbivore, isCarnivore);
					System.out.println("\nA(n) " + name2 + " has successfully been added as prey for the " + tree.getCursor().getName() + "!");
					option = "";
					break;
				} catch (DietMismatchException e) {
					System.out.println("\nERROR: This prey cannot be added as it does not match the diet of the predator.");
					option = "";
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("\nERROR: This prey already exists for this predator.");
					option = "";
					break;
				} catch (PositionNotAvailableException e) {
					System.out.println("\nERROR: There is no more room for more prey for this predator.");
					option = "";
					break;
				} catch (IsPlantException e) {
					System.out.println("\nERROR: THe cursor is at a plant node. Plants cant have preys.");
					option = "";
					break;
				}
			case "RC":
				System.out.print("What is the name of the organism to be removed?: ");
				String removeName = input.nextLine();
				try{
					tree.removeChild(removeName);
					System.out.println("\nA(n) " + removeName +" has been successfully removed as prey for the " + tree.getCursor().getName() + "!");
					option = "";
					break;
				}catch(IllegalArgumentException e) {
					System.out.println("\nName not found in prey section");
					option = "";
					break;
				}
			case "P":
				try {
					System.out.println(tree.listPrey());
					option = "";
					break;
				} catch (IsPlantException e) {
					System.out.println("plants can't have preys.");
					option = "";
					break;
				}
			case "C":
				System.out.println(tree.listFoodChain());
				option = "";
				break;
			case "F":
				tree.printOrganismTree();
				option ="";
				break;
			case "LP":
				tree.listAllPlants();
				option = "";
				break;
			case "R":
				tree.cursorReset();
				System.out.println("Cursor successfully reset to root!");
				option = "";
				break;
			case "M":
				System.out.print("Move to?: ");
				String cursorName = input.nextLine();
				try{
					tree.moveCursor(cursorName);
					System.out.println("\nCursor successfully moved to " + cursorName + "!");
					option = "";
					break;
				}catch(IllegalArgumentException | NullPointerException e) {
					System.out.println("\nError: This prey does not exist for this predator.");
					option = "";
					break;
				}
			case "Q":
				System.out.println();
				option = "";
				System.out.println("Program terminating....");
				quit = true;
				break;
			}
		}
	}
}
