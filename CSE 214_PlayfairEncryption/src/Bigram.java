/**
 * @author Joohak Lee 108699180 Recitation 1
 * <dd><code>Bigram</code> which represents a bigram (two characters)
 * contain two char member variables for the two characters it represents. 
 */

public class Bigram {
	private char first;
	private char second;
	/**
	 * no argument constructor to create the bigram object
	 */
	public Bigram() {
	}
	/**
	 * retrieve the first character in the Bigram
	 * @return
	 * the first character
	 */
	public char getFirst() {
		return first;
	}
	/**
	 * sets the first character in the Bigram as a given character
	 * @param first
	 * a character to set the Bigram's first character
	 */
	public void setFirst(char first) {
		this.first = first;
	}
	/**
	 * retrieve the second character in the Bigram
	 * @return
	 * the second character
	 */
	public char getSecond() {
		return second;
	}
	/**
	 * sets the second character in the Bigram as a given character
	 * @param second
	 * a character to set the Bigram's second character
	 */
	public void setSecond(char second) {
		this.second = second;
	}
	/**
	 * prints out the characters in Bigram
	 * @return
	 * the characters in string
	 */
	public String toString() {
		String result = "";
		result = Character.toString(first) + Character.toString(second);
		return result;
	}
}
