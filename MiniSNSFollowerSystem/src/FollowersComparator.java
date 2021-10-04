/**
 * @author Joohak Lee 108699180 
 * Joohak.lee@stonybrook.edu
 * CSE 214 HW # 7
 * Recitation 1, TA: Daniel Calabria   
 * <dd><code>FollowersComparator Comparator</code><dd>will be used to sort your list of users. 
 */
import java.util.Comparator;

public class FollowersComparator implements Comparator{
	/**
	 * method to compare the two objects
	 */
	public int compare(Object o1, Object o2) {
		User e1 = (User)o1;
		User e2 = (User)o2;
		if(e1.getFollower() == e2.getFollower()) {
			return 0;
		}
		else if(e1.getFollower() > e2.getFollower()) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
