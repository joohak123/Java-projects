/**
 * @author Joohak Lee 108699180 
 * Joohak.lee@stonybrook.edu
 * CSE 214 HW # 7
 * Recitation 1, TA: Daniel Calabria   
 * <dd><code>FollowGraphDriver</code><dd>  class that contains the main method and the user interface. 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
public class FollowGraphDriver {
	private static FollowerGraph last;
	/**
	 * main driver(method)for the FollowGraph
	 */
	public static void main(String [] args) {
		try {
			FileInputStream file = new FileInputStream("library.obj");
			ObjectInputStream inStream = new ObjectInputStream(file);
			FollowerGraph testing;
			testing = (FollowerGraph) inStream.readObject();
			last = testing;
			System.out.println("-------Object loaded------");
		} catch (FileNotFoundException e) {
			last = new FollowerGraph();
			System.out.println("Object created");
		} catch (IOException e) {
			last = new FollowerGraph();
			System.out.println("Object created");
		} catch (ClassNotFoundException e) {
			last = new FollowerGraph();
			System.out.println("Object created");
		}
		boolean quit = false;
		Scanner input = new Scanner(System.in);
		while(quit == false) {
			System.out.println();
			System.out.println(String.format("%-3s%-20s", "(U)", " Add User"));
			System.out.println(String.format("%-3s%-20s", "(C)", " Add Connection"));
			System.out.println(String.format("%-4s%-20s", "(AU)", " Load all Users"));
			System.out.println(String.format("%-4s%-20s", "(AC)", " Load all Connections"));
			System.out.println(String.format("%-3s%-20s", "(P)", " Print all Users"));
			System.out.println(String.format("%-3s%-20s", "(L)", " Print all Loops"));
			System.out.println(String.format("%-4s%-20s", "(RU)", " Remove User"));
			System.out.println(String.format("%-4s%-20s", "(RC)", " Remove Connection"));
			System.out.println(String.format("%-4s%-20s", "(SP)", " Find Shortest Path"));
			System.out.println(String.format("%-4s%-20s", "(AP)", " Find All Paths"));
			System.out.println(String.format("%-3s%-20s", "(Q)", " Quit"));
			System.out.println("Enter a selection:");
			String selection = input.nextLine();
			selection = selection.toUpperCase();
			switch(selection){
			case "U":
				System.out.println("Please enter the name of the user: ");
				String name = input.nextLine();
				if(name.equals("")) {
					System.out.println("Invald User name.");
				}
				else {
				last.addUser(name);
				}
				selection = "";
				break;
			case "C":
				System.out.println("Please enter the source of the connection to add:");
				String from = input.nextLine();
				System.out.println("Please enter the dest of the connection to add: ");
				String to = input.nextLine();
				last.addConnection(from, to);
				selection = "";
				break;
			case "AU":
				System.out.println("Enter the file name: ");
				String filename = input.nextLine();
				last.loadAllUsers(filename);
				selection = "";
				break;
			case "AC":
				System.out.println("Enter the file name: ");
				filename = input.nextLine();
				last.loadAllConnections(filename);
				selection = "";
				break;
			case "P":
				while(!selection.equals("")) {
					System.out.println();
					System.out.println(String.format("%-4s%-20s", "(SA)", " Sort Users by Name"));
					System.out.println(String.format("%-4s%-30s", "(SB)", " Sort Users by Number of Followers"));
					System.out.println(String.format("%-4s%-30s", "(SC)", " Sort Users by Number of Following"));
					System.out.println(String.format("%-3s%-20s", "(Q)", " Quit"));
					String target = input.nextLine();
					target = target.toUpperCase();
					if(target.equals("SA")) {
						NameComparator compName = new NameComparator();
						last.printAllUsers(compName);
						target = "";
					}
					if(target.equals("SB")) {
						FollowersComparator compFollower = new FollowersComparator();
						last.printAllUsers(compFollower);
					}
					if(target.equals("SC")) {
						FollowingComparator compFollowing = new FollowingComparator(); 
						last.printAllUsers(compFollowing);
						target = "";
					}
					if(target.equals("Q")) {
						selection = "";
					}
				}
				break;
			case "L":
				last.findAllLoops();
				selection = "";
				break;
			case "RU":
				System.out.println("Please enter the user to remove: ");
				String removePerson = input.nextLine();
				last.removeUser(removePerson);
				selection = "";
				break;
			case "RC":
				System.out.println("Please enter the source of the connection to remove: ");
				String removeSauce = input.nextLine();
				System.out.println("Please enter the dest of the connection to remove: ");
				String removeTo = input.nextLine();
				last.removeConnection(removeSauce, removeTo);
				selection = "";
				break;
			case "SP":
				System.out.println("Please enter the desired source: ");
				String shortSauce = input.nextLine();
				System.out.println("Please enter the desired destination: ");
				String shortTo = input.nextLine();
				last.shortestPath(shortSauce, shortTo);
				selection = "";
				break;
			case "AP":
				System.out.println("Please enter the desired source: ");
				String allSauce = input.nextLine();
				System.out.println("Please enter the desired destination:");
				String allTo = input.nextLine();
				last.allPaths(allSauce, allTo);
				selection = "";
				break;
			case "Q":
				quit = true;
				selection = "";
				System.out.println("Program terminated....");
				break;
			}
		}
		try {
			FileOutputStream file2 = new FileOutputStream("library.obj");
			ObjectOutputStream outStream = new ObjectOutputStream(file2);
			outStream.writeObject(last);
			System.out.println("---object saved---");
		}catch(IOException ex) {
		}
	}
}
