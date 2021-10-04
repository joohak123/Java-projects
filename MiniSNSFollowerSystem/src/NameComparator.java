/**
 * @author Joohak Lee 108699180 
 * Joohak.lee@stonybrook.edu
 * CSE 214 HW # 7
 * Recitation 1, TA: Daniel Calabria   
 * <dd><code>Name Comparator</code><dd>will be used to sort your list of users. 
 */

import java.util.Comparator;

public class NameComparator implements Comparator{
	/**
	 * method to compare the two objects
	 */
	public int compare(Object arg0, Object arg1) {
		User e1 = (User)arg0;
		User e2 = (User)arg1;
		if(e1.getUserName().compareToIgnoreCase(e2.getUserName()) > 0) {
			return -1;
		}
		else if (e1.getUserName().compareToIgnoreCase(e2.getUserName()) == 0) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
