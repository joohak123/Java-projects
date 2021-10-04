
/**
 * @author Joohak Lee 108699180 
 * Joohak.lee@stonybrook.edu
 * CSE 214 HW # 7
 * Recitation 1, TA: Daniel Calabria   
 * <dd><code>User</code><dd>  A User class that contains the name of the user (String), indexPos (int), and static variable userCount (int).
 */
import java.io.Serializable;
public class User implements Serializable{
	static int userCount = 0;
	private String userName;
	private int indexPos;
	private int follower = 0;
	private int following = 0;
	private int userCount2 = 0;
	/**
	 * no Argument constructor to create the user object. Increase the userCount by 1.
	 */
	public User() {
		userName = "!####!!!";
		userCount += 1;
		indexPos = userCount;
		userCount2 = userCount;
	}
	/**
	 * Getter for the user Count
	 * @return
	 * the int that is the user count;
	 */
	public int getUserCount() {
		return userCount;
	}
	/**
	 * returns the name of the User
	 * @return
	 * the String that is the user's name
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * returns the followers of the user
	 * @return
	 * the int that is the number of the followers that user has 
	 */
	public int getFollower() {
		return follower;
	}
	/**
	 * sets the follower number of the user
	 * @param follower
	 * the int that is the follower of the user;
	 */
	public void setFollower(int follower) {
		this.follower = follower;
	}
	/**
	 * returns the User's following number
	 * @return
	 * the int that is the user's following number
	 */
	public int getFollowing() {
		return following;
	}
	/**
	 * sets the following number of the user
	 * @param following
	 * int that is the following number of the user
	 */
	public void setFollowing(int following) {
		this.following = following;
	}
	/**
	 * index location of the User is going to be saved in an array
	 * @return
	 * the index location of the User in an int
	 */
	public int getIndexPos() {
		return indexPos;
	}
	/**
	 * returns the String that includes the uesr's name + follower + followings in a neatly format.
	 */
	public String toString() {
		String result;
		if(this.getUserName().equals("!####!!!")) {
			result = "";
		}
		else {
		result = String.format("%-25s%-25s%-25s", this.getUserName(), "         "+this.getFollower(), "       " + this.getFollowing());
		}
		return result;
	}
	/**
	 * set's the user name with a given string
	 * @param userName
	 * String that is the name of the user
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
