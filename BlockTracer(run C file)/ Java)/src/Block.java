/**
 * 
 * @author Joohak Lee 108699180 Recitation 01
 *
 *<code>Block </code> that is used for <code>Stack</code>.
 *This class implements codes from <code>Variable</code>.
 *<code>Block</code> contains 10 <code>Variable</code>.
 */

import java.util.*;

public class Block {
	private Variable [] datalist;
	private int counter;
	private int listlength;
	/**
	 * no argument constructor creates a variable Object.
	 */
	public Block() {
		counter = 0;
		listlength = 10;
		datalist = new Variable[listlength];
	}
	/**
	 * retrieves the variable array
	 * @return
	 * the variable array
	 */
	public Variable[] getDatalist() {
		return this.datalist;
	}
	/**
	 * retrieves the number of variable objects in the variable array
	 * @return
	 * the number of variable objects in integer
	 */
	public int getCounter() {
		return counter;
	}
	/**
	 * adds the given variable into the array.
	 * @param data
	 * variable that is going to replace the objects in the array
	 */
	public void addData(Variable data) {
		datalist[counter] = data;
		counter ++;
	}
}
