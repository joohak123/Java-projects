/**
 * @author Joohak Lee 108699180 
 * Joohak.lee@stonybrook.edu
 * CSE 214 HW # 7
 * Recitation 1, TA: Daniel Calabria   
 * <dd><code>FollowerGraph</code><dd>  class that contains an adjacency matrix for the users. 
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FollowerGraph implements Serializable{
	public static final int MAX_USERS = 100;
	private ArrayList<User> users;
	private boolean [][] connections;
	/**
	 * No argument Constructor to create the FollowerGraph object
	 */
	public FollowerGraph() {
		users = new ArrayList <>();
		connections = new boolean [MAX_USERS][MAX_USERS];
		list = new ArrayList[100];
		for(int i =0 ; i < 100; i ++) {
			list[i] = new ArrayList<>();
		}
	}
	/**
	 * Adds the User into the array users
	 * @param userName
	 * string of the name to createt the user object with
	 */
	public void addUser(String userName) {
		if(users.size() == MAX_USERS) {
			System.out.println("MAX USERS can't add anymore users");
			return;
		}
		if(findUser(userName) == -1) {
			User user = new User();
			user.setUserName(userName);
			users.add(user);
			System.out.println(user.getUserName() + " has been  added");
		}
	}
	/**
	 * remove the user from the users array
	 * @param userName
	 * String of the name of the user object to be removed
	 */
	public void removeUser(String userName) {
		int position = findUser(userName);
		if(position == -1) {
			System.out.println("***** "+userName + " Is not Found" + " *******");
			return;
		}
		if(position >= 0) {
			for(int i = 0; i < users.size(); i ++) {
				if (users.get(position).getFollower() >= 1 || users.get(position).getFollowing() >= 1) {
					if (connections[position][i] == true) {
						this.removeConnection(users.get(position).getUserName(), users.get(i).getUserName());
						connections[position][i] = false;
					}
					if (connections[i][position] == true) {
						this.removeConnection(users.get(i).getUserName(), users.get(position).getUserName());
						connections[i][position] = false;
					}
				}
			}
			User filler = new User();
			users.set(position, filler);
			this.checkConnection();
			System.out.println(userName + " has been removed");
		}
	}
	/**
	 * helper method to find the User in the users array
	 * @param name
	 * name of the User in string
	 * @return
	 * the index of the array inf found, and -1 if not found.
	 */
	public int findUser(String name) {
		for(int i = 0; i < users.size(); i++) {
			if(name.compareToIgnoreCase(users.get(i).getUserName()) == 0) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * adds the connection between the two Users
	 * @param userFrom
	 * Name of the From person
	 * @param userTo
	 * Name of the to person
	 */
	private int count = 0;
	public void addConnection(String userFrom, String userTo) {
		int fromIndex = findUser(userFrom);
		int toIndex = findUser(userTo);
		if(fromIndex == -1) {
			System.out.println("***** "+userFrom + " Is not Found" + " *******");
		}
		if(toIndex == -1) {
			System.out.println("*******" + userTo + " Is not Found ********" );
		}
		if(fromIndex >= 0 && toIndex >=0 && connections[fromIndex][toIndex] == false) {
			connections[fromIndex][toIndex] = true;
			users.get(fromIndex).setFollowing(users.get(fromIndex).getFollowing() +1 );
			users.get(toIndex).setFollower(users.get(toIndex).getFollower() +1 );
			count ++;
			System.out.println(userFrom + ", " + userTo + " coonection has been added");
			this.checkConnection();
		}
	}
	
	/**
	 * remove the connection between the two users
	 * @param userFrom
	 * name of the from person
	 * @param userTo
	 * name of the to person
	 */
	public void removeConnection(String userFrom, String userTo) {
		int fromIndex = findUser(userFrom);
		int toIndex = findUser(userTo);
		if(fromIndex == -1) {
			System.out.println("***** "+userFrom + " Is not Found" + " *******");
		}
		if(toIndex == -1) {
			System.out.println("*******" + userTo + " Is not Found ********" );
		}
		if( connections[fromIndex][toIndex] == true && fromIndex >= 0 && toIndex >=0) {
			if(users.get(fromIndex).getFollowing()>= 1) {
				users.get(fromIndex).setFollowing(users.get(fromIndex).getFollowing() -1 );
				connections[fromIndex][toIndex] = false;
			}
			if(users.get(toIndex).getFollower() >= 1) {
				users.get(toIndex).setFollower(users.get(toIndex).getFollower() -1 );
				connections[fromIndex][toIndex] = false;
			}
			this.checkConnection();
			System.out.println(users.get(fromIndex).getUserName() + ", " + users.get(toIndex).getUserName() + " connection has been removed.");
		}
	}
	private ArrayList <String> testing;
	/**
	 * return the string which has the shortest path from a source user to a target user 
	 * @param userFrom
	 * name of the source user in a string
	 * @param userTo
	 * name of the target user in a string
	 * @return
	 * return the shortest path in a string
	 */
	public String shortestPath(String userFrom, String userTo) {
		testing = new ArrayList<>();
		String test  = "";
		List<String> paths = new ArrayList <>();
		boolean [] visited = new boolean [100];
		int fromIndex = findUser(userFrom);
		int toIndex = findUser(userTo);
		if(fromIndex == -1 || toIndex == -1) {
			System.out.println("User not Found.");
		}
		else {
			searchPath(fromIndex, toIndex, visited, paths);
			int count= 0, location= 0, originalCount = 0;
				if(testing.size() > 0) {
				for(int j = 0; j < testing.get(0).length(); j++) {
					if(testing.get(0).charAt(j) == '#') {
						originalCount ++;
					}
				}
				for(int i = 0; i < testing.size(); i++) {
					count = 0;
					for(int k = 0; k < testing.get(i).length(); k++) {
						if(testing.get(i).charAt(k) == '#') {
							count ++;
						}
					}
					if(originalCount >= count) {
						originalCount = count;
						location = i;
					}
				}
				test = testing.get(location);
				test = test.replaceAll("#", " -> ");
				System.out.println(test);
				}
				else {
					System.out.println("No path available.");
				}
			}
		return test;
	}
	/**
	 * returns all the paths of a given source person to a given target person in an array of strings
	 * @param userFrom
	 * name of the source user in a string
	 * @param userTo
	 * name of the target user in a astring
	 * @return
	 * the all the paths of the source user to a target user
	 */
    public List<String> allPaths(String userFrom, String userTo){
		testing = new ArrayList<>();
		List<String> paths = new ArrayList <>();
		boolean [] visited = new boolean [100];
		int fromIndex = findUser(userFrom);
		int toIndex = findUser(userTo);
		if(fromIndex == -1 || toIndex == -1) {
			System.out.println("User Not found.");
		}
		else {
			searchPath(fromIndex, toIndex, visited, paths);
			if(testing.size() > 0) {
				System.out.println("There are a total of " + testing.size() + " paths:");
				for(int i =0 ; i < testing.size(); i++) {
					testing.set(i, testing.get(i).replaceAll("#"," -> "));
					System.out.println("\t"+ testing.get(i));
				}
			}
			else {
				System.out.println("No path available.");
			}
		}
		return paths;
	}
    /**
     * finds all the loops within the graph
     * @return
     * the all the loops in a list of String
     */
    public List<String> findAllLoops(){
    	testing = new ArrayList<>();
		List<String> paths = new ArrayList <>();
		boolean [] visited = new boolean [100];
		int fromIndex = 0;
		for(int i = 0; i < users.size(); i++) {
			searchPath(fromIndex, i, visited, paths);
		}
    	if(testing.size() ==0 || count == 0) {
    		System.out.println("No Loops.");
		}
		else {
			System.out.println("There are a total of " + testing.size() + " paths:");
			for(int i =0 ; i < testing.size(); i++) {
				testing.set(i, testing.get(i).replaceAll("#"," -> "));
				System.out.println("\t"+ testing.get(i));
			}
		}
    	return testing;
    }

    /**
     * helper method to search the path to find the target person from a source person
     * @param start
     * index of the start person in the users array
     * @param end
     * index of the target person in the users array
     * @param visited
     * boolean array to help whether the location was visited
     * @param paths
     * paths of the target person to a source persn
     */
	public void searchPath(int start, int end, boolean [] visited, List<String> paths) {
		visited[start] = true;
		String print = "";
		if(start == end) {
			paths.add(users.get(end).getUserName());
			for(int i = 0; i<paths.size(); i ++) {
				print += paths.get(i) + "#";
			}
			print = print.substring(0, print.length()-1);
			testing.add(print);
			visited[start] = false;
			return;
		}
		for(Integer i : list[start]) {
			if(!visited[i]) {
				paths.add(users.get(start).getUserName());
				searchPath(i, end, visited,paths);
				paths.remove(users.get(i).getUserName());
				paths.remove(users.get(start).getUserName());
			}
		}
		visited[start] = false;
	}
	/**
	 * prints out all the users in the users array depends on the Comparator 
	 * @param comp
	 * comparator to compare the users with: name, followings, or followers.
	 */
	public void printAllUsers(Comparator comp) {
		ArrayList<User> testing = new ArrayList<>();
		testing = (ArrayList) users.clone();
		System.out.printf("%-25s%-25s%-25s", "User Name", "Number of Followers", "Number of Following");
		System.out.println();
		for(int i = 0; i < testing.size(); i ++) {
			for(int k = i + 1; k < testing.size(); k++) {
				if(comp.compare(testing.get(i), testing.get(k)) < 0) {
					User temporary = testing.get(i);
					testing.set(i, testing.get(k));
					testing.set(k, temporary);
				}
			}
		}
		for(int i = 0; i < testing.size(); i++) {
			if(!testing.get(i).getUserName().equals("!####!!!")) {
				System.out.println(testing.get(i));
			}
		}
	}
	/**
	 * loads all the users in the file
	 * @param filename
	 * name of the given file
	 */
	public void loadAllUsers(String filename) {
		try {
			FileInputStream file = new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(file);
			BufferedReader reader = new BufferedReader(inputStream);
			String words = reader.readLine();
			while(words != null) {
				this.addUser(words);
				words = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("File NOT FOUND please re enter the file name");
		}
	}
	
	public boolean [][] visit = new boolean [100][100];
    private ArrayList<Integer>[] list;
    /**
     * helping method to check the connection between all the users.
     */
	public void checkConnection() {
		for(int i = 0; i < 100; i++) {
			for(int k = 0; k <100; k++) {
				if(connections[i][k] && !visit[i][k]) {
					list[i].add(k);
					visit[i][k] = true;
				}
			}
		}
	}
	/**
	 * loads all the connections within the file
	 * @param filename
	 * name of the given file in a string
	 */
	public void loadAllConnections(String filename) {
		try {
			FileInputStream file = new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(file);
			BufferedReader reader = new BufferedReader(inputStream);
			String words = reader.readLine();
			while(words != null) {
				int location = words.indexOf(",");
				String userFrom = words.substring(0, location);
				String userTo = words.substring(location + 2);
				this.addConnection(userFrom, userTo);
				words = reader.readLine();
			}
		}catch(IOException e) {
			System.out.println("File NOT FOUND please re enter the file name");
		}
	}	
}
