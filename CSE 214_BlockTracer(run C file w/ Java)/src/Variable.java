/**
 * 
 * @author Joohak Lee 108699180 Recitation 01
 *
 *<code>Variable </code> that is used for <code>Block</code>
 *Variable has a name and an initial value.
 */
public class Variable {
	private String name;
	private int InitialValue;
/**
* no argument constructor to create a variable object.
*/
	public Variable() {
		InitialValue = 0;
		name = "";
	}

/**
 * retrieves the name of the variable.
 * @return
 * name of the variable in String.
 */
	public String getName() {
		return name;
	}
/**
 * sets the name of the variable
 * @param name
 * set the name of the variable as a given string.
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * retrieves the value of the variable.
 * @return
 * value of the variable in integer.
 */
	public int getInitialValue() {
		return InitialValue;
	}

/**
 * sets the value of the variable
 * @param value
 * set the value of the variable as a given integer.
 */
	public void setInitialValie(int value) {
		this.InitialValue = value;
	}
/**
 *  prints out the Song's name, artist, album and length in a neatly formatted look.
 *  @return
 *   a String that contains variable's name and initial value.
 */
	public String toString() {
		String variable = "";
		variable = String.format("%-20s%-13s", this.getName(), this.getInitialValue());
		return variable;
	}
}
